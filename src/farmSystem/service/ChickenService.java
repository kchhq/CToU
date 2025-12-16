package farmSystem.service;

import Entity.livestock.Chicken;
import Entity.livestock.Livestock;
import breedingSystem.traits.Trait;
import breedingSystem.traits.TraitEffectContext;
import farmSystem.Product;

public class ChickenService {

    // 달걀 수확 시 HP 비용
    private static final int EGG_HARVEST_COST = 10;

    public void interactHarvestEgg(Chicken chicken, Product productInventory) {

        // 1) 기본 조건 체크
        if (chicken.getInteractedToday()) {
            System.out.println("동물이 지쳐 오늘은 더 이상 상호작용 할 수 없습니다.");
            return;
        }
        if (chicken.getHp() < Livestock.MAX_HP) {
            System.out.println(chicken.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 달걀을 수확할 수 없습니다. (현재 HP " + chicken.getHp() + ")");
            return;
        }

        // 2) Trait 훅 실행 (공용 + 종특)
        TraitEffectContext ctx = new TraitEffectContext(productInventory);

        Trait common = chicken.getCommonTrait();
        if (common != null) common.onBeforeInteraction(ctx);

        Trait species = chicken.getSpeciesTrait();
        if (species != null) species.onBeforeInteraction(ctx);

        // 3) HP 처리
        // Stressfull: HP를 10으로 강제 (100에서 시작해서 데미지 90을 줌)
        if (ctx.getForceHpTo() != null) {
            int targetHp = ctx.getForceHpTo();
            int dmg = Math.max(0, chicken.getHp() - targetHp);
            chicken.takeDamage(dmg);
        } else if (!ctx.isCancelHpDamage()) {
            // 특성이 발동 안할 경우
            chicken.takeDamage(EGG_HARVEST_COST);
        }

        // 4) 생산물 처리
        // Careless: 생산물 추가 취소
        if (ctx.isCancelProductAdd()) {
            System.out.println(chicken.getName() + "이(가) 알을 낳다가 깨뜨렸습니다...");
        } else {
            // Golden_egg: 황금알로 대체
            if (ctx.isReplaceWithGoldenEgg()) {
                productInventory.addGoldenEggs(1);
                System.out.println(chicken.getName() + "이(가) 황금 알을 낳았습니다!");
            } else {
                productInventory.addEggs(1);
            }
        }

        // 5) 하루 상호작용 처리
        chicken.setInteractedToday(true);

        System.out.println(chicken.getName() + "의 달걀을 수확했습니다. 현재 HP: " + chicken.getHp());
    }
}

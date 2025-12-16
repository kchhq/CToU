package farmSystem.service;

import Entity.livestock.Livestock;
import Entity.livestock.Unicorn;
import breedingSystem.traits.TraitEffectContext;
import breedingSystem.traits.Trait;
import farmSystem.Finance;

import java.util.concurrent.ThreadLocalRandom;

public class UnicornService {

    // 경마 대회 출전 시 HP 비용
    private static final int RACE_HP_COST = 30;

    public void interactRace(Unicorn unicorn, Finance finance) {

        // 1. 오늘 이미 상호작용 했는지
        if (unicorn.getInteractedToday()) {
            System.out.println("동물이 지쳐 오늘은 더 이상 상호작용 할 수 없습니다.");
            return;
        }

        // 2. HP가 MAX인지
        if (unicorn.getHp() < Livestock.MAX_HP) {
            System.out.println(unicorn.getName() +
                    ": HP가 " + Livestock.MAX_HP +
                    "이 아니라 경마 대회에 출전할 수 없습니다. (현재 HP " + unicorn.getHp() + ")");
            return;
        }

        // 3. Trait 훅 실행
        // Product를 쓰지 않으므로 null로 넣어도 됨
        TraitEffectContext ctx = new TraitEffectContext(null);

        Trait common = unicorn.getCommonTrait();
        if (common != null) common.onBeforeInteraction(ctx);

        Trait species = unicorn.getSpeciesTrait();
        if (species != null) species.onBeforeInteraction(ctx);

        // 4. 경마 기본 결과 (1~5등)
        int rank = ThreadLocalRandom.current().nextInt(1, 6);

        // 5. Trait 결과 보정
        if (ctx.getForceRaceRank() != null) {
            rank = ctx.getForceRaceRank();
        } else if (ctx.getCapWorstRaceRank() != null) {
            // 4~5등이면 3등으로 끌어올림
            rank = Math.min(rank, ctx.getCapWorstRaceRank());
        }

        // 6. 보상 계산/지급
        int reward = rank * 100;
        finance.addMoney(reward);

        // 7. HP 감소
        unicorn.takeDamage(RACE_HP_COST);

        // 8. 하루 상호작용 처리
        unicorn.setInteractedToday(true);

        // 9. 결과 출력
        System.out.println(unicorn.getName() + "이(가) 경마 대회에 출전해 "
                + rank + "등을 했습니다! 보상: " + reward + "원");
        System.out.println("HP 감소 → 현재 HP: " + unicorn.getHp());
    }
}

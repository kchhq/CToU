package breedingSystem.traits;

import java.util.concurrent.ThreadLocalRandom;

public enum ChickenTrait implements Trait {
    GOLDEN_EGG {
        @Override public String id() { return "GOLDEN_EGG"; }
        @Override public void onBeforeInteraction(TraitEffectContext ctx) {
            // 30% 확률로 황금 알로 변경
            if (ThreadLocalRandom.current().nextInt(100) < 30) {
                ctx.replaceWithGoldenEgg();
            }
        }
    },
    CARELESS {
        @Override public String id() { return "CARELESS"; }
        @Override public void onBeforeInteraction(TraitEffectContext ctx) {
            // 20% 확률로 생산물 추가 취소(HP는 정상 감소)
            if (ThreadLocalRandom.current().nextInt(100) < 20) {
                ctx.cancelProductAdd();
            }
        }
    }
}

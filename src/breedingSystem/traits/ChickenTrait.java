package breedingSystem.traits;

import java.util.concurrent.ThreadLocalRandom;

public enum ChickenTrait implements Trait {
    CHICKEN_NORMAL {
        @Override
        public String id() {
            return "NORMAL";
        }

        @Override public String displayName() { return "평범함"; }

        @Override
        public String description() {
            return "평범한 동물입니다.";
        }
        // 효과 없음
    },

    GOLDEN_EGG {
        @Override public String id() { return "GOLDEN_EGG"; }
        @Override public String displayName() { return "황금알"; }
        @Override public void onBeforeInteraction(TraitEffectContext ctx) {
            // 30% 확률로 황금 알로 변경
            if (ThreadLocalRandom.current().nextInt(100) < 30) {
                ctx.replaceWithGoldenEgg();
            }
        }
    },
    CARELESS {
        @Override public String id() { return "CARELESS"; }
        @Override public String displayName() { return "사고뭉치"; }
        @Override public void onBeforeInteraction(TraitEffectContext ctx) {
            // 20% 확률로 생산물 추가 취소(HP는 정상 감소)
            if (ThreadLocalRandom.current().nextInt(100) < 20) {
                ctx.cancelProductAdd();
            }
        }
    }
}

package breedingSystem.traits;

import java.util.concurrent.ThreadLocalRandom;

public enum CommonTrait implements Trait {

    NORMAL {
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

    // LIVELY : 상호작용 시 40% 확률로 HP가 감소하지 않음
    LIVELY {
        @Override public String id() { return "LIVELY"; }

        @Override public String displayName() { return "활발함"; }

        @Override
        public String description() {
            return "활발한 동물입니다. 상호작용 시 HP가 감소하지 않을 수 있습니다.";
        }

        @Override public void onBeforeInteraction(TraitEffectContext ctx) {
            // 40% 확률로 HP 감소 취소
            if (ThreadLocalRandom.current().nextInt(100) < 40) {
                ctx.cancelHpDamage();
            }
        }
    },
    // STRESSFULL : 상호작용 시 20% 확률로 HP가 10이 됨
    STRESSFULL {
        @Override public String id() { return "STRESSFULL"; }


        @Override
        public String description() {
            return "예민한 동물입니다. 상호작용 시 큰 스트레스를 받을 수 있습니다.";
        }

        @Override public String displayName() { return "예민함"; }

        @Override public void onBeforeInteraction(TraitEffectContext ctx) {
            // 20% 확률로 HP 10 강제
            if (ThreadLocalRandom.current().nextInt(100) < 20) {
                ctx.forceHpTo(10);
            }
        }
    }
}

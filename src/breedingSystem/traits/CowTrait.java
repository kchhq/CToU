package breedingSystem.traits;

public enum CowTrait implements Trait {
    COW_NORMAL {
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


    DOUBLE_MILK {
        @Override
        public String id() {
            return "DOUBLE_MILK";
        }

        @Override public String displayName() { return "명품"; }

        @Override
        public String description() {
            return "일정 확률로 두 배의 우유를 얻습니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            if (TraitRng.chance(30)) { // 30% 확률
                ctx.multiplyProduct(2); // 생산물 2배
            }
        }
    };
}

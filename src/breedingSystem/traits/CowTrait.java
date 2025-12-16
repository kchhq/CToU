package breedingSystem.traits;

public enum CowTrait implements Trait {

    DOUBLE_MILK {
        @Override
        public String id() {
            return "DOUBLE_MILK";
        }

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

package breedingSystem.traits;

public enum DeerTrait implements Trait {

    GOLDEN_ANTLER {
        @Override
        public String id() {
            return "GOLDEN_ANTLER";
        }

        @Override
        public String description() {
            return "일정 확률로 2배 가격의 황금 뿔을 얻습니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            if (TraitRng.chance(30)) {
                ctx.replaceWithGoldenAntler();
            }
        }
    },

    BREAKAWAY {
        @Override
        public String id() {
            return "BREAKAWAY";
        }

        @Override
        public String description() {
            return "10% 확률로 뿔갈이를 하여 HP가 10이 됩니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            if (TraitRng.chance(10)) {
                ctx.forceHpTo(10);
            }
        }
    };
}

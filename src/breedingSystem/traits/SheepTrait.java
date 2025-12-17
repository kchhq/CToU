package breedingSystem.traits;

public enum SheepTrait implements Trait {
    SHEEP_NORMAL {
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

    GOLDEN_WOOL {
        @Override
        public String id() {
            return "GOLDEN_WOOL";
        }

        @Override public String displayName() { return "황금양털"; }

        @Override
        public String description() {
            return "30% 확률로 2배 가격의 황금 양털을 얻습니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            if (TraitRng.chance(30)) {   // 30% 확률
                ctx.replaceWithGoldenWool();
            }
        }
    };
}

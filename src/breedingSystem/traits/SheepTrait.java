package breedingSystem.traits;

public enum SheepTrait implements Trait {

    GOLDEN_WOOL {
        @Override
        public String id() {
            return "GOLDEN_WOOL";
        }

        @Override
        public String description() {
            return "30% 확률로 2배 가격의 황금 양털을 얻습니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            if (TraitRng.chance(30)) {   // ✅ 30% 확률
                ctx.replaceWithGoldenWool();
            }
        }
    };
}

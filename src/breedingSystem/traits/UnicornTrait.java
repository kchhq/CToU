package breedingSystem.traits;

public enum UnicornTrait implements Trait {

    // atLeast3rd[승부욕] : 경주에서 최소 3등 보장
    AT_LEAST_3RD {
        @Override public String id() { return "AT_LEAST_3RD"; }

        @Override
        public String description() {
            return "승부욕: 경주에서 최소 3등을 합니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            ctx.capWorstRaceRank(3);
        }
    },

    // winAlways[우월종] : 일정 확률로 발동하면 무조건 1등
    WIN_ALWAYS {
        private static final int ACTIVATE_CHANCE = 20; // ✅ 너가 원하는 확률로 바꿔

        @Override public String id() { return "WIN_ALWAYS"; }

        @Override
        public String description() {
            return "우월종: 일정 확률로 발동하면 그날 경주를 무조건 우승합니다.";
        }

        @Override
        public void onBeforeInteraction(TraitEffectContext ctx) {
            if (TraitRng.chance(ACTIVATE_CHANCE)) {
                ctx.forceRaceRank(1);
            }
        }
    };
}

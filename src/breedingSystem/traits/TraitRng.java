package breedingSystem.traits;

import java.util.concurrent.ThreadLocalRandom;

public class TraitRng {
    public static boolean chance(int percent) {
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }
}

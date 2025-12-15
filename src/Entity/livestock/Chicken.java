package Entity.livestock;

import common.enums.PreferredFeed;

public class Chicken extends Livestock {

    public Chicken(String name) {
        super(name, "Chicken");
    }

    @Override
    protected int getFeedAmount() {
        return 30;
    }

    @Override
    public PreferredFeed getPreferredFeed() {
        return PreferredFeed.CHICKEN;
    }

    @Override
    protected int getStressFromUnpreferredFeed() {
        return 25;
    }

    @Override
    protected int getStressDecreaseAmount() {
        return 40;
    }
}
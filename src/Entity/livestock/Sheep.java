package Entity.livestock;

import common.enums.PreferredFeed;

public class Sheep extends Livestock {

    public Sheep(String name) {
        super(name, "Sheep");
    }

    @Override
    protected int getFeedAmount() {
        return 40;
    }

    @Override
    public PreferredFeed getPreferredFeed() {
        return PreferredFeed.SHEEP;
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
package Entity.livestock;

import common.enums.PreferredFeed;

public class Chicken extends Livestock {

    public Chicken(String name) {
        super(name, "Chicken");
    }

    @Override
    protected int getFeedAmount(){
        return 30;
    }
    @Override
    public PreferredFeed getPreferredFeed() {
        return PreferredFeed.CHICKEN;
    }

}
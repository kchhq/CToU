package Entity.livestock;

import common.enums.PreferredFeed;

public class Cow extends Livestock {

    public Cow(String name) {
        super(name, "Cow");
    }

    @Override
    protected int getFeedAmount(){
        return 20;
    }

    @Override
    public PreferredFeed getPreferredFeed() {
        return PreferredFeed.COW;
    }
}
package Entity.livestock;

import common.enums.PreferredFeed;

public class Unicorn extends Livestock {

    public Unicorn(String name, String type) {
        super(name, "Unicorn");
    }

    @Override
    protected int getFeedAmount(){
        return 30;
    }
    @Override
    public PreferredFeed getPreferredFeed() {
        return PreferredFeed.UNICORN;
    }

}

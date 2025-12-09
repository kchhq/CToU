package Entity.livestock;


import common.enums.PreferredFeed;

/**
 * 사슴 개체 (Entity)
 * - 자신의 상태(HP, 이름)를 정의하고, 상태를 변경
 */
public class Deer extends Livestock {


    public Deer(String name) {
        super(name, "Deer");
    }

    @Override
    protected int getFeedAmount(){
        return 40;
    }

    @Override
    public PreferredFeed getPreferredFeed() {
        return PreferredFeed.DEER;
    }
}
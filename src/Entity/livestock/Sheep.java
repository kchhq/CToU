package Entity.livestock;

public class Sheep extends Livestock {

    public Sheep(String name) {
        super(name, "Sheep");
    }

    @Override
    protected int getFeedAmount() {
        return 40;
    }

}
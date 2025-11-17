package Entity.livestock;

public class Unicorn extends Livestock {

    public Unicorn(String name, String type) {
        super(name, "Unicorn");
    }

    @Override
    protected int getFeedAmount(){
        return 30;
    }
}

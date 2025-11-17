package Entity.livestock;

public class Chicken extends Livestock {

    public Chicken(String name) {
        super(name, "Chicken");
    }

    @Override
    protected int getFeedAmount(){
        return 30;
    }

}
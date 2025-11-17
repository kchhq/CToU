package Entity.livestock;

public class Cow extends Livestock {

    public Cow(String name) {
        super(name, "Cow");
    }

    @Override
    protected int getFeedAmount(){
        return 20;
    }

}
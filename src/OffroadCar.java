public class OffroadCar extends Vehicle
{
    String engineType;
    boolean isPickUp;
    public OffroadCar(String name, double width, double length, double height, String engineType, boolean isPickUp)
    {
        super(name, width, length, height);
        this.engineType = engineType;
        this.isPickUp = isPickUp;

    }
}


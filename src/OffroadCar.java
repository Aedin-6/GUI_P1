public class OffroadCar extends Vehicle
{
    String engineType;
    boolean isPickUp;
    public OffroadCar(String name, double width, double length, double height, String engineType, boolean isPickUp,
                      int engineCap)
    {
        super(name, width, length, height, engineCap);
        this.engineType = engineType;
        this.isPickUp = isPickUp;

    }
}


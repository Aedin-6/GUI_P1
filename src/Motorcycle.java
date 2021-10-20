public class Motorcycle extends Vehicle
{
    boolean isTricycle;
    String bikeType;
    public Motorcycle(String name, double width, double length, double height, String bikeType, boolean isTricycle)
    {
        super(name, width, length, height);
        this.isTricycle = isTricycle;
        this.bikeType = bikeType;
    }
}

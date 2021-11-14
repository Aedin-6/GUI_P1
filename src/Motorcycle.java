public class Motorcycle extends Vehicle
{
    boolean isTricycle;
    String bikeType;
    public Motorcycle(String name, double width, double length, double height, String bikeType, boolean isTricycle, int engineCap)
    {
        super(name, width, length, height, engineCap);
        this.isTricycle = isTricycle;
        this.bikeType = bikeType;
    }
}

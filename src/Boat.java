public class Boat extends Vehicle
{
    String boatType;
    double displacement;
    public Boat(String name, double width, double length, double height, int engineCap, String boatType, double displacement)
    {
        super(name, width, length, height, engineCap);
        this.boatType = boatType;
        this.displacement = displacement;
    }
}

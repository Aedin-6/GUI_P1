public class Boat extends Vehicle
{
    String boatType;
    double displacement;
    public Boat(String name, double width, double length, double height, String boatType, double displacement)
    {
        super(name, width, length, height);
        this.boatType = boatType;
        this.displacement = displacement;
    }
}

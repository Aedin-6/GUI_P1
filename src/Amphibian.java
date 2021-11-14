public class Amphibian extends Vehicle
{
    double displacement;
    boolean wheels;

    public Amphibian(String name, double width, double length, double height, double displacement, int engineCap, boolean wheels)
    {
        super(name, width, length, height, engineCap);
        this.displacement = displacement;
        this.wheels =  wheels;
    }
}

public class CityCar extends Vehicle
{
    int numberOfDoors;
    String engineType;
    public CityCar(String name, double width, double length, double height, int numberOfDoors, String engineType,
                   int engineCap)
    {
        super(name, width, length, height, engineCap);
        this.numberOfDoors = numberOfDoors;
        this.engineType = engineType;
    }
}

public class CityCar extends Vehicle
{
    int numberOfDoors;
    String engineType;
    public CityCar(String name, double width, double length, double height, int numberOfDoors, String engineType)
    {
        super(name, width, length, height);
        this.numberOfDoors = numberOfDoors;
        this.engineType = engineType;
    }
}

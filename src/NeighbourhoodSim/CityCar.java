package NeighbourhoodSim;

public class CityCar extends Vehicle
{
    protected int numberOfDoors;
    protected String engineType;

    public CityCar(String name, double width, double length, double height, int numberOfDoors, String engineType,
                   int engineCap)
    {
        super(name, width, length, height, engineCap);
        this.numberOfDoors = numberOfDoors;
        this.engineType = engineType;
    }
}

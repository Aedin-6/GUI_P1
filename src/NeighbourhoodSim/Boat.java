package NeighbourhoodSim;

public class Boat extends Vehicle
{
    protected String boatType;
    protected double displacement;

    public Boat(String name, double width, double length, double height, int engineCap, String boatType, double displacement)
    {
        super(name, width, length, height, engineCap);
        this.boatType = boatType;
        this.displacement = displacement;
    }
}

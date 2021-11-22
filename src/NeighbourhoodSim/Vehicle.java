package NeighbourhoodSim;

public class Vehicle extends Item
{
    private int engineCapacity;
    protected static int counter;

    public Vehicle(String name, double width, double length, double height, int engineCapacity)
    {
        super(name, width, length, height);
        this.engineCapacity = engineCapacity;
        counter++;
        id = "V" + counter;
    }

    @Override
    public String toString()
    {
        return id + " Vehicle: " + name +", volume: " + volume +
                ", engine capacity: " + engineCapacity;
    }

    @Override
    public void RemoveFromStash()
    {
        isParkedOrStored = false;
        System.out.println( "Vehicle successfully withdrawn!");
    }
}

import java.util.List;

public class Vehicle extends Item
{
    int enginceCapacity;
    //String id = "V";
    static int counter;

    public Vehicle(String name, double width, double length, double height, int enginceCapacity)
    {
        super(name, width, length, height);
        this.enginceCapacity = enginceCapacity;
        counter++;
        id = "V" + counter;
    }

    @Override
    public String toString()
    {
        return id + " Vehicle: " + name +", volume: " + volume +
                ", engine capacity: " + enginceCapacity;
    }

    public void Park(ParkingSpot psToPark, List<Space> ownedSpaces)
    {
        try
        {
            psToPark.Overloaded();
            if (psToPark.contains <= this.volume)
            {
                if (((psToPark.contains = +this.volume) < this.volume))
                {
                    System.out.println("\n");
                    throw new Lodging.TooManyThingsException("This item is too big!");
                }
                else
                {
                    psToPark.contains = +this.volume;
                    psToPark.occupied.add(this);
                    isParkedOrStored = true;
                    System.out.println("\nVehicle parked.");
                }
            }
        }
        catch (Lodging.TooManyThingsException e)
        {
            System.out.println(e.toString());
        }
    }
}

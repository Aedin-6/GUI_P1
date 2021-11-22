package NeighbourhoodSim;

public class Item extends Space
{
    protected String name;
    protected boolean isParkedOrStored = false;
    protected String id = "I";
    private static int counter;

    public Item(String name, double width, double length, double height)
    {
        super(height, width, length);
        this.name = name;
        counter++;
        id = id + counter;

    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + ", Name: " + name + ", Volume: " + volume;
    }

    public void Park(Lodging placeToStore)
    {
        try
        {
            placeToStore.Overloaded();
            if (placeToStore.contains <= this.volume)
            {
                if (((placeToStore.contains = +this.volume) < this.volume))
                {
                    System.out.println("\n");
                    throw new Lodging.TooManyThingsException("This item is too big!");
                }
                else
                {
                    if (this instanceof Vehicle && placeToStore instanceof ParkingSpot)
                    {
                        placeToStore.contains = +this.volume;
                        placeToStore.occupied.add(this);
                        isParkedOrStored = true;
                        System.out.println("\nVehicle parked.");
                    }
                    else if (this instanceof Vehicle && !(placeToStore instanceof Apartment))
                    {
                        throw new IllegalArgumentException("You cannot park car in apartment!");
                    }
                    else
                    {
                        placeToStore.contains = +this.volume;
                        placeToStore.occupied.add(this);
                        isParkedOrStored = true;
                        System.out.println("\nItem stored.");
                    }
                }
            }
        }
        catch (Lodging.TooManyThingsException e)
        {
            System.out.println(e.toString());
        }
    }
    public void RemoveFromStash()
    {
        isParkedOrStored = false;
        System.out.println("Item successfully removed!");
    }

}

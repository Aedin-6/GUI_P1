package NeighbourhoodSim;

import java.util.List;

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

    double Volume() { return height*length*width; }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + ", Name: " + name + ", Voulme: " + volume;
    }

    public void Park(Lodging placeToStore, List<Space> ownedSpaces)
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
                    placeToStore.contains = +this.volume;
                    placeToStore.occupied.add(this);
                    isParkedOrStored = true;
                    System.out.println("\nNeighbourhoodSim.Item stored.");
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
        System.out.println("NeighbourhoodSim.Item successfully removed!");
    }

}
package NeighbourhoodSim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lodging extends Space implements Renting
{
    protected LocalDate rentDate;
    protected LocalDate dueDate;
    protected List<Item> occupied = new ArrayList<>();
    protected String address;
    protected double contains;
    protected String id;

    public Lodging(double height, double width, double length, String address)
    {
        super(height, width, length);
        this.address = address;
    }
    protected void Overloaded() throws TooManyThingsException
    {
        if (occupied != null)
        {
            for (var item : occupied)
            {
                contains += item.volume;
            }
            if (contains > this.volume)
            {
                throw new TooManyThingsException("Remove some old items to insert a new item.");
            }
        }
    }
    protected void KickStuff()
    {
        List<Item> itemClone = occupied;
        boolean carParked = false;
        if (itemClone.stream().anyMatch(v -> v instanceof Vehicle))
        {
            carParked = true;
        }

        if (!carParked)
        {
            itemClone.clear();
            contains = 0;
            if (this instanceof Apartment)
                ((Apartment) this).livesIn.clear();
        }
        else
        {
            for (Item item : itemClone)
            {
                if (item instanceof Vehicle)
                {
                    itemClone.remove(item);
                    contains = contains - item.volume;
                    dueDate = TimeSim.date.plusDays(60);
                    break;
                }
            }
        }
        occupied = itemClone;
    }
    protected void ShowRentInfo(){}
    protected void ExtendRent()
    {
        this.dueDate = TimeSim.date.plusDays(30);
        Start.user.files.remove(this);
        System.out.printf("\nYou contract for %s prolonged for 30 days", this);
    }
    protected String SortedStuff()
    {
        List<Item> sorted;
        sorted = occupied;

        sorted.sort(Comparator.comparing(Item::volume));
        return sorted.toString();
    }

    @Override
    public void Rent()
    {
        Start.user.rentedList.add(this);
        this.isOwnedOrRented = true;
        this.Owner = Start.user;
        this.rentDate = TimeSim.date;
        this.dueDate = this.rentDate.plusDays(30);
        if (Start.user.address == null)
            if(this instanceof Apartment)
                Start.user.address = this.address;
        Start.user.OwnCheck();
    }

    @Override
    public void Unrent()
    {
        Start.user.rentedList.remove(this);
        Start.user.files.remove(this);
        this.isOwnedOrRented = false;
        this.Owner = null;
        this.dueDate = null;
        this.rentDate = null;
        this.contains = 0;
        this.occupied.clear();
        if ((this instanceof Apartment))
        {
            ((Apartment) this).livesIn.clear();
            if (Start.user.address.equals(this.address))
            {
                if(Start.user.rentedList != null)
                    for (Space estates : Start.user.rentedList)
                    {
                        if (estates instanceof Apartment)
                        {
                            Start.user.address = ((Apartment) estates).address;
                            break;
                        }
                    }
                else
                    Start.user.address = null;
            }
        }
        Start.user.OwnCheck();

    }

    public static class TooManyThingsException extends Throwable
    {
        public TooManyThingsException(String errorMessage)
        {
            super(errorMessage);
        }
    }
}

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lodging extends Space
{
    public LocalDate rentDate;
    public LocalDate dueDate;
    List<Item> occupied = new ArrayList<>();
    String address;
    double contains;
    String id;

    public Lodging(double height, double width, double length, String address)
    {
        super(height, width, length);
        this.address = address;
    }
    public void Overloaded() throws TooManyThingsException
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
    void KickStuff()
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
    void ShowRentInfo(){}
    void ExtendRent()
    {
        this.dueDate = TimeSim.date.plusDays(30);
        Start.user.files.remove(this);
        System.out.printf("\nYou contract for %s prolonged for 30 days", this);
    }
    protected String SortedStuff()
    {
        List<Item> sorted = new ArrayList<>();
        sorted = occupied;

        sorted.sort(Comparator.comparing(Item::volume));
        return sorted.toString();
    }

    public static class TooManyThingsException extends Throwable
    {
        public TooManyThingsException(String errorMessage)
        {
            super(errorMessage);
        }
    }
}

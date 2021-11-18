import java.time.LocalDate;
import java.util.ArrayList;
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



    public static class TooManyThingsException extends Throwable
    {
        public TooManyThingsException(String errorMessage)
        {
            super(errorMessage);
        }
    }
}

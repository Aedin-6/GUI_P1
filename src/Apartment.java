import jdk.internal.icu.text.UnicodeSet;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;

public class Apartment extends Lodging
{
    static int counter;
    public List<Person> livesIn = new ArrayList<>();

    public Apartment(double height, double length, double width, String address)
    {
        super(height, width, length, address);
        counter++;
        id = "A" + counter;
        this.contains = 0;
    }

    @Override
    public String toString()
    {
        return "Apartament ID:" + " " + id + ". Volume: " + volume() + ", Address: " + address
                + " Contains: " + SortedStuff();
    }



    @Override
    void ShowRentInfo()
    {
        System.out.println("Apartment ID: " +  this.id + " is rented from "
                + this.rentDate.format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))) + " until "
        + this.dueDate.format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }


}

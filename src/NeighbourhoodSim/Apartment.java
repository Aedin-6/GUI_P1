package NeighbourhoodSim;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Apartment extends Lodging
{
    protected static int counter;
    protected List<Person> livesIn = new ArrayList<>();

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
        return "Apartment ID:" + " " + id + ". Volume: " + volume() + ", Address: " + address
                + " Contains: " + SortedStuff();
    }



    @Override
    protected void ShowRentInfo()
    {
        System.out.println("NeighbourhoodSim.Apartment ID: " +  this.id + " is rented from "
                + this.rentDate.format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))) + " until "
        + this.dueDate.format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }


}

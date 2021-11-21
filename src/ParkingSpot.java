import java.time.format.DateTimeFormatter;

public class ParkingSpot extends Lodging
{
    static int counter;

    public ParkingSpot(double height, double length, double width, String address)
    {
        super(height, width, length, address);
        counter++;
        id = "PS" + counter;
        this.contains = 0;
    }

    @Override
    public String toString()
    {
        return "Parking Spot ID:" + " " + id + ". Volume: " + volume() + ", Address: " + address
                + " Contains: " + SortedStuff();
    }
    @Override
    void ShowRentInfo()
    {
        System.out.println("Parking Spot ID: " +  this.id + " is rented from "
                + this.rentDate.format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))) + " until "
                + this.dueDate.format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }
}

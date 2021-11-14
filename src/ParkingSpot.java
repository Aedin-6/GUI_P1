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
        return "Parking Spot ID:" + " " + id + ". Volume: " + volume() + ", Address: " + address;
    }
}

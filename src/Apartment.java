public class Apartment extends Lodging
{
    static int counter;

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
        return "Apartament ID:" + " " + id + ". Volume: " + volume() + ", Address: " + address;
    }
}

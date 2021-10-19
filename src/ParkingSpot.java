public class ParkingSpot extends Space
{
    String id = "PS";
    static int counter;
    public ParkingSpot(double height, double length, double width)
    {
        super(height, width, length);
        counter++;
        id = id + counter;
    }

    @Override
    public String toString() {
        return "Miejsce parkingowe ID:" +
                " " + id + ". O wymiarach:" +
                " wysokosc - " + height +
                ", szerokosc - " + width +
                ", dlugosc - " + length;
    }
}

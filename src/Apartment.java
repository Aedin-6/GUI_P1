import java.util.Objects;

public class Apartment extends Space
{
    String id = "A";
    int hashId;
    static int counter;
    public Apartment(double height, double length, double width)
    {
        super(height, width, length);
        counter++;
        hashId = counter;
        id = id + hashId;
    }

    @Override
    public String toString() {
        return "Apartament ID:" +
                " " + id + ". O wymiarach:" +
                " wysokosc - " + height +
                ", szerokosc - " + width +
                ", dlugosc - " + length;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(id, apartment.id);
    }

    @Override
    public int hashCode()
    {
        return hashId;
    }
}

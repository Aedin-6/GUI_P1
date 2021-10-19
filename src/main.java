public class main
{
    public static void main(String[] args)
    {
        Person p1 = new Person("Woj", "Chi", 123, 10101991, "Ulanska 1", true);
        System.out.println(p1);

        Apartment a1 = new Apartment(10.1, 2, 3.5);
        System.out.println(a1);

        ParkingSpot ps1 = new ParkingSpot(2,3.5,8.5);
        System.out.println(ps1);
    }
}

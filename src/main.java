import java.awt.*;
import java.lang.invoke.StringConcatFactory;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

public class main
{
    public static void main(String[] args)
    {
        List<Apartment> apartmentList = new ArrayList<>();
        List<Person> peopleList = new ArrayList<>();
        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        
        Person p1 = new Person("Woj", "Chi", 123, 10101991, "Ulanska 1", true);
        System.out.println(p1);
        peopleList.add(p1);

        Person p2 = new Person("Rok", "Jar", 321, 10101991, "Ulanska 8", false);
        System.out.println(p2);
        peopleList.add(p2);


        Apartment a1 = new Apartment(10.1, 2, 3.5);
        System.out.println(a1);
        apartmentList.add(a1);


        ParkingSpot ps1 = new ParkingSpot(2,3.5,8.5);
        System.out.println(ps1);
        parkingSpotList.add(ps1);

        Start starter = new Start();
        starter.Start(apartmentList, peopleList, parkingSpotList);
    }
}

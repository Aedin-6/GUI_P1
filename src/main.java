import java.awt.*;
import java.lang.invoke.StringConcatFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class main
{
    public static void main(String[] args)
    {
        List<Space> apartmentList = new ArrayList<>();
        List<Person> peopleList = new ArrayList<>();
        List<Space> parkingSpotList = new ArrayList<>();
        Person p1 = null;
        Person p2 = null;
        try
        {
            //OSOBY
            p1 = new Person("Woj", "Chi", 123,
                    new SimpleDateFormat("dd/MM/yyyy").parse("18/10/1991"), "Ulanska 1");
            p2 = new Person("Rok", "Jar", 321,
                    new SimpleDateFormat("dd/MM/yyyy").parse("12/08/1994"), "Ulanska 8");
        }
        catch (ParseException e)
        {
            System.out.println("Parsing went wrong.");
        }
        //NIERUCHOMSCI
        Apartment a1 = new Apartment(11.1, 2, 6.5, "Babilonska 1");
        Apartment a2 = new Apartment(16.1, 5, 3.5, "Waska 31");
        Apartment a3 = new Apartment(18.1, 1, 8.5, "Dolna 15");
        ParkingSpot ps1 = new ParkingSpot(5, 2, 1, "Gruba 3");
        ParkingSpot ps2 = new ParkingSpot(2.1, 1.5, 8.5, "Szeroka 36");
        ParkingSpot ps3 = new ParkingSpot(3, 3.5, 4.5, "Rozowa 32");
        a1.isOwnedOrRented = true;
        a1.Owner = p1;
        ps1.isOwnedOrRented = true;
        ps1.Owner = p1;
        apartmentList.add(a1);
        apartmentList.add(a2);
        apartmentList.add(a3);
        parkingSpotList.add(ps1);
        parkingSpotList.add(ps2);
        parkingSpotList.add(ps3);
        p1.rentedList.add(a1);
        p1.rentedList.add(ps1);
        a1.rentDate = LocalDate.now();
        a1.dueDate = a1.rentDate.plusDays(30);
        ps1.rentDate = LocalDate.now();
        ps1.dueDate = a1.rentDate.plusDays(30);

        //POJAZDY
        CityCar p1cc = new CityCar("Porsche", 2.0, 3.2, 1.3, 4,
                "Disel", 1200);
        p1cc.isOwnedOrRented = true;
        p1cc.Owner = p1;
        p1.stash.add(p1cc);


        Motorcycle p2moto = new Motorcycle("Harley", 1.0, 2.2, 1.3, "Chopper",
                false, 1200);
        p2moto.isOwnedOrRented = true;
        p2moto.Owner = p2;
        p2.stash.add(p2moto);

        //PRZEDMIOTY
       Item tv = new Item("TV", 1.5, 0.2,1);
       tv.isOwnedOrRented = true;
       p1.stash.add(tv);

        Item wardrobe = new Item("Wardrobe", 1.0, 2.2,2);
        wardrobe.isOwnedOrRented = true;
        p1.stash.add(wardrobe);

        peopleList.add(p1);
        peopleList.add(p2);
        System.out.println(wardrobe.volume);
        p1.OwnCheck();
        p2.OwnCheck();
        try
        {
            a1.Overloaded();
        }
        catch (Lodging.TooManyThingsException ignored){}
        Start starter = new Start(apartmentList, peopleList, parkingSpotList);
        starter.Start();

    }
}

package NeighbourhoodSim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class main
{
    public static void main(String[] args)
    {

        List<Space> apartmentList = new ArrayList<>();
        List<Person> peopleList = new ArrayList<>();
        List<Space> parkingSpotList = new ArrayList<>();
        Person p1, p2, p3, p4, p5, p6;
        p1 = p2 = p3 = p4 = p5 = p6 = null;
        try
        {
            //OSOBY
             p1 = new Person("Mace", "Windu", 91101823345L,
                    new SimpleDateFormat("dd/MM/yyyy").parse("18/10/1991"));
             p2 = new Person("Sease", "Tinn", 85081234631L,
                    new SimpleDateFormat("dd/MM/yyyy").parse("12/08/1985"));
             p3 = new Person("Kit", "Fisto", 87072385725L,
                    new SimpleDateFormat("dd/MM/yyyy").parse("23/07/1987"));
             p4 = new Person("Obi-Wan", "Kenobi", 94021982764L,
                    new SimpleDateFormat("dd/MM/yyyy").parse("19/02/1994"));
             p5 = new Person("Agen", "Kolar", 91052593852L,
                    new SimpleDateFormat("dd/MM/yyyy").parse("25/05/1991"));
             p6 = new Person("Plo", "Koon", 94120283752L,
                    new SimpleDateFormat("dd/MM/yyyy").parse("02/12/1994"));

             peopleList.add(p1); peopleList.add(p2); peopleList.add(p3);
             peopleList.add(p4); peopleList.add(p5); peopleList.add(p6);


        }
        catch (ParseException e)
        {
            System.out.println("Parsing went wrong.");
        }
        //NIERUCHOMSCI
        Apartment a1 = new Apartment(3.1, 6, 6.5, "Babilonska 1");
        Apartment a2 = new Apartment(3.4, 7, 3.5, "Waska 31");
        Apartment a3 = new Apartment(2.8, 5.5, 8.5, "Dolna 15");
        Apartment a4 = new Apartment(4.1, 4, 6.5, "Koszykowa 1");
        Apartment a5 = new Apartment(2.6, 7.8, 3.5, "Wiejska 31");
        Apartment a6 = new Apartment(3.2, 12, 8.5, "Murawska 15");
        ParkingSpot ps1 = new ParkingSpot(2.2, 2, 3.2, "Gruba 3");
        ParkingSpot ps2 = new ParkingSpot(2.1, 1.5, 3.5, "Szeroka 36");
        ParkingSpot ps3 = new ParkingSpot(3, 3.5, 4.5, "Rozowa 32");
        ParkingSpot ps4 = new ParkingSpot(3.2, 2, 1.7, "Chuda 3");
        ParkingSpot ps5 = new ParkingSpot(2.4, 2.5, 2.5, "Gleboka 36");
        ParkingSpot ps6 = new ParkingSpot(2.7, 3.8, 3.1, "Gawronow 32");

        apartmentList.add(a1); apartmentList.add(a2); apartmentList.add(a3); apartmentList.add(a4);
        apartmentList.add(a5); apartmentList.add(a6); apartmentList.add(ps1); apartmentList.add(ps2);
        apartmentList.add(ps3); apartmentList.add(ps4); apartmentList.add(ps5); apartmentList.add(ps6);

        //POJAZDY
        CityCar car = new CityCar("Porsche", 2.0, 3.2, 1.3, 4,
                "Disel", 1200);
        Motorcycle moto = new Motorcycle("Harley", 1.0, 2.2, 1.3, "Chopper",
                false, 1200);
        Boat boat = new Boat("Aprils'Flame", 2.4, 4.2, 1.4, 2000,
                "Yacht", 230);
        OffroadCar offroadCar = new OffroadCar("4x4", 2.5, 5.2, 1.6, "Petrol",
                true, 2200);


        //PRZEDMIOTY
        Item tv = new Item("TV", 0.2, 1.2,1);
        Item wardrobe = new Item("Wardrobe", 1.0, 2.2,2);
        Item bike = new Item("Bice", 0.5, 1.2,1);
        Item oldBed = new Item("Old Bed", 2.0, 2.2,0.5);
        Item laptop = new Item("laptop", 0.5, 0.5,0.02);
        Item lightsaber = new Item("Lightsaber", 0.1, 2.2,0.1);

        //WLASNOSC P1:
        p1.MoveInPerson(a1, ps1, tv, bike, car);
        car.Park(ps1);
        tv.Park(a1);
        //WLASNOSC P2:
        p2.MoveInPerson(a2, ps2, laptop, null, boat);
        boat.Park(ps2);
        //WLASNOSC P3:
        p3.MoveInPerson(a3, null, lightsaber, null, moto);
        lightsaber.Park(a3);
        //WLASNOSC P4:
        p4.MoveInPerson(a4, ps5, wardrobe, null, null);

        //WLASNOSC P5:
        p5.MoveInPerson(a5, null, null ,null, null);

        //WLASNOSC P6:
        p6.MoveInPerson(null, null, oldBed, null, offroadCar);


        Start starter = new Start(apartmentList, peopleList, parkingSpotList);
        starter.Start();

    }
}

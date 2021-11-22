package NeighbourhoodSim;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Person implements Serializable
{
    protected static int counter;
    protected String id ="O";
    protected String name;
    protected String surename;
    private long pesel;
    protected Date bornDate;
    protected String address;
    protected Map<Lodging, ArrayList<File>> files = new HashMap<>();
    protected List<Space> rentedList = new ArrayList<>();
    protected List<Item> stash = new ArrayList<>();
    private boolean isOwner = false;

    public Person(String name, String surename, long pesel, Date bornDate)
    {
        counter++;
        this.name = name;
        this.surename = surename;
        this.pesel = pesel;
        this.bornDate = bornDate;
        id = id +  counter;
    }

    public void OwnCheck()
    {
        isOwner = rentedList.size() > 0 || stash.size() > 0;
    }


    @Override
    public String toString() {
        if (isOwner)
            return "\nId: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". \nOwner status: " + "Owns: " + "Apartments and Parking Spots: " + rentedList  + " \nItems: " + SortedStuff();
        else
            return "\nId: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". \nOwner status: " + "Doesn't own anything,";
    }


    protected void addFile(Lodging space)
    {
        File newFile = new File();
        ArrayList<File> rightSpace = files.get(space);
        if (rightSpace == null)
        {
            ArrayList<File> newFilesList = new ArrayList<>();
            newFilesList.add(newFile);
            files.put(space, newFilesList);
        }
        else
            rightSpace.add(newFile);
    }

    protected String SortedStuff()
    {
        List<Item> sorted;
        sorted = stash;

        sorted.sort(Comparator.comparing(Item::volume).thenComparing(Item::getName).reversed());
        return sorted.toString();
    }

    protected void CheckProblematicTenant() throws ProblematicTenantException
    {
        int countFiles = 0;
        for (Map.Entry<Lodging, ArrayList<File>> entry : files.entrySet())
        {
            countFiles = countFiles + entry.getValue().size();
        }
        if (countFiles >= 3)
        {
            throw new ProblematicTenantException("You have unpaid rent! You are currently renting: "
                    + this.rentedList + " You have unpaid rent for: " + files);
        }
    }

    public void MoveInPerson(Apartment apartment, ParkingSpot parkingSpot, Item item1, Item item2, Vehicle vehicle)
    {
        if(apartment != null)
        {
            apartment.isOwnedOrRented = true;
            apartment.Owner = this;
            apartment.rentDate = LocalDate.now();
            apartment.dueDate = apartment.rentDate.plusDays(30);
            this.rentedList.add(apartment);
        }
        if(parkingSpot != null)
        {
            parkingSpot.isOwnedOrRented = true;
            parkingSpot.Owner = this;
            parkingSpot.rentDate = LocalDate.now();
            parkingSpot.dueDate = parkingSpot.rentDate.plusDays(30);
            this.rentedList.add(parkingSpot);
        }
        if(item1 != null)
        {
            this.stash.add(item1);
            item1.isOwnedOrRented = true;
        }
        if(item2 != null)
        {
            this.stash.add(item2);
            item2.isOwnedOrRented = true;
        }
        if(vehicle != null)
        {
            vehicle.isOwnedOrRented = true;
            vehicle.Owner = this;
            this.stash.add(vehicle);
        }
        this.OwnCheck();
    }

    public class ProblematicTenantException extends Throwable
    {
        public ProblematicTenantException(String errorMsg)
        {
            super(errorMsg);
        }
    }
}

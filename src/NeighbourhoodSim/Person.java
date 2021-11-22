package NeighbourhoodSim;

import java.io.Serializable;
import java.util.*;

public class Person implements Serializable
{
    protected static int counter;
    protected String id ="O";
    protected String name;
    protected String surename;
    private int pesel;
    protected Date bornDate;
    private String address;
    protected Map<Lodging, ArrayList<File>> files = new HashMap<>();
    protected List<Space> rentedList = new ArrayList<>();
    protected List<Item> stash = new ArrayList<>();
    private boolean isOwner = false;

    public Person(String name, String surename, int pesel, Date bornDate, String address)
    {
        counter++;
        this.name = name;
        this.surename = surename;
        this.pesel = pesel;
        this.bornDate = bornDate;
        this.address = address;
        id = id +  counter;
    }

    public void OwnCheck()
    {
        isOwner = rentedList.size() > 0 || stash.size() > 0;
    }


    @Override
    public String toString() {
        if (isOwner)
            return "Id: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". Owner status: " + "Owns: " + "Apartmnets and Parking Spots: " + rentedList  + " Items: " + SortedStuff();
        else
            return "Id: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". Owner status: " + "Doesn't own anything,";
    }


    protected void addFile(Lodging space) //TODO: DO FILE
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
            throw new ProblematicTenantException("You have unpayed rent! You are currently renting: "
                    + this.rentedList + " You have unpaid rent for: " + files);
        }
    }

    public class ProblematicTenantException extends Throwable
    {
        public ProblematicTenantException(String errorMsg)
        {
            super(errorMsg);
        }
    }
}

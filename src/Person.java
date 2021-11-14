import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person
{
    static int counter;
    String id ="O";
    String name;
    String surename;
    int pesel;
    Date bornDate;
    String address;
    List<File> files = new ArrayList<>();
    List<Space> rentedList = new ArrayList<>();
    List<Item> stash = new ArrayList<>();
    boolean isOwner = false;

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
            return "Id: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". Owner status: " + "Owns: " + "Apartmnets and Parking Spots: " + rentedList  + " Items: " + stash;
        else
            return "Id: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". Owner status: " + "Doesn't own anything,";
    }

    void addFile()
    {
        File newFile = new File();
        files.add(newFile);
    }

}

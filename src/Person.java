import java.util.List;

public class Person
{
    static int counter;
    String id ="O";
    int hashId;
    String name;
    String surename;
    int pesel;
    int bornDate;
    String address;
    boolean isOwner;
    List<File> files;
    List<Space> rentedList;

    public Person(String name, String surename, int pesel, int bornDate, String address, boolean isOwner)
    {
        counter++;
        hashId = counter;
        this.name = name;
        this.surename = surename;
        this.pesel = pesel;
        this.bornDate = bornDate;
        this.address = address;
        this.isOwner = isOwner;
        id = id +  hashId;
    }

    @Override
    public int hashCode() {
        return hashId;
    }

    @Override
    public String toString() {
        return "Id: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". Owner status: " + (isOwner? "Owns a place.":"Doesn't own anything,");
    }

    void addFile()
    {
        File newFile = new File();
        files.add(newFile);
    }

    void addPlace(Space place) //TODO: pomyslec czy to nie powinno byc w space?
    {
        rentedList.add(place);
    }

    void removePlace(Space place) //TODO: to tez xD
    {
        rentedList.remove(place);
    }

}

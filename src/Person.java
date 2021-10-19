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
        return "Id: " + id + ". Osoba: " + name + " "+ surename + " Pesel: " + pesel + ". Owner status: " + isOwner;
    }
}

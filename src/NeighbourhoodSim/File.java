package NeighbourhoodSim;

public class File
{
    protected String id = "F";
    protected static int counter;

    public File()
    {
        counter++;
        id = id + counter;
    }
}

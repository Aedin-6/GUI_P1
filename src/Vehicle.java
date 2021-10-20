public abstract class Vehicle
{
    String name;
    double height;
    double length;
    double width;

    public Vehicle(String name, double width, double length, double height)
    {
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;

    }

    double Volume() { return height*length*width; }


}

public abstract class Space
{
    double height;
    double width;
    double length;

    double volume(){ return height*width*length; }

    public Space(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }
}

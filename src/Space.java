import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public abstract class Space implements Serializable
{
    public Person Owner;
    double height;
    double width;
    double length;
    boolean isOwnedOrRented;
    double volume;


    double volume()
    {
        double vol = height*length*width;
        return new BigDecimal(Double.toString(vol)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Space(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
        volume = volume();
    }
}

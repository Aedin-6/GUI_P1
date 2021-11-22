package NeighbourhoodSim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Space implements Serializable
{
    protected Person Owner;
    protected double height;
    protected double width;
    protected double length;
    protected  boolean isOwnedOrRented;
    protected double volume;


    protected double volume()
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

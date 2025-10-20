package com.tosan.bpms.controller;

/**
 * Created by k.haghpanah on 5/01/2019.
 */
public class Cartizian{
    Double x;
    Double y;
    Double z;

    public Cartizian() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Cartizian{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}

package com.anth0o0ny.sdes;

public abstract class SDE {

    protected final double x0;
    protected final double y0;
    protected final double c;

    public SDE(double x0, double y0) {
        this.x0 = x0;
        this.y0 = y0;
        this.c = calculateC(x0, y0);
    }

    public abstract double y(double x);

    public abstract double dy(double x, double y);

    public abstract double calculateC(double x0, double y0);

}

package com.anth0o0ny.sdes;


public class MySde extends SDE {

    public MySde(double x0, double y0) {
        super(x0, y0);
    }

    @Override
    public double y(double x) {
        return c * Math.exp(x) - x - 5;
    }

    @Override
    public double dy(double x, double y) {
        return x + y + 4;
    }

    @Override
    public double calculateC(double x0, double y0) {
        return (y0 + x0 + 5) / Math.exp(x0);
    }
}

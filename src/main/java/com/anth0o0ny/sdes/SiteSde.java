package com.anth0o0ny.sdes;

public class SiteSde extends SDE {

    public SiteSde(double x0, double y0) {
        super(x0, y0);
    }

    @Override
    public double y(double x) {
        return c * x + 1 / x;
    }

    @Override
    public double dy(double x, double y) {
        return y / x - 2 / x / x;
    }

    @Override
    public double calculateC(double x0, double y0) {
        return (y0 * x0 - 1) / x0 / x0;
    }

}

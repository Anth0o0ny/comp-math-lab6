package com.anth0o0ny.sdes;

public class LectureSde extends SDE {

    public LectureSde(double x0, double y0) {
        super(x0, y0);
    }

    @Override
    public double y(double x) {
        return -Math.exp(x) / ( c + Math.exp(x) * x );
    }

    @Override
    public double dy(double x, double y) {
        return y + (1 + x) * y * y;
    }

    @Override
    public double calculateC(double x0, double y0) {
        return -Math.exp(x0) / y0 - Math.exp(x0) * x0;
    }

}

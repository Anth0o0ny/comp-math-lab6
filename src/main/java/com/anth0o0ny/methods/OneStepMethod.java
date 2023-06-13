package com.anth0o0ny.methods;


import com.anth0o0ny.model.SdeData;
import com.anth0o0ny.sdes.SDE;

import java.util.ArrayList;

public abstract class OneStepMethod {

    protected final int ACCURACY_ORDER;
    protected final int MAX_ITERATIONS_COUNT = 10000;
    protected final double X_0;
    protected final double X_N;
    protected final double Y_0;
    protected double H;
    protected final double EPSILON;
    protected final SDE sdeEquation;

    protected final ArrayList<Double> xArray = new ArrayList<>();
    protected final ArrayList<Double> yArray = new ArrayList<>();
    protected final ArrayList<Double> yExpectedArray = new ArrayList<>();

    public OneStepMethod(SdeData data, int accuracyOrder) {
        this.X_0 = data.getX0();
        this.X_N = data.getxN();
        this.Y_0 = data.getyX0();
        this.H = data.getH();
        this.EPSILON = data.getEps();
        this.sdeEquation = data.getSdeEquation();
        this.ACCURACY_ORDER = accuracyOrder;
    }

    public void solve() {

        xArray.add(X_0);
        yArray.add(Y_0);
        int restarts = 0;


        for (int i = 1;;) {
            Double result;
            double startH = H;
            while (true) {
                result = calculateAndCheckByRunge(i);
                if (result == null) {
                    continue;
                }
                break;
            }
            if (startH != H) {
                i = 1;
                xArray.clear();
                yArray.clear();
                xArray.add(X_0);
                yArray.add(Y_0);
                restarts += 1;
            } else {
                i++;
            }
            if (result > X_N) {
                break;
            }
            if (i == MAX_ITERATIONS_COUNT || xArray.contains(Double.NaN) || yArray.contains(Double.NaN) || restarts == MAX_ITERATIONS_COUNT) {
                xArray.clear();
                yArray.clear();
                break;
            }
        }

        if (!xArray.isEmpty()) {
            if (xArray.get(xArray.size() - 1) - X_N > 1e-15) {
                xArray.remove(xArray.size() - 1);
                yArray.remove(yArray.size() - 1);
            }
            for (Double x : xArray) {
                yExpectedArray.add(sdeEquation.y(x));
            }
        }

    }

    protected abstract Double calculateAndCheckByRunge(int index);

    public ArrayList<Double> getxArray() {
        return xArray;
    }

    public ArrayList<Double> getyArray() {
        return yArray;
    }

    public ArrayList<Double> getyExpectedArray() {
        return yExpectedArray;
    }

    public double getH() {
        return H;
    }
}

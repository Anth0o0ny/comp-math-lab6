package com.anth0o0ny.methods;

import com.anth0o0ny.model.SdeData;
import com.anth0o0ny.sdes.SDE;

import java.util.ArrayList;

public class AdamsMethod {

    private final int MAX_ITERATIONS_COUNT = 50000;
    private final int MAX_CYCLES_COUNT = 1000000;
    private final double X_0;
    private final double X_N;
    private final double Y_0;
    private final double H;
    private final double EPSILON;
    private final SDE sdeEquation;

    protected final ArrayList<Double> xArray = new ArrayList<>();
    protected final ArrayList<Double> yArray = new ArrayList<>();
    protected final ArrayList<Double> yExpectedArray = new ArrayList<>();

    public AdamsMethod(SdeData data) {
        this.X_0 = data.getX0();
        this.X_N = data.getxN();
        this.Y_0 = data.getyX0();
        this.EPSILON = data.getEps();
        this.sdeEquation = data.getSdeEquation();

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(data);
        rungeKuttaMethod.solve();
        this.H = rungeKuttaMethod.getH();
        ArrayList<Double> rungeKuttaXArray = rungeKuttaMethod.getxArray();
        ArrayList<Double> rungeKuttaYArray = rungeKuttaMethod.getyArray();

        if (rungeKuttaXArray.isEmpty() || rungeKuttaYArray.isEmpty()) {
            throw new NullPointerException();
        }

        xArray.add(rungeKuttaXArray.get(0));
        xArray.add(rungeKuttaXArray.get(1));
        xArray.add(rungeKuttaXArray.get(2));
        xArray.add(rungeKuttaXArray.get(3));

        yArray.add(rungeKuttaYArray.get(0));
        yArray.add(rungeKuttaYArray.get(1));
        yArray.add(rungeKuttaYArray.get(2));
        yArray.add(rungeKuttaYArray.get(3));
    }
    public void solve() {
        // Вычисление значений функции с использованием метода Рунге-Кутты
        for (int i = 4; i < 5; i++) {
            double curX = xArray.get(i - 1) + H;
            xArray.add(curX);
            yArray.add(sdeEquation.y(curX));
        }

        // Вычисление значений функции с использованием метода Адамса
        for (int i = 5; ; i++) {
            double curX = xArray.get(i - 1) + H;
            xArray.add(curX);

            double fIminus1 = sdeEquation.dy(xArray.get(i - 1), yArray.get(i - 1));
            double fIminus2 = sdeEquation.dy(xArray.get(i - 2), yArray.get(i - 2));
            double fIminus3 = sdeEquation.dy(xArray.get(i - 3), yArray.get(i - 3));
            double fIminus4 = sdeEquation.dy(xArray.get(i - 4), yArray.get(i - 4));

            double yCorrection = yArray.get(i - 1) + H / 24 * (55 * fIminus1 - 59 * fIminus2 + 37 * fIminus3 - 9 * fIminus4);

            yArray.add(yCorrection);

            if (curX > X_N) {
                break;
            }

            if (i == MAX_ITERATIONS_COUNT || xArray.contains(Double.NaN) || yArray.contains(Double.NaN)) {
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


    public ArrayList<Double> getxArray() {
        return xArray;
    }

    public ArrayList<Double> getyArray() {
        return yArray;
    }

    public ArrayList<Double> getyExpectedArray() {
        return yExpectedArray;
    }
}


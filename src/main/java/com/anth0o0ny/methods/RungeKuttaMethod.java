package com.anth0o0ny.methods;

import com.anth0o0ny.model.SdeData;

public class RungeKuttaMethod extends OneStepMethod {

    public RungeKuttaMethod(SdeData data) {
        super(data, 4);
    }

    @Override
    protected Double calculateAndCheckByRunge(int index) {
        double prevY = yArray.get(index-1);
        double prevX = xArray.get(index-1);
        double k1 = H * sdeEquation.dy(prevX, prevY);
        double k2 = H * sdeEquation.dy(prevX + H/2, prevY + k1/2);
        double k3 = H * sdeEquation.dy(prevX + H/2, prevY + k2/2);
        double k4 = H * sdeEquation.dy(prevX + H, prevY + k3);

        double yH = prevY + (k1 + 2*k2 +2*k3 + k4) / 6;

        k1 = H/2 * sdeEquation.dy(prevX, prevY);
        k2 = H/2 * sdeEquation.dy(prevX + H/4, prevY + k1/2);
        k3 = H/2 * sdeEquation.dy(prevX + H/4, prevY + k2/2);
        k4 = H/2 * sdeEquation.dy(prevX + H/2, prevY + k3);

        double yHdiv2 = prevY + (k1 + 2*k2 +2*k3 + k4) / 6;

        if (Math.abs((yH - yHdiv2) / (Math.pow(2, ACCURACY_ORDER) - 1)) <= EPSILON) {
            xArray.add(prevX + H);
            yArray.add(yH);
            return prevX + H;
        } else {
            H /= 2;
            return null;
        }

    }
}

package com.anth0o0ny.methods;

import com.anth0o0ny.model.SdeData;

public class EulerMethod extends OneStepMethod {

    public EulerMethod(SdeData data) {
        super(data, 1);
    }

    @Override
    protected Double calculateAndCheckByRunge(int index) {
        double prevY = yArray.get(index-1);
        double prevX = xArray.get(index-1);
        double yH = prevY + H * sdeEquation.dy(prevX, prevY);
        double xH = prevX + H;

        xArray.add(xH);
        yArray.add(yH);

        return xH;
    }

}

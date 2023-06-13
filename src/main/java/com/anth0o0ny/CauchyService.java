package com.anth0o0ny;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.anth0o0ny.methods.AdamsMethod;
import com.anth0o0ny.methods.EulerMethod;
import com.anth0o0ny.methods.RungeKuttaMethod;
import com.anth0o0ny.model.SdeData;
import org.springframework.stereotype.Component;

@Component
public class CauchyService {

    public ObjectNode solve(SdeData data) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        if (!data.isValidData()) {
            node.put("error", "Got invalid data");
            return node;
        }

        data.validateData();

        EulerMethod eulerMethod = new EulerMethod(data);
        eulerMethod.solve();
        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(data);
        rungeKuttaMethod.solve();
        try {
            AdamsMethod adamsMethod = new AdamsMethod(data);
            adamsMethod.solve();
            node.putPOJO("adamsX", adamsMethod.getxArray());
            node.putPOJO("adamsY", adamsMethod.getyArray());
            node.putPOJO("adamsExpectedY", adamsMethod.getyExpectedArray());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            node.put("error", "Метод Адамса не работает, потому что метод Рунге-Кутта не может определить начальное приближение.");
        }


        node.putPOJO("eulerX", eulerMethod.getxArray());
        node.putPOJO("eulerY", eulerMethod.getyArray());
        node.putPOJO("eulerExpectedY", eulerMethod.getyExpectedArray());
        node.putPOJO("rungeKuttaX", rungeKuttaMethod.getxArray());
        node.putPOJO("rungeKuttaY", rungeKuttaMethod.getyArray());
        node.putPOJO("rungeKuttaExpectedY", rungeKuttaMethod.getyExpectedArray());

        return node;
    }

}

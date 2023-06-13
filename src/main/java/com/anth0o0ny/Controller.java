package com.anth0o0ny;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.anth0o0ny.model.SdeData;
import com.anth0o0ny.CauchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
;
    private final CauchyService cauchyService;

    @Autowired
    public Controller(CauchyService cauchyService) {
        this.cauchyService = cauchyService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("sde/solve")
    @ResponseBody
    public ObjectNode solve(@RequestBody SdeData data) {
        return cauchyService.solve(data);
    }


}

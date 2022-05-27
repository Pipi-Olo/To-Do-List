package com.item.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }
}

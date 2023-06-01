package com.nivkart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/NivKart/")
    public String handleNivKartRequest() {
        return "index"; // "index" is the name of your index.html file
    }
}

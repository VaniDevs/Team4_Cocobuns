package com.swiftavenue.vanhacks2018.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIMainController {


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("eventName", "Fun time");
        return "index.html";
    }

}

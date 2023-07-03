package com.example.testdoan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/translate")
    public String getTranslate() {
        return "test";
    }
}

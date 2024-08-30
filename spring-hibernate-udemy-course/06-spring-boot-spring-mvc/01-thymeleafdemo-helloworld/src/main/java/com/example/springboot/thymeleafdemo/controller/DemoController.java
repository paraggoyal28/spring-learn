package com.example.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class DemoController {

    // create a mapping for "/hello"
    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        theModel.addAttribute("theDate", LocalDateTime.now());

        return "helloworld";
    }
}

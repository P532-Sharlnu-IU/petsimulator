package com.sharath.petsimulator.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class HomeController implements ErrorController {
    @GetMapping("/")
    public String greetings()
    {
        return "Welcome to the Virtual Pet Service!!";
    }

}
package com.sharath.petsimulator.controller;

import com.sharath.petsimulator.config.DataInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

    private final DataInitializer initializer;

    public AdminController(DataInitializer initializer) {
        this.initializer = initializer;
    }

    @PostMapping("/reset")
    public void resetGame() {
        initializer.reset();
    }
}
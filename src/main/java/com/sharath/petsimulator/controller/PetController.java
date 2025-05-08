package com.sharath.petsimulator.controller;

import com.sharath.petsimulator.dto.*;
import com.sharath.petsimulator.model.Pet;
import com.sharath.petsimulator.service.PetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin
public class PetController {
    private final PetService petService;
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/adopt")
    public Pet adopt(@RequestBody AdoptRequest req) {
        return petService.adoptPet(req.getName());
    }

    @GetMapping("/status")
    public Pet status() {
        return petService.getPet();
    }

    @PostMapping("/action")
    public Pet action(@RequestBody ActionRequest req) {
        return petService.performAction(req.getAction());
    }

    @PostMapping("/undo")
    public Pet undo() {
        return petService.undoAction();
    }
}

package com.sharath.petsimulator.controller;

import com.sharath.petsimulator.dto.*;
import com.sharath.petsimulator.model.Pet;
import com.sharath.petsimulator.model.PetType;
import com.sharath.petsimulator.model.repository.ActivePetSession;
import com.sharath.petsimulator.model.repository.PetTypeRepository;
import com.sharath.petsimulator.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin(origins="*")
public class PetController {
    private final PetService petService;
    private final PetTypeRepository typeRepo;
    private final ActivePetSession session;
    public PetController(PetService petService, PetTypeRepository typeRepo, ActivePetSession session) {
        this.petService = petService;
        this.typeRepo = typeRepo;
        this.session = session;
    }

    @GetMapping("/types")
    public List<PetType> listTypes() {
        return typeRepo.findAll();
    }

    @GetMapping("/current")
    public Pet getCurrent() {
        return session.getCurrentPet();
    }


    @PostMapping("/select/{id}")
    public Pet selectPet(@PathVariable Long id) {
        return petService.selectPet(id);
    }

//    @GetMapping("/current")
//    public Pet currentPet() {
//        return petService.getPet();
//    }

    @PostMapping("/adopt")
    public Pet adopt(@RequestBody AdoptRequest req) {
        return petService.adoptPet(req.getName(), req.getTypeId());
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

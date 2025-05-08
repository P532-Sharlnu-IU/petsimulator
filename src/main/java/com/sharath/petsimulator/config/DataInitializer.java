package com.sharath.petsimulator.config;

import com.sharath.petsimulator.inventory.InventoryRepository;
import com.sharath.petsimulator.model.PetType;
import com.sharath.petsimulator.model.repository.PetTypeRepository;
import com.sharath.petsimulator.model.repository.PetRepository;
import com.sharath.petsimulator.model.Pet;
import com.sharath.petsimulator.model.repository.ActivePetSession;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.sharath.petsimulator.inventory.*;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {

    private final PetTypeRepository petTypeRepo;
    private final PetRepository petRepo;
    private final ActivePetSession session;
    private final InventoryRepository repo;

    public DataInitializer(PetTypeRepository petTypeRepo, PetRepository petRepo, ActivePetSession session, InventoryRepository repo) {
        this.petTypeRepo = petTypeRepo;
        this.petRepo = petRepo;
        this.session = session;
        this.repo = repo;
    }

    @PostConstruct
    @Transactional
    public void init() {
        // 1) seed exactly two PET TYPES if none exist
        if (petTypeRepo.count() == 0) {
            petTypeRepo.save(new PetType(null, "Cat", "/images/cat.png"));
            petTypeRepo.save(new PetType(null, "Dog", "/images/dog.png"));
        }

        if (repo.count() == 0) {
            repo.save(new InventoryItem(null, "Food", "Pet Food", 5));
            repo.save(new InventoryItem(null, "Food", "Treat",    3));
            repo.save(new InventoryItem(null, "Toys", "Ball",      1));
            repo.save(new InventoryItem(null, "Toys", "Chew Toy",  2));
            repo.save(new InventoryItem(null, "Medicine", "Med Pack", 3));
        }

        session.clear();
    }

    @Transactional
    public void reset() {
        // delete all user‐mutated data
        repo.deleteAll();
        petRepo.deleteAll();
        petTypeRepo.deleteAll();
        session.clear();
        // re‐seed
        init();
    }
}

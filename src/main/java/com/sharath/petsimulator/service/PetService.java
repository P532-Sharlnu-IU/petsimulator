package com.sharath.petsimulator.service;

import com.sharath.petsimulator.command.*;
import com.sharath.petsimulator.model.*;
import com.sharath.petsimulator.model.repository.*;
import com.sharath.petsimulator.state.*;
import org.springframework.stereotype.Service;
import com.sharath.petsimulator.dto.*;
import lombok.RequiredArgsConstructor;

@Service
public class PetService {
    private final PetRepository petRepo;
    private final ActivePetSession session = ActivePetSession.getInstance();
    private final Invoker invoker = new Invoker();
    private final InventoryService inventoryService;

    public PetService(PetRepository petRepo, InventoryService inventoryService) {
        this.petRepo = petRepo;
        this.inventoryService = inventoryService;
    }

    public Pet adoptPet(String name) {
        Pet p = new Pet(name);
        p.setHunger(50);
        p.setHappiness(50);
        p.setHealth(100);
        p.setState(new HungryState());
        p = petRepo.save(p);
        session.setCurrentPet(p);
        return p;
    }

    public Pet getPet() {
        return session.getCurrentPet();
    }

    public Pet performAction(String action) {
        Pet p = session.getCurrentPet();
        // 1) consume the correct item
        switch(action.toLowerCase()) {
            case "feed":
                inventoryService.consume("Pet Food", 1);
                break;
            case "play":
                // try Ball first
                try {
                    inventoryService.consume("Ball", 1);
                } catch (IllegalStateException e) {
                    // no Balls left? consume a Chew Toy instead
                    inventoryService.consume("Chew Toy", 1);
                }
                break;
            case "clean":
                inventoryService.consume("Treat", 1);
                break;
            case "heal":
                inventoryService.consume("Med Pack", 1);
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }

        // 2) execute the command + state transition
        // state handles execution + transition
        PetState st = p.getState();
        PetActionCommand cmd = createCommand(action);
        st.handle(p, cmd);
        invoker.execute(cmd, p);
        petRepo.save(p);
        return p;
    }

    public Pet undoAction() {
        Pet p = session.getCurrentPet();
        invoker.undo(p);
        petRepo.save(p);
        return p;
    }

    private PetActionCommand createCommand(String name) {
        switch(name.toLowerCase()) {
            case "feed": return new FeedCommand();
            case "play": return new PlayCommand();
            case "clean":return new CleanCommand();
            case "heal": return new HealCommand();
            default:    throw new IllegalArgumentException("Unknown action: "+name);
        }
    }
}

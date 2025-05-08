package com.sharath.petsimulator.command;

import com.sharath.petsimulator.model.Pet;

public class FeedCommand implements PetActionCommand {
    @Override
    public void execute(Pet pet) {
        pet.setHunger(Math.max(0, pet.getHunger() - 30));
    }
    @Override
    public void undo(Pet pet) {
        pet.setHunger(Math.min(100, pet.getHunger() + 30));
    }
    @Override public String name() { return "Feed"; }
}
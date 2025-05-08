package com.sharath.petsimulator.command;

import com.sharath.petsimulator.model.Pet;

public class PlayCommand implements PetActionCommand {
    @Override
    public void execute(Pet pet) {
        pet.setHappiness(Math.min(100, pet.getHappiness() + 20));
    }
    @Override
    public void undo(Pet pet) {
        pet.setHappiness(Math.max(0, pet.getHappiness() - 20));
    }
    @Override public String name() { return "Play"; }
}

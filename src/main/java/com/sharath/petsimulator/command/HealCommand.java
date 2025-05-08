package com.sharath.petsimulator.command;

import com.sharath.petsimulator.model.Pet;

public class HealCommand implements PetActionCommand {
    @Override
    public void execute(Pet pet) {
        pet.setHealth(Math.min(100, pet.getHealth() + 20));
    }
    @Override
    public void undo(Pet pet) {
        pet.setHealth(Math.max(0, pet.getHealth() - 20));
    }
    @Override public String name() { return "Heal"; }
}

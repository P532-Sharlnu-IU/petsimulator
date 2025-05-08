package com.sharath.petsimulator.state;

import com.sharath.petsimulator.command.PetActionCommand;
import com.sharath.petsimulator.model.Pet;

public class SickState implements PetState {
    @Override
    public void handle(Pet pet, PetActionCommand cmd) {
        cmd.execute(pet);
        pet.setHealth(Math.min(100, pet.getHealth() + 10));
        pet.setState(new HappyState());
    }
    @Override public String name() { return "Sick"; }
}

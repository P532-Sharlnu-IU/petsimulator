package com.sharath.petsimulator.state;

import com.sharath.petsimulator.command.PetActionCommand;
import com.sharath.petsimulator.model.Pet;

public class HungryState implements PetState {
    @Override
    public void handle(Pet pet, PetActionCommand cmd) {
        cmd.execute(pet);
        // adjust and transition
        pet.setHunger(Math.max(0, pet.getHunger() - 20));
        pet.setHappiness(pet.getHappiness() + 10);
        pet.setState(new HappyState());
    }
    @Override public String name() { return "Hungry"; }
}
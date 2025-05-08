package com.sharath.petsimulator.state;

import com.sharath.petsimulator.command.PetActionCommand;
import com.sharath.petsimulator.model.Pet;

public class HappyState implements PetState {
    @Override
    public void handle(Pet pet, PetActionCommand cmd) {
        cmd.execute(pet);
        // if over-played, maybe get sleepy
        pet.setHappiness(Math.min(100, pet.getHappiness() + 5));
        pet.setState(new SleepyState());
    }
    @Override public String name() { return "Happy"; }
}

package com.sharath.petsimulator.state;

import com.sharath.petsimulator.command.PetActionCommand;
import com.sharath.petsimulator.model.Pet;

public class SleepyState implements PetState {
    @Override
    public void handle(Pet pet, PetActionCommand cmd) {
        cmd.execute(pet);
        pet.setHappiness(pet.getHappiness() - 5);
        pet.setState(new HungryState());
    }
    @Override public String name() { return "Sleepy"; }
}

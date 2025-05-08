package com.sharath.petsimulator.state;

import com.sharath.petsimulator.command.PetActionCommand;
import com.sharath.petsimulator.model.Pet;

public interface PetState {
    void handle(Pet pet, PetActionCommand cmd);
    String name();
}

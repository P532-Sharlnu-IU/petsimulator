package com.sharath.petsimulator.command;

import com.sharath.petsimulator.model.Pet;

public interface PetActionCommand {
    void execute(Pet pet);
    void undo(Pet pet);
    String name();
}

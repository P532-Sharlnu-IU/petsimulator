package com.sharath.petsimulator.command;

import com.sharath.petsimulator.model.Pet;

import java.util.Stack;

public class Invoker {
    private final Stack<PetActionCommand> history = new Stack<>();

    public void execute(PetActionCommand cmd, Pet pet) {
        cmd.execute(pet);
        history.push(cmd);
    }

    public void undo(Pet pet) {
        if (!history.isEmpty()) {
            PetActionCommand cmd = history.pop();
            cmd.undo(pet);
        }
    }
}

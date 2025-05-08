package com.sharath.petsimulator.model.repository;

import com.sharath.petsimulator.model.Pet;

public class ActivePetSession {
    private static final ActivePetSession INSTANCE = new ActivePetSession();
    private Pet currentPet;
    private ActivePetSession() {}
    public static ActivePetSession getInstance() { return INSTANCE; }
    public Pet getCurrentPet() { return currentPet; }
    public void setCurrentPet(Pet pet) { this.currentPet = pet; }
}

package com.sharath.petsimulator.routine;

import com.sharath.petsimulator.command.*;
import com.sharath.petsimulator.model.Pet;

public class StandardPetRoutine extends AbstractDailyRoutine {
    private final Invoker invoker = new Invoker();
    private final Pet pet;
    private final FeedCommand feed = new FeedCommand();
    private final PlayCommand play = new PlayCommand();

    public StandardPetRoutine(Pet pet) {
        this.pet = pet;
    }

    @Override
    protected void wakeUp() {
        System.out.println("Good morning, " + pet.getName() + "!");
    }
    @Override
    protected void morningRoutine() {
        invoker.execute(feed, pet);
        invoker.execute(play, pet);
    }
    @Override
    protected void afternoonRoutine() {
        invoker.execute(play, pet);
    }
    @Override
    protected void eveningRoutine() {
        invoker.execute(feed, pet);
    }
    @Override
    protected void sleep() {
        System.out.println("Good night, " + pet.getName() + "!");
    }
}

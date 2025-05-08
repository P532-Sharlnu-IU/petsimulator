package com.sharath.petsimulator.routine;

public abstract class AbstractDailyRoutine {
    public final void runDay() {
        wakeUp();
        morningRoutine();
        afternoonRoutine();
        eveningRoutine();
        sleep();
    }
    protected abstract void wakeUp();
    protected abstract void morningRoutine();
    protected abstract void afternoonRoutine();
    protected abstract void eveningRoutine();
    protected abstract void sleep();
}

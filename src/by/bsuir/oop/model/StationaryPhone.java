package by.bsuir.oop.model;

import by.bsuir.oop.model.Phone;

public abstract class StationaryPhone extends Phone {

    public boolean hasCallerID;
    public boolean hasAnsweringSystem;

    public int phoneBookMemory; // in phone numbers;

    public boolean isHasCallerID() {
        return hasCallerID;
    }

    public void setHasCallerID(boolean hasCallerID) {
        this.hasCallerID = hasCallerID;
    }

    public boolean isHasAnsweringSystem() {
        return hasAnsweringSystem;
    }

    public void setHasAnsweringSystem(boolean hasAnsweringSystem) {
        this.hasAnsweringSystem = hasAnsweringSystem;
    }

    public int getPhoneBookMemory() {
        return phoneBookMemory;
    }

    public void setPhoneBookMemory(int phoneBookMemory) {
        this.phoneBookMemory = phoneBookMemory;
    }
}

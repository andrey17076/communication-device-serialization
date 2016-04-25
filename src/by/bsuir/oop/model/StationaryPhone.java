package by.bsuir.oop.model;

public abstract class StationaryPhone extends Phone {

    public boolean hasCallerID;
    public boolean hasAnsweringSystem;

    private int phoneBookMemory; // in phone numbers;

    public boolean getHasCallerID() {
        return hasCallerID;
    }

    public void setHasCallerID(boolean hasCallerID) {
        this.hasCallerID = hasCallerID;
    }

    public boolean getHasAnsweringSystem() {
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

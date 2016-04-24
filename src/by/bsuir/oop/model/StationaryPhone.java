package by.bsuir.oop.model;

public abstract class StationaryPhone extends Phone {

    public boolean isHasCallerID;
    public boolean isHasAnsweringSystem;

    private int phoneBookMemory; // in phone numbers;

    public boolean isHasCallerID() {
        return isHasCallerID;
    }

    public void setHasCallerID(boolean hasCallerID) {
        isHasCallerID = hasCallerID;
    }

    public boolean isHasAnsweringSystem() {
        return isHasAnsweringSystem;
    }

    public void setHasAnsweringSystem(boolean hasAnsweringSystem) {
        isHasAnsweringSystem = hasAnsweringSystem;
    }

    public int getPhoneBookMemory() {
        return phoneBookMemory;
    }

    public void setPhoneBookMemory(int phoneBookMemory) {
        this.phoneBookMemory = phoneBookMemory;
    }
}

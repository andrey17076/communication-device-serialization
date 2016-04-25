package by.bsuir.oop.model;

public class CellPhone extends Phone {

    public int screenSize;
    public int usageTime;
    public int internalMemory; //in megabytes

    public boolean hasMemoryCardSupport;
    public boolean hasTouchScreen;

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(int usageTime) {
        this.usageTime = usageTime;
    }

    public int getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(int internalMemory) {
        this.internalMemory = internalMemory;
    }

    public boolean getHasMemoryCardSupport() {
        return hasMemoryCardSupport;
    }

    public void setHasMemoryCardSupport(boolean hasMemoryCardSupport) {
        this.hasMemoryCardSupport = hasMemoryCardSupport;
    }

    public boolean getHasTouchScreen() {
        return hasTouchScreen;
    }

    public void setHasTouchScreen(boolean hasTouchScreen) {
        this.hasTouchScreen = hasTouchScreen;
    }

}

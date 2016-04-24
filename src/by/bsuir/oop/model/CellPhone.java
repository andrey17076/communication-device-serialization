package by.bsuir.oop.model;

public class CellPhone extends Phone {

    public int screenSize;
    public int usageTime;
    public int internalMemory; //in megabytes

    public boolean isHasMemoryCardSupport;
    public boolean isHasTouchScreen;

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

    public boolean isHasMemoryCardSupport() {
        return isHasMemoryCardSupport;
    }

    public void setHasMemoryCardSupport(boolean hasMemoryCardSupport) {
        isHasMemoryCardSupport = hasMemoryCardSupport;
    }

    public boolean isHasTouchScreen() {
        return isHasTouchScreen;
    }

    public void setHasTouchScreen(boolean hasTouchScreen) {
        isHasTouchScreen = hasTouchScreen;
    }

}

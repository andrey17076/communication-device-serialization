package by.bsuir.oop.model;

public class CordedStationaryPhone extends StationaryPhone {

    private enum DialerType { DISK, KEYPAD };

    public int cordLength; //in metres;

    public boolean isWallMountable;
    public boolean hasHandSetKeyboard;

    public DialerType dialer;

    public int getCordLength() {
        return cordLength;
    }

    public void setCordLength(int cordLength) {
        this.cordLength = cordLength;
    }

    public boolean isWallMountable() {
        return isWallMountable;
    }

    public void setWallMountable(boolean wallMountable) {
        isWallMountable = wallMountable;
    }

    public boolean isHasHandSetKeyboard() {
        return hasHandSetKeyboard;
    }

    public void setHasHandSetKeyboard(boolean hasHandSetKeyboard) {
        this.hasHandSetKeyboard = hasHandSetKeyboard;
    }

    public DialerType getDialer() {
        return dialer;
    }

    public void setDialer(DialerType dialer) {
        this.dialer = dialer;
    }
}

package by.bsuir.oop.model;

public class CordedStationaryPhone extends StationaryPhone {

    public enum DialerType { DISK, KEYPAD };

    public int cordLength; //in metres;

    public boolean isWallMountable;
    public boolean isHasHandSetKeyboard;

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
        return isHasHandSetKeyboard;
    }

    public void setHasHandSetKeyboard(boolean hasHandSetKeyboard) {
        isHasHandSetKeyboard = hasHandSetKeyboard;
    }

    public DialerType getDialer() {
        return dialer;
    }

    public void setDialer(DialerType dialer) {
        this.dialer = dialer;
    }
}

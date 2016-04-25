package by.bsuir.oop.model;

public class CordedStationaryPhone extends StationaryPhone {

    public enum DialerType { DISK, KEYPAD }

    public int cordLength; //in metres;

    public boolean hasWallMounts;
    public boolean hasHandSetKeyboard;

    public DialerType dialer;

    public int getCordLength() {
        return cordLength;
    }

    public void setCordLength(int cordLength) {
        this.cordLength = cordLength;
    }

    public boolean getHasWallMounts() {
        return hasWallMounts;
    }

    public void setHasWallMounts(boolean hasWallMounts) {
        this.hasWallMounts = hasWallMounts;
    }

    public boolean getHasHandSetKeyboard() {
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

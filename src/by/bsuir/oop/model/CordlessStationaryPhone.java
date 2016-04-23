package by.bsuir.oop.model;

public class CordlessStationaryPhone extends StationaryPhone {

    public boolean hasBluetooth;
    public boolean hasPaging;

    public int coverage; //in metres

    public boolean isHasBluetooth() {
        return hasBluetooth;
    }

    public void setHasBluetooth(boolean hasBluetooth) {
        this.hasBluetooth = hasBluetooth;
    }

    public boolean isHasPaging() {
        return hasPaging;
    }

    public void setHasPaging(boolean hasPaging) {
        this.hasPaging = hasPaging;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }
}

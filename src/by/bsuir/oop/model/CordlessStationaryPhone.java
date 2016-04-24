package by.bsuir.oop.model;

public class CordlessStationaryPhone extends StationaryPhone {

    public boolean isHasBluetooth;
    public boolean isHasPaging;

    public int coverage; //in metres

    public boolean isHasBluetooth() {
        return isHasBluetooth;
    }

    public void setHasBluetooth(boolean hasBluetooth) {
        isHasBluetooth = hasBluetooth;
    }

    public boolean isHasPaging() {
        return isHasPaging;
    }

    public void setHasPaging(boolean hasPaging) {
        isHasPaging = hasPaging;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }
}

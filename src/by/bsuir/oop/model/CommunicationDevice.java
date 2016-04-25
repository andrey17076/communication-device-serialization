package by.bsuir.oop.model;

import java.io.Serializable;

public abstract class CommunicationDevice implements Serializable {

    public enum AntennaType { FIXED, FOLDING, INTERNAL, REMOVABLE }

    public String brand;
    public String model;

    public boolean hasVolumeControl;
    public AntennaType antenna;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getHasVolumeControl() {
        return hasVolumeControl;
    }

    public void setHasVolumeControl(boolean hasVolumeControl) {
        this.hasVolumeControl = hasVolumeControl;
    }

    public AntennaType getAntenna() {
        return antenna;
    }

    public void setAntenna(AntennaType antenna) {
        this.antenna = antenna;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
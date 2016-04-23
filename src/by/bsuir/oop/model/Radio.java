package by.bsuir.oop.model;

public class Radio extends CommunicationDevice {

    public int chanelNumber;
    public int communicationRange;
    public int transmitterPower;

    public int getChanelNumber() {
        return chanelNumber;
    }

    public void setChanelNumber(int chanelNumber) {
        this.chanelNumber = chanelNumber;
    }

    public int getCommunicationRange() {
        return communicationRange;
    }

    public void setCommunicationRange(int communicationRange) {
        this.communicationRange = communicationRange;
    }

    public int getTransmitterPower() {
        return transmitterPower;
    }

    public void setTransmitterPower(int transmitterPower) {
        this.transmitterPower = transmitterPower;
    }
}

package by.bsuir.oop.model;

public abstract class Phone extends CommunicationDevice {

    public boolean hasJack;
    public boolean hasSpeakerphone;

    public boolean getHasJack() {
        return hasJack;
    }

    public void setHasJack(boolean hasJack) {
        this.hasJack = hasJack;
    }

    public boolean getHasSpeakerphone() {
        return hasSpeakerphone;
    }

    public void setHasSpeakerphone(boolean hasSpeakerphone) {
        this.hasSpeakerphone = hasSpeakerphone;
    }
}

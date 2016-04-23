package by.bsuir.oop.model;

public abstract class Phone extends CommunicationDevice {

    public boolean hasJack;
    public boolean hasSpeakerphone;

    public boolean isHasJack() {
        return hasJack;
    }

    public void setHasJack(boolean hasJack) {
        this.hasJack = hasJack;
    }

    public boolean isHasSpeakerphone() {
        return hasSpeakerphone;
    }

    public void setHasSpeakerphone(boolean hasSpeakerphone) {
        this.hasSpeakerphone = hasSpeakerphone;
    }
}

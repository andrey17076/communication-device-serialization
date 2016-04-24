package by.bsuir.oop.model;

public abstract class Phone extends CommunicationDevice {

    public boolean isHasJack;
    public boolean isHasSpeakerphone;

    public boolean isHasJack() {
        return isHasJack;
    }

    public void setHasJack(boolean hasJack) {
        isHasJack = hasJack;
    }

    public boolean isHasSpeakerphone() {
        return isHasSpeakerphone;
    }

    public void setHasSpeakerphone(boolean hasSpeakerphone) {
        isHasSpeakerphone = hasSpeakerphone;
    }
}

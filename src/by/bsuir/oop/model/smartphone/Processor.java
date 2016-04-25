package by.bsuir.oop.model.smartphone;

import java.io.Serializable;

public class Processor implements Serializable{

    public String model;

    public int speed; //in megahertz;
    public int coresNumber;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCoresNumber() {
        return coresNumber;
    }

    public void setCoresNumber(int coresNumber) {
        this.coresNumber = coresNumber;
    }
}

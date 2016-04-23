package by.bsuir.oop.model.smartphone;

import by.bsuir.oop.model.CellPhone;

public class SmartPhone extends CellPhone {

    public Processor processor;
    public OperationSystem os;

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public OperationSystem getOs() {
        return os;
    }

    public void setOs(OperationSystem os) {
        this.os = os;
    }
}

package by.bsuir.oop.model.smartphone;

import by.bsuir.oop.model.CellPhone;

public class SmartPhone extends CellPhone {

    public Processor processor;
    public OperationSystem operationSystem;

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(OperationSystem os) {
        this.operationSystem = os;
    }
}

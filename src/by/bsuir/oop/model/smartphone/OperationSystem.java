package by.bsuir.oop.model.smartphone;

import java.io.Serializable;

public class OperationSystem implements Serializable {

    public String name;
    public String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

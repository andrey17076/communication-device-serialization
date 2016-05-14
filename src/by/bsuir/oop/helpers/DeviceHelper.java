package by.bsuir.oop.helpers;

import by.bsuir.oop.Controller;
import by.bsuir.oop.model.CommunicationDevice;
import javafx.scene.control.ListView;

public class DeviceHelper {

    Controller controller;

    public DeviceHelper(Controller controller) {
        this.controller = controller;
    }

    public void addDevice(Class cls, ListView<CommunicationDevice> listView) {
        controller.addDevice(cls, listView);
    }

    public void deleteDevices(ListView<CommunicationDevice> listView) {
        controller.deleteDevices(listView);
    }
}

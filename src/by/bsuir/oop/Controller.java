package by.bsuir.oop;

import by.bsuir.oop.model.*;
import by.bsuir.oop.model.smartphone.SmartPhone;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class Controller {

    private Class[] availableClasses = { Radio.class, CordedStationaryPhone.class, CordlessStationaryPhone.class, CellPhone.class, SmartPhone.class };
    private ArrayList<Object> devices = new ArrayList<>();
    private StringConverter<Class> stringConverter;

    public Controller() {
        stringConverter = new StringConverter<Class>() {
            @Override
            public String toString(Class object) {
                return object.getSimpleName();
            }

            @Override
            public Class fromString(String string) {
                try {
                    return Class.forName(string);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public StringConverter<Class> getStringConverter() {
        return stringConverter;
    }

    public void showDeviceFields(CommunicationDevice device, TableColumn fieldName, TableColumn fieldValue) {
    }

    public void addDevice(Class cls, ListView<CommunicationDevice> listView) {

        try {
            CommunicationDevice device;
            device = (CommunicationDevice) cls.newInstance();
            devices.add(device);
            listView.getItems().add(device);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void deleteDevices(ListView<CommunicationDevice> listView) {
        listView.getSelectionModel().getSelectedItems().forEach(item -> {
            devices.remove(item);
            listView.getItems().remove(item);
        });
    }

    public Class[] getAvailableClasses() {
        return availableClasses;
    }


}

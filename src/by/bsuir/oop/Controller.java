package by.bsuir.oop;

import by.bsuir.oop.helpers.FieldHelper;
import by.bsuir.oop.helpers.GuiHelper;
import by.bsuir.oop.model.*;
import by.bsuir.oop.model.smartphone.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Controller {

    private Class[] availableClasses = { Radio.class, CordedStationaryPhone.class, CordlessStationaryPhone.class, CellPhone.class, SmartPhone.class };
    private ArrayList<Object> devices = new ArrayList<>();

    public static VBox getObjectFieldsEditor(Object object) {

        VBox fieldsBox = new VBox();
        fieldsBox.setMinWidth(300);
        fieldsBox.setMaxWidth(300);
        fieldsBox.setPadding(new Insets(15));
        fieldsBox.setSpacing(10);

        Field[] fields = object.getClass().getFields();

        for (Field field: fields) {
            FieldHelper fieldHelper = new FieldHelper(object, field);
            Label fieldLabel = new Label(fieldHelper.getFieldName());

            if (fieldHelper.isIntField() || fieldHelper.isStringField()) {
                fieldsBox.getChildren().addAll(fieldLabel, GuiHelper.constructTextField(fieldHelper));
            } else if (fieldHelper.isBooleanField()) {
                fieldsBox.getChildren().addAll(fieldLabel, GuiHelper.constructCheckBox(fieldHelper));
            } else if (fieldHelper.isEnumField()) {
                fieldsBox.getChildren().addAll(fieldLabel, GuiHelper.constructComboBox(fieldHelper));
            } else {
                fieldsBox.getChildren().addAll(fieldLabel, GuiHelper.constructVBox(fieldHelper));
            }
        }
        return fieldsBox;
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

    public static StringConverter<Class> getClassStringConverter() {
        return new StringConverter<Class>() {
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
}

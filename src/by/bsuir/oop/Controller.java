package by.bsuir.oop;

import by.bsuir.oop.helpers.FieldHelper;
import by.bsuir.oop.helpers.GuiHelper;
import by.bsuir.oop.model.*;
import by.bsuir.oop.model.smartphone.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;

public class Controller {

    private static final Class[] availableClasses = { Radio.class, CordedStationaryPhone.class,
            CordlessStationaryPhone.class, CellPhone.class, SmartPhone.class };

    static Class[] getAvailableClasses() {
        return availableClasses;
    }

    public static VBox getObjectFieldsEditor(Object object) {

        VBox fieldsBox = new VBox();
        fieldsBox.setMinWidth(300);
        fieldsBox.setMaxWidth(300);
        fieldsBox.setPadding(new Insets(15));
        fieldsBox.setSpacing(10);

        if (object != null) {

            Field[] fields = object.getClass().getFields();

            for (Field field : fields) {
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
        }

        return fieldsBox;
    }

    static void addDevice(Class cls, ListView<CommunicationDevice> listView) {

        try {
            CommunicationDevice device;
            device = (CommunicationDevice) cls.newInstance();
            listView.getItems().add(device);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void deleteDevices(ListView<CommunicationDevice> listView) {
        CommunicationDevice selectedDevice = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(selectedDevice);
    }
}

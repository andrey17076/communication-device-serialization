package by.bsuir.oop;

import by.bsuir.oop.helpers.FieldHelper;
import by.bsuir.oop.helpers.GuiHelper;
import by.bsuir.oop.model.*;
import by.bsuir.oop.model.smartphone.*;
import by.bsuir.oop.serializers.BinarySerializer;
import by.bsuir.oop.serializers.Serializer;
import by.bsuir.oop.serializers.TextSerializer;
import by.bsuir.oop.serializers.XMLSerializer;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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
            listView.getSelectionModel().getSelectedItem();
            listView.getSelectionModel().select(device);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void deleteDevices(ListView<CommunicationDevice> listView) {
        CommunicationDevice selectedDevice = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(selectedDevice);
    }

    private static Serializer getSerializer(ToggleGroup radioGroup) {
        RadioButton selectedRButton = (RadioButton) radioGroup.getSelectedToggle();
        Serializer serializer;

        switch (selectedRButton.getText()) {
            case "Binary":
                serializer = new BinarySerializer();
                break;
            case "XML":
                serializer = new XMLSerializer();
                break;
            case "Text":
                serializer = new TextSerializer();
                break;
            default:
                serializer = new BinarySerializer();
        }
        return serializer;
    }

    static void saveToFile(ToggleGroup radioGroup, ListView<CommunicationDevice> listView, Stage stage) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            Serializer serializer = getSerializer(radioGroup);
            Object[] devices = listView.getItems().toArray();
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                serializer.serialize(devices, out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    static void loadFromFile(ToggleGroup radioGroup, ListView<CommunicationDevice> listView, Stage stage) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Serializer serializer = getSerializer(radioGroup);
            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                Object[] devices = (Object[]) serializer.deserialize(in);
                listView.getItems().clear();
                for (Object device : devices) {
                    listView.getItems().add((CommunicationDevice) device);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

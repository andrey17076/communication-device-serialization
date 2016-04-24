package by.bsuir.oop;

import by.bsuir.oop.model.*;
import by.bsuir.oop.model.smartphone.SmartPhone;
import com.sun.tools.javac.code.Attribute;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Controller {

    private Class[] availableClasses = { Radio.class, CordedStationaryPhone.class, CordlessStationaryPhone.class, CellPhone.class, SmartPhone.class };
    private ArrayList<Object> devices = new ArrayList<>();

    public StringConverter<Class> getClassStringConverter() {
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

    public void showDeviceFields(CommunicationDevice device, ScrollPane fieldsPane) {

        VBox fieldsBox = new VBox();
        fieldsBox.setMinWidth(300);
        fieldsBox.setMaxWidth(300);
        fieldsBox.setPadding(new Insets(15));
        fieldsBox.setSpacing(10);

        Field[] fields = device.getClass().getFields();

        for (Field field: fields) {

            HBox fieldBox = new HBox();
            fieldBox.setSpacing(10);

            Label fieldLabel = new Label(field.getName());
            fieldLabel.setMinHeight(27);
            fieldBox.getChildren().add(fieldLabel);

            Class fieldType = field.getType();

            try {
                String fieldName = field.getName();
                Method getMethod;

                if (fieldType.equals(int.class)) {
                    TextField fieldEdit = new TextField();
                    getMethod = device.getClass().getMethod("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1));
                    fieldEdit.setText(Integer.toString((Integer) getMethod.invoke(device)));
                    fieldBox.getChildren().add(fieldEdit);
                    fieldEdit.setOnKeyReleased(event -> {
                        try {
                            Method setMethod = device.getClass().getMethod("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1), fieldType);
                            setMethod.invoke(device, Integer.parseInt(fieldEdit.getText()));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (fieldType.equals(String.class)) {
                    TextField fieldEdit = new TextField();
                    getMethod = device.getClass().getMethod("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1));
                    fieldEdit.setText((String) getMethod.invoke(device));
                    fieldBox.getChildren().add(fieldEdit);
                    fieldEdit.setOnKeyReleased(event -> {
                        try {
                            Method setMethod = device.getClass().getMethod("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1), fieldType);
                            setMethod.invoke(device, fieldEdit.getText());
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (fieldType.equals(boolean.class)) {
                    CheckBox fieldCheckBox = new CheckBox();
                    fieldCheckBox.setMinHeight(24);
                    getMethod = device.getClass().getMethod(fieldName);
                    fieldCheckBox.setSelected((boolean) getMethod.invoke(device));
                    fieldBox.getChildren().add(fieldCheckBox);
                    fieldCheckBox.setOnMouseReleased(event -> {
                        try {
                            Method setMethod = device.getClass().getMethod("set" + fieldName.substring(2), fieldType);
                            setMethod.invoke(device, fieldCheckBox.isSelected());
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (fieldType.isEnum()) {
                    ComboBox<Enum> fieldComboBox = new ComboBox<>();
                    Field[] enumFields = field.getType().getFields();
                    for (Field enumField : enumFields) {
                        fieldComboBox.getItems().add(Enum.valueOf(fieldType, enumField.getName()));
                    }

                    getMethod = device.getClass().getMethod("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1));
                    fieldComboBox.getSelectionModel().select((Enum) getMethod.invoke(device));

                    fieldBox.getChildren().add(fieldComboBox);
                    fieldComboBox.setOnAction(event -> {
                        try {
                            Method setMethod = device.getClass().getMethod("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1), fieldType);
                            setMethod.invoke(device, fieldComboBox.getSelectionModel().getSelectedItem());
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    });
                }
                fieldsBox.getChildren().addAll(fieldBox);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        fieldsPane.setContent(fieldsBox);
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

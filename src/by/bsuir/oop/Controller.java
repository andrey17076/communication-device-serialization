package by.bsuir.oop;

import by.bsuir.oop.model.*;
import by.bsuir.oop.model.smartphone.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Controller {

    private Class[] availableClasses = { Radio.class, CordedStationaryPhone.class, CordlessStationaryPhone.class, CellPhone.class, SmartPhone.class };
    private ArrayList<Object> devices = new ArrayList<>();

    public VBox getObjectFieldsEditor(Object object) {

        VBox fieldsBox = new VBox();
        fieldsBox.setMinWidth(300);
        fieldsBox.setMaxWidth(300);
        fieldsBox.setPadding(new Insets(15));
        fieldsBox.setSpacing(10);

        Field[] fields = object.getClass().getFields();

        for (Field field: fields) {
            FieldHelper fieldHelper = new FieldHelper(object, field);
            Label fieldLabel = new Label(fieldHelper.getFieldName());

            try {

                if (fieldHelper.isIntField() || fieldHelper.isStringField()) {
                    TextField fieldEdit = new TextField();

                    if (fieldHelper.isIntField()) {
                        fieldEdit.setText(Integer.toString((Integer) fieldHelper.getFieldValue()));
                        fieldEdit.setOnKeyReleased(event -> fieldHelper.setFieldValue(Integer.parseInt(fieldEdit.getText())));
                    } else {
                        fieldEdit.setText((String) fieldHelper.getFieldValue());
                        fieldEdit.setOnKeyReleased(event -> fieldHelper.setFieldValue(fieldEdit.getText()));
                    }
                    fieldsBox.getChildren().addAll(fieldLabel, fieldEdit);

                } else if (fieldHelper.isBooleanField()) {

                    CheckBox fieldCheckBox = new CheckBox();
                    fieldCheckBox.setSelected((boolean) fieldHelper.getFieldValue());
                    fieldCheckBox.setOnMouseReleased(event -> fieldHelper.setFieldValue(fieldCheckBox.isSelected()));
                    fieldsBox.getChildren().addAll(fieldLabel, fieldCheckBox);

                } else if (fieldHelper.isEnumField()) {

                    ComboBox<Enum> fieldComboBox = new ComboBox<>();
                    Field[] enumFields = fieldHelper.getFieldType().getFields();
                    for (Field enumField : enumFields) {
                        fieldComboBox.getItems().add(Enum.valueOf(fieldHelper.getFieldType(), enumField.getName()));
                    }
                    fieldComboBox.getSelectionModel().select((Enum) fieldHelper.getFieldValue());
                    fieldComboBox.setOnAction(event -> fieldHelper.setFieldValue(fieldComboBox.getSelectionModel().getSelectedItem()));
                    fieldsBox.getChildren().addAll(fieldLabel, fieldComboBox);

                } else {

                    if (fieldHelper.getFieldValue() == null) {
                        Object objectField = fieldHelper.getFieldType().newInstance();
                        fieldHelper.setFieldValue(objectField);
                    }
                    fieldsBox.getChildren().addAll(fieldLabel, getObjectFieldsEditor(fieldHelper.getFieldValue()));
                }
            } catch ( IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
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
}

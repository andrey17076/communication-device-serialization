package by.bsuir.oop.helpers;

import by.bsuir.oop.Controller;
import by.bsuir.oop.packer.Packer;
import by.bsuir.oop.packer.PackerLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class GuiHelper {

    public static TextField constructTextField(FieldHelper fieldHelper) {
        TextField fieldEdit = new TextField();

        if (fieldHelper.isIntField()) {
            fieldEdit.setText(Integer.toString((Integer) fieldHelper.getFieldValue()));
            fieldEdit.setOnKeyReleased(event -> fieldHelper.setFieldValue(Integer.parseInt(fieldEdit.getText())));
        } else {
            fieldEdit.setText((String) fieldHelper.getFieldValue());
            fieldEdit.setOnKeyReleased(event -> fieldHelper.setFieldValue(fieldEdit.getText()));
        }

        return fieldEdit;
    }

    public static CheckBox constructCheckBox(FieldHelper fieldHelper) {
        CheckBox fieldCheckBox = new CheckBox();
        fieldCheckBox.setSelected((boolean) fieldHelper.getFieldValue());
        fieldCheckBox.setOnMouseReleased(event -> fieldHelper.setFieldValue(fieldCheckBox.isSelected()));

        return fieldCheckBox;
    }

    public static ComboBox constructComboBox(FieldHelper fieldHelper) {
        ComboBox<Enum> fieldComboBox = new ComboBox<>();
        Field[] enumFields = fieldHelper.getFieldType().getFields();
        for (Field enumField : enumFields) {
            fieldComboBox.getItems().add(Enum.valueOf(fieldHelper.getFieldType(), enumField.getName()));
        }
        fieldComboBox.getSelectionModel().select((Enum) fieldHelper.getFieldValue());
        fieldComboBox.setOnAction(event -> fieldHelper.setFieldValue(fieldComboBox.getSelectionModel().getSelectedItem()));

        return fieldComboBox;
    }

    public static VBox constructVBox(FieldHelper fieldHelper) {
        if (fieldHelper.getFieldValue() == null) {
            Object objectField = null;
            try {
                objectField = fieldHelper.getFieldType().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            fieldHelper.setFieldValue(objectField);
        }
        return Controller.getObjectFieldsEditor(fieldHelper.getFieldValue());
    }

    public static ComboBox<Packer> constructPackerComboBox() {
        ComboBox<Packer> packerComboBox= new ComboBox<>();
        packerComboBox.setMinWidth(120);
        packerComboBox.setMaxWidth(120);
        packerComboBox.getItems().add(new Packer());
        ArrayList<Packer> packers = PackerLoader.getPackers();
        for (Packer packer : packers)
            packerComboBox.getItems().add(packer);

        return packerComboBox;
    }

    public static StringConverter<Class> getClassStringConverter() {
        return new StringConverter<Class>() {
            @Override
            public String toString(Class object) {
                return object.getSimpleName();
            }

            @Override
            public Class fromString(String string) {
                Class cls = null;
                try {
                    cls = Class.forName(string);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return cls;
            }
        };
    }
}

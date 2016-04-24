package by.bsuir.oop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Serialization menu controls
        RadioButton binaryRadioButton = new RadioButton("Binary");
        RadioButton xmlRadioButton = new RadioButton("XML");
        RadioButton textRadioButton = new RadioButton("Text");

        ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.getToggles().addAll(binaryRadioButton, xmlRadioButton, textRadioButton);

        Button openButton = new Button("Open");
        openButton.setMinWidth(90);
        openButton.setMaxWidth(90);

        Button saveButton = new Button("Save");
        saveButton.setMinWidth(90);
        saveButton.setMaxWidth(90);

        //List of devices
        ListView listView = new ListView();
        listView.setPrefHeight(Region.USE_COMPUTED_SIZE);
        listView.setMinWidth(300);
        listView.setMaxWidth(300);

        //List controls
        ComboBox devicesComboBox = new ComboBox();
        devicesComboBox.setMinWidth(200);
        devicesComboBox.setMaxWidth(200);

        Button addButton = new Button("Add");
        addButton.setMinWidth(90);
        addButton.setMaxWidth(90);

        Button deleteButton = new Button("Delete");
        deleteButton.setMinWidth(90);
        deleteButton.setMaxWidth(90);

        //GridPane for control buttons
        GridPane gridPane = new GridPane();
        gridPane.setMinWidth(300);
        gridPane.setMaxWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        GridPane.setConstraints(devicesComboBox, 0, 0);
        GridPane.setConstraints(addButton, 1, 0);
        GridPane.setConstraints(deleteButton, 1, 1);
        gridPane.getChildren().addAll(devicesComboBox, addButton, deleteButton);

        //Table for fields
        TableView fieldsTable = new TableView();
        fieldsTable.setMinWidth(300);
        fieldsTable.setMaxWidth(300);
        fieldsTable.setEditable(true);
        fieldsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn fieldName = new TableColumn("Field Name");
        fieldName.setMinWidth(200);
        fieldName.setMaxWidth(200);

        TableColumn fieldValue = new TableColumn("Value");
        fieldValue.setMinWidth(100);
        fieldValue.setMaxWidth(100);

        fieldsTable.getColumns().addAll(fieldName, fieldValue);

        //Menu for Serializers
        VBox serializersMenu = new VBox();
        serializersMenu.setSpacing(10);
        serializersMenu.getChildren().addAll(binaryRadioButton, xmlRadioButton, textRadioButton, openButton, saveButton);

        //Communication devices list editing menu
        VBox listMenu = new VBox();
        listMenu.setPrefHeight(Region.USE_COMPUTED_SIZE);
        listMenu.setSpacing(10);
        listMenu.getChildren().addAll(listView, gridPane);

        //Main Layout
        HBox mainLayout = new HBox();
        mainLayout.setPadding(new Insets(15));
        mainLayout.setSpacing(10);
        mainLayout.getChildren().addAll(serializersMenu, listMenu, fieldsTable);

        //Primary Stage
        Scene scene = new Scene(mainLayout, 740, 350);
        primaryStage.setMinWidth(740);
        primaryStage.setMinHeight(350);
        primaryStage.setTitle("Communication Devices");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

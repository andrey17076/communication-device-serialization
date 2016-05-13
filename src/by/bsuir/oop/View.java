package by.bsuir.oop;

import by.bsuir.oop.helpers.GuiHelper;
import by.bsuir.oop.model.CommunicationDevice;
import by.bsuir.oop.packer.Packer;
import by.bsuir.oop.packer.PackerLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Pane for device fields
        ScrollPane fieldsPane = new ScrollPane();
        fieldsPane.setMinWidth(300);
        fieldsPane.setMaxWidth(300);
        fieldsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //List of devices
        ListView<CommunicationDevice> listView = new ListView<>();
        listView.setPrefHeight(Region.USE_COMPUTED_SIZE);
        listView.setMinWidth(300);
        listView.setMaxWidth(300);
        listView.setOnMousePressed(event -> {
            CommunicationDevice selected = listView.getSelectionModel().getSelectedItem();
            fieldsPane.setContent(Controller.getObjectFieldsEditor(selected));
        });

        //List controls
        ComboBox<Class> devicesComboBox = new ComboBox<>();
        devicesComboBox.setMinWidth(200);
        devicesComboBox.setMaxWidth(200);
        devicesComboBox.getItems().addAll(Controller.getAvailableClasses());
        devicesComboBox.setConverter(GuiHelper.getClassStringConverter());

        devicesComboBox.getSelectionModel().selectFirst();

        Button addButton = new Button("Add");
        addButton.setMinWidth(90);
        addButton.setMaxWidth(90);
        addButton.setOnMousePressed(event -> {
            Controller.addDevice(devicesComboBox.getValue(), listView);
            listView.fireEvent(event);
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setMinWidth(90);
        deleteButton.setMaxWidth(90);
        deleteButton.setOnMousePressed(event -> {
            Controller.deleteDevices(listView);
            fieldsPane.setContent(null);
        });

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

        //Serialization menu controls
        RadioButton binaryRadioButton = new RadioButton("Binary");
        RadioButton xmlRadioButton = new RadioButton("XML");
        RadioButton textRadioButton = new RadioButton("Text");

        ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.getToggles().addAll(binaryRadioButton, xmlRadioButton, textRadioButton);
        radioGroup.selectToggle(binaryRadioButton);

        Button openButton = new Button("Open");
        openButton.setMinWidth(90);
        openButton.setMaxWidth(90);
        openButton.setOnAction(event -> {
            Controller.loadFromFile(radioGroup, listView, primaryStage);
            fieldsPane.setContent(null);
        });

        Button saveButton = new Button("Save");
        saveButton.setMinWidth(90);
        saveButton.setMaxWidth(90);
        saveButton.setOnAction(event -> {
            Controller.saveToFile(radioGroup, listView, primaryStage);
            fieldsPane.setContent(null);
        });

        //Plugin menu
        VBox pluginMenu = new VBox();
        pluginMenu.setSpacing(10);
        Separator separator = new Separator(Orientation.HORIZONTAL);
        Label pluginLabel = new Label("Pack before with:");
        ComboBox<Packer> packerComboBox = GuiHelper.constructPackerComboBox();
        packerComboBox.getSelectionModel().selectFirst();
        pluginMenu.getChildren().addAll(separator, pluginLabel, packerComboBox);


        //Menu for Serializers
        VBox serializersMenu = new VBox();
        serializersMenu.setSpacing(10);
        serializersMenu.getChildren().addAll(binaryRadioButton, xmlRadioButton, textRadioButton, openButton, saveButton, pluginMenu);

        //Communication devices list editing menu
        VBox listMenu = new VBox();
        listMenu.setPrefHeight(Region.USE_COMPUTED_SIZE);
        listMenu.setSpacing(10);
        listMenu.getChildren().addAll(listView, gridPane);

        //View Layout
        HBox mainLayout = new HBox();
        mainLayout.setPadding(new Insets(15));
        mainLayout.setSpacing(10);
        mainLayout.getChildren().addAll(serializersMenu, listMenu, fieldsPane);

        //Primary Stage
        Scene scene = new Scene(mainLayout, 770, 350);
        primaryStage.setMinWidth(770);
        primaryStage.setMinHeight(350);
        primaryStage.setTitle("Communication Devices");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        File dir = new File(PackerLoader.pluginsDir);
        if (!dir.exists())
            dir.mkdir();

        launch(args);
    }
}

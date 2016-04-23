package by.bsuir.oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Menu for Serializers
        VBox serializersMenu = new VBox();

        //Communication devices list editing menu
        VBox listMenu = new VBox();

        //Pane for device's fields editing
        Pane fieldsEditingPane = new Pane();

        //Main Layout
        HBox mainLayout = new HBox();
        mainLayout.getChildren().addAll(serializersMenu, listMenu, fieldsEditingPane);

        //Primary Stage
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(350);
        primaryStage.setTitle("Communication Devices");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

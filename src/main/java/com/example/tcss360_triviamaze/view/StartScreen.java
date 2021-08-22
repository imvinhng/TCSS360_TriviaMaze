package com.example.tcss360_triviamaze.view;

import com.example.tcss360_triviamaze.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartScreen extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-scene.fxml"));
//        Parent root = FXMLLoader.load(HelloApplication.class.getResource("event-handling.fxml"));
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("start-scene.fxml"));
        Scene scene = new Scene(root);


        File file = new File("C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\main\\java\\com\\example\\tcss360_triviamaze\\smiley.jpg");
        Image image = new Image(file.toURI().toString());

        // for 1 scene
        scene.getStylesheets().add(HelloApplication.class.getResource("css/application.css").toExternalForm());

        // for multiple scenes
//        String css = HelloApplication.class.getResource("css/application.css").toExternalForm();
//        scene.getStylesheets().add(css);

        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Main menu!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

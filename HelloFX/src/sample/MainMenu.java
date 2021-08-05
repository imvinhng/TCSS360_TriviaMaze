package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;

public class MainMenu extends Application {
    final Dimension BUTTON_SIZE = new Dimension(20,50);

        @Override
        public void start(Stage primaryStage) throws Exception {
            try {

                Pane root = new Pane();

                Button button = new Button( "Click me!");
                button.setStyle("-fx-text-fill: white; -fx-background-color: red;");

                button.setOnAction(e -> {
                    System.out.println("Bye! See you later!");
                    Platform.exit();
                });

                root.getChildren().add(button);

                Scene scene = new Scene(root, 800, 400);
                primaryStage.setScene(scene);
                primaryStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public static void main(String[] args) {
            Application.launch(args);

        }
}

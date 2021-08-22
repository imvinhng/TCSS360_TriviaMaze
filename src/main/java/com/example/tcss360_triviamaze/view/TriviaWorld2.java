package com.example.tcss360_triviamaze.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class TriviaWorld2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene myScene = new Scene(root, 1000, 650, Color.SKYBLUE);

        File file = new File("C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\main\\java\\com\\example\\tcss360_triviamaze\\smiley-too.jpg");
        Image image = new Image(file.toURI().toString());
//        ImageView iv = new ImageView(image);
//        root.getChildren().add(iv);
//        primaryStage.setHeight(420);
//        primaryStage.setWidth(420);
//        primaryStage.setResizable(false);
//        primaryStage.setX(50);
//        primaryStage.setY(50);
//        primaryStage.setFullScreen(true);
//        primaryStage.setFullScreenExitHint("YOU CAN'T ESCAPE unless you press q");
//        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

        Text text = new Text();
        text.setText("WOOOOOAA!");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.DARKGREEN);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        line.setOpacity(0.5);
        line.setRotate(45);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.BLUE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);

//        Polygon triangle = new Polygon();
//        triangle.getPoints().setAll(
//                200.0, 200.0,
//                300.0, 300.0,
//                200.0, 300.0
//        );
//        triangle.setFill(Color.YELLOW);

        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.ORANGE);

        File myFile2 = new File("C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\images\\pizza_steve_cheers.png");
        Image playerImage = new Image(myFile2.toURI().toString());

        ImageView imageView = new ImageView(playerImage);
        imageView.setX(180);
        imageView.setY(180);
        imageView.setRotate(-90);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        root.getChildren().add(imageView);
        root.getChildren().add(circle);
//        root.getChildren().add(triangle);
        root.getChildren().add(rectangle);
        root.getChildren().add(line);
        root.getChildren().add(text);
        primaryStage.getIcons().add(image);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}

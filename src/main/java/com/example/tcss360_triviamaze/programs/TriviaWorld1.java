package com.example.tcss360_triviamaze.programs;

import com.example.tcss360_triviamaze.structures.LShape;
import com.example.tcss360_triviamaze.structures.Room;
import com.example.tcss360_triviamaze.structures.ShapeSuper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class TriviaWorld1 extends Application {
    private final Dimension GAME_SIZE = new Dimension(800, 600);
    private final double playerVelocity = 50;
    private final double ROOM_BORDER = 10;
    private boolean inGame = false;
    private boolean endReach = false;
    private String currentKey = "";
    private Dimension L_startingPos = new Dimension(200,200);
    private LShape myLShape;
    private Room demoRoom;

    /** Player X Position.
     * make sure to change this variable according to the control system
     * for mouse based, we set this to 0 0, since the mouse dictate where the player is
     * for keyboard based, we set this to the beginning of the maze (0,0)
     */
    private double playerXPos = 0.0;
    private double playerYPos = 0.0;

    private final int PLAYER_SIDE = 50;

    public void start(Stage stage) throws Exception {
        stage.setTitle("Maze");
        //background size
        Canvas canvas = new Canvas(GAME_SIZE.width, GAME_SIZE.height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //JavaFX Timeline = free form animation defined by KeyFrames and their duration
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        //number of cycles in animation INDEFINITE = repeat indefinitely
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse control (move and click)
//        canvas.setOnMouseMoved(e -> {
//            playerOneXPos  = e.getX();
//            playerOneYPos = e.getY();
//        });

//        canvas.setOnMouseClicked(e ->  gameStarted = true);

        Scene basicScene = new Scene(new StackPane(canvas));
        stage.setScene(basicScene);
        stage.show();
        tl.play();


        //keyboard control
        basicScene.setOnKeyPressed(e -> {
//            System.out.println("Key was pressed " + e.getCode() + " \n" + e);

            inGame = true;
            currentKey = e.getCode().toString();

            if ((e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) && (playerYPos - playerVelocity) >= 0) {

                if (!collision(myLShape, "UP")) {
                    playerYPos -= playerVelocity;
                }
            }
            if ((e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) && (playerYPos + playerVelocity) <= GAME_SIZE.height) {

                if (!collision(myLShape, "DOWN")) {
                    playerYPos += playerVelocity;
                }
            }
            if ((e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) && (playerXPos - playerVelocity) >= 0) {
                // obstacle collision

                if (!collision(myLShape, "LEFT")) {
                    playerXPos -= playerVelocity;
                }
            }
            if ((e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) && (playerXPos + playerVelocity) <= GAME_SIZE.width) {

                if (!collision(myLShape, "RIGHT")) {
                    playerXPos += playerVelocity;
                }

            }


        });

    }

    private boolean collision(ShapeSuper shape, String direction) {
        boolean collide = true;

        if (direction.equalsIgnoreCase("UP")) {
            if (playerXPos == 200 && (playerYPos - playerVelocity) == 350
                    || playerXPos == 250 && (playerYPos - playerVelocity) == 350
                    || playerXPos == 300 && (playerYPos - playerVelocity) == 350) {

                collide = true;

            } else {
                collide = false;
            }

        } else if (direction.equalsIgnoreCase("DOWN")) {
            if (playerXPos == 200 && (playerYPos + playerVelocity) == 200
                    || playerXPos == 200 && (playerYPos + playerVelocity) == 350
                    || playerXPos == 250 && (playerYPos + playerVelocity) == 350
                    || playerXPos == 300 && (playerYPos + playerVelocity) == 350) {

                collide = true;

            }else {
                collide = false;
            }
        } else if (direction.equalsIgnoreCase("LEFT")) {
            if ( (playerXPos - playerVelocity) == 200 && playerYPos == 200
                    || (playerXPos - playerVelocity) == 200 && playerYPos == 250
                    || (playerXPos - playerVelocity) == 200 && playerYPos == 300
                    || (playerXPos - playerVelocity) == 300 && playerYPos == 350) {

                collide = true;

            }  else {
                collide = false;
            }
        } else if (direction.equalsIgnoreCase("RIGHT")) {
            if ((playerXPos + playerVelocity) == 200 && playerYPos == 200
                    || (playerXPos + playerVelocity) == 200 && playerYPos == 250
                    || (playerXPos + playerVelocity) == 200 && playerYPos == 300
                    || (playerXPos + playerVelocity) == 200 && playerYPos == 350) {

                collide = true;

            }  else {
                collide = false;
            }
        } else {
            collide = false;
        }

        return collide;
    }

    public void run(GraphicsContext gc) {
        //set graphics
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_SIZE.width, GAME_SIZE.height);

        // draw Title
        drawTitle(gc);

        //set text
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(inGame &&!endReach) {

            if (playerXPos >= GAME_SIZE.width - PLAYER_SIDE) {
                playerXPos = GAME_SIZE.width - PLAYER_SIDE;
            }
            if (playerYPos >= GAME_SIZE.height - PLAYER_SIDE) {
                playerYPos = GAME_SIZE.height - PLAYER_SIDE;
            }

            drawPlayer(gc);
//            drawScore(gc);
            drawObstacle(gc, "L");
            drawObstacle(gc, "ROOM");

            // test for end state
            if (playerXPos == GAME_SIZE.width - PLAYER_SIDE && playerYPos == GAME_SIZE.height - PLAYER_SIDE) {
                inGame = false;
                drawEndingScreen(gc, true);
                System.out.println("Ending screen was drawn");
            }




        } else {
            //set the start text

            gc.setFill(Color.BLUEVIOLET);
            gc.setFont (new Font ("TimesRoman", 20));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Press any key to start game", GAME_SIZE.width / 2, 2 * GAME_SIZE.height / 3);

        }




    }


    /**
     * Utility method to draw player (if needed).
     * @param gc
     */
    private void drawEndingScreen(GraphicsContext gc, boolean endReach) {
        if (endReach) {
            System.out.println("I'm here");
            gc.setFill(Color.WHITE);
            gc.fillText("Maze solved. Congratulations!", GAME_SIZE.width/2, GAME_SIZE.height/2);
        } else {
            System.out.println("You failed");
            gc.setFill(Color.WHITE);
            gc.fillText("Maze locked. Try again?", GAME_SIZE.width/2, GAME_SIZE.height/2);
        }
    }



    /**
     * Utility method to draw player (if needed).
     * @param gc
     */
    private void drawPlayer(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(playerXPos, playerYPos, PLAYER_SIDE, PLAYER_SIDE);
    }

    private void drawTitle(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillText("TriviaWorld1", GAME_SIZE.width/2, GAME_SIZE.height/3);
    }
    /**
     * Utility method to draw score (if needed).
     * @param gc
     */
    private void drawScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFill(Color.CORAL);
        gc.fillText(currentKey, GAME_SIZE.width/2, GAME_SIZE.height/2 + 50);

    }

    /**
     * Draw some bassic-shape obstacles.
     * @param theShapeName string
     * @param gc
     */
    private void drawObstacle(GraphicsContext gc, String theShapeName) {
        if (theShapeName.equals("L")) {
            myLShape = new LShape(gc, Color.ORANGE);
            myLShape.draw();
        }
        if (theShapeName.equals("T")) {

        }
        if (theShapeName.equals("J")) {
            gc.fillRect(150, 0, ROOM_BORDER, 150);
            gc.fillRect(0, 150, 150 + ROOM_BORDER, ROOM_BORDER);
        }
        if (theShapeName.equals("U")) {

        }
        if (theShapeName.equals("N")) {

        }
        if (theShapeName.equals("ROOM")) {
            demoRoom = new Room(gc, Color.CADETBLUE);
            demoRoom.draw();
        }
    }

    // start the application
    public static void main(String[] args) {
        launch(args);
    }


}
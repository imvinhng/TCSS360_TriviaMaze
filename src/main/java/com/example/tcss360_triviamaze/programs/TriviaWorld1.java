package com.example.tcss360_triviamaze.programs;

import com.example.tcss360_triviamaze.structures.LShape;
import com.example.tcss360_triviamaze.structures.Player;
import com.example.tcss360_triviamaze.structures.Room;
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
    private final Dimension GAME_SIZE = new Dimension(900, 600);
    private final int ROOM_TOTAL = 24;
    private boolean inGame = false;
    private boolean endReach = false;
    private Room[] myRooms = new Room[ROOM_TOTAL];
    private String currentKey = "";
    private Dimension L_startingPos = new Dimension(200,200);
    private LShape myLShape;
    private Player myPlayer;
    private Room myCurrentRoom, myStartRoom, myEndRoom;


    public void start(Stage stage) throws Exception {
        stage.setTitle("Maze");
        //background size
        Canvas canvas = new Canvas(GAME_SIZE.width, GAME_SIZE.height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // make sure to call this before run()
        populateRoomArray(gc);
        populatePlayer(gc);

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
            int playerY = myPlayer.getPlayerY();
            int playerX = myPlayer.getPlayerX();
            int playerVelocity =  myPlayer.getVelocity();

            if ((e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) && (playerY - playerVelocity) >= 0) {

                if (!collision("UP")) {
                    myPlayer.setPlayerY(playerY - playerVelocity);

                }
            }
            if ((e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) && (playerY + playerVelocity) <= GAME_SIZE.height) {

                if (!collision("DOWN")) {
                    myPlayer.setPlayerY(playerY + playerVelocity);
                }
            }
            if ((e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) && (playerX - playerVelocity) >= 0) {
                // obstacle collision

                if (!collision("LEFT")) {
                    myPlayer.setPlayerX(playerX - playerVelocity);
                }
            }
            if ((e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) && (playerX + playerVelocity) <= GAME_SIZE.width) {

                if (!collision("RIGHT")) {
                    myPlayer.setPlayerX(playerX + playerVelocity);
                }

            }

        });

    }

    private boolean collision(String direction) {
        boolean collide = false;
        final boolean PERMISSION_LEFT = true;
        final boolean PERMISSION_RIGHT = true;
        final boolean PERMISSION_UP = true;
        final boolean PERMISSION_DOWN = true;
        Point updatedPos;

        if (direction.equalsIgnoreCase("UP")) {

            updatedPos = new Point(myPlayer.getPlayerX(), myPlayer.getPlayerY() - myPlayer.getVelocity());
            if (updateCurrentRoom(updatedPos, PERMISSION_UP) && PERMISSION_UP) {
                collide = false;
            } else if (updateCurrentRoom(updatedPos, PERMISSION_UP) && !PERMISSION_UP) {
                collide = true;
            }


        } else if (direction.equalsIgnoreCase("DOWN")) {

            updatedPos = new Point(myPlayer.getPlayerX(), myPlayer.getPlayerY() + myPlayer.getVelocity());
            if (updateCurrentRoom(updatedPos, PERMISSION_DOWN) && PERMISSION_DOWN) {
                collide = false;
            } else if (updateCurrentRoom(updatedPos, PERMISSION_DOWN) && !PERMISSION_DOWN) {
                collide = true;
            }


        } else if (direction.equalsIgnoreCase("LEFT")) {

            updatedPos = new Point(myPlayer.getPlayerX() - myPlayer.getVelocity(), myPlayer.getPlayerY());
            if (updateCurrentRoom(updatedPos, PERMISSION_LEFT) && PERMISSION_LEFT) {
                collide = false;
            } else if (updateCurrentRoom(updatedPos, PERMISSION_LEFT) && !PERMISSION_LEFT) {
                collide = true;
            }

        } else if (direction.equalsIgnoreCase("RIGHT")) {

            updatedPos = new Point(myPlayer.getPlayerX() + myPlayer.getVelocity(), myPlayer.getPlayerY());
            if (updateCurrentRoom(updatedPos, PERMISSION_RIGHT) && PERMISSION_RIGHT) {
                collide = false;
            } else if (updateCurrentRoom(updatedPos, PERMISSION_RIGHT) && !PERMISSION_RIGHT) {
                collide = true;
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

            int playerX = myPlayer.getPlayerX();;
            int playerY = myPlayer.getPlayerY();

            if (playerX >= GAME_SIZE.width - myPlayer.PLAYER_SIZE.width) {
                myPlayer.setPlayerX(GAME_SIZE.width - myPlayer.PLAYER_SIZE.width);
            }
            if (playerY >= GAME_SIZE.height - myPlayer.PLAYER_SIZE.height) {
                myPlayer.setPlayerY(GAME_SIZE.height - myPlayer.PLAYER_SIZE.height);
            }

            myPlayer.drawPlayer();
//            drawScore(gc);
//            drawObstacle(gc, "L");
//            drawObstacle(gc, "ROOM");
            drawRooms();

//            System.out.println("Current player position: " + myPlayer.getPos());

            // test for end state
            if (myEndRoom.containsPlayer(myPlayer.getPos())) {
                inGame = false;
                drawEndingScreen(gc, true);
                System.out.println("Ending screen was drawn");
//                Platform.exit();
            }




        } else {
            //set the start text

            gc.setFill(Color.BLUEVIOLET);
            gc.setFont (new Font ("TimesRoman", 20));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Press any key to start game", GAME_SIZE.width / 2, 2 * GAME_SIZE.height / 3);

        }




    }

    private boolean updateCurrentRoom(final Point theUpdatedPos, boolean permission) {
        boolean update = false;

        for (Room r : myRooms) {
            if (r.containsPlayer(theUpdatedPos) && r != myCurrentRoom) {
                System.out.println("Player is trying to move from " + myCurrentRoom.roomID + " to " + r.roomID);
                update = true;
                if (permission) {
                    System.out.println("You shall pass!");
                    myCurrentRoom = r;
                } else {
                    System.out.println("You shall not pass!");
                }
            }
        }

        return update;
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

    private void drawRooms() {
        for (Room r : myRooms) {
            r.draw();
        }
    }

    /**
     * Draw some basic-shape obstacles.
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

        }
        if (theShapeName.equals("U")) {

        }
        if (theShapeName.equals("N")) {

        }
    }

    private void populateRoomArray(GraphicsContext gc) {
        int counter = 0;
        for(int i = 0; i < GAME_SIZE.width; i+=150) {
            for (int y = 0; y < GAME_SIZE.height; y+=150) {
                myRooms[counter] = new Room(gc, Color.CADETBLUE, i, y);
                myRooms[counter].roomID = counter;
//                myRooms[counter].draw();
                counter++;
            }
        }

        myStartRoom = myRooms[0];
        myCurrentRoom = myStartRoom;
        myEndRoom = myRooms[myRooms.length-1];

    }

    private void populatePlayer(GraphicsContext gc) {
        myPlayer = new Player(gc, Color.WHITE, 0, 0);

    }

    // start the application
    public static void main(String[] args) {
        launch(args);
    }


}

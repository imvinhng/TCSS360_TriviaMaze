package com.example.tcss360_triviamaze.programs;

import com.example.tcss360_triviamaze.structures.Door;
import com.example.tcss360_triviamaze.structures.LShape;
import com.example.tcss360_triviamaze.structures.Player;
import com.example.tcss360_triviamaze.structures.Room;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
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
    private Alert myAlert = new Alert(Alert.AlertType.NONE);


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
        boolean currPath = false;
        boolean nextPath = false;
        final boolean PATH_PERMISSION_WEST = false;
        final boolean PATH_PERMISSION_EAST = false;
        final boolean PATH_PERMISSION_NORTH = false;
        final boolean PATH_PERMISSION_SOUTH = false;
        Point updatedPos;

        // this prohibits the player from going through walls
        currPath = collideWithDoors(myPlayer.getPos());


        if (direction.equalsIgnoreCase("UP")) {

            updatedPos = new Point(myPlayer.getPlayerX(), myPlayer.getPlayerY() - myPlayer.getVelocity());

            nextPath = collideWithDoors(updatedPos);
            if (nextPath) {
                System.out.println("Player is moving to next to a door path");
            }


            if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_NORTH)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_NORTH) && currPath && PATH_PERMISSION_NORTH) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_NORTH) && (!PATH_PERMISSION_NORTH || !currPath)) {
                collide = true;

                myAlert.setAlertType(Alert.AlertType.ERROR);
                myAlert.setContentText("Player is attempting to cross a locked door. Permission denied!");
                myAlert.show();

                System.out.println("Player is attempting to cross a locked door. Permission denied!");
            }


        } else if (direction.equalsIgnoreCase("DOWN")) {

            updatedPos = new Point(myPlayer.getPlayerX(), myPlayer.getPlayerY() + myPlayer.getVelocity());

            nextPath = collideWithDoors(updatedPos);
            if (nextPath) {
                System.out.println("Player is moving to next to a door path");
            }

            if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_SOUTH)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_SOUTH) && nextPath && PATH_PERMISSION_SOUTH) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_SOUTH) && (!PATH_PERMISSION_SOUTH || !nextPath)) {
                collide = true;

                myAlert.setAlertType(Alert.AlertType.ERROR);
                myAlert.setContentText("Player is attempting to cross a locked door. Permission denied!");
                myAlert.show();

                System.out.println("Player is attempting to cross a locked door. Permission denied!");
            }


        } else if (direction.equalsIgnoreCase("LEFT")) {

            updatedPos = new Point(myPlayer.getPlayerX() - myPlayer.getVelocity(), myPlayer.getPlayerY());

            nextPath = collideWithDoors(updatedPos);
            if (nextPath) {
                System.out.println("Player is moving to next to a door path");
            }


            if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_WEST)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_WEST) && nextPath && PATH_PERMISSION_WEST) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_WEST) && (!PATH_PERMISSION_WEST || !nextPath)) {
                collide = true;

                myAlert.setAlertType(Alert.AlertType.ERROR);
                myAlert.setContentText("Player is attempting to cross a locked door. Permission denied!");
                myAlert.show();

                System.out.println("Player is attempting to cross a locked door. Permission denied!");
            }

        } else if (direction.equalsIgnoreCase("RIGHT")) {

            updatedPos = new Point(myPlayer.getPlayerX() + myPlayer.getVelocity(), myPlayer.getPlayerY());

            nextPath = collideWithDoors(updatedPos);
            if (nextPath) {
                System.out.println("Player is moving to next to a door path");
            }



            if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_EAST)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_EAST) && nextPath && PATH_PERMISSION_EAST) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_EAST) && (!PATH_PERMISSION_EAST || !nextPath)) {
                collide = true;

                myAlert.setAlertType(Alert.AlertType.ERROR);
                myAlert.setContentText("Player is attempting to cross a locked door. Permission denied!");
                myAlert.show();
                System.out.println("Player is attempting to cross a locked door. Permission denied!");
            }

        } else {
            collide = false;
        }

        return collide;
    }

    private boolean collideWithDoors(Point updatedPos) {
        boolean collide = false;

        for (Door door : myCurrentRoom.getMyDoors()) {
            if (door.collideWith(updatedPos)) {
                collide = true;
//                System.out.println("Player is currently colliding with the " + door.getMyDirection() + " door");
            }
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

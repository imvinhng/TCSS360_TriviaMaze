package com.example.tcss360_triviamaze.view;

import com.example.tcss360_triviamaze.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.Optional;


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
    private Player endPlayer;
    private Room myCurrentRoom, myStartRoom, myEndRoom;
    private Alert myAlert = new Alert(Alert.AlertType.NONE);
    private Image smileyImg, myPlayerImg, endPlayerImg;
    private Question myQuestion;
    private ButtonType option1, option2, option3, option4;
    private ButtonType buttonTypeCancel;


    public void start(Stage stage) {
        stage.setTitle("TriviaMazeUltimate");
        //background size
        Canvas canvas = new Canvas(GAME_SIZE.width, GAME_SIZE.height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        BorderPane myPane = new BorderPane();

        myPane.setCenter(canvas);

        // set up the img directories
        setImages();


        // make sure to call this before run()
        populateRoomArray(gc);
        populatePlayer(gc);

        //JavaFX Timeline = free form animation defined by KeyFrames and their duration
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        //number of cycles in animation INDEFINITE = repeat indefinitely
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse control (move and click)
        /* canvas.setOnMouseMoved(e -> {
            playerOneXPos  = e.getX();
            playerOneYPos = e.getY();
        });

        canvas.setOnMouseClicked(e ->  gameStarted = true); */

        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save Game");
        MenuItem openMenuItem = new MenuItem("Load Game");
        MenuItem exitMenuItem = new MenuItem("Exit");
        menuFile.getItems().addAll(saveMenuItem, openMenuItem, exitMenuItem);


        // --- Menu View
        Menu menuHelp = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        MenuItem instructionMenuItem = new MenuItem("Game Play Instructions");
        MenuItem cheatsMenuItem = new MenuItem("Cheats");
        menuHelp.getItems().addAll(aboutMenuItem, instructionMenuItem, cheatsMenuItem);

        menuBar.getMenus().addAll(menuFile, menuHelp);


        myPane.setTop(menuBar);

        Scene basicScene = new Scene(myPane);

        stage.getIcons().add(smileyImg);
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
            int playerVelocity = myPlayer.getVelocity();

            if ((e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) && (playerY - playerVelocity) >= 0) {

                try {
                    if (!collision("UP")) {
                        myPlayer.setPlayerY(playerY - playerVelocity);


                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
                if ((e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) && (playerY + playerVelocity) <= GAME_SIZE.height) {

                    try {
                        if (!collision("DOWN")) {
                            myPlayer.setPlayerY(playerY + playerVelocity);
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if ((e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) && (playerX - playerVelocity) >= 0) {
                    // obstacle collision

                    try {
                        if (!collision("LEFT")) {
                            myPlayer.setPlayerX(playerX - playerVelocity);
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                if ((e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) && (playerX + playerVelocity) <= GAME_SIZE.width) {

                    try {
                        if (!collision("RIGHT")) {
                            myPlayer.setPlayerX(playerX + playerVelocity);
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }

                }

        });

    }

    private void setImages() {
        File file = new File("C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\images\\pizza_steve_cheers.png");
        myPlayerImg = new Image(file.toURI().toString());

        file = new File("C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\images\\cherry.png");
        endPlayerImg = new Image(file.toURI().toString());

        file = new File("C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\main\\java\\com\\example\\tcss360_triviamaze\\smiley.jpg");
        smileyImg = new Image(file.toURI().toString());


    }

    private boolean collision(String direction) throws ClassNotFoundException {
        boolean collide = false;
        boolean currPath = false;
        boolean nextPath = false;
        boolean PATH_PERMISSION_WEST = myCurrentRoom.getWestDoor().getMyPathPermission();
        boolean PATH_PERMISSION_EAST = myCurrentRoom.getEastDoor().getMyPathPermission();
        boolean PATH_PERMISSION_NORTH = myCurrentRoom.getNorthDoor().getMyPathPermission();
        boolean PATH_PERMISSION_SOUTH = myCurrentRoom.getSouthDoor().getMyPathPermission();
        Point updatedPos;

        // this prohibits the player from going through walls
        currPath = collideWithDoors(myPlayer.getPos());


        if (direction.equalsIgnoreCase("UP")) {

            updatedPos = new Point(myPlayer.getPlayerX(), myPlayer.getPlayerY() - myPlayer.getVelocity());

            nextPath = collideWithDoors(updatedPos);


            if (!currPath && !nextPath) {
                collide = true;
                System.out.println("Player cannot pass through the wall");
            } else if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_NORTH)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_NORTH) && currPath && PATH_PERMISSION_NORTH) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_NORTH) && (!PATH_PERMISSION_NORTH || !currPath)) {
                collide = true;

                myQuestion = new Question("short-answer");

                // set up the questions and check for answers
                myAlert = new Alert(Alert.AlertType.CONFIRMATION);
                myAlert.setTitle("Multiple Choices");
                myAlert.setHeaderText(myQuestion.getUserQuestion());
                myAlert.setContentText("Choose your option.");

                option1 = new ButtonType(myQuestion.getUserOption1());
                option2 = new ButtonType(myQuestion.getUserOption2());
                option3 = new ButtonType(myQuestion.getUserOption3());
                option4 = new ButtonType(myQuestion.getUserOption4());


                buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                myAlert.getButtonTypes().setAll(option1, option2, option3, option4, buttonTypeCancel);

                boolean answerCorrect;
                Optional<ButtonType> result = myAlert.showAndWait();
                if (result.get().getText() == myQuestion.getUserAnswer()){
                    // ... user chose "Yes"
                    answerCorrect = true;
                    myAlert = new Alert(Alert.AlertType.INFORMATION);
                    myAlert.setContentText("You answered correctly! Moving on to the next room!");
                    myAlert.show();
                } else {
                    // ... user chose CANCEL or closed the dialog
                    answerCorrect = false;
                    myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setContentText("You answered incorrectly! Door remain locked!");
                    myAlert.show();
                }

                if (answerCorrect) {
                    myCurrentRoom.getNorthDoor().unlockDoor();
                }


            }


        } else if (direction.equalsIgnoreCase("DOWN")) {

            updatedPos = new Point(myPlayer.getPlayerX(), myPlayer.getPlayerY() + myPlayer.getVelocity());

            nextPath = collideWithDoors(updatedPos);
            if (!currPath && !nextPath) {
                collide = true;
                System.out.println("Player cannot pass through the wall");
            } else if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_SOUTH)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_SOUTH) && nextPath && PATH_PERMISSION_SOUTH) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_SOUTH) && (!PATH_PERMISSION_SOUTH || !nextPath)) {
                collide = true;

                myQuestion = new Question("multiple_choice");

                // set up the questions and check for answers
                myAlert = new Alert(Alert.AlertType.CONFIRMATION);
                myAlert.setTitle("Multiple Choices");
                myAlert.setHeaderText(myQuestion.getUserQuestion());
                myAlert.setContentText("Choose your option.");

                option1 = new ButtonType(myQuestion.getUserOption1());
                option2 = new ButtonType(myQuestion.getUserOption2());
                option3 = new ButtonType(myQuestion.getUserOption3());
                option4 = new ButtonType(myQuestion.getUserOption4());


                buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                myAlert.getButtonTypes().setAll(option1, option2, option3, option4, buttonTypeCancel);


                Optional<ButtonType> result = myAlert.showAndWait();
                if (result.get().getText() == myQuestion.getUserAnswer()){
                    // ... user chose "Yes"
                    myAlert = new Alert(Alert.AlertType.INFORMATION);
                    myAlert.setContentText("You answered correctly! Moving on to the next room!");
                    myAlert.show();

                    myCurrentRoom.getSouthDoor().unlockDoor();
                } else {
                    // ... user chose CANCEL or closed the dialog

                    myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setContentText("You answered incorrectly! Door remain locked!");
                    myAlert.show();
                }


            }


        } else if (direction.equalsIgnoreCase("LEFT")) {

            updatedPos = new Point(myPlayer.getPlayerX() - myPlayer.getVelocity(), myPlayer.getPlayerY());

            nextPath = collideWithDoors(updatedPos);
            if (!currPath && !nextPath) {
                collide = true;
                System.out.println("Player cannot pass through the wall");
            } else if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_WEST)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_WEST) && nextPath && PATH_PERMISSION_WEST) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_WEST) && (!PATH_PERMISSION_WEST || !nextPath)) {
                collide = true;

                myQuestion = new Question("multiple_choice");

                // set up the questions and check for answers
                myAlert = new Alert(Alert.AlertType.CONFIRMATION);
                myAlert.setTitle("Multiple Choices");
                myAlert.setHeaderText(myQuestion.getUserQuestion());
                myAlert.setContentText("Choose your option.");

                option1 = new ButtonType(myQuestion.getUserOption1());
                option2 = new ButtonType(myQuestion.getUserOption2());
                option3 = new ButtonType(myQuestion.getUserOption3());
                option4 = new ButtonType(myQuestion.getUserOption4());


                buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                myAlert.getButtonTypes().setAll(option1, option2, option3, option4, buttonTypeCancel);


                Optional<ButtonType> result = myAlert.showAndWait();
                if (result.get().getText() == myQuestion.getUserAnswer()){
                    // ... user chose "Yes"
                    myAlert = new Alert(Alert.AlertType.INFORMATION);
                    myAlert.setContentText("You answered correctly! Moving on to the next room!");
                    myAlert.show();

                    myCurrentRoom.getWestDoor().unlockDoor();
                } else {
                    // ... user chose CANCEL or closed the dialog

                    myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setContentText("You answered incorrectly! Door remain locked!");
                    myAlert.show();
                }



            }

        } else if (direction.equalsIgnoreCase("RIGHT")) {

            updatedPos = new Point(myPlayer.getPlayerX() + myPlayer.getVelocity(), myPlayer.getPlayerY());

            nextPath = collideWithDoors(updatedPos);

            if (!currPath && !nextPath) {
                collide = true;
                System.out.println("Player cannot pass through the wall");
            } else if (!updateCurrentRoom(updatedPos, PATH_PERMISSION_EAST)) {
                collide = false;
//                System.out.println("Player is moving within the room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_EAST) && nextPath && PATH_PERMISSION_EAST) {
                collide = false;
                System.out.println("Player is crossing a door into the next room");
            } else if (updateCurrentRoom(updatedPos, PATH_PERMISSION_EAST) && (!PATH_PERMISSION_EAST || !nextPath)) {
                collide = true;

                myQuestion = new Question("multiple_choice");

                // set up the questions and check for answers
                myAlert = new Alert(Alert.AlertType.CONFIRMATION);
                myAlert.setTitle("Multiple Choices");
                myAlert.setHeaderText(myQuestion.getUserQuestion());
                myAlert.setContentText("Choose your option.");

                option1 = new ButtonType(myQuestion.getUserOption1());
                option2 = new ButtonType(myQuestion.getUserOption2());
                option3 = new ButtonType(myQuestion.getUserOption3());
                option4 = new ButtonType(myQuestion.getUserOption4());


                buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                myAlert.getButtonTypes().setAll(option1, option2, option3, option4, buttonTypeCancel);


                Optional<ButtonType> result = myAlert.showAndWait();
                if (result.get().getText() == myQuestion.getUserAnswer()){
                    // ... user chose "Yes"
                    myAlert = new Alert(Alert.AlertType.INFORMATION);
                    myAlert.setContentText("You answered correctly! Moving on to the next room!");
                    myAlert.show();

                    myCurrentRoom.getEastDoor().unlockDoor();
                } else {
                    // ... user chose CANCEL or closed the dialog

                    myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setContentText("You answered incorrectly! Door remain locked!");
                    myAlert.show();
                }


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

            myPlayer.drawPlayerUsingImage();
            endPlayer.drawPlayerUsingImage();
//            drawScore(gc);
//            drawObstacle(gc, "L");
//            drawObstacle(gc, "ROOM");
            drawRooms();

//            System.out.println("Current player position: " + myPlayer.getPos());

            // test for end state
            if (endPlayer.getPos().equals(myPlayer.getPos())) {
                endReach = true;
//                drawEndingScreen(gc, true);
                System.out.println("Ending screen was drawn");
//                Platform.exit();
            }




        } else if (inGame && endReach) {
            gc.setFill(Color.BLUEVIOLET);
            gc.fillRect(0, 0, GAME_SIZE.width, GAME_SIZE.height);
            gc.setFont (new Font ("TimesRoman", 50));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFill(Color.WHITE);
            gc.fillText("Congrats! You won!", GAME_SIZE.width / 2, GAME_SIZE.height / 2);

            for (Room r : myRooms) {
                for (Door d : r.getMyDoors()) {
                    d.unlockDoor();
                }
            }
            myPlayer.drawPlayerUsingImage();


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
//                System.out.println("Player is trying to move from " + myCurrentRoom.roomID + " to " + r.roomID);
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
            System.out.println("Congrats");
//            gc.setFill(Color.WHITE);
//            gc.fillText("Maze solved. Congratulations!", GAME_SIZE.width/2, GAME_SIZE.height/2);
        } else {
            System.out.println("You lost!");
//            gc.setFill(Color.WHITE);
//            gc.fillText("Maze locked. Try again?", GAME_SIZE.width/2, GAME_SIZE.height/2);
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
        myPlayer = new Player(gc, myPlayerImg, 0, 0);
        endPlayer = new Player(gc, endPlayerImg, GAME_SIZE.width - 100, GAME_SIZE.height-100);

    }



}

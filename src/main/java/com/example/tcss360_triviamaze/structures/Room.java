package com.example.tcss360_triviamaze.structures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Room extends ShapeSuper {
    GraphicsContext myGraphicContext;
    Color myColor = Color.BEIGE;
    public int roomID;
    boolean isLocked;
    boolean isActive;
    Door d1, d2, d3;
    Question q;
//    private int myRoomWidth = 150;
//    private int myRoomHeight = 150;
    private Dimension ROOM_SIZE = new Dimension(150, 150);
    private Point roomLocation = new Point(0, 0);


    public Room(GraphicsContext gc, Color color, int theStartX, int theStartY) {
        super(gc);
        myColor = color;
        roomLocation.x = theStartX;
        roomLocation.y = theStartY;
        myGraphicContext = gc;

    }

    public void draw() {
//        myGraphicContext.setFill(myColor);
        myGraphicContext.setStroke(myColor);
        myGraphicContext.strokeRect(roomLocation.x, roomLocation.y, ROOM_SIZE.width,ROOM_SIZE.height);
//        myGraphicContext.strokeRect(startX, startY, myRoomWidth, myRoomHeight);

    }

    public Point getRoomLocation() {
        return roomLocation;
    }

    public void getInfo() {
        System.out.println("Room. width: " + ROOM_SIZE.width + ",height: " + ROOM_SIZE.height
                + " X: " + roomLocation.x + " Y: " + roomLocation.y );
    }

    public boolean containsPlayer(Player player) {
        int x_start = roomLocation.x;;
        int y_start = roomLocation.y;;
        int x_end = roomLocation.x + ROOM_SIZE.width;
        int y_end = roomLocation.y + ROOM_SIZE.height;
        int playerXPos = player.getPlayerX();
        int playerYPos = player.getPlayerY();
        int playerHeight = player.getPlayerSize().height;
        int playerWidth = player.getPlayerSize().width;
        boolean contains = false;

        if ((playerXPos + playerWidth/2) >= x_start && (playerXPos - playerWidth/2) <= x_end
            && (playerYPos + playerHeight/2)>= y_start && (playerYPos - playerHeight/2) <= y_end ) {
            contains = true;
        }

        return contains;
    }

    public boolean containsPlayer(Point thePos) {
        int x_start = roomLocation.x;;
        int y_start = roomLocation.y;;
        int x_end = roomLocation.x + ROOM_SIZE.width;
        int y_end = roomLocation.y + ROOM_SIZE.height;
        int playerXPos = thePos.x;
        int playerYPos = thePos.y;
        int playerHeight = new Player().getPlayerSize().height;
        int playerWidth = new Player().getPlayerSize().width;
        boolean contains = false;

        if ((playerXPos + playerWidth/2) >= x_start && (playerXPos + playerWidth/2) <= x_end
                && (playerYPos + playerHeight/2)>= y_start && (playerYPos + playerHeight/2) <= y_end ) {
            contains = true;
        }

        return contains;
    }

}

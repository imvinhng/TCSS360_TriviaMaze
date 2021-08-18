package com.example.tcss360_triviamaze.structures;

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Objects;

public class Door {
    Room myRoom;
    boolean open;
    // size for the door placeholder, will have to implement sprite for the door
    private final Dimension DOOR_SIZE_VERTICAL = new Dimension(5, 50);
    private final Dimension DOOR_SIZE_HORIZONTAL = new Dimension(50, 5);
    private Point doorLocation;
    private boolean myPathPermission = false;
    String myDirection;

    public Door(Room theRoom, String direction) {

        myRoom = theRoom;
        myDirection = direction;

        if (myDirection.equalsIgnoreCase("WEST")) {
            doorLocation = new Point(myRoom.getRoomLocation().x,
                    myRoom.getRoomLocation().y + DOOR_SIZE_VERTICAL.height);
        } else if (myDirection.equalsIgnoreCase("EAST")) {
            doorLocation = new Point(myRoom.getRoomLocation().x + myRoom.ROOM_SIZE.width - DOOR_SIZE_VERTICAL.width,
                    myRoom.getRoomLocation().y + DOOR_SIZE_VERTICAL.height);
        } else if (myDirection.equalsIgnoreCase("SOUTH")) {
            doorLocation = new Point(myRoom.getRoomLocation().x + DOOR_SIZE_HORIZONTAL.width,
                    myRoom.getRoomLocation().y + myRoom.ROOM_SIZE.height - DOOR_SIZE_HORIZONTAL.height);
        } else if (myDirection.equalsIgnoreCase("NORTH")) {
            doorLocation = new Point(myRoom.getRoomLocation().x + DOOR_SIZE_HORIZONTAL.width,
                    myRoom.getRoomLocation().y);
        }

    }

    public void draw() {
        myRoom.myGraphicContext.setStroke(Color.PAPAYAWHIP);

        if (myDirection.equalsIgnoreCase("WEST") || myDirection.equalsIgnoreCase("EAST")) {

            myRoom.myGraphicContext.strokeRect(
                    doorLocation.x, doorLocation.y,
                    DOOR_SIZE_VERTICAL.width, DOOR_SIZE_VERTICAL.height);


        } else if (myDirection.equalsIgnoreCase("SOUTH") || myDirection.equalsIgnoreCase("NORTH")) {

            myRoom.myGraphicContext.strokeRect(
                    doorLocation.x, doorLocation.y,
                    DOOR_SIZE_HORIZONTAL.width, DOOR_SIZE_HORIZONTAL.height);

        }
    }

    public boolean collideWith(Player thePlayer) {
        boolean collide = false;

        // collide with west or north
        if (myDirection.equalsIgnoreCase("WEST") || myDirection.equalsIgnoreCase("NORTH") ) {
            if (thePlayer.getPlayerX() == doorLocation.x & thePlayer.getPlayerY() == doorLocation.y) {
                collide = true;
            }
        }

        // collide with east or south
        if (myDirection.equalsIgnoreCase("EAST")) {
            if (thePlayer.getPlayerX() == (doorLocation.x + DOOR_SIZE_VERTICAL.width - new Player().PLAYER_SIZE.width) &&
                    thePlayer.getPlayerY() == doorLocation.y) {
                collide = true;
            }
        }

        if (myDirection.equalsIgnoreCase("SOUTH")) {
            if (thePlayer.getPlayerX() == doorLocation.x &&
                    thePlayer.getPlayerY() == (doorLocation.y + DOOR_SIZE_HORIZONTAL.height - new Player().PLAYER_SIZE.height)) {
                collide = true;
            }
        }

        return collide;
    }

    public boolean collideWith(Point thePos) {
        boolean collide = false;

        // collide with west or north
        if (myDirection.equalsIgnoreCase("WEST") || myDirection.equalsIgnoreCase("NORTH") ) {
            if (thePos.x == doorLocation.x && thePos.y == doorLocation.y) {
                collide = true;
            }
        }

        // collide with east or south
        if (myDirection.equalsIgnoreCase("EAST")) {
            if (thePos.x == (doorLocation.x + DOOR_SIZE_VERTICAL.width - new Player().PLAYER_SIZE.width) &&
                    thePos.y == doorLocation.y) {
                collide = true;
            }
        }

        if (myDirection.equalsIgnoreCase("SOUTH")) {
            if (thePos.x == doorLocation.x &&
                    thePos.y == (doorLocation.y + DOOR_SIZE_HORIZONTAL.height - new Player().PLAYER_SIZE.height)) {
                collide = true;
            }
        }

        return collide;
    }

    public Point getDoorLocation() {
        return Objects.requireNonNull(doorLocation);
    }

    public String getMyDirection() {
        return myDirection;
    }

    public boolean getMyPathPermission() {
        return myPathPermission;
    }

    public void unlockDoor() {
        myPathPermission = true;
    }



}

package com.example.tcss360_triviamaze.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;

public class Player {
    private Color myColor;
    private Point playerPos = new Point (0,0);
    public final Dimension PLAYER_SIZE = new Dimension(50, 50);
    private final int playerVelocity = 50;
    private GraphicsContext myGraphicContext;
    private Image myImage;

    public Player() {
    }

    public Player(GraphicsContext gc, Color color, int theStartX, int theStartY) {

        myColor = color;
        playerPos.x = theStartX;
        playerPos.y = theStartY;
        myGraphicContext = gc;

    }

    public Player(GraphicsContext gc, Image image, int theStartX, int theStartY) {

        myImage = image;
        playerPos.x = theStartX;
        playerPos.y = theStartY;
        myGraphicContext = gc;


    }

    public Dimension getPlayerSize() {
        return PLAYER_SIZE;
    }

    public int getPlayerX() {
        return playerPos.x;
    }

    public int getPlayerY() {
        return playerPos.y;
    }

    public Point getPos() {
        return playerPos;
    }

    public int getVelocity() {
        return playerVelocity;
    }

    public void setPlayerX(int theNewX) {
        playerPos.x = theNewX;
    }

    public void setPlayerY(int theNewY) {
        playerPos.y = theNewY;
    }

    /**
     * Utility method to draw player
     * @param
     */
    public void drawPlayerUsingImage() {
        myGraphicContext.drawImage(myImage, playerPos.x, playerPos.y, PLAYER_SIZE.width, PLAYER_SIZE.height);
    }

    public void drawPlayerUsingImage(int x, int y) {
        myGraphicContext.drawImage(myImage, x, y, PLAYER_SIZE.width, PLAYER_SIZE.height);
    }

    public void drawPlayerByDefault() {
        myGraphicContext.setFill(Color.WHITE);
        myGraphicContext.fillRect(playerPos.x, playerPos.y, PLAYER_SIZE.width, PLAYER_SIZE.height);
    }
}

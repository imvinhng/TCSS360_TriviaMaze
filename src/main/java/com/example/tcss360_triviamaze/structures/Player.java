package com.example.tcss360_triviamaze.structures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Player {
    private Color myColor;
    public Point playerPos = new Point (0,0);
    private Dimension PLAYER_SIZE = new Dimension(50, 50);
    private final int playerVelocity = 50;
    private GraphicsContext myGraphicContext;

    public Player() {
    }

    public Player(GraphicsContext gc, Color color, int theStartX, int theStartY) {

        myColor = color;
        playerPos.x = theStartX;
        playerPos.y = theStartY;
        myGraphicContext = gc;
    }

    public Dimension getPlayerSize() {
        return PLAYER_SIZE;
    }

    public int getXPos() {
        return playerPos.x;
    }

    public Point getPos() {
        return playerPos;
    }

    public int getVelocity() {
        return playerVelocity;
    }

    /**
     * Utility method to draw player (if needed).
     * @param
     */
    public void drawPlayer() {
        myGraphicContext.setFill(Color.WHITE);
        myGraphicContext.fillRect(playerPos.x, playerPos.y, PLAYER_SIZE.width, PLAYER_SIZE.height);
    }
}

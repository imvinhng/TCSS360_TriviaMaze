package com.example.tcss360_triviamaze.structures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Room extends ShapeSuper {
    GraphicsContext myGraphicContext;
    Color myColor = Color.BEIGE;
    private int myRoomWidth = 150;
    private int myRoomHeight = 150;
    private int startX;
    private int startY;

    public Room(GraphicsContext gc, Color color, int roomHeight, int roomWidth, int theStartX, int theStartY) {
        super(gc);
        myRoomWidth = roomWidth;
        myRoomHeight = roomHeight;
        myColor = color;
        startX = theStartX;
        startY = theStartY;
        myGraphicContext = gc;

    }

    public void draw() {
//        myGraphicContext.setFill(myColor);
        myGraphicContext.setStroke(myColor);
        myGraphicContext.strokeRect(startX, startY, myRoomWidth,myRoomHeight);
//        myGraphicContext.strokeRect(startX, startY, myRoomWidth, myRoomHeight);

    }
}

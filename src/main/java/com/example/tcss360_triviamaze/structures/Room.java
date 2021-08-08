package com.example.tcss360_triviamaze.structures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Room extends ShapeSuper {
    GraphicsContext myGraphicContext;
    Color myColor = Color.BEIGE;
    private int roomWidth;
    private int roomHeight;
    private int borderWidth;

    public Room(GraphicsContext gc, Color color) {
        super(gc);
        roomWidth = 150;
        roomHeight = 150;
        borderWidth = 10;
        myColor = color;
        myGraphicContext = gc;

    }

    public void draw() {
//        myGraphicContext.setFill(myColor);
        myGraphicContext.setStroke(myColor);
//        myGraphicContext.strokeRect(0, 0, roomWidth + borderWidth, roomHeight + borderWidth );
        myGraphicContext.strokeRect(0, 0, roomWidth, roomHeight);

    }
}

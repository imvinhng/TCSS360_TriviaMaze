package com.example.tcss360_triviamaze.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class LShape extends ShapeSuper {
    GraphicsContext myGraphicContext;
    Color myColor = Color.DARKORANGE; // default


    public LShape(GraphicsContext gc, Color color) {
        super(gc);
        myGraphicContext = Objects.requireNonNull(gc);
        myColor = Objects.requireNonNull(color);

    }

    public void draw() {
        myGraphicContext.setFill(myColor);
        myGraphicContext.fillRect(200, 200, 50, 200);
        myGraphicContext.fillRect(250, 350, 100, 50);
    }
}

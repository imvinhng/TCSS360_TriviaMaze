package com.example.tcss360_triviamaze.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface ShapeInterface {
    GraphicsContext myGraphicContext = null;
    Color myColor = null;

    public void draw();
}

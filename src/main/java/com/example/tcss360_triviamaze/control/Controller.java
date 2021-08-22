package com.example.tcss360_triviamaze.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {
    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    @FXML
    public void up(ActionEvent e) {
//        System.out.println("UP");
        myCircle.setCenterY(y-=10);
    }
    @FXML
    public void down(ActionEvent e) {
        //        System.out.println("DOWN");
        myCircle.setCenterY(y+=10);
    }
    @FXML
    public void left(ActionEvent e) {
//        System.out.println("LEFT");
        myCircle.setCenterX(x-=10);
    }
    @FXML
    public void right(ActionEvent e) {
        System.out.println("RIGHT");
        myCircle.setCenterX(x+=10);
    }
}

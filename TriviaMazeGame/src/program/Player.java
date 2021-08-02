package program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    Integer myXPosition;
    Integer myYPosition;
    Integer myAvailableHints;

    public Player (Integer theInitialX, Integer theInitialY) {
        setPlayerPosition(theInitialX, theInitialY);
        myAvailableHints = 3;
    }

    public void movePlayerNorth() {
        setPlayerPosition(myXPosition, myYPosition - 1);
    }

    public void movePlayerSouth() {
        setPlayerPosition(myXPosition, myYPosition + 1);
    }

    public void movePlayerEast() {
        setPlayerPosition(myXPosition + 1, myYPosition);
    }

    public void movePlayerWest() {
        setPlayerPosition(myXPosition - 1, myYPosition);
    }

    public void setPlayerPosition(Integer theXPosition, Integer theYPosition) {
        myXPosition = theXPosition;
        myYPosition = theYPosition;
    }

    public Integer getPlayerXPosition() {
        return myXPosition;
    }

   public Integer getPlayerYPosition() {
       return myYPosition;
   }

    public void useHint() {
        if (myAvailableHints >0) {
            myAvailableHints = myAvailableHints - 1;
        }
    }

    public Integer getAvailableHints() {
        return myAvailableHints;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            movePlayerNorth();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            movePlayerSouth();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            movePlayerWest();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movePlayerEast();
        }

        System.out.println("X pos: " + myXPosition + ", Y pos: " + myYPosition);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

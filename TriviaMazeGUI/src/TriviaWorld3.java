import javax.swing.*;
import java.awt.*;

/**
 * A tic tac toe game in disguise.
 *
 * @author vnguye3
 */
public class TriviaWorld3  extends JPanel {

    char playerMark = 'x';
    JButton[] buttons = new JButton[9];

    public TriviaWorld3() {
        setLayout(new GridLayout(3,3));
        initializeButtons();

    }

    // a method used to create 9 buttons
    // set the text, add action listeners
    // and add them to the screen
    public void initializeButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);
            buttons[i].addActionListener(e -> {
                JButton buttonClicked = (JButton) e.getSource(); //get the particular button that was clicked
                buttonClicked.setText(String.valueOf(playerMark));


                if(playerMark == 'x') {
                    playerMark = 'o';
                    buttonClicked.setBackground(Color.CYAN);
                }
                else {
                    playerMark ='x';
                    buttonClicked.setBackground(Color.ORANGE);
                }
                displayVictor();


            });

            add(buttons[i]); //adds this button to JPanel
        }
    }


    // display the victorious player

    public void displayVictor() {

        if(checkForWinner()) {

            // reverse the player marks
            // because after we put x and we win, the game changes it to o
            // but x is the winner
            if(playerMark == 'x') playerMark = 'o';
            else playerMark ='x';

            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. "+ playerMark + " wins. Would you like to play again?","Game over.",
                    JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        }

        else if(checkDraw()) {
            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?","Game over.", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION)  resetTheButtons();
            else System.exit(0);
        }
    }



    // method used to reset the buttons
    // so you can play again
    private void resetTheButtons() {
        playerMark = 'x';
        for(int i =0;i<9;i++) {

            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);

        }
    }

    // checks for draw

    public boolean checkDraw() {
        boolean full = true;
        for(int i = 0 ; i<9;i++) {
            if(buttons[i].getText().charAt(0) == '-') {
                full = false;
            }
        }
        return full;
    }

    // checks for a winner
    public boolean checkForWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    // checks rows for a win
    public boolean checkRows() {
        int i = 0;
        for(int j = 0;j<3;j++) {
            if( buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText())
                    && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
            i = i+3;

        }
        return false;
    }


    // checks columns for a win
    public boolean checkColumns() {

        int i = 0;
        for(int j = 0;j<3;j++) {
            if( buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())
                    && buttons[i].getText().charAt(0) != '-')
            {
                return true;
            }
            i++;
        }
        return false;
    }

    // checks diagonals for a win
    public boolean checkDiagonals() {
        if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
                && buttons[0].getText().charAt(0) !='-')
            return true;
        else return buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
                && buttons[2].getText().charAt(0) != '-';
    }



    public static void main(String[] args) {
        JFrame window = new JFrame("Tic Tac Toe by Java Coding Community");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TriviaWorld3()); // adds the data
        window.setBounds(500,500,500,500); // area
        window.setVisible(true); // show the window
        window.setLocationRelativeTo(null); // center the window
    }

}

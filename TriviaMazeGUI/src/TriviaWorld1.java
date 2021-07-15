import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriviaWorld1 {
        /**
         * Frame Size.
         */
        final int FRAME_SIZE = 400;

        /**
         * set up.
         */
        private void start() {

            final JFrame frame = new JFrame();


            // Label 1
            final JLabel label1 = new JLabel("First Label");
            label1.setBounds(60, 50, 280, 30);
            label1.setText("Select/Enter the file containing unsorted integers:");
            label1.setForeground(Color.BLUE);
            frame.add(label1);

            // Label 2
            final JLabel folderLabel = new JLabel("Icon");
            folderLabel.setBounds(320, 100, 30, 30);
            folderLabel.setBackground(Color.black);
            frame.add(folderLabel);

            // TextField 1
            final JTextField textfield1 = new JTextField("");
            textfield1.setBounds(100, 100, 200, 30);
            frame.add(textfield1);

            // Label 2
            final JLabel label2 = new JLabel("Binary Representation");
            label2.setBounds(150, 250, 200, 30);
            label2.setText("");
            label2.setForeground(Color.BLUE);
            frame.add(label2);

            // Button1
            final JButton button1 = new JButton("CONVERT");
            button1.setBounds(140, 170, 100, 50);
            button1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    checkAndMessage(textfield1, label2, frame);


                }

            });
            frame.add(button1);



            // make sure you put setLocationRelativeTo last to center JFrame
            frame.setSize(FRAME_SIZE, FRAME_SIZE);
            frame.setTitle("Decimal To Binary");
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setLocationRelativeTo(null);
        }

        /**
         * Check if the input is valid, and respond accordingly.
         *
         * @param theText
         * @param theLabel
         * @param theFrame
         */
        private static void checkAndMessage (final JTextField theText, final JLabel theLabel, final JFrame theFrame) {
            final String s = theText.getText();
            try {
                // check if the input is an integer
                final int value = Integer.parseInt(s);

                try {
                    // check if the input is an integer <= 0
                    final String binary = "I am here";

                    // set the label text to binary result
                    theLabel.setText(binary);
                } catch (final IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(theFrame,
                            "The value must be positive. Please try again.");
                }
            } catch (final IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(theFrame,
                        "Not an integer; try again.",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }

        /**
         * Main method.
         *
         * @param theArgs arguments.
         */
        public static void main(final String[] theArgs) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    new TriviaWorld1().start();

                }
            });
        }

}

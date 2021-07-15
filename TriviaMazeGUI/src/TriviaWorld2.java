import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Button with number clicks label.
 *
 * @author vnguye3
 */
public class TriviaWorld2 implements ActionListener{
    int count = 0;
    private JFrame frame;
    private JButton button;
    private JLabel label;
    private JPanel panel;

    public TriviaWorld2() {

        

        frame = new JFrame();

        button = new JButton("Click me");
        button.addActionListener((ActionListener) this);

        label = new JLabel("Number of clicks: 0");



        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(20, 20));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Trivia World 2");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] theArgs){
        new TriviaWorld2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Numbers of clicks: " + count);
    }
}

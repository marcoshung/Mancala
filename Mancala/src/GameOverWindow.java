import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameOverWindow class to represent when the game is over
 * Based on code from Ilan Granot HW4 Error window
 */
public class GameOverWindow extends JFrame {
    /**
     * Constructor
     */
    public GameOverWindow() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Game Over");
        setLocation(300,300);
        setLayout(new GridLayout(2,1));
        JLabel label = new JLabel("   Game Over   ",JLabel.CENTER);
        add(label);
        JButton okButton = new JButton("Okay");
        okButton.setPreferredSize(new Dimension(35,25));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(okButton);
        pack();
        setVisible(true);
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * Mancala program by the IMaX team, CS151 Team Project
 * IMaX team is: Ilan Granot, Marcos Hung, and Xufeng Xu
 */


public class MancalaTester {
    public static void main(String[] args) {

        BoardModel boardModel = new BoardModel();

        // Main menu
        JFrame mainWindow = new JFrame("Main Window");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(640, 480));

        JLabel greetingsLabel = new JLabel("Welcome to IMaX Mancala");
        greetingsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        greetingsLabel.setBounds(20,20,620,100);
        mainPanel.add(greetingsLabel);

        JButton undoButton = new JButton("UNDO");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardModel.undo();
            }
        });
        undoButton.setBounds(300,300,60,20);
        mainPanel.add(undoButton);

        mainWindow.add(mainPanel);
        mainWindow.pack();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);





        // Game window
        BoardView boardView = new BoardView(boardModel);

        boardView.setStyle(new StandardStyle(600, 200));

    }
}

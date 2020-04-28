import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * Mancala CS151 Team Project by the IMaX Team,
 * The IMaX team is:
 * Ilan Granot, Marcos Hung, and Xufeng Xu
 * Class Main program entrance
 * Creates MainWindow with control and constructs
 * the Mancala Board Window
 */

public class MancalaTester {
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;
    public static final int MANCALA_WIDTH = 600;
    public static final int MANCALA_HEIGHT = 150;
    public static final int WINDOW_GAP = 100;

    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Main Window");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        mainWindow.setLocation(WINDOW_GAP,WINDOW_GAP);
        mainWindow.setResizable(false);

        JLabel greetingsLabel = new JLabel("Welcome to IMaX Mancala");
        greetingsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        greetingsLabel.setBounds(SCREEN_WIDTH / 8 , SCREEN_HEIGHT/8, 620, 100);
        mainPanel.add(greetingsLabel);

        JPanel init_set = new JPanel(new FlowLayout());
        JLabel askUser1 = new JLabel("Choose Initial Number of Stones");
        JLabel askUser2 = new JLabel("Choose Game Style");

        init_set.setBounds(150, 200, 300, 200);
        init_set.add(askUser1);

        String selecList[] = { "3", "4" };
        JComboBox comboBoxInitialStones = new JComboBox(selecList);
        init_set.add(comboBoxInitialStones);
        init_set.add(askUser2);

        String styleList[] = { "Standard Style", "Space Style" };
        JComboBox comboBoxStyle = new JComboBox(styleList);
        init_set.add(comboBoxStyle);

        JButton enterGame = new JButton("ENTER");
        enterGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                BoardModel boardModel = new BoardModel(comboBoxInitialStones.getSelectedIndex() == 0 ? 3 : 4);
                BoardView boardView = new BoardView(boardModel);

                BoardStyle boardStyle = (comboBoxStyle.getSelectedIndex() == 0 ?
                        new StandardStyle(MANCALA_WIDTH, MANCALA_HEIGHT) :
                        new SpaceStyle(MANCALA_WIDTH, MANCALA_HEIGHT));
                boardView.setStyle(boardStyle);
            }
        });

        init_set.add(enterGame);
        mainPanel.add(init_set);
        mainWindow.add(mainPanel);
        mainWindow.pack();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }
}
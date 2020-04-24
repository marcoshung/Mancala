import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * Mancala program by the IMaX team, CS151 Team Project IMaX team is: Ilan
 * Granot, Marcos Hung, and Xufeng Xu
 * Version 1.21 edited by Xuefeng 
 * Features added: Greeting Page with initial settings. 
 *                  Initial Stones set and GameStyle Set.
 *                  Close the greeting page afer press ENTER button.
 */

public class MancalaTester {
    private static int initStone = 3; // default value
    private static String gameStyle;


    public static void main(String[] args) {

        // Main menu
        JFrame mainWindow = new JFrame("Main Window");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(640, 480));

        JLabel greetingsLabel = new JLabel("Welcome to IMaX Mancala");
        greetingsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        greetingsLabel.setBounds(20, 20, 620, 100);
        mainPanel.add(greetingsLabel);

        JPanel init_set = new JPanel(new FlowLayout());
        JLabel askUser1 = new JLabel("Chooes initial Stones");
        JLabel askUser2 = new JLabel("Choose Game Style");

        init_set.setBounds(170, 200, 300, 200);
        init_set.add(askUser1);

        String selecList[] = { "3", "4" };
        JComboBox selection = new JComboBox(selecList);
        selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == selection) {
                    initStone = Integer.valueOf((String) selection.getSelectedItem());
                    System.out.println("Init Stone is set as: " + initStone);
                }
            }
        });
        init_set.add(selection);
        init_set.add(askUser2);

        String styleList[] = { "Standard Style", "Spacing Style" };
        JComboBox styleSelec = new JComboBox(styleList);
        styleSelec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == styleSelec) {
                    gameStyle = (String) styleSelec.getSelectedItem();
                    System.out.println("game style now is: " + gameStyle);
                }
            }
        });
        init_set.add(styleSelec);

        JButton enterGame = new JButton("ENTER");
        enterGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                BoardModel boardModel = new BoardModel(initStone);
                BoardView boardView = new BoardView(boardModel);


                boardView.setStyle(new StandardStyle(600, 150));
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

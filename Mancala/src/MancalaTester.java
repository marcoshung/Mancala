import javax.swing.*;
import java.awt.*;

/**
 * Mancala program by the IMaX team, CS151 Team Project
 * IMaX team is: Ilan Granot, Marcos Hung, and Xufeng Xu
 */


public class MancalaTester {
    public static void main(String[] args){
        // Main menu
        JFrame mainWindow = new JFrame("Welcome to IMaX Mancala");
        JPanel mainMenu = new JPanel();
        mainMenu.setPreferredSize(new Dimension(640,480));
        mainWindow.add(mainMenu);
        mainWindow.pack();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);


        // Game window
        JFrame gameWindow = new JFrame("Game Window");
        BoardView boardView = new BoardView(new StandardStyle(640,380));
        gameWindow.add(boardView);

        gameWindow.setVisible(true);
        gameWindow.pack();
    }
}

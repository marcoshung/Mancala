import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * An interface to represent a custom style Mancala board
 * Extends layout manager to create the layout of the board
 */

public interface BoardStyle extends LayoutManager{
   public JLabel drawLabel(String string);
   public JPanel drawPits(Graphics g, ArrayList<Integer> pits, MouseListener listener);
   public JPanel drawMancalas(Graphics g, ArrayList<Integer> mancalas);

}
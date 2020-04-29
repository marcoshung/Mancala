import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * An interface to represent a custom style Mancala board
 * Extends layout manager to create the layout of the board
 */

public interface BoardStyle {
   /**
    * Sets the graphic style to the board
    * @param container the Container which holds the mancala board
    * @param boardView the view of the game
    */
   public void layoutStyle(Container container, BoardView boardView);

   /**
    * Helper function to control the styling to mark the current player
    * @param boardView the view of the game
    */
   public void markCurrentPlayer(BoardView boardView);

   /**
    * gets the width of the board
    * @return the width of the board
    */
   public int getWidth();

   /**
    * gets the height of the board
    * @return the height of the board
    */
   public int getHeight();
}
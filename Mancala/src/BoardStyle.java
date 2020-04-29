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
    * @param boardModel the model of the game
    * @param mouseListener a mouse listener for clicking the pits
    */
   public void layoutStyle(Container container, BoardModel boardModel, MouseListener mouseListener);

   /**
    * Helper function to control the styling to mark the current player
    */
   public void markCurrentPlayer();

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
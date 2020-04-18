import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * An interface to represent a custom style Mancala board
 * Extends layout manager to create the layout of the board
 */

public interface BoardStyle {
   public void layoutStyle(Container container, BoardModel boardModel, MouseListener mouseListener);
   public void markCurrentPlayer();
   public int getWidth();
   public int getHeight();
}
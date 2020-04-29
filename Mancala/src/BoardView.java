import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Class to represent a Mancala Board in a window and to implement a graphic style using BoardStyle
 */
public class BoardView extends JFrame implements ChangeListener{
    public static final boolean RESIZABLE = false;
    private static final int UNDO_BUTTON_SPACING = 100;
    private static final int SCREEN_LOCATION = 200;
    public static final int UNDO_PROPORTION = 300;
    private ArrayList<Integer> pits;
    private int width;
    private int height;
    private JPanel pitsPanel, leftMancalaPanel,rightMancalaPanel;
    private JLabel labelPlayerA, labelPlayerB;
    private BoardModel boardModel;
    private BoardStyle boardStyle;
    private JPanel mainPanel;

    /**
     * Constructor
     * @param boardModel a reference to the model
     */
    public BoardView(BoardModel boardModel){
        this.boardModel = boardModel;
        setLayout(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
        boardModel.attach(this);
        setTitle("Mancala Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(SCREEN_LOCATION,SCREEN_LOCATION);
        setResizable(RESIZABLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(mainPanel, BorderLayout.NORTH);

        pitsPanel = new JPanel();
        leftMancalaPanel = new JPanel();
        rightMancalaPanel = new JPanel();
        labelPlayerA = new JLabel("Player A ->", JLabel.CENTER);
        labelPlayerB = new JLabel("<- Player B", JLabel.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton undoButton = new JButton("UNDO");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardModel.undo();
                repaint();
                boardStyle.markCurrentPlayer();
            }
        });

        undoButton.setBounds(UNDO_PROPORTION, UNDO_PROPORTION, UNDO_PROPORTION/5, UNDO_PROPORTION/15);
        bottomPanel.add(undoButton);
        add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(labelPlayerB, BorderLayout.NORTH);
        mainPanel.add(leftMancalaPanel, BorderLayout.WEST);
        mainPanel.add(pitsPanel, BorderLayout.CENTER);
        mainPanel.add(rightMancalaPanel, BorderLayout.EAST);
        mainPanel.add(labelPlayerA, BorderLayout.SOUTH);

    }

    /**
     * To set the style of the graphic to use
     * @param boardStyle the style to use
     */
    public void setStyle(BoardStyle boardStyle){
        this.boardStyle = boardStyle;
        this.width = boardStyle.getWidth();
        this.height = boardStyle.getHeight();
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boardModel.update(((JLabel) e.getSource()).getText());
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };

        boardStyle.layoutStyle(mainPanel, boardModel, mouseListener);

        setSize(boardStyle.getWidth(), boardStyle.getHeight() + UNDO_BUTTON_SPACING);
        setVisible(true);
    }


    /**
     * Notifies the view about a change in the model
     * @param e the event that is the source of the change
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
        boardStyle.markCurrentPlayer();
        if (boardModel.getState().getIsGameOver())
            new GameOverWindow();
    }
}
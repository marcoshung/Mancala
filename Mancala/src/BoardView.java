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

public class BoardView extends JFrame implements ChangeListener{
    public static final boolean RESIZABLE = false;
    private static final int UNDO_BUTTON_SPACING = 100;
    private static final int SCREEN_LOCATION = 200;
    private ArrayList<Integer> pits;
    private int width;
    private int height;
    private JPanel pitsPanel, leftMancalaPanel,rightMancalaPanel;
    private JLabel labelPlayerA, labelPlayerB;
    private BoardModel boardModel;
    private BoardStyle boardStyle;
    private JPanel mainPanel;

    public BoardView(BoardModel boardModel){
        this.boardModel = boardModel;
        setLayout(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
        boardModel.attach(this);
        setTitle("Mancala Board");
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

        undoButton.setBounds(300, 300, 60, 20);
        bottomPanel.add(undoButton);
        add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(labelPlayerB, BorderLayout.NORTH);
        mainPanel.add(leftMancalaPanel, BorderLayout.WEST);
        mainPanel.add(pitsPanel, BorderLayout.CENTER);
        mainPanel.add(rightMancalaPanel, BorderLayout.EAST);
        mainPanel.add(labelPlayerA, BorderLayout.SOUTH);


    public BoardView(BoardModel boardModel){
        this.boardModel = boardModel;
        boardModel.attach(this);
        setLayout(new BorderLayout());
        setTitle("Mancala Board");
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pitsPanel = new JPanel();
        leftMancalaPanel = new JPanel();
        rightMancalaPanel = new JPanel();
        labelPlayerA = new JLabel("Player A ->", JLabel.CENTER);
        labelPlayerB = new JLabel("<- Player B", JLabel.CENTER);

        add(labelPlayerB, BorderLayout.NORTH);
        add(leftMancalaPanel, BorderLayout.WEST);
        add(pitsPanel, BorderLayout.CENTER);
        add(rightMancalaPanel, BorderLayout.EAST);
        add(labelPlayerA, BorderLayout.SOUTH);
    }


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

        boardStyle.layoutStyle(getContentPane(), boardModel ,mouseListener);

        pack();
        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
        boardStyle.markCurrentPlayer();
        if (boardModel.getState().getIsGameOver())
            new GameOverWindow();
    }
}

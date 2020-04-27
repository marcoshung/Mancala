import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardView extends JFrame implements ChangeListener{
    private ArrayList<Integer> pits;
    private int width;
    private int height;
    private JPanel pitsPanel, leftMancalaPanel,rightMancalaPanel;
    private JLabel labelPlayerA, labelPlayerB;
    private BoardModel boardModel;
    private BoardStyle boardStyle;

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
    }
}

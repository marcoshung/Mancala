import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class BoardView extends JPanel implements ChangeListener{
    private BoardStyle boardStyle;
    private ArrayList<Integer> pits;



    @Override
    public Dimension getPreferredSize() {
        return boardStyle.preferredLayoutSize(this);
    }

    public BoardView(BoardStyle boardStyle){
        this.boardStyle = boardStyle;
        setLayout(boardStyle);
    }

    public void setBoardStyle(BoardStyle boardStyle){
        this.boardStyle = boardStyle;
    }

    @Override
    protected void paintComponent(Graphics g) {

        Random random = new Random();
        pits = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            pits.add(random.nextInt(7));
        }


        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Model.update()
                System.out.println("dataModel.update(gameState)"+((JLabel)e.getSource()).getText());
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };

        add(boardStyle.drawLabel("Player B"), BorderLayout.NORTH);

        add(boardStyle.drawPits(g, pits, mouseListener), BorderLayout.CENTER);

        add(boardStyle.drawMancalas(g,null), BorderLayout.WEST);

        add(boardStyle.drawMancalas(g,null), BorderLayout.EAST);

        add(boardStyle.drawLabel("Player A"), BorderLayout.SOUTH);
    }

    /**
     * Invoked when the target of the listener has changed its state.
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        //pits = model.getState.getPits();
        repaint();
    }
}

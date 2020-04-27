import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for concrete custom styling of a Mancala board
 */
public class StandardStyle implements BoardStyle {

    private final int WIDTH, HEIGHT;
    private static final int GAP = 10;
    private static final int PIT_RATIO = 10;
    private static final int PEBBLES_RATIO = 80;
    private static final int MANCALA_SPACING = 20;
    private static final Color BOARD_COLOR = Color.GRAY;
    private static final Color PITS_COLOR = Color.BLUE;
    private static final Color MANCALA_COLOR = Color.BLUE;
    private static final Color PEBBLES_COLOR = Color.YELLOW;
    private JLabel labelPlayerA, labelPlayerB;
    private BoardModel boardModel;
    private Random random;


    public StandardStyle(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        random = new Random();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void layoutStyle(Container container, BoardModel boardModel, MouseListener mouseListener) {
        this.boardModel = boardModel;
        Component[] components = container.getComponents();
        labelPlayerB = (JLabel) components[0];
        JPanel leftMancalaPanel = (JPanel) components[1];
        JPanel rightMancalaPanel = (JPanel) components[3];
        JPanel pitsPanel = (JPanel) components[2];
        labelPlayerA = (JLabel) components[4];


        labelPlayerB.add(new JLabel("<- Player B"));
        labelPlayerA.add(new JLabel("Player A ->"));
        markCurrentPlayer();
        leftMancalaPanel.add(new JLabel(new Mancala(1)));
        rightMancalaPanel.add(new JLabel(new Mancala(0)));
        pitsPanel.setLayout(new GridLayout(2,6));

        ArrayList<Integer> pits = boardModel.getState().getPits();
        for ( int i = pits.size()-1; i >= pits.size() / 2; i--){
            String text = "B" + String.valueOf((i % (pits.size()/2)) + 1);
            JLabel label = new JLabel(text,new Pit(i),JLabel.LEADING);
            label.addMouseListener(mouseListener);
            pitsPanel.add(label);
        }

        for ( int i = 0 ; i < pits.size()/2; i++){
            String text = "A" + String.valueOf((i % (pits.size()/2)) + 1);
            JLabel label = new JLabel(text,new Pit(i),JLabel.LEADING);
            label.addMouseListener(mouseListener);
            pitsPanel.add(label);
        }
    }


    public void markCurrentPlayer(){
        if (boardModel.getState().get_next_isPlayerA()){
            labelPlayerA.setForeground(Color.GREEN);
            labelPlayerB.setForeground(Color.BLACK);
        } else {
            labelPlayerA.setForeground(Color.BLACK);
            labelPlayerB.setForeground(Color.GREEN);
        }
    }


    // ICONS:
    //Implements represntation of a mancala
    private class Mancala implements Icon {
        private final int mancalaIndex;

        public Mancala(int mancalaIndex){
            this.mancalaIndex = mancalaIndex;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            ArrayList<Integer> mancalasArray = boardModel.getState().getMancala();
            Graphics2D g2 = (Graphics2D) g;
            RoundRectangle2D mancala = new RoundRectangle2D.Double(0 , 0, MANCALA_SPACING, HEIGHT - 4 * MANCALA_SPACING ,10,10);
            g2.setColor(MANCALA_COLOR);
            g2.fill(mancala);
            g2.setColor(PEBBLES_COLOR);
            for (int i = 0; i < mancalasArray.get(mancalaIndex); i++){
                g2.fill( new Ellipse2D.Double(GAP, random.nextInt(100), WIDTH / PEBBLES_RATIO, WIDTH / PEBBLES_RATIO)   );
            }
            g2.setColor(Color.RED);
            g2.drawString((mancalaIndex == 1 ? "B:" : "A:") +String.valueOf(mancalasArray.get(mancalaIndex)),2,10);
        }
        @Override
        public int getIconWidth() {return MANCALA_SPACING;}
        @Override
        public int getIconHeight() {return HEIGHT;}
    };


    // Implements the represenatation of a pit
    private class Pit implements Icon {
        private final int pitIndex;

        public Pit(int pitIndex){
            this.pitIndex = pitIndex;
        }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            ArrayList<Integer> pits = boardModel.getState().getPits();
            Graphics2D g2 = (Graphics2D) g;
            Ellipse2D pitTop = new Ellipse2D.Double(0, 0, WIDTH / PIT_RATIO, WIDTH / PIT_RATIO);
            g2.setColor(PITS_COLOR);
            g2.fill(pitTop);
            g2.setColor(PEBBLES_COLOR);
            for (int i = 0; i < pits.get(pitIndex); i++){
                double theta = Math.PI * i / (pits.get(pitIndex) +1) * 2;
                int r = random.nextInt(WIDTH / PIT_RATIO / 4)+PIT_RATIO;
                g2.fill( new Ellipse2D.Double(PIT_RATIO * 3 + r * Math.cos(theta), PIT_RATIO * 3 + r * Math.sin(theta), WIDTH / PEBBLES_RATIO, WIDTH / PEBBLES_RATIO)   );
            }
            g2.setColor(Color.RED);
            g2.drawString(String.valueOf(pits.get(pitIndex)),10,10);
        }
        @Override
        public int getIconWidth() {return WIDTH/PIT_RATIO;}
        @Override
        public int getIconHeight() {return HEIGHT/PIT_RATIO;}
    }
}

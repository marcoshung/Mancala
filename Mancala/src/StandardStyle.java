import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

/**
 * Class for STANDARD STYLE concrete custom styling of a Mancala board
 */
public class StandardStyle implements BoardStyle {
    private static final boolean DEBUG = false;
    private final int WIDTH, HEIGHT;
    private static final int GAP = 10;
    private static final int PIT_RATIO = 10;
    private static final int PEBBLES_RATIO = 80;
    private static final int MANCALA_SPACING = 20;
    private static final Color PITS_COLOR = Color.BLUE;
    private static final Color MANCALA_COLOR = Color.BLUE;
    private static final Color PEBBLES_COLOR = Color.YELLOW;
    private JLabel labelPlayerA, labelPlayerB;

    /**
     * Constructor
     * @param width the width of the board
     * @param height the height of the board
     */
    public StandardStyle(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    /**
     * gets the width of the board
     * @return the width of the board
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * gets the height of the board
     * @return the height of the board
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Sets the graphic style to the board
     * @param container the Container which holds the mancala board
     * @param boardView the view of the game
     */
    public void layoutStyle(Container container, BoardView boardView) {
        Component[] components = container.getComponents();
        labelPlayerB = (JLabel) components[0];
        JPanel leftMancalaPanel = (JPanel) components[1];
        JPanel rightMancalaPanel = (JPanel) components[3];
        JPanel pitsPanel = (JPanel) components[2];
        labelPlayerA = (JLabel) components[4];


        labelPlayerB.add(new JLabel("<- Player B"));
        labelPlayerA.add(new JLabel("Player A ->"));
        markCurrentPlayer(boardView);
        leftMancalaPanel.add(new JLabel(new Mancala(1, boardView)));
        rightMancalaPanel.add(new JLabel(new Mancala(0, boardView)));
        pitsPanel.setLayout(new GridLayout(2,6));

        ArrayList<Integer> pits = boardView.getBoardModel().getState().getPits();
        for ( int i = pits.size()-1; i >= pits.size() / 2; i--){
            String text = "B" + String.valueOf((i % (pits.size()/2)) + 1);
            JLabel label = new JLabel(text,new Pit(i,boardView),JLabel.LEADING);
            label.addMouseListener(boardView.getMouseListener());
            pitsPanel.add(label);
        }

        for ( int i = 0 ; i < pits.size()/2; i++){
            String text = "A" + String.valueOf((i % (pits.size()/2)) + 1);
            JLabel label = new JLabel(text,new Pit(i, boardView),JLabel.LEADING);
            label.addMouseListener(boardView.getMouseListener());
            pitsPanel.add(label);
        }
    }

    /**
     * Helper function to control the styling to mark the current player
     * @param boardView the view of the game
     */
    public void markCurrentPlayer(BoardView boardView){
        if (boardView.getBoardModel().getState().get_next_isPlayerA()){
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
        private final BoardView boardView;
        public Mancala(int mancalaIndex, BoardView boardView){
            this.mancalaIndex = mancalaIndex;
            this.boardView = boardView;
        }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            ArrayList<Integer> mancalasArray = boardView.getBoardModel().getState().getMancala();
            Graphics2D g2 = (Graphics2D) g;
            RoundRectangle2D mancala = new RoundRectangle2D.Double(0 , 0, MANCALA_SPACING, HEIGHT - MANCALA_SPACING ,GAP,GAP);
            g2.setColor(MANCALA_COLOR);
            g2.fill(mancala);
            g2.setColor(PEBBLES_COLOR);
            int pebbleHeight = GAP;
            boolean leftPebble = false;
            for (int i = 0; i < mancalasArray.get(mancalaIndex); i++){
                leftPebble = !leftPebble;
                pebbleHeight += GAP/2;
                g2.fill( new Ellipse2D.Double((leftPebble ? GAP / 2 : GAP ), pebbleHeight, WIDTH / PEBBLES_RATIO, WIDTH / PEBBLES_RATIO)   );
            }
            g2.setColor(Color.RED);
            if (DEBUG)
                g2.drawString(String.valueOf(mancalasArray.get(mancalaIndex)),GAP/2,GAP);
        }
        @Override
        public int getIconWidth() {return MANCALA_SPACING;}
        @Override
        public int getIconHeight() {return HEIGHT;}
    };


    // Implements the represenatation of a pit
    private class Pit implements Icon {
        private final int pitIndex;
        private final BoardView boardView;
        public Pit(int pitIndex, BoardView boardView){
            this.pitIndex = pitIndex;
            this.boardView = boardView;
        }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            ArrayList<Integer> pits = boardView.getBoardModel().getState().getPits();
            Graphics2D g2 = (Graphics2D) g;
            Ellipse2D pitTop = new Ellipse2D.Double(0, 0, WIDTH / PIT_RATIO, WIDTH / PIT_RATIO);
            g2.setColor(PITS_COLOR);
            g2.fill(pitTop);
            g2.setColor(PEBBLES_COLOR);
            for (int i = 0; i < pits.get(pitIndex); i++){
                double theta = Math.PI * i / (pits.get(pitIndex) +1) * 2;
                int r = PIT_RATIO;
                g2.fill( new Ellipse2D.Double(PIT_RATIO * 3 + r * Math.cos(theta), PIT_RATIO * 3 + r * Math.sin(theta), WIDTH / PEBBLES_RATIO, WIDTH / PEBBLES_RATIO)   );
            }
            g2.setColor(Color.RED);
            if (DEBUG)
                g2.drawString(String.valueOf(pits.get(pitIndex)),GAP,GAP);
        }
        @Override
        public int getIconWidth() {return WIDTH/PIT_RATIO;}
        @Override
        public int getIconHeight() {return HEIGHT/PIT_RATIO;}
    }
}
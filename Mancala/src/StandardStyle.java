import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Class for concrete custom styling of a Mancala board
 */
public class StandardStyle extends BorderLayout implements BoardStyle {

    private final int WIDTH, HEIGHT;
    private static final int MARGIN = 10;
    private final int PIT_RATIO = 10;
    private final int PEBBLES_RATIO = 80;
    private final int MANCALA_SPACING = 20;
    private static final Color BOARD_COLOR = Color.GRAY;
    private static final Color PITS_COLOR = Color.BLUE;
    private static final Color MANCALA_COLOR = Color.YELLOW;
    private static final Color PEBBLES_COLOR = Color.GREEN;


    public StandardStyle(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public JLabel drawLabel(String string){
        return new JLabel(string);
    }


    public JPanel drawPits(Graphics g, ArrayList<Integer> pits, MouseListener listener) {
        JPanel panel = new JPanel(new GridLayout(2,6));

        class Pit implements Icon {
            int pebbleIndex;
            public Pit(int i){
                pebbleIndex = i;
            }

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                Ellipse2D pitTop = new Ellipse2D.Double(0, 0, WIDTH / PIT_RATIO, WIDTH / PIT_RATIO);
                g2.setColor(PITS_COLOR);
                g2.fill(pitTop);
                paintPebbles(g2);
            }

            @Override
            public int getIconWidth() {
                return WIDTH/PIT_RATIO;
            }

            @Override
            public int getIconHeight() {
                return HEIGHT/PIT_RATIO;
            }

            public void paintPebbles(Graphics2D g2){
                Random random = new Random();
                g2.setColor(PEBBLES_COLOR);

                for (int i = 0; i < pits.get(pebbleIndex); i++){
                    double theta = Math.PI * i / (pebbleIndex+1) * 2;
                    int r = random.nextInt(WIDTH / PIT_RATIO / 2)+PIT_RATIO;
                    g2.fill( new Ellipse2D.Double(PIT_RATIO + r * Math.cos(theta), PIT_RATIO + r * Math.sin(theta), WIDTH / PEBBLES_RATIO, WIDTH / PEBBLES_RATIO)   );
                }
            }
        }

        for ( int i = 0; i < pits.size(); i++){
            String text = ( i < pits.size()/2 ? "B" : "A") + String.valueOf((i % (pits.size()/2)) + 1);
            JLabel label = new JLabel(text,new Pit(i),JLabel.LEADING);
            label.addMouseListener(listener);
            panel.add(label);
        }
        return panel;
    }


    public JPanel drawMancalas(Graphics g, ArrayList<Integer> mancalas) {
        JPanel panel = new JPanel();
        class Mancala implements Icon {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                RoundRectangle2D mancala = new RoundRectangle2D.Double(0 , 0, MANCALA_SPACING, HEIGHT - 6 * MANCALA_SPACING,10,10);
                g2.setColor(MANCALA_COLOR);
                g2.fill(mancala);
            }

            @Override
            public int getIconWidth() {
                return MANCALA_SPACING;
            }

            @Override
            public int getIconHeight() {
                return HEIGHT;
            }
        };


        panel.add(new JLabel(new Mancala()));
        return panel;
    }



    public Dimension preferredLayoutSize(Container parent) {
        return minimumLayoutSize(parent);
    }

    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(WIDTH, HEIGHT);
    }
}

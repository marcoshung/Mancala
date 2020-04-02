import java.awt.Dimension;

import javax.swing.JFrame;

public class Mancala {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Play Mancala");
		Board b = new Board(50,50, 500, 200);
		BoardPanel bp = new BoardPanel(b);
		
		frame.add(bp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(1000,1000));
	    frame.pack();
		frame.setVisible(true);
	}
}

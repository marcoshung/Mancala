import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	Board board;
	
	public BoardPanel(Board b) {
		this.board = b;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		board.draw(g2);
	}
}

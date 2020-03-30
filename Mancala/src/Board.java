import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Board extends JComponent{
	private ArrayList<Pit> pits;
	
	private PlayerPool playerAPool;
	private PlayerPool playerBPool;
	
	private int x;
	private int y;
	private double width;
	private double length;
	
	private final static int NUM_OF_PITS = 12;
	private final static int PIT_RADIUS = 50;
	private final static int PIT_SPACING = 10;
	public Board(int x, int y, double width, double length) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		
		pits = new ArrayList<Pit>();
		int xpos = this.x + 30;
		int ypos = this.y + 30;
		int count = 0;
		for(int i = 0; i < NUM_OF_PITS; i++) {
			if(count % 6 == 0 & count != 0) {
				count = 0;
				xpos = this.x + 30;
				ypos = (int) (this.length - 30 );
			}
			pits.add(new Pit(this.x + xpos, ypos, PIT_RADIUS));
			count++;
			xpos += PIT_SPACING + PIT_RADIUS;
		}
		
		playerAPool = new PlayerPool(this.x + PIT_SPACING , this.y + PIT_SPACING, 50, length - 20);
		playerBPool = new PlayerPool((int) this.width - PIT_SPACING , this.y + PIT_SPACING, 50, length - 20);
		

	}
	
	public void draw(Graphics2D g2) {
		RoundRectangle2D.Double board = new RoundRectangle2D.Double(x, y, width, length, 50,50);
		g2.draw(board);
		for(Pit p :pits) {
			p.draw(g2);
		}
		playerAPool.draw(g2);
		playerBPool.draw(g2);
	}
}

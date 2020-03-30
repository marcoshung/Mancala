import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Pit extends JComponent{
	private int x;
	private int y;
	private double radius;
	
	public Pit(int x, int y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void draw(Graphics2D g2){
		Ellipse2D.Double pit = new Ellipse2D.Double(x,y, radius, radius);
		g2.draw(pit);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public double getRadius() {
		return this.radius;
	}
}

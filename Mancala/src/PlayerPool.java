import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class PlayerPool extends Pit{
	
	private double length;
	//In this instance radius will serve as the horizontal width
	public PlayerPool(int x, int y, double radius, Color color, double length) {
		super(x, y, radius, color);
		this.length = length;
	}
	
	public void draw(Graphics2D g2) {
		RoundRectangle2D.Double pool = new RoundRectangle2D.Double(super.getX(), super.getY(), super.getRadius(), length, 50,50);
		g2.setColor(super.getColor());
		g2.fill(pool);
		g2.draw(pool);
	}
}

import java.awt.Color;
import java.awt.geom.Point2D;

public class TrailBall {
	public Color fillColor;
	public Point2D.Double position; 
	
	
	public TrailBall(Color fillColor, Point2D.Double position) {
		this.fillColor = fillColor;
		this.position = position;
	}
	
	public void disappear() {
		int newAlpha = fillColor.getAlpha()-10;
		fillColor = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), Math.max(newAlpha, 0));
	}
	
}

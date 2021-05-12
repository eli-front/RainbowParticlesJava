import java.awt.Color;
import java.awt.geom.Point2D;

public class TrailBall {
	public Color fillColor;
	public Point2D.Double position;
	private Scene scene;
	
	
	public TrailBall(Color fillColor, Point2D.Double position, Scene scene) {
		this.fillColor = fillColor;
		this.position = position;
		this.scene = scene;
	}
	
	public void disappear() {

		int newAlpha = fillColor.getAlpha()-10;

		if (position.x < Ball.SIZE/2.0 || position.x > scene.getWidth()-Ball.SIZE/2.0 || position.y < Ball.SIZE/2.0 || position.y > scene.getHeight()-Ball.SIZE/2.0)  {
			fillColor = new Color(0,0,0,0);
		} else {
			fillColor = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), Math.max(newAlpha, 0));

		}


	}
	
}

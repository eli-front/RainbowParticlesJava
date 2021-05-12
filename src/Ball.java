import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;
public class Ball {
	public static int SIZE = 5;

	private final Scene scene;

	private final Point2D.Double velocity;
	
	public Color fillColor;

	public Point2D.Double position;

	public static double CONNECTION_VALUE = 100.0;
	
	public Ball(Scene scene, Point2D.Double position) {
		Random r = new Random();
		velocity = new Point2D.Double(r.nextDouble()*4.0-2, r.nextDouble()*4.0-2);
		
		this.scene = scene;
		
		this.position = position;
		
		updateColor();
	}
	
	
	public void update() {
		position.x += velocity.x;
		position.y += velocity.y;
		checkWall();
		updateColor();
	}
	
	public TrailBall getTrail() {
		return new TrailBall(fillColor, new Point2D.Double(position.getX(), position.getY()));
	}
	
	private void checkWall() {
		if (position.x < 0) {
			velocity.x *= -1;
			position.x = 0;
		}
		if (position.x > scene.getWidth()-SIZE) {
			velocity.x *= -1;
			position.x = scene.getWidth()-SIZE;
			
		}
		if (position.y < 0) {
			velocity.y *= -1;
			position.y = 0;
		}
		if (position.y > scene.getHeight()-SIZE) {
			velocity.y *= -1;
			position.y = scene.getHeight()-SIZE;
		}
	}
	
	private void updateColor() {	
		
		fillColor = new Color((int)(position.x /(double)scene.getWidth()*255.0), (int)(position.y /(double)scene.getHeight()*255.0), (int)((1.0- position.x /(double)scene.getWidth())*255.0), 255);
	}

	public static double distance(Point2D.Double p1, Point2D.Double p2) {
		double xDist = p1.getX() - p2.getX();
		double yDist = p1.getY() - p2.getY();

		return Math.sqrt(xDist*xDist + yDist*yDist);
	}
}

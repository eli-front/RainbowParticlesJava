import java.awt.*;
import java.awt.geom.Point2D;

public class Connector {

	public Color fillColor;
	public Point2D.Double start;
	public Point2D.Double end;

	public Connector(Point2D.Double start, Point2D.Double end, Scene scene) {
		this.start = start;
		this.end = end;

		Point2D midpoint = midpoint(start, end);
		fillColor = new Color((int)(midpoint.getX() /(double)scene.getWidth()*255.0), (int)(midpoint.getY() /(double)scene.getHeight()*255.0), (int)((1.0- midpoint.getX() /(double)scene.getWidth())*255.0), (int)Math.abs((Ball.CONNECTION_VALUE - Ball.distance(start, end))/Ball.CONNECTION_VALUE*255));
	}

	public void disappear() {
		int newAlpha = fillColor.getAlpha()-7;
		fillColor = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), Math.max(newAlpha, 0));
	}

	private Point2D.Double midpoint(Point2D.Double p1, Point2D.Double p2) {
		return new Point2D.Double((p1.getX()+p2.getX())/2, (p1.getY()+p2.getY())/2);
	}

}

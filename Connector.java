import java.awt.*;
import java.awt.geom.Point2D;

public class Connector extends SceneObject {

    private Point2D.Double start;
    private Point2D.Double end;

    private final Point2D midpoint;

    public Connector(Point2D.Double start, Point2D.Double end, Scene scene) {
        this.start = start;
        this.end = end;
        setScene(scene);

        this.midpoint = midpoint(start, end);
        updateColor((int)Math.abs((Ball.CONNECTION_VALUE - Ball.distance(start, end))/Ball.CONNECTION_VALUE*255));
    }

    public void disappear() {
        int newAlpha = getColor().getAlpha()-7;
        updateColor(Math.max(newAlpha, 0));
    }

    private Point2D.Double midpoint(Point2D.Double p1, Point2D.Double p2) {
        return new Point2D.Double((p1.getX()+p2.getX())/2, (p1.getY()+p2.getY())/2);
    }

    private void updateColor(int alpha) {
        if (midpoint.getX() < Ball.SIZE/2.0 || midpoint.getX() > getScene().getWidth()-Ball.SIZE/2.0 || midpoint.getY() < Ball.SIZE/2.0 || midpoint.getY() > getScene().getHeight()-Ball.SIZE/2.0) {
            setColor(new Color(0,0,0,0));
        } else {
            setColor(new Color((int)(midpoint.getX() /(double)getScene().getWidth()*255.0), (int)(midpoint.getY() /(double)getScene().getHeight()*255.0), (int)((1.0- midpoint.getX() /(double)getScene().getWidth())*255.0), alpha));
        }
    }
    
    public Point2D.Double getStart() { return start; }
    public Point2D.Double getEnd() { return end; }
    

}

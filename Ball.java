import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;
public class Ball extends SceneObject {
    public static int SIZE = 5;

    private final Point2D.Double velocity;

    private Point2D.Double position;

    public static double CONNECTION_VALUE = 100.0;
    
    public Ball(Scene scene, Point2D.Double position) {        
        Random r = new Random();
        velocity = new Point2D.Double(r.nextDouble()*4.0-2, r.nextDouble()*4.0-2);
        
        setScene(scene);
        
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
        checkWall();
        return new TrailBall(getColor(), new Point2D.Double(position.getX(), position.getY()), getScene());
    }
    
    private void checkWall() {
        if (position.x < SIZE/2.0) {
            velocity.x *= -1;
            position.x = SIZE/2.0;
        }
        if (position.x > getScene().getWidth()-SIZE/2.0) {
            velocity.x *= -1;
            position.x = getScene().getWidth()-SIZE/2.0;
            
        }
        if (position.y < SIZE/2.0) {
            velocity.y *= -1;
            position.y = SIZE/2.0;
        }
        if (position.y > getScene().getHeight()-SIZE/2.0) {
            velocity.y *= -1;
            position.y = getScene().getHeight()-SIZE/2.0;
        }
    }
    
    private void updateColor() {    
        setColor(new Color((int)(position.x /(double)getScene().getWidth()*255.0), (int)(position.y /(double)getScene().getHeight()*255.0), (int)((1.0- position.x /(double)getScene().getWidth())*255.0), 255));
    }
    

    public static double distance(Point2D.Double p1, Point2D.Double p2) {
        double xDist = p1.getX() - p2.getX();
        double yDist = p1.getY() - p2.getY();

        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    
    public Point2D.Double getPosition() {return position;}
    
}

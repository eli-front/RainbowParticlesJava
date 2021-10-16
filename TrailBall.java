import java.awt.Color;
import java.awt.geom.Point2D;

public class TrailBall extends Ball {
    
    public TrailBall(Color fillColor, Point2D.Double position, Scene scene) {
        super(scene, position);
        setColor(fillColor);
    }
    
    public void disappear() {

        int newAlpha = getColor().getAlpha()-10;

        if (this.getPosition().x < Ball.SIZE/2.0 || this.getPosition().x > getScene().getWidth()-Ball.SIZE/2.0 || getPosition().y < Ball.SIZE/2.0 || getPosition().y > getScene().getHeight()-Ball.SIZE/2.0)  {
            setColor(new Color(0,0,0,0));
        } else {
            setColor(new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), Math.max(newAlpha, 0)));
        }
    }
}


/**
 * Write a description of class SceneObject here.
 *
 * @author Eli Front
 * @version 1.0
 */

import java.awt.Color;

public class SceneObject
{
    private Color fillColor;
    
    private Scene scene;
    
    public Color getColor() {return fillColor;}
        
    public void setColor(Color c) {fillColor = c;}
    
    public Scene getScene() {return scene;}
    
    public void setScene(Scene s) {scene = s;}
    
}

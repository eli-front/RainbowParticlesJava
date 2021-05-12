import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Random;


public class Scene extends JPanel implements ActionListener {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	Timer timer = new Timer(10, this);
	
	final private int BALL_COUNT = 120;
	
	private final Ball[] leadBalls = new Ball[BALL_COUNT];

	private final ArrayList<Connector> connectors = new ArrayList<>();
	
	private final ArrayList<TrailBall> trailBalls = new ArrayList<>();

	private final Random r = new Random();
	
	public void addBalls() {
		for (int i = 0; i < BALL_COUNT; i++) {
			leadBalls[i] = new Ball(this, new Point2D.Double(r.nextDouble()*getWidth(), r.nextDouble()*getHeight()));
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2draw = (Graphics2D) g.create();
        try {
        	 g2draw.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
             g2draw.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
             g2draw.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        	for (Ball ball : leadBalls) {
	            Ellipse2D.Double e = new Ellipse2D.Double(ball.position.x, ball.position.y, Ball.SIZE, Ball.SIZE);
	            g2draw.setColor(ball.fillColor);
	            g2draw.fill(e);
        	}
        	
        	trailBalls.forEach((ball) -> {
        		Ellipse2D.Double e = new Ellipse2D.Double(ball.position.x, ball.position.y, Ball.SIZE, Ball.SIZE);
	            g2draw.setColor(ball.fillColor);
	            g2draw.fill(e);
        	});

        	connectors.forEach((connector) -> {
				Line2D.Double c = new Line2D.Double(connector.start, connector.end);
				g2draw.setColor(connector.fillColor);
				g2draw.draw(c);
			});
        	
        } finally {
            g2draw.dispose();
        }
		
		
		
		timer.start();
	}
	
	public static void main(String[] args) {
		
		Scene scene = new Scene();
		JFrame frame = new JFrame();
		
		frame.setTitle("Rainbow Particles");
		frame.setSize(1000, 700);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scene);

		
		frame.setVisible(true);
		scene.addBalls();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < leadBalls.length; i++) {
			Ball ball = leadBalls[i];
			ball.update();

			for (int j = i+1; j < leadBalls.length; j++) {
				if (Math.abs(Ball.distance(ball.position, leadBalls[j].position)) < Ball.CONNECTION_VALUE) {
					connectors.add(new Connector((Point2D.Double) ball.position.clone(), (Point2D.Double) leadBalls[j].position.clone(), this));
				}
			}
			
			trailBalls.add(ball.getTrail());
		}
		for (int i = trailBalls.size()-1; i >= 0; i--) {
			trailBalls.get(i).disappear();
			if (trailBalls.get(i).fillColor.getAlpha()<=0) {
				trailBalls.remove(i);
			}
		}

		for (int i = connectors.size()-1; i >= 0; i--) {
			connectors.get(i).disappear();
			if (connectors.get(i).fillColor.getAlpha()<=0) {
				connectors.remove(i);
			}
		}
	
		repaint();
	}
	

}

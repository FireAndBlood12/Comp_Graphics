import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

@SuppressWarnings("serial")
public class FigureAnimation extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;


    double points[][] = {
            { -55, -150 }, { -5, -150 }, { -30, -130 }, { -55, -110 }, { -5, -110 }
    };

    Timer timer;

    // Для анімації повороту
    private double angle = 0;

    // for movement animation
    private double tx = 0;
    private double ty = 1;
    private double deltaX = 0.5;
    private int radius = 100;
    private int radiusExtention = 230;

    public FigureAnimation() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering params.
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        // Set background color.
        g2d.setBackground(new Color(218, 112, 214));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        // Set (0;0) to the center to draw main Frame.
        g2d.translate(maxWidth/2, maxHeight/2);
        // Draw the main Frame.
        BasicStroke bs2 = new BasicStroke(16, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs2);
        g2d.drawRect(
                -(radius + radiusExtention),
                -(radius + radiusExtention),
                (radius + radiusExtention)*2,
                (radius + radiusExtention)*2
        );

        // Reset center to default value for the main animation.
        g2d.translate(tx, ty);

        // Set antenna crazy_triangle
        GeneralPath clock = new GeneralPath();
        clock.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            clock.lineTo(points[k][0], points[k][1]);
        clock.closePath();

        // Draw antenna.
        GradientPaint gp = new GradientPaint(
                25, 50,
                new Color(10, 105, 85),
                60, 5,
                new Color(5, 20, 105),
                true
        );
        g2d.setPaint(gp);
        g2d.rotate(angle, clock.getBounds2D().getCenterX(),
                clock.getBounds2D().getCenterY());
        g2d.fill(clock);

        g2d.setColor(new Color(0, 128, 0));
        g2d.fillRect(-80, -180, 100, 30);
        g2d.setColor(Color.yellow);
        g2d.fill(new Ellipse2D.Double(-5, -180, 25, 100));
        g2d.setColor(Color.red);
        g2d.fillRect(-80, -110, 100, 30);
        g2d.setColor(Color.blue);
        g2d.fill(new Ellipse2D.Double(-80, -180, 25, 100));
        g2d.setColor(new Color(0, 128, 0));
        g2d.fillRect(-80, -180, 50, 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new FigureAnimation());

        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        double radiusInSquare = Math.pow(radius, 2);
        if (tx <= 0 && ty < 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx > 0 && ty <= 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx >= 0 && ty >= 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx < 0 && ty >= 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }

        angle += 0.01;

        repaint();
    }
}

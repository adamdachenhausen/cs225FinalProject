import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Write a description of class Roads here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Roads extends AnimatedGraphicsObject
{
    List<Point> roads;
    public Roads(JComponent container){
        super(container);
        roads = new ArrayList<Point>();
        this.container = container;
    }

    public void addLocation(Point p){
        roads.add(p);
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        g.setColor(Color.ORANGE);

        for(Point l : roads){
            g.fillOval(l.x,l.y,5,5);
        }
        //Set g back to the original color
        g.setColor(cur);
    }

    @Override
    public void run(){

    }
}

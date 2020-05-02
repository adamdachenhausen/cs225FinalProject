import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Write a description of class CityLocations here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CityLocations extends AnimatedGraphicsObject
{
    List<Point> locations;
    public CityLocations(JComponent container){
        super(container);
        locations = new ArrayList<Point>();
        this.container = container;
    }

    public void addLocation(Point p){
        locations.add(p);
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        g.setColor(Color.BLACK);

        for(Point l : locations){
            g.fillOval(l.x,l.y,5,5);
        }
        //Set g back to the original color
        g.setColor(cur);
    }

    @Override
    public void run(){

    }
}

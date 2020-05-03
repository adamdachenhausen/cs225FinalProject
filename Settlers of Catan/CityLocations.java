import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
/**
 * A collection of points that represent city/settlements
 *
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class CityLocations extends AnimatedGraphicsObject
{
    List<Point> locations;
    public CityLocations(JComponent container){
        super(container);
        locations = new ArrayList<Point>();
        this.container = container;
    }

    /** Adds a point to this
     *  @param p a city/settlement to add to this
     */
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

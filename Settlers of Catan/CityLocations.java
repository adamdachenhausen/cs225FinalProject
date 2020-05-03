import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * A collection of points that represent city/settlements
 *
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class CityLocations extends AnimatedGraphicsObject
{
    private List<City> locations;

    public CityLocations(JComponent container){
        super(container);
        locations = new ArrayList<City>();
        this.container = container;
    }

    /** Adds a point to this
     *  @param p a city/settlement to add to this
     */
    public void addLocation(City c){
        locations.add(c);
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        //Set g back to the original color
        g.setColor(cur);
    }

    @Override
    public void run(){

    }

}

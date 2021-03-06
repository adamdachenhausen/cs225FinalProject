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

    /** Removes a point from this
     *  @param p a city/settlement to remove from this
     */
    public void removeLocation(City c){
        locations.remove(c);
    }
    
    /** Adds a point to this
     *  @param p a city/settlement to add to this
     */
    public List<City> getLocations(){
        return locations;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        for(City c : locations){
            c.paint(g);
        }
        
        //Set g back to the original color
        g.setColor(cur);
    }

    @Override
    public void run(){

    }

}

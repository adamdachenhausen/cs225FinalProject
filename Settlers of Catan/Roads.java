import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
/**
 * A collection of Road objects
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Roads extends AnimatedGraphicsObject
{
    protected List<Road> roads;
    /** Constructor for Roads
     * @param container what should I be drawn in?
     */
    public Roads(JComponent container){
        super(container);
        roads = new ArrayList<Road>();
        this.container = container;
    }

    /** Adds a Road to roads
     *  @param r the Road to be added to the internal roads
     */
    public void addRoad(Road r){
        roads.add(r);
    }

    /** Removes a Road from roads
     *  @param r the Road to be removed from the internal roads
     */
    public void removeRoad(Road r){
        roads.remove(r);
    }

    /** Adds a point to this
     *  @return the roads List
     */
    public List getRoadList(){
        return roads;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();


        for(Road r : roads){
            r.paint(g);
        }
        //Set g back to the original color
        g.setColor(cur);
    }

    @Override
    public void run(){

    }
}

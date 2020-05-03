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
    
            /** Adds a point to this
     *  @param p a city/settlement to add to this
     */
    public List getRoadList(){
        return roads;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        g.setColor(Color.ORANGE);

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

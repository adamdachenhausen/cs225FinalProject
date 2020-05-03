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
    List<Road> roads;
    public Roads(JComponent container){
        super(container);
        roads = new ArrayList<Road>();
        this.container = container;
    }

    public void addRoad(Road r){
        roads.add(r);
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

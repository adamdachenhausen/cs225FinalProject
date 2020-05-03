import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Point;
import java.awt.Color;
import java.awt.Polygon;
/**
 * Write a description of class Road here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Road extends AnimatedGraphicsObject
{
    protected Point start;
    protected Point end;
    protected Point midPoint;
    public Road(JComponent container,Point start, Point end){
        super(container);
        this.start = start;
        this.end = end;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();
        
        
        g.setColor(Color.ORANGE);
        Polygon p = new Polygon();
        p.addPoint(start.x-3,start.y-3);
        p.addPoint(start.x+3,start.y+3);
        p.addPoint(end.x+3,end.y+3);
        p.addPoint(end.x-3,end.y-3);
        
        g.fillPolygon(p);
        //g.drawRect(start.x,start.y, end.x-start.x, end.y-start.y);
        
        //Set g back to the original color
        g.setColor(cur);
    }

    /** Given two points, returns the midpoint
     *  @param p1 The first point of the line to calculate midpoint
     *  @param p2 The second point of the line to calculate midpoint
     */
    private Point midPoint(){
        return new Point((start.x + end.x)/2,(start.y + end.y)/2);
    }

    @Override
    public void run(){

    }
}

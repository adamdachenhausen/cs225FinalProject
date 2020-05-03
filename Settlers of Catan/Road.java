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
    public static final int SHIFT = 3;
    protected Point start;
    protected Point end;
    protected Point midPoint;
    protected String type;
    public Road(JComponent container,Point start, Point end, String type){
        super(container);
        this.start = start;
        this.end = end;
        this.type = type;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        g.setColor(Color.ORANGE);
        Polygon p = new Polygon();

        if(type.equals("|")){
            p.addPoint(start.x-SHIFT,start.y);
            p.addPoint(start.x+SHIFT,start.y);
            p.addPoint(end.x+SHIFT,end.y);
            p.addPoint(end.x-SHIFT,end.y);
        }
        else if(type.equals("-")){
            p.addPoint(start.x,start.y-SHIFT);
            p.addPoint(start.x,start.y+SHIFT);
            p.addPoint(end.x,end.y+SHIFT);
            p.addPoint(end.x,end.y-SHIFT);
        }
        else if(type.equals("/")){
            p.addPoint(start.x-SHIFT,start.y-SHIFT);
            p.addPoint(start.x+SHIFT,start.y+SHIFT);
            p.addPoint(end.x+SHIFT,end.y+SHIFT);
            p.addPoint(end.x-SHIFT,end.y-SHIFT);
        }
        //Can't use "\" so !/ works
        else if(type.equals("!/")){
            p.addPoint(start.x-SHIFT,start.y+SHIFT);
            p.addPoint(start.x+SHIFT,start.y-SHIFT);
            p.addPoint(end.x+SHIFT,end.y-SHIFT);
            p.addPoint(end.x-SHIFT,end.y+SHIFT);
        }
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

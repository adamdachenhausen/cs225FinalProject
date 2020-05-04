import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Point;
import java.awt.Color;
import java.awt.Polygon;
/**
 * Represents a road in the game, can be not drawn, if it is impossible
 * to get to this yet, or if it is, this is drawn as a circle, if the player chooses
 * to place this down, it draws a parallelagram colored the same as the player
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Road extends AnimatedGraphicsObject
{
    public static final int SHIFT = 3;
    protected Point start;
    protected Point end;
    protected Point midPoint;
    protected String type;
    protected boolean placed;
    protected boolean possible;
    
    /** Constructor for Road
     * 
     *  @param container what should I be drawn in?
     *  @param start where should I start?
     *  @param end where should I end?
     *  @param type what type should I be?
     */
    public Road(JComponent container,Point start, Point end, String type){
        super(container);
        this.start = start;
        this.end = end;
        this.type = type;
        this.placed = false;
        this.possible = true;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        if(placed){
            //g.setColor(Color.ORANGE);
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
        }
        else{
            if(possible){
                Point mid = midPoint();
                g.drawOval(mid.x-3,mid.y-3,6,6);
            }
            else{
                //Do nothing
            }
        }

        //Set g back to the original color
        g.setColor(cur);
    }

    /** Given two points, returns the midpoint
     *  @param p1 The first point of the line to calculate midpoint
     *  @param p2 The second point of the line to calculate midpoint
     */
    public Point midPoint(){
        return new Point((start.x + end.x)/2,(start.y + end.y)/2);
    }

    /** returns start
     *  @return start
     */
    public Point getRoadPoint1(){
        return start;
    }

    /** returns end
     * @return end
     */
    public Point getRoadPoint2(){
        return end;
    }

    /** Updates this so that it will draw a polygon, not just a circle
     * 
     */
    public void update(){
        if(possible){
            placed = true;
        }
    }
    
    @Override
    public void run(){

    }
}

import javax.swing.Icon;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Creates one of the hexagon tiles used to form the gameboard.
 *
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class HexTiles extends AnimatedGraphicsObject{
    public static final int POINTS = 6;

    //I found this give "nice" height and width
    //SRC: https://hexagoncalculator.apphb.com/
    public static final int MULTIPLIER = 4;
    public static final int SIDE_LENGTH = 17*MULTIPLIER;
    public static final double SIDE_SIDE_LENGTH = 29.5*MULTIPLIER;
    public static final int PERIMETER = 102*MULTIPLIER;
    public static final int AREA = 750*MULTIPLIER;
    public static final int VERTEX_VERTEX_LENGTH = 34*MULTIPLIER;

    public static final int X_OFFSET = 15*MULTIPLIER;
    public static final int Y_OFFSET = 8*MULTIPLIER;

    protected Point center;

    protected String type = "";

    protected Resource r;

    protected Polygon p;

    protected Tokens t;

    protected Point[] pts;
    protected String locationType;
    protected String subType;
    private boolean hasRobber;
    protected String resourceType;
    /**
     * Constructor for objects of class Tiles
     * 
     * @param container what should I be drawn in?
     * @param center where should I be drawn?
     * @param r the Resource value of this
     * @param t the Tokens value of this
     * @param locationType where in the gameBoard this is
     * @param subType further specifics of locationType
     */
    public HexTiles(JComponent container, Point center, Resource r,Tokens t, String locationType, String subType){
        super(container);
        this.container = container;
        p = new Polygon();
        this.center=center;
        this.r = r;
        this.t=t;
        this.pts = new Point[6];
        this.locationType=locationType;
        this.subType=subType;
        
        //Outsource completing the polygon to make it a hexagon
        completeHex();
        
        createHexType();
        
        
        hasRobber = false;

    }

    /**
     * Constructor for objects of class Tiles, but with added translate center functionality
     * 
     * @param container what should I be drawn in?
     * @param center where should I be drawn?
     * @param r the Resource value of this
     * @param t the Tokens value of this
     * @param dx the amount to move this in the x direction
     * @parm dy the amound to move this in the y direction
     * @param locationType where in the gameBoard this is
     * @param subType further specifics of locationType
     *
     */
    public HexTiles(JComponent container, Point center, Resource r,Tokens t,int dx, int dy, String locationType, String subType){
        super(container);
        p = new Polygon();
        //this.center=center;
        this.r = r;
        this.t = t;
        this.locationType=locationType;
        this.subType=subType;
        this.pts = new Point[6];
        this.center = new Point(center.x+dx,center.y+dy);

        //Outsource completing the polygon to make it a hexagon
        completeHex();
    }

    /**
     *  Adds points to p to create a hexagon in this shape
     *      /\
     *      ||
     *      \/
     */
    private void completeHex(){
        //Init points in array, so they can be used again
        pts[0] = new Point(center.x,center.y+SIDE_LENGTH);
        pts[1] = new Point(center.x+X_OFFSET,center.y+Y_OFFSET);
        pts[2] = new Point(center.x+X_OFFSET,center.y-Y_OFFSET);
        pts[3] = new Point(center.x,center.y-SIDE_LENGTH);
        pts[4] = new Point(center.x-X_OFFSET,center.y-Y_OFFSET);
        pts[5] = new Point(center.x-X_OFFSET,center.y+Y_OFFSET);

        p.addPoint(pts[0].x,pts[0].y);

        p.addPoint(pts[1].x,pts[1].y);

        p.addPoint(pts[2].x,pts[2].y);
        p.addPoint(pts[3].x,pts[3].y);

        p.addPoint(pts[4].x,pts[4].y);
        p.addPoint(pts[5].x,pts[5].y);
    }

    @Override
    public void paint(Graphics g){
        if(this == null){
            //do nothing
        }
        else{
            //For returning g back to the original color
            Color cur = g.getColor();

            //Determine what color to make the tile based on its resource
            switch(r){
                case BRICKS:
                g.setColor(new Color(203, 65, 84));
                break;

                case WOOD:
                g.setColor(new Color(34, 139, 34));
                break;

                case ORE:
                g.setColor(new Color(149, 148, 139));
                break;

                case WHEAT:
                g.setColor(new Color(245, 222, 179));
                break;

                case WOOL:
                g.setColor(new Color(86, 125, 70));
                break;

                case SAND:
                g.setColor(new Color(194, 178, 128));
                break;
            }

            //Draw the filled polygon
            g.fillPolygon(p);

            //Outline the polygon in black
            g.setColor(Color.BLACK);

            //Draw the polygon outline
            g.drawPolygon(p);

            //Set g back to the original color
            g.setColor(cur);
        }
    }

    /** Returns the center coordinate of this
     *  @return the center of this
     */
    public Point getHexPoint(){
        return center;
    }

    /** Returns the type of this
     *  @return the type of this
     */
    public String getHexType(){
        return type;
    }

    /** Sets the hexType of this
     * 
     */
    public void createHexType(){
        String hextype = "";
        switch(r){
            case BRICKS:
            hextype = "Brick";
            resourceType = hextype;
            break;

            case WOOD:
            hextype = "Lumber";
            resourceType = hextype;
            break;

            case ORE:
            hextype = "Ore";
            resourceType = hextype;
            break;

            case WHEAT:
            hextype = "Grain";
            resourceType = hextype;
            break;

            case WOOL:
            hextype = "Wool";
            resourceType = hextype;
            break;

            case SAND:
            hextype = "Desert";
            resourceType = hextype;
            break;
        }

    }

    /** Returns the resource represented by this
     *  @return the resource of this 
     */
    public String getResourceType(){
        return resourceType;
    }

    /** Returns the resource represented by this
     *  @return the resource of this 
     */
    public Resource getResource(){
        return r;
    }

    /** Returns the token held by this
     *  @return the token held by this
     */
    public Tokens getToken(){
        return t;
    }

    /** Puts a new token on this
     *  @param newToken the token to replace this token with
     */
    public void setToken(Tokens newToken){
        t = newToken;
    }

    /** Removes the token from this
     * 
     */
    public void removeToken(){
        t = null;
    }

    /** Returns true if a robber is on this
     *  @return true if this contains a robber
     */
    public boolean getHasRobber(){
        return hasRobber;
    }

    /** Sets hasRobber
     *  @param newHasRobber updates the hasRobber of this
     */
    public void setRobber(boolean newHasRobber){
        hasRobber = newHasRobber;
    }

    /** Returns city locations of this, but only certain ones, so that the gameboard
     *  does not have doubled, or very close city locations
     * 
     *  @param type refers to where in the gameboard this is.
     *  Can be type: CORNER, MIDDLE, INBETWEEN, or left null for default
     *  @param subType refers to where this type is, for instance if this was the
     *  top left hex, type would be corner, and subType would be topLeft
     *  
     */
    public ArrayList getCityLocations(){

        ArrayList<Point> loc = new ArrayList(6);

        //We always need the northernmost and southernmost points, aka tips
        loc.add(pts[0]);
        loc.add(pts[3]);

        //Depending if we have a special case, we will add those extra points too
        if(locationType!=null && subType!=null){
            if(locationType.equals("CORNER")){
                if(subType.equals("TOP")){
                    loc.add(pts[2]);
                    loc.add(pts[4]);
                }
                else if(subType.equals("BOTTOM")){
                    loc.add(pts[1]);
                    loc.add(pts[5]);
                }
            }
            else if(locationType.equals("INBETWEEN")){
                if(subType.equals("TOPLEFT")){
                    loc.add(pts[4]);
                }
                else if(subType.equals("TOPRIGHT")){
                    loc.add(pts[2]);
                }
                else if(subType.equals("BOTTOMLEFT")){
                    loc.add(pts[5]);
                }
                else if(subType.equals("BOTTOMRIGHT")){
                    loc.add(pts[1]);
                }
            }
            else if(locationType.equals("MIDDLE")){
                if(subType.equals("LEFT")){
                    loc.add(pts[4]);
                    loc.add(pts[5]);
                }
                else if(subType.equals("RIGHT")){
                    loc.add(pts[1]);
                    loc.add(pts[2]);
                }
            }
        }

        return loc;
    }

    /** Returns road locations of this, but only certain ones, so that the gameboard
     *  does not have doubled, or very close road locations
     *  @return an ArrayList of Road objects
     */
    public ArrayList getRoads(){
        ArrayList<Road> roads = new ArrayList(6);

        roads.add(new Road(container, pts[3],pts[4],"/"));
        roads.add(new Road(container, pts[3],pts[2],"!/"));
        roads.add(new Road(container, pts[1],pts[2],"|"));

        if(locationType!=null && subType!=null){
            if(locationType.equals("CORNER")){

                roads.add(new Road(container, pts[4],pts[5],"|"));

                if(subType.equals("BOTTOM")){
                    roads.add(new Road(container, pts[5],pts[0],"!/"));
                    roads.add(new Road(container, pts[0],pts[1],"/"));

                }
            }
            else if(locationType.equals("INBETWEEN")){
                if(subType.equals("TOPLEFT")){
                    roads.add(new Road(container, pts[4],pts[5],"|"));
                }
                else if(subType.equals("TOPRIGHT")){
                    //Do nothing
                }
                else if(subType.equals("BOTTOMLEFT")){
                    roads.add(new Road(container, pts[4],pts[5],"|"));
                    roads.add(new Road(container, pts[5],pts[0],"!/"));
                }
                else if(subType.equals("BOTTOMRIGHT")){
                    roads.add(new Road(container, pts[0],pts[1],"/"));
                }
            }
            else if(locationType.equals("MIDDLE")){
                if(subType.equals("LEFT")){
                    roads.add(new Road(container, pts[4],pts[5],"|"));
                    roads.add(new Road(container, pts[5],pts[0],"!/"));
                }
                else if(subType.equals("RIGHT")){
                    roads.add(new Road(container, pts[0],pts[1],"/"));
                }
            }
            else if(locationType.equals("SPECIAL")){
                roads.add(new Road(container, pts[5],pts[0],"!/"));
                roads.add(new Road(container, pts[0],pts[1],"/"));
            }
        }

        return roads;
    }

    /** Given two points, returns the midpoint
     *  @param p1 The first point of the line to calculate midpoint
     *  @param p2 The second point of the line to calculate midpoint
     *  @return the midpoint beween p1 and p2
     */
    private Point midPoint(Point p1, Point p2){
        return new Point((p1.x + p2.x)/2,(p1.y + p2.y)/2);
    }

    @Override
    public void run(){

    }
}
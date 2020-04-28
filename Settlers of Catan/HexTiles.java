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
    public static final int Y_OFFSET1 = 8*MULTIPLIER;
    public static final int Y_OFFSET2 = 9*MULTIPLIER;

    protected Point center;

    protected String type;

    protected Resource r;

    protected Polygon p;

    private boolean debug = true;
    /**
     * Constructor for objects of class Tiles
     */
    public HexTiles(JComponent container, Point center, Resource r,Token t){
        super(container);
        this.container = container;
        p = new Polygon();
        this.center=center;
        this.r = r;

        //Outsource completing the polygon to make it a hexagon
        completeHex();
    }

    /**
     * Constructor for objects of class Tiles, but with added translate center functionality
     */
    public HexTiles(JComponent container, Point center, Resource r,Token t,int dx, int dy){
        super(container);
        p = new Polygon();
        //this.center=center;
        this.r = r;

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
        //Init points as degrees around a circle, so they can be used multiple times
        Point zero = new Point(center.x,center.y+SIDE_LENGTH);
        Point sixty = new Point(center.x+X_OFFSET,center.y+Y_OFFSET1);
        Point oneHTwenty = new Point(center.x+X_OFFSET,center.y-Y_OFFSET2);
        Point oneHEighty = new Point(center.x,center.y-SIDE_LENGTH);
        Point twoHFourty = new Point(center.x-X_OFFSET,center.y-Y_OFFSET1);
        Point threeH = new Point(center.x-X_OFFSET,center.y+Y_OFFSET2);

        //Add points in clockwise rotation starting at northernmost point
        p.addPoint(zero.x,zero.y);

        p.addPoint(sixty.x,sixty.y);

        p.addPoint(oneHTwenty.x,oneHTwenty.y);
        p.addPoint(oneHEighty.x,oneHEighty.y);

        p.addPoint(twoHFourty.x,twoHFourty.y);
        p.addPoint(threeH.x,threeH.y);
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

    @Override
    public void run(){
        while(!done){

            container.repaint();
        }
    }
}
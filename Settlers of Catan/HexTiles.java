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
 * Creates the hexagon tiles used to form the gameboard.
 *
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class HexTiles extends AnimatedGraphicsObject{
    public static final int POINTS = 6;
    
    //I found this give "nice" height and width
    //SRC: https://hexagoncalculator.apphb.com/
    public static final int SIDE_LENGTH = 17;
    public static final double SIDE_SIDE_LENGTH = 29.5;
    public static final int PERIMETER = 102;
    public static final int AREA = 750;
    public static final int VERTEX_VERTEX_LENGTH = 34;
    
    public static final int X_OFFSET = 15;

    protected Point center;
    
    protected String type;

    protected Polygon p;
    /**
     * Constructor for objects of class Tiles
     */
    public HexTiles(JComponent container, Point center){
        super(container);
        p = new Polygon();
        this.center=center;
        
        //Outsource completing the polygon to make it a hexagon
        completeHex();
    }

    private void completeHex(){
        //Add the northern most and southernmost points
        p.addPoint(center.x+SIDE_LENGTH,center.y);
        p.addPoint(center.x-SIDE_LENGTH,center.y);
        
        //Add right side points
        p.addPoint(center.x+X_OFFSET,center.y+8);
        p.addPoint(center.x+X_OFFSET,center.y-9);
        
        //Add left side points
        p.addPoint(center.x-X_OFFSET,center.y+9);
        p.addPoint(center.x-X_OFFSET,center.y-8);
    }
    
    @Override
    public void paint(Graphics g){
        
    }

    @Override
    public void run(){

    }

    public void addPoints(){

    }

}
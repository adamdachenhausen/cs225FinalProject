import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Write a description of class LaserShot here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LaserShot
{
    //Standard unit to base a square off of
    public static final int STD = 1;
    protected String type;
    protected String subType;
    protected Point upperLeft;
    public void paint(Graphics g){
        int y_Shift = 0;
        if(type.equals("1")){
            //Zigzag
            if(subType.equals("A")){
                //Draw \ leg
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw / leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw \ leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "B";
            }
            else if(subType.equals("B")){
                //Draw \ leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw / leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw \ leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw / leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "C";
            }
            else if(subType.equals("C")){
                //Draw / leg
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw \ leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw / leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "D";
            }
            else{
                //Draw / leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw \ leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw / leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;

                //Draw \ leg
                g.drawRect(upperLeft.x,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "A";
            }
        }
        // | with horizontal lines
        else if(type.equals("2")){
            //Draw base arrow
            g.drawRect(upperLeft.x,upperLeft.y,STD,7*STD);
            if(subType.equals("A")){
                y_Shift = 1;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "B";
            }
            else if(subType.equals("B")){
                y_Shift = 3;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "C";
            }
            else{
                y_Shift = 5;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "A";
            }
        }
        // | with barbs
        else if(type.equals("3")){
            //Draw base arrow
            g.drawRect(upperLeft.x,upperLeft.y,STD,6*STD);
            if(subType.equals("A")){
                y_Shift = 2;

                //Draw barbs
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "B";
            }
            else if(subType.equals("C")){
                y_Shift = 0;

                //Draw barbs
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x+STD,upperLeft.y+y_Shift,STD,STD);y_Shift++;
                g.drawRect(upperLeft.x-STD,upperLeft.y+y_Shift,STD,STD);
                
                //Update subtype
                subType = "A";
            }
            else{
                //Do nothing a B is just |
                //Update subtype
                subType = "C";
            }
        }
        // |
        else{
            g.drawRect(upperLeft.x,upperLeft.y,STD,7*STD);
        }
    }
}

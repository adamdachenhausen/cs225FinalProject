import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
/**
 * Handles individual animation of a laser shot
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class LaserShot extends AnimatedGraphicsObject
{
    //Standard unit to base a square off of
    public static final int STD = 1;

    public LaserShot(JComponent container, Point upperLeft,String typeIn){
        super(container);
        this.upperLeft = upperLeft;
        type=typeIn;

        //If its an alien's laser, generate a random laser for them to use
        if(!typeIn.equals("PLAYER")){
            Random rand = new Random();
            type = ""+rand.nextInt(3)+1;
        }
        //Set subType to "A" as its the first frame of animation
        subType = "A";
    }

    protected void updateUpperLeft(Point upperLeftIn){
        upperLeft = upperLeftIn;
    }

    @Override
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

    /**
     * This object's run method, which manages the life of the shot as it

     */
    @Override
    public void run() {
        int i = 0;
        while(i < 2){
            sleepWithCatch(DELAY_TIME);

            container.repaint();
            i++;
        }
    }
}

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

    }

    protected void updateUpperLeft(Point upperLeftIn){
        upperLeft = upperLeftIn;
    }

    @Override
    public void paint(Graphics g){
        int y_Shift = 0;

        if(type.equals("ALIEN")){
            g.setColor(Color.RED);
            g.drawRect(upperLeft.x,upperLeft.y,STD,7*STD);
        }else{
            g.setColor(Color.GREEN);
            g.drawRect(upperLeft.x,upperLeft.y,STD,7*STD);
        }

    }

    /**
     * This object's run method, which manages the life of the shot as it

     */
    @Override
    public void run() {

    }
}

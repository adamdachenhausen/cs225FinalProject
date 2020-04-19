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
 * An abstract class that provides some instance variables and 
 * default versions of methods that are needed by threaded objects
 * representing animated graphics objects.
 * 
 * @author Jim Teresco modified by Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public abstract class AnimatedGraphicsObject extends Thread {

    /** Is the animation done, meaning the object can safely
    never be drawn again? */
    protected boolean done;

    /** The container on which we will call repaint after changes are made */
    protected JComponent container;

    /** delay time between frames of animation (ms)*/
    public static final int DELAY_TIME = 33;

    /** max allowed coordinates of play area*/
    public static final int LEFT_MAX = 0;
    public static final int RIGHT_MAX = 830;
    public static final int TOP_MAX = 0;
    public static final int BOTTOM_MAX = 600;

    /** type of animated object */
    protected int type;

    /** flag to tell if this object is dead or alive*/
    protected boolean dead;

    /** point to draw object from */
    protected Point upperLeft;

    /** status of the object */
    String status;
    /**
    Construct an AnimatedGraphicsObject.  All derived classes must
    call this as a superconstructor or explicitly set the container
    variable.

    @param container the Swing component on which we will need to
    call repaint when the object needs to be redrawn
     */
    public AnimatedGraphicsObject(JComponent container) {

        this.container = container;
    }

    /**
    A utility method to have the thread sleep without the need to
    place the call in a try-catch block.

    @param millis the number of milliseconds for the thread to sleep
     */
    protected static void sleepWithCatch(long millis) {

        try {
            sleep(millis);
        }
        catch (InterruptedException e) {}
    }

    /**
    Accessor method to check the value of the done variable.
    The done variable should only be set to true when it this
    object is guaranteed never to need to be painted again.

    @return whether this object's lifetime is done and can safely
    never be painted again
     */
    protected boolean done() {

        return done;
    }

    /**
     * Returns the current status of a bubble (start, grow, float, empty, pop)
     *
     * @return status the status of the bubble
     */
    public String getStatus(){
        return status;
    }

    /**
     * Sets the status of the bubble (start, grow, float, empty, pop)
     *
     * @param newStatus the new status to set the bubble to
     */
    public void setStatus(String newStatus){
        status = newStatus;
    }

    /**
     * Returns the current position of a bubble
     *
     * @return status the position of the bubble
     */
    public Point getPosition(){
        return upperLeft;
    }

    /**
    Required paint method that will be called when this graphical
    object needs to be drawn on the given Graphics object.

    @param g the Graphics object in which to draw
     */
    protected abstract void paint(Graphics g);

    /**
    Abstract run method to ensure that derived classes override
    the default provided by Thread.
     */
    @Override
    public abstract void run();
}

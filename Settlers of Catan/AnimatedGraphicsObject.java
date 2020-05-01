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
An abstract class that provides some instance variables and 
default versions of methods that are needed by threaded objects
representing animated graphics objects.

@author Jim Teresco modified by Kate Nelligan, Lindsay Clark, Adam Dachenhausen
@version Spring 2020
 */
public abstract class AnimatedGraphicsObject extends Thread {

    /** Is the animation done, meaning the object can safely
    never be drawn again? */
    protected boolean done;

    /** The container on which we will call repaint after changes are made */
    protected JComponent container;

    /** point to draw object from */
    protected Point upperLeft;

    // /** type of animated object */
    // protected String type;

    /** status of the object */
    String status;

    /** boolean flag to show or not show the game piece */
    protected boolean visible;

    /** delay time between frames of animation (ms)*/
    public static final int DELAY_TIME = 33;

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
    public static void sleepWithCatch(long millis) {

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
    public boolean done() {

        return done;
    }

    /**
    Accessor method to check the value of the done variable.
    The done variable should only be set to true when it this
    object is guaranteed never to need to be painted again.

    @return whether this object's lifetime is done and can safely
    never be painted again
     */
    protected void setDone(boolean newDone) {
        done = newDone;
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
     * Returns the current position 
     *
     * @return status the position of the bubble
     */
    public Point getPosition(){
        return upperLeft;
    }
    
        /**
     * Returns the current position 
     *
     * @return status the position of the bubble
     */
    public void setPosition(Point p){
        upperLeft = p;
    }

    public boolean getVisible(){
        return visible;
    }

    public void setVisible(boolean vis){
        visible = vis;
    }

    /**
    Required paint method that will be called when this graphical
    object needs to be drawn on the given Graphics object.

    @param g the Graphics object in which to draw
     */
    public abstract void paint(Graphics g);

    /**
    Abstract run method to ensure that derived classes override
    the default provided by Thread.
     */
    @Override
    public abstract void run();
}

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.image.*;
/**
 * Manages the life of a red ufo which either scrolls from 
 * left or right to the opposite edge of screen
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class AlienShip extends Thread implements ImageObserver{
    /** Is the animation done, meaning the object can safely
    never be drawn again? */
    protected boolean done;

    /** type of animated object */
    protected String type;

    /** flag to tell if this object is dead or alive*/
    protected boolean dead;

    /** point to draw object from */
    protected Point upperLeft;

    /** status of the object */
    String status;

    /** The container on which we will call repaint after changes are made */
    protected JComponent container;
    // Color of the ship/cannon
    protected Color ufoColor = new Color(255, 25, 0);

    /** delay time between frames of animation (ms)*/
    public static final int DELAY_TIME = 66;

    /** amount to move during animation*/
    public static final int MOVE_AMT = 5;

    private static Image ufoImage;
    private static Image explodeImage; 
    /**
     * Constructor for objects of class alienShip
     */
    public AlienShip(JComponent container)
    {
        this.upperLeft = upperLeft;

        this.container = container;
        status = "alive";
        done = false;
    }

    /**
     * Draw the alien at its current location.
     * 
     * @param g the Graphics object on which the alien should be drawn
     */
    public void paint(Graphics g) {

    }

    /**
     * This object's run method, which manages the life of the alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {
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

    // the method required by ImageObserver
    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    protected static void loadUfoPic(){
        // create the image that will be drawn by the
        // paintComponent method

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ufoImage = toolkit.getImage("ufo.png");

        explodeImage = toolkit.getImage("explode.png");
    }

    protected boolean isDead(){
        return dead;
    }
}

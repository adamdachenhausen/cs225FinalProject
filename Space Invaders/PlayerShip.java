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
 * Green cannon that player can move to the left or right
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class PlayerShip extends AnimatedGraphicsObject{
    // pixels to move each frame
    protected static final int SPEED = 2;

    // delay between each frame
    protected static final int DELAY_TIME = 33;

    // who do we live in so we can repaint?
    //private JComponent container;

    // current size of biggest ship/cannon rectangle
    protected int lgWidth = 48;

    // current size of medium ship/cannon rectangle
    protected int medWidth = 20;

    // current size of smallest ship/cannon rectangle
    protected int smWidth = 4;

    // current size of biggest ship/cannon rectangle
    protected int lgHeight = 12;

    // current size of medium ship/cannon rectangle
    protected int medHeight = 6;

    // current size of smallest ship/cannon rectangle
    protected int smHeight = 4;

    // latest location of the largest rectangle that makes the ship/cannon
    protected Point upperLeft;

    // Color of the ship/cannon
    protected Color shipColor = new Color(34, 204, 0);

    protected boolean done = false;

    protected int width;
    protected int height;

    String status;
    /**
     * Constructor for objects of class playerShip
     */
    public PlayerShip(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
        status = "alive";
        width = container.getWidth();
        height = container.getHeight();
    }

    /**
     * Draw the cannon/ship at its current location.
     * 
     * @param g the Graphics object on which the cannon should be drawn
     */
    @Override
    public void paint(Graphics g) {
        //(x, y, width, height)
        g.setColor(shipColor);
        g.fillRect(upperLeft.x, upperLeft.y,lgWidth, lgHeight);
        //g.setColor(Color.black);
        g.fillRect((upperLeft.x + lgWidth/2) - (medWidth/2), upperLeft.y - medHeight, medWidth, medHeight);
        //g.setColor(Color.red);
        g.fillRect((upperLeft.x + lgWidth/2) - (smWidth/2), upperLeft.y - (smHeight + medHeight), smWidth, smHeight);
    }

    /**
     * This object's run method, which manages the life of the ship/alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {
        container.repaint();
        while(getStatus().equals("alive")){
            upperLeft = getPosition();
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            container.repaint();
        }
        done = true;
    }

    /**
     * Returns the current status of a ship (alive/dead)
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
     * Returns the current position of a bubble
     *
     * @return status the position of the bubble
     */
    public Point setPosition(){
        return upperLeft;
    }

}

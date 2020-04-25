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
    //protected static final int SPEED = 4;

    protected int lives;

    // delay between each frame
    protected static final int DELAY_TIME = 33;

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

    // Color of the ship/cannon
    protected Color shipColor = new Color(34, 204, 0);

    protected int width;
    protected int height;

    /**
     * Constructor for objects of class playerShip
     */
    public PlayerShip(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
        status = "alive";
        width = container.getWidth();
        height = container.getHeight();
        type = "ship";
        done = false;
        lives = 3;
    }

    // /**
     // * Constructor for objects of class playerShip
     // */
    // public PlayerShip(Point upperLeft, JComponent container, int resurrect){
        // super(container);
        // this.upperLeft = upperLeft;
        // status = "alive";
        // width = container.getWidth();
        // height = container.getHeight();
        // type = "ship";
        // done = false;
        // lives = resurrect;
    // }

    /**
     * Draw the cannon/ship at its current location.
     * 
     * @param g the Graphics object on which the cannon should be drawn
     */
    @Override
    public void paint(Graphics g) {
        //(x, y, width, height)
        if(lives >= 3){
            g.setColor(Color.GREEN);
            g.fillRect(upperLeft.x, upperLeft.y,lgWidth, lgHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (medWidth/2), upperLeft.y - medHeight, medWidth, medHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (smWidth/2), upperLeft.y - (smHeight + medHeight), smWidth, smHeight);
        }else if(lives == 2){
            g.setColor(Color.YELLOW);
            g.fillRect(upperLeft.x, upperLeft.y,lgWidth, lgHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (medWidth/2), upperLeft.y - medHeight, medWidth, medHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (smWidth/2), upperLeft.y - (smHeight + medHeight), smWidth, smHeight);
        }else if(lives == 1){
            g.setColor(Color.RED);
            g.fillRect(upperLeft.x, upperLeft.y,lgWidth, lgHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (medWidth/2), upperLeft.y - medHeight, medWidth, medHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (smWidth/2), upperLeft.y - (smHeight + medHeight), smWidth, smHeight);
        }else{

            g.setColor(Color.BLACK);
            g.fillRect(upperLeft.x, upperLeft.y,lgWidth, lgHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (medWidth/2), upperLeft.y - medHeight, medWidth, medHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (smWidth/2), upperLeft.y - (smHeight + medHeight), smWidth, smHeight); 
        }
    }

    /**
     * This object's run method, which manages the life of the ship/alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {
        container.repaint();
        while(getStatus().equals("alive")){

            sleepWithCatch(DELAY_TIME);

            container.repaint();
        }
        done = true;
    }

    /**
     * Returns the current status of a ship (alive/dead)
     *
     * @return status the status of the ship
     */
    public int getWidth(){
        return lgWidth;
    }

    /**
     * Returns the current status of a ship (alive/dead)
     *
     * @return status the status of the ship
     */
    public int getHeight(){
        return lgHeight + medHeight + smHeight;
    }

    /**
     * Returns the current status of a ship (alive/dead)
     *
     * @return status the status of the ship
     */
    public String getStatus(){
        return status;
    }

    /**
     * Sets the status of the ship
     *
     * @param newStatus the new status to set the ship to
     */
    public void setStatus(String newStatus){
        status = newStatus;
    }

    /**
     * Returns the current position of a cannon
     *
     * @return status the position of the cannon
     */
    public Point getPosition(){
        return upperLeft;
    }

    /**
     * Returns the current position of a cannon
     *
     * @return status the position of the cannon
     */
    public int getLives(){
        return lives;
    }

    /**
     * Returns the current position of a cannon
     *
     * @return status the position of the cannon
     */
    public void setLives(int newLives){
        lives = newLives;
    }

    /**
     * Returns the current position of a cannon
     *
     * @return status the position of the cannon
     */
    public void setPosition(int newLives){
        lives = newLives;
    }

    /**
     * Returns the current position of a cannon
     *
     * @return status the position of the cannon
     */
    public Point setPosition(){
        return upperLeft;
    }

    /**
     * Returns the current position of a cannon
     *
     * @return status the position of the cannon
     */
    public Point getCannonPosition(){
        int x = (upperLeft.x + lgWidth/2) - (smWidth/2);
        int y = upperLeft.y - (smHeight + medHeight);
        Point cannon = new Point(x, y);
        return cannon;
    }

}

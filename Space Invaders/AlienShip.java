import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
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
public class AlienShip extends AnimatedGraphicsObject implements ImageObserver{
    // Color of the ship/cannon
    protected Color ufoColor = new Color(255, 25, 0);

    /** delay time between frames of animation (ms)*/
    public static final int DELAY_TIME = 22;

    private static Image ufoImage;
    private static Image explodeImage; 
    /**
     * Constructor for objects of class alienShip
     */
    public AlienShip(JComponent container, Point upperLeft)
    {
        super(container);
        this.upperLeft = upperLeft;

        //this.container = container;
        status = "alive";
        done = false;
    }

    /**
     * Draw the alien at its current location.
     * 
     * @param g the Graphics object on which the alien should be drawn
     */
    @Override
    public void paint(Graphics g) {
        if(!dead){
            g.drawImage(ufoImage, upperLeft.x, upperLeft.y, this);
        }
    }

    /**
     * This object's run method, which manages the life of the alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {
        int i = 0;
        //direction for ship to move;
        int moveAmt;
        if(upperLeft.x > 100){
            moveAmt = -5;
        }else{
            moveAmt = 5;
        }
        while(i < 50){
            container.repaint();
            upperLeft.x += moveAmt;
            sleepWithCatch(DELAY_TIME);
            container.repaint();
        }
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
    
        //SRC: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
    public static synchronized void playSound(String soundIn) {
        new Thread(new Runnable() {
                // The wrapper thread is unnecessary, unless it blocks on the
                // Clip finishing; see comments.
                public void run() {
                    try {
                        File soundFile = new File(soundIn);
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
                        clip.open(inputStream);
                        clip.start(); 
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }).start();
    }
}

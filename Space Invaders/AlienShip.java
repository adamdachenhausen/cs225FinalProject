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
    public static final int DELAY_TIME = 66;

    private static Image ufoImage;
    private static Image explodeImage; 
    /**
     * Constructor for objects of class alienShip
     * 
     * @param container what should I be drawn in?
     * @param upperLeft where should I be drawn?
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
        if(!done){
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
            if(upperLeft.x + 50 < 0){
                done = true;
            }
        }else{
            moveAmt = 5;
            if(upperLeft.x + 50 > 850){
                done = true;
            }
        }
        while(i < 50){
            container.repaint();
            upperLeft.x += moveAmt;
            sleepWithCatch(DELAY_TIME);
            container.repaint();
        }
        done = true;
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

    /** Returns the status of dead
     *  @return dead
     */
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

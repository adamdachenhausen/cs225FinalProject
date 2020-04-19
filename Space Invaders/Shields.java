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
 * Class responsible for creating shields for the game.
 * Each shield is comprised of 5 rectangles.  As the player
 * or alien enemies hit a rectangle on the shield it
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Shields extends AnimatedGraphicsObject{

    // individual rectangle for shield size
    public static final int SIZE = 25;

    // delay time between frames of animation (ms)
    public static final int DELAY_TIME = 33;

    // location of upperLeft rectangle, other rects drawn based on it's position
    private Point upperLeft;

    // Color of the shield
    protected Color shieldColor = new Color(34, 204, 0);
    /**
     * Constructor for objects of class Shield
     * Comprised of 5 points to make up filled rects
     *      
     */
    public Shields(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
    }

    /**
     * Draw the shield at its current location.
     * 
     * @param g the Graphics object on which the shield should be drawn
     */
    @Override
    public void paint(Graphics g) {

    }

    /**
     * This object's run method, which manages the life of the shield as it
     * bounces around the screen.
     */
    @Override
    public void run() {

    }
}

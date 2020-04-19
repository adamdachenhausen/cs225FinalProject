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
    private JComponent container;

    // current size of biggest ship/cannon rectangle
    protected int lgSize = 20;

    // current size of medium ship/cannon rectangle
    protected int medSize = 10;

    // current size of smallest ship/cannon rectangle
    protected int smSize = 2;

    // latest location of the ship/cannon
    protected Point upperLeft;

    // Color of the ship/cannon
    protected Color shipColor = new Color(34, 204, 0);


    protected boolean gameDone = false;
    /**
     * Constructor for objects of class playerShip
     */
    public PlayerShip(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
    }

    /**
     * Draw the cannon/ship at its current location.
     * 
     * @param g the Graphics object on which the alien should be drawn
     */
    @Override
    public void paint(Graphics g) {

    }

    /**
     * This object's run method, which manages the life of the ship/alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {

    }
}

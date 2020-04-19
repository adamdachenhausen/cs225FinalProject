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
 * Class responsible for creating and animating a single alien
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Alien extends AnimatedGraphicsObject{

    // Alien size
    public static final int SIZE = 50;

    // delay time between frames of animation (ms)
    public static final int DELAY_TIME = 33;

    // what to add to ySpeed to simulate gravity
    public static final double GRAVITY = 0.3;

    // pixels to move each iteration
    private double xSpeed, ySpeed;

    // latest location of the ball
    private double upperLeftX, upperLeftY;

    // max allowed coordinates of the upper left corner
    private int xMax, yMax;

    /**
     * Constructor for objects of class Alien
     *      
     */
    public Alien(Point startCenter,
    double xSpeed, double ySpeed,
    JComponent container){
        super(container);

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.yMax = container.getHeight() - SIZE;
        this.xMax = container.getWidth() - SIZE;
    }

    /**
     * Draw the alien at its current location.
     * 
     * @param g the Graphics object on which the alien should be drawn
     */
    @Override
    public void paint(Graphics g) {

    }

    /**
     * This object's run method, which manages the life of the alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {

    }
}

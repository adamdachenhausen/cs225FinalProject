import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Manages the life of a red ufo which either scrolls from 
 * left or right to the opposite edge of screen
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class AlienShip extends AnimatedGraphicsObject{
    // Color of the ship/cannon
    protected Color ufoColor = new Color(255, 25, 0);

    /**
     * Constructor for objects of class alienShip
     */
    public AlienShip(JComponent container)
    {
        super(container);
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

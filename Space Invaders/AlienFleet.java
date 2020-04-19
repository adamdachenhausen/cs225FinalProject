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
 * Class responsible for creating and managing all of the 
 * aliens attacking the player
 *
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark, 
 * @version Spring 2020
 */
public class AlienFleet extends AnimatedGraphicsObject{

    /**
     * Constructor for objects of class AlienFleet
     */
    public AlienFleet(JComponent container)
    {
        super(container);
        type = 1;
        done = false;
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

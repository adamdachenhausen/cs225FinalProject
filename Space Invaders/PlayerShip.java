import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class playerShip here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class PlayerShip extends Thread{

    // initial bubble size
    public static final int INITIAL_SIZE = 2;

    // pixels to move each frame
    protected static final int GROWTH_RATE = 2;

    // pixels to move each frame
    protected static final int SPEED = 2;

    // delay between each frame
    protected static final int DELAY_TIME = 33;

    // who do we live in so we can repaint?
    private JComponent container;

    // current size of bubble
    protected int size;

    // latest location of the bubble
    protected Point upperLeft;

    // Color of the bubble
    protected Color color;

    // current state of the bubble: start, grow, float, empty, pop
    protected String status;

    protected boolean game = false;
    /**
     * Constructor for objects of class playerShip
     */
    public PlayerShip()
    {

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sampleMethod(int y)
    {

    }
}

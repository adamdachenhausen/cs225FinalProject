import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class AlienFleet here.
 *
* @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark, 
* @version Spring 2020
 */
public class AlienFleet extends Thread{

    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class AlienFleet
     */
    public AlienFleet()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}

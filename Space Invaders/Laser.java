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
 * Write a description of class Laser here.
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Laser extends AnimatedGraphicsObject
{
    //Amount to translate every DELAY_TIME
    public static final int Y_SPEED = 4;

    /** Constructor for a laser
     *  @param container where this object should draw itself?
     *  @param upperLeft where does this object start?
     *  @param typeInt the type of laser this should be represented by
     *  an Int in a String representation
     */
    public Laser(JComponent container, Point upperLeft,String typeInt){
        super(container);
        this.upperLeft = upperLeft;
        done=false;
        type=typeInt;
    }

    @Override
    public void paint(Graphics g){
        if(!done){
            
        }
    }

    @Override
    public void run(){
        sleepWithCatch(DELAY_TIME);
        upperLeft.translate(0,Y_SPEED);
        container.repaint();
    }
}
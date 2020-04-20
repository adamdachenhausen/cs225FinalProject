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

    public Laser(JComponent container, Point upperLeft){
        super(container);
        this.upperLeft = upperLeft;
        done=false;
    }

    @Override
    public void paint(Graphics g){

    }

    @Override
    public void run(){
        sleepWithCatch(DELAY_TIME);
        upperLeft.translate(0,Y_SPEED);
        container.repaint();
    }
}

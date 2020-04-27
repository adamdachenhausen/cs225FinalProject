import javax.swing.Icon;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Write a description of class DevelopmentCards here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class DevelopmentCards extends AnimatedGraphicsObject{

    //Knight card (x14)- lets the player move the robber    
    //Road Building (x2)- player can place 2 roads as if they just built them
    //Year of Plenty (x2)- the player can draw 2 resource cards of their choice from the bank
    //Monopoly (x2)- player can claim all resource cards of a specific declared type
    //Victory Point card (x5)- 1 additional Victory Point is added to the owners total and doesn't need to be played to win.

    /**
     * Constructor for objects of class DevelopmentCards
     */
    public DevelopmentCards(JComponent container)
    {
        super(container);
    }

    @Override
    public void paint(Graphics g){
        //draw colored rectangle
        
        //paint image of card type icon
        
        //paint text that describes card type
    }

    @Override
    public void run(){

    }
}


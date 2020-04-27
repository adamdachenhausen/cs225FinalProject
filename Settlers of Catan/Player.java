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
 * Abstract class Player - write a description of the class here
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Player
{
    // instance variables - replace the example below with your own
    protected int playerNumber;
    protected Colors c;

    // Points for game--development is development cards
    protected int cities;
    protected int settlements;
    protected int roads;
    protected int development;
    protected int victoryPoints;
    protected int victoryPointCards;
    protected int knights;
    protected int roadLength;
    protected boolean largestArmy;
    protected boolean longestRoad;
    protected boolean turn = false;
    protected ArrayList<DevelopmentCards> devCards;
    protected ArrayList<ResourceCards> resourceCards;
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Player(){

    }
    
    public int getCities(){
        //if city is on hex border, you get two resource cards 
        //instead of one.
        return cities;
    }
    
    
}

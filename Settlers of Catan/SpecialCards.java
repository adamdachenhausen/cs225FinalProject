import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

/**
 * Write a description of class SpecialObjects here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class SpecialCards extends AnimatedGraphicsObject implements ImageObserver{


    //Knight card (x14)- lets the player move the robber    
    //Road Building (x2)- player can place 2 roads as if they just built them
    //Year of Plenty (x2)- the player can draw 2 resource cards of their choice from the bank
    //Monopoly (x2)- player can claim all resource cards of a specific declared type
    //Victory Point card (x5)- 1 additional Victory Point is added to the owners total and doesn't need to be played to win.

    // Constants for card numbers in deck


    //Special card constants
    public static final int LONGEST_ROAD = 1;
    public static final int LARGEST_ARMY = 1;


    private static Image road;    
    private static Image army;

    /**
     * Constructor for objects of class DevelopmentCards
     */
    public SpecialCards(JComponent container)
    {
        super(container);
        visible = false;
    }

    public void showCard(){
        visible = true;
    }

    @Override
    public void paint(Graphics g){
        // //draw colored rectangle

        // //paint image of card type icon
        // if(!done){
        // //draw image of card
        // if(value == 1){
        // g.drawImage(dice1, upperLeft.x , upperLeft.y, this);
        // }else if(value == 2){
        // g.drawImage(dice2, upperLeft.x , upperLeft.y, this);
     
        // //paint text that describes card type
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    protected static void loadPic(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        road = toolkit.getImage("road.png");
        army = toolkit.getImage("army.png");
    }

    @Override
    public void run(){

    }
}


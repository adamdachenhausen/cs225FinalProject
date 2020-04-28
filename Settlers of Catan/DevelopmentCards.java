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
 * Write a description of class DevelopmentCards here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class DevelopmentCards extends AnimatedGraphicsObject implements ImageObserver{

    //Knight card (x14)- lets the player move the robber    
    //Road Building (x2)- player can place 2 roads as if they just built them
    //Year of Plenty (x2)- the player can draw 2 resource cards of their choice from the bank
    //Monopoly (x2)- player can claim all resource cards of a specific declared type
    //Victory Point card (x5)- 1 additional Victory Point is added to the owners total and doesn't need to be played to win.

    // Constants for card numbers in deck
    public static final int KNIGHT = 14;
    public static final int ROAD_BUILDING = 2;
    public static final int YEAR_PLENTY = 2;
    public static final int MONOPOLY = 2;
    public static final int VICTORY_PT_CARD = 5;

    
    private static Image knight;    
    private static Image monopoly;
    private static Image plenty;
    private static Image victoryPoint;

    private static Image city;
    private static Image settlement;
    private static Image road;    
    private static Image army;
    private static Image robber;

    private static Image brick;
    private static Image grain;
    private static Image ore;
    private static Image lumber;
    private static Image wool;

    /**
     * Constructor for objects of class DevelopmentCards
     */
    public DevelopmentCards(JComponent container)
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
            // //draw image of explosion
            // if(value == 1){
                // g.drawImage(dice1, upperLeft.x , upperLeft.y, this);
            // }else if(value == 2){
                // g.drawImage(dice2, upperLeft.x , upperLeft.y, this);
            // }else if(value == 3){
                // g.drawImage(dice3, upperLeft.x , upperLeft.y, this);
            // }else if(value == 4){
                // g.drawImage(dice4, upperLeft.x , upperLeft.y, this);
            // }else if(value == 5){
                // g.drawImage(dice5, upperLeft.x , upperLeft.y, this);
            // }else{
                // //If alien
                // g.drawImage(dice6, upperLeft.x , upperLeft.y, this);
            // }
        // }
        // //paint text that describes card type
    }

    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public static Stack populateR(){
        Stack<Development> d = new Stack<Development>();

        //Add everything to r
        for(int i=0;i<KNIGHT;i++){
            d.add(Development.KNIGHT);
        }

        for(int i=0;i<ROAD_BUILDING;i++){
            d.add(Development.ROADS);
        }

        for(int i=0;i<YEAR_PLENTY;i++){
            d.add(Development.PLENTY);
        }

        for(int i=0;i<MONOPOLY;i++){
            d.add(Development.MONOPOLY);
        }

        for(int i=0;i<VICTORY_PT_CARD;i++){
            d.add(Development.VICTORY);
        }


        return d;
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
        // dice1 = toolkit.getImage("dice1.png");
        // dice2 = toolkit.getImage("dice2.png");
        // dice3 = toolkit.getImage("dice3.png");
        // dice4 = toolkit.getImage("dice4.png");
        // dice5 = toolkit.getImage("dice5.png");
        // dice6 = toolkit.getImage("dice6.png");

    }

    @Override
    public void run(){

    }
}


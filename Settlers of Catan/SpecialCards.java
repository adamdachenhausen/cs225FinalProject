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
 * Constructs special cards--each are worth 2 victory points.
 * 
 * The longest road card is set after 3 road segments are used aligned by a player
 * The largest army card is set after 3 knight cards are drawn by one player
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class SpecialCards extends AnimatedGraphicsObject implements ImageObserver{
    // Constants for card numbers in deck
    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 125;

    //Special card constants
    public static final int LONGEST_ROAD = 1;
    public static final int LARGEST_ARMY = 1;
    //image for the card
    private static Image road;    
    private static Image army;
    //type of the card
    private String type;
    //owner of the card
    private String owner;

    /**
     * Constructor for objects of class Special Cards
     * @param container what should I be drawn in?
     */
    public SpecialCards(JComponent container, Point upperLeft, String type)
    {
        super(container);

        this.type = type;
        this.upperLeft = upperLeft;
        visible = true;
    }

    /** Sets visible to true
     * 
     */
    public void showCard(){
        visible = true;
    }

    /** Sets visible to true
     * 
     */
    public void getOwner(){
        visible = true;
    }

    /** Sets visible to true
     * 
     */
    public void setOwner(String newOwner){
        owner = newOwner;
    }

    /** Paints the objects
     * 
     */
    @Override
    public void paint(Graphics g){

        if(visible){
            // //draw colored rectangle
            g.setColor(Color.YELLOW);

            g.fillRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);
            // g.setColor(Color.BLACK);
            // g.drawRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);

            Point picturePoint = new Point(upperLeft.x + 5, upperLeft.y + 15);
            // //paint image of card type icon
            switch(type){
                case MONOPOLY:
                g.drawImage(monopoly, picturePoint.x , picturePoint.y, this);
                break;

                case ROADBUILD:
                g.drawImage(road, picturePoint.x , picturePoint.y, this);
                break;

            }
        }
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


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
 * Constructs development cards for the game.  These cards are used to
 * develop your settlements faster by giving "power ups" to the user.
 * 
 * A knight moves the robber, steal 1 card from blocked player
 * a Year of plenty card yields  - gain two free resources
 * Monopoly  - choose one type of resource to take all from the other players
 * Road Building - build 2 free roads
 * Victory point cards - Gives the player 1 victory point

 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class DevelopmentCard extends AnimatedGraphicsObject implements ImageObserver{
    // Constants for card numbers in deck
    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 125;

    //types of images for cards
    private static Image knight;    
    private static Image monopoly;
    private static Image plenty;
    private static Image victoryPoint;
    private static Image road;    

    //variables to describe the type of card
    private String type;
    private Development cardType;
    /**
     * Constructor for objects of class DevelopmentCards
     * @param container what should I be drawn in?
     * @param d the type of card I am.
     * @param upperLeft where should I be drawn?
     */
    public DevelopmentCard(JComponent container, Development d, Point upperLeft){
        super(container);
        cardType = d;
        type = "";
        this.upperLeft = upperLeft;
        visible = true;
        setCardType();
    }

    /**
    /** Sets visible to true, when called
     * 
     */
    public void showCard(){
        visible = true;
    }

    /**
     * Returns the type of card
     *  @return type
     */
    public String getType(){
        return type;
    }

    /**
     * Paints the graphics to the graphics pane.
     */
    @Override
    public void paint(Graphics g){

        if(visible){
            // //draw colored rectangle
            g.setColor(Color.RED);

            g.fillRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);

            Point picturePoint = new Point(upperLeft.x + 5, upperLeft.y + 15);
            //paint image of card type icon
            switch(cardType){
                case MONOPOLY:
                g.drawImage(monopoly, picturePoint.x , picturePoint.y, this);
                break;

                case ROADBUILD:
                g.drawImage(road, picturePoint.x , picturePoint.y, this);
                break;

                case KNIGHT:
                g.drawImage(knight, picturePoint.x , picturePoint.y, this);
                break;

                case PLENTY:
                g.drawImage(plenty, picturePoint.x , picturePoint.y, this);
                break;

                case VICTORY:
                g.drawImage(victoryPoint, picturePoint.x , picturePoint.y, this);
                break;
            }

            // //paint text that describes card type
            String cardString;
            int x = 0;
            int y = 0;

            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            FontMetrics fm = g.getFontMetrics();
            switch(cardType){
                case MONOPOLY:
                cardString = "Monopoly";
                type = cardString;
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case ROADBUILD:
                cardString = "Road Building";
                type = cardString;
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case KNIGHT:
                cardString = "Knight";
                type = cardString;
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case PLENTY:
                cardString = "Year of Plenty";
                type = cardString;
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case VICTORY:
                cardString = "Victory Point";
                type = cardString;
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;
            }
        }
    }

    /** 
     * Sets the cardType of this card
     * @return the String type value of the card
     * 
     */
    public String setCardType(){
        String cardString;
        switch(cardType){
            case MONOPOLY:
            cardString = "Monopoly";
            type = cardString;

            break;

            case ROADBUILD:
            cardString = "Road Building";
            type = cardString;

            break;

            case KNIGHT:
            cardString = "Knight";
            type = cardString;

            break;

            case PLENTY:
            cardString = "Year of Plenty";
            type = cardString;

            break;

            case VICTORY:
            cardString = "Victory Point";
            type = cardString;

            break;
        }
        return type;
    }

    /**
     * Returns the type of card this is
     *  @return the type of card this is
     */
    public String getCardType(){
        return type;
    }

    /**
     * Required method for the imageObserver interface
     * @param img image
     * @param int infoflags
     * @param int x a position
     * @param int y a position
     * @returns true if it updates
     */
    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    /**
     * Loads the picture ahead of time so it loads properly in the panel
     * 
     */
    protected static void loadPic(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        knight = toolkit.getImage("knight.png");    
        monopoly = toolkit.getImage("monopoly.png");
        plenty = toolkit.getImage("plenty.png");
        victoryPoint = toolkit.getImage("victorypoint.png");
        road = toolkit.getImage("road.png");
    }

    /**
     * The required run method 
     * 
     */
    @Override
    public void run(){

    }
}


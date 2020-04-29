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
public class DevelopmentCard extends AnimatedGraphicsObject implements ImageObserver{

    //Knight card (x14)- lets the player move the robber    
    //Road Building (x2)- player can place 2 roads as if they just built them
    //Year of Plenty (x2)- the player can draw 2 resource cards of their choice from the bank
    //Monopoly (x2)- player can claim all resource cards of a specific declared type
    //Victory Point card (x5)- 1 additional Victory Point is added to the owners total and doesn't need to be played to win.
    // Constants for card numbers in deck
    
    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 125;
    
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
    private static Image road;    

    private Development cardType;
    /**
     * Constructor for objects of class DevelopmentCards
     */
    public DevelopmentCard(JComponent container, Development d, Point upperLeft){
        super(container);
        cardType = d;
        this.upperLeft = upperLeft;
        visible = true;
    }

    public void showCard(){
        visible = true;
    }
    

    @Override
    public void paint(Graphics g){

        if(visible){
            // //draw colored rectangle
            g.setColor(Color.RED);
            
            g.fillRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);
            // g.setColor(Color.BLACK);
            // g.drawRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);

            Point picturePoint = new Point(upperLeft.x + 5, upperLeft.y + 15);
            // //paint image of card type icon
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
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case ROADBUILD:
                cardString = "Road Building";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case KNIGHT:
                cardString = "Knight";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case PLENTY:
                cardString = "Year of Plenty";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case VICTORY:
                cardString = "Vitory Point";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
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
        knight = toolkit.getImage("knight.png");    
        monopoly = toolkit.getImage("monopoly.png");
        plenty = toolkit.getImage("plenty.png");
        victoryPoint = toolkit.getImage("victorypoint.png");
        road = toolkit.getImage("road.png");
    }

    @Override
    public void run(){

    }
}


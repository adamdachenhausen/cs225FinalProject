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
 * Write a description of class ResourceCards here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class ResourceCard extends AnimatedGraphicsObject implements ImageObserver{
    //Cards: Lumber(19x), Wool(19x), Grain(19x), Brick(19x), Ore(19x);
    // Constants for card numbers in deck
    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 125;

    private static Image brick;
    private static Image grain;
    private static Image ore;
    private static Image lumber;
    private static Image wool;

    private String type;
    private Resource cardType;
    /**
     * Constructor for objects of class ResourceCards
     */
    public ResourceCard(JComponent container, Resource r, Point upperLeft){
        super(container);
        cardType = r;

        this.upperLeft = upperLeft;
        visible = true;
    }

    public void showCard(){
        visible = true;
    }
    public String getType(){
        return type;
    }
    
    public Resource getCardType(){
        return cardType;
    }

    public void getColor(Graphics g){
        //Determine what color to make the tile based on its resource
        switch(cardType){
            case BRICKS:
            g.setColor(new Color(203, 65, 84));
            break;

            case WOOD:
            g.setColor(new Color(34, 139, 34));
            break;

            case ORE:
            g.setColor(new Color(149, 148, 139));
            break;

            case WHEAT:
            g.setColor(new Color(245, 222, 179));
            break;

            case WOOL:
            g.setColor(new Color(86, 125, 70));
            break;

            case SAND:
            g.setColor(new Color(194, 178, 128));
            break;
        }
    }

    @Override
    public void paint(Graphics g){

        if(visible){
            // //draw colored rectangle
            getColor(g);
            g.fillRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);
            // g.setColor(Color.BLACK);
            // g.drawRect(upperLeft.x,upperLeft.y, CARD_WIDTH, CARD_HEIGHT);

            Point picturePoint = new Point(upperLeft.x + 5, upperLeft.y + 15);
            // //paint image of card type icon
            switch(cardType){
                case BRICKS:
                g.drawImage(brick, picturePoint.x , picturePoint.y, this);
                break;

                case WOOD:
                g.drawImage(lumber, picturePoint.x , picturePoint.y, this);
                break;

                case ORE:
                g.drawImage(ore, picturePoint.x , picturePoint.y, this);
                break;

                case WHEAT:
                g.drawImage(grain, picturePoint.x , picturePoint.y, this);
                break;

                case WOOL:
                g.drawImage(wool, picturePoint.x , picturePoint.y, this);
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
                case BRICKS:
                cardString = "Brick";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case WOOD:
                cardString = "Lumber";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case ORE:
                cardString = "Ore";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case WHEAT:
                cardString = "Grain";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case WOOL:
                cardString = "Wool";
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

        brick = toolkit.getImage("brick.png");
        grain = toolkit.getImage("grain.png");
        ore = toolkit.getImage("ore.png");
        lumber = toolkit.getImage("lumber.png");
        wool = toolkit.getImage("wool.png");
    }

    @Override
    public void run(){

    }
}


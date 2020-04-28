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

    public static final int LUMBER = 19;
    public static final int WOOL = 19;
    public static final int GRAIN = 19;
    public static final int BRICK = 19;
    public static final int ORE = 19;

    private static Image brick;
    private static Image grain;
    private static Image ore;
    private static Image lumber;
    private static Image wool;

    private Resource cardType;
    /**
     * Constructor for objects of class ResourceCards
     */
    public ResourceCard(JComponent container, Resource r){
        super(container);
        cardType = r;
    }

    public void showCard(){
        visible = true;
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


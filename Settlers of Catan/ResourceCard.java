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
public class ResourceCard extends AnimatedGraphicsObject{
    //Cards: Lumber(19x), Wool(19x), Grain(19x), Brick(19x), Ore(19x);
    // Constants for card numbers in deck

    public static final int LUMBER = 19;
    public static final int WOOL = 19;
    public static final int GRAIN = 19;
    public static final int BRICK = 19;
    public static final int ORE = 19;

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
     * Constructor for objects of class ResourceCards
     */
    public ResourceCard(JComponent container){
        super(container);
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
        Stack<Resource> r = new Stack<Resource>();

        //Add everything to r
        for(int i=0;i<LUMBER;i++){
            r.add(Resource.BRICKS);
        }

        for(int i=0;i<WOOL;i++){
            r.add(Resource.WOOD);
        }

        for(int i=0;i<GRAIN;i++){
            r.add(Resource.WHEAT);
        }

        for(int i=0;i<BRICK;i++){
            r.add(Resource.WOOL);
        }

        for(int i=0;i<ORE;i++){
            r.add(Resource.WOOL);
        }

        return r;
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

        city = toolkit.getImage("city.png");
        settlement = toolkit.getImage("settlement.png");
        road = toolkit.getImage("road.png");
        army = toolkit.getImage("army.png");
        robber = toolkit.getImage("robber.png");

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


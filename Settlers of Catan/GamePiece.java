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
 * Write a description of class GamePieces here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class GamePiece  extends AnimatedGraphicsObject implements ImageObserver{
    final static protected int CITIES = 4; 
    final static protected int SETTLEMENTS = 5;
    final static protected int ROADS = 15;

    private static Image cities;
    private static Image settlements;
    private static Image roads;   
    
    String type;

    /**
     * Constructor for objects of class GamePieces
     */
    public GamePiece(JComponent container, String type)
    {
        super(container);
        this.type = type;
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
        Stack<Pieces> p = new Stack<Pieces>();

        //Add everything to r
        for(int i=0;i<CITIES;i++){
            p.add(Pieces.CITIES);
        }

        for(int i=0;i<SETTLEMENTS;i++){
            p.add(Pieces.SETTLEMENTS);
        }

        for(int i=0;i<ROADS;i++){
            p.add(Pieces.ROADS);
        }

        return p;
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
        
        cities = toolkit.getImage("city.png");
        settlements = toolkit.getImage("settlement.png");
        roads = toolkit.getImage("road.png");

    }

    @Override
    public void run(){

    }
}
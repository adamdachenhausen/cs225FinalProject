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
 * Write a description of class DevelopmentDeck here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class DevelopmentDeck extends AnimatedGraphicsObject{
    // Stores the cards
    ArrayList<DevelopmentCard> developmentDeck;

    // Constants for card numbers in deck
    public static final int KNIGHT = 14;
    public static final int ROAD_BUILDING = 2;
    public static final int YEAR_PLENTY = 2;
    public static final int MONOPOLY = 2;
    public static final int VICTORY_PT_CARD = 5;

    //temporary start point to draw the card
    private Point cardStartPoint = new Point(500,500);

    /**
     * Constructor for objects of class DevelopmentDeck
     */
    public DevelopmentDeck(JComponent container){
        super(container);
        developmentDeck = new ArrayList<DevelopmentCard>();
        populateDeck();
    }

    public ArrayList getList(){
        return developmentDeck;    
    }

    public void addCard(DevelopmentCard dc){
        developmentDeck.add(dc);

    }

    public DevelopmentCard removeCard(){
        DevelopmentCard removed = null;
        boolean found = false;
        int i = 0;

        if(i < developmentDeck.size()){
            removed = developmentDeck.remove(0);
        }
        return removed;
    }

    public DevelopmentCard getCard(String rcType){
        DevelopmentCard removed = null;
        boolean found = false;
        int i = 0;

        while(i < developmentDeck.size() && !found){
            if(rcType.equals(developmentDeck.get(i).getType())){
                found = true;
                removed =developmentDeck.get(i);
            }
            i++;
        }
        return removed;    
    }

    public boolean searchCards(String searchType){
        boolean found = false;
        int i = 0;

        while(i < developmentDeck.size() && !found){
            if(searchType.equals(developmentDeck.get(i).getType())){
                found = true;
            }
            i++;
        }
        return found;
    }

    @Override
    public void paint(Graphics g){

    }

    /** Populates the development arraylist with exact number of each development card
     *  Then shuffles the array, so when items are removed, they are random
     */
    public void populateDeck(){

        //Add everything to r
        for(int i=0;i<KNIGHT;i++){
            developmentDeck.add(new DevelopmentCard(container, Development.KNIGHT, cardStartPoint));
        }

        for(int i=0;i<ROAD_BUILDING;i++){
            developmentDeck.add(new DevelopmentCard(container, Development.ROADBUILD, cardStartPoint));
        }

        for(int i=0;i<YEAR_PLENTY;i++){
            developmentDeck.add(new DevelopmentCard(container, Development.PLENTY, cardStartPoint));
        }

        for(int i=0;i<MONOPOLY;i++){
            developmentDeck.add(new DevelopmentCard(container, Development.MONOPOLY, cardStartPoint));
        }

        for(int i=0;i<VICTORY_PT_CARD;i++){
            developmentDeck.add(new DevelopmentCard(container, Development.VICTORY, cardStartPoint));
        }
        Collections.shuffle(developmentDeck);

    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    @Override
    public void run(){

    }

}

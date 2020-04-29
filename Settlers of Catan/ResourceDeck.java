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
 * Write a description of class ResourceDeck here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class ResourceDeck extends AnimatedGraphicsObject{
    ArrayList<ResourceCard> resourceDeck;
    public static final int LUMBER = 19;
    public static final int WOOL = 19;
    public static final int GRAIN = 19;
    public static final int BRICK = 19;
    public static final int ORE = 19;
    
    private Point cardStartPoint = new Point(500,500);
    /**
     * Constructor for objects of class ResourceDeck
     */
    public ResourceDeck(JComponent container){
        super(container);
        new ArrayList<ResourceCard>();
    }

    public ArrayList getList(){
    return resourceDeck;    
    }
    
    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public void populateDeck(){

        //Add everything to r
        for(int i=0;i<LUMBER;i++){
            resourceDeck.add(new ResourceCard(container, Resource.WOOD, cardStartPoint));
        }

        for(int i=0;i<WOOL;i++){
            resourceDeck.add(new ResourceCard(container, Resource.WOOL, cardStartPoint));
        }

        for(int i=0;i<GRAIN;i++){
            resourceDeck.add(new ResourceCard(container, Resource.WHEAT, cardStartPoint));
        }

        for(int i=0;i<BRICK;i++){
            resourceDeck.add(new ResourceCard(container, Resource.BRICKS, cardStartPoint));
        }

        for(int i=0;i<ORE;i++){
            resourceDeck.add(new ResourceCard(container, Resource.ORE, cardStartPoint));
        }
        Collections.shuffle(resourceDeck);
        
    }

    @Override
    public void paint(Graphics g){

    }

    @Override
    public void run(){

    }
}

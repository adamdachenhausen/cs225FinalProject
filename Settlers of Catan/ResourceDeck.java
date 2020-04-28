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
    /**
     * Constructor for objects of class ResourceDeck
     */
    public ResourceDeck(JComponent container){
        super(container);
        new ArrayList<ResourceCard>();
    }

    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public static Stack populateR(){

        //Add everything to r
        for(int i=0;i<LUMBER;i++){
            resourceDeck.add(Resource.BRICKS);
        }

        for(int i=0;i<WOOL;i++){
            resourceDeck.add(Resource.WOOL);
        }

        for(int i=0;i<GRAIN;i++){
            resourceDeck.add(Resource.WHEAT);
        }

        for(int i=0;i<BRICK;i++){
            resourceDeck.add(Resource.BRICKS);
        }

        for(int i=0;i<ORE;i++){
            resourceDeck.add(Resource.ORE);
        }
        Collections.shuffle(r);
        return r;
    }

    @Override
    public void paint(Graphics g){

    }

    @Override
    public void run(){

    }
}

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
 * Write a description of class TokenStack here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class TokenStack extends AnimatedGraphicsObject{
    ArrayList<Tokens> tokenStack;
    
    public static final int NUM_TWO = 1;
    public static final int NUM_THREE = 2;
    public static final int NUM_FOUR = 2;
    public static final int NUM_FIVE =2;
    public static final int NUM_SIX = 2;
    public static final int NUM_EIGHT = 2;
    public static final int NUM_NINE = 2;
    public static final int NUM_TEN = 2;
    public static final int NUM_ELEVEN = 2;
    public static final int NUM_TWELVE = 1;
    public static final int TOTAL_TOKENS = 19;
    //Have to put this so we don't deal with null
    public static final int NUM_ZERO = 1;

    /**
     * Constructor for objects of class TokenStack
     */
    public TokenStack(JComponent container){
        super(container);
        new ArrayList<ResourceCard>();
    }

    public ArrayList getList(){
        return tokenStack;    
    }

    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public void populateDeck(){

        // //Add everything to r
        // for(int i=0;i<LUMBER;i++){
        // resourceDeck.add(new ResourceCard(container, Resource.WOOD, cardStartPoint));
        // }

        // for(int i=0;i<WOOL;i++){
        // resourceDeck.add(new ResourceCard(container, Resource.WOOL, cardStartPoint));
        // }

        // for(int i=0;i<GRAIN;i++){
        // resourceDeck.add(new ResourceCard(container, Resource.WHEAT, cardStartPoint));
        // }

        // for(int i=0;i<BRICK;i++){
        // resourceDeck.add(new ResourceCard(container, Resource.BRICKS, cardStartPoint));
        // }

        // for(int i=0;i<ORE;i++){
        // resourceDeck.add(new ResourceCard(container, Resource.ORE, cardStartPoint));
        // }
        // Collections.shuffle(resourceDeck);

    }

    @Override
    public void paint(Graphics g){

    }

    @Override
    public void run(){

    }
}

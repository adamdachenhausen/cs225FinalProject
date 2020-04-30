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
    Stack<Tokens> tokenStack;
    
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
        tokenStack = new Stack<Tokens>();
    }

    public Stack getList(){
        return tokenStack;    
    }

    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public void populateDeck(){
        //Stack t = new Stack<Token>();
        //Add everything to tokens
        for(int i=0;i<NUM_TWO;i++){
            tokenStack.add(Token.TWO);
        }
        for(int i=0;i<NUM_THREE;i++){
            tokenStack.add(Token.THREE);
        }
        for(int i=0;i<NUM_FOUR;i++){
            tokenStack.add(Token.FOUR);
        }
        for(int i=0;i<NUM_FIVE;i++){
            tokenStack.add(Token.FIVE);
        }
        for(int i=0;i<NUM_SIX;i++){
            tokenStack.add(Token.SIX);
        }
        for(int i=0;i<NUM_EIGHT;i++){
            tokenStack.add(Token.EIGHT);
        }
        for(int i=0;i<NUM_NINE;i++){
            tokenStack.add(Token.NINE);
        }
        for(int i=0;i<NUM_TEN;i++){
            tokenStack.add(Token.TEN);
        }
        for(int i=0;i<NUM_ELEVEN;i++){
            tokenStack.add(Token.ELEVEN);
        }
        for(int i=0;i<NUM_TWELVE;i++){
            tokenStack.add(Token.TWELVE);
        }
        for(int i=0;i<NUM_ZERO;i++){
            tokenStack.add(Token.ZERO);
        }
        Collections.shuffle(tokenStack);
        //return tokenStack;
        
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

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
 * Represents a stack of tokens, so that they can be randomized and easily handed
 * out.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class TokenStack extends AnimatedGraphicsObject{
    Stack<Tokens> tokenList;

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
     * Constructor for objects of class tokenList
     * @param container what should I be drawn in?
     */
    public TokenStack(JComponent container){
        super(container);
        tokenList = new Stack<Tokens>();

        //upperleft will change with call for placeToken();
        upperLeft = new Point(500,500);
    }

    /**
     * Constructor for objects of class tokenList
     * @param container what should I be drawn in?
     * @param upperLeft where should I be drawn?
     */
    public TokenStack(JComponent container,Point upperLeft){
        super(container);
        tokenList = new Stack<Tokens>();

        this.upperLeft = upperLeft;
    }

    /** Returns the stack of tokens
     *  @return the stack of tokens
     */
    public Stack getList(){
        return tokenList;    
    }

    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public void populateStack(){
        //Add everything to tokens
        //1 and 12 only get one token
        int tokenValue = 1;

        //need to fix upperleft call a place token method here
        //place token takes hex upperleft as input to place each token
        while(tokenValue <= 12){
            if(tokenValue == 1 || tokenValue == 2 || tokenValue == 12){
                Tokens t = new Tokens(container, upperLeft, tokenValue);
                tokenList.push(t);
            }else if(tokenValue == 7){
                //do nothing (maybe create robber here?)

            }else{
                for(int i = 0; i < 2; i++){
                    Tokens t = new Tokens(container, upperLeft, tokenValue);  
                    tokenList.push(t);
                }
            }

            tokenValue++;
        }

        Collections.shuffle(tokenList);
    }

    /**
     * Loops through the list and starts each token
     */
    public void startTokens(){
        for(Tokens t: tokenList){
            t.start();
        }
    }

    @Override
    public void paint(Graphics g){

    }

    @Override
    public void run(){

    }
}

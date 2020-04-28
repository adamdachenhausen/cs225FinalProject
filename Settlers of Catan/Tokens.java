import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Creates the tokens that go on top of each hex
 * Draws a circle with a number on it on top of each hex that 
 * represents the value for each dice roll.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Tokens extends AnimatedGraphicsObject{
    //1x"2" 2x"3" 2x"4" 2x"5" 2x"6" 2x"8" 2x"9" 2x"10" 2x"11" 1x"12"

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
     * Constructor for objects of class Tokens
     */
    public Tokens(JComponent container){
        super(container);
    }

    public static Stack populateT(){
        Stack t = new Stack<Token>();
        //Add everything to tokens
        for(int i=0;i<NUM_TWO;i++){
            t.add(Token.TWO);
        }
        for(int i=0;i<NUM_THREE;i++){
            t.add(Token.THREE);
        }
        for(int i=0;i<NUM_FOUR;i++){
            t.add(Token.FOUR);
        }
        for(int i=0;i<NUM_FIVE;i++){
            t.add(Token.FIVE);
        }
        for(int i=0;i<NUM_SIX;i++){
            t.add(Token.SIX);
        }
        for(int i=0;i<NUM_EIGHT;i++){
            t.add(Token.EIGHT);
        }
        for(int i=0;i<NUM_NINE;i++){
            t.add(Token.NINE);
        }
        for(int i=0;i<NUM_TEN;i++){
            t.add(Token.TEN);
        }
        for(int i=0;i<NUM_ELEVEN;i++){
            t.add(Token.ELEVEN);
        }
        for(int i=0;i<NUM_TWELVE;i++){
            t.add(Token.TWELVE);
        }
        for(int i=0;i<NUM_ZERO;i++){
            t.add(Token.ZERO);
        }
        return t;
    }

    @Override
    public void paint(Graphics g){
        
    }

    @Override
    public void run(){

    }
}
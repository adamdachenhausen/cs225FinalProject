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
    public static final int TOTAL_TOKENS = 18;

    private Stack tokens;
    /**
     * Constructor for objects of class Tokens
     */
    public Tokens(JComponent container){
        super(container);
        tokens = new Stack();
    }

    private void populateTokens(){
        //Add everything to tokens
        for(int i=0;i<NUM_TWO;i++){
            tokens.add(2);
        }
        for(int i=0;i<NUM_THREE;i++){
            tokens.add(3);
        }
        for(int i=0;i<NUM_FOUR;i++){
            tokens.add(4);
        }
        for(int i=0;i<NUM_FIVE;i++){
            tokens.add(5);
        }
        for(int i=0;i<NUM_SIX;i++){
            tokens.add(6);
        }
        for(int i=0;i<NUM_EIGHT;i++){
            tokens.add(8);
        }
        for(int i=0;i<NUM_NINE;i++){
            tokens.add(9);
        }
        for(int i=0;i<NUM_TEN;i++){
            tokens.add(10);
        }
        for(int i=0;i<NUM_ELEVEN;i++){
            tokens.add(11);
        }
        for(int i=0;i<NUM_TWELVE;i++){
            tokens.add(12);
        }
    }

    @Override
    public void paint(Graphics g){

    }

    @Override
    public void run(){

    }
}
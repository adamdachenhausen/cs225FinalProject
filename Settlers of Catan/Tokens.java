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

    final static protected int SIZE = 75;

    public static final int NUM_TWO = 2;
    public static final int NUM_THREE = 3;
    public static final int NUM_FOUR = 4;
    public static final int NUM_FIVE =5;
    public static final int NUM_SIX = 6;
    public static final int NUM_EIGHT = 8;
    public static final int NUM_NINE = 9;
    public static final int NUM_TEN = 10;
    public static final int NUM_ELEVEN = 11;
    public static final int NUM_TWELVE = 12;

    //Have to put this so we don't deal with null
    public static final int NUM_ZERO = 1;

    protected int tokenValue;

    protected Token t;

    /**
     * Constructor for objects of class Tokens
     * @param Point p a point to use to draw the circle for the token
     */
    public Tokens(JComponent container, Point p, int val){
        super(container);
        upperLeft = p;
        tokenValue = val;
        visible = true;
        getToken(tokenValue); 
    }

    public int getTokenValue(){

        return tokenValue;
    }

    public void getToken(int tokenValue){
        switch(tokenValue){
            case NUM_TWO:
            t = Token.TWO;
            break;

            case NUM_THREE:
            t = Token.THREE;
            break;

            case NUM_FOUR:
            t = Token.FOUR;
            break;

            case NUM_FIVE:
            t = Token.FIVE;
            break;

            case NUM_SIX:
            t = Token.SIX;
            break;

            case NUM_EIGHT:
            t = Token.EIGHT;
            break;

            case NUM_NINE:
            t = Token.NINE;
            break;

            case NUM_TEN:
            t = Token.TEN;
            break;

            case NUM_ELEVEN:
            t = Token.ELEVEN;
            break;

            case NUM_TWELVE:
            t = Token.TWELVE;
            break;
        }
    }

    @Override
    public void paint(Graphics g){
        if(visible){
            //draw oval that represents token
            g.setColor(Color.WHITE);

            g.fillOval(upperLeft.x, upperLeft.y, SIZE , SIZE);
            g.setColor(Color.BLACK);

            //draw border around oval
            g.drawOval(upperLeft.x, upperLeft.y, SIZE , SIZE);


            //paint number value on the token
            switch(tokenValue){
                case NUM_TWO:
                getNumberText(g);
                break;

                case NUM_THREE:
                getNumberText(g);
                break;

                case NUM_FOUR:
                getNumberText(g);
                break;

                case NUM_FIVE:
                getNumberText(g);
                break;

                case NUM_SIX:
                getNumberText(g);
                break;

                case NUM_EIGHT:
                getNumberText(g);
                break;

                case NUM_NINE:
                getNumberText(g);
                break;

                case NUM_TEN:
                getNumberText(g);
                break;

                case NUM_ELEVEN:
                getNumberText(g);
                break;

                case NUM_TWELVE:
                getNumberText(g);
                break;
            }

        }

    }
    public void getNumberText(Graphics g){
        String numberInfo;
        int x = 0;
        int y = 0;

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        FontMetrics fm = g.getFontMetrics();

        numberInfo = "" + tokenValue;
        x = (upperLeft.x + (SIZE - fm.stringWidth(numberInfo)) / 2);
        y = (upperLeft.y + fm.getAscent()) + 5;
        g.drawString(numberInfo, x, y);
    }

    @Override
    public void run(){

    }
}
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

    protected int tokenValue;

    /**
     * Constructor for objects of class Tokens
     * @param Point p a point to use to draw the circle for the token
     */
    public Tokens(JComponent container, Point p, int val){
        super(container);
        upperLeft = p;
        tokenValue = val;
        visible = true;
    }

    public int getTokenValue(){
        return tokenValue;
    }

    @Override
    public void paint(Graphics g){
        if(visible){
            // //draw oval that represents token
            g.setColor(Color.WHITE);

            g.fillOval(upperLeft.x, upperLeft.y, SIZE , SIZE);
            g.setColor(Color.BLACK);

            //draw border around oval
            g.drawOval(upperLeft.x, upperLeft.y, SIZE , SIZE);

            Point numberPoint = new Point(upperLeft.x + 5, upperLeft.y + 15);
            
            //paint number value on the token
            switch(tokenValue){
                case BRICKS:
                g.drawImage(brick, picturePoint.x , picturePoint.y, this);
                break;

                case WOOD:
                g.drawImage(lumber, picturePoint.x , picturePoint.y, this);
                break;

                case ORE:
                g.drawImage(ore, picturePoint.x , picturePoint.y, this);
                break;

                case WHEAT:
                g.drawImage(grain, picturePoint.x , picturePoint.y, this);
                break;

                case WOOL:
                g.drawImage(wool, picturePoint.x , picturePoint.y, this);
                break;

                case BRICKS:
                g.drawImage(brick, picturePoint.x , picturePoint.y, this);
                break;

                case WOOD:
                g.drawImage(lumber, picturePoint.x , picturePoint.y, this);
                break;

                case ORE:
                g.drawImage(ore, picturePoint.x , picturePoint.y, this);
                break;

                case WHEAT:
                g.drawImage(grain, picturePoint.x , picturePoint.y, this);
                break;

                case WOOL:
                g.drawImage(wool, picturePoint.x , picturePoint.y, this);
                break;
            }

            // //paint text that describes card type
            String cardString;
            int x = 0;
            int y = 0;

            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            FontMetrics fm = g.getFontMetrics();
            switch(cardType){
                case BRICKS:
                cardString = "Brick";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case WOOD:
                cardString = "Lumber";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case ORE:
                cardString = "Ore";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case WHEAT:
                cardString = "Grain";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;

                case WOOL:
                cardString = "Wool";
                x = (upperLeft.x + (CARD_WIDTH - fm.stringWidth(cardString)) / 2);
                y = (upperLeft.y + CARD_HEIGHT - fm.getAscent());
                g.drawString(cardString, x, y);
                break;
            }
        }
        // for(int i=0; i<GameBoard.board.length;i++){

        // for(int j=0; j<board[0].length;j++){
        // if(board[i][j]!= null){
        // Point tokenPt = board[i][j].getUpperLeft();

        // g.setColor(Color.WHITE);
        // g.fillOval(upperLeft.x, upperLeft.y, SIZE , SIZE);
        // g.setColor(Color.BLACK);
        // g.drawOval(upperLeft.x, upperLeft.y, SIZE , SIZE);
        // int x = upperLeft.x + SIZE/2 - (robber.getWidth(this)/2);
        // int y = upperLeft.y + SIZE/2 - (robber.getHeight(this)/2);
        // g.drawImage(robber, x, y, this);

        // String playerInfo;
        // int x = 0;
        // int y = 0;

        // g.setColor(BROWN);
        // g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        // FontMetrics fm = g.getFontMetrics();

        // playerInfo = "PLAYER: " + turn;
        // x = (upperLeft.x + (PANE_WIDTH - fm.stringWidth(playerInfo)) / 2);
        // y = (upperLeft.y + fm.getAscent()) + 5;
        // g.drawString(playerInfo, x, y);

        // }
        // }

        // }

    }

    @Override
    public void run(){

    }
}
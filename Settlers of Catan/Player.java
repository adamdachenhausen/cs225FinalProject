import javax.swing.Icon;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Abstract class Player - write a description of the class here
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Player
{
    //Constants
    public static final int MAX_ROADS = 15;
    public static final int MAX_SETTLEMENTS = 5;
    public static final int MAX_CITIES = 4;

    // instance variables - replace the example below with your own
    protected int playerNumber;
    protected Color c;
    protected boolean turn;

    // Gamepices for game
    protected int cities = 0;
    protected int settlements = 0;
    protected int roads = 0;

    //variables based on development cards
    protected int victoryPoints;
    protected int victoryPointCards = 0;
    protected int knights = 0;
    protected int roadLength = 0;

    //variables based on special victory point cards (worth 2 victory points)
    protected boolean largestArmy = false;
    protected boolean longestRoad = false;

    //arraylists to store cards
    protected ArrayList<DevelopmentCard> devCards;
    protected ArrayList<ResourceCard> resourceCards;
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Player(int playNum, Color color){
        playerNumber = playNum;
        c = color;
        turn = false;
        victoryPoints = 0;
        devCards = new ArrayList<DevelopmentCard>();
        resourceCards = new ArrayList<ResourceCard>();
    }

        public int getPlayerNumber(){

        return playerNumber;
    }
    
    public Color getColor(){

        return c;
    }

    public boolean getTurn(){

        return turn;
    }

    public int getCities(){

        return cities;
    }

    public int getSettlements(){

        return settlements;
    }

    public int getRoads(){

        return roads;
    }

    public int getVictoryPoints(){

        return victoryPoints;
    }

    public int getVictoryPointCards(){

        return victoryPointCards;
    }

    public int getKnights(){

        return knights;
    }

    public int getRoadLength(){

        return roadLength;
    }

    public boolean getLargestArmy(){

        return largestArmy;
    }

    public boolean getLongestRoad(){

        return longestRoad;
    }

    public void setTurn(boolean turnflag){
        turn = turnflag;
    }

    public void setCities(int newcities){

        cities = newcities;
    }

    public void setSettlements(int newsettle){

        settlements = newsettle;
    }

    public void setRoads(int newroads){

        roads = newroads;
    }

    public void setVictoryPoints(int vp){

        victoryPoints = vp;
    }

    public void setVictoryPointCards(int vpc){

        victoryPointCards = vpc;
    }

    public void setKnights(int newknights){
        knights = newknights;
    }

    public void setRoadlength(int rl){
        roadLength = rl;
    }

    public void setLargestArmy(boolean la){
        largestArmy = la;
    }

    public void setLongestRoad(boolean lr){

        longestRoad = lr;
    }


    public void setRoadLength(int newRoadLength){

         roadLength = newRoadLength;
    }



    public void getCities(int newcities){

        cities = newcities;
    }

    public void getSettlements(int newsettle){

        settlements = newsettle;
    }

    public void getRoads(int newroads){

        roads = newroads;
    }

    public void getVictoryPoints(int vp){

        victoryPoints = vp;
    }

    public void getVictoryPointCards(int vpc){

        victoryPointCards = vpc;
    }

    public void getKnights(int newknights){
        knights = newknights;
    }

    public void getRoadlength(int rl){
        roadLength = rl;
    }

    public void getLargestArmy(boolean la){
        largestArmy = la;
    }

    public void getLongestRoad(boolean lr){

        longestRoad = lr;
    }
}

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
    protected int cities;
    protected int settlements;
    protected int roads;

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
    protected ArrayList<String> devHand;
    protected ArrayList<String> resourceHand;
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
        resourceHand = new ArrayList<String>();
        devHand = new ArrayList<String>();
        cities = 0;
        settlements = 0;
        roads = 0;
    }

    public void addResourceCard(ResourceCard rc){
        String rcType = rc.getType();

        //adds resource card to hand
        resourceCards.add(rc);
        if(!resourceHand.contains(rcType)){
            resourceHand.add(rcType);
        }
    }

    public ResourceCard removeResourceCard(String rcType){
        ResourceCard removed = null;
        boolean found = false;
        int i = 0;

        while(i > resourceCards.size() && !found){
            if(rcType.equals(resourceCards.get(i).getType())){
                found = true;
                removed =resourceCards.get(i);
                resourceCards.remove(i);
            }
            i++;
        }

        //remove the card from rescource card options if it is no longer there.
        if(removed != null){
            if(!resourceHand.contains(rcType)){
                resourceHand.remove(rcType);
            }
        }

        return removed;
    }

    public ResourceCard getResourceCard(String rcType){
        ResourceCard foundCard = null;
        boolean found = false;
        int i = 0;

        while(i > resourceCards.size() && !found){
            if(rcType.equals(resourceCards.get(i).getType())){
                found = true;
                foundCard =resourceCards.get(i);
            }
            i++;
        }
        return foundCard;    
    }

    public boolean searchResourceCards(String searchType){
        boolean found = false;
        int i = 0;

        while(i > resourceCards.size() && !found){
            if(searchType.equals(resourceCards.get(i).getType())){
                found = true;
            }
            i++;
        }
        return found;
    }

    public DevelopmentCard getDevelopmentCard(String rcType){
        DevelopmentCard foundCard = null;
        boolean found = false;
        int i = 0;

        while(i > devCards.size() && !found){
            if(rcType.equals(devCards.get(i).getType())){
                found = true;
                foundCard = devCards.get(i);
            }
            i++;
        }
        return foundCard;    
    }

    public boolean searchDevCards(String searchType){
        boolean found = false;
        int i = 0;

        while(i > devCards.size() && !found){
            if(searchType.equals(devCards.get(i).getType())){
                found = true;
            }
            i++;
        }
        return found;
    }

    public void addDevelopmentCard(DevelopmentCard dc){
        String dcType = dc.getType();

        //adds development card to hand
        devCards.add(dc);

        if(!devHand.contains(dcType)){
            devHand.add(dcType);
        }

        devCards.add(dc);

    }

    public DevelopmentCard removeDevelopmentCard(String dcType){
        DevelopmentCard removed = null;
        boolean found = false;
        int i = 0;
        //get type to check card types held by player

        while(i > devCards.size() && !found){
            if(dcType.equals(devCards.get(i).getType())){
                found = true;
                removed =devCards.get(i);
                devCards.remove(i);
            }
            i++;
        }

        //remove the card from dev card options if it is no longer there.
        if(removed != null){
            if(!devHand.contains(dcType)){
                devHand.remove(dcType);
            }
        }
        return removed;
    }

    public ArrayList<String> getDevelopmentHand(){

        return devHand;
    }

    public ArrayList<String> getRescourceHand(){

        return resourceHand;
    }

    public int getPlayerNumber(){

        return playerNumber;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards(){

        return devCards;
    }

    public String getResourceToString(){
        StringBuilder s = new StringBuilder();
        String resCards;
        int brick = 0;
        int lumber= 0;
        int ore = 0;
        int wheat = 0;
        int sheep =0; 

        for(int i = 0; i < resourceCards.size(); i++){
            if(resourceCards.get(i).getCardType().equals("Brick")){
                brick++;
            }else if(resourceCards.get(i).getCardType().equals("Lumber")){
                lumber++;
            }else if(resourceCards.get(i).getCardType().equals("Ore")){
                ore++;
            }else if(resourceCards.get(i).getCardType().equals("Grain")){
                wheat++;
            }else if(resourceCards.get(i).getCardType().equals("Wool")){
                sheep++;
            }
        }
        s.append("Brick: " + brick + ", Lumber: " + lumber + ", Ore: " + ore+ ", Wheat: " +wheat+ ", Wool: " + sheep);

        resCards = s.toString();
        return resCards;
    }

    public String getDevToString(String toStringType){
        StringBuilder s = new StringBuilder();
        String dCards;
        int monopoly = 0;
        int rb= 0;
        int knight = 0;
        int yop = 0;
        int vp =0; 

        for(int i = 0; i < devCards.size(); i++){
            if(devCards.get(i).getCardType().equals("Monopoly")){
                monopoly++;
            }else if(devCards.get(i).getCardType().equals("Road Building")){
                rb++;
            }else if(devCards.get(i).getCardType().equals("Knight")){
                knight++;
            }else if(devCards.get(i).getCardType().equals("Year of Plenty")){
                yop++;
            }else if(devCards.get(i).getCardType().equals("Victory Point")){
                vp++;
            }
        }
        if(toStringType.equals("Full")){
            s.append("Monopoly: " + monopoly + ", Road Building: " + rb + ", Knights: " + knight+ ", Year of Plenty: " + yop +", Victory Point: " + vp);
        }else{
            s.append("Knights: " +knight);
        }
        dCards = s.toString();
        return dCards;
    }

    public String getPiecesToString(){
        return ("Settlements: " + settlements + ", Cities: " + cities + ", Roads: " + roads);
    }

    public String getColorToString(){
        String colString = "";
        if(getColor().equals(Color.RED)){
            colString = "Red";
        }else if(getColor().equals(Color.WHITE)){
            colString = "White";
        }else if(getColor().equals(Color.BLUE)){
            colString = "Blue";
        }else{
            colString = "Orange";
        }
        return colString;
    }

    public ArrayList<ResourceCard> getResourceCards(){
        return resourceCards;
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

}

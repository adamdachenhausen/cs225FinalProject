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
 * Creates a player object
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
    protected int armyPoints = 0;
    protected int roadPoints = 0;

    //variables based on special victory point cards (worth 2 victory points)
    protected boolean largestArmy = false;
    protected boolean longestRoad = false;

    //arraylists to store cards
    protected ArrayList<DevelopmentCard> devCards;
    protected ArrayList<ResourceCard> resourceCards;
    protected ArrayList<String> devHand;
    protected ArrayList<String> resourceHand;

    //The players roads and cities
    protected ArrayList<Road> myRoads;
    protected ArrayList<City> myCities;

    /**
     * Player constructor
     *
     * @param  playNum the player's number
     * @param  color the player's color
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

        myRoads = new ArrayList(MAX_ROADS);
        myCities = new ArrayList(MAX_CITIES + MAX_SETTLEMENTS);
    }

    /**
     * Adds resource cards to the player's hand and to the 
     * types of resource cards the player is holding
     * (resourceHand is used to create an array used to populate the 
     * dialog box with trade options)
     *
     * @param  rc a Resource card to add
     */
    public void addResourceCard(ResourceCard rc){
        String rcType = rc.getType();

        //adds resource card to hand
        resourceCards.add(rc);
        if(!resourceHand.contains(rcType)){
            resourceHand.add(rcType);
        }
    }

    /**
     * Removes resource cards to the player's hand and to the 
     * types of resource cards the player is holding
     *
     * @param  rc a Resource card to add
     */
    public ResourceCard removeResourceCard(String rcType){
        ResourceCard removed = null;
        boolean found = false;
        int i = 0;

        while(i < resourceCards.size() && !found){
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

    /**
     * Returns a resource card after searching through the deck.
     *
     * @param  rc a Resource card to add
     * @return foundCard the resource card if it is found
     */
    public ResourceCard getResourceCard(String rcType){
        ResourceCard foundCard = null;
        boolean found = false;
        int i = 0;

        while(i < resourceCards.size() && !found){
            if(rcType.equals(resourceCards.get(i).getType())){
                found = true;
                foundCard =resourceCards.get(i);
            }
            i++;
        }
        return foundCard;    
    }

    /**
     * Searches the cards to see if the parameter value string contains
     * the same type of card in the deck.
     *
     * @param  searchType the String used to search the cards
     * @return true if found, false otherwise
     */
    public boolean searchResourceCards(String searchType){
        boolean found = false;
        int i = 0;

        while(i < resourceCards.size() && !found){
            if(searchType.equals(resourceCards.get(i).getType())){
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Gets a development card if it is in the players possession.
     *
     * @param  foundcard the found development card or null if not found
     */
    public DevelopmentCard getDevelopmentCard(String rcType){
        DevelopmentCard foundCard = null;
        boolean found = false;
        int i = 0;

        while(i < devCards.size() && !found){
            if(rcType.equals(devCards.get(i).getType())){
                found = true;
                foundCard = devCards.get(i);
            }
            i++;
        }
        return foundCard;    
    }

    /**
     * Loops through the deck to find the appropriate dev card.
     *
     * @param  String searchtype to search the cards with
     */
    public boolean searchDevCards(String searchType){
        boolean found = false;
        int i = 0;

        while(i < devCards.size() && !found){
            if(searchType.equals(devCards.get(i).getType())){
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Adds a development card to the players hand
     *
     * @param  dc a Development card to add
     */
    public void addDevelopmentCard(DevelopmentCard dc){
        String dcType = dc.getType();

        //adds development card to hand
        devCards.add(dc);

        if(!devHand.contains(dcType)){
            devHand.add(dcType);
        }

        devCards.add(dc);

    }

    /**
     * Removes a development card from the user's hand.
     *
     * @param  dc a Development card to remove
     */
    public DevelopmentCard removeDevelopmentCard(String dcType){
        DevelopmentCard removed = null;
        boolean found = false;
        int i = 0;
        //get type to check card types held by player

        while(i < devCards.size() && !found){
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

    /**
     * Returns the development deck hand. Used to populate joption
     * panes that guide the player through the game (for trading)
     *
     * @returns  devHand the arraylist of dev card types
     */
    public ArrayList<String> getDevelopmentHand(){

        return devHand;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public ArrayList<String> getResourceHand(){

        return resourceHand;
    }

    /**
     * Returns player number
     *
     * @returns  the player number
     */
    public int getPlayerNumber(){

        return playerNumber;
    }

    /**
     * Returns the development cards held by the player 
     * (all cards not just types)
     *
     * @returns  devCards arraylist
     */
    public ArrayList<DevelopmentCard> getDevelopmentCards(){

        return devCards;
    }

    /**
     * Returns the tostring of the cards held
     *
     * @returns  the String value of the cards.
     */
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

    /**
     * Returns the development card to string
     *
     * @returns  the toString of the dev cards
     */
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

    /**
     * Returns the game piece toString
     *
     * @returns the string value for the gamepieces
     */
    public String getPiecesToString(){
        return ("Cities: " + cities + ", Roads: " + roads);
    }

    /**
     * Returns the color value of the player
     *
     * @returns  a string value of the color of the player pieces
     */
    public String getColorToString(){
        String colString = "";
        if(getColor().equals(Color.RED)){
            colString = "Red";
        }else if(getColor().equals(Color.BLACK)){
            colString = "Black";
        }else if(getColor().equals(Color.BLUE)){
            colString = "Blue";
        }else{
            colString = "Orange";
        }
        return colString;
    }

    /**
     * Checks the cities if they got upgraded
     *
     * 
     */
    protected void checkCitiesForUpdates(Point p){
        for(City c : myCities){
            if(p.distance(c.getCityPoint())<=GameBoard.USER_ERROR_TOL){
                c.update();
                break;
            }
        }
    }

    /**
     * The required paint method to draw pieces.
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void paint(Graphics g){
        Color cur = g.getColor();

        g.setColor(this.c);

        for(Road r : myRoads){
            r.paint(g);
        }
        for(City city : myCities){
            city.paint(g);
        }

        g.setColor(cur);
    }

    /**
     * Added the location of the city to myCities (player gamepiece)
     *
     */
    public void addLocation(City c){
        myCities.add(c);
    }

    /**
     * Adds a road to myRoads (player gamepiece)
     *
     */
    public void addRoad(Road r){
        myRoads.add(r);
    }

    /**
     * Returns the arraylist of ResourceCards
     *
     * @returns the ArrayList of resource cards
     */
    public ArrayList<ResourceCard> getResourceCards(){
        return resourceCards;
    }

    /**
     * Returns the color value of the player
     *
     * @returns  Color value of the player
     */
    public Color getColor(){
        return c;
    }

    /**
     * Returns true if it is someone's turn
     *
     * @returns  determines whose turn it is, returns 
     * true if it is player turn
     */
    public boolean getTurn(){

        return turn;
    }

    /**
     * Returns the cities of the player
     *
     * @returns  cities variable for cities owned by player
     */
    public int getCities(){

        return cities;
    }

    /**
     * Returns the arraylist of myCities
     *
     * @returns  the arraylist myCities
     */
    public ArrayList<City> getMyCities(){

        return myCities;
    }

    /**
     * Returns the settlements of the player
     *
     * @returns settlements
     */
    public int getSettlements(){
        return settlements;
    }

    /**
     * Returns the roads of the player
     *
     * @returns roads
     */
    public int getRoads(){

        return roads;
    }

    /**
     * Returns the roads list of the player
     *
     * @returns the roads list arraylist of the player
     */
    public ArrayList<Road> getMyRoads(){

        return myRoads;
    }

    /**
     * Returns the victory points of the player
     *
     * @returns the victory points total of the player
     */
    public int getVictoryPoints(){

        return victoryPoints;
    }

    /**
     * Returns if the user holds a victory points card
     *
     * @returns the number of vp cards the player holds
     */
    public int getVictoryPointCards(){

        return victoryPointCards;
    }

    /**
     * Returns the number of knights cards the player holds
     *
     * @returns the number of knights the player holds
     */
    public int getKnights(){

        return knights;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public int getRoadLength(){

        return roadLength;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public boolean getLargestArmy(){

        return largestArmy;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public boolean getLongestRoad(){

        return longestRoad;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setTurn(boolean turnflag){
        turn = turnflag;
    }

    public void setCities(int newcities){

        cities = newcities;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setSettlements(int newsettle){

        settlements = newsettle;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setRoads(int newroads){

        roads = newroads;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void addCities(){

        cities++;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void addSettlements(){

        settlements++;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void addRoads(){

        roads++;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setVictoryPoints(int vp){

        victoryPoints = vp;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setVictoryPointCards(int vpc){

        victoryPointCards = vpc;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setKnights(int newknights){
        knights = newknights;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setRoadlength(int rl){
        roadLength = rl;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setLargestArmy(boolean la){
        armyPoints = 2;
        largestArmy = la;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setLongestRoad(boolean lr){
        roadPoints = 2;
        longestRoad = lr;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void setRoadLength(int newRoadLength){
        roadLength = newRoadLength;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void updateCities(){
        cities = myCities.size();;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void updateRoads(){
        roads = myRoads.size();;
    }

    /**
     * Returns the resource card hand 
     *
     * @returns  resourceHand the arraylist of resource card types
     */
    public void updatePoints(){

        victoryPoints = cities + settlements +armyPoints + roadPoints;
    }
}

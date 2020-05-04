import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
/**
 * A collection of HexTiles in a specific pattern
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class GameBoard extends AnimatedGraphicsObject
{
    public static final Color BACKGROUND = new Color(225,225,225);
    public static final Color SEA = new Color(49, 159, 181);

    public static final int BOARD_WIDTH = 5;
    public static final int OFFSET = HexTiles.SIDE_LENGTH;

    public static final int HEX_SIZE = 136;
    public static final int TOKEN_SIZE = 54;
    public static final int ROBBER_SIZE = 76;

    //This is the polygon that constructs the hexagon sea
    public static final int SEA_SIDE_LENGTH = OFFSET * BOARD_WIDTH;

    //The amount of error that the user can be off of an object's 
    //exact point and still place that object
    public static final int USER_ERROR_TOL = 5;

    private JPanel panel;
    protected HexTiles[][] board;
    private Stack<Resource> r;
    private Stack<Tokens> t;

    //Create robber
    Robber robber;

    //The center point of this
    private Point center;

    private Polygon sea;

    protected CityLocations c;

    protected Roads roads;

    protected ArrayList<Point> locs;

    protected Player curPlayer;
    protected Player player1;
    protected Player player2;
    protected Player player3;
    protected Player player4;

    /** Constructor for GameBoard
     * @param container what should I be drawn in?
     * @param center where should I be drawn?
     */
    public GameBoard(JComponent container, Point center){
        super(container);
        this.container = container;
        this.center = center;
        robber = new Robber(container, new Point(0,0));
        c = new CityLocations(container);
        roads = new Roads(container);
        

        board=new HexTiles[BOARD_WIDTH][BOARD_WIDTH];
        r = Resources.populateR();

        //Create tokenStack, populate it and return it

        TokenStack tokenStack = new TokenStack(container);
        tokenStack.start();
        tokenStack.populateStack();

        t = tokenStack.getList();

        sea = Sea.createSea(center);

    }

    /** Given four players, updates the locally saved ones
     *  @param p1 player1 
     *  @param p2 player2 
     *  @param p3 player3 
     *  @param p4 player4 
     */
    public void updatePlayers(Player p1, Player p2, Player p3, Player p4){
        player1 = p1;
        player2 = p2;
        player3 = p3;
        player4 = p4;
    }

    /** Manually created board of hexTiles
     *  
     */
    protected void createBoard(){

        //CREATE ROW 0

        //Create left side hexagon
        board[0][0] = new HexTiles(panel,center, r.pop(),t.pop(),-(int)HexTiles.SIDE_LENGTH-54,-(int)HexTiles.SIDE_LENGTH*3-2, "CORNER", "TOP");
        board[0][0].createHexType();
        board[0][0].getToken().start();
        //Create middle hexagon
        board[0][1] = new HexTiles(panel,center, r.pop(),t.pop(),0,-(int)HexTiles.SIDE_LENGTH*3-2,null,null);
        board[0][1].createHexType();
        board[0][1].getToken().start();

        //Create right hexagon
        board[0][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH+54,-(int)HexTiles.SIDE_LENGTH*3-2, "CORNER", "TOP");
        board[0][2].createHexType();
        board[0][2].getToken().start();

        //CREATE ROW 1

        //Create left side
        board[1][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH*2-47,(int)-HexTiles.SIDE_SIDE_LENGTH+15,"INBETWEEN","TOPLEFT");
        board[1][0].createHexType();
        board[1][0].getToken().start();

        board[1][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH+7,(int)-HexTiles.SIDE_SIDE_LENGTH+15,null,null);
        board[1][1].createHexType();
        board[1][1].getToken().start();

        //Create right side
        board[1][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH-7,(int)-HexTiles.SIDE_SIDE_LENGTH+15,null,null);
        board[1][2].createHexType();
        board[1][2].getToken().start();

        board[1][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH*2+47,(int)-HexTiles.SIDE_SIDE_LENGTH+15,"INBETWEEN","TOPRIGHT");
        board[1][3].createHexType();
        board[1][3].getToken().start();

        //CREATE ROW 2

        //Create middle hexagon
        board[2][2] = new HexTiles(panel,center, r.pop(),t.pop(),null,null);
        board[2][2].createHexType();
        board[2][2].getToken().start();

        //Create hexagons to the left of center
        board[2][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)(-HexTiles.SIDE_SIDE_LENGTH-4) * 2,0,"MIDDLE","LEFT");
        board[2][0].createHexType();

        board[2][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_SIDE_LENGTH-4,0,null,null);
        board[2][1].createHexType();
        board[2][1].getToken().start();

        //Create hexagons to the right of center
        board[2][4] = new HexTiles(panel,center, r.pop(),t.pop(),(int)(HexTiles.SIDE_SIDE_LENGTH+4) * 2,0,"MIDDLE","RIGHT");
        board[2][4].createHexType();
        board[2][4].getToken().start();

        board[2][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_SIDE_LENGTH+4,0,null,null);
        board[2][3].createHexType();
        board[2][3].getToken().start();

        //CREATE ROW 3

        //Create left side
        board[3][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH*2-47,(int)HexTiles.SIDE_SIDE_LENGTH-15,"INBETWEEN","BOTTOMLEFT");
        board[3][0].createHexType();
        board[3][0].getToken().start();

        board[3][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH+7,(int)HexTiles.SIDE_SIDE_LENGTH-15,null,null);
        board[3][1].createHexType();
        board[3][1].getToken().start();

        //Create right side
        board[3][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH-7,(int)HexTiles.SIDE_SIDE_LENGTH-15,null,null);
        board[3][2].createHexType();
        board[3][2].getToken().start();

        board[3][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH*2+47,(int)HexTiles.SIDE_SIDE_LENGTH-15,"INBETWEEN","BOTTOMRIGHT");
        board[3][3].createHexType();
        board[3][3].getToken().start();

        //CREATE ROW 4

        //Create left side hexagon
        board[4][0] = new HexTiles(panel,center, r.pop(),t.pop(),-(int)HexTiles.SIDE_LENGTH-54,(int)HexTiles.SIDE_LENGTH*3+2,"CORNER","BOTTOM");
        board[4][0].createHexType();
        board[4][0].getToken().start();

        //Create middle hexagon
        board[4][1] = new HexTiles(panel,center, r.pop(),t.pop(),0,(int)HexTiles.SIDE_LENGTH*3+2,"SPECIAL","");
        board[4][1].createHexType();
        board[4][1].getToken().start();

        //Create right hexagon
        board[4][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH+54,(int)HexTiles.SIDE_LENGTH*3+2,"CORNER","BOTTOM");
        board[4][2].createHexType();
        board[4][2].getToken().start();

        findCitiesAndRoads();
        placeToken();
    }

    /** Given a player, updates the current player to p
     * @param p the player whose turn it is 
     */
    public void updateCurPlayer(Player p){
        curPlayer = p;
    }

    /**
     *  Just loops through each item in board and calls its paint method
     */
    @Override
    public void paint(Graphics g){
        Color cur = g.getColor();
        g.drawPolygon(sea);
        g.setColor(SEA);
        g.fillPolygon(sea);
        g.setColor(cur);
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    board[i][j].paint(g);
                }
            }

        }
        g.setColor(cur);
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    if(board[i][j].getToken() != null){
                        board[i][j].getToken().paint(g);
                    }
                }
            }

        }
        if(robber != null){
            if(robber.container != null){
                robber.paint(g);
            }
        }
        if(c != null){
            c.paint(g);
        }
        if(roads != null){
            roads.paint(g);
        }

        if(player1!=null){
            player1.paint(g);
        }
        if(player2!=null){
            player2.paint(g);
        }
        if(player3!=null){
            player3.paint(g);
        }
        if(player4!=null){
            player4.paint(g);
        }
    }

    @Override
    public void run(){

    }

    /**
     *  Loops through the double array and starts each hexTile if possible
     */
    public void startBoard(){
        //starts each hex run method
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    board[i][j].start();
                }
            }

        }
    }

    /**
     * This returns the point where the hex is located
     *
     * @return The return value
     */
    public Point getHexPosition(){
        return center;
    }

    /** Returns the size of a hexTile
     *  @return HEX_SIZE
     */
    public int getHexSize(){

        return HEX_SIZE;
    }

    /** Given a type, finds a hexTile of that type
     *  @param hexType the type of a hex to search for
     *  @return the point of a hex that matches hexType
     */
    public Point findHex(String hexType){
        Point hexPt = upperLeft;
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    if(board[i][j].getResourceType().equals(hexType)){
                        hexPt = board[i][j].getHexPoint();
                    }
                }
            }

        }
        return hexPt;
    }

    /** Given a specific tokenValue, searches for a hex and returns its resource value
     *  @param tokenValue the token value to search hexes for
     *  @return the resourceType of a hex with this token value
     * 
     */
    public String getHexResourceValue(int tokenValue){
        String hexResourceType = "";
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    if(board[i][j].getToken()!= null){
                        if(board[i][j].getToken().getTokenValue() == tokenValue){
                            hexResourceType = board[i][j].getResourceType();
                        }
                    }
                }
            }

        }
        return hexResourceType;
    }

    /**
     *  @return the gameboard represented as a double array
     */
    public HexTiles[][] getTiles(){
        return board;
    }

    /**
     *  @return the gameboard represented as a double array
     */
    public Player getCurPlayer(){
        return curPlayer;
    }

    /** Places tokens on the hexTiles
     * 
     */
    public void placeToken(){

        Point tokenPoint = new Point(0,0);
        Point robberPoint = new Point(0,0);
        Tokens movedToken = new Tokens(container, new Point(0,0), 0);
        Tokens searchToken = new Tokens(container, new Point(0,0), 0);
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    HexTiles h = board[i][j];
                    if(!board[i][j].getResourceType().equals("Desert")){
                        tokenPoint = findCenter(board[i][j].getHexPoint(), false);
                        board[i][j].getToken().setPosition(tokenPoint);
                        board[i][j].getToken().setPlaced(true);
                    }else{
                        if(board[i][j].getToken().getTokenValue()!=1){
                            movedToken = board[i][j].getToken();
                            board[i][j].removeToken();
                        }else{
                            board[i][j].removeToken();
                        }
                        robberPoint = findCenter(board[i][j].getHexPoint(), true);
                        robber.setPosition(robberPoint);
                        board[i][j].setRobber(true);
                        robber.start();
                    }
                }
            }

        }

        if(movedToken.getTokenValue() != 1){
            for(int i=0; i<board.length;i++){

                for(int j=0; j<board[0].length;j++){
                    if(board[i][j]!= null){
                        HexTiles h = board[i][j];
                        if(board[i][j].getToken() != null){
                            if(board[i][j].getToken().getTokenValue() == 1){
                                board[i][j].setToken(movedToken);
                                tokenPoint = board[i][j].getHexPoint();
                                board[i][j].getToken().setPosition(tokenPoint);
                            }
                        }
                    }
                }

            }
        }
    }

    /** Loops through the board, and for each calls their respective
     *  getCityLocations() and getRoads()
     * 
     */
    private void findCitiesAndRoads(){
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    locs = board[i][j].getCityLocations();
                    int tokenValue = board[i][j].getToken().getTokenValue();
                    ArrayList<Road> rds = board[i][j].getRoads();
                    for(int k = 0; k<locs.size();k++){
                        c.addLocation(new City(container,locs.get(k), tokenValue));
                    }
                    for(int k = 0; k<rds.size();k++){
                        roads.addRoad(rds.get(k));
                    }
                }
            }

        }
        //System.out.println();
    }

    /** Given a p and boolean, determines the center of a token
     *  @param p the point of the token
     *  @param isRobber a boolean flag if the token is a robber
     *  @return the center of this token
     */
    private Point findCenter(Point p, boolean isRobber){
        Point tPoint = p;
        if(!isRobber){

            tPoint.x = tPoint.x - ((TOKEN_SIZE/2));
            tPoint.y = tPoint.y - ((TOKEN_SIZE/2));
        }else{

            tPoint.x = tPoint.x - ((ROBBER_SIZE/2));
            tPoint.y = tPoint.y - ((ROBBER_SIZE/2));
        }
        return tPoint;
    }

    /** Returns the robber object
     *  @return the robber
     */
    public Robber getRobber(){
        return robber;
    }

    /** Returns the cityLocations
     *  @return the city locations
     */
    public CityLocations getCities(){
        return c;
    }

    /** Returns the roads List
     *  @return the roads
     */
    public Roads getRoadsList(){
        return roads;
    }

    /** Returns the locations of cities
     *  @return the locs
     */
    public ArrayList<Point> getAllPoints(){
        return locs;
    }

    /** Event handler for when a user clicks on the panel
     *  Since road midpoints and city locations have to be farther than
     *  USER_ERROR_TOL, running through an extra loop yields nothing
     * 
     *  @param p The point of click
     *  @param playerNum the current player's number
     */
    public String handleClick(Point p, int playerNum){
        List<City> citiesList = c.getLocations();
        List<Road> roadsList = roads.getRoadList();

        String added = "";

        //These two check the lists to see if the user clicked on a city or road, not
        //currently on the board
        for(City c : citiesList){

            if(p.distance(c.getCityPoint())<=USER_ERROR_TOL){
                c.update();
                curPlayer.addLocation(c);
                c.setOwner(playerNum);
                c.setPlaced(true);
                //return value to update player city count
                added = "City";
                this.c.removeLocation(c);
                break;
            }

        }
        for(Road r : roadsList){
            if(p.distance(r.midPoint())<=USER_ERROR_TOL){
                r.update();
                curPlayer.addRoad(r);

                //return value to update player road count
                added = "Road";
                this.roads.removeRoad(r);
                break;
            }
        }

        //But we still need to check if they wanted to upgrade their city to a settlement
        curPlayer.checkCitiesForUpdates(p);
        return added;
    }

}
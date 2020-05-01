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

    //This is the polygon that constructs the hexagon sea
    public static final int SEA_SIDE_LENGTH = OFFSET * BOARD_WIDTH;

    private JPanel panel;
    protected HexTiles[][] board;
    private Stack<Resource> r;
    private Stack<Tokens> t;

    private Stack<Tokens> tstack2;

    //The center point of this
    private Point center;

    private Polygon sea;
    public GameBoard(JComponent container, Point center){
        super(container);
        this.container = container;
        this.center = center;
        //panel = new JPanel();
        //panel.setBackground(SEA);

        board=new HexTiles[BOARD_WIDTH][BOARD_WIDTH];
        r = Resources.populateR();

        //Create tokenStack, populate it and return it

        TokenStack tokenStack = new TokenStack(container);
        tokenStack.start();
        tokenStack.populateStack();
        //tokenStack.startTokens();
        t = tokenStack.getList();

        //just testing if i can get the tokens to draw
        // TokenStack tokenStack2 = new TokenStack(container);
        // tokenStack2.start();
        // tokenStack2.populateStack();
        // tokenStack2.startTokens();
        // tstack2 = tokenStack2.getList();

        sea = Sea.createSea(center);
    }

    /**
     *  
     */
    protected void createBoard(){

        //CREATE ROW 0

        //Create left side hexagon
        board[0][0] = new HexTiles(panel,center, r.pop(),t.pop(),-(int)HexTiles.SIDE_LENGTH-54,-(int)HexTiles.SIDE_LENGTH*3-2);
        board[0][0].createHexType();
        board[0][0].getToken().start();
        //Create middle hexagon
        board[0][1] = new HexTiles(panel,center, r.pop(),t.pop(),0,-(int)HexTiles.SIDE_LENGTH*3-2);
        board[0][1].createHexType();
        board[0][1].getToken().start();
        
        //Create right hexagon
        board[0][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH+54,-(int)HexTiles.SIDE_LENGTH*3-2);
        board[0][2].createHexType();
        board[0][2].getToken().start();
        
        //CREATE ROW 1
        //Create left side
        board[1][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH*2-47,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        board[1][0].createHexType();
        board[1][0].getToken().start();
        
        board[1][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH+7,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        board[1][1].createHexType();
        board[1][1].getToken().start();
        
        //Create right side
        board[1][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH-7,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        board[1][2].createHexType();
        board[1][2].getToken().start();
        
        board[1][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH*2+47,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        board[1][3].createHexType();
        board[1][3].getToken().start();
        
        //CREATE ROW 2

        //Create middle hexagon
        board[2][2] = new HexTiles(panel,center, r.pop(),t.pop());
        board[2][2].createHexType();
        board[2][2].getToken().start();
        
        //Create hexagons to the left of center
        board[2][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)(-HexTiles.SIDE_SIDE_LENGTH-4) * 2,0);
        board[2][0].createHexType();
        
        board[2][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_SIDE_LENGTH-4,0);
        board[2][1].createHexType();
        board[2][1].getToken().start();
        
        //Create hexagons to the right of center
        board[2][4] = new HexTiles(panel,center, r.pop(),t.pop(),(int)(HexTiles.SIDE_SIDE_LENGTH+4) * 2,0);
        board[2][4].createHexType();
        board[2][4].getToken().start();
        
        board[2][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_SIDE_LENGTH+4,0);
        board[2][3].createHexType();
        board[2][3].getToken().start();
        
        //CREATE ROW 3
        //Create left side
        board[3][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH*2-47,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        board[3][0].createHexType();
        board[3][0].getToken().start();
        
        board[3][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH+7,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        board[3][1].createHexType();
        board[3][1].getToken().start();
        
        //Create right side
        board[3][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH-7,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        board[3][2].createHexType();
        board[3][2].getToken().start();
        
        board[3][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH*2+47,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        board[3][3].createHexType();
        board[3][3].getToken().start();
        
        //CREATE ROW 4

        //Create left side hexagon
        board[4][0] = new HexTiles(panel,center, r.pop(),t.pop(),-(int)HexTiles.SIDE_LENGTH-54,(int)HexTiles.SIDE_LENGTH*3+2);
        board[4][0].createHexType();
        board[4][0].getToken().start();
        
        //Create middle hexagon
        board[4][1] = new HexTiles(panel,center, r.pop(),t.pop(),0,(int)HexTiles.SIDE_LENGTH*3+2);
        board[4][1].createHexType();
        board[4][1].getToken().start();
        
        //Create right hexagon
        board[4][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH+54,(int)HexTiles.SIDE_LENGTH*3+2);
        board[4][2].createHexType();
        board[4][2].getToken().start();
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
    }

    @Override
    public void run(){

    }

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

    public int getHexSize(){

        return HEX_SIZE;
    }

    public Point findHex(String hexType){
        Point hexPt = upperLeft;
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    if(board[i][j].getHexType().equals(hexType)){
                        hexPt = board[i][j].getHexPoint();
                    }
                }
            }

        }
        return hexPt;
    }

    public HexTiles[][] getTiles(){
        return board;
    }

    public void placeToken(){
        ArrayList<Tokens> tokenArray = new ArrayList<Tokens>();
        int loopvar = 0;
        while(!tstack2.isEmpty()){
            tokenArray.add(tstack2.pop());
            loopvar++;
        }
        // for(int i = 0; i < tstack2.size(); i++){
        // tokenArray.add(tstack2.pop());
        // }
        Point tokenPoint = new Point(0,0);
        Point robberPoint = new Point(0,0);
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                if(board[i][j]!= null){
                    HexTiles h = board[i][j];
                    if(!board[i][j].getHexType().equals("Desert")){
                        tokenPoint = board[i][j].getHexPoint();
                        tokenArray.get(i).setPosition(tokenPoint);
                    }else{
                        robberPoint = board[i][j].getHexPoint();
                    }
                }
            }

        }
    }
}
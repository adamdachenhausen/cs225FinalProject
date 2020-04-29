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
/**
 * A collection of HexTiles in a specific pattern
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class GameBoard extends AnimatedGraphicsObject
{
    public static final Color BACKGROUND = new Color(100,100,100);
    public static final Color SEA = new Color(49, 159, 181);

    public static final int BOARD_WIDTH = 5;
    public static final int OFFSET = HexTiles.SIDE_LENGTH;

    //This is the polygon that constructs the hexagon sea
    public static final int SEA_SIDE_LENGTH = OFFSET * BOARD_WIDTH;

    private JPanel panel;
    protected HexTiles[][] board;
    private Stack<Resource> r;
    private Stack<Token> t;

    //The center point of this
    private Point center;

    private Polygon sea;
    public GameBoard(JComponent container, Point center){
        super(container);
        this.container = container;
        //panel = new JPanel();
        //panel.setBackground(SEA);

        board=new HexTiles[BOARD_WIDTH][BOARD_WIDTH];
        r = Resources.populateR();
        t = Tokens.populateT();
        this.center = center;

        sea = Sea.createSea(center);
    }

    /**
     *  
     */
    protected void createBoard(){
        
        //CREATE ROW 0
        
        //Create left side hexagon
        board[0][0] = new HexTiles(panel,center, r.pop(),t.pop(),-(int)HexTiles.SIDE_LENGTH-54,-(int)HexTiles.SIDE_LENGTH*3-2);
        
        //Create middle hexagon
        board[0][1] = new HexTiles(panel,center, r.pop(),t.pop(),0,-(int)HexTiles.SIDE_LENGTH*3-2);
        
        //Create right hexagon
        board[0][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH+54,-(int)HexTiles.SIDE_LENGTH*3-2);
        
        
        //CREATE ROW 1
        
        //Create left side
        board[1][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH*2-47,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        board[1][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH+7,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        
        //Create right side
        board[1][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH-7,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        board[1][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH*2+47,(int)-HexTiles.SIDE_SIDE_LENGTH+15);
        
        //CREATE ROW 2
        
        //Create middle hexagon
        board[2][2] = new HexTiles(panel,center, r.pop(),t.pop());

        //Create hexagons to the left of center
        board[2][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)(-HexTiles.SIDE_SIDE_LENGTH-4) * 2,0);
        board[2][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_SIDE_LENGTH-4,0);

        //Create hexagons to the right of center
        board[2][4] = new HexTiles(panel,center, r.pop(),t.pop(),(int)(HexTiles.SIDE_SIDE_LENGTH+4) * 2,0);
        board[2][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_SIDE_LENGTH+4,0);
        
        
        
        //CREATE ROW 3
        
        //Create left side
        board[3][0] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH*2-47,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        board[3][1] = new HexTiles(panel,center, r.pop(),t.pop(),(int)-HexTiles.SIDE_LENGTH+7,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        
        //Create right side
        board[3][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH-7,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        board[3][3] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH*2+47,(int)HexTiles.SIDE_SIDE_LENGTH-15);
        
        //CREATE ROW 4
        
        //Create left side hexagon
        board[4][0] = new HexTiles(panel,center, r.pop(),t.pop(),-(int)HexTiles.SIDE_LENGTH-54,(int)HexTiles.SIDE_LENGTH*3+2);
        
        //Create middle hexagon
        board[4][1] = new HexTiles(panel,center, r.pop(),t.pop(),0,(int)HexTiles.SIDE_LENGTH*3+2);
        
        //Create right hexagon
        board[4][2] = new HexTiles(panel,center, r.pop(),t.pop(),(int)HexTiles.SIDE_LENGTH+54,(int)HexTiles.SIDE_LENGTH*3+2);
        
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
}
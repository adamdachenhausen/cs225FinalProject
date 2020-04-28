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

    //The current point to place a new hex at
    private Point cur;

    //The top left point of this
    private Point topLeft;
    
    private Polygon sea;
    public GameBoard(JComponent container, Point topLeft){
        super(container);
        this.container = container;
        //panel = new JPanel();
        //panel.setBackground(SEA);

        board=new HexTiles[BOARD_WIDTH][BOARD_WIDTH];
        r = Resources.populateR();
        t = Tokens.populateT();
        this.topLeft = topLeft;
        cur = topLeft;
        
        sea = Sea.createSea(topLeft);
    }

    /**
     *  Loops through board[][] and depending on loop variables, either:
     *  -makes a null hex
     *  -makes a hex with appropriate params.
     */
    protected void createBoard(){

        for(int i=0; i<board.length;i++){
            boolean shifted = false;
            if(i>0){
                cur = new Point(topLeft.x,cur.y+OFFSET);
            }
            for(int j=0; j<board[0].length;j++){
                //Corners should not be drawn but cur should still be moved
                if(i==0 && (j==0 || j==4)){
                    board[i][j] = null;
                    cur = new Point(cur.x+OFFSET,cur.y);
                }
                else if(i==4 && (j==0 || j==4)){
                    board[i][j] = null;
                    cur = new Point(topLeft.x,cur.y);
                }
                //The last index of row 1, and 3 should not be drawn
                else if(i==4 && (j==1 || j==3)){
                    board[i][j] = null;
                    cur = new Point(topLeft.x,cur.y);
                }
                else{
                    //Determine if a x_offset is needed, and only shifts once
                    if((i==1 || i==3) && !shifted){
                        cur.translate(OFFSET,0);
                        shifted=true;
                    }
                    board[i][j] = new HexTiles(container,cur,r.pop(),t.pop());
                    cur.translate(OFFSET,0);
                }
            }
        }
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

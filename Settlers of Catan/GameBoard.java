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
/**
 * Write a description of class GameBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameBoard extends AnimatedGraphicsObject
{
    public static final Color SEA = new Color(49, 159, 181);

    public static final int NUM_BRICKS = 3;
    public static final int NUM_WOOD = 4;
    public static final int NUM_ORE = 3;
    public static final int NUM_WHEAT = 4;
    public static final int NUM_WOOL = 4;
    public static final int NUM_SAND = 1;
    public static final int NUM_RESOURCES = 19;
    public static final int BOARD_WIDTH = 5;
    public static final int OFFSET = HexTiles.SIDE_LENGTH;

    private JPanel panel;
    protected HexTiles[][] board;
    private Stack<Resource> r;
    private Point cur;
    private Point topLeft;
    public GameBoard(JComponent container, Point topLeft){
        super(container);
        this.container = container;
        panel = new JPanel();
        panel.setBackground(SEA);

        board=new HexTiles[BOARD_WIDTH][BOARD_WIDTH];
        r = new Stack<Resource>();
        populateR();
        this.topLeft = topLeft;
        cur = topLeft;
    }

    private void populateR(){

        //Add everything to r
        for(int i=0;i<NUM_BRICKS;i++){
            r.add(Resource.BRICKS);
        }

        for(int i=0;i<NUM_WOOD;i++){
            r.add(Resource.WOOD);
        }

        for(int i=0;i<NUM_ORE;i++){
            r.add(Resource.ORE);
        }

        for(int i=0;i<NUM_WHEAT;i++){
            r.add(Resource.WHEAT);
        }

        for(int i=0;i<NUM_WOOL;i++){
            r.add(Resource.WOOL);
        }

        for(int i=0;i<NUM_SAND;i++){
            r.add(Resource.SAND);
        }

        //Randomize r now, so we don't have to do this later
        Collections.shuffle(r);
    }

    private void createBoard(){

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
                    board[i][j] = new HexTiles(panel,cur,r.pop());
                    cur.translate(OFFSET,0);
                }
            }
        }

    }

    @Override
    public void paint(Graphics g){
        for(int i=0; i<board.length;i++){

            for(int j=0; j<board[0].length;j++){
                board[i][j].paint(g);
            }

        }
    }

    @Override
    public void run(){
        
    }
}

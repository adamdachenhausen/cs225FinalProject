import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

/**
 * Write a description of class Dice here.
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class StatusPane extends AnimatedGraphicsObject implements ImageObserver{
    //constants
    public static final Color BEIGE = new Color(249, 228,183);
    public static final Color LT_BROWN = new Color(207, 185,152);
    public static final Color BROWN = new Color(101, 67,33);
    public static final int PANE_WIDTH = 300;
    public static final int PANE_HEIGHT = 850;

    //what is happening in the game? dice roll, trading etc
    private String gamePhase;

    //which player's turn is it?
    private int turn;
    private int roll;
    private ArrayList<Player> p ;
    /**
     * Constructor for objects of class Dice
     */
    public StatusPane(JComponent container, String phase, int turn, int roll, ArrayList<Player>p){
        super(container);
        upperLeft = new Point(container.getWidth() - 300, 0);
        this.turn = turn;
        this.roll = roll;
        this.p = p;
    }

    @Override
    public void paint(Graphics g){

        g.setColor(BEIGE);
        g.fillRect(upperLeft.x,upperLeft.y, PANE_WIDTH, PANE_HEIGHT);

        g.setColor(LT_BROWN);
        g.fillRect(upperLeft.x,upperLeft.y, PANE_WIDTH, 35);  

        updateText(g);
        // if(!done){
        // //draw image of explosion
        // if(value == 1){
        // g.drawImage(dice1, upperLeft.x , upperLeft.y, this);
        // }else if(value == 2){
        // g.drawImage(dice2, upperLeft.x , upperLeft.y, this);
        // }else if(value == 3){
        // g.drawImage(dice3, upperLeft.x , upperLeft.y, this);
        // }else if(value == 4){
        // g.drawImage(dice4, upperLeft.x , upperLeft.y, this);
        // }else if(value == 5){
        // g.drawImage(dice5, upperLeft.x , upperLeft.y, this);
        // }else{
        // //If alien
        // g.drawImage(dice6, upperLeft.x , upperLeft.y, this);
        // }
        // }
    }

    public void updateText(Graphics g){
        //Variables to paint text
        String statusTitle;
        String playerInfo;
        int x = 0;
        int y = 0;

        g.setColor(BROWN);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        FontMetrics fm = g.getFontMetrics();

        //Title
        statusTitle = "GAME STATUS";
        x = (upperLeft.x + (PANE_WIDTH - fm.stringWidth(statusTitle)) / 2);
        y = (upperLeft.y + fm.getAscent()) + 5;
        g.drawString(statusTitle, x, y);

        //Turn status
        String currentTurn;
        if(turn == 0){
            currentTurn = "Game has not started.";
        }else{
            currentTurn = "Player " + turn + "'s turn."; 
        }
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        int x2 = (upperLeft.x + 3);
        int y2 = (upperLeft.y + 55);
        g.drawString(currentTurn, x2, y2);

        //Game status
        String currentRoll = "Dice roll: " + roll;
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        int x3 = (upperLeft.x + 3);
        int y3 = (y2 + 20);
        g.drawString(currentRoll, x3, y3);

        //player1Info
        String p1Status = "Player 1 " +"("+ p.get(0).getColor() +")" + " points: " + p.get(0).getVictoryPoints();
        //String p1Status = "Brick9: , Lumber9: , Ore9: , Wheat9: , Sheep9: " + p.get(0).getVictoryPoints();
        int x4 = (upperLeft.x + 3);
        int y4 = (y3 + 20);
        g.drawString(p1Status, x4, y4);

        //player1cards 
        g.setFont(new Font("TimesRoman", Font.BOLD, 12));
        String p1cards;
        p1cards = p.get(0).getResourceToString();
        int x5 = (upperLeft.x + 3);
        int y5 = (y3 + 20);
        g.drawString(p1cards, x5, y5);

        //player1pieces
        g.setFont(new Font("TimesRoman", Font.BOLD, 12));
        String p1pieces;
        p1cards = p.get(0).getResourceToString();
        //int x5 = (upperLeft.x + 3);
        //int y5 = (y3 + 20);
        g.drawString(p1cards, x5, y5);

        //player2Info
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        String p2Status = "Player 2 points: " + p.get(1).getVictoryPoints();
        int x6 = (upperLeft.x + 3);
        int y6 = (y4 + 20);
        g.drawString(p2Status, x6, y6);

        //player3Info 
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        String p3Status = "Player 3 points: " + p.get(2).getVictoryPoints();
        //int x6 = (upperLeft.x + 3);
        //int y6 = (y5 + 20);
        g.drawString(p3Status, x6, y6);

        //player4Info 
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        String p4Status = "Player 4 points: " + p.get(3).getVictoryPoints();
        int x7 = (upperLeft.x + 3);
        int y7 = (y6 + 20);
        g.drawString(p4Status, x7, y7);

        g.setFont(new Font("TimesRoman", Font.BOLD, 12));
        container.repaint();
        //Player Instructions text
    }

    @Override
    public void run(){

        container.repaint();
        // sleepWithCatch(DELAY_TIME);
        // container.repaint();

    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    protected static void loadPic(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();

    }
}

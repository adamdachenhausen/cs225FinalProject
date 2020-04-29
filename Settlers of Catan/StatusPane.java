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

    /**
     * Constructor for objects of class Dice
     */
    public StatusPane(JComponent container, String phase, int turn){
        super(container);
        upperLeft = new Point(container.getWidth() - 300, 0);
        this.turn = turn;

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
        String playerInfo;
        int x = 0;
        int y = 0;

        g.setColor(BROWN);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        FontMetrics fm = g.getFontMetrics();

        playerInfo = "Brick";
        x = (upperLeft.x + (PANE_WIDTH - fm.stringWidth(playerInfo)) / 2);
        y = (upperLeft.y + fm.getAscent()) + 5;
        g.drawString(playerInfo, x, y);


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

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
    // instance variables - replace the example below with your own

    private static Image dice1;
    private static Image dice2;
    private static Image dice3;
    private static Image dice4;
    private static Image dice5;
    private static Image dice6;

    private String gamePhase;
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
        g.fillRect(upperLeft.x,upperLeft.y, PANE_WIDTH, 25);  
        
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
        //Player text
        Point startText = upperLeft;
        FontMetrics fm = g.getFontMetrics();
        String player = "PLAYER " + turn;
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x = (container.getWidth() - fm.stringWidth(player)) / 2;
        int y = (upperLeft.y + fm.getAscent() + 20);
        g.setColor(BROWN);
        g.drawString(player, x, y);
        
        container.repaint();

        //Player Instructions text
    }

    @Override
    public void run(){

        while(!done){
            container.repaint();
            sleepWithCatch(DELAY_TIME);
            container.repaint();

        }

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
        dice1 = toolkit.getImage("dice1.png");
        dice2 = toolkit.getImage("dice2.png");
        dice3 = toolkit.getImage("dice3.png");
        dice4 = toolkit.getImage("dice4.png");
        dice5 = toolkit.getImage("dice5.png");
        dice6 = toolkit.getImage("dice6.png");

    }

}

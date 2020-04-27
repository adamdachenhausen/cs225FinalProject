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
    public static final Color BROWN = new Color(101, 67,33);
    public static final int PANE_WIDTH = 200;
    public static final int PANE_HEIGHT = 850;
    // instance variables - replace the example below with your own
    protected int value;
    private static Image dice1;
    private static Image dice2;
    private static Image dice3;
    private static Image dice4;
    private static Image dice5;
    private static Image dice6;

    /**
     * Constructor for objects of class Dice
     */
    public StatusPane(JComponent container){
        super(container);
        upperLeft = new Point(container.getWidth() - 200, 0);
    }

    @Override
    public void paint(Graphics g){

        g.setColor(BEIGE);
        g.fillRect(upperLeft.x,upperLeft.y, PANE_WIDTH, PANE_HEIGHT);
        if(!done){
            //draw image of explosion
            if(value == 1){
                g.drawImage(dice1, upperLeft.x , upperLeft.y, this);
            }else if(value == 2){
                g.drawImage(dice2, upperLeft.x , upperLeft.y, this);
            }else if(value == 3){
                g.drawImage(dice3, upperLeft.x , upperLeft.y, this);
            }else if(value == 4){
                g.drawImage(dice4, upperLeft.x , upperLeft.y, this);
            }else if(value == 5){
                g.drawImage(dice5, upperLeft.x , upperLeft.y, this);
            }else{
                //If alien
                g.drawImage(dice6, upperLeft.x , upperLeft.y, this);
            }
        }
    }

    public int rollDice(){
        Random r = new Random();
        value = r.nextInt(5) + 1;
        return value;
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

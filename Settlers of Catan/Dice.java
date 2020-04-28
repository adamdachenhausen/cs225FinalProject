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
public class Dice extends AnimatedGraphicsObject implements ImageObserver{

    // instance variables - replace the example below with your own
    protected int value;
    protected boolean visible;
    private static Image dice1;
    private static Image dice2;
    private static Image dice3;
    private static Image dice4;
    private static Image dice5;
    private static Image dice6;

    /**
     * Constructor for objects of class Dice
     */
    public Dice(JComponent container, Point ul){
        super(container);
        upperLeft = ul;
    }

    @Override
    public void paint(Graphics g){
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

    public boolean getVisible(){
        return visible;
    }

    public void setVisible(boolean vis){
        visible = vis;
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

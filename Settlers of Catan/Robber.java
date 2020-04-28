import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Write a description of class GamePieces here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Robber  extends AnimatedGraphicsObject implements ImageObserver{

    final static protected int ROBBER = 1;


    private static Image robber;

    /**
     * Constructor for objects of class GamePieces
     */
    public Robber(JComponent container)
    {
        super(container);
    }

    public void showCard(){
        visible = true;
    }

    @Override
    public void paint(Graphics g){
        // //draw colored rectangle

        // //paint image of card type icon
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
        // //paint text that describes card type
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
        knight = toolkit.getImage("knight.png");    
        monopoly = toolkit.getImage("monopoly.png");
        plenty = toolkit.getImage("plenty.png");
        victoryPoint = toolkit.getImage("victorypoint.png");

        city = toolkit.getImage("city.png");
        settlement = toolkit.getImage("settlement.png");
        road = toolkit.getImage("road.png");
        army = toolkit.getImage("army.png");
        robber = toolkit.getImage("robber.png");

        brick = toolkit.getImage("brick.png");
        grain = toolkit.getImage("grain.png");
        ore = toolkit.getImage("ore.png");
        lumber = toolkit.getImage("lumber.png");
        wool = toolkit.getImage("wool.png");
    }

    @Override
    public void run(){

    }
}
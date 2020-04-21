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
 * One of 4 types of laser shots.
 * It either will hit an alien, shield, or the top of the gameboard.
 * One of those will cause it to explode on impact.
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Laser extends AnimatedGraphicsObject
{
    //Amount to translate every DELAY_TIME
    public static final int Y_SPEED = 4;

    protected LaserShot shot;
    /** Constructor for a laser
     *  @param container where this object should draw itself?
     *  @param upperLeft where does this object start?
     *  @param typeIn the type of laser this should be represented by
     *  "PLAYER" if this should be a player made laser, and thus ascend up the screen
     *  "ALIEN" if this should be an alien made laser, and thus descend down the screen
     */
    public Laser(JComponent container, Point upperLeft,String typeIn){
        super(container);
        this.upperLeft = upperLeft;
        done=false;
        type=typeIn;
        shot = new LaserShot(upperLeft,type);
    }

    @Override
    public void paint(Graphics g){
        if(!dead){

        }
        else if(!done){
            //draw image of explosion
        }
        else{
            //do nothing
        }
    }

    @Override
    public void run(){
        sleepWithCatch(DELAY_TIME);
        if(!done){
            if(type.equals("PLAYER")){
                upperLeft.translate(0,-Y_SPEED);
                shot.updateUpperLeft(upperLeft);
            }
            else{
                upperLeft.translate(0,Y_SPEED);
                shot.updateUpperLeft(upperLeft);
            }
        }
        container.repaint();
    }


}
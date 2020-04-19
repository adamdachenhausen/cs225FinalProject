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
 * A single section of a shield, upon being hit once, it first will breakdown,
 * then it will dissapear.
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class ShieldSection extends AnimatedGraphicsObject
{
    // delay time between frames of animation (ms)
    public static final int DELAY_TIME = 33;

    // individual rectangle for shield size
    public static final int SIZE = 25;

    // Color of the shield
    protected Color shieldColor = new Color(34, 204, 0);

    //Am I dead or alive?
    protected boolean dead;

    //Am I damaged?
    protected boolean damaged;

    //Where am I?
    protected Point upperLeft;

    //The black squares to color in to show damage (damage indicated by a true value)
    private boolean[][] damagePoints;
    public ShieldSection(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
        dead = false;
        damaged = false;
        damagePoints = new boolean[SIZE/5][SIZE/5];

        //Calculate the damage on init
        hurt();
    }

    @Override
    public void paint(Graphics g){
        if(!dead && !damaged){
            //Draw one big full rectangle
            g.setColor(shieldColor);
            g.fillRect(upperLeft.x,upperLeft.y,SIZE,SIZE);
        }
        else if(!dead){
            //Draw a 5x5 matrix of smaller rectangles
            for(int i=0; i<damagePoints.length;i++){
                for(int j=0; j<damagePoints.length;j++){
                    if(damagePoints[i][j]){
                        g.setColor(Color.BLACK);
                        g.fillRect(upperLeft.x+5*i,upperLeft.y+5*j,SIZE/5,SIZE/5);
                    }
                    else{
                        g.setColor(shieldColor);
                        g.fillRect(upperLeft.x+5*i,upperLeft.y+5*j,SIZE/5,SIZE/5);
                    }
                }
            }
        }
        else{
            //Don't draw a rectangle
        }
    }

    private void hurt(){
        Random rand = new Random();

        //Generate a random anywhere from 5 to 15 inclusive aka SIZE/5 to (3*SIZE)/5
        int maxDamage = rand.nextInt(11)+5;
        while(maxDamage>=0){
            //Select a random point in the double array to set damaged
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);

            //Set it to true, unless it is already, then just do nothing
            if(damagePoints[x][y]){
                //Do nothing
            }
            else{
                maxDamage--;
                damagePoints[x][y] = true;
            }
        }
    }

    @Override
    public void run(){

    }
}

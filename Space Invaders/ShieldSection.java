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
    // individual rectangle for shield size
    public static final int SIZE = 25;

    // Color of the shield
    protected Color shieldColor = new Color(34, 204, 0);

    //Am I dead or alive?
    protected boolean dead;

    //Am I damaged? If so, to what extent
    protected int damaged;

    //How much damage have I sustained
    private int maxDamage;

    //The black squares to color in to show damage (damage indicated by a true value)
    private boolean[][] damagePoints;
    /** Constructor for ShieldSection
     *  @param upperLeft where should I be?
     *  @param container what should I be drawn in?
     * 
     */
    public ShieldSection(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
        dead = false;
        damaged = 0;
        maxDamage = 5;
        damagePoints = new boolean[SIZE/5][SIZE/5];

    }

    @Override
    public void paint(Graphics g){
        //This is to set the color back to whatever it was before this paint was 
        //called.
        Color cur = g.getColor();
        if(this != null){
            if(!dead && damaged==0){
                //Draw one big full rectangle
                g.setColor(shieldColor);
                g.fillRect(upperLeft.x,upperLeft.y,SIZE,SIZE);
                g.setColor(cur);
            }
            else if(!dead){
                //Draw a 5x5 matrix of smaller rectangles
                for(int i=0; i<damagePoints.length;i++){
                    for(int j=0; j<damagePoints.length;j++){
                        if(damagePoints[i][j]){
                            g.setColor(Color.BLACK);
                            g.fillRect(upperLeft.x+5*i,upperLeft.y+5*j,SIZE/5,SIZE/5);
                            g.setColor(cur);
                        }
                        else{
                            g.setColor(Color.GREEN);
                            g.fillRect(upperLeft.x+5*i,upperLeft.y+5*j,SIZE/5,SIZE/5);
                            g.setColor(cur);
                        }
                    }
                }
            }
            else{
                //Don't draw a rectangle
            }
        }
    }

    /** Updates the look of a shieldSection by introducing random black squares
     * 
     */
    protected void hurt(){
        Random rand = new Random();

        maxDamage += 10;
        damaged += 1;
        if(maxDamage >= SIZE){
            dead = true;
            done = true;
        }
        else{
            int i = maxDamage;
            while(i>=0){
                //Select a random point in the double array to set damaged
                int x = rand.nextInt(5);
                int y = rand.nextInt(5);

                //Set it to true, unless it is already, then just do nothing
                if(damagePoints[x][y]){
                    //Do nothing
                }
                else{
                    i--;
                    damagePoints[x][y] = true;
                }
            }
        }
    }

    /** To determine if this contains p
     *  @param p the point to see if this contains
     *  @return true if this contains p
     */
    public boolean hit(Point p){
        boolean hit = false;
        if(p.x>=upperLeft.x && p.x<=upperLeft.x+SIZE){
            if(p.y>=upperLeft.y && p.y<=upperLeft.y+SIZE){
                //this.hurt();
                hit = true;
                container.repaint();
            }
        }
        return hit;
    }

    @Override
    public void run(){
        while(!dead||!done){
            container.repaint();
            sleepWithCatch(DELAY_TIME);
        }
    }
}

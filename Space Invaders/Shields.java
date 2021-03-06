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
 * Class responsible for creating shields for the game.
 * Each shield is comprised of 5 ShieldSections.  As the player
 * or alien enemies hit a section on the shield it breaks down
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Shields extends AnimatedGraphicsObject{ 
    //Number of sections to comprise one shield
    public static final int NUM_SECTIONS = 5;

    //List of shield sections
    protected ShieldSection[][] sections;
    /**
     * Constructor for objects of class Shield
     * Comprised of 5 points to make up filled rects
     *      
     * @param upperLeft where should I be drawn?
     * @param container what should I be drawn in?
     */
    public Shields(Point upperLeft, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
        dead=false;
        done=false;
        sections = new ShieldSection[3][2];
        build();
    }

    /** Assembles the shield by making a 2x3 double array filled with
     *  shield sections.
     * 
     */
    public void build(){
        for(int i=0; i<sections.length;i++){
            for(int j=0; j<sections[0].length;j++){

                //Position every new ShieldSection relative to upperLeft
                //Then move it by i*SIZE and j*SIZE in x and y respectively
                //Since i and j are both 0 to start with, the first rectangle isn't moved

                sections[i][j] = new ShieldSection(new Point(
                        upperLeft.x + ShieldSection.SIZE*i,upperLeft.y + ShieldSection.SIZE*j)
                ,this.container);

                if(i==j && i>0){
                    //We kill this one on init to create the U shape
                    sections[i][j].dead = true;
                    sections[i][j].done = true;
                }
            }
        }

    }

    /** Loop through the double array and start if possible
     * 
     */
    public void startSections(){
        for(int i=0; i<sections.length;i++){
            for(int j=0; j<sections[0].length;j++){
                if(i==j && i>0){
                    if(sections[i][j] != null){
                        sections[i][j].dead = true;
                        sections[i][j].done = true;
                    }
                }else{
                    sections[i][j].start(); 
                }

            }
        }
    }

    /**
     * Draw the shield at its current location. AKA call each section's paint method
     * 
     * @param g the Graphics object on which the shield should be drawn
     */
    @Override
    public void paint(Graphics g) {
        for(int i=0; i<sections.length;i++){
            for(int j=0; j<sections[0].length;j++){
                if(sections[i][j]!=null){
                    sections[i][j].paint(g);
                }
            }
        }
    }

    /**
     * This object's run method, which manages the life of the shield as it
     * bounces around the screen.
     */
    @Override
    public void run() {
        while(!dead||!done){
            container.repaint();
            sleepWithCatch(DELAY_TIME);
        }
    }
}

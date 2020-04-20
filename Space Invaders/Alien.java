import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.image.*;
/**
 * Class responsible for creating and animating a single alien
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Alien extends Thread implements ImageObserver{
    //implements ImageObserver

    /** Is the animation done, meaning the object can safely
    never be drawn again? */
    protected boolean done;

    /** The container on which we will call repaint after changes are made */
    protected JComponent container;

    /** delay time between frames of animation (ms)*/
    public static final int DELAY_TIME = 266;

    /** amount to move during animation*/
    public static final int MOVE_AMT = 5;
    


    /** max allowed coordinates of play area*/
    public static final int LEFT_MAX = 0;
    public static final int RIGHT_MAX = 830;
    public static final int TOP_MAX = 0;
    public static final int BOTTOM_MAX = 600;

    /** type of animated object */
    protected String type;

    /** flag to tell if this object is dead or alive*/
    protected boolean dead;

    /** point to draw object from */
    protected Point upperLeft;

    /** status of the object */
    String status;

    // pixels to move each iteration
    private double xSpeed, ySpeed;

    // we store the contents of the files in an Image object,
    // declared as static since we'd only ever need it once.

    private static Image alien1aImage;
    private static Image alien1bImage;
    private static Image alien2aImage;
    private static Image alien2bImage;
    private static Image alien3aImage;
    private static Image alien3bImage;    
    private static Image alien4aImage;
    private static Image alien4bImage;   
    private static Image explodeImage;   

    //Type of alien (1,2,3,4)
    int subType;

    //Alien version (a or b)
    String version;

    /**
     * Constructor for objects of class Alien
     *      
     */
    public Alien(Point upperLeft, JComponent container){
        this.upperLeft = upperLeft;
        this.container = container;

        xSpeed = 0;
        ySpeed = 0;

        subType = 1;
        version = "a";
        status = "alive";
        done = false;
    }

    /**
     * Draw the alien at its current location.
     * 
     * @param g the Graphics object on which the alien should be drawn
     */
    public void paint(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillOval(upperLeft.x, upperLeft.y,50, 50);

        if(!dead){
            // draw the appropriate image
            switch(subType){
                case 1:
                g.drawImage(alien1aImage, upperLeft.x, upperLeft.y, this);
                break;
                case 2:
                g.drawImage(alien1bImage, upperLeft.x, upperLeft.y, this);
                break;
                case 3:
                g.drawImage(alien2aImage, upperLeft.x, upperLeft.y, this);
                break;
                case 4:
                g.drawImage(alien2bImage, upperLeft.x, upperLeft.y, this);
                break;
                case 5:
                g.drawImage(alien3aImage, upperLeft.x, upperLeft.y, this);
                break;
                case 6:
                g.drawImage(alien3bImage, upperLeft.x, upperLeft.y, this);
                break;
                case 7:
                g.drawImage(alien4aImage, upperLeft.x, upperLeft.y, this);
                break;
                case 8:
                g.drawImage(alien4bImage, upperLeft.x, upperLeft.y, this);
                break;
                default:
                g.drawImage(explodeImage, upperLeft.x, upperLeft.y, this);

            }
        }
    }

    /**
     * This object's run method, which manages the life of the alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {
        //If aliens are moving left
        boolean left = true;

        //If aliens are moving down
        boolean descend = false;

        //After 5 iterations of run method, switch to other picture;
        int walk = 0;

        //After 5 iterations of run method, switch to other picture;
        int changeDirection = 0;

        container.repaint();
        int j = 0;
        while(j < 200){
            //while(getStatus().equals("alive")){
            //After 5 iterations of loop, our alien switches to 
            //alternate image to represent movement
            if(walk < 5){

            }else{
                if(version.equals("a")){
                    version = "b";
                    subType++;
                }else{
                    version = "a";
                    subType--;
                }
                walk = 0;
            }
            //wait
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }
            //if descend is true, move y value down 
            if(descend){
                for(int i = 0; i < 10; i++){
                    upperLeft.y += MOVE_AMT;
                    descend = false;
                }
            }else{
                if(left){
                    upperLeft.x -= MOVE_AMT;
                }else{
                    upperLeft.x += MOVE_AMT;
                }
            }

            //check if image is at the edge of screen
            if(changeDirection >10){
                descend = true;
                if(left){
                    left = false;
                }else{
                    left = true;
                }
                changeDirection = 0;
            }
            walk++;
            changeDirection++;
            container.repaint();
            //}

            //DELETE THIS
            j++;
        }
        //set subtype to number that will show explode image
        subType = 10;
        for(int i = 0; i < 20; i++){
            container.repaint();
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }
            container.repaint();
        }
        done = true;
    }

    /**
    Accessor method to check the value of the done variable.
    The done variable should only be set to true when it this
    object is guaranteed never to need to be painted again.

    @return whether this object's lifetime is done and can safely
    never be painted again
     */
    protected boolean done() {

        return done;
    }

    /**
     * Returns the current status of a bubble (start, grow, float, empty, pop)
     *
     * @return status the status of the bubble
     */
    public String getStatus(){
        return status;
    }

    /**
     * Sets the status of the bubble (start, grow, float, empty, pop)
     *
     * @param newStatus the new status to set the bubble to
     */
    public void setStatus(String newStatus){
        status = newStatus;
    }

    /**
     * Returns the current position of a bubble
     *
     * @return status the position of the bubble
     */
    public Point getPosition(){
        return upperLeft;
    }

    // the method required by ImageObserver
    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    protected static void loadPic(){

        // create the image that will be drawn by the
        // paintComponent method

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        alien1aImage = toolkit.getImage("alien1a.png");
        alien1bImage = toolkit.getImage("alien1b.png");
        alien2aImage = toolkit.getImage("alien2a.png");
        alien3aImage = toolkit.getImage("alien3a.png");
        alien3bImage = toolkit.getImage("alien3b.png");
        alien4aImage = toolkit.getImage("alien4a.png");
        alien4bImage = toolkit.getImage("alien4b.png");
        explodeImage = toolkit.getImage("explode.png");
    }

    protected boolean isDead(){
        return dead;
    }
}

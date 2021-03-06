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
public class Alien extends AnimatedGraphicsObject implements ImageObserver{

    /** delay time between frames of animation (ms)*/
    public static final int DELAY_TIME = 99;

    /** amount to move during animation*/
    public static final int MOVE_AMT = 1;

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

    boolean attack;
    boolean hasLaser;
    /**
     * Constructor for objects of class Alien
     *      
     * @param upperLeft where should I be drawn?
     * @param subType the subType of this
     * @param container what should I be drawn in?
     */
    public Alien(Point upperLeft, int subType, JComponent container){
        super(container);
        this.upperLeft = upperLeft;
        this.subType = subType;
        this.container = container;
        version = "a";
        status = "alive";
        done = false;
        attack = false;
        hasLaser = false;
    }

    /**
     * Draw the alien at its current location.
     * 
     * @param g the Graphics object on which the alien should be drawn
     */
    @Override
    public void paint(Graphics g) {

        // if(!dead && !done){
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
        //}
    }

    /**
     * This object's run method, which manages the life of the alien as it
     * bounces around the screen.
     */
    @Override
    public void run() {
        //determines if alien will shoot at player

        //randomizes shots
        Random rand = new Random();

        double speed = 1;
        //int delay = 333;

        //for test purposes.
        int distMoved = 0;

        //If aliens are moving left
        boolean left = true;

        //If aliens are moving down
        boolean descend = false;

        //After 5 iterations of run method, switch to other picture;
        int walk = 0;

        //After 5 iterations of run method, switch to other picture;
        int changeDirection = 0;
        if(!getStatus().equals("shot")){
            container.repaint();
            int j = 0;
            while(j < 300){

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
                sleepWithCatch(DELAY_TIME);
                //if descend is true, move y value down 
                if(descend){
                    for(int i = 0; i < 10; i++){
                        upperLeft.y += MOVE_AMT;
                        descend = false;

                        distMoved += MOVE_AMT;
                    }
                    //speed += .21;
                    //delay -= 50;
                }else{
                    if(left){
                        upperLeft.x -= MOVE_AMT;
                    }else{
                        upperLeft.x += MOVE_AMT;
                    }
                }

                //change direction of alien fleet
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

                j++;
            }
        }else if(getStatus().equals("shot")){
            int i = 0;
            subType +=10;

            while(i < 20){
                sleepWithCatch(DELAY_TIME);
                container.repaint();
            }
            i++;
        }

        setStatus("dead");
        done = true;

    }

    /**
     * Returns the current value of subType 
     *
     * @return subType
     */
    public int getSubType(){
        return subType;
    }

    /**
     * Sets subType of newType 
     *
     * @param newType the new value of subType
     */
    public void setSubType(int newType){
        subType = newType;

    }

    /** Returns the width of the alien image
     *  @return the width of the alien image
     */
    protected int getAlienWidth(int alienType){
        int width = alien1aImage.getWidth(null);
        return width;
    }

    /** Returns the height of the alien image
     *  @return the height of the alien image
     */
    protected int getAlienHeight(){
        int height = alien1aImage.getHeight(null);

        return height;
    }

    /** Returns the status of attack
     *  @return attack
     */
    protected boolean getAttack(){
        return attack;
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
        alien2bImage = toolkit.getImage("alien2b.png");
        alien3aImage = toolkit.getImage("alien3a.png");
        alien3bImage = toolkit.getImage("alien3b.png");
        alien4aImage = toolkit.getImage("alien4a.png");
        alien4bImage = toolkit.getImage("alien4b.png");
        explodeImage = toolkit.getImage("explode.png");
    }

    /** Returns the status of dead
     *  @return dead
     */
    protected boolean isDead(){
        return dead;
    }

    //SRC: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
    public static synchronized void playSound(String soundIn) {
        new Thread(new Runnable() {
                // The wrapper thread is unnecessary, unless it blocks on the
                // Clip finishing; see comments.
                public void run() {
                    try {
                        File soundFile = new File(soundIn);
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
                        clip.open(inputStream);
                        clip.start(); 
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }).start();
    }
}

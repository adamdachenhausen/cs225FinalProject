import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
/**
 * Handles individual animation of a laser shot
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Explosion extends AnimatedGraphicsObject implements ImageObserver{
    private static Image explosion;
    private static Image shotExplosion;
    private static Image playerExplosion;
    private static Image ufoExplosion;
    
    public static final int EXPLOSION_WIDTH = 50;
    public static final int EXPLOSION_HEIGHT = 39;
    /** Constructor for Explosion
     *  @param container the container to draw this in
     *  @param upperLeft where to draw this
     *  @param typeIn the type of Explosion this should be
     */
    public Explosion(JComponent container, Point upperLeft,String typeIn){
        super(container);
        
        //We have to shift the upperLeft point to the center of the image
        this.upperLeft = new Point(upperLeft.x-EXPLOSION_WIDTH/2,upperLeft.y-EXPLOSION_HEIGHT/2);
        
        type = typeIn;
        done = false;
        dead = false;
    }

    @Override
    public void paint(Graphics g){
        if(!done){
            //draw image of explosion
            if(type.equals("SHOT")){
                g.drawImage(shotExplosion, upperLeft.x , upperLeft.y, this);
            }else if(type.equals("PLAYER")){
                g.drawImage(playerExplosion, upperLeft.x , upperLeft.y, this);
            }else if(type.equals("UFO")){
                g.drawImage(ufoExplosion, upperLeft.x , upperLeft.y, this);
            }else{
                //If alien
                g.drawImage(explosion, upperLeft.x , upperLeft.y, this);
            }
        }
    }

    @Override
    public void run(){
        //upperLeft.x -= 25;
        int i = 0;
        while(i < 20){
            container.repaint();
            sleepWithCatch(DELAY_TIME);
            container.repaint();
            i++;
        }

        done = true;
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    /** Loads the image to draw
     * 
     */
    protected static void loadPic(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        explosion = toolkit.getImage("explode.png");

        shotExplosion = toolkit.getImage("shotexplosion.png");
        playerExplosion = toolkit.getImage("playexplosion.png");
        ufoExplosion = toolkit.getImage("ufoexplosion.png");
    }

}

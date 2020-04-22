import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
/**
 * One of 4 types of laser shots.
 * It either will hit an alien, shield, or the top of the gameboard.
 * One of those will cause it to explode on impact.
 *
 * @author Lindsay Clark, Kate Nelligan, Adam Dachenhausen
 * @version Spring 2020
 */
public class Laser extends AnimatedGraphicsObject implements ImageObserver
{
    //Amount to translate every DELAY_TIME
    public static final int Y_SPEED = 4;

    protected LaserShot shot;

    private static Image explosion;
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
        dead=false;
        type=typeIn;
        shot = new LaserShot(container,upperLeft,type);
        shot.start();
    }

    @Override
    public void paint(Graphics g){
        if(!dead){
            shot.paint(g);
        }
        else if(!done){
            //draw image of explosion
            g.drawImage(explosion, upperLeft.x , upperLeft.y, this);
        }
        else{
            //do nothing
        }
    }

    @Override
    public void run(){
        while(!done){
            sleepWithCatch(DELAY_TIME);
            if(type.equals("PLAYER")){
                upperLeft.translate(0,-Y_SPEED);
                if(upperLeft.y<0){dead = true;}
            }
            else{
                upperLeft.translate(0,Y_SPEED);
                if(upperLeft.y>container.getHeight()){dead = true;}
            }
            container.repaint();
        }
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
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        explosion = toolkit.getImage("explode.png");
    }


}
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
 * Represents the Robber in the Game
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Robber  extends AnimatedGraphicsObject implements ImageObserver{

    final static protected int ROBBER = 1;

    final static protected int SIZE = 76;

    private static Image robber;

    private Point upperLeft;

    /**
     * Constructor for objects of class GamePieces
     * 
     * @param container what should I be drawn in?
     * @param upperLeft where should I be drawn?
     */
    public Robber(JComponent container, Point upperLeft)
    {
        super(container);

        this.upperLeft = upperLeft;
    }

    /** Sets visible to true, when called
     * 
     */
    public void showRobber(){
        visible = true;
    }

    @Override
    public void paint(Graphics g){
        // //draw colored circle with robber picture

        g.setColor(Color.BLACK);

        g.fillOval(upperLeft.x, upperLeft.y, SIZE , SIZE);
        g.setColor(Color.WHITE);
        g.drawOval(upperLeft.x, upperLeft.y, SIZE , SIZE);
        int x = upperLeft.x + SIZE/2 - (robber.getWidth(this)/2);
        int y = upperLeft.y + SIZE/2 - (robber.getHeight(this)/2);
        g.drawImage(robber, x, y, this);

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
        robber = toolkit.getImage("robber.png");

    }

    /** Sets upperLeft to p
     *  @param p the point to save in upperLeft
     */
    public void setPosition(Point p){
        upperLeft = p;
    }

    @Override
    public void run(){

    }
}
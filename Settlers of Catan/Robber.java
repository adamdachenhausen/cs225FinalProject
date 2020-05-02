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
 * Write a description of class Robber here.
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
     */
    public Robber(JComponent container, Point upperLeft)
    {
        super(container);

        this.upperLeft = upperLeft;
    }

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

    @Override    
    public void setPosition(Point p){
        upperLeft = p;
    }

    @Override
    public void run(){

    }
}
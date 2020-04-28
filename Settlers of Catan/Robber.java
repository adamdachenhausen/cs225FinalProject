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
    private static Image robber;

    /**
     * Constructor for objects of class GamePieces
     */
    public Robber(JComponent container)
    {
        super(container);
    }

    public void showCard(){
        visible = true;
    }

    @Override
    public void paint(Graphics g){
        // //draw colored circle with robber picture
        //g.drawFilledOval

        // g.drawImage(robber, upperLeft.x , upperLeft.y, this);

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
    public void run(){

    }
}
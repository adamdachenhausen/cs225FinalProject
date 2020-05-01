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
 * Paints a table
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Table  extends AnimatedGraphicsObject implements ImageObserver{

    private static Image table;

    /**
     * Constructor for objects of class GamePieces
     */
    public Table(JComponent container)
    {
        super(container);
        upperLeft = new Point(0, 0);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(table, upperLeft.x , upperLeft.y, this);
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

        table = toolkit.getImage("table.png");

    }
    @Override
    public void run(){

    }
}
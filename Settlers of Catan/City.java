import javax.swing.JComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * Write a description of class City here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class City extends AnimatedGraphicsObject implements ImageObserver
{
    public static final int SIZE = 6;
    public static final int SHIFT = 3;
    protected int x;
    protected int y;

    //Am I a city?
    protected boolean city;

    //Am I a settlement
    protected boolean settlement;

    //Have I been placed?
    protected boolean placed;

    //Am a possible placement, but not yet placed?
    protected boolean possible;

    private static Image cityImage;
    private static Image settlementImage;
    public City(JComponent container, Point center){
        super(container);
        this.container = container;
        
        this.x = center.x;
        this.y = center.y;

        possible = false;
        placed = false;
        city = true;
        settlement = true;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();

        if(placed){
            if(settlement){
                g.drawImage(settlementImage,100,100,this);
            }
            else if(city){
                g.drawImage(cityImage,100,100,this);
            }
        }
        else{
            g.fillOval(x-SHIFT,y-SHIFT,SIZE,SIZE);
        }

        //Set g back to the original color
        g.setColor(cur);
    }

    @Override
    public void run(){

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
        cityImage = toolkit.getImage("city.png");
        settlementImage = toolkit.getImage("settlement.png");
    }
}

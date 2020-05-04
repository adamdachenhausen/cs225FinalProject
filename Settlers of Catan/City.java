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
    public static final int PLACED_SIZE = 50;
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

    protected String owner;
    protected int tokenValue;

    private static Image cityImage;
    private static Image settlementImage;
    public City(JComponent container, Point center, int tokenValue){
        super(container);
        this.container = container;

        this.x = center.x;
        this.y = center.y;

        possible = true;
        placed = false;
        city = true;
        settlement = false;
        owner = "Player ";
        this.tokenValue = tokenValue;
    }

    @Override
    public void paint(Graphics g){
        //For returning g back to the original color
        Color cur = g.getColor();
        
        if(placed){
            Point newPoint = findCenter(new Point(x,y));
            if(settlement){
                g.fillOval(newPoint.x,newPoint.y, PLACED_SIZE,PLACED_SIZE);
                g.drawImage(settlementImage,newPoint.x+5,newPoint.y+5,this);
            }
            else if(city){
                g.drawImage(cityImage,newPoint.x,newPoint.y,this);
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

    /** returns point
     */
    public Point getCityPoint(){
        return new Point(x,y);
    }

    public void update(){
        if(possible && !city){
            placed = true;
            city = true;
            possible = false;
        }
        else if(placed && city){
            settlement = true;
            possible = false;
        }
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

    protected String getOwner(){
        return owner;
    }

    protected void setPlaced(boolean newPlaced){
        placed = newPlaced;
    }

    protected int getTokenValue(){
        return tokenValue;
    }

    /** Given a p and boolean, determines the center of a token
     *  @param p the point of the token
     *  @param isRobber a boolean flag if the token is a robber
     *  @return the center of this token
     */
    private Point findCenter(Point p){
        Point cPoint = p;

        cPoint.x = cPoint.x - ((PLACED_SIZE/2));
        cPoint.y = cPoint.y -((PLACED_SIZE/2));

        return cPoint;
    }

    protected void setOwner(int playerNumber){
        if(playerNumber == 1){
            owner = "Player 1";  
        }else if(playerNumber == 2){
            owner = "Player 2"; 
        }else if(playerNumber == 3){
            owner = "Player 3";
        }else{
            owner = "Player 4";
        }
    }
}

import java.awt.Polygon;
import java.awt.Point;
/**
 * Represents the blue hexagon that holds the many smaller hexagons
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Sea{
    public static final int MULTIPLIER = 4;
    public static final int X_OFFSET = 0;
    public static final int Y_OFFSET = 0;

    //This is the polygon that constructs the hexagon sea
    public static final int SEA_SIDE_LENGTH = 340;
    public static final int APOTHEM = 294;
    public static Polygon createSea(Point center){
        Polygon p = new Polygon();
        //Create the polygon "sea"
               
        //Add points in clockwise rotation starting at due West point
        p.addPoint(center.x-SEA_SIDE_LENGTH,center.y);
        p.addPoint(center.x-SEA_SIDE_LENGTH/2,center.y-APOTHEM);
        p.addPoint(center.x+SEA_SIDE_LENGTH/2,center.y-APOTHEM);
        p.addPoint(center.x+SEA_SIDE_LENGTH,center.y);
        p.addPoint(center.x+SEA_SIDE_LENGTH/2,center.y+APOTHEM);
        p.addPoint(center.x-SEA_SIDE_LENGTH/2,center.y+APOTHEM);
        
        return p;
    }

}

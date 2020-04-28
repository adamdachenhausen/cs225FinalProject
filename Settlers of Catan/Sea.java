import java.awt.Polygon;
import java.awt.Point;
/**
 * Represents the blue hexagon that holds the many smaller hexagons
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Sea{

    //This is the polygon that constructs the hexagon sea
    public static final int SEA_SIDE_LENGTH = HexTiles.SIDE_LENGTH * GameBoard.BOARD_WIDTH;
    public static Polygon createSea(Point topLeft){
        Polygon p = new Polygon();
        //Create the polygon "sea"
        p.addPoint(topLeft.x,topLeft.y);
        p.addPoint(topLeft.x+SEA_SIDE_LENGTH,topLeft.y);
        //
        //
        //
        //
        return p;
    }
    

}


import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
/**
 * Write a description of class Resources here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Resources
{
    
    public static final int NUM_BRICKS = 3;
    public static final int NUM_WOOD = 4;
    public static final int NUM_ORE = 3;
    public static final int NUM_WHEAT = 4;
    public static final int NUM_WOOL = 4;
    public static final int NUM_SAND = 1;
    public static final int NUM_RESOURCES = 19;

    
    /** Populates the r stack with exact number of each resource
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public static Stack populateR(){
        Stack<Resource> r = new Stack<Resource>();
        
        //Add everything to r
        for(int i=0;i<NUM_BRICKS;i++){
            r.add(Resource.BRICKS);
        }

        for(int i=0;i<NUM_WOOD;i++){
            r.add(Resource.WOOD);
        }

        for(int i=0;i<NUM_ORE;i++){
            r.add(Resource.ORE);
        }

        for(int i=0;i<NUM_WHEAT;i++){
            r.add(Resource.WHEAT);
        }

        for(int i=0;i<NUM_WOOL;i++){
            r.add(Resource.WOOL);
        }

        for(int i=0;i<NUM_SAND;i++){
            r.add(Resource.SAND);
        }

        //Randomize r now, so we don't have to do this later
        Collections.shuffle(r);
        
        return r;
    }
}

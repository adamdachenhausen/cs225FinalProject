import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Random;
import java.io.*;
/**
 * Write a description of class Catan here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Catan extends ThreadGraphicsController implements MouseListener, MouseMotionListener, ActionListener{

    // button that starts the game
    protected JButton startButton;

    // button that resets the game;
    protected JButton resetButton;

    // button that displays instructions for the game;
    protected JButton instructionsButton;

    protected static boolean gameStart = false;

    protected static boolean gameWon = false;

    protected static boolean gameEnded = false;

    protected boolean reset = false;

    // main panel with buttons for the game
    protected JPanel mainPanel;

    // bottom panel with buttons for the game
    protected JPanel bottomPanel;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public Catan() {

        super("Catan", FRAME_WIDTH, FRAME_HEIGHT);
        //alienShip = new AlienShip(panel, start);
    }

    /**
     * Default implementation of the method that will connect the
     * given frame, which represents the whole window and the panel,
     * which is where graphics will be drawn and mouse events
     * delivered.  If additional components are used, they can be set
     * up here.  The default implementation simply adds the panel to
     * the frame.
     * 
     * Derived classes should override this if such functionality is
     * needed.
     * 
     * @param frame the JFrame to which components ultimately need to
     * be added
     * @param panel the JPanel where graphics will be drawn that needs
     * to be added somewhere in the GUI hierarchy
     */
    @Override
    protected void buildGUI(JFrame frame, JPanel panel) {

    }

    /**
     *   Add the mouse listeners to the panel.  Here, we need methods
     *   from both MouseListener and MouseMotionListener.
     * 
     *   @param p the JPanel to which the mouse listeners will be
     *   attached
     */
    @Override
    protected void addListeners(JPanel panel) {

        //Add action listeners to buttons
        //startButton.addActionListener(this);

        //resetButton.addActionListener(this);
        //instructionsButton.addActionListener(this);
   	panel.addMouseListener(this);
	panel.addMouseMotionListener(this);
    }

    /**
       Mouse press event handler to set up to create a new
       BouncingGravityBall on subsequent release.

       @param e mouse event info
    */
    @Override
    public void mousePressed(MouseEvent e) {

	panel.repaint();
    }
    /**
       Mouse drag event handler to create remember the current point
       for sling line drawing.

       @param e mouse event info
    */
    @Override
    public void mouseDragged(MouseEvent e) {

	// = e.getPoint();

	panel.repaint();
    }

    /**
       Mouse release event handler to create a new BouncingGravityBall
       centered at the release point, initial velocities depending on 
       distance from press point.

       @param e mouse event info
    */
    @Override
    public void mouseReleased(MouseEvent e) {


	panel.repaint();
    }

    // fill in unused methods needed to satify the interfaces, which
    // are needed since we can't use the MouseAdapter, as this class
    // now needs to extend the abstract class
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    //----------------------------------------------------------

    //The following methods are called in the action listener

    //---------------------------------------------------------- 
    /**
     * Sets up the game after start button pressed
     *
     */
    public void startGame() {

    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void resetGame() {

    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void showInstructions() {
        JFrame instructionDialog = new JFrame("Catan Instructions");
        JOptionPane.showMessageDialog(instructionDialog, "Here is how to play!");

    }

    /**
     * Executes action each time button pressed.
     *
     *@param e ActionEvent that triggered this call
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(startButton)){

        }
        if(e.getSource().equals(resetButton)){

        }
        if(e.getSource().equals(instructionsButton)){

        }
    }
    //----------------------------------------------------------

    //The following methods set up the game for playing

    //---------------------------------------------------------- 
    /**
     * Sets the hex tiles that make up the island
     *
     * @param 
     * @return 
     */
    public void setBoard(){
        // shuffle hex tiles (randomize order of tiles)

        // place tiles together in a rough hex shape (see google doc for reference)

    }

    /**
     * Each player puts down a road and a settlement.
     * 
     * Continues in reverse order until every player puts down two 
     * settlements and two roads.
     * 
     * Direction: clockwise/counterclockwise 
     *
     * @param 
     * @return 
     */
    public void createSettlements(){
        // roll dice: highest roll chooses first player to play

    }
    
        /**
     * Each player puts down a road and a settlement
     * Direction: clockwise
     *
     * @param 
     * @return 
     */
        public void getResources(){
            // roll dice: highest roll chooses first player to play
        }

    //----------------------------------------------------------

    //Starts the main run method
    
    //----------------------------------------------------------
    public static void main(String args[]) {

        //launch main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new Catan());
    }
}

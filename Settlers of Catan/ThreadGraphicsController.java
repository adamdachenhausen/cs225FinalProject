import javax.swing.Icon;
import java.util.Timer;
import java.util.TimerTask;
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
import javax.sound.sampled.*;

/**
A base class to help manage animated graphics programs where the
animated objects extend the companion class AnimatedGraphicsObject,
and are managed by the run and paintComponent methods provided
here.  Event handlers and specifics of the animated and
non-animated graphics are to be given in the classes that extend
this and AnimatedGraphicsObject.

@author Jim Teresco modified by Kate Nelligan, Lindsay Clark, Adam Dachenhausen
@version Spring 2020
 */

public class ThreadGraphicsController implements Runnable {
    //Width of Frame
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 850;

    //Width of Bottom button panel
    public static final int PANEL_WIDTH = 1200;
    public static final int PANEL_HEIGHT = 150;

    /** list of animated graphics objects currently on the screen */


    protected ResourceDeck resourceDeck;
    protected DevelopmentDeck developmentDeck;

    protected java.util.ArrayList<Player> players;

    /** the table for the board */
    protected Table table;

    /** the whole gameboard where tiles are stored */
    protected GameBoard gameboard;

    /** the status pane to give player directions */
    protected StatusPane statusPane;

    /** Two dice */
    protected Dice die1;
    protected Dice die2;

    /** the panel in which our graphics are drawn */
    protected JPanel panel;

    /** the frame that contains all elements of gui */
    protected JFrame frame;

    // these store the values passed to the constructor
    // for window label and size to be used when needed in
    // the run method that sets up the user interface
    protected String windowLabel;
    protected Dimension windowSize;

    /** an object to serve as the lock for thread safety of our list
    access */
    protected Object lock = new Object();

    // thisTGC is a copy of the this reference of this object
    // so we can access it in the paintComponent method (where
    // this would refer to the JPanel instead!)
    protected ThreadGraphicsController thisTGC;

    /**
    Constructor, which needs to take the size and name of the
    window to create.

    @param label Window label string
    @param size Size of the window to create
     */
    public ThreadGraphicsController(String label, int width, int height) {

        windowLabel = label;
        windowSize = new Dimension(width, height);
        thisTGC = this;
    }

    /**
    The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        frame = new JFrame(windowLabel);
        frame.setPreferredSize(windowSize);

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                // first, we should call the paintComponent method we are
                // overriding in JPanel
                super.paintComponent(g);

                // call the method to draw any non-animated
                // objects such as background patterns, visual
                // feedback about dragging, etc.
                // we need to use thisTGC to get the reference
                // to the ThreadGraphicsController since this
                // here will refer to the JPanel on which the
                // paintComponent is called
                thisTGC.paint(g);

                // redraw each animated graphics object at its
                // current position, remove the ones that are done
                // along the way
                int i = 0;

                // since we will be modifying the list, we will
                // lock access in case any other code tries to
                // access the list
                if(gameboard != null && players != null && checkForWinner() > 0){
                    int winner = checkForWinner();
                    displayWinScreen(g, winner);
                }else if(!Catan.gameStart) {
                    introScreen(g);
                    
                }

                if(gameboard != null){
                    table.paint(g);
                    gameboard.paint(g);
                }else{
                    introScreen(g);
                }

                if(gameboard != null && statusPane != null){
                    statusPane.paint(g);
                }

                if(gameboard != null && die1 != null && die2 != null){
                    die1.paint(g);
                    die2.paint(g);
                }


                if(!Catan.gameStart && gameboard != null){
                    clearScreen();
                }

                

                i = 0;


            }
        };

        // the panel should be placed appropriately within the frame
        // by this method, so if anything further is needed such as
        // additional panels, buttons, etc., that can be accomplished
        // here by overriding the default implementation of buildGUI
        // below
        buildGUI(frame, panel);

        

        // we don't know here what mouse, keyboard, or other listeners
        // a derived class might want to add, so derived classes that
        // use listeners should override this method and set them
        addListeners(panel);

        // construct the list of AnimatedGraphicsObject
        //hexTilesList = new ArrayList<HexTiles>();
        resourceDeck = new ResourceDeck(panel);
        developmentDeck = new DevelopmentDeck(panel);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Checks if someone won the game.
     *
     * 
     * @return true if a player has over 10 points
     */
    public int checkForWinner(){
        int winner = 0;
        for(int i = 0; i < 4; i++){
            if(players.get(i).getVictoryPoints() == 10){
                winner = players.get(i).getPlayerNumber();
            }
        }
        return winner;
    }

    /**
     * Shows a screen displaying game win info
     *
     * @param g Graphics
     * @param winner the number of the player who won
     * 
     */
    public void displayWinScreen(Graphics g, int winner){
        g.setColor(new Color(182, 30, 44));
        g.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

        g.setColor(new Color(250, 210, 24));
        String catan = "PLAYER " + winner+ " WON!";
        g.setFont(new Font("TimesRoman", Font.BOLD, 150));
        FontMetrics fm = g.getFontMetrics();
        int x = ((panel.getWidth() - fm.stringWidth(catan)) / 2);

        int y = ((panel.getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(catan, x, y);

        String instruction = "Press start to play again.";
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x2 = (panel.getWidth() - fm.stringWidth(instruction)) / 2;
        int y2 = (y + fm.getAscent() + 20);

        g.drawString(instruction, x2, y2);
        panel.repaint();
    }

    /**
     * Shows a screen displaying intro
     *
     * @param g Graphics
     * 
     */
    public void introScreen(Graphics g){
        g.setColor(new Color(182, 30, 44));
        g.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

        g.setColor(new Color(250, 210, 24));
        String catan = "CATAN";
        g.setFont(new Font("TimesRoman", Font.BOLD, 150));
        FontMetrics fm = g.getFontMetrics();
        int x = ((panel.getWidth() - fm.stringWidth(catan)) / 2);

        int y = ((panel.getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(catan, x, y);

        String instruction = "Press start to play.";
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x2 = (panel.getWidth() - fm.stringWidth(instruction)) / 2;
        int y2 = (y + fm.getAscent() + 20);

        g.drawString(instruction, x2, y2);
        panel.repaint();
    }

    /**
     * Clears the screen
     *
     * @param 
     * @return 
     */
    public void clearScreen(){
        resourceDeck = null;
        developmentDeck = null;
        
        players = null;
        gameboard = null;
        
        die1 = null;
        die2 = null; 

        table = null; 

    }

    /**
    Default implementation of the method that will draw any static
    image needed in the window and any visual feedback needed for
    event handling (like mouse press to mouse location "sling"
    lines).

    Derived classes should override this if such functionality is
    needed.

    @param g Graphics object in which to draw.
     */
    protected void paint(Graphics g) {

    }

    /**
    Default implementation of the method that will add listeners
    at the appropriate time during creation of the window.

    Derived classes should override this if such functionality is
    needed.

    @param p the JPanel to which any mouse or keyboard listeners
    should be attached
     */
    protected void addListeners(JPanel p) {

    }

    /**
    Default implementation of the method that will connect the
    given frame, which represents the whole window and the panel,
    which is where graphics will be drawn and mouse events
    delivered.  If additional components are used, they can be set
    up here.  The default implementation simply adds the panel to
    the frame.

    Derived classes should override this if such functionality is
    needed.

    @param frame the JFrame to which components ultimately need to
    be added
    @param panel the JPanel where graphics will be drawn that needs
    to be added somewhere in the GUI hierarchy
     */
    protected void buildGUI(JFrame frame, JPanel panel) {

        frame.add(panel);
    }

}

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
import javax.sound.sampled.*;
/**
 * Creates the game Space Invaders
 * 
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class ArcadeMachine extends ThreadGraphicsController implements ActionListener, KeyListener{ 
    //Constants
    protected static final int ALIEN_POINTS = 10;
    protected static final int SPACESHIP_POINTS = 100;
    protected static final int LIVES = 3;
    public static final int GRAPHICS_WIDTH = 800;
    public static final int GRAPHICS_HEIGHT = 800;

    // current coordinates of the upper left corner of the 
    //user's cannon/ship (a rectangle).
    private Point upperLeft;

    // amount to move on each arrow key press
    public static final int MOVE_BY = 2;

    // button that starts the game
    private JButton startButton;

    // button that resets the game;
    private JButton resetButton;

    // game is started
    private boolean gameStart = false;

    // score for game so far
    private int score = 0;

    // label that holds the score to the game
    private JLabel scoreLabel;

    // button that holds the high score to the game
    private int highScore = 0;

    // label that holds the high score for the game
    private JLabel highScoreLabel;

    // label to show how many shots are left before the game ends.
    private JLabel instructions;

    // bottom panel with buttons for the game
    private JPanel bottomPanel;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public ArcadeMachine() {

        super("Space Invaders", GRAPHICS_WIDTH, GRAPHICS_HEIGHT);
    }

    /**
     *   Add the mouse listeners to the panel.  Here, we need methods
     *   from both MouseListener and MouseMotionListener.
     * 
     *   @param p the JPanel to which the mouse listeners will be
     *   attached
     */
    @Override
    protected void addListeners(JPanel panel, JFrame frame) {
        frame.addKeyListener(this);
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
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        startButton.addActionListener(this);
        resetButton.addActionListener(this);

        panel.add(startButton);
        panel.add(resetButton);

    }

    /**
     * Executes action each time button pressed.
     *
     *@param e ActionEvent that triggered this call
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
    Handle key typed, not used here.

    @param e KeyEvent for this key typed
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    /**
    Handle key released, not used here.

    @param e KeyEvent for this key released
     */
    @Override
    public void keyReleased(KeyEvent e) { }

    /**
     * Handle the arrow key press, which results in motion of the ball.
     *
     * @param e the KeyEvent to determine direction
     */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upperLeft.translate(0, -MOVE_BY);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            upperLeft.translate(0, MOVE_BY);
        }
        else{
            upperLeft.translate(0, 0);
        }

        // trigger paint so we can see the ship in its new location
        panel.repaint();
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new ArcadeMachine());
    }
}

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
    public static final int FRAME_WIDTH = 850;
    public static final int FRAME_HEIGHT = 850;

    public static final int PANEL_WIDTH = 850;
    public static final int PANEL_HEIGHT = 150;

    // current coordinates of the upper left corner of the 
    //user's cannon/ship (a rectangle).
    private Point playerPoint;

    //Starting point of first alien in first row
    private Point alienPoint = new Point(50,100);

    // amount to move on each arrow key press
    public static final int MOVE_BY = 4;

    // button that starts the game
    private JButton startButton;

    // button that resets the game;
    private JButton resetButton;

    // button that displays instructions for the game;
    private JButton instructionsButton;

    // game is started
    private boolean gameStart = false;

    // score for game so far
    private int score = 0;

    // button that holds the high score to the game
    private int highScore = 0;

    // label that holds the score to the game
    private JLabel scoreLabel;

    // label that holds the high score for the game
    private JLabel highScoreLabel;

    // label to show how many shots are left before the game ends.
    private JLabel instructions;

    // main panel with buttons for the game
    private JPanel mainPanel;

    // bottom panel with buttons for the game
    private JPanel bottomPanel;

    // Player ship/cannon
    PlayerShip player;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public ArcadeMachine() {

        super("Space Invaders", FRAME_WIDTH, FRAME_HEIGHT);
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
        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.setResizable(false);
        //frame.requestFocus();
        

        //add graphics panel to main panel
        mainPanel.add(panel);
        //mainPanel.requestFocus();
        
        //Add border around game panel
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(blackLine);

        //add bottom panel: a panel for the buttons
        bottomPanel = new JPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        bottomPanel.add(buttonPanel);

        JPanel scorePanel = new JPanel();
        bottomPanel.add(scorePanel);

        JLabel buttonInstructions = new JLabel("Click 'Start' to play the game!");
        buttonInstructions.setFont(buttonInstructions.getFont().deriveFont(Font.BOLD,15));
        buttonInstructions.setBorder(new EmptyBorder(5,330,10,330));
        buttonPanel.add(buttonInstructions);

        //Construct buttons
        startButton = new JButton("Start");
        startButton.setToolTipText("Press to begin the game");
        resetButton = new JButton("Reset");
        resetButton.setToolTipText("Press to reset the game");
        instructionsButton = new JButton("Instructions");
        instructionsButton.setToolTipText("Press for instructions on how to play the game");

        //Add buttons
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(instructionsButton);

        //Add score label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.BOLD,15));
        scorePanel.add(scoreLabel);
        scoreLabel.setBorder(new EmptyBorder(10,20,10,20));

        //Add highscore label
        highScoreLabel = new JLabel("High score: " + highScore);
        highScoreLabel.setFont(highScoreLabel.getFont().deriveFont(Font.BOLD,15));
        highScoreLabel.setBorder(new EmptyBorder(10,20,10,20));
        scorePanel.add(highScoreLabel);

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
        //Add key listener to frame
        //mainPanel.addKeyListener(this);
        panel.addKeyListener(this);
        //bottomPanel.addKeyListener(this);

        

        //Add action listeners to buttons
        startButton.addActionListener(this);
        startButton.addKeyListener(this);
        resetButton.addActionListener(this);
        instructionsButton.addActionListener(this);
    }
    /**
     * Executes action each time button pressed.
     *
     *@param e ActionEvent that triggered this call
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startButton)){

            //SoundEffect.CLICK.play();
            startGame();
        }
        if(e.getSource().equals(resetButton)){
            //SoundEffect.CLICK.play();
            resetGame();
        }
        if(e.getSource().equals(instructionsButton)){
            //SoundEffect.CLICK.play();
            showInstructions();
        }
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void startGame() {
        gameStart = true;

        createPlayer();

        createAliens();
        //edit more instance variables here, this is a stub
    }

    public void createPlayer() {
        //starting point for player ship
        playerPoint = new Point(401, 650);

        player = new PlayerShip(    playerPoint, panel);
        synchronized (lock) {
            list.add(player);
        }
        player.start();
        panel.repaint();
    }

    public void createAliens() {

    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void resetGame() {
        gameStart = false;

        //edit more instance variables here, this is a stub
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void showInstructions() {
        JFrame instructionDialog = new JFrame("Game Instructions");
        JOptionPane.showMessageDialog(instructionDialog, "Use left/right arrow keys to move, press 'SPACE' to fire!");

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
        System.out.println("key pressed");
        //Source for consume(): https://docs.oracle.com/javase/7/docs/api/java/awt/event/InputEvent.html#consume()
        if(gameStart){
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.getPosition().translate(-MOVE_BY, 0);

                //move ship
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.getPosition().translate(MOVE_BY, 0);

                //move ship
            }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                //fire cannon code (call method)

            }
            else{
                e.consume();
            }
        }else{
            e.consume();   
        }
        // trigger paint so we can see the ship in its new location
        panel.repaint();
    }

    public static void main(String args[]) {
        //load sound files and set volume
        SoundEffect.init();
        SoundEffect.volume = SoundEffect.Volume.LOW;

        //load pics
        Alien.loadPic();

        //launch main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new ArcadeMachine());
    }
}

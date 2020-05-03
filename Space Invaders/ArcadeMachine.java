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
 * Creates the game Space Invaders
 * 
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class ArcadeMachine extends ThreadGraphicsController implements ActionListener, KeyListener{ 
    //Constants

    //Width of Frame
    public static final int FRAME_WIDTH = 850;
    public static final int FRAME_HEIGHT = 850;

    //Width of Bottom button panel
    public static final int PANEL_WIDTH = 850;
    public static final int PANEL_HEIGHT = 150;

    //Alien type values for switch statement in Alien class
    public static final int ALIEN_1 = 1;
    public static final int ALIEN_2 = 3;
    public static final int ALIEN_3 = 5;
    public static final int ALIEN_4 = 7;

    //Maximum shift can go right
    public static final int WIDTH_MAX = 830;

    //Alien ship width
    public static final int SHIP_SIZE = 48;

    // amount to move on each arrow key press
    public static final int MOVE_BY = 6;

    UfoTimer timer1;
    UfoTimer timer2;

    // button that starts the game
    protected JButton startButton;

    // button that resets the game;
    protected JButton resetButton;

    // button that displays instructions for the game;
    protected JButton instructionsButton;

    protected JLabel introTextLabel;

    protected JLabel pressStartLabel;

    // label that holds the score to the game
    protected static JLabel scoreLabel;

    // label that holds the high score for the game
    protected JLabel highScoreLabel;

    // label to show how many shots are left before the game ends.
    protected JLabel instructions;

    // score
    protected static int score;

    // high score
    protected static int highScore;

    protected static boolean gameStart = false;

    protected static boolean gameWon = false;

    protected static boolean gameEnded = false;

    protected boolean reset = false;

    // main panel with buttons for the game
    protected JPanel mainPanel;

    // bottom panel with buttons for the game
    protected JPanel bottomPanel;

    /** amount to space aliens horizontally apart*/
    public static final int H_SPACING = 75;

    /** amount to space aliens vertically apart*/
    public static final int V_SPACING = 70;

    protected long lastShotTime;

    //The delay in between shots in ms, so the user can't spam
    public static int SHOT_DELAY = 750;

    protected boolean leftDown;

    protected boolean rightDown;

    protected boolean spaceDown;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public ArcadeMachine() {

        super("Space Invaders", FRAME_WIDTH, FRAME_HEIGHT);
        lastShotTime = System.currentTimeMillis();
        //alienShip = new AlienShip(panel, start);

        leftDown = false;
        rightDown = false;
        spaceDown = false;
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
        //mainPanel.setBackground(Color.black);
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.setResizable(false);
        //frame.requestFocus();

        //add graphics panel to main panel
        mainPanel.add(panel);

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
        frame.addKeyListener(this);

        //Add action listeners to buttons
        startButton.addActionListener(this);

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
        frame.requestFocus();
        if(e.getSource().equals(startButton)){
            playSound("buttonclick.wav");
            startGame();

        }
        if(e.getSource().equals(resetButton)){

            playSound("buttonclick.wav");
            resetGame();
        }
        if(e.getSource().equals(instructionsButton)){
            //SoundEffect.CLICK.play();
            playSound("buttonclick.wav");
            showInstructions();
        }
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void startGame() {
        resetGame();
        if(!gameStart){

            gameStart = true;
            gameWon = false;
            gameEnded = false;
            reset = false;
            createPlayer();

            createShields();

            createAliens();

            createTimer();
        }
    }

    public void createTimer() {
        Random r = new Random();
        int timeForUFO1 = r.nextInt(5000) + 3000;
        int timeForUFO2 = r.nextInt(17000) + 12000;

        //Researched timer class here: 
        //http://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/timer.html
        timer1 = new UfoTimer(timeForUFO1);
        timer2 = new UfoTimer(timeForUFO2);

    }
    public class UfoTimer{
        protected Timer timer;
        protected UfoTask ufoTimer;
        public UfoTimer(int ms){
            timer = new Timer();

            ufoTimer = new UfoTask();

            timer.schedule(ufoTimer, ms);

        }

        public UfoTask getTask(){
            return ufoTimer;
        }

        public void setTask(UfoTask ut){
            ufoTimer = ut;
        }
        class UfoTask extends TimerTask{
            public void run(){
                Random r = new Random();
                int direction = r.nextInt(2);
                Point start;
                if(direction > 0){
                    start = new Point(0,50);
                }else{
                    start = new Point(800,50); 
                }

                AlienShip alienShip = new AlienShip(panel, start);
                alienShips.add(alienShip);
                alienShip.start();
                playSound("ufo_lowpitch.wav");

                //If we need to reset, throw this timer, and its tasks away
                if(reset){
                    timer.cancel();
                    timer.purge();
                }else if(gameEnded){
                    timer.cancel();
                    timer.purge();
                }else if(gameWon){
                    timer.cancel();
                    timer.purge();
                }else{
                    alienShip = new AlienShip(panel, start);
                    //ships.add(alienShip);
                    alienShip.start();
                    playSound("ufo_lowpitch.wav");
                }

            }
        }
    }
    public void createPlayer() {
        //starting point for player ship

        player = new PlayerShip(new Point(401, 650), panel);

        player.start();
        panel.repaint();
    }

    public void createAliens() {
        Alien alien;

        //Starting x,y values for first alien in fleet
        int x = 75;
        int y = 80;

        //Starting alien type
        int alienType = ALIEN_1;
        synchronized (lock) {
            for(int i = 1; i <= 4; i++){
                for(int j = 0; j < 10; j++){
                    if(i == 2){
                        alienType = ALIEN_2;
                    }else if(i == 3){
                        alienType = ALIEN_3;
                    }else if(i == 4){
                        alienType = ALIEN_4;
                    }
                    if (j == 0){
                        alien = new Alien(new Point(x, y), alienType, panel);

                        aliens.add(alien);
                    }else{
                        x += H_SPACING;
                        alien = new Alien(new Point(x, y), alienType, panel);

                        aliens.add(alien);
                    }
                }
                x = 50;
                y += V_SPACING;
            }
        }

        for(Alien i: aliens){
            i.start();  
        }

        panel.repaint();

    }

    public void createShields() {
        //Create shield object
        int x = 125;
        int y = 520;
        Shields shield;
        for(int i = 0; i < 4; i++){

            synchronized (lock) {
                shield = new Shields(new Point(x, y), panel);
                shields.add(shield);
            }
            x += 175;
        }
        for(Shields i: shields){
            i.start();  
        }

        for(Shields i: shields){
            i.startSections();  
        }

        // for(int i = 0; i < shields.size(); i++){
        // for(int j=0; j<shields.get(i).sections.length;j++){
        // for(int k=0; k<shields.get(i).sections[0].length;k++){   
        // shields.get(i).sections[i][j].start();
        // }
        // }
        // }

        panel.repaint();
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void resetGame() {
        if(gameStart){
            gameStart = false;
            gameWon = false;
            gameEnded = false;
            reset = true;
            player.setStatus("dead");
            aliens.clear();
            shields.clear();
            explosions.clear();

            //cancel red ufos
            timer1.getTask().cancel();
            timer2.getTask().cancel();
            alienShips.clear();

            score=0;
            scoreLabel.setText("Score: " + score);

            //introTextLabel.setVisible(true);

            //pressStartLabel.setVisible(true);
        }
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public static void beatLevel() {
        gameStart = false;
        gameEnded = false;
        gameWon = true;

        //player.setStatus("dead");
        //aliens.clear();
        //shields.clear();
        //alienShip.setStatus("dead");
        //edit more instance variables here, this is a stub
        new ScoreSender(score);

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
    public void keyReleased(KeyEvent e) {
        if(gameStart){
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                leftDown = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                rightDown = false;
            }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                spaceDown = false;
            }
            else{
                e.consume();
            }
        }else{
            e.consume();   
        }

    }

    /**
     * Handle the arrow key press, which results in motion of the ball.
     *
     * @param e the KeyEvent to determine direction
     */
    public void keyPressed(KeyEvent e) {

        //Source for consume(): https://docs.oracle.com/javase/7/docs/api/java/awt/event/InputEvent.html#consume()
        if(gameStart){
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                leftDown = true;
                playerMoveFire();
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                rightDown = true;
                playerMoveFire();
            }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                spaceDown = true;
                playerMoveFire();
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

    /** Method that allows multiple keystrokes to be registered at the same time
     * 
     */
    protected void playerMoveFire(){
        if(leftDown){
            if(player.getPosition().x >= 10) {
                player.getPosition().translate(-MOVE_BY, 0);
            }
        }
        if(rightDown){
            int pos = player.getPosition().x;
            if(pos <= WIDTH_MAX - SHIP_SIZE){
                player.getPosition().translate(MOVE_BY, 0);
            }
        }
        if(spaceDown){
            if(System.currentTimeMillis() > lastShotTime + SHOT_DELAY){
                lastShotTime = System.currentTimeMillis();
                playSound("shoot.wav");
                Point p = player.getPosition();
                int x = (p.x + 48/2) - (4/2);
                int y = p.y - (4 + 6);
                Laser laser = new Laser(panel, new Point(x,y), "PLAYER");
                lasers.add(laser);
                laser.start();
            }
        }
    }

    /**
     * Returns the current status of a alien 
     *
     * @return status the status of the alien
     */
    public void alienAttack(){
        // playSound("shoot.wav");
        // //Point p = alien.getPosition();
        // int x = (p.x + 48/2) - (4/2);
        // int y = p.y - (4 + 6);
        // Laser alienLaser = new Laser(panel, new Point(x,y), "ALIEN");
        // alienLasers.add(alienLaser);
        // alienLaser.start();
    }

    public class AttackTimer{
        protected Timer timer;
        AttackTask attack;
        public AttackTimer(int ms){
            timer = new Timer();
            attack = new AttackTask();
            timer.schedule(attack, ms);

        }

        public AttackTask getTask(){
            return attack;
        }

        public void setTask(AttackTask nt){
            attack = nt;
        }
        class AttackTask extends TimerTask{
            public void run(){

                Random r = new Random();
                int direction = r.nextInt(2);
                Point start;
                if(direction > 0){
                    start = new Point(0,50);
                }else{
                    start = new Point(800,50); 
                }

                AlienShip alienShip = new AlienShip(panel, start);
                alienShips.add(alienShip);

                //ships.add(alienShip);
                alienShip.start();
                playSound("ufo_lowpitch.wav");

            }
        }
    }

    public int getScore(){
        return score;

    }

    public int getHighScore(){
        return highScore;

    }

    public void setScore(int newScore){
        score = newScore;

    }

    public void setHighScore(int newHighScore){
        highScore = newHighScore;

    }

    //SRC: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
    public synchronized void playSound(String soundIn) {
        new Thread(new Runnable() {
                // The wrapper thread is unnecessary, unless it blocks on the
                // Clip finishing; see comments.
                public void run() {
                    try {
                        File soundFile = new File(soundIn);
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
                        clip.open(inputStream);
                        clip.start(); 
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }).start();
    }

    public static void main(String args[]) {
        //load pics
        AlienShip.loadUfoPic();
        Alien.loadPic();
        Laser.loadPic();
        Explosion.loadPic();
        //launch main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new ArcadeMachine());
    }
}

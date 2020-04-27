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
 * A base class to help manage animated graphics programs where the
 * animated objects extend the companion class AnimatedGraphicsObject,
 * and are managed by the run and paintComponent methods provided
 * here.  Event handlers and specifics of the animated and
 * non-animated graphics are to be given in the classes that extend
 * this and AnimatedGraphicsObject.
 * 
 * @author Jim Teresco modified by Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */

public class ThreadGraphicsController implements Runnable {

    protected int lives = 3;
    protected int recreate = 0;

    /** list of animated graphics objects currently on the screen */
    protected java.util.List<Alien>  aliens;
    protected java.util.List<Shields> shields;
    protected java.util.List<Laser> lasers;
    protected java.util.List<Laser> alienLasers;
    protected java.util.List<Explosion> explosions;
    protected java.util.List<AlienShip> alienShips;

    /** the player */
    PlayerShip player;

    /** the panel in which our graphics are drawn */
    protected JPanel panel;

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

    protected JFrame frame;

    public static final int ALIEN1_PTS = 50;
    public static final int ALIEN2_PTS = 40;
    public static final int ALIEN3_PTS = 30;
    public static final int ALIEN4_PTS = 20;
    public static final int UFO_PTS = 100;

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

        // construct the list of AnimatedGraphicsObject
        aliens = new ArrayList<Alien>();
        shields = new ArrayList<Shields>();
        lasers = new ArrayList<Laser>();
        alienLasers = new ArrayList<Laser>();
        explosions = new ArrayList<Explosion>();
        alienShips = new ArrayList<AlienShip>();

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

                //displays appropriate title screen:
                //intro, gameover, or game won.
                //otherwise, shows lives in upper left because game started.
                if(!ArcadeMachine.gameStart && !ArcadeMachine.gameEnded && !ArcadeMachine.gameWon){
                    clearScreen();
                    introScreen(g);

                }else if(!ArcadeMachine.gameStart && ArcadeMachine.gameEnded && !ArcadeMachine.gameWon){
                    clearScreen();
                    gameOverScreen(g);
                    //ArcadeMachine.beatLevel();
                }else if(!ArcadeMachine.gameStart && ArcadeMachine.gameWon && !ArcadeMachine.gameEnded){
                    clearScreen();
                    gameWonScreen(g);
                    
                    //ArcadeMachine.beatLevel();
                }else{

                    displayLives(g);   
                }

                int i = 0;
                
                //if player has no lives, ends game, shows end game screen
                //otherwise paints the player object.
                if(player != null && ArcadeMachine.gameStart){
                    if(player.getLives() < 1){

                        gameOverScreen(g);

                        clearScreen();
                        //ArcadeMachine.beatLevel();
                        panel.repaint();

                    }else{
                        player.paint(g);
                    }
                }

                //creates shields, removes if done
                if(shields != null){
                    while(i < shields.size()){
                        Shields s = shields.get(i);
                        if (s.done()) {
                            shields.remove(i);
                        }
                        else {
                            s.paint(g);
                            i++;
                        }
                    }
                }
                i = 0;
                
                // creates aliens and removes if they are done.
                synchronized (lock) {
                    while (i < aliens.size()) {
                        Alien a = aliens.get(i);
                        if(a.done()) {
                            aliens.remove(i);
                        }
                        else {
                            a.paint(g);
                            i++;
                        }

                    }
                }
                i = 0;

                //creates alien laser attacks.
                synchronized (lock) {
                    while (i < aliens.size()) {
                        Alien a = aliens.get(i);

                        if(a.getAttack()){
                            Point laserPoint = new Point(a.getPosition().x + 20, a.getPosition().y + a.getAlienHeight());
                            Laser alienlaser = new Laser(panel, new Point(a.getPosition().x + 20, a.getPosition().y + 50), "ALIEN");
                            alienLasers.add(alienlaser);
                            alienlaser.start();
                        }

                        i++;
                    }
                }

                i = 0;
                
                //draws player lasers if player if firing
                //checks if an alien was hit, if so, creates explosion object
                //checks if red ufo was hit, if so, creates explosion object
                //removes lasers from arraylist if done
                //checks if last alien/ufo hit, if so shows game won screen.
                synchronized (lock) {
                    while (i < lasers.size()) {
                        Laser l = lasers.get(i);
                        if(checkAlienHit(l.getPosition())){
                            Explosion explode = new Explosion(panel,l.getPosition(), "ALIEN");
                            explosions.add(explode);

                            lasers.remove(i);
                            explode.start();
                        }

                        if(alienShips != null && alienShips.size()>0){
                            if(checkUfoHit(l.getPosition())){

                                Explosion explode = new Explosion(panel,l.getPosition(), "UFO");
                                explosions.add(explode);

                                lasers.remove(i);
                                explode.start();
                            }
                        }
                        // if(shields != null && shields.size()>0){
                        // if(checkShieldHit(l.getPosition())){
                        // //get shield working
                        // lasers.remove(i);

                        // }
                        // }

                        //System.out.println("laser: "+l.getPosition().x +" "+ l.getPosition().y);
                        if (l.done()) {
                            lasers.remove(i);
                        }
                        else {
                            l.paint(g);

                            i++;
                        }
                        if(aliens.size() < 1 && alienShips.size() < 1){
                            ArcadeMachine.gameWon = true;
                            ArcadeMachine.gameEnded = false;
                            //ArcadeMachine.beatLevel();
                            gameWonScreen(g);
                            //panel.repaint();
                        }
                    }
                }

                // i = 0;
                // if(alienLasers != null && alienLasers.size() > 0){                
                    // synchronized (lock) {
                        // while (i < alienLasers.size()) {
                            // Laser l = alienLasers.get(i);


                            // if (l.done()) {
                                // alienLasers.remove(i);
                            // }
                            // else {
                                // l.paint(g);

                                // i++;
                            // }
                        // }
                    // }
                // }

                i = 0;
                
                //draws explosions if they exist.
                synchronized (lock) {
                    while (i < explosions.size()) {
                        Explosion e = explosions.get(i);
                        if (e.done()) {
                            explosions.remove(i);
                        }
                        else {
                            e.paint(g);
                            i++;
                        }
                    }
                }

                i = 0;
                
                //redraws alien laser attacks if they exist.
                synchronized (lock) {
                    while (i < alienLasers.size()) {
                        Laser l = alienLasers.get(i);
                        if (l.done()) {
                            alienLasers.remove(i);
                        }
                        else {
                            l.paint(g);
                            i++;
                        }

                    }
                }

                i = 0;
                
                // creates an alien laser randomly then checks if player hit
                // if player hit, removes 1 life.
                // if no lives are left, displays game over screen.
                while (i < alienLasers.size()) {
                    Laser l = alienLasers.get(i);
                    boolean hit = checkPlayerHit(l.getPosition(), i);
                    if(hit){
                        alienLasers.remove(i);
                        player.setLives(player.getLives() - 1);
;
                        //update lives method that paints lives.
                        if(player.getLives() < 1){
                            ArcadeMachine.gameEnded = true;

                            ArcadeMachine.gameWon = false;
                            //ArcadeMachine.beatLevel();
                        }
                    }
                    if(hit){
                        i+= 20000;
                    }
                    i++;
                }

                i = 0;
                
                //repaints red ufo if it exists
                if(alienShips != null){
                    while (i < alienShips.size()) {
                        AlienShip as = alienShips.get(i);
                        if (as.done()) {
                            alienShips.remove(i);
                        }
                        else {
                            as.paint(g);
                            i++;
                        }
                    }
                }

                //updates variables to show game won screen
                if(ArcadeMachine.gameStart  && aliens.size() == 0 && player.getLives() > 0){
                    ArcadeMachine.gameWon = true;
                    ArcadeMachine.gameEnded = false;
                    
                    clearScreen();
                    gameWonScreen(g);
                    //ArcadeMachine.beatLevel();
                    panel.repaint();
                    
                }else{
                }
            }
        };

        panel.setBackground(Color.black);
        // the panel should be placed appropriately within the frame
        // by this method, so if anything further is needed such as
        // additional panels, buttons, etc., that can be accomplished
        // here by overriding the default implementation of buildGUI
        // below
        buildGUI(frame, panel);

        // we don't know here what mouse, keyboard, or other listeners
        // a derived class might want to add, so derived classes that
        // use listeners should override this method and set them

        addListeners(panel, frame);

        // // construct the list of AnimatedGraphicsObject
        // aliens = new ArrayList<Alien>();
        // shields = new ArrayList<Shields>();
        // lasers = new ArrayList<Laser>();
        // alienLasers = new ArrayList<Laser>();
        // explosions = new ArrayList<Explosion>();
        // alienShips = new ArrayList<AlienShip>();

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public boolean checkAlienHit(Point p) {
        int i = 0;
        boolean hit = false;
        while (i < aliens.size()) {
            Alien a = aliens.get(i);
            Point alienUpperLeft = a.getPosition();
            //int alienWidth = 50;
            int alienWidth = a.getAlienWidth(a.getSubType());
            //int alienHeight= 50;
            int alienHeight = a.getAlienHeight();

            //Point alienCenter = new Point(alienUpperLeft.x + alienWidth/2, alienUpperLeft.y + alienHeight/2);
            int leftPt = alienUpperLeft.x;
            int rightPt = alienUpperLeft.x + alienWidth;
            int bottom = alienUpperLeft.y + alienHeight;
            int laserpt = p.x;

            if (p.x > leftPt && p.x < rightPt) {
                if(p.y <= bottom && p.y >= alienUpperLeft.y){

                    hit = true;
                    int points = 0;
                    if(a.getSubType() == 1 || a.getSubType() == 2){
                        points = ALIEN1_PTS;
                    }else if(a.getSubType() == 3 || a.getSubType() == 4){
                        points = ALIEN2_PTS;
                    }else if(a.getSubType() == 5 || a.getSubType() == 6){
                        points = ALIEN3_PTS;
                    }else if(a.getSubType() == 7 || a.getSubType() == 8){
                        points = ALIEN4_PTS;
                    }
                    ArcadeMachine.score += points;
                    ArcadeMachine.scoreLabel.setText("Score: " + ArcadeMachine.score);
                    a.setStatus("shot");
                    aliens.remove(i);
                    if(aliens.isEmpty()){
                        if(alienShips.size()==0){
                            //ArcadeMachine.beatLevel();
                            panel.repaint();
                        }
                    }
                }

            }
            i++;

        }
        return hit;
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public boolean checkPlayerHit(Point p, int laser) {
        int i = 0;
        boolean hit = false;

        Point playerPoint= player.getPosition();

        int playerWidth = player.getWidth();

        int playerHeight = player.getHeight();

        //Point alienCenter = new Point(alienUpperLeft.x + alienWidth/2, alienUpperLeft.y + alienHeight/2);
        int leftPt = playerPoint.x;
        int rightPt = playerPoint.x + playerWidth;
        int bottom = playerPoint.y + playerHeight;
        int laserpt = p.x;

        if (p.x > leftPt && p.x < rightPt) {
            if(p.y <= bottom && p.y >= playerPoint.y){
                alienLasers.remove(laser);

                hit = true;

            }
        }
        return hit;
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public boolean checkUfoHit(Point p) {
        int i = 0;
        boolean hit = false;
        while(i < alienShips.size()){
            Point ufoPos = alienShips.get(i).getPosition();

            int ufoWidth = 60;
            int ufoHeight = 50;

            int leftPt = ufoPos.x;
            int rightPt = ufoPos.x + ufoWidth;
            int bottom = ufoPos.y + ufoHeight;
            int laserpt = p.x;
            if (p.x > leftPt && p.x < rightPt) {
                if(p.y <= bottom){
                    hit = true;
                    int points = UFO_PTS;
                    ArcadeMachine.score += points;
                    ArcadeMachine.scoreLabel.setText("Score: " + ArcadeMachine.score);

                    alienShips.remove(0);
                }
            }
            i++;
        }
        return hit;
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public boolean checkShieldHit(Point p) {
        boolean hit = false;

        for(int i = 0; i < shields.size(); i++){
            Shields s = shields.get(i);
            for(int j=0; j<shields.get(i).sections.length;j++){
                for(int k=0; k<shields.get(i).sections[0].length;k++){   
                    shields.get(i).sections[i][j].hurt();
                }
            }
            // int i = 0;
            // boolean hit = false;
            // while(i < shields.size()){
            // Shields s = shields.get(i);
            // s.checkSectionHit(p);

            // }

        }
        return hit;
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public boolean checkSectionHit(Point p) {
        boolean hit = false;
        return hit;
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public void introScreen(Graphics g ) {
        g.setColor(Color.GREEN);
        String intro = "SPACE INVADERS!";
        FontMetrics fm = g.getFontMetrics();

        g.setFont(new Font("TimesRoman", Font.BOLD, 70));
        fm = g.getFontMetrics();
        int x = (panel.getWidth() - fm.stringWidth(intro)) / 2;
        int y = ((panel.getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(intro, x, y);

        String instruction = "PRESS START TO PLAY.";
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x2 = (panel.getWidth() - fm.stringWidth(instruction)) / 2;
        int y2 = (y + fm.getAscent() + 20);
        g.setColor(Color.WHITE);
        g.drawString(instruction, x2, y2);
        panel.repaint();
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public void gameWonScreen(Graphics g ) {
        g.setColor(Color.GREEN);
        String intro = "YOU WON!";
        FontMetrics fm = g.getFontMetrics();

        g.setFont(new Font("TimesRoman", Font.BOLD, 70));
        fm = g.getFontMetrics();
        int x = (panel.getWidth() - fm.stringWidth(intro)) / 2;
        int y = ((panel.getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(intro, x, y);

        String instruction = "PRESS START TO PLAY AGAIN.";
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x2 = (panel.getWidth() - fm.stringWidth(instruction)) / 2;
        int y2 = (y + fm.getAscent() + 20);
        g.setColor(Color.WHITE);
        g.drawString(instruction, x2, y2);
        panel.repaint();
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public void gameOverScreen(Graphics g ) {
        g.setColor(Color.RED);
        String intro = "GAME OVER.";
        FontMetrics fm = g.getFontMetrics();

        g.setFont(new Font("TimesRoman", Font.BOLD, 70));
        fm = g.getFontMetrics();
        int x = (panel.getWidth() - fm.stringWidth(intro)) / 2;
        int y = ((panel.getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(intro, x, y);

        String instruction = "PRESS START TO PLAY AGAIN.";
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x2 = (panel.getWidth() - fm.stringWidth(instruction)) / 2;
        int y2 = (y + fm.getAscent() + 20);
        g.setColor(Color.WHITE);
        g.drawString(instruction, x2, y2);
        panel.repaint();
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public void displayLives(Graphics g ) {
        // current size of biggest ship/cannon rectangle
        FontMetrics fm = g.getFontMetrics();
        String livesText = "LIVES: ";
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        fm = g.getFontMetrics();
        int x = 5;
        int y = (fm.getAscent() + 10);
        g.setColor(Color.WHITE);
        g.drawString(livesText, x, y);

        int lgWidth = 48;
        int medWidth = 20;
        int smWidth = 4;
        int lgHeight = 12;
        int medHeight = 6;
        int smHeight = 4;
        Point upperLeft = new Point(fm.stringWidth(livesText) + 5, fm.getAscent());
        for(int i = 0; i <+ player.getLives(); i++){
            g.setColor(Color.GREEN);
            g.fillRect(upperLeft.x, upperLeft.y,lgWidth, lgHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (medWidth/2), upperLeft.y - medHeight, medWidth, medHeight);
            g.fillRect((upperLeft.x + lgWidth/2) - (smWidth/2), upperLeft.y - (smHeight + medHeight), smWidth, smHeight);

            upperLeft.x += lgWidth + 10;

        }
        panel.repaint();
    }

    /**
     * Checks if a laser hit an alien.
     *
     */
    public void clearScreen( ) {
        aliens.clear();
        shields.clear();
        lasers.clear();
        alienLasers.clear();
        explosions.clear();
        alienShips.clear();
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
     *  Default implementation of the method that will add listeners
     *  at the appropriate time during creation of the window.
     * 
     *  Derived classes should override this if such functionality is
     * needed.

     * @param p the JPanel to which any mouse or keyboard listeners
     * should be attached
     */
    protected void addListeners(JPanel p, JFrame f) {

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
    protected void buildGUI(JFrame frame, JPanel panel) {

        frame.add(panel);
    }

}

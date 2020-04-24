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

    /** list of animated graphics objects currently on the screen */
    protected java.util.List<Alien>  aliens;
    protected java.util.List<Shields> shields;
    protected java.util.List<Laser> lasers;
    protected java.util.List<Laser> alienLasers;
    protected java.util.List<Explosion> explosions;
    protected java.util.List<AlienShip> alienShips;

    /** the player */
    AnimatedGraphicsObject player;

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

                if(aliens.size() == 0 && alienShips.size() == 0){
                    g.setColor(Color.GREEN);
                    String intro = "SPACE INVADERS!";
                    FontMetrics fm = g.getFontMetrics();

                    g.setFont(new Font("TimesRoman", Font.BOLD, 70));
                    fm = g.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(intro)) / 2;
                    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                    g.drawString(intro, x, y);

                    String instruction = "PRESS START TO PLAY.";
                    g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                    fm = g.getFontMetrics();
                    int x2 = (getWidth() - fm.stringWidth(instruction)) / 2;
                    int y2 = (y + fm.getAscent() + 20);
                    g.setColor(Color.WHITE);
                    g.drawString(instruction, x2, y2);
                }
                //g.fillRect(0, 0, 850, 675);
                // redraw each animated graphics object at its
                // current position, remove the ones that are done
                // along the way
                int i = 0;

                if(player != null && !player.getStatus().equals("dead")){
                    player.paint(g);
                }

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
                // since we will be modifying the list, we will
                // lock access in case any other code tries to
                // access the list
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
                // synchronized (lock) {
                // while (i < aliens.size()) {
                // Alien a = aliens.get(i);

                // // if(a.getAttack()){
                // // //Point laserPoint = new Point(a.getPosition().x + 20, a.getPosition().y + a.getAlienHeight());
                // // Laser alienlaser = new Laser(panel, new Point(a.getPosition().x + 20, a.getPosition().y + 50), "ALIEN");
                // // alienLasers.add(alienlaser);
                // // alienlaser.start();
                // // }

                // }
                // }

                i = 0;
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
                        //System.out.println("laser: "+l.getPosition().x +" "+ l.getPosition().y);
                        if (l.done()) {
                            lasers.remove(i);
                        }
                        else {
                            l.paint(g);

                            i++;
                        }
                    }
                }

                i = 0;
                if(alienLasers != null && alienLasers.size() > 0){                
                    synchronized (lock) {
                        while (i < alienLasers.size()) {
                            Laser l = alienLasers.get(i);
                            // if(checkAlienHit(l.getPosition())){
                            // Explosion explode = new Explosion(panel,l.getPosition(), "ALIEN");
                            // explosions.add(explode);

                            // lasers.remove(i);
                            // explode.start();
                            // }

                            if (l.done()) {
                                alienLasers.remove(i);
                            }
                            else {
                                l.paint(g);

                                i++;
                            }
                        }
                    }
                }

                i = 0;
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
                synchronized (lock) {
                    while (i < alienLasers.size()) {
                        Laser l = lasers.get(i);
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
                            ArcadeMachine.beatLevel();
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

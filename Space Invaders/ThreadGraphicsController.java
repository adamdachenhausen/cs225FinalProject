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

    /** the player */
    AnimatedGraphicsObject player;

    /** the ufo*/
    AnimatedGraphicsObject alienShip;

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

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 850, 675);
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
                        if (a.done()) {
                            aliens.remove(i);
                        }
                        else {
                            a.paint(g);
                            i++;
                        }
                    }
                }
            }
        };

        //frame.setFocusable(true);
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

        // construct the list of AnimatedGraphicsObject
        aliens = new ArrayList<Alien>();
        shields = new ArrayList<Shields>();

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
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

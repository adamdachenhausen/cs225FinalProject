import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class ArcadeMachine here.
 *
 * @author (your name)
 * @version Spring 2020
 */
public class ArcadeMachine extends MouseAdapter implements Runnable
{
    private JPanel panel;
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
        JFrame frame = new JFrame("Space Invaders");
        frame.setPreferredSize(new Dimension(800,800));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        
        frame.add(panel);
	panel.addMouseListener(this);
	panel.addMouseMotionListener(this);
	
	// display the window we've created
	frame.pack();
	frame.setVisible(true);
    }
    public static void main(String args[]) {
	javax.swing.SwingUtilities.invokeLater(new ArcadeMachine());
    }
}

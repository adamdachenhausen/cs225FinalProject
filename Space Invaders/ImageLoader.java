
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Loads images for use in the graphics display

@author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
@version Spring 2020
 */

public class ImageLoader implements Runnable, ImageObserver {

    // we store the contents of the files in an Image object,
    // declared as static since we'd only ever need it once.
    private static Image cannonImage;
    private static Image alien1aImage;
    private static Image alien1bImage;
    private static Image alien2aImage;
    private static Image alien2bImage;
    private static Image alien3aImage;
    private static Image alien3bImage;    
    private static Image alien4aImage;
    private static Image alien4bImage;   
    private static Image ufoImage;   
    private static Image attack1Image;   
    private static Image attack2Image;   
    private static Image explodeImage;   
    private static Image missImage; 
    private JPanel panel;

    private int type;
    /**
     * Constructor for ImageLoader
     *
     * @param  type type of image
     */
    public ImageLoader(int type)
    {
        // put your code here
        this.type = type;
    }

    /**
     * Constructor for ImageLoader
     *
     * @param  type type of image
     */
    public ImageLoader()
    {
        // put your code here
        this.type = type;
    }

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame("ShowSnowflake");
        frame.setPreferredSize(new Dimension(500,500));

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

                // draw a big, black background
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 700, 700);

                // draw the appropriate image
                switch(type){
                    case 1:
                    g.drawImage(alien1aImage, 100, 100, this);
                    break;
                    case 2:
                    g.drawImage(alien1bImage, 100, 100, this);
                    break;
                    case 3:
                    g.drawImage(alien2aImage, 100, 100, this);
                    break;
                    case 4:
                    g.drawImage(alien2bImage, 100, 100, this);
                    break;
                    case 5:
                    g.drawImage(alien3aImage, 100, 100, this);
                    break;
                    case 6:
                    g.drawImage(alien3bImage, 100, 100, this);
                    break;
                    case 7:
                    g.drawImage(alien4aImage, 100, 100, this);
                    break;
                    case 8:
                    g.drawImage(alien4bImage, 100, 100, this);
                    break;
                    case 9:
                    g.drawImage(ufoImage, 100, 100, this);
                    break;
                    case 10:
                    g.drawImage(attack1Image, 100, 100, this);
                    break;
                    case 11:
                    g.drawImage(attack2Image, 100, 100, this);
                    break;
                    case 12:
                    g.drawImage(explodeImage, 100, 100, this);
                    break;
                    default:
                    g.drawImage(missImage, 100, 100, this);

                }
            }
        };
        frame.add(panel);

        // display the window we've created
        frame.pack();
        frame.setVisible(true);

    }

    // the method required by ImageObserver
    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            panel.repaint();
            return false;
        }
        return true;
    }

    public static void main(String args[]) {

        // create the image that will be drawn by the
        // paintComponent method
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        cannonImage = toolkit.getImage("cannon.png");
        alien1aImage = toolkit.getImage("alien1a.png");
        alien1bImage = toolkit.getImage("alien1b.png");
        alien2aImage = toolkit.getImage("alien2a.png");
        alien3aImage = toolkit.getImage("alien3a.png");
        alien3bImage = toolkit.getImage("alien3b.png");
        alien4aImage = toolkit.getImage("alien4a.png");
        alien4bImage = toolkit.getImage("alien4b.png");
        ufoImage = toolkit.getImage("ufo.png");
        attack1Image = toolkit.getImage("attack1.png");
        attack2Image = toolkit.getImage("attack2.png");
        explodeImage = toolkit.getImage("explode.png");
        missImage = toolkit.getImage("miss.png");

        // launch the main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new ImageLoader());
    }
}


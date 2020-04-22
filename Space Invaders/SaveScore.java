import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter; 
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import java.awt.Container;
/**
 * Modified from SkiBallBonus
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SaveScore
{
    Container frame;
    int score;
    highScore current;
    boolean debug;
    File highScores;
    highScore highestScore;
    boolean newHigh;
    public SaveScore(Container frame,int score){
        this.frame=frame;
        this.score=score;
        debug=false;
    }

    /** Converts a string to a highScore obj
     * 
     */
    public static highScore toHighScore(String input){
        Scanner scnr = new Scanner(input);
        highScore output = new highScore();
        String first = scnr.next();

        //Check if score is in form of full or simplified
        if(first.equals("Name: "))
        {
            output.name = scnr.next();
            scnr.next();
            output.score = Integer.parseInt(scnr.next());
        }
        else{
            output.score = Integer.parseInt(first);
            output.name = scnr.next();
        }
        scnr.close();
        return output;
    }

    /** First saves the current score, then 
     * 
     * Checks to see if user has a highscore file
     *      if yes, reads it and reorders it inserting the new score
     *      if no, creates one and adds the highscore
     * 
     */
    public void highScoreUpdater()
    {
        //Make Popup
        String name=JOptionPane.showInputDialog(frame,"Your Score: "+score+"\nEnter Initials");
        current = new highScore(name,score);
        boolean currentAdded = false;

        if(debug)
        {
            System.out.println();
            System.out.println(name);
            System.out.println(current.toString());
        }

        try{
            highScores = new File("HighScores.txt");

            //Read in prev highScores
            Scanner scnr = new Scanner(highScores);

            //Make a highScore Object here
            ArrayList storage = new ArrayList<highScore>();

            //Check if not empty and read in file
            if(highScores.length() != 0){
                //Read the old scores in
                while(scnr.hasNextLine())
                {
                    String next = scnr.nextLine();
                    if(debug){System.out.println(next);}
                    storage.add(toHighScore(next));
                }

                Iterator iter = storage.iterator();
                FileWriter writer = new FileWriter(highScores);
                while(iter.hasNext())
                {
                    //Cast problem here
                    highScore next = (highScore) iter.next();

                    //Current highScore is greater than next or equal to
                    if(current.compareTo(next) == 1 || current.compareTo(next) == 0)
                    {
                        writer.write(current.toString()+"\n");
                        writer.write(next.toString()+"\n");
                        currentAdded=true;
                        break;
                    }
                    //Next highScore is greater than current
                    else if(current.compareTo(next) == -1)
                    {
                        writer.write(next.toString()+"\n");
                    }
                }

                //Finish copying if premature break
                while(iter.hasNext())
                {
                    highScore next =(highScore) iter.next();
                    writer.write(next.toString());
                }

                //Current is the lowest score, so put it at the end
                if(!currentAdded)
                {
                    writer.write(current.toString()+"\n");
                }

                writer.close();
                scnr.close();
            }
            else{
                FileWriter writer = new FileWriter(highScores);
                writer.write(current.toString()+"\n");
                writer.close();
            }

        }
        catch (IOException e){
            if(debug)
            {System.out.println("Error: " + e);}
        }

    }
}
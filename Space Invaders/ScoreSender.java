import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Container;
import javax.swing.JFrame;
/**
 *  Prompts player to save score, then opens a connection up with a ServerSocket
 *  at a specific IP and Port. Then verifies the connection, and sends the score.
 *  The server will then figure out the leaderboard, and send the top 10 scores back.
 * 
 *  SRC:https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 *
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class ScoreSender
{
    /** Constructor for ScoreSender
     *  
     *  Makes a popup and then sends that information to sendScore
     * 
     *  @param score, the player's score in the game
     */
    public ScoreSender(int score){
        //Make Popup
        JFrame saveScore = new JFrame("Save Score");
        String name=JOptionPane.showInputDialog(saveScore,"Your Score: "+score+"\nEnter Initials");
        sendScore(name,score);
    }

    /** Opens a connection up with a ServerSocket
     *  at a specific IP and Port. Then verifies the connection, and sends the score.
     *  The server will then figure out the leaderboard, and send the top 10 scores back.
     *  Then call printArrayList to print scores out
     * 
     *  @param name the username of the player
     *  @param score the score of the player
     */
    public static void sendScore(String name, int score){
        //Create a new highscore from params
        highScore currentScore = new highScore(name,score);

        String hostname = "192.168.1.12";
        int port = 225;
        highScore highestScore;
        boolean newHigh;
        ArrayList scores = new ArrayList();
        try (Socket s = new Socket(hostname,port)){

            //Init an OutputStream to handle outgoing data
            OutputStream out = s.getOutputStream();

            //Init a PW with auto flush
            PrintWriter w = new PrintWriter(out,true);

            //Init an InputStream to handle incoming data
            InputStream in = s.getInputStream();

            //Init a BR so we can read the input
            BufferedReader r = new BufferedReader(new InputStreamReader(in));

            //Ping the server to get the highest score
            w.println("PING");

            if(r.readLine().equals("PONG")){

                //Send currentScore to the server for processing
                w.println(currentScore.toString());

                //"HIGH" from the server indicates that current is the highest
                //score seen so far.
                String msg = r.readLine();
                if(msg.equals("true")){
                    newHigh = true;
                }
                //"LOW indicates that the current is not the highest
                else if(msg.equals("false")){
                    newHigh = false;
                }   
                else{
                    throw new IOException("Error processing highScore, will not continue");
                }

                //Set msg to what should be "High Scores:"
                msg = r.readLine()+"\n";

                //Read in the top 10 scores
                for(int i=1; i<=10; i++){
                    msg += i+". "+r.readLine()+"\n";
                }
                System.out.println(msg);
                //Read in the done
                String lastMsg = r.readLine();

                if(lastMsg.equals("DONE")){

                }
                else{
                    throw new IOException("Did not hear end word from server, will terminate anyways.");
                }

                r.close();
                in.close();
                out.close();
                w.close();
                s.close();

            }
            else{
                throw new IOException("Did not hear passcode from server, will not continue");
            }
        }
        catch(UnknownHostException e){
            System.err.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }
        printArrayList(scores);
    }

    /** Given an ArrayList, loops through it and prints in the following format:
     * (i)<data>(\n)
     * 
     * @param a the ArrayList to print out
     * 
     */
    public static void printArrayList(ArrayList a){
        for(int i=0; i<a.size(); i++){

            System.out.println(i + ". " + a +"\n");

        }
    }
}

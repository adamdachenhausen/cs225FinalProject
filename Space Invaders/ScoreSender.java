import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Container;
import javax.swing.JFrame;
/**
 * Write a description of class ScoreSender here.
 *  SRC:https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 * @author (your name)
 * @version (a version number or a date)
 */
public class ScoreSender
{
    public ScoreSender(int score){
        //Make Popup
        JFrame saveScore = new JFrame("Save Score");
        String name=JOptionPane.showInputDialog(saveScore,"Your Score: "+score+"\nEnter Initials");
        sendScore(name,score);
    }
    public static ArrayList sendScore(String name, int score){
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
        return scores;
    }
}

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * A constantly running program that opens a ServerSocket and listens on port
 * 225. Any incoming data is accepted and given its own thread. This then will
 * verify that the data is coming from a ScoreSender. The next part is recieved
 * and translated to a highScore object, and added to the leaderboard. The top
 * 10 leaderboard positions are found, and sent back to the ScoreSender. This then
 * sends a temination message
 *  
 * SRC:https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
 * 
 * @author Adam Dachenhausen, Kate Nelligan, Lindsay Clark
 * @version Spring 2020
 */
public class ScoreReceiver extends Thread
{
    private boolean newHighest;
    public ScoreReceiver(){
        newHighest = false;
    }

    /** Starts a ServerSocket on port 225, and listens for incoming data.
     *  Once received, verifies it, adds it to the leaderboard, and sends the
     *  top 10 scores back. Finally terminates by sending a termination message.
     * 
     */
    public void receiveScore(){
        int port = 225;

        Object lock = new Object();
        try(ServerSocket svr = new ServerSocket(port)){
            while(true){
                //Accept any incoming connection on this port
                Socket s = svr.accept();

                //Print a message with IP
                System.out.println("New client connected IP: " + s.getInetAddress());

                //Create a new thread every time a new connection is made, so
                //the server can handle multiple users.
                new Thread(new Runnable(){
                        public void run(){
                            try{
                                //Init an OutputStream to handle outgoing data
                                OutputStream out = s.getOutputStream();

                                //Init a PW with auto flush
                                PrintWriter w = new PrintWriter(out,true);

                                //Init an InputStream to handle incoming data
                                InputStream in = s.getInputStream();

                                //Init a BR so we can read the input
                                BufferedReader r = new BufferedReader(new InputStreamReader(in));

                                //Where we will store the clients incoming score
                                highScore playerScore;

                                //A flag that lets the client know that they had the highest score
                                boolean highest = false;

                                //We verify that the message has the correct header, and
                                //handshake the client
                                String firstMsg = r.readLine();
                                if(firstMsg.equals("PING")){
                                    //Handshake the client
                                    w.println("PONG");

                                    ArrayList topTen = new ArrayList<highScore>();

                                    playerScore = toHighScore(r.readLine());

                                    synchronized(lock){
                                        topTen = leaderBoardAdd(playerScore);
                                        w.println(newHighest);
                                    }

                                    //Let the client know that top 10 scores are coming
                                    w.println("High Scores");

                                    //Send the top ten scores
                                    for(int i=0; i<=9; i++){
                                        w.println(topTen.toString());
                                    }
                                    w.println("DONE");
                                }

                                r.close();
                                in.close();
                                out.close();
                                w.close();
                                s.close();
                            }
                            catch (IOException e){
                                System.err.println("I/O error: " + e.getMessage());
                            }
                        }
                    }).start();
            }
        }

        catch(UnknownHostException e){
            System.err.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    /** Converts a string to a highScore obj
     *  @param input a String in the form "Name: <username> <score>"
     *  @return a highScore object that represents input
     */
    public static highScore toHighScore(String input){
        Scanner scnr = new Scanner(input);
        highScore output = new highScore();
        String first = scnr.next();

        //Check if score is in form of full or simplified
        if(first.equals("Name:"))
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

    /** Given a highScore, will try to add the Score to "HighScores.txt".
     *  It first creates an ArrayList, then reads in the text file, and 
     *  adds in current (if possible) then rewrites the data to HighScores.txt
     * 
     *  @param current the highScore to be added to the leaderBoard
     */
    private ArrayList leaderBoardAdd(highScore current){
        //Storage that will mirror the data in the file
        ArrayList output = new ArrayList<highScore>();
        try{
            File highScores = new File("HighScores.txt");

            //Read in prev highScores
            Scanner scnr = new Scanner(highScores);

            //Storage for top 10 highScores, not including the new one
            ArrayList storage = new ArrayList<highScore>();

            boolean currentAdded = false;

            //Check if not empty and read in file
            if(highScores.length() != 0){
                //Read the old scores in
                while(scnr.hasNextLine())
                {
                    String next = scnr.nextLine();
                    Scanner s = new Scanner(next);

                    //Throw out the placement number and "NAME:"
                    s.next();
                    s.next();

                    //Get the initials
                    String name = s.next();

                    //Throw out "Score"
                    s.next();

                    //Get the score
                    int score = s.nextInt();

                    storage.add(new highScore(name,score));
                }

                Iterator iter = storage.iterator();
                FileWriter writer = new FileWriter(highScores);
                int placement = 1;
                while(iter.hasNext() && placement<=10)
                {
                    highScore next = (highScore) iter.next();

                    //Current highScore is greater than next or equal to next
                    if(current.compareTo(next) == 1 || current.compareTo(next) == 0)
                    {
                        writer.write(placement+". ");
                        if(placement == 1){newHighest = true;}
                        writer.write(current.toString()+"\n");
                        output.add(current);
                        placement++;

                        //Have to check incase the #10 was taken by current, as so not to write a #11
                        if(placement <=10){
                            writer.write(placement+". ");
                            writer.write(next.toString()+"\n");
                            output.add(next);
                            placement++;
                        }

                        currentAdded=true;
                        break;
                    }
                    //Next highScore is greater than current
                    //or
                    //Finish copying if premature break
                    writer.write(placement+". ");
                    writer.write(next.toString());
                    output.add(next);
                    placement++;
                }

                //Current is the lowest score, so put it at the end
                if(!currentAdded && placement<=10)
                {
                    writer.write(placement+". ");
                    writer.write(current.toString()+"\n");
                    output.add(current);
                    placement++;
                }

                writer.close();
                scnr.close();
            }
            else{
                //This is the case of an empty file, so there is no other score to compare to
                FileWriter writer = new FileWriter(highScores);
                writer.write("1. ");
                writer.write(current.toString()+"\n");
                newHighest = true;
                writer.close();
            }

        }
        catch (IOException e){

            {System.out.println("Error: " + e);}
        }
        return output;
    }
}

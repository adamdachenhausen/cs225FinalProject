import java.io.*;
import javax.sound.sampled.*;
/**
 * Enumeration class SoundEffects - write a description of the enum class here
 * 
 * Research for this class:
 * https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/package-summary.html
 * https://docs.oracle.com/javase/8/docs/technotes/guides/sound/
 * 
 * Modified from these sample methods and classes:
 * https://people.ok.ubc.ca/bowenhui/coding/SoundEffect.java
 * https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 * 
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public enum SoundEffect{
    CLICK("buttonclick.wav"),

    EXPLODE("explosion.wav"),

    SHOOT("shoot.wav"),

    UFO("ufo_lowpitch.wav"),

    KILL("invaderkilled.wav");

    /**
     * Class to set up volume control
     */
    public static enum Volume{
        MUTE, LOW, MEDIUM, HIGH
    }
    public static Volume volume = Volume.MEDIUM;

    private Clip clip;

    /**
     * SoundEffect Constructor
     *
     * @param fileName the sound file name
     */
    SoundEffect(String fileName){
        try{
            clip = AudioSystem.getClip();
        }catch(LineUnavailableException e){
            e.printStackTrace(); 
        }
    }

    /**
     * Plays the sound clip
     *
     */
    public void play(){
        if(volume != Volume.MUTE){
            if(clip.isRunning()){
                //stop if player is running
                clip.stop();
                //rewind to the beginning for replay
                clip.setFramePosition(0);
                //start playing
                clip.start();
            }
        }
    }

    public static void init(){
        //calls the constructor for all the elements
        values();
    }
}


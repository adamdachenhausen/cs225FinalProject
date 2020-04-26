import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
/**
 * Write a description of class GamePieces here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GamePiece  extends AnimatedGraphicsObject{
    final static protected int ROADS = 2; 
    final static protected int SETTLEMENTS = 2;
    final static protected int ROBBER = 1;
    /**
     * Constructor for objects of class GamePieces
     */
    public GamePiece(JComponent container)
    {
          super(container);
    }

    @Override
    public void paint(Graphics g){
        
    }

    @Override
    public void run(){

    }

}

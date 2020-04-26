import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Color;
/**
 * Write a description of class GameBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameBoard
{
    public static final Color SEA = new Color(49, 159, 181);
    JPanel panel;
    Container frame;
    public GameBoard(Container frame){
        panel = new JPanel();
        panel.setBackground(SEA);
    }

}

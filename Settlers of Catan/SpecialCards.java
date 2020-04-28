
/**
 * Write a description of class SpecialObjects here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpecialCards
{

    //Knight card (x14)- lets the player move the robber    
    //Road Building (x2)- player can place 2 roads as if they just built them
    //Year of Plenty (x2)- the player can draw 2 resource cards of their choice from the bank
    //Monopoly (x2)- player can claim all resource cards of a specific declared type
    //Victory Point card (x5)- 1 additional Victory Point is added to the owners total and doesn't need to be played to win.

    // Constants for card numbers in deck


    //Special card constants
    public static final int LONGEST_ROAD = 1;
    public static final int LARGEST_ARMY = 1;


    private static Image road;    
    private static Image army;

    /**
     * Constructor for objects of class DevelopmentCards
     */
    public DevelopmentCard(JComponent container)
    {
        super(container);
        visible = false;
    }

    public void showCard(){
        visible = true;
    }

    @Override
    public void paint(Graphics g){
        // //draw colored rectangle

        // //paint image of card type icon
        // if(!done){
        // //draw image of card
        // if(value == 1){
        // g.drawImage(dice1, upperLeft.x , upperLeft.y, this);
        // }else if(value == 2){
        // g.drawImage(dice2, upperLeft.x , upperLeft.y, this);
     
        // //paint text that describes card type
    }

    /** Populates the r stack with exact number of each development card
     *  Then shuffles the stack, so when items are popped, they are random
     */
    public static Stack populateR(){
        Stack<Development> d = new Stack<Development>();

        //Add everything to r
        for(int i=0;i<KNIGHT;i++){
            d.add(Development.KNIGHT);
        }

        for(int i=0;i<ROAD_BUILDING;i++){
            d.add(Development.ROADBUILD);
        }

        for(int i=0;i<YEAR_PLENTY;i++){
            d.add(Development.PLENTY);
        }

        for(int i=0;i<MONOPOLY;i++){
            d.add(Development.MONOPOLY);
        }

        for(int i=0;i<VICTORY_PT_CARD;i++){
            d.add(Development.VICTORY);
        }

        return d;
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y,
    int width, int height) {

        if ((infoflags & ImageObserver.ALLBITS) > 0) {
            container.repaint();
            return false;
        }
        return true;

    }

    protected static void loadPic(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        knight = toolkit.getImage("knight.png");    
        monopoly = toolkit.getImage("monopoly.png");
        plenty = toolkit.getImage("plenty.png");
        victoryPoint = toolkit.getImage("victorypoint.png");

        city = toolkit.getImage("city.png");
        settlement = toolkit.getImage("settlement.png");
        road = toolkit.getImage("road.png");
        army = toolkit.getImage("army.png");
        robber = toolkit.getImage("robber.png");

        brick = toolkit.getImage("brick.png");
        grain = toolkit.getImage("grain.png");
        ore = toolkit.getImage("ore.png");
        lumber = toolkit.getImage("lumber.png");
        wool = toolkit.getImage("wool.png");
    }

    @Override
    public void run(){

    }
}


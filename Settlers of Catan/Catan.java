import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.Random;
import java.io.*;
/**
 * Write a description of class Catan here.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Catan extends ThreadGraphicsController implements MouseListener, MouseMotionListener, ActionListener{
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int PLAYER_3 = 3;
    public static final int PLAYER_4 = 4;

    //Color list
    public static final Color ORANGE = new Color(230, 108, 44);
    protected Color[] playerColors = {Color.RED, Color.BLUE, Color.WHITE, ORANGE};

    //adds variables for gameplay
    protected int roll;

    //phase of gameplay
    protected String gamePhase;

    //player whose turn it is
    protected int turn;

    //Players
    protected Player player1, player2, player3, player4;

    //Player 1 color
    protected Color p1Color;

    // button that starts the game
    protected JButton startButton;

    // button that resets the game
    protected JButton resetButton;

    // button that displays instructions for the game;
    protected JButton instructionsButton;

    // button that draws resource card
    protected JButton drawResourceButton;

    // button that draws development card
    protected JButton drawDevelopmentButton;

    // button that makes game continue to next phase
    protected JButton continueButton;

    // button that draws development card
    protected JButton useDevCardButton;

    // button that draws development card
    protected JButton buildButton;

    // button that displays opens trade panel;
    protected JButton tradeButton;

    // button that rolls dice;
    protected JButton rollDiceButton;

    protected static boolean gameStart = false;

    protected static boolean gameWon = false;

    protected static boolean gameEnded = false;

    protected boolean reset = false;

    // main panel with buttons for the game
    protected JPanel mainPanel;

    // bottom panel with buttons for the game
    protected JPanel bottomPanel;

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public Catan() {

        super("Catan", FRAME_WIDTH, FRAME_HEIGHT);

    }

    /**
     * Default implementation of the method that will connect the
     * given frame, which represents the whole window and the panel,
     * which is where graphics will be drawn and mouse events
     * delivered.  If additional components are used, they can be set
     * up here.  The default implementation simply adds the panel to
     * the frame.
     * 
     * Derived classes should override this if such functionality is
     * needed.
     * 
     * @param frame the JFrame to which components ultimately need to
     * be added
     * @param panel the JPanel where graphics will be drawn that needs
     * to be added somewhere in the GUI hierarchy
     */
    @Override
    protected void buildGUI(JFrame frame, JPanel panel) {

        //main panel
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.setResizable(false);

        //add graphics panel to main panel
        mainPanel.add(panel);

        //Add border around game panel
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(blackLine);

        //add bottom panel: a panel for the buttons
        bottomPanel = new JPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        bottomPanel.add(buttonPanel);

        JPanel scorePanel = new JPanel();
        bottomPanel.add(scorePanel);

        // JLabel buttonInstructions = new JLabel("Click 'Start' to play the game!");
        // buttonInstructions.setFont(buttonInstructions.getFont().deriveFont(Font.BOLD,15));
        // buttonInstructions.setBorder(new EmptyBorder(5,330,10,330));
        // buttonPanel.add(buttonInstructions);

        //Construct buttons
        startButton = new JButton("Start");
        startButton.setToolTipText("Press to begin the game");

        resetButton = new JButton("Reset");
        resetButton.setToolTipText("Press to reset the game");

        instructionsButton = new JButton("Instructions");
        instructionsButton.setToolTipText("Press for instructions on how to play the game");

        drawResourceButton = new JButton("Draw Resource");
        drawResourceButton.setToolTipText("Draws a resource card");

        drawDevelopmentButton = new JButton("Draw Development");
        drawDevelopmentButton.setToolTipText("Draws a development card");

        useDevCardButton = new JButton("Use Development");
        useDevCardButton.setToolTipText("Uses a development card");

        buildButton = new JButton("Build");
        buildButton.setToolTipText("Build using resources");

        tradeButton = new JButton("Trade Resource");
        tradeButton.setToolTipText("Trade with another player");

        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.setToolTipText("Rolls the dice");

        continueButton = new JButton("Continue");
        continueButton.setToolTipText("Continues to the next phase of the game");

        //Add buttons
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(drawResourceButton);
        buttonPanel.add(drawDevelopmentButton);
        buttonPanel.add(useDevCardButton);
        buttonPanel.add(buildButton);
        buttonPanel.add(tradeButton);
        buttonPanel.add(rollDiceButton);
        buttonPanel.add(continueButton);


    }

    /**
     *   Add the mouse listeners to the panel.  Here, we need methods
     *   from both MouseListener and MouseMotionListener.
     * 
     *   @param p the JPanel to which the mouse listeners will be
     *   attached
     */
    @Override
    protected void addListeners(JPanel panel) {

        //Add action listeners to buttons
        startButton.addActionListener(this);
        resetButton.addActionListener(this);
        instructionsButton.addActionListener(this);

        resetButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        drawResourceButton.addActionListener(this);
        drawDevelopmentButton.addActionListener(this);
        useDevCardButton.addActionListener(this);
        rollDiceButton.addActionListener(this);
        tradeButton.addActionListener(this);
        continueButton.addActionListener(this);
        buildButton.addActionListener(this);

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    /**
    Mouse press event handler to set up to create a new
    BouncingGravityBall on subsequent release.

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

        panel.repaint();
    }

    /**
    Mouse drag event handler to create remember the current point
    for sling line drawing.

    @param e mouse event info
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        // = e.getPoint();

        panel.repaint();
    }

    /**
    Mouse release event handler to create a new BouncingGravityBall
    centered at the release point, initial velocities depending on 
    distance from press point.

    @param e mouse event info
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        panel.repaint();
    }

    // fill in unused methods needed to satify the interfaces, which
    // are needed since we can't use the MouseAdapter, as this class
    // now needs to extend the abstract class
    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    //----------------------------------------------------------

    //The following methods are called in the action listener

    //---------------------------------------------------------- 
    /**
     * Sets up the game after start button pressed
     *
     */
    public void startGame() {
        gamePhase = "Game starting";
        gameStart = true;

        setBoard();

        //create dice
        die1 = new Dice(panel, new Point(550,300));
        die2 = new Dice(panel, new Point(550,375));

        //pick colors
        selectColor();

        //create players
        createPlayers();

        //display the player's gamepieces (roads, settlements and cities)
        //createGamepieces();

        //add the robber to the desert
        //createRobber();
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void resetGame() {

    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void showInstructions() {
        JFrame instructionDialog = new JFrame("Catan Instructions");
        JOptionPane.showMessageDialog(instructionDialog, "Here is how to play!");

    }

    /**
     * Executes action each time button pressed.
     *
     *@param e ActionEvent that triggered this call
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(startButton)){
            if(!gameStart){
                startGame(); 
            }
        }
        if(e.getSource().equals(resetButton)){
            if(gameStart){
                resetGame(); 
            }
        }
        if(e.getSource().equals(instructionsButton)){
            showInstructions();
        }

        //fix these buttons
        if(e.getSource().equals(drawResourceButton)){
            showInstructions();
        }
        if(e.getSource().equals(drawDevelopmentButton)){
            showInstructions();
        }
        if(e.getSource().equals(useDevCardButton)){
            showInstructions();
        }
        if(e.getSource().equals(rollDiceButton)){
            showInstructions();
        }
        if(e.getSource().equals(continueButton)){
            showInstructions();
        }
        if(e.getSource().equals(tradeButton)){
            showInstructions();
        }
    }
    //----------------------------------------------------------

    //The following methods set up the game for playing

    //---------------------------------------------------------- 
    /**
     * Sets the hex tiles that make up the island
     *
     * @param 
     * @return 
     */
    public void setBoard(){
        gamePhase = "Setting Board";
        //draw gameboard

        //Forest Hex (4x), Pasture Hex (4x), Fields Hex (4x), Hills Hex (3x)
        //Mountain Hex(3x), Desert Hex(1x)
        panel.setBackground(GameBoard.BACKGROUND);

        //ADD BACK WHEN SEA IS DONE
        //sea = new Sea(panel, new Point(200, 5));

        gameboard = new GameBoard(panel,new Point(300,300));
        gameboard.createBoard();
        gameboard.start();
        gameboard.startBoard();

        //draw status pane
        statusPane = new StatusPane(panel, gamePhase, turn);

        //place tokens

        //set board with 2 settlements per player
        //ADD THIS BACK WHEN METHOD CREATED
        //createSettlements();
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void selectColor() {
        String[] colors = new String[] {"Red", "Blue", "White", "Orange"};
        int choice = JOptionPane.showOptionDialog(null, "Pick a color!", "Color Selector",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, colors, colors[0]);
        if(choice == 1){
            p1Color = Color.RED;
        }else if(choice ==2){
            p1Color = Color.BLUE;
        }else if(choice == 3){
            p1Color = Color.WHITE;
        }else{
            p1Color = Color.ORANGE;
        }
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void createPlayers() {
        player1 = new Player(PLAYER_1, p1Color);
        int i = 0;
        boolean found = false;
        while(i < 4 && !found){
            if(!player1.getColor().equals(playerColors[i])){
                player2 = new Player(PLAYER_2, playerColors[i]);
                found = true;
            }
            i++;
        }
        i = 0;
        found = false;
        while(i < 4 && !found){
            if(!player1.getColor().equals(playerColors[i])){
                if(!player2.getColor().equals(playerColors[i])){
                    player3 = new Player(PLAYER_3, playerColors[i]);
                    found = true;
                }
            }
            i++;
        }
        i = 0;
        found = false;
        while(i < 4 && !found){
            if(!player1.getColor().equals(playerColors[i])){
                if(!player2.getColor().equals(playerColors[i])){
                    if(!player3.getColor().equals(playerColors[i])){
                        player4 = new Player(PLAYER_4, playerColors[i]);
                        found = true;
                    }
                }
            }
            i++;
        }

    }

    /**
     * Each player puts down a road and a settlement.
     * 
     * Continues in reverse order until every player puts down two 
     * settlements and two roads.
     * 
     * Direction: 1st round clockwise/2nd round counterclockwise 
     *
     * @param 
     * @return 
     */
    public void createSettlements(){
        // roll dice: highest roll chooses first player to play
        roll = die1.rollDice();
        roll += die2.rollDice();
        //place settlement between two hexes

        //place road between two hexes

        panel.repaint();
    }

    /**
     * Each player puts down a road and a settlement
     *
     * @param 
     * @return 
     */
    public void getResources(){
        // roll dice: highest roll chooses first player to play

        //get resource cards based on the hex tiles that are 
        //adjacent to your settlement
    }

    /**
     * Players trade resource cards
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void PlayerTurn(Player p){
        //roll dice

        //whichever token/hex (the tokens number the hexes) is rolled
        //any settlement on the border of that hex gets resources.
        //Determine players with "activated hexes"

        //distribute resources *if not enough resources, none distributed

        //offer trades
    }

    /**
     * Players trade resource cards
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void tradeResources(Player player1, Player player2){
        //trading can only happen with the active player on a turn
    }

    /**
     * Players build to develop your empire
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void build(Player p){
        //called every turn after dice are rolled

        //build: roads, settlements, cities, development cards

        //need to provide info on what is required to build each

        //player selects what to build

        //building is placed on gameboard

        //if building settlement, can only build in place
        //connecting to existing roads

        //if building city need to pay resources and replace
        //existing settlement with a city piece
    }

    /**
     * Move the robber to a hex.
     * Take action based on placement.
     *
     * @param a hex tile to move the robber to.
     * @return 
     */
    public void activateRobber(HexTiles h){
        //call method when dice roll = 7

        //anyone with more than 7 cards must discard extras to bank

        //move robber to different hex (call method)

        //can steal one card from player with a settlement touching 
        //hex robber was placed on

        //no resources are distributed from hex robber is touching
    }

    /**
     * Move the robber to a hex.
     * Take action based on placement.
     *
     * @param a hex tile to move the robber to.
     * @return 
     */
    public void moveRobber(HexTiles h){
        //move the robber to a different hex

    }

    /**
     * Removes card for circulation and apply card benefit.
     *
     * @param 
     * @return 
     */
    public void useDevelopmentCard(){
        //card types

        //knight: move robber, don't discard the card
    }

    /**
     * Use card to build.
     *
     * @param 
     * @return 
     */
    public void useResourceCard(){
        //move the robber to a different hex

        //if players have more than 7 resource cards must remove them

        //
    }

    /**
     * Draw a card from the resource card bank.
     *
     * @param 
     * @return 
     */
    public void drawDevelopmentCard(){

        // Add to player's hand

    }

    /**
     * Draw a card from the resource card bank.
     *
     * @param 
     * @return 
     */
    public void drawResourceCard(){

        //if player has a city, gets two resource cards

        //else gets one card

        // Add to player's hand

    }

    /**
     * Draw a card from the resource card bank.
     *
     * @param 
     * @return 
     */
    public void checkRoads(){

        //first player to build 5 uninterrupted roads you get longest
        //road card worth 2 victory points.

        //player can steal card if they build longer road.

    }

    /**
     * Draw a card from the resource card bank.
     *
     * @param 
     * @return 
     */
    public void checkKnights(){

        //first player to get 3 knight development cards gets
        //largest army card.  

        //player can steal card if they build longer road.

    }

    //----------------------------------------------------------
    //Starts the main run method
    //----------------------------------------------------------
    public static void main(String args[]) {
        Dice.loadPic();
        ResourceCard.loadPic();
        DevelopmentCard.loadPic();
        Robber.loadPic();

        //launch main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new Catan());

        // while(gameStart){

        // }
    }
}

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
    public static final int WINNING_POINTS = 10;

    final static protected int CITIES = 4; 
    final static protected int SETTLEMENTS = 5;
    final static protected int ROADS = 15;

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

    // button that starts the game
    protected JButton startButton;

    // button that resets the game
    protected JButton resetButton;

    // button that displays instructions for the game;
    protected JButton instructionsButton;

    // button that displays building costs for the game;
    protected JButton buildingCostsButton;

    // // button that draws resource card
    // protected JButton drawResourceButton;

    // // button that draws development card
    // protected JButton drawDevelopmentButton;

    // button that makes game continue to next phase
    protected JButton continueButton;

    // // button that draws development card
    // protected JButton useDevCardButton;

    // // button that draws development card
    // protected JButton buildButton;

    // // button that displays opens trade panel;
    // protected JButton tradeButton;

    // // button that rolls dice;
    // protected JButton rollDiceButton;

    protected static boolean gameStart = false;

    protected static boolean gameWon = false;

    protected static boolean gameEnded = false;

    protected static boolean longestRoad = false;

    protected static boolean largestArmy = false;

    protected static int longestRoadAmt = 0;

    protected static int largestArmyAmt = 0;

    protected boolean reset = false;

    // main panel with buttons for the game
    protected JPanel mainPanel;

    // bottom panel with buttons for the game
    protected JPanel bottomPanel;

    ImageIcon icon = new ImageIcon("catanicon.png");

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

        buildingCostsButton = new JButton("Building Costs");
        buildingCostsButton.setToolTipText("Press for information about building in the game");

        continueButton = new JButton("Continue");
        continueButton.setToolTipText("Continues to the next phase of the game");

        // drawResourceButton = new JButton("Draw Resource");
        // drawResourceButton.setToolTipText("Draws a resource card");

        // drawDevelopmentButton = new JButton("Draw Development");
        // drawDevelopmentButton.setToolTipText("Draws a development card");

        // useDevCardButton = new JButton("Use Development");
        // useDevCardButton.setToolTipText("Uses a development card");

        // buildButton = new JButton("Build");
        // buildButton.setToolTipText("Build using resources");

        // tradeButton = new JButton("Trade Resource");
        // tradeButton.setToolTipText("Trade with another player");

        // rollDiceButton = new JButton("Roll Dice");
        // rollDiceButton.setToolTipText("Rolls the dice");

        //Add buttons
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(buildingCostsButton);
        buttonPanel.add(continueButton);

        // buttonPanel.add(drawResourceButton);
        // buttonPanel.add(drawDevelopmentButton);
        // buttonPanel.add(useDevCardButton);
        // buttonPanel.add(buildButton);
        // buttonPanel.add(tradeButton);
        // buttonPanel.add(rollDiceButton);

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
        buildingCostsButton.addActionListener(this);
        resetButton.addActionListener(this);

        continueButton.addActionListener(this);
        // drawResourceButton.addActionListener(this);
        // drawDevelopmentButton.addActionListener(this);
        // useDevCardButton.addActionListener(this);
        // rollDiceButton.addActionListener(this);
        // tradeButton.addActionListener(this);

        // buildButton.addActionListener(this);

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

        //Create table, then set board
        table = new Table(panel);
        setBoard();

        //create dice
        die1 = new Dice(panel, new Point(700,300));
        die2 = new Dice(panel, new Point(700,375));

        //intro dialog
        int answer = introDialog();
        if(answer == 0){
            //create players
            createPlayers(selectColor());
        }else{
            answer = introDialog(); 
        }

        //display the player's gamepieces (roads, settlements and cities)
        //createGamepieces();

        //add the robber to the desert
        //createRobber();

        //Play game
        //playGame();
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void resetGame() {
        gameStart = false;
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public int introDialog() {
        //reference for dialog boxes: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
        Object[] options = {"Continue","Quit"};
        int answer = JOptionPane.showOptionDialog(frame, "Welcome to Catan! Press "
                + "continue to select your gamepiece colors","Catan",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                icon,options,options[1]);

        return answer;
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
        if(e.getSource().equals(buildingCostsButton)){
            displayBuildingCosts();
        }

        // //fix these buttons
        // if(e.getSource().equals(drawResourceButton)){
        // showInstructions();
        // }
        // if(e.getSource().equals(drawDevelopmentButton)){
        // showInstructions();
        // }
        // if(e.getSource().equals(useDevCardButton)){
        // showInstructions();
        // }
        // if(e.getSource().equals(rollDiceButton)){
        // roll();
        // }
        // if(e.getSource().equals(continueButton)){
        // displayBuildingCosts();
        // }
        // if(e.getSource().equals(tradeButton)){
        // showInstructions();
        // }
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
        //panel.setBackground(GameBoard.BACKGROUND);

        //ADD BACK WHEN SEA IS DONE
        //sea = new Sea(panel, new Point(200, 5));

        //Draw the gameboard pieces, tokens and robber
        gameboard = new GameBoard(panel,new Point(350,350));
        gameboard.createBoard();
        gameboard.start();
        gameboard.startBoard();

        //draw status pane
        statusPane = new StatusPane(panel, gamePhase, turn);

        //set board with 2 settlements per player
        //distributeGamepieces();
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public Color selectColor() {
        Color p1Color;
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
        return p1Color;
    }

    /**
     * Displays building cost info on how you would build
     * roads, cities, settlements, or purchase development cards.
     *
     */
    public void displayBuildingCosts() {
        //research on jtable: https://stackoverflow.com/questions/27832268/how-to-format-a-table-in-joptionpane
        JLabel buildingTitle = new JLabel("Building Costs");
        //JLabel buildingInfo = new JLabel("Below are the required components to build or acquire special cards as well as victory points associated with each item.");
        Object[][] rows = {
                {"Road", "0", "1 Brick & 1 Lumber"},
                {"Settlement","1","1 Brick, 1 Lumber, 1 Grain & 1 Wool"},
                {"City", "2","2 Grain and 3 Ore"},
                {"Development card","?","1 Wool, 1 Grain & 1 Ore"},
                {" "," "," "},
                {"Special"," "," "},
                {"Longest road", "2","At least 5 contiguous road pieces"},
                {"Largest army","2","At least 3 knight cards"}};
        Object[] cols = {"Item","Victory Points","Cost"};
        JTable table = new JTable(rows, cols);
        JOptionPane buildingCosts = new JOptionPane();
        // buildingCosts.add(buildingInfo);

        buildingCosts.setSize(new Dimension(880, 400));
        // buildingCosts.setPreferredSize(new Dimension(680, buildingCosts.getPreferredSize().height));

        buildingCosts.showMessageDialog(null,new JScrollPane(table), "BuildingCosts",JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null,new JScrollPane(table));
    }

    /**
     * Sets up the game after start button pressed
     *
     */
    public void createPlayers(Color c) {
        players = new ArrayList<Player>();
        player1 = new Player(PLAYER_1, c);
        players.add(player1);
        int i = 0;
        boolean found = false;
        while(i < 4 && !found){
            if(!player1.getColor().equals(playerColors[i])){
                player2 = new Player(PLAYER_2, playerColors[i]);
                found = true;
                players.add(player2);
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
                    players.add(player3);
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
                        players.add(player4);
                    }
                }
            }
            i++;
        }

    }

    /**
     * Players trade resource cards
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void playGame(){
        turn = PLAYER_1;
        while(gameStart){
            //call player turn with correct player
            if(turn == PLAYER_1){
                PlayerTurn();
            }else{
                NpcTurn();
            }

            //whichever token/hex (the tokens number the hexes) is rolled
            //any settlement on the border of that hex gets resources.
            //Determine players with "activated hexes"

            //distribute resources *if not enough resources, none distributed

            //offer trades
            panel.repaint();

            //update player turn
            turn++;
            if(turn > PLAYER_4){
                turn = PLAYER_1;
            }

            //check if anyone has 10 victory points
            checkPoints();
        }
    }

    /**
     * The turn for the person playing the game
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void PlayerTurn(){
        boolean turnDone = false;

        //roll dice
        rollDialog();

        //whichever token/hex (the tokens number the hexes) is rolled
        //any settlement on the border of that hex gets resources.
        //Determine players with "activated hexes"

        //distribute resources *if not enough resources, none distributed

        //offer trades
    }

    /**
     * The turn for the non-player character (NPC)
     * Most steps are automated/randomized
     *

     * @return 
     */
    public void NpcTurn(){
        boolean turnDone = false;
        //roll dice
        autoRoll();

        //whichever token/hex (the tokens number the hexes) is rolled
        //any settlement on the border of that hex gets resources.
        //Determine players with "activated hexes"

        //distribute resources *if not enough resources, none distributed

        //offer trades
    }

    /**
     * Each player puts down a road and a settlement
     *
     * @param 
     * @return 
     */
    public void rollDialog(){
        // roll dice: highest roll chooses first player to play
        //Custom button text
        Object[] options = {"Roll Dice",
                "Cancel"};
        int answer = JOptionPane.showOptionDialog(frame,
                "Roll the dice or cancel.",
                "Roll the Dice",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        if(answer == 0){
            roll = 0;
            roll = die1.rollDice();
            roll += die2.rollDice();

        }else{
            rollDialog();
        }

    }

    /**
     * Automatically rolls dice for NPC
     *
     * @param 
     * @return 
     */
    public void autoRoll(){

        roll = 0;
        roll = die1.rollDice();
        roll += die2.rollDice();

    }

    /**
     * Each player puts down a road and a settlement
     *
     * @param 
     * @return 
     */
    public void distributeGamepieces(){
        for(int i = 0; i < players.size(); i++){
            //generate settlements
            for(int s = 0; s < SETTLEMENTS; s++){
                player1Pieces.add(new GamePiece(panel, "Settlement", players.get(0).getColor()));
                player2Pieces.add(new GamePiece(panel, "Settlement", players.get(0).getColor()));
                player3Pieces.add(new GamePiece(panel, "Settlement", players.get(0).getColor()));
                player4Pieces.add(new GamePiece(panel, "Settlement", players.get(0).getColor()));

            }
            for(int c = 0; c < CITIES; c++){
            }
            for(int r = 0; r < ROADS; r++){
            }
            
            // Stack<Pieces> p = new Stack<Pieces>();
            // //Add everything to r
            // for(int i=0;i<CITIES;i++){
            // p.add(Pieces.CITIES);
            // }

            // for(int i=0;i<SETTLEMENTS;i++){
            // p.add(Pieces.SETTLEMENTS);
            // }

            // for(int i=0;i<ROADS;i++){
            // p.add(Pieces.ROADS);
            // }

            // return p;
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
    public void placeGamepiece(){

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
    public void tradeResources(Player player1, Player player2){
        //trading can only happen with the active player on a turn
    }

    /**
     * Exchanges cards between two players
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void swapCards(Player player1, Player player2){
        //trading can only happen with the active player on a turn
    }

    /**
     * Players build to develop your empire
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void build(Player p, int choice){
        //called every turn after dice are rolled

        //build: roads, settlements, cities, development cards
        switch(choice){
            case 0:
            buildRoad();
            break;

            case 1:
            buildSettlement();
            break;

            case 2:
            buildCity();
            break;
        }
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
    public void buildRoad(){

    }

    /**
     * Move the robber to a hex.
     * Take action based on placement.
     *
     * @param a hex tile to move the robber to.
     * @return 
     */
    public void buildSettlement(){

    }

    /**
     * Move the robber to a hex.
     * Take action based on placement.
     *
     * @param a hex tile to move the robber to.
     * @return 
     */
    public void buildCity(){

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
     * @return winner if no one won yet, returns 0, else returns 
     * player number. 10 points wins the game.
     */
    public int checkPoints(){
        int winner = 0;

        for(int i = 0; i < 4; i++){
            if(players.get(i) != null){
                if(players.get(i).getVictoryPoints()>=10){
                    winner = players.get(i).getPlayerNumber();
                }
            }
        }

        return winner;
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
        if(!longestRoad){
            for(int i = 0; i < 4; i++){
                if(players.get(i) != null){
                    if(players.get(i).getRoadLength()>=5){
                        longestRoad = true;
                        longestRoadAmt = players.get(i).getRoadLength();
                        players.get(i).setLongestRoad(true);
                    }
                }
            }
        }else{
            //see if someone can steal longest road card
            for(int i = 0; i < 4; i++){
                if(players.get(i) != null){
                    if(players.get(i).getRoadLength()>=longestRoadAmt){
                        longestRoadAmt = players.get(i).getRoadLength();
                        players.get(i).setLongestRoad(true);
                    }
                }
            }
        }

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
        Table.loadPic();

        //launch main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new Catan());

    }
}

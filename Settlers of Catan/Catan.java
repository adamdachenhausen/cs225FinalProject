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
    protected int roll = 0;

    //phase of gameplay
    protected String gamePhase;

    //player whose turn it is
    protected int turn = 0;

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

    protected boolean reset = false;

    protected static boolean longestRoad = false;

    protected static boolean largestArmy = false;

    protected static int longestRoadAmt = 0;

    protected static int largestArmyAmt = 0;

    protected static int longestRoadHolder = 0;

    protected static int largestArmyHolder = 0;

    //These flags determine what action is selected
    //with mouse pointer
    protected boolean clicked = false;
    protected boolean buildSettlement = false;
    protected boolean buildCity = false;
    protected boolean buildRoad = false;
    protected boolean moveRobber = false;

    protected Point cityPoint;

    protected Point roadPoint1;
    protected Point roadPoint2;

    protected Point robberPoint;

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
    

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

        clicked = true;

        System.out.println(e.getPoint());

        //use boolean flags to determine if mouse listener is used to place
        //gamepieces. 
        if(buildSettlement){
            cityPoint = e.getPoint();

        }else if(buildCity){
            cityPoint = e.getPoint();

        }else if(buildRoad){
            if(roadPoint1 == null){
                roadPoint1 = e.getPoint();
            }else{
                roadPoint2 = e.getPoint();
            }

        }else if(moveRobber){
            robberPoint = e.getPoint();

        }else{
            //do nothing
        }
        
    }

    /**
    Mouse drag event handler to create remember the current point
    for sling line drawing.

    @param e mouse event info
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
    Mouse release event handler to create a new point to place a gamepiece
    centered at the release point.

    @param e mouse event info
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //if building, create gamepiece on board

        //if moving robber, change it's position appropriately

        panel.repaint();
    }

    // fill in unused methods needed to satify the interfaces, which
    // are needed since we can't use the MouseAdapter, as this class
    // now needs to extend the abstract class
    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        //panel.setRequestFocusEnabled(true);
    }

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

        //draw status pane
        statusPane = new StatusPane(panel, gamePhase, turn, roll, players);
        statusPane.start();
        panel.repaint();

        //display the player's gamepieces (roads, settlements and cities)
        //distributeGamepieces();

        //Set turn to first player and place first two settlements
        turn = 1;
        // placeGamePiece("Settlement");
        // buildSettlement = true;
        // placeGamePiece("Road");
        // buildRoad = true;

        // while(turn <= 4){
        // for(int i = 0; i < 2; i++){
        // if(turn == 1){
        // placeGamePiece("Settlement");
        // placeGamePiece("Road");
        // }else{
        // autoPlacePiece("Settlement", turn);
        // autoPlacePiece("Road", turn);
        // }                
        // }
        // turn++;
        // }

        //Play game
        playGame();
    }

    /**
     * Resets the game (in TGC class)
     *
     */
    public void resetGame() {
        gameStart = false;
    }

    /**
     * Displays information about the game
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
     * Shows game instructions
     *
     */
    public void showInstructions() {
        JFrame instructionDialog = new JFrame("Catan Instructions");
        JOptionPane.showMessageDialog(instructionDialog, "Here is how to play!");

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
    public Color selectColor() {
        Color p1Color;
        String[] colors = new String[] {"Red", "Blue", "White", "Orange"};
        int choice = JOptionPane.showOptionDialog(null, "Pick a color!", "Color Selector",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, colors, colors[0]);
        if(choice == 0){
            p1Color = Color.RED;
        }else if(choice ==1){
            p1Color = Color.BLUE;
        }else if(choice == 2){
            p1Color = Color.WHITE;
        }else{
            p1Color = Color.ORANGE;
        }
        return p1Color;
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
        if(e.getSource().equals(continueButton)){
            //rollDialog();
            //tradeResourcesDialog();
                    placeGamePiece("Settlement");
        buildSettlement = true;
        //placeGamePiece("Road");
        //buildRoad = true;
        }
        //panel.requestFocus(true);
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

        //Draw the gameboard pieces, tokens and robber
        gameboard = new GameBoard(panel,new Point(350,350));
        gameboard.createBoard();
        gameboard.start();
        gameboard.startBoard();

        //set board with 2 settlements per player
        //distributeGamepieces();
    }

    /**
     * Creates each player's color
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
        System.out.println("p1: "+ players.get(0).getColorToString() +"p2: "+ players.get(1).getColorToString() 
            +"p3: "+ players.get(2).getColorToString() +"p4: "+ players.get(3).getColorToString());
    }

    /**
     * Controls the gameplay as long as someone doesn't have 10 Victory Points, the game continues.
     *
     */
    public void playGame(){

        turn = PLAYER_1;
        while(gameStart && !gameWon){
            //call player turn with correct player
            if(turn == PLAYER_1){
                playerTurn();
            }else{
                npcTurn();
            }
            gamePhase = "Distributing Resources...";

            //whichever token/hex (the tokens number the hexes) is rolled
            //any settlement on the border of that hex gets resources.
            //Determine players with "activated hexes"

            //distribute resources *if not enough resources, none distributed
            distributeResources(roll);
            panel.repaint();

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
    public void playerTurn(){
        boolean turnDone = false;

        //roll dice
        //rollDialog();
        if(roll == 7){
            activateRobber();
        }else{
            //call distribute resource cards
            //distribute resources *if not enough resources, none distributed

            //whichever token/hex (the tokens number the hexes) is rolled
            //any settlement on the border of that hex gets resources.
            //Determine players with "activated hexes"
            tradeResourcesDialog();
        }

        //offer trades
    }

    /**
     * The turn for the non-player character (NPC)
     * Most steps are automated/randomized
     *

     * @return 
     */
    public void npcTurn(){
        boolean turnDone = false;
        //roll dice
        //autoRoll();

        //whichever token/hex (the tokens number the hexes) is rolled
        //any settlement on the border of that hex gets resources.
        //Determine players with "activated hexes"

        //distribute resources *if not enough resources, none distributed
        distributeResources(roll);

        //offer trades
    }

    /**
     * Each player puts down a road and a settlement
     *
     * @param 
     * @return 
     */
    public void rollDialog(){
        gamePhase = "Rolling dice...";
        // roll dice: highest roll chooses first player to play
        //Custom button text
        String[] options = new String[]{"Roll Dice","Cancel"};
        int answer = JOptionPane.showOptionDialog(null,
                "Roll the dice or cancel.",
                "Roll the Dice",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            roll = 0;
            roll = die1.rollDice();
            roll += die2.rollDice();

        }else{
            rollDialog();
        }
        panel.repaint();

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
     * Robber actions tree begins with several choices available for the player who
     * rolled 7.
     * 
     * Take action based on placement.
     *
     * @param a hex tile to move the robber to.
     * @return 
     */
    public void activateRobber(){
        moveRobberDialog();

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
    public void moveRobberDialog(){
        //move the robber to a different hex
        String[] options = new String[]{"Yes","No"};
        int answer = JOptionPane.showOptionDialog(null,
                "Would you like to move the robber?",
                "Building interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            moveRobber = true;
            moveRobber();
        }else if(answer == 1){
            tradeResourcesDialog();
        }
        panel.repaint();
    }

    /**
     * Move the robber to a hex.
     * Take action based on placement.
     *
     * @param a hex tile to move the robber to.
     * @return 
     */
    public void moveRobber(){
        //move the robber to a different hex
        String[] options = new String[]{"Ok"};
        int answer = JOptionPane.showOptionDialog(null,
                "Click on the hex tile where you want to move the robber.",
                "Move robber interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.OK_OPTION,
                null,
                options,
                options[0]);

        moveRobber = false;
    }

    /**
     * Each player puts down a road and a settlement
     *
     * @param 
     * @return 
     */
    public void distributeGamepieces(){
        //generate settlements
        for(int s = 0; s < SETTLEMENTS; s++){
            player1Pieces.add(new GamePiece(panel, "Settlement", players.get(0).getColor()));
            player2Pieces.add(new GamePiece(panel, "Settlement", players.get(1).getColor()));
            player3Pieces.add(new GamePiece(panel, "Settlement", players.get(2).getColor()));
            player4Pieces.add(new GamePiece(panel, "Settlement", players.get(3).getColor()));

        }
        //generate cities
        for(int c = 0; c < CITIES; c++){
            player1Pieces.add(new GamePiece(panel, "City", players.get(0).getColor()));
            player2Pieces.add(new GamePiece(panel, "City", players.get(1).getColor()));
            player3Pieces.add(new GamePiece(panel, "City", players.get(2).getColor()));
            player4Pieces.add(new GamePiece(panel, "City", players.get(3).getColor()));

        }
        //generate roads
        for(int r = 0; r < ROADS; r++){
            player1Pieces.add(new GamePiece(panel, "Road", players.get(0).getColor()));
            player2Pieces.add(new GamePiece(panel, "Road", players.get(1).getColor()));
            player3Pieces.add(new GamePiece(panel, "Road", players.get(2).getColor()));
            player4Pieces.add(new GamePiece(panel, "Road", players.get(3).getColor()));

        }
        System.out.println("piecesmade");
    }

    /**
     * Points for cities and roads are already defined. If a point is within 20 pixels
     * of a predefined point, it counts for that point.
     * 
     *
     * @param p a point to check if it is close enough to a defined point
     * @param type of point to check against
     * @return true if close, false otherwise
     */
    public boolean checkHitbox(Point p, String type){
        boolean close = false;

        // ArrayList<Point> checkPoints = gameboard.getAllPoints();
        // int size = 20;
        // if(type.equals("Road")){
        // for(int i = 0; i < checkPoints.size(); i++){
        // Point checkPoint = checkPoints.get(i);
        // if(checkPoint.distance(p) <= size) {
        // close = true;
        // }

        // }
        // }else{
        // for(int i = 0; i < checkPoints.size(); i++){
        // Point checkPoint = checkPoints.get(i);
        // if(checkPoint.distance(p) <= size) {
        // close = true;
        // }

        // }
        // }

        // //Trying to find point within roads object
        // Roads rs = gameboard.getRoadsList();
        // java.util.List<Roads>rlist = rs.getRoadList();

        // int size = 20;
        // if(type.equals("Road")){
        // for(int i = 0; i < rlist.size(); i++){

        // Roads checkRoads = rlist.get(i);
        // for(int j = 0; j < checkRoads
        // // Point checkPoint = 
        // // if(checkPoint.distance(p) <= size) {
        // // close = true;
        // // }

        // }
        // }else{

        // }
        return close;
    }

    /**
     * Each player puts down a road and a settlement.
     * 
     * Continues in reverse order until every player puts down two 
     * settlements and two roads.
     * 
     * Direction: 1st round clockwise/2nd round counterclockwise 
     *
     * @param pieceType either a settlement, city or road
     * @return 
     */
    public void placeGamePiece(String pieceType){
        GamePiece newPlacedPiece = null;
        //place settlements and cities on the corner of a hex
        if(pieceType.equals("Settlement")){
            //run until the user clicks on a spot on the gameboard
            // while(!clicked){
                // panel.repaint();
            // }
            for(int i = 0; i < player1Pieces.size(); i++){
                if(player1Pieces.get(i).getType().equals(pieceType)){
                    newPlacedPiece = player1Pieces.get(i);

                    //HERE WE NEED TO ADD THE POINT TO THE SELECTED GAMEPIECE
                    //Or, we just need to set the one gamepiece as visible here.

                    player1Pieces.get(i).setPlaced(true);
                }
            }

            //reset pressPoint for reuse
            cityPoint = null;
        }else if(pieceType.equals("City")){

            //reset pressPoint for reuse
            cityPoint = null;
        }else if(pieceType.equals("Road")){
            //place road between two hexes (connected to the settlement); 

            //reset pressPoint for reuse
            roadPoint1 = null;
            roadPoint2 = null;
        }

        panel.repaint();
    }

    /**
     * Each player puts down a road and a settlement.
     * 
     * Continues in reverse order until every player puts down two 
     * settlements and two roads.
     * 
     * Direction: 1st round clockwise/2nd round counterclockwise 
     *
     * @param pNum the player who is placing the piece
     * @param pieceType either a settlement, city or road
     * @return 
     */
    public void autoPlacePiece(String pieceType, int pNum){

        //place settlement between two hexes

        //place road between two hexes

        panel.repaint();
    }

    /**
     * Distributes resource cards to each player based on the roll of the player in control's dice.
     *
     * @param tokenVal the value of the roll of the dice that corresponds to token pieces on the board.
     * 
     */
    public void distributeResources(int tokenVal){
        gamePhase = "Distributing resources...";
        //call method from hextiles or gameboard to determine how many of each
        //get resource cards based on the hex tiles that are 
        //adjacent to your settlement
        for(int i = 0; i < players.size(); i++){

        }

    }

    /**
     * Players trade resource cards
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void tradeResourcesDialog(){
        gamePhase = "Trading...";
        //trading can only happen with the active player on a turn
        String[] options = new String[]{"Yes","No"};
        int answer = JOptionPane.showOptionDialog(null,
                "Would you like to trade?",
                "Trading interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            swapCards();
            buildDialog();
        }else if(answer == 1){
            buildDialog();
        }
        panel.repaint();
    }

    /**
     * Exchanges cards between two players
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void swapCards(){
        //trading can only happen with the active player on a turn
        gamePhase = "Trading...";
        //trading can only happen with the active player on a turn
        String[] options = new String[]{"Yes","No"};
        int answer = JOptionPane.showOptionDialog(null,
                "Which card would you like to trade?",
                "Trade Selection interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            swapCards();
            buildDialog();
        }else if(answer == 1){
            buildDialog();
        }

        panel.repaint();
    }

    /**
     * Players build to develop your empire
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void buildDialog(){
        String[] options = new String[]{"Yes","No"};
        int answer = JOptionPane.showOptionDialog(null,
                "Would you like to build?",
                "Building interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            swapCards();
            buyDevelopmentDialog();
        }else if(answer == 1){
            buyDevelopmentDialog();
        }
        panel.repaint();
    }

    /**
     * Players build to develop your empire
     *
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void buyDevelopmentDialog(){
        String[] options = new String[]{"Yes","No"};
        int answer = JOptionPane.showOptionDialog(null,
                "Would you like to buy a development card?",
                "Development card interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            //1 ore, 1 wool, 1 grain
            useResourceCard("Ore");
            useResourceCard("Grain");
            useResourceCard("Wool");
            drawDevelopmentCard();

            //proceed to next branch of dialog tree
            useDevelopmentDialog();
        }else if(answer == 1){
            useDevelopmentDialog();
        }
        panel.repaint();
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
     * Asks player if they would like to use a development card.
     * If no, returns to the turn (which ends this player's turn).
     */
    public void useDevelopmentDialog(){
        String[] options = new String[]{"Yes","No"};
        int answer = JOptionPane.showOptionDialog(null,
                "Would you like to use a development card?",
                "Use Development card interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == 0){
            selectDevelopmentDialog();
        }
        panel.repaint();
    }

    /**
     * Asks player if they would like to use a development card.
     * If no, returns to the turn (which ends this player's turn).
     */
    public void selectDevelopmentDialog(){

        String[] options;

        if(turn == 1){
            if(players.get(0).getDevelopmentHand().size()>0){
                options = new String[players.get(0).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(0).getDevelopmentHand().size(); i++){
                    options[i] = players.get(0).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to use",
                        "Use Development card interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                String choice = options[answer];

                useDevelopmentCard(choice);
            }
        }else if(turn == 2){
            if(players.get(1).getDevelopmentHand().size()>0){
                options = new String[players.get(1).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(1).getDevelopmentHand().size(); i++){
                    options[i] = players.get(1).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to use",
                        "Use Development card interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                String choice = options[answer];

                useDevelopmentCard(choice);
            }
        }else if(turn == 3){
            if(players.get(2).getDevelopmentHand().size()>0){
                options = new String[players.get(2).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(2).getDevelopmentHand().size(); i++){
                    options[i] = players.get(2).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to use",
                        "Use Development card interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                String choice = options[answer];

                useDevelopmentCard(choice);
            }
        }else if(turn == 4){
            if(players.get(3).getDevelopmentHand().size()>0){
                options = new String[players.get(3).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(3).getDevelopmentHand().size(); i++){
                    options[i] = players.get(3).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to use",
                        "Use Development card interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                String choice = options[answer];

                useDevelopmentCard(choice);
            } 

        }else{
            //Player has no development cards, show message.
            Object[] noCards = {"Ok"};
            int answer = JOptionPane.showOptionDialog(frame, "You have no development cards.","Catan",
                    JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                    icon,noCards,noCards[0]);
        }
        panel.repaint();
    }

    /**
     * Removes card for circulation and apply card benefit.
     *
     * @param 
     * @return 
     */
    public void useDevelopmentCard(String dType){
        DevelopmentCard dc = null;
        boolean found = false;
        int i = 0;
        //remove card from player's hand
        if(turn == 1){
            while(i < players.get(0).getDevelopmentCards().size() && !found){
                if(players.get(0).getDevelopmentCards().get(i).getType().equals(dType)){
                    dc = players.get(0).removeDevelopmentCard(dType);
                    found = true;
                } 
                i++;
            }
        }else if(turn == 2){
            i=0;
            found = false;
            while(i < players.get(1).getDevelopmentCards().size() && !found){
                if(players.get(1).getDevelopmentCards().get(i).getType().equals(dType)){
                    dc = players.get(0).removeDevelopmentCard(dType);
                    found = true;
                } 
                i++;
            }
        }else if(turn == 3){
            i=0;
            found = false;
            while(i < players.get(2).getDevelopmentCards().size() && !found){
                if(players.get(2).getDevelopmentCards().get(i).getType().equals(dType)){
                    dc = players.get(0).removeDevelopmentCard(dType);
                    found = true;
                } 
                i++;
            }
        }else{
            i=0;
            found = false;
            while(i < players.get(3).getDevelopmentCards().size() && !found){
                if(players.get(3).getDevelopmentCards().get(i).getType().equals(dType)){
                    dc = players.get(0).removeDevelopmentCard(dType);
                    found = true;
                } 
                i++;
            }
        }
        //card is not added back to development deck
        // if(dc != null){
        // developmentDeck.addCard(dc);
        // }
    }

    /**
     * Use resource card
     *
     * @param 
     * @return 
     */
    public void useResourceCard(String rType){
        ResourceCard rc = null;
        boolean found = false;
        int i = 0;
        //remove card from player's hand
        if(turn == 1){
            while(i < players.get(0).getResourceCards().size() && !found){
                if(players.get(0).getResourceCards().get(i).getType().equals(rType)){
                    rc = players.get(0).removeResourceCard(rType);
                    found = true;
                } 
                i++;
            }
        }else if(turn == 2){
            i=0;
            found = false;
            while(i < players.get(1).getResourceCards().size() && !found){
                if(players.get(1).getResourceCards().get(i).getType().equals(rType)){
                    rc = players.get(0).removeResourceCard(rType);
                    found = true;
                } 
                i++;
            }
        }else if(turn == 3){
            i=0;
            found = false;
            while(i < players.get(2).getResourceCards().size() && !found){
                if(players.get(2).getResourceCards().get(i).getType().equals(rType)){
                    rc = players.get(0).removeResourceCard(rType);
                    found = true;
                } 
                i++;
            }
        }else{
            i=0;
            found = false;
            while(i < players.get(3).getResourceCards().size() && !found){
                if(players.get(3).getResourceCards().get(i).getType().equals(rType)){
                    rc = players.get(0).removeResourceCard(rType);
                    found = true;
                } 
                i++;
            }
        }
        //add card removed from player hand to resource card deck
        if(rc != null){
            resourceDeck.addCard(rc);
        }
    }

    /**
     * Draw a card from the development card bank.
     *
     */
    public void drawDevelopmentCard(){

    }

    /**
     * Draw a card from the resource card bank.
     *
     * if player has a city, gets two resource cards, else gets one card
     */
    public void drawResourceCard(String rType){
        ResourceCard rc = null;
        boolean found = false;
        int i = 0;

        //add card removed from player hand to resource card deck
        if(rc != null){
            rc = resourceDeck.removeCard(rType);
        }
        //remove card from player's hand
        if(turn == 1){
            if(rc != null){
                players.get(0).addResourceCard(rc);
            }
        }else if(turn == 2){
            if(rc != null){
                players.get(1).addResourceCard(rc);
            }
        }else if(turn == 3){
            if(rc != null){
                players.get(2).addResourceCard(rc);
            }
        }else{
            if(rc != null){
                players.get(3).addResourceCard(rc);
            }
        }

    }

    /**
     * Check victory points scored by each player.
     *
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
        gameWon = true;

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
                        longestRoadHolder = players.get(i).getPlayerNumber(); 
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
                        //subtract 1 because array position is one less than playernumber
                        players.get(longestRoadHolder - 1).setLongestRoad(false);
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
        if(!largestArmy){
            for(int i = 0; i < 4; i++){
                if(players.get(i) != null){
                    if(players.get(i).getKnights()>=3){
                        largestArmy = true;
                        largestArmyAmt = players.get(i).getKnights();
                        largestArmyHolder = players.get(i).getPlayerNumber(); 
                        players.get(i).setLargestArmy(true);
                    }
                }
            }
        }else{
            //see if someone can steal largest army card
            for(int i = 0; i < 4; i++){
                if(players.get(i) != null){
                    if(players.get(i).getKnights()>=largestArmyAmt){
                        largestArmyAmt = players.get(i).getKnights();
                        //subtract 1 because array position is one less than playernumber
                        players.get(largestArmyHolder - 1).setLargestArmy(false);
                        players.get(i).setLargestArmy(true);
                    }
                }
            }
        }

    }

    //Found here: http://www.java2s.com/Code/Java/Event/ActionMouseFocus.htm
    class ActionFocusMover implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            Object source = actionEvent.getSource();
            if (source instanceof Component) {
                Component component = (Component) source;
                component.transferFocus();
            }
        }
    }

    class MouseEnterFocusMover extends MouseAdapter {
        public void mouseEntered(MouseEvent mouseEvent) {
            Component component = mouseEvent.getComponent();
            if (!component.hasFocus()) {
                component.requestFocus();
            }
        }
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

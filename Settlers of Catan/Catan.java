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
 * Creates the game Catan.
 *
 * @author Kate Nelligan, Lindsay Clark, Adam Dachenhausen
 * @version Spring 2020
 */
public class Catan extends ThreadGraphicsController implements MouseListener, MouseMotionListener, ActionListener, KeyListener{
    //Players in the game
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int PLAYER_3 = 3;
    public static final int PLAYER_4 = 4;

    //Total points to win and total amounts you can have for cities
    //roads and settlements
    public static final int WINNING_POINTS = 10;
    final static protected int CITIES = 4; 
    final static protected int SETTLEMENTS = 5;
    final static protected int ROADS = 15;

    //Color list
    public static final Color ORANGE = new Color(230, 108, 44);
    protected Color[] playerColors = {Color.RED, Color.BLUE, Color.BLACK, ORANGE};

    //This tracks the dice roll
    protected int roll = 0;

    //Tracks the phase of the game
    protected String gamePhase;

    //player whose turn it is
    protected int turn = 0;

    //Player objects
    protected Player player1, player2, player3, player4;

    // button that starts the game
    protected JButton startButton;

    // button that resets the game
    protected JButton resetButton;

    // button that continues to the next turn
    protected JButton continueButton;

    // button that displays instructions for the game;
    protected JButton instructionsButton;

    // button that displays building costs for the game;
    protected JButton buildingCostsButton;

    //booleans to track the state of the game
    protected static boolean gameStart = false;
    protected static boolean gameWon = false;
    protected static boolean gameEnded = false;
    protected static boolean gameboardSet = false;
    protected static boolean turnsStarted = false;
    protected boolean reset = false;
    protected static boolean longestRoad = false;
    protected static boolean largestArmy = false;

    //Track which player has special cards
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
    protected boolean buildStart = false;
    protected boolean buildEnd = false;
    protected boolean doneBuilding;

    //holds a traded card
    protected ResourceCard tradedCard;

    // main panel with buttons for the game
    protected JPanel mainPanel;

    // bottom panel with buttons for the game
    protected JPanel bottomPanel;

    //The catan icon for the first dialog
    ImageIcon icon = new ImageIcon("catanicon.png");

    /**
     * Constructor, which simply calls the superclass constructor
     * with an appropriate window label and dimensions.
     */
    public Catan() {

        super("Catan", FRAME_WIDTH, FRAME_HEIGHT);
        doneBuilding = false;
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

        //Add buttons
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(buildingCostsButton);

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
        buildingCostsButton.addActionListener(this);
        resetButton.addActionListener(this);

        continueButton.addActionListener(this);

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        panel.addKeyListener(this);
    }

    /**

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

        Player curPlayer;

        if(turn == 1){curPlayer = player1;}
        else if (turn == 2){curPlayer = player2;}
        else if(turn == 3) {curPlayer = player3;}
        else{curPlayer = player4;}

        gameboard.updateCurPlayer(curPlayer);
        String checkGamePiece = "";
        checkGamePiece = gameboard.handleClick(e.getPoint(), turn);
        for(Player p: players){
            p.updateCities();
            p.updateRoads();
            p.updatePoints();
        }
        //if the 4th player placed both cities and roads
        //gameboard setup is over.
        if(!gameboardSet && players.get(3).getCities() >= 2 && players.get(3).getRoads() >= 2){
            checkGamePiece ="";
            gameboardSet = true;
            turn = PLAYER_1;

            distributeResources();
            playerTurn();
        }
        //buildStart && checkGamePiece != ""
        if(buildStart){
            checkGamePiece ="";
            developmentTurn();
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

        panel.repaint();
    }

    /**
     * Key Event handler to signal when the player is done building
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            doneBuilding = true;
        }
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

    public void keyReleased(KeyEvent e){}

    public void keyTyped(KeyEvent e){}
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
        die1 = new Dice(panel, new Point(770,100));
        die2 = new Dice(panel, new Point(770,175));

        //intro dialog
        int answer = introDialog();
        if(answer == 0){
            //create players
            createPlayers(selectColor());
        }else{
            answer = introDialog(); 
        }
        turn = 1;

        //draw status pane
        statusPane = new StatusPane(panel, gamePhase, turn, roll, players);
        statusPane.start();
        panel.repaint();

        gamePhase = "Place 2 roads and 2 Settlements";
        statusPane.setPhase(gamePhase);

    }

    /**
     * Resets the game (in TGC class)
     *
     */
    public void resetGame() {
        gameStart = false;
    }

    /**
     * Displays information about the game before it starts
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
        JOptionPane.showMessageDialog(instructionDialog, "For official rules, please see:\nhttps://www.catan.com/service/game-rules");
        JOptionPane.showMessageDialog(null, "To move to the next player's turn, click continue.");
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
        String[] colors = new String[] {"Red", "Blue", "Black", "Orange"};
        int choice = JOptionPane.showOptionDialog(null, "Pick a color!", "Color Selector",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, colors, colors[0]);
        if(choice == 0){
            p1Color = Color.RED;
        }else if(choice ==1){
            p1Color = Color.BLUE;
        }else if(choice == 2){
            p1Color = Color.BLACK;
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

            if(turn < PLAYER_4){
                turn++;
            }else{
                turn = PLAYER_1;
            }
            statusPane.setTurn(turn);
            if(gameboardSet){
                playerTurn();
            }else if(turnsStarted){
                playerTurn();
            }
        }
        //panel.requestFocus(true);
    }
    //----------------------------------------------------------

    //The following methods set up the game for playing

    //---------------------------------------------------------- 
    /**
     * Sets the hex tiles that make up the island
     *
     */
    public void setBoard(){
        gamePhase = "Setting Board";

        //Draw the gameboard pieces, tokens and robber
        gameboard = new GameBoard(panel,new Point(350,350));
        gameboard.createBoard();
        gameboard.start();
        gameboard.startBoard();

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

        gameboard.updatePlayers(player1,player2,player3,player4);
    }

    /**
     * Player's full turn up to the building process.
     * Having a break here allows for a pause in the dialog tree
     * that guides the flow of the game.
     * 
     * The next method called either way is the use of development
     * cards.
     *
     */
    public void playerTurn(){

        //roll dice
        rollDialog();

        if(roll == 7){
            //activateRobber();
        }else{
            //call distribute resource cards
            //distribute resources *if not enough resources, none distributed
            //send out resource cards to players
            distributeResources();
            //whichever token/hex (the tokens number the hexes) is rolled
            //any settlement on the border of that hex gets resources.
            //Determine players with "activated hexes"

        }
        tradeResourcesDialog();

        buildDialog();

    }

    /**
     * The turn for the person playing the game
     *
     */
    public void developmentTurn(){
        buildStart = false;
        buyDevelopmentDialog();

        useDevelopmentDialog();

        checkPoints();
    }

    /**
     * Rolls the dice and calls up a dialog
     *
     */
    public void rollDialog(){
        gamePhase = "Rolling dice...";
        statusPane.setPhase(gamePhase);
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
            statusPane.setRoll(roll);
        }else{
            rollDialog();
        }
        panel.repaint();

    }

    /**
     * Robber actions tree begins with several choices available for the player who
     * rolled 7.  This was an extra that is excluded from method calls.
     * We did not get to implement it.
     * 
     * Take action based on placement.
     *
     */
    public void activateRobber(){
        gamePhase = "Activating robber...";
        statusPane.setPhase(gamePhase);
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
     */
    public void moveRobberDialog(){
        gamePhase = "Moving robber...";
        statusPane.setPhase(gamePhase);

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
        }
        panel.repaint();
    }

    /**
     * This would happen if the user selectd to move the robber to a hex.
     * Take action based on placement.
     *
     */
    public void moveRobber(){
        //move the robber to a different hex

        gamePhase = "Moving robber...";
        statusPane.setPhase(gamePhase);
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
     * Distributes resource cards to each player based on the roll 
     * of the player in control's dice.
     *
     */
    public void distributeResources(){
        gamePhase = "Distributing resources...";
        statusPane.setPhase(gamePhase);
        String rType;
        //call method from hextiles or gameboard to determine how many of each
        //get resource cards based on the hex tiles that are 
        //adjacent to your settlement
        if(gameboardSet){
            for(int i = 0; i < players.size(); i++){
                ArrayList<City> c = players.get(i).getMyCities();
                //search list of player-owned cities
                for(int j = 0; j < c.size(); j++){
                    //if city token value == roll value

                    int tokVal = c.get(j).getTokenValue();

                    //get hex resource type to distribute;
                    rType = gameboard.getHexResourceValue(tokVal);
                    drawResourceCard(rType);
                    statusPane.setPlayers(players);

                }

            }
            gameboardSet = false;
            turnsStarted = true;
        }else{
            for(int i = 0; i < players.size(); i++){
                ArrayList<City> c = players.get(i).getMyCities();
                //search list of player-owned cities
                for(int j = 0; j < c.size(); j++){
                    //if city token value == roll value
                    if(c.get(j).getTokenValue() == roll){
                        //get hex resource type to distribute;
                        rType = gameboard.getHexResourceValue(roll);
                        drawResourceCard(rType);
                        statusPane.setPlayers(players);
                    }
                }
            }
        }
    }

    /**
     * Dialog for players to trade resource cards
     *
     */
    public void tradeResourcesDialog(){
        gamePhase = "Trading...";

        statusPane.setPhase(gamePhase);

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
        }
        statusPane.setPlayers(players);
        panel.repaint();
    }

    /**
     * Exchanges cards between two players
     *
     */
 public String swapCards(){
        //trading can only happen with the active player on a turn
        gamePhase = "Trading...";
        statusPane.setPhase(gamePhase);

        String[] options;
        String choice = "";
        if(turn == 1){
            if(players.get(0).getResourceHand().size()>0){
                options = new String[players.get(0).getResourceHand().size()];
                for(int i = 0; i < players.get(0).getResourceHand().size(); i++){
                    options[i] = players.get(0).getResourceHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to trade",
                        "Trading interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                choice = options[answer];

                //useResourceCard(choice);
            }
        }else if(turn == 2){
            if(players.get(1).getDevelopmentHand().size()>0){
                options = new String[players.get(1).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(1).getDevelopmentHand().size(); i++){
                    options[i] = players.get(1).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to trade",
                        "Trading interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                choice = options[answer];

                //useDevelopmentCard(choice);
            }
        }else if(turn == 3){
            if(players.get(2).getDevelopmentHand().size()>0){
                options = new String[players.get(2).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(2).getDevelopmentHand().size(); i++){
                    options[i] = players.get(2).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to trade",
                        "Trading interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                choice = options[answer];

                //useDevelopmentCard(choice);
            }
        }else if(turn == 4){
            if(players.get(3).getDevelopmentHand().size()>0){
                options = new String[players.get(3).getDevelopmentHand().size()];
                for(int i = 0; i < players.get(3).getDevelopmentHand().size(); i++){
                    options[i] = players.get(3).getDevelopmentHand().get(i);
                }

                int answer = JOptionPane.showOptionDialog(null,
                        "Choose which card to trade",
                        "Trading  interface",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                //Choice is the answer in the array of the card to use
                choice = options[answer];

                //useDevelopmentCard(choice);
            } 

        }else{
            //Player has no development cards, show message.
            Object[] noCards = {"Ok"};
            int answer = JOptionPane.showOptionDialog(frame, "You have no development cards.","Catan",
                    JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                    icon,noCards,noCards[0]);
        }
        if(choice != ""){
            selectPlayerTrade(choice);
        }
        statusPane.setPlayers(players);
        panel.repaint();
        return choice;
    }

    /**
     * Player selected which card to trade in a previous dialog
     * Find a player to trade with (if a player has a card, 
     * it is assumed they will trade for simplicity's sake);
     * 
     * @param player1 Player initiating trade
     * @param player2 Player trading with
     * @return 
     */
    public void selectPlayerTrade(String choice){
        gamePhase = "Trading...";
        statusPane.setPhase(gamePhase);
        int i = 0;
        boolean traded = false;
        int answer=-1;
        while(i < players.size() && !traded){
            String[] options = new String[]{"Player 1","Player 2", "Player 3", "Player 4"};
            answer = JOptionPane.showOptionDialog(null,
                "Which player do you want to trade with?",
                "Trading interface",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[3]);
            if(players.get(answer).searchResourceCards(choice)){
                ResourceCard rc =players.get(answer).removeResourceCard(choice);
                players.get(turn + 1).addResourceCard(rc);
                traded = true;
                JOptionPane.showMessageDialog(null, "You traded the card!");
            }
            i++;
        }
        if(answer == 0){
            buildStart = true;
        }else{
            developmentTurn();
        }
        statusPane.setPlayers(players);
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
        gamePhase = "Building...";
        statusPane.setPhase(gamePhase);
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
            buildStart = true;
            JOptionPane.showMessageDialog(null,"Press space to end building");
            //Wait for user to build
        }else{
            developmentTurn();
        }
        panel.repaint();
    }

    /**
     * Players get a dialog box asking if they want to buy a development
     * card.
     *
     */
    public void buyDevelopmentDialog(){
        gamePhase = "Buying development card...";
        statusPane.setPhase(gamePhase);

        boolean cardsExist = false;
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
            //search for each type of card before purchase
            if(!players.get(turn+1).searchResourceCards("Ore")){
                if(!players.get(turn+1).searchResourceCards("Grain")){
                    if(!players.get(turn+1).searchResourceCards("Wool")){
                        cardsExist = true;
                    }
                }
            }

            if(cardsExist){

                //1 ore, 1 wool, 1 grain
                useResourceCard("Ore");
                useResourceCard("Grain");
                useResourceCard("Wool");
                drawDevelopmentCard();
                JOptionPane.showMessageDialog(null, "You spent an ore, grain & wood card!");
            }
        }
        statusPane.setPlayers(players);
        panel.repaint();

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
     * Asks player to select a development card to use.
     * If no, returns to the turn (which ends this player's turn).
     */
    public void selectDevelopmentDialog(){
        String devName = "";
        if(players.get(turn-1).devCardSize() > 0){
            devName = players.get(turn-1).useDevelopmentCard();
                    JOptionPane.showMessageDialog(null, "You used the "+ devName+ " card!");
        }else{
            JOptionPane.showMessageDialog(null, "You don't have a development card.");
        }

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
        gamePhase = "Drawing development card...";
        statusPane.setPhase(gamePhase);
        DevelopmentCard dc = developmentDeck.removeCard();
        String cardtype = dc.getType();

        //remove card from player's hand
        if(turn == 1){
            if(dc != null){
                players.get(0).addDevelopmentCard(dc);
                JOptionPane.showMessageDialog(null, "You got a " +cardtype+ " card!");
            }
        }else if(turn == 2){
            if(dc != null){
                players.get(1).addDevelopmentCard(dc);
                JOptionPane.showMessageDialog(null, "You got a " +cardtype+ " card!");
            }
        }else if(turn == 3){
            if(dc != null){
                players.get(2).addDevelopmentCard(dc);
                JOptionPane.showMessageDialog(null, "You got a " +cardtype+ " card!");
            }
        }else{
            if(dc != null){
                players.get(3).addDevelopmentCard(dc);
                JOptionPane.showMessageDialog(null, "You got a " +cardtype+ " card!");
            }
        }

    }

    /**
     * Draw a card from the resource card bank.
     *
     * if player has a city, gets two resource cards, else gets one card
     */
    public void drawResourceCard(String rType){
        gamePhase = "Drawing resource card...";
        statusPane.setPhase(gamePhase);
        ResourceCard rc = resourceDeck.removeCard(rType);

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
     *.
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
     * Checks for the longest road.
     *
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
     * Checks how many knights exist to determine if someone gets
     * the largest army card
     *
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

    //----------------------------------------------------------
    //Starts the main run method
    //----------------------------------------------------------

    /**
     * The main method that calls invoke later and loads all pictures used.
     *
     * @param args A parameter
     */
    public static void main(String args[]) {
        Dice.loadPic();
        ResourceCard.loadPic();
        DevelopmentCard.loadPic();
        Robber.loadPic();
        Table.loadPic();
        City.loadPic();

        //launch main thread that will manage the GUI
        javax.swing.SwingUtilities.invokeLater(new Catan());

    }
}

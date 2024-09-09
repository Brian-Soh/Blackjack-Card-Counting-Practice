package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.BlackJack;
import model.Dealer;
import model.GameDeck;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.decisions.*;

// NOTE: Code structure and command methodology
// adapted from Teller App in Construction 1. Save feature
// adapted from the JsonSerializationDemo. Graphic user interface
// adapted from SimpleDrawingPlayer-Starter. Card images retrieved from online.
public class BlackJackApp extends JFrame implements WindowListener {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/blackjack.json";
    private static final Color backgroundColour = new Color(53, 101, 77);
    private static final Font font = new Font("Ariel", Font.BOLD, 30);

    private BlackJack blackjack;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLabel status;
    private JTextField entry;
    private JPanel gamePanel;
    private Player newPlayer;
    private GameDeck newGameDeck;
    private Dealer newDealer;

    // Constructor
    // EFFECTS: runs the Blackjack game
    public BlackJackApp() {
        super("BlackJack App");
        addWindowListener(this);
        initializeFields();
        initializeGraphics();
        runGame();
    }

    // EFFECTS: returns the current Blackjack game
    public BlackJack getCurrentBlackJackGame() {
        return blackjack;
    }

    // MODIFIES: this
    // EFFECTS: initializes the fields
    public void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        newDealer = new Dealer();
        status = new JLabel("Welcome to Blackjack!", SwingConstants.CENTER);
        status.setFont(font);
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics
    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().setBackground(backgroundColour);
        getContentPane().add(status);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this, Player, Dealer, GameDeck, Hand
    // EFFECTS: initiates an instance of the Blackjack game
    private void runGame() {
        createStartTools();
    }

    // MODIFIES: this
    // EFFECTS: creates the start tools
    private void createStartTools() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(0, 1));
        gamePanel.setSize(new Dimension(0, 0));
        add(gamePanel, BorderLayout.SOUTH);
        new NewGame(this, gamePanel);
        new LoadGame(this, gamePanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a new player
    public void createNewPlayer() {
        gamePanel.removeAll();
        revalidate();
        entry = new JTextField();
        status.setText("Enter starting balance");
        gamePanel.add(entry);
        setVisible(true);
        entry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (Integer.valueOf(entry.getText()) > 0) {
                        newPlayer = new Player(Integer.valueOf(entry.getText()));
                        createNewGameDeck();
                    } else {
                        status.setText("Invalid Amount Entered. Please Enter A Positive Value!");
                    }
                }
            }

        });
    }

    // MODIFIES: this
    // EFFECTS: creates a new game deck
    public void createNewGameDeck() {
        gamePanel.removeAll();
        revalidate();
        entry = new JTextField();
        status.setText("Enter number of decks");
        gamePanel.add(entry);
        setVisible(true);
        entry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    newGameDeck = new GameDeck();
                    newGameDeck.createDeck(Integer.valueOf(entry.getText()));
                    initializeNewGame();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes a new game
    private void initializeNewGame() {
        blackjack = new BlackJack(newPlayer, newGameDeck, newDealer);
        status.setText("Select from:");
        createMenu();
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu
    private void createMenu() {
        gamePanel.removeAll();
        revalidate();
        new PlaceBet(this, gamePanel);
        new CheckBalance(this, gamePanel);
        new RunningCount(this, gamePanel);
        new TrueCount(this, gamePanel);
        new SaveGame(this, gamePanel);
        new LoadGame(this, gamePanel);
        setVisible(true);
    }

    // MODDIFIES: this, player
    // EFFECTS: prompts the player to place a bet
    public void placeBet() {
        gamePanel.removeAll();
        revalidate();
        entry = new JTextField();
        status.setText("Place Bet");
        gamePanel.add(entry);
        setVisible(true);
        entry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    blackjack.getPlayer().bet(Integer.valueOf(entry.getText()));
                    blackjack.playerHit();
                    blackjack.dealerHit();
                    blackjack.playerHit();
                    initializeGame();
                }
            }
        });
    }

    // EFFECTS: initializes the game after bet has been placed
    private void initializeGame() {
        status.setText("Current Bet: $" + blackjack.getPlayer().getBet());
        inGameMenu();
        setLayout(null);
        displayPlayerCards();
        displayDealerCards();
    }

    // displays the mid hand menu
    private void inGameMenu() {
        gamePanel.removeAll();
        revalidate();
        new Hit(this, gamePanel);
        new Stand(this, gamePanel);
        new DoubleDown(this, gamePanel);
        new CheckBalance(this, gamePanel);
        new RunningCount(this, gamePanel);
        new TrueCount(this, gamePanel);
        new SaveGame(this, gamePanel);
        new LoadGame(this, gamePanel);
        setVisible(true);
    }

    // EFFECTS: displays the cards of the player
    private void displayPlayerCards() {
        int displacement = 0;
        for (String card : blackjack.getPlayerHand()) {
            ImageIcon imageIcon = new ImageIcon(
                    new ImageIcon("resources/" + card + ".png").getImage().getScaledInstance(60, 86,
                            Image.SCALE_DEFAULT));
            JLabel label = new JLabel();
            label.setIcon(imageIcon);
            add(label);
            setVisible(true);
            label.setBounds(350 + displacement, 325, 60, 86);
            displacement += 65;
        }
    }

    // EFFECTS: displays the cards of the dealer
    private void displayDealerCards() {
        int displacement = 0;
        for (String card : blackjack.getDealerHand()) {
            ImageIcon imageIcon = new ImageIcon(
                    new ImageIcon("resources/" + card + ".png").getImage().getScaledInstance(60, 86,
                            Image.SCALE_DEFAULT));
            JLabel label = new JLabel();
            label.setIcon(imageIcon);
            add(label);
            setVisible(true);
            label.setBounds(350 + displacement, 100, 60, 86);
            displacement += 65;
        }
    }

    // EFFECTS: saves the blackjack game to file
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(blackjack);
            jsonWriter.close();
            status.setText("Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            status.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads blackjack game from file
    public void loadGame() {
        try {
            blackjack = jsonReader.read();
            status.setText("Loaded game From " + JSON_STORE);
            handleLoadGame();
        } catch (IOException e) {
            status.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: handles loaded game
    private void handleLoadGame() {
        if (blackjack.getPlayerTotal() > 0) {
            initializeGame();
        } else {
            clearCurrentHand();
        }
    }

    // MODIFIES: player
    // EFFECTS: player hits
    public void hit() {
        blackjack.playerHit();
        displayPlayerCards();
        if (blackjack.getPlayerTotal() > 21) {
            playerBusts();
        } else {
            status.setText("Player Hits!");
        }
    }

    // EFFECTS: player stands and deals to the dealer
    public void stand() {
        status.setText("Player Stands");
        handleDealer();
    }

    // MODIFIES: this, player
    // EFFECTS: player doubles their bet
    public void doubleDown() {
        int newBet = 2 * blackjack.getPlayer().getBet();
        blackjack.getPlayer().addBalance(blackjack.getPlayer().getBet());
        blackjack.getPlayer().bet(newBet);
        blackjack.playerHit();
        displayPlayerCards();
        if (blackjack.getPlayerTotal() > 21) {
            playerBusts();
        } else {
            status.setText("Player Doubles Down!");
            handleDealer();
        }
    }

    // EFFECTS: handles dealer's decisions
    private void handleDealer() {
        while (blackjack.getDealerTotal() < 17) {
            blackjack.dealerHit();
            displayDealerCards();
            status.setText("Dealer Hits!");
        }
        if (blackjack.getDealerTotal() > 21) {
            dealerBusts();
        } else {
            handleOutcome();
        }
    }

    // EFFECTS: handles the outcome of the game
    private void handleOutcome() {
        if (blackjack.getPlayerTotal() > blackjack.getDealerTotal()) {
            playerWins();
        } else if (blackjack.getDealerTotal() == blackjack.getPlayerTotal()) {
            push();
        } else {
            dealerWins();
        }
    }

    // EFFECTS: displays a message indicating that the player has won
    private void playerWins() {
        status.setText("Player Wins!");
        blackjack.getPlayer().addBalance(2 * blackjack.getPlayer().getBet());
        gamePanel.removeAll();
        revalidate();
        new ContinueGame(this, gamePanel);
        setVisible(true);
    }

    // EFFECTS: displays a message indicating that there has been a tie
    private void push() {
        status.setText("It's a Tie, Push!");
        blackjack.getPlayer().addBalance(blackjack.getPlayer().getBet());
        gamePanel.removeAll();
        revalidate();
        new ContinueGame(this, gamePanel);
        setVisible(true);
    }

    // EFFECTS: displays a message indicating that the dealer has won
    private void dealerWins() {
        status.setText("Dealer Wins!");
        gamePanel.removeAll();
        revalidate();
        new ContinueGame(this, gamePanel);
        setVisible(true);
    }

    // EFFECTS: displays a message indicating that the dealer has bust
    private void dealerBusts() {
        status.setText("Dealer Busts, You Win!");
        blackjack.getPlayer().addBalance(2 * blackjack.getPlayer().getBet());
        gamePanel.removeAll();
        revalidate();
        new ContinueGame(this, gamePanel);
        setVisible(true);
    }

    // EFFECTS: displays a message indicating that the player has bust
    private void playerBusts() {
        status.setText("Player Busts!");
        gamePanel.removeAll();
        revalidate();
        new ContinueGame(this, gamePanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: clears all components and goes back to the main menu
    // if player balance is zero or less, goes to the out of funds screen
    public void clearCurrentHand() {
        blackjack.getPlayer().clearHand();
        blackjack.getDealer().clearHand();
        blackjack.getPlayer().setBet(0);
        getContentPane().removeAll();
        revalidate();
        repaint();
        initializeFields();
        initializeGraphics();
        createStartTools();
        if (blackjack.playerOutOfFunds()) {
            status.setText("Out of funds! Come again next time!");
        } else {
            createMenu();
            checkBalance();
        }
    }

    public void checkBalance() {
        status.setText("Current Balance: $" + blackjack.getPlayer().getBalance());
    }

    public void checkRunningCount() {
        status.setText("Running Count: " + blackjack.getGameDeck().getRunningCount());
    }

    public void checkTrueCount() {
        status.setText("True Count: " + blackjack.getGameDeck().getTrueCount());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (blackjack != null) {
            blackjack.printLog();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}

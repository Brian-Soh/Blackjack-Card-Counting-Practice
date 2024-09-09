package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Represents a deck(s) of cards
public class GameDeck {
    private static final String[] VALUES = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king",
            "ace" };
    private static final String[] SUITS = { "spades", "hearts", "clubs", "diamonds" };
    private static final String[] PLUS1 = { "2", "3", "4", "5", "6" };
    private static final String[] MINUS1 = { "10", "jack", "queen", "king", "ace" };
    private static final ArrayList<String> PLUS1_LIST = new ArrayList<>(Arrays.asList(PLUS1));
    private static final ArrayList<String> MINUS1_LIST = new ArrayList<>(Arrays.asList(MINUS1));
    private int runningCount;
    private int trueCount;
    private ArrayList<Card> gameDeck;
    private Random rand = new Random();

    // Constructor
    // REQUIRES n > 0
    // EFFECT: creates n amounts of decks with 52 cards
    public GameDeck() {

        gameDeck = new ArrayList<Card>();
        runningCount = 0;
        trueCount = 0;
    }

    //MODIFIES: this, Event Log
    public void createDeck(int n) {
        int numCards = 52 * n;
        for (int i = 0; i < n; i++) {
            for (String v : VALUES) {
                for (String s : SUITS) {
                    Card card = new Card(v, s);
                    gameDeck.add(card);
                }
            }
            ;
        }
        EventLog.getInstance().logEvent(
                new Event("A game deck consisting of " + n + " decks (" + numCards + " cards) has been created!"));
    }

    // MODIFIES: this
    // EFFECT: deals a random card in the deck(s)
    // and removes it from the deck(s)
    public Card dealCard() {
        int i = rand.nextInt(this.getDeck().size());
        Card c = this.getDeck().get(i);
        gameDeck.remove(i);
        updateRunningCount(c);
        calculateTrueCount(runningCount);
        return c;
    }

    // MODIFIES: this
    // EFFECTS: updates the running count by the given card
    public void updateRunningCount(Card c) {
        if (PLUS1_LIST.contains(c.getValue())) {
            runningCount++;
        } else if (MINUS1_LIST.contains(c.getValue())) {
            runningCount--;
        }
    }

    // MODIFIES: this
    // EFFECTS: computes the true count with the given running count
    public void calculateTrueCount(int runningCount) {
        trueCount = Math.round(52 * runningCount / gameDeck.size());
    }

    // MODIFIES: this
    // EFFECT: adds card to deck
    public void addCard(Card card) {
        gameDeck.add(card);
    }

    public void setRunningCount(int i) {
        runningCount = i;
    }

    public void setTrueCount(int i) {
        trueCount = i;
    }

    public ArrayList<Card> getDeck() {
        return gameDeck;
    }

    public int getRunningCount() {
        return runningCount;
    }

    public int getTrueCount() {
        return trueCount;
    }
}

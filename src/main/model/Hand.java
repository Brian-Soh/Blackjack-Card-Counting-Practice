package model;

import java.util.ArrayList;
import java.util.Arrays;

// Represents the player's hand with cards
public class Hand {

    private static final String[] NUMBERCARDS = { "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] FACECARDS = { "10", "jack", "queen", "king" };
    private static final ArrayList<String> NUMBERCARDS_LIST = new ArrayList<>(Arrays.asList(NUMBERCARDS));
    private static final ArrayList<String> FACECARDS_LIST = new ArrayList<>(Arrays.asList(FACECARDS));
    private ArrayList<Card> hand;
    private int handTotal;
    private int numofAce;

    // Constructor
    // EFFECTS: creates a new hand with no cards
    public Hand() {
        handTotal = 0;
        numofAce = 0;
        hand = new ArrayList<Card>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given card to the hand
    public void hit(Card c) {
        hand.add(c);
        calculateTotal(c);
        handleAce();
    }

    // MODIFIES: this
    // EFFECTS: calculates the total of the hand with ace as 11
    public void calculateTotal(Card c) {
        if (NUMBERCARDS_LIST.contains(c.getValue())) {
            handTotal += Integer.valueOf(c.getValue());
        } else if (FACECARDS_LIST.contains(c.getValue())) {
            handTotal += 10;
        } else {
            handTotal += 11;
            numofAce++;
        }
    }

    // MODIFIES: this
    // EFFECTS: converts the ace's value to 1 if the total is above 21
    public void handleAce() {
        if (handTotal > 21 && numofAce > 0) {
            handTotal -= 10;
            numofAce--;
        }
    }

    public ArrayList<Card> getCards() {
        return hand;
    }
    
    public void setHandTotal(int i) {
        handTotal = i;
    }

    public void setNumOfAce(int i) {
        numofAce = i;
    }

    public void addCard(Card c) {
        hand.add(c);
        calculateTotal(c);
        handleAce();
    }

    public int getSize() {
        return hand.size();
    }

    public ArrayList<String> getCardsString() {
        ArrayList<String> cards = new ArrayList<>();
        for (Card c : hand) {
            cards.add(c.getCard());
        }
        return cards;
    }

    // MODIFIES: this
    // EFFECTS: clears hand
    public void clearHand() {
        this.hand = new ArrayList<Card>();
        this.handTotal = 0;
        this.numofAce = 0;
    }

    public int getHandTotal() {
        return handTotal;
    }

    public int getNumofAce() {
        return numofAce;
    }
}
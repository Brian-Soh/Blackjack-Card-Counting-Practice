package model;

// Represents the player with a balance (in dollars) and an empty hand
public class Player {

    private Hand hand;
    private int bet;
    private int balance;

    // CONSTRUCTOR
    // EFFECTS: creates a player with the given balance and an empty hand
    public Player(int balance) {
        this.balance = balance;
        hand = new Hand();
        bet = 0;
        EventLog.getInstance().logEvent(new Event("A player with $" + balance +" has been created!"));
    }

    // REQUIRES amount > 0
    // MODIFIES: this
    // EFFECTS: adds the given amount to the player's balance
    public void addBalance(int amount) {
        balance += amount;
    }

    // REQUIRES amount < balance
    // MODIFIES: this
    // EFFECTS: subtracts the given amount from the player's balance
    public void bet(int amount) {
        balance -= amount;
        bet = amount;
    }

    // MODIFIES: this
    // EFFECTS: clears the player's handd
    public void clearHand() {
        this.hand = new Hand();
    }

    public void addCard(Card c) {
        this.hand.addCard(c);
    }

    public void setHandTotal(int i) {
        this.hand.setHandTotal(i);
    }

    public void setHandAces(int i) {
        this.hand.setNumOfAce(i);
    }

    public void setBet(int i) {
        this.bet = i;
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    public Hand getHand() {
        return hand;
    }

    public int getHandTotal() {
        return hand.getHandTotal();
    }

    public int getNumOfAce() {
        return hand.getNumofAce();
    }

}

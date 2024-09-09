package model;

//Represents the dealer with an empty hand
public class Dealer {

    private Hand hand;

    // CONSTRUCTOR
    // EFFECTS: creates a dealer with an empty hand
    public Dealer() {
        hand = new Hand();
    }

    // MODIFIES: this
    // EFFECTS: clears dealer's hand
    public void clearHand() {
        this.hand = new Hand();
    }

    public void addCard(Card c) {
        hand.addCard(c);
    }

    public void setHandTotal(int i) {
        hand.setHandTotal(i);
    }

    public void setHandAces(int i) {
        hand.setNumOfAce(i);
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

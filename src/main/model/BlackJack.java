package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a blackjack game with a player, dealer and game deck
public class BlackJack implements Writable {
    private GameDeck gameDeck;
    private Player player;
    private Dealer dealer;

    // EFFECTS: constructs a blackjack game with a player, game deck and dealer
    public BlackJack(Player player, GameDeck gameDeck, Dealer dealer) {
        this.gameDeck = gameDeck;
        this.player = player;
        this.dealer = dealer;
        EventLog.getInstance().logEvent(new Event("The Blackjack game has started!"));
    }

    // MODIFIES: this, EventLog
    // EFFECTS: Deals a card from the gamedeck and puts it in the player's hand.
    // Creates an event to record this instance and logs it in the Event Log
    public void playerHit() {
        Card card = gameDeck.dealCard();
        player.getHand().hit(card);
        EventLog.getInstance().logEvent(new Event("A " + card.getCard() + " has been dealt to the Player!"));

    }

    // MODIFIES: this, Event Log
    // EFFECTS: Deals a card from the gamedeck and puts it in the dealer's hand.
    // Creates an event to record this instance and logs it in the Event Log
    public void dealerHit() {
        Card card = gameDeck.dealCard();
        dealer.getHand().hit(card);
        EventLog.getInstance().logEvent(new Event("A " + card.getCard() + " has been dealt to the Dealer!"));
    }

    public boolean playerOutOfFunds() {
        if (getPlayer().getBalance() <= 0) {
            EventLog.getInstance().logEvent(
                    new Event("Game Over! The player, dealer and game deck has been cleared!"));
            return true;
        }
        return false;

    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GameDeck getGameDeck() {
        return gameDeck;
    }

    public ArrayList<String> getDealerHand() {
        return dealer.getHand().getCardsString();
    }

    public ArrayList<String> getPlayerHand() {
        return player.getHand().getCardsString();
    }

    public int getPlayerTotal() {
        return player.getHandTotal();
    }

    public int getDealerTotal() {
        return dealer.getHandTotal();
    }

    // MODIFIES: Event Log
    // EFFECTS: converts blackjack game into a json file
    // Creates an event to record the deck size saved and logs it in the Event Log
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Player Balance", player.getBalance());
        json.put("Player Bet", player.getBet());
        json.put("Player Hand", playerHandToJson());
        json.put("Player Hand Total", player.getHandTotal());
        json.put("Player Ace Total", player.getNumOfAce());
        json.put("Dealer Hand", dealerHandToJson());
        json.put("Dealer Hand Total", dealer.getHandTotal());
        json.put("Dealer Ace Total", dealer.getNumOfAce());
        json.put("GameDeck", cardsToJson());
        json.put("Running Count", gameDeck.getRunningCount());
        json.put("True Count", gameDeck.getTrueCount());
        EventLog.getInstance().logEvent(
                new Event("A game deck consisting of " + gameDeck.getDeck().size() + " cards has been saved!"));
        return json;
    }

    // EFFECTS: returns cards in this deck as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : gameDeck.getDeck()) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns cards in player hand as a JSON array
    private JSONArray playerHandToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : player.getHand().getCards()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns cards in dealer hand as a JSON array
    private JSONArray dealerHandToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : dealer.getHand().getCards()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    public void printLog() {
        for (Event evt : EventLog.getInstance()) {
            System.out.println(evt.toString());
        }
    }

}

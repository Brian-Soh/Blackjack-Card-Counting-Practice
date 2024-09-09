package persistence;

import model.BlackJack;
import model.Card;
import model.Dealer;
import model.Event;
import model.EventLog;
import model.GameDeck;
import model.Player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads the blackjack game from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the blackjack game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BlackJack read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBlackJack(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses blackjack game from JSON object and returns it
    private BlackJack parseBlackJack(JSONObject jsonObject) {
        int balance = jsonObject.getInt("Player Balance");
        int playerTotal = jsonObject.getInt("Player Hand Total");
        int playerBet = jsonObject.getInt("Player Bet");
        int dealerTotal = jsonObject.getInt("Dealer Hand Total");
        int playerAces = jsonObject.getInt("Player Ace Total");
        int dealerAces = jsonObject.getInt("Dealer Ace Total");
        int runningCount = jsonObject.getInt("Running Count");
        int trueCount = jsonObject.getInt("True Count");
        Player player = new Player(balance);
        GameDeck gameDeck = new GameDeck();
        gameDeck.setRunningCount(runningCount);
        gameDeck.setTrueCount(trueCount);
        Dealer dealer = new Dealer();
        createDeck(gameDeck, jsonObject);
        createPlayerHand(player, jsonObject);
        createDealerHand(dealer, jsonObject);
        player.setHandTotal(playerTotal);
        dealer.setHandTotal(dealerTotal);
        player.setHandAces(playerAces);
        dealer.setHandAces(dealerAces);
        player.setBet(playerBet);
        BlackJack blackjack = new BlackJack(player, gameDeck, dealer);
        return blackjack;
    }

    // MODIFIES: player
    // EFFECTS: parses player hand from JSON object and adds them to player
    private void createPlayerHand(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Player Hand");
        for (Object json : jsonArray) {
            JSONObject card = (JSONObject) json;
            addCardToPlayerHand(player, card);
        }
    }

    // MODIFIES: player, Event Log
    // EFFECTS: parses player hand cards from JSON object and adds it to blackjack
    // game
    // Creates an event to record this instance and logs it in the Event Log
    private void addCardToPlayerHand(Player player, JSONObject jsonObject) {
        String value = jsonObject.getString("value");
        String suit = jsonObject.getString("suit");
        Card card = new Card(value, suit);
        player.addCard(card);
        EventLog.getInstance().logEvent(new Event("A " + card.getCard() + " has been dealt to the Player!"));
    }

    // MODIFIES: dealer
    // EFFECTS: parses dealer hand from JSON object and adds them to blackjack game
    private void createDealerHand(Dealer dealer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Dealer Hand");
        for (Object json : jsonArray) {
            JSONObject card = (JSONObject) json;
            addCardToDealerHand(dealer, card);
        }
    }

    // MODIFIES: dealer
    // EFFECTS: parses dealer hand cards from JSON object and adds it to blackjack
    // game
    // Creates an event to record this instance and logs it in the Event Log

    private void addCardToDealerHand(Dealer dealer, JSONObject jsonObject) {
        String value = jsonObject.getString("value");
        String suit = jsonObject.getString("suit");
        Card card = new Card(value, suit);
        dealer.addCard(card);
        EventLog.getInstance().logEvent(new Event("A " + card.getCard() + " has been dealt to the Dealer!"));
    }

    // MODIFIES: gd
    // EFFECTS: parses gameDeck cards from JSON object and adds them to the GameDeck
    // Creates an event to record this instance and logs it in the Event Log
    private void createDeck(GameDeck gd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("GameDeck");
        for (Object json : jsonArray) {
            JSONObject card = (JSONObject) json;
            addCardToDeck(gd, card);
        }
        EventLog.getInstance().logEvent(
                new Event("A game deck consisting of " + gd.getDeck().size() + " cards has been loaded"));
    }

    // MODIFIES: gd
    // EFFECTS: parses cards from JSON object and adds it to the GameDeck
    private void addCardToDeck(GameDeck gd, JSONObject jsonObject) {
        String value = jsonObject.getString("value");
        String suit = jsonObject.getString("suit");
        Card card = new Card(value, suit);
        gd.addCard(card);
    }
}

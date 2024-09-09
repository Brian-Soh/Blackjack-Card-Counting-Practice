package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a single card with a value and suit
public class Card implements Writable {

    private String value;
    private String suit;

    // Constructor
    // EFFECTS: Creates a card object with the given value and string
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getCard() {
        return value + "_of_" + suit;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    // EFFECTS: converts card to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("value", value);
        json.put("suit", suit);
        return json;
    }

}

package persistence;


import model.BlackJack;
import model.Dealer;
import model.GameDeck;
import model.Player;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;


// Reference: implemented from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest {
    BlackJack blackjack;
    Player p1;
    Dealer d1;
    GameDeck gd1;


    @BeforeEach
    public void runBefore() {
        p1 = new Player(1000);
        gd1 = new GameDeck();
        d1 = new Dealer();
        gd1.createDeck(2);
    }


    @Test
    void testWriterInvalidFile() {
        try {
            blackjack = new BlackJack(p1, gd1, d1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyWorkroom() {
        try {
            BlackJack blackjack = new BlackJack(p1, gd1, d1);
            JsonWriter writer = new JsonWriter("./data/testWriterUnplayedTwoDeck.json");
            writer.open();
            writer.write(blackjack);
            writer.close();


            JsonReader reader = new JsonReader("./data/testWriterUnplayedTwoDeck.json");
            blackjack = reader.read();
            assertEquals(1000, blackjack.getPlayer().getBalance());
            assertEquals(0, blackjack.getPlayer().getBet());
            assertEquals(104, blackjack.getGameDeck().getDeck().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterMidGame() {
        try {
            BlackJack blackjack = new BlackJack(p1, gd1, d1);
            blackjack.getPlayer().bet(20);
            blackjack.playerHit();
            blackjack.dealerHit();
            blackjack.playerHit();
            JsonWriter writer = new JsonWriter("./data/testWriterMidGame.json");
            writer.open();
            writer.write(blackjack);
            writer.close();


            JsonReader reader = new JsonReader("./data/testWriterMidGame.json");
            blackjack = reader.read();
            assertEquals(20, blackjack.getPlayer().getBet());
            assertEquals(2, blackjack.getPlayer().getHand().getSize());
            assertEquals(1, blackjack.getDealer().getHand().getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

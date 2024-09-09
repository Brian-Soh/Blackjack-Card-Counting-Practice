package persistence;


import model.BlackJack;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//Reference: implemented from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {
    BlackJack blackjack;

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            blackjack = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    
    @Test
    void testBlackJackMidGame() {
        JsonReader reader = new JsonReader("./data/testReaderMidGame.json");
        try {
            BlackJack blackjack = reader.read();
            assertEquals(225, blackjack.getPlayer().getBalance());
            assertEquals(153, blackjack.getGameDeck().getDeck().size());
            assertEquals(25, blackjack.getPlayer().getBet());
            assertEquals(1, blackjack.getGameDeck().getRunningCount());
            assertEquals(0, blackjack.getGameDeck().getTrueCount());
            assertEquals(25, blackjack.getPlayer().getBet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderUnplayed() {
        JsonReader reader = new JsonReader("./data/testReaderUnplayed.json");
        try {
            blackjack = reader.read();
            assertEquals(1000, blackjack.getPlayer().getBalance());
            assertEquals(104, blackjack.getGameDeck().getDeck().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}

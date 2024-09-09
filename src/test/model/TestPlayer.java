package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    Player player;

    @BeforeEach
    void runBefore() {
        player = new Player(1000);
    }

    @Test
    void testConstructor() {
        assertEquals(1000, player.getBalance());
        assertEquals(0, player.getHand().getCards().size());
        assertEquals(0, player.getHandTotal());
        assertEquals(0, player.getBet());
        assertEquals(0, player.getNumOfAce());
    }

    @Test
    void testAddBalance() {
        player.addBalance(500);
        assertEquals(1500, player.getBalance());
        player.addBalance(2000);
        assertEquals(3500, player.getBalance());
        player.addBalance(0);
        assertEquals(3500, player.getBalance());
    }

    @Test
    void testBet() {
        player.bet(500);
        assertEquals(500, player.getBalance());
        player.bet(0);
        assertEquals(500, player.getBalance());
    }

    @Test
    void testClearHand() {
        Card c1 = new Card("2", "D");
        Card c2 = new Card("A", "S");
        player.addCard(c1);
        player.addCard(c2);
        assertEquals(1, player.getNumOfAce());
        player.clearHand();
        assertEquals(0, player.getHandTotal());
        assertEquals(0, player.getNumOfAce());
    }

    @Test
    void testSetters() {
        assertEquals(0, player.getHandTotal());
        assertEquals(0, player.getNumOfAce());
        assertEquals(0, player.getBet());
        player.setHandTotal(10);
        player.setHandAces(1);
        player.setBet(50);
        assertEquals(10, player.getHandTotal());
        assertEquals(1, player.getNumOfAce());
        assertEquals(50, player.getBet());
    }
}
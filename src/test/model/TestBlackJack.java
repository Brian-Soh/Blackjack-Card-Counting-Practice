package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBlackJack {
    BlackJack blackjack;
    BlackJack noFundsBlackjack;
    Player p1;
    Player p2;
    Dealer d1;
    GameDeck gd1;

    @BeforeEach
    void runBefore() {
        p1 = new Player(1000);
        p2 = new Player(0);
        gd1 = new GameDeck();
        d1 = new Dealer();
        gd1.createDeck(2);
        blackjack = new BlackJack(p1, gd1, d1);
        noFundsBlackjack = new BlackJack(p2, gd1, d1);
    }

    @Test
    void testConstructor() {
        assertEquals(p1, blackjack.getPlayer());
        assertEquals(gd1, blackjack.getGameDeck());
        assertEquals(d1, blackjack.getDealer());
        assertEquals(0, blackjack.getPlayerTotal());
        assertEquals(0, blackjack.getDealerTotal());

    }

    @Test
    void testPlayerHit() {
        assertEquals(0, blackjack.getPlayerHand().size());
        blackjack.playerHit();
        assertEquals(1, blackjack.getPlayerHand().size());
        assertEquals(103, blackjack.getGameDeck().getDeck().size());
    }

    @Test
    void testDealerHit() {
        assertEquals(0, blackjack.getDealerHand().size());
        blackjack.dealerHit();
        assertEquals(1, blackjack.getDealerHand().size());
        assertEquals(103, blackjack.getGameDeck().getDeck().size());
    }

    @Test
    void testPlayerOutOfFunds() {
        assertTrue(noFundsBlackjack.playerOutOfFunds());
    }


}

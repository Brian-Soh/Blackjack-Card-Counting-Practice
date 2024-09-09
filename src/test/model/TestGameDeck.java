package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestGameDeck {

    GameDeck d1;
    GameDeck d2;

    @BeforeEach
    void runBefore() {
        d1 = new GameDeck();
        d2 = new GameDeck();
        d1.createDeck(1);
        d2.createDeck(2);
    }

    @Test
    void testConstructor() {
        assertEquals(52, d1.getDeck().size());
        assertEquals("3_of_spades", d1.getDeck().get(4).getCard());
        assertEquals("ace_of_diamonds", d1.getDeck().get(51).getCard());
        assertEquals(104, d2.getDeck().size());
        assertEquals("queen_of_spades", d2.getDeck().get(92).getCard());
    }

    @Test
    void testDealCard() {
        Card c = d1.dealCard();
        assertEquals(51, d1.getDeck().size());
        assertFalse(d1.getDeck().contains(c));
    }

    @Test
    void testDealCardMultiple() {
        Card c1 = d2.dealCard();
        Card c2 = d2.dealCard();
        assertEquals(102, d2.getDeck().size());
        assertFalse(d2.getDeck().contains(c1));
        assertFalse(d2.getDeck().contains(c2));
    }

    @Test
    void testUpdateRunningCount() {
        Card c1 = new Card("ace", "diamonds");
        d1.updateRunningCount(c1);
        assertEquals(-1, d1.getRunningCount());
        Card c2 = new Card("2", "spades");
        d1.updateRunningCount(c2);
        Card c3 = new Card("10", "clubs");
        d1.updateRunningCount(c3);
        assertEquals(-1, d1.getRunningCount());
        Card c4 = new Card("9", "clubs");
        d2.addCard(c4);
        Card c5 = new Card("8", "spades");
        d1.addCard(c5);
        assertEquals(-1, d1.getRunningCount());
    }

    @Test
    void testUpdateTrueCount() {
        Card c1 = new Card("ace", "diamonds");
        d2.updateRunningCount(c1);
        assertEquals(0, d1.getTrueCount());
        Card c2 = new Card("jack", "spades");
        d2.updateRunningCount(c2);
        Card c3 = new Card("10", "clubs");
        d2.updateRunningCount(c3);
        d2.calculateTrueCount(d2.getRunningCount());
        assertEquals(-1, d2.getTrueCount());
    }

    @Test
    void addCard() {
        Card c1 = new Card("ace", "diamonds");
        d1.addCard(c1);
        assertEquals(53, d1.getDeck().size());
    }

    @Test
    void testSetter() {
        assertEquals(0, d2.getRunningCount());
        assertEquals(0, d2.getTrueCount());
        d2.setRunningCount(-5);
        d2.setTrueCount(-2);
        assertEquals(-5, d2.getRunningCount());
        assertEquals(-2, d2.getTrueCount());
    }
}

package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHand {
    Hand h1;
    Hand h2;
    Card c1;
    Card c2;
    Card c3;
    Card c4;
    Card c5;

    @BeforeEach
    void runBefore() {
        h1 = new Hand();
        h2 = new Hand();
        c1 = new Card("2", "hearts");
        c2 = new Card("jack", "diamonds");
        c3 = new Card("ace", "spades");
        c4 = new Card("7", "diamonds");
        c5 = new Card("10", "clubs");
    }

    @Test
    void testConstructor() {
        assertEquals(0, h1.getHandTotal());
        assertEquals(0, h1.getNumofAce());
    }

    @Test
    void testAddCard() {
        h1.hit(c1);
        assertEquals(c1, h1.getCards().get(0));
        h1.hit(c2);
        h1.hit(c3);
        assertEquals(c3, h1.getCards().get(2));
    }

    @Test
    void testCalculateTotal() {
        h1.calculateTotal(c1);
        assertEquals(2, h1.getHandTotal());
        h1.calculateTotal(c2);
        assertEquals(12, h1.getHandTotal());
        h1.calculateTotal(c3);
        assertEquals(23, h1.getHandTotal());
        assertEquals(1, h1.getNumofAce());
    }

    @Test
    void testHandleAce() {
        h1.calculateTotal(c1);
        assertEquals(2, h1.getHandTotal());
        h1.calculateTotal(c2);
        assertEquals(12, h1.getHandTotal());
        h1.handleAce();
        h1.calculateTotal(c3);
        assertEquals(23, h1.getHandTotal());
        assertEquals(1, h1.getNumofAce());
        h1.handleAce();
        assertEquals(13, h1.getHandTotal());
        assertEquals(0, h1.getNumofAce());

        h2.calculateTotal(c1);
        h2.calculateTotal(c2);
        h2.calculateTotal(c5);
        assertEquals(22, h2.getHandTotal());
        h2.handleAce();
        assertEquals(22, h2.getHandTotal());

    }

    @Test
    void testGetCardsString() {
        h1.hit(c1);
        h1.hit(c2);
        h1.hit(c3);
        assertEquals("2_of_hearts", h1.getCardsString().get(0));
    }

    @Test
    void testClearHand() {
        h1.hit(c1);
        h1.hit(c2);
        h1.hit(c3);
        h1.clearHand();
        assertEquals(0, h1.getCards().size());
        assertEquals(0, h1.getHandTotal());

    }
}
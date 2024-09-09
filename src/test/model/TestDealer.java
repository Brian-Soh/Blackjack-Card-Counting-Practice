package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDealer {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void testConstructor() {
        assertEquals(0, dealer.getHand().getCards().size());
        assertEquals(0, dealer.getHandTotal());
        assertEquals(0, dealer.getNumOfAce());
    }

    @Test
    void testClearHand() {
        Card c1 = new Card("A", "C");
        dealer.addCard(c1);
        assertEquals(11, dealer.getHandTotal());
        assertEquals(1, dealer.getNumOfAce());
        dealer.clearHand();
        assertEquals(0, dealer.getHandTotal());
        assertEquals(0, dealer.getNumOfAce());
    }

    @Test
    void testSetters() {
        assertEquals(0, dealer.getHandTotal());
        assertEquals(0, dealer.getNumOfAce());
        dealer.setHandTotal(21);
        dealer.setHandAces(3);
        assertEquals(21, dealer.getHandTotal());
        assertEquals(3, dealer.getNumOfAce());
    }

}
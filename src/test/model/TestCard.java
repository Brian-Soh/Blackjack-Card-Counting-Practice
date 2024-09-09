package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCard {

    Card c1;

    @BeforeEach
    void runBefore() {
        c1 = new Card("2", "hearts");
    }

    @Test
    void testConstructor() {
        assertEquals(c1.getCard(), "2_of_hearts");
        assertEquals(c1.getValue(), "2");
        assertEquals(c1.getSuit(), "hearts");
        assertFalse(c1.getSuit().equals("spades"));
        assertFalse(c1.getValue().equals("3"));
        assertFalse(c1.getCard().equals("1_of_hearts"));
    }
}

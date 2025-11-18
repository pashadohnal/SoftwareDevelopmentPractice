package pokerDecks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pokerDecks.Card;

public class CardUnitTest {

    @Test
    public void testNumberCardValueAndToString() {
        Card c = new Card(Card.Suit.CLUB, Card.Face.FIVE);
        assertEquals(5, c.getValue(), "Five should have value 5");
        assertEquals("♣5", c.toString(), "toString should be suit symbol + face");
    }

    @Test
    public void testFaceCardsAreTen() {
        Card j = new Card(Card.Suit.SPADE, Card.Face.J);
        Card q = new Card(Card.Suit.HEART, Card.Face.Q);
        Card k = new Card(Card.Suit.DIAMOND, Card.Face.K);

        assertEquals(10, j.getValue(), "J should count as 10");
        assertEquals(10, q.getValue(), "Q should count as 10");
        assertEquals(10, k.getValue(), "K should count as 10");

        assertEquals("♠J", j.toString());
        assertEquals("♥Q", q.toString());
        assertEquals("♦K", k.toString());
    }

    @Test
    public void testTenDisplaysAs10() {
        Card t = new Card(Card.Suit.HEART, Card.Face.TEN);
        assertFalse(t.isAce(), "TEN should not be recognized as Ace");
        assertEquals(10, t.getValue(), "TEN should count as 10");
        assertEquals("♥10", t.toString(), "TEN should display as 10");
    }

    @Test
    public void testAceBehavior() {
        Card a = new Card(Card.Suit.SPADE, Card.Face.A);
        assertTrue(a.isAce(), "A should be recognized as Ace");
        assertEquals(1, a.getValue(), "Ace base value should be 1");
        assertEquals("♠A", a.toString(), "Ace toString should include 'A'");
    }

}
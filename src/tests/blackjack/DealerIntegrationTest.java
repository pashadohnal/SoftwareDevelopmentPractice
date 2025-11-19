package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pokerDecks.Card;
import pokerDecks.Decks;

import java.util.ArrayList;

public class DealerIntegrationTest {

    @Test
    public void testDealerDrawsUntilThreshold() {
        ArrayList<Card> cards = new ArrayList<>();
        // Use identical small-value cards so shuffle doesn't affect total value ordering
        cards.add(new Card(Card.Suit.HEART, Card.Face.FIVE));
        cards.add(new Card(Card.Suit.HEART, Card.Face.FIVE));
        cards.add(new Card(Card.Suit.HEART, Card.Face.FIVE));
        cards.add(new Card(Card.Suit.HEART, Card.Face.FIVE));

        Decks decks = new Decks(1, cards);
        Dealer dealer = new Dealer(decks);

        int draws = 0;
        while (dealer.drawCard()) {
            draws++;
            // protect against accidental infinite loops in test
            if (draws > 10) break;
        }

        // With four FIVEs (5*4) dealer should draw 4 times and reach 20
        assertEquals(4, draws, "Dealer should draw exactly 4 times to reach >=17");
        assertEquals(20, dealer.getValue(), "Dealer's value after drawing should be 20");
    }

    @Test
    public void testResetClearsHandAndValue() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADE, Card.Face.TEN));
        Decks decks = new Decks(1, cards);

        Dealer dealer = new Dealer(decks);
        assertTrue(dealer.drawCard(), "Dealer should draw the single card available");
        assertTrue(dealer.getValue() > 0, "Precondition: dealer has non-zero hand value after draw");

        dealer.reset();
        assertEquals(0, dealer.getValue(), "After reset, dealer's value should be 0");
        assertTrue(dealer.handToString(false).contains("Value:0"), "handToString after reset should indicate Value:0");
    }

}
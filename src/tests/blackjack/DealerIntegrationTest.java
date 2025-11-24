package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pokerDecks.Card;
import pokerDecks.Decks;

import java.util.ArrayList;

public class DealerIntegrationTest {
	@Test
	public void testDealerInitialDraw() {
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(Card.Suit.SPADE, Card.Face.TEN));

        Decks decks = new Decks(1, cards);

		Dealer dealer = new Dealer(decks);
		assertTrue(dealer.drawCard(), "Dealer should successfully draw the first card");
		assertEquals(10, dealer.getValue(), "Dealer's value after first draw should be 10");
		decks.reset();
		cards.clear();
		cards.add(new Card(Card.Suit.SPADE, Card.Face.TEN));
		cards.add(new Card(Card.Suit.HEART, Card.Face.SEVEN));
		decks = new Decks(1, cards);
		dealer = new Dealer(decks);
		assertTrue(dealer.drawCard(), "Dealer should successfully draw the first card");
		assertTrue(dealer.drawCard(), "Dealer should successfully draw the second card");
		assertEquals(17, dealer.getValue(), "Dealer's value after second draw should be 17");
	}
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
        }

      
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
        

        dealer.reset();
        assertEquals(0, dealer.getValue(), "After reset, dealer's value should be 0");
        assertTrue(dealer.handToString(false).contains("Value:0"), "handToString after reset should indicate Value:0");
    }
    @Test
	public void testDealerHandlesAceValueCorrectly() {
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(Card.Suit.HEART, Card.Face.A));
		Decks decks = new Decks(1, cards);

		Dealer dealer = new Dealer(decks);
		assertTrue(dealer.drawCard(), "Dealer should draw the Ace");
		assertEquals(11, dealer.getValue(), "Dealer's value with Ace should be 11 initially");
		decks.reset();
		cards.clear();
		cards.add(new Card(Card.Suit.SPADE, Card.Face.A));
		cards.add(new Card(Card.Suit.DIAMOND, Card.Face.SIX));
		decks = new Decks(1, cards);
		dealer = new Dealer(decks);
		assertTrue(dealer.drawCard());
		assertTrue(dealer.drawCard());
		assertEquals(17, dealer.getValue(), "Dealer's value should adjust Ace to 1, total 17");
		String showHand = dealer.handToString(false);
		assertTrue(showHand.contains("♠A"), "handToString should include the Ace");
		assertTrue(showHand.contains("♦6"), "handToString should include the Six");
	}
    @Test
    public void testDealerHideCard() {
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(new Card(Card.Suit.SPADE, Card.Face.TEN));
		cards.add(new Card(Card.Suit.SPADE, Card.Face.TEN));
		Decks decks = new Decks(1, cards);

		Dealer dealer = new Dealer(decks);
		assertTrue(dealer.drawCard(), "Dealer should draw the first card");
		assertTrue(dealer.drawCard(), "Dealer should draw the second card");

		String hiddenHand = dealer.handToString(true);
		assertEquals("♠10 Hidden", hiddenHand, "handToString with hide=true should show first card and 'Hidden'");
    }
   
    
    

}

package blackjack.traits;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pokerDecks.Decks;
import pokerDecks.Card;

import java.util.ArrayList;

public class PlayCardUnitTest {

    @Test
    public void testDrawSingleNonAceCard() {
        ArrayList<Card> deck = new ArrayList<>();
        Card fiveHearts = new Card(Card.Suit.HEART, Card.Face.FIVE);
        deck.add(fiveHearts);
        Decks decks = new Decks(1, deck);

        PlayCard player = new PlayCard(decks);
        player.drawCard();

        assertEquals(1, player.gethandsize(), "Hand size should be 1 after one draw");
        assertEquals(5, player.getValue(), "Value should equal the card's blackjack value");
        String s = player.handToString(false);
        assertTrue(s.contains("Value:5"), "handToString should include the numeric value");
        assertTrue(s.contains(fiveHearts.toString()), "handToString should include the card's toString");
    }

    @Test
    public void testHandToStringHideShowsHidden() {
        ArrayList<Card> deck = new ArrayList<>();
        Card sevenClubs = new Card(Card.Suit.CLUB, Card.Face.SEVEN);
        deck.add(sevenClubs);
        Decks decks = new Decks(1, deck);

        PlayCard player = new PlayCard(decks);
        player.drawCard();

        String hidden = player.handToString(true);
        assertTrue(hidden.endsWith(" Hidden"), "Hidden hand string should end with ' Hidden'");
        assertTrue(hidden.startsWith(sevenClubs.toString()), "Hidden hand string should start with the first card's toString");
    }

    @Test
    public void testAcePlusNineCountsAsTwenty() {
        ArrayList<Card> deck = new ArrayList<>();
        Card aceSpade = new Card(Card.Suit.SPADE, Card.Face.A);
        Card nineDiamond = new Card(Card.Suit.DIAMOND, Card.Face.NINE);
        deck.add(aceSpade);
        deck.add(nineDiamond);
        Decks decks = new Decks(1, deck);

        PlayCard player = new PlayCard(decks);
        player.drawCard();
        player.drawCard();

        assertEquals(2, player.gethandsize(), "Hand size should be 2 after two draws");
        assertEquals(20, player.getValue(), "Ace + 9 should be counted as 20 (Ace as 11)");
    }

    @Test
    public void testTwoAcesCountAsTwelve() {
        ArrayList<Card> deck = new ArrayList<>();
        Card ace1 = new Card(Card.Suit.HEART, Card.Face.A);
        Card ace2 = new Card(Card.Suit.CLUB, Card.Face.A);
        deck.add(ace1);
        deck.add(ace2);
        Decks decks = new Decks(1, deck);

        PlayCard player = new PlayCard(decks);
        player.drawCard();
        player.drawCard();

        assertEquals(12, player.getValue(), "Two aces should be counted as 12");
    }

    @Test
    public void testResetClearsHandAndValue() {
        ArrayList<Card> deck = new ArrayList<>();
        Card fiveHearts = new Card(Card.Suit.HEART, Card.Face.FIVE);
        Card ace = new Card(Card.Suit.SPADE, Card.Face.A);
        deck.add(fiveHearts);
        deck.add(ace);
        Decks decks = new Decks(1, deck);

        PlayCard player = new PlayCard(decks);
        player.drawCard();
        player.drawCard();

        assertEquals(2, player.gethandsize(), "Precondition: two cards drawn");
        assertTrue(player.getValue() > 0, "Precondition: value should be > 0 after draws");

        player.reset();

        assertEquals(0, player.gethandsize(), "After reset, hand size should be 0");
        assertEquals(0, player.getValue(), "After reset, value should be 0");
        assertTrue(player.handToString(false).contains("Value:0"), "handToString for empty hand should indicate Value:0");
    }

}
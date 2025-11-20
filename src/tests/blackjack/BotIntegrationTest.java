package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pokerDecks.Card;
import pokerDecks.Decks;
import blackjack.strategies.Dn;
import blackjack.strategies.Bn;

import java.util.ArrayList;

public class BotIntegrationTest {

    @Test
    public void testPlaceBetAndWinIncreasesBalance() {
        ArrayList<Card> cards = new ArrayList<>();
        // one card of value 10
        cards.add(new Card(Card.Suit.HEART, Card.Face.TEN));
        Decks decks = new Decks(1, cards);

        Bot bot = new Bot("", decks, 100, new Dn(18), new Bn(10));
        int bet = bot.placeBet();
        assertEquals(10, bet, "Bot should place 10% of 100 when using Bn(10)");

        // draw the card (value 10)
        assertTrue(bot.drawCard(), "Bot should draw when threshold allows");
        assertEquals(10, bot.getValue(), "After drawing TEN, bot value should be 10");

        // dealer had 9 -> bot wins -> balance increases by bet
        bot.updateBalance(9);
        assertEquals(110, bot.getBalance(), "After winning, balance should increase by the bet amount");
    }

    @Test
    public void testDrawBehaviorBasedOnDnThreshold() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Face.FIVE));
        cards.add(new Card(Card.Suit.SPADE, Card.Face.FIVE));
        Decks decks = new Decks(1, cards);

        Bot bot = new Bot("", decks, 100, new Dn(18), new Bn(10));
        // Dn(18) will request draws while value < 18
        assertTrue(bot.drawCard(), "First draw should occur");
        assertTrue(bot.drawCard(), "Second draw should occur because value remains below 18");
        assertEquals(10, bot.getValue(), "Two FIVEs should sum to 10");

        // Dn(0) should never draw (value 0 is not less than 0)
        Decks decks2 = new Decks(1, cards);
        Bot bot2 = new Bot("", decks2, 100, new Dn(0), new Bn(10));
        assertFalse(bot2.drawCard(), "With n=0, drawCard should return false");
        assertEquals(0, bot2.getValue(), "No card should be drawn when Dn(0) is used");
    }

    @Test
    public void testMakeBotsCreatesCorrectNumberWithInitialBalance() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Face.FIVE));
        Decks decks = new Decks(1, cards);

        ArrayList<Bot> bots = Bot.makeBots(decks, 50, 5);
        assertEquals(5, bots.size(), "makeBots should return the requested number of bots");
        for (Bot b : bots) {
            assertEquals(50, b.getBalance(), "Each created bot should have the initial balance provided");
        }
    }

}
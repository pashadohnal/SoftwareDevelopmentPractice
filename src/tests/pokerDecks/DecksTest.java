package pokerDecks;

import org.junit.jupiter.api.Test;

import pokerDecks.Card;
import pokerDecks.Decks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DecksTest {

    @Test
    public void testSingleDeckUniqueness() {
        Decks decks = new Decks(1, Card.genDeck());
        List<String> drawn = new ArrayList<>();

        // Draw all cards from the single deck
        for (int i = 0; i < 52; i++) {
            Card c = decks.draw();
            assertNotNull(c, "draw() should not return null for valid deck");
            drawn.add(c.toString());
        }

        // Check that all drawn cards are unique (by their string representation)
        long uniqueCount = drawn.stream().distinct().count();
        assertEquals(52, uniqueCount, "Single deck should have 52 unique cards");
    }

    @Test
    public void testMultipleDeckTotalDrawCount() {
        Decks decks = new Decks(2, Card.genDeck());
        int totalDraws = 104;

        // Draw exactly totalDraws cards and ensure each draw returns a card
        for (int i = 0; i < totalDraws; i++) {
            assertNotNull(decks.draw(), "draw() should return a Card while cards remain");
        }

        // Drawing one more should fail (out of bounds)
        assertThrows(IndexOutOfBoundsException.class, () -> decks.draw());
    }

    @Test
    public void testResetBehavior() {
        Decks decks = new Decks(1, Card.genDeck());

        // Consume entire deck
        for (int i = 0; i < 52; i++) {
            decks.draw();
        }

        // Deck should be empty now
        assertThrows(IndexOutOfBoundsException.class, () -> decks.draw(), "Drawing after consuming all cards should throw");

        // Reset and ensure we can draw again
        decks.reset();
        for (int i = 0; i < 52; i++) {
            assertNotNull(decks.draw(), "After reset, draw() should return cards again");
        }
    }

    @Test
    public void testZeroDeckEdgeCase() {
        Decks decks = new Decks(0, Card.genDeck());

        // Drawing from a zero-deck should throw
        assertThrows(IndexOutOfBoundsException.class, () -> decks.draw());
    }
}
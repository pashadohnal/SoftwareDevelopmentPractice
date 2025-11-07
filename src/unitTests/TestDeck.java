package unitTests;
import org.junit.jupiter.api.Test;

import blackjack.Decks;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestDeck {
    @Test
    public void testDraw() {
    	Decks decks = new Decks();
    	ArrayList<Character> cards = decks.getDeck();
    	
    	// check can Deck get first card
    	assertEquals(decks.draw(), cards.get(0));
    	// check can Deck get second card
    	assertEquals(decks.draw(), cards.get(1));
    }
}

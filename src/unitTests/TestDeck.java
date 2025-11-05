package unitTests;
import org.junit.jupiter.api.Test;

import blackjack.Decks;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestDeck {
    @Test
    public void testDraw() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	
    	// check can Deck get first card
    	assertEquals(Decks.draw(), cards.get(0));
    	
    	// check can Deck get second card
    	assertEquals(Decks.draw(), cards.get(1));
    }
}

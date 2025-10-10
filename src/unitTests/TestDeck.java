package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import deck.Deck;

public class TestDeck {
    @Test
    public void testDraw() {
    	ArrayList<Character> cards = Deck.getDeck();
    	
    	// check can Deck get first card
    	assertEquals(Deck.draw(), cards.get(0));
    	
    	// check can Deck get second card
    	assertEquals(Deck.draw(), cards.get(1));
    	
    	
    }
}

package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import blackjack.Deck;

public class TestDeck {
    @Test
    public void testDraw() {
    	ArrayList<Character> cards = Deck.getDeck();
    	
    	// check can Deck get first card
    	assertEquals(Deck.draw(), cards.get(0));
    	
    	// check can Deck get second card
    	assertEquals(Deck.draw(), cards.get(1));
    }
    
    @Test
    public void testCal() {
    	ArrayList<Character> hand = new ArrayList<>();
    	hand.add('K');
    	assertEquals(Deck.calValue(hand), 10);
    	hand.add('A');
    	assertEquals(Deck.calValue(hand), 21);
    	hand.add('A');
    	assertEquals(Deck.calValue(hand), 22);
    	hand.add('Q');
    	assertEquals(Deck.calValue(hand), 32);
    	hand.add('J');
    	assertEquals(Deck.calValue(hand), 42);
    	hand.add('5');
    	assertEquals(Deck.calValue(hand), 47);
    }
}

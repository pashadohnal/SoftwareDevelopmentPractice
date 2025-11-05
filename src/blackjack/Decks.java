package blackjack;

import java.util.*;

// Using Singleton Design Pattern
public class Decks {
    public static final List<Character> RANKS = Arrays.asList(
            'A', '2', '3', '4', '5', '6', '7', '8', '9', 'J', 'Q', 'K'
        );

    
    private static ArrayList<Character> DECK = new ArrayList<>();

    private Decks() {
    	for (int i=0; i<Blackjack.noOfDeckUsed; i++) {
            DECK.addAll(RANKS);
            DECK.addAll(RANKS);
            DECK.addAll(RANKS);
            DECK.addAll(RANKS);
    	}
        Collections.shuffle(DECK);
    }

    private static Decks deck = new Decks();
    
    static private int index =0;
    
    static public char draw() {
    	return DECK.get(index++);
    }
    
    static public void reset() {
    	Decks.deck = new Decks();
    	index = 0;
    }
        
    // ----- FOR DEBUG AND TESTING ONLY -----
    
    // get the whole deck
    static public ArrayList<Character> getDeck() {
        return new ArrayList<>(DECK);
    }
}




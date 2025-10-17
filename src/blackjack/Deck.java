package blackjack;

import java.util.*;

// Using Singleton Design Pattern
public class Deck {
    private final Character[] RANKS = {'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'J', 'Q', 'K'};

    private final static ArrayList<Character> DECK = new ArrayList<>();

    private Deck() {
        DECK.addAll(Arrays.asList(RANKS));
        DECK.addAll(Arrays.asList(RANKS));
        DECK.addAll(Arrays.asList(RANKS));
        DECK.addAll(Arrays.asList(RANKS));
        Collections.shuffle(DECK);
    }

    private static final Deck deck = new Deck();
    
    // ----- FOR OTHERS -----
    
    // draw card
    static private int index =0;
    
    static public char draw() {
    	return DECK.get(index++);
    }
    
    // cal values
    // TODO:　it is better to let the player class to calculate the value
    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<=21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10;
		else return (int)card - '0';
    }
    
    // ----- FOR DEBUG -----
    
    // get the whole deck
    static public ArrayList<Character> getDeck() {
        return new ArrayList<>(DECK);
    }
}


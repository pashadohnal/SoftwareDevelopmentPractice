package deck;

import java.util.*;

public class Deck {
    private final Character[] RANKS = {'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'J', 'Q', 'K'};

    private final ArrayList<Character> DECK = new ArrayList<>();

    private Deck() {
        DECK.addAll(Arrays.asList(RANKS));
        DECK.addAll(Arrays.asList(RANKS));
        DECK.addAll(Arrays.asList(RANKS));
        DECK.addAll(Arrays.asList(RANKS));
        Collections.shuffle(DECK);
    }

    private static final Deck deck = new Deck();

    // get the whole deck
    public ArrayList<Character> getDeck() {
        return new ArrayList<>(DECK);
    }
    
    // draw card
    private int index =0;
    
    public char draw() {
    	return DECK.get(index++);
    }
}


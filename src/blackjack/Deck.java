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
    // TODO:　ｉｔ　ｉｓ　ｂｅｔｔｅｒ　ｔｏ　ｌｅｔ　ｐｌａｙｅｒ　ｃｏｕｎｔ　ｔｈｅ　ｖａｌｕｅ　ｏｆ　ｃａｒｄｓ
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
	public static Object calValue(ArrayList<Character> hand) {
        if (hand == null || hand.isEmpty()) {
            return 0;
        }
        int value = 0;
        int aceCount = 0;
        for (Character hands : hand) {
            if (hands == 'A') {
                aceCount++;
                value += 1; // Start with Ace as 1
            } else if (hands == 'J' || hands == 'Q' || hands == 'K') {
                value += 10;
            } else if (hands >= '2' && hands <= '9') {
                value += hands - '0';
            } else {
                value +=0; 
            }
        }
		// if ACE do not exceeds 21, add it up, when a+a will return 12, a+a+k will return 12
        for (int i = 0; i < aceCount && value <= 21; i++) {
            if (value + 10 <= 21) {
                value += 10;
            }
        }
        if (value > 21) {
            return "BUST";
        }
        return value;
    }
    
}


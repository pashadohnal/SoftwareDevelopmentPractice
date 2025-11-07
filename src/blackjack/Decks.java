package blackjack;

import java.util.*;

public class Decks {

<<<<<<< HEAD
    
    private static ArrayList<Character> DECK = new ArrayList<>();
=======
    public static final String[] SUITS = {"♠", "♥", "♦", "♣"};
    public static final List<String> RANKS = Arrays.asList(
    	    "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    	);
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git

    private static final ArrayList<String> DECK = new ArrayList<>();
    private static int index = 0;

    static {
        buildDeck();
    }

    private static void buildDeck() {
        DECK.clear();
        for (int i = 0; i < Blackjack.noOfDeckUsed; i++) {
            for (String suit : SUITS) {
                for (String rank : RANKS) {
                    DECK.add(suit + rank);
                }
            }
        }
        Collections.shuffle(DECK);
        index = 0;
    }

<<<<<<< HEAD
    private static Decks deck = new Decks();
    
    static private int index =0;
    
    static public char draw() {
    	return DECK.get(index++);
=======
    public static String draw() {
        if (index >= DECK.size()) {
            buildDeck(); // reshuffle when deck is empty
        }
        return DECK.get(index++);
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git
    }
<<<<<<< HEAD
    
    static public void reset() {
    	Decks.deck = new Decks();
    	index = 0;
=======

    public static void reset() {
        buildDeck();
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git
    }
<<<<<<< HEAD
        
    // ----- FOR DEBUG AND TESTING ONLY -----
    
    // get the whole deck
    static public ArrayList<Character> getDeck() {
=======

    public static ArrayList<String> getDeck() {
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git
        return new ArrayList<>(DECK);
    }
}
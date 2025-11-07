package blackjack;

import java.util.*;

public class Decks {

    public static final String[] SUITS = {"♠", "♥", "♦", "♣"};
    public static final List<String> RANKS = Arrays.asList(
    	    "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    	);

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

    public static String draw() {
        if (index >= DECK.size()) {
            buildDeck(); // reshuffle when deck is empty
        }
        return DECK.get(index++);
    }

    public static void reset() {
        buildDeck();
    }

    public static ArrayList<String> getDeck() {
        return new ArrayList<>(DECK);
    }
}
package blackjack;

import java.util.*;

/**
 * Represent decks for the game
 * Note that multiple decks is used to prevent card counting
 */
public class Decks {
	/**
	 * Store the shuffled cards
	 */
	private ArrayList<Card> cards = new ArrayList<>();;
	
	/**
	 * Record the index of the next card to be drawn
	 */
	private int index = 0;
	
	/**
	 * Constructor of the class
	 * @param noOfDeckUsed
	 */
	public Decks(int noOfDeckUsed, ArrayList<Card> deck) {
		for (int i=0; i<noOfDeckUsed; i++) {
			cards.addAll(deck);
		}
		reset();
	}
	
	public Card draw() {
		return cards.get(index++);
	}
	
	public void reset() {
		Collections.shuffle(cards);
		index =0;	
	}
}
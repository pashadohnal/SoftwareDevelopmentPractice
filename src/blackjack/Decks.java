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
	 * @return a whole ordered poker deck
	 */
	private ArrayList<Card> genDeck() {
		ArrayList<Card> cards = new ArrayList<>();
		for (char suit : Arrays.asList('♠', '♥', '♦', '♣')) {
			for (int face = 1; face <= 13; face++) {
			    cards.add(new Card(suit, face));
			}

		}
		return cards;
	}
	
	/**
	 * Constructor of the class
	 * @param noOfDeckUsed
	 */
	public Decks(int noOfDeckUsed) {
		for (int i=0; i<noOfDeckUsed; i++) {
			cards.addAll(genDeck());
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




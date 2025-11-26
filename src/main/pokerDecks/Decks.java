package pokerDecks;

import java.util.*;

public class Decks {

	protected ArrayList<Card> cards = new ArrayList<>();;
	
	private int index = 0;
	
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
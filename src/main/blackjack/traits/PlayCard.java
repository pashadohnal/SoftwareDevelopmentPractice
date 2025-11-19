package blackjack.traits;

import pokerDecks.*;

import java.util.ArrayList;

public class PlayCard {
	private Decks decks;
	private ArrayList<Card> hand = new ArrayList<>();
	private int value;
	
	public PlayCard(Decks decks) {
		this.decks = decks;
	}
	public int gethandsize() {
		 return hand.size();
	}
	public void drawCard() {
		Card card = decks.draw();
		hand.add(card);
		value = calValue();
	}
	
	private int calValue() {
		int value =0;
		int noA =0;
		for (Card card: hand) {
			if (card.isAce()) {noA++;}
			value +=card.getValue();
		}

		ArrayList<Integer> values = new ArrayList<>();
		values.add(value);
		while(noA-->0) {values.add(values.get(values.size()-1)+10);}

		int index =0;
		while (index+1<values.size() && values.get(index+1)<=21) index++;
		return values.get(index);		
	}
	
	public void reset() {
		hand.clear();
		value = 0;
	}
	
	public int getValue() {return value;}
	
	public String handToString(boolean hide) {
		if (hide) {
			return hand.get(0).toString() + " Hidden";
		}
		String cards = "Value:" + value + " ";
		for (Card card: hand) {
			cards += " " + card.toString();
		}
		return cards;
	}
}

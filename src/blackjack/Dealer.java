package blackjack;

import java.util.ArrayList;

public class Dealer {
	protected Decks decks;
	protected ArrayList<Card> hand = new ArrayList<>();
	protected int value;
	
	public Dealer(Decks decks) {
		this.decks = decks;
	}
	
	public void draw() {
		Card card = decks.draw();
		hand.add(card);
		value = calValue();
	}
	
	public void reset() {
		hand.clear();
		value = 0;
	}
	
	protected int calValue() {
    	int value =0;
    	int noA =0;
    	for (Card card: hand) {
    		if (card.isAce()) {noA++;}
    		value +=card.getValue();
    	}

    	ArrayList<Integer> values = new ArrayList<>();
    	values.add(value);
    	while(noA-->0) {values.add(values.getLast()+10);}

    	int index =0;
    	while (index+1<values.size() && values.get(index+1)<=21) index++;
    	return values.get(index);		
	}
	
	public int getValue() {return value;}
	
	public boolean autoDraw() {
		if (value<17) {draw();}
		return value<17;
	}
	
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

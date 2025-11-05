package blackjack;

import java.util.*;

public class Player {
	protected ArrayList<Character> hand = new ArrayList<>();
	protected int value =0;
	
	public Player(){}
	
	public void draw() {
		Character card = Decks.draw();
		draw(card);
	}
	
	public void draw(Character card) {
		hand.add(card);
		value =Blackjack.calValue(hand);
	}
	
	public int getValue() {return value;}
	
	// ----- FOR DEBUG AND TESTING ONLY -----
	
	public void reset() {
		getHand().clear();
		value = 0;
	}
	
	public ArrayList<Character> getHand() {
		return hand;
	}
}

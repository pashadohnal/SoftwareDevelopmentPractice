package blackjack;

import java.util.*;

public class Player {
	private ArrayList<Character> hand = new ArrayList<>();
	protected int value =0;
	
	public Player(){}
	
	public void draw() {
		Character card = Deck.draw();
		hand.add(card);
		value+=Blackjack.valueOf(value, card);
	}
}

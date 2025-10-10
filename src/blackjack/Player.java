package blackjack;

import java.util.*;

public class Player {
	private ArrayList<Character> hand = new ArrayList<>();
	private int value =0;
	
	public Player(){}
	
	public void draw(Deck useDeck) {
		Character card = useDeck.draw();
		hand.add(card);
		value+=useDeck.valueOf(value, card);
	}
}

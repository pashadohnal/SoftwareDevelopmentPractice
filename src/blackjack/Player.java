package blackjack;

import java.util.*;

public class Player {
	public ArrayList<Character> hand = new ArrayList<>();
	protected int value =0;
	
	public Player(){}
	
	@Deprecated
	public void draw(int x) {
		value+=x;
		hand.add('X');
	}
	
	public void draw() {
		Character card = Decks.draw();
		hand.add(card);
		value =Blackjack.valueOf(value, card);
		//Blackjack.updateValue(value, noA, card);
	}
	public void reset() {
		hand.clear();
		value = 0;
	}
	public int getValue() {return value;}
}

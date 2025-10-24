package blackjack;

import java.util.*;

public class Player {
	protected ArrayList<Character> hand = new ArrayList<>();
	protected int value =0;
	protected int noA =0;
	
	public Player(){}
	
	public void draw() {
		Character card = Deck.draw();
		hand.add(card);
		value =Blackjack.valueOf(value, card);
		//Blackjack.updateValue(value, noA, card);
	}
	
	public int getValue() {return value;}
}

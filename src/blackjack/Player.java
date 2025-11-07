package blackjack;

import java.util.*;

/**
 * Represent a Player
 */
public class Player {
	/**
	 * Store the cards a player has
	 */
	protected ArrayList<Card> hand;
	
	/**
	 * the value of player's hand
	 */
	protected int value;
	
	/**
	 * the deck the player draw cards from
	 * note that it is shallow copy
	 */
	protected Decks decks;
	
	/** 
	 * calculate the value of the hand of the player
	 * @return value
	 */
	protected int calValue() {
    	int value =0;
    	int noA =0;
    	for (Card card: hand) {
    		if (card.getValue()==Card.A) {value +=1; noA++; continue;}
    		if (card.getValue()==Card.J || card.getValue()==Card.Q || card.getValue()==Card.K) value +=10;
    		else value +=card.getValue();
    		
    	}

    	ArrayList<Integer> values = new ArrayList<>();
    	values.add(value);
    	while(noA-->0) {values.add(values.getLast()+10);}

    	int index =0;
    	while (index+1<values.size() && values.get(index+1)<=21) index++;
    	return values.get(index);		
	}

	/**
	 * constructor of Player
	 * @param decks the deck the player draw card from
	 * @param balance
	 */
	public Player(Decks decks, int balance) {
		this.decks = decks;
		accountBalance = balance;
	}
	
	/**
	 * draw card from deck
	 * update hand
	 * update value
	 */
	public void draw() {
		Card card = decks.draw();
		hand.add(card);
		value = calValue();
	}
	
	/**
	 * the amount of money the player has
	 */
	private int accountBalance;
	
	/**
	 * the amount of bet the player put
	 */
	private int bet;
	
	/**
	 * allow the player to place bet
	 * @param bet
	 * @return false if the player don't have enough money, otherwise true and store bet
	 */
	public boolean betting(int bet) {
		if (bet > accountBalance) {return false;}
		this.bet = bet;
		return true;
	}
	
	/**
	 * the player won and get the bet
	 */
	public void blackjack() {
		accountBalance +=bet;
	}
	
	/**
	 * the player lose and lost the bet
	 */
	public void bust() {
		accountBalance -=bet;
	}
	
	/**
	 * Reset hand and value 
	 */
	public void reset() {
		hand.clear();
		value = 0;
	}
}

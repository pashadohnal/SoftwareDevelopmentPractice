package blackjack;

/**
 * Represents a Poker Card
 */
public class Card {
	/**
	 * Store the face of the card
	 * 2 to 10
	 * A -> 1
	 * J, K, Q -> 11, 12, 13
	 */		
	private int face; 

	/**
	 * Store the suit of the card
	 * ♠, ♥, ♦, ♣
	 */	
	private char suit;
	
	/**
	 * Store the value of the card in blackjack
	 * A : 1 (A can also be 11 but that will be handled by other part of the code)
	 * J, K, Q : 10
	 * others : their face their fate
	 */	
	private int value;

	/**
	 * Constructor of the Card
	 * @param Assume Correct
	 */	
	public Card(char suit, int face) {
		this.face = face;
		this.suit = suit;
		if (face==1) {this.value =1;} 
		if (face>10) {this.value =10;}
		this.value = face;
	}
	
	/**
	 * @return true if the card is an Ace
	 */	
	public boolean isAce() {
		return face==1;
	}
	
	/**
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Convert to card to String
	 */
	public String toString() {
		String value = Integer.toString(face);
		if (face==A) {value = "A";} 
		if (face==J) {value = "J";}
		if (face==Q) {value = "Q";}
		if (face==K) {value = "K";}
		return String.valueOf(suit) + value;
	}
	
	/*
	 * Sudo Enum
	 * @see Player.calValue()
	 */
	public static final int A = 1;
	public static final int J = 11;
	public static final int Q = 12;
	public static final int K = 13;
}

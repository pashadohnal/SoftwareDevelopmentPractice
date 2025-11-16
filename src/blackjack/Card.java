package blackjack;

import java.util.ArrayList;

/**
 * Represents a Poker Card
 */
public class Card {


	/**
	 * Store the suit of the card (enum)
	 */	
	private Suit suit;
	
	/**
	 * Store the face of the card (enum)
	 */	
	private Face face; 
	
	/**
	 * Store the value of the card in blackjack
	 * A : 1 (A can also be 11 but that will be handled by other part of the code)
	 * J, K, Q : 10
	 * others : their face value
	 */	
	private int value;

	/**
	 * Constructor of the Card
	 * @param Assume Correct
	 */	
	public Card(Suit suit, Face face) {
		this.suit = suit;
		this.face = face;
		int faceVal = face.getValue();
		if (faceVal > 10) {
			this.value = 10;
		} else {
			this.value = faceVal;
		}
	}
	
	public static ArrayList<Card> genDeck() {
		ArrayList<Card> cards = new ArrayList<>();
		// iterate over the Suit and Face enums defined in Card
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Face face : Card.Face.values()) {
				cards.add(new Card(suit, face));
			}
		}
		return cards;
	}
	
	/**
	 * @return true if the card is an Ace
	 */	
	public boolean isAce() {
		return face.isAce();
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
		String faceStr = face.display();
		return String.valueOf(suit.getSymbol()) + faceStr;
	}
	
	public enum Suit {
	    SPADE('♠'),
	    HEART('♥'),
	    DIAMOND('♦'),
	    CLUB('♣');

	    private final char symbol;

	    Suit(char symbol) {
	        this.symbol = symbol;
	    }

	    public char getSymbol() {
	        return symbol;
	    }
	}
	
	public enum Face {
		A(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), J(11), Q(12), K(13);
		
		private final int value;

	    Face(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	    
		public boolean isAce() {
			return value==1;
		}

		/**
		 * Display string for the face used in toString()
		 */
		public String display() {
			switch (this) {
				case A: return "A";
				case J: return "J";
				case Q: return "Q";
				case K: return "K";
				case TEN: return "10";
				default: return Integer.toString(value);
			}
		}
	}
}
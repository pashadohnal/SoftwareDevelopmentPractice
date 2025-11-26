package pokerDecks;

import java.util.ArrayList;

import pokerDecks.Card.Face;

// A stub class for Decks to allow injection of a predefined deck for testing
public class DecksStub extends Decks {
	
	public Face valueToFace(Integer face) {
		switch (face.intValue()) {
			case 1: return Card.Face.A;
			case 2: return Card.Face.TWO;
			case 3: return Card.Face.THREE;
			case 4: return Card.Face.FOUR;
			case 5: return Card.Face.FIVE;
			case 6: return Card.Face.SIX;
			case 7: return Card.Face.SEVEN;
			case 8: return Card.Face.EIGHT;
			case 9: return Card.Face.NINE;
			case 10: return Card.Face.TEN;
			case 11: return Card.Face.J;
			case 12: return Card.Face.Q;
			case 13: return Card.Face.K;
			default: throw new IllegalArgumentException("Invalid face value: " + face);
		}
	}

	public DecksStub(ArrayList<Integer> deck) {
		super(0, new ArrayList<Card>());
		for (Integer face : deck) {
			cards.add(new Card(Card.Suit.HEART, valueToFace(face)));
		}
	}

}

package blackjack;

import java.util.ArrayList;
import blackjack.*;

public class Blackjack {
	static public int noOfDeckUsed =1;

    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<=21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10; 
		else return (int)card - '0'; //Card 10 would be 1. There is no conversion from char "1" into 10. Either better to store as a string or convert using a dictionary (Map).
    }
    
    static public int calValue(ArrayList<String> hand) throws InvalidCardException {
        int value = 0;
        int aceCount = 0;

        for (String card : hand) {
            // Extract the rank: everything except the last character if suit is included
            String rank;
            if (card.length() == 3) { // "10♠"
                rank = card.substring(0, 2);
            } else { // "A♠", "J♠", "2♠", etc.
                rank = card.substring(0, 1);
            }

            if (!Decks.RANKS.contains(rank)) {
                throw new InvalidCardException("The hand has unknown card(s): " + card);
            }

            if (rank.equals("A")) aceCount++;
            else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) value += 10;
            else value += Integer.parseInt(rank); // works for "2".."10"
        }

        // Add Aces — count as 11 if it doesn’t bust, else 1
        for (int i = 0; i < aceCount; i++) {
            if (value + 11 <= 21) value += 11;
            else value += 1;
        }

        return value;
    }

class InvalidCardException extends Exception {
	private static final long serialVersionUID = 1L;

    public InvalidCardException(String message) {
        super(message);
    }
}

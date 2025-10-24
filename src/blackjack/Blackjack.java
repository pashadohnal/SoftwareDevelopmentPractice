package blackjack;

public class Blackjack {
	static public int noOfDeckUsed =1;
    // cal values
    // it is better to let the player class to calculate the value
    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<=21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10; 
		else return (int)card - '0'; //Card 10 would be 1. There is no conversion from char "1" into 10. Either better to store as a string or convert using a dictionary.
    }
}

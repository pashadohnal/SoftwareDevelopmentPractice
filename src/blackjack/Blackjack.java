package blackjack;

public class Blackjack {
	static public int noOfDeckUsed =1;
    // cal values
    // it is better to let the player class to calculate the value
    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<=21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10;
		else return (int)card - '0';
    }
}

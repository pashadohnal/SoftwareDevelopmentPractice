package blackjack;

public class Bot extends Player {
	/**
	 * Constructor of the Dealer. 
	 * The Dealer should have infinite balance
	 * @param deck
	 */
	public Bot(Decks deck, int balance) {
		super(deck, balance);
	}

	/**
	 * stupid ai that allow the bot to play the game
	 * @return true is the bot hit, false if stand
	 */
	public boolean play() {
		if (value<17) {super.draw();}
		return value<17;
	}
}



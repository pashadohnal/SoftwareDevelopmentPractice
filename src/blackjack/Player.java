package blackjack;

/**
 * Represent a Player
 */
public class Player extends Dealer {
	/**
	 * constructor of Player
	 * @param decks the deck the player draw card from
	 * @param balance
	 */
	public Player(Decks decks, int balance) {
		super(decks);
		accountBalance = balance;
	}
	
	/**
	 * the amount of money the player has
	 */
	private int accountBalance;
	
	/**
	 * the amount of bet the player put
	 */
	private int bet;
	
	public int getAccountBalance() {
		return accountBalance;
	}
	
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
	 * Convert Hand to String
	 * @param hide
	 * @return
	 */

	
	public void updateBalance(int dealerValue) {
		if(value>21) {bust();return;}
		if(dealerValue>21) {blackjack();return;}
		if(value>dealerValue) {blackjack();return;};
		if(value<dealerValue) {bust();return;};
	}
	
	/**
	 * the player won and get the bet
	 */
	private void blackjack() {
		accountBalance +=bet;
	}
	
	/**
	 * the player lose and lost the bet
	 */
	private void bust() {
		accountBalance -=bet;
	}
	
	/**
	 * Reset hand and value 
	 */

}

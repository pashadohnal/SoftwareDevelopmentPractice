package blackjack.traits;

public class Gambling {
	private int balance;
	private int bet;
	
	public Gambling(int initialBalance) {
		this.balance = initialBalance;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public boolean placeBet(int amount) {
		if (amount > balance) {
			return false;
		}
		this.bet = amount;
		return true;
	}
	
	public void blackjack() {
		balance += bet;
	}
	
	public void bust() {
		balance -= bet;
	}
}

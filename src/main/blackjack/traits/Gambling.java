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
	
	public void updateBalance(int selfValue, int dealerValue) {
		if(selfValue>21) {
			bust();
			return;
		}
		if(dealerValue>21) {
			blackjack();
			return;
		}
		if(selfValue>dealerValue) {
			blackjack();
			return;
		}
		if(selfValue<dealerValue) {
			bust();
			return;
		}
	}
	
	private void blackjack() {
		balance += bet;
	}
	
	private void bust() {
		balance -= bet;
	}
}

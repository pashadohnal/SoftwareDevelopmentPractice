package blackjack.strategies;

public class Bn extends BetStrategy {
	private int n;
	
	public Bn(int n) {
		this.n = n;
	}
	
	public int placeBet(int balance) {
		int bet = (int)(balance * n / 100.0);
		return bet;
	}
}

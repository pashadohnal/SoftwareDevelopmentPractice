package blackjack.strategies;

public class Dn extends DrawStrategy {
	private int n;
	
	public Dn(int n) {
		this.n = n;
	}
	
	public boolean drawCard(int value) {
		if (value<n) {return true;}
		return false;
	}
}

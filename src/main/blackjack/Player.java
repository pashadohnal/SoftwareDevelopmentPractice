package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;
import blackjack.traits.*;

public class Player {
	protected PlayCard playCard;
	protected Gambling gambling;
	protected DrawStrategy drawStrategy;
	protected BetStrategy betStrategy;
	
	public Player(Decks decks, int initialBalance, DrawStrategy drawStrategy, BetStrategy betStrategy) {
		this.playCard = new PlayCard(decks);
		this.gambling = new Gambling(initialBalance);
		this.drawStrategy = drawStrategy;
		this.betStrategy = betStrategy;
	}
	
	public void updateBalance(int dealerValue) {
		gambling.updateBalance(playCard.getValue(), dealerValue);
	}
	
	// public boolean drawCard() {}
	
	// public int placeBet() {}
	
	public void reset() {
		playCard.reset();
	}
	
	public String handToString(boolean hide) {
		return playCard.handToString(hide);
	}
	
	public int getValue() {
		return playCard.getValue();
	}
	
	public int getBalance() {
		return gambling.getBalance();
	}
}

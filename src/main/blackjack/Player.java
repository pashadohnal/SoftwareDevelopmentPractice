package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;
import blackjack.traits.*;

public class Player {
	protected PlayCard playCard;
	protected Gambling gambling;
	protected DrawStrategy drawStrategy;
	protected BetStrategy betStrategy;
	protected String name;
	
	public Player(String name, Decks decks, int initialBalance, DrawStrategy drawStrategy, BetStrategy betStrategy) {
		this.name = name;
		this.playCard = new PlayCard(decks);
		this.gambling = new Gambling(initialBalance);
		this.drawStrategy = drawStrategy;
		this.betStrategy = betStrategy;
	}
	
	public boolean drawCard() {
		return false;
	}
	
	public int placeBet() {
		return 0;
	}
	
	// default functions
	
	public void forceDrawCard() {
		playCard.drawCard();
	}
	
	public String getName() {
		return name;
	}
	
	public void updateBalance(int dealerValue) {
		gambling.updateBalance(playCard.getValue(), dealerValue);
	}
	
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

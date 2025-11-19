package blackjack;

import pokerDecks.*;
import blackjack.traits.PlayCard;
import blackjack.strategies.*;

public class Dealer {
	private PlayCard playCard;
	private Dn strategy = new Dn(17);
	
	public Dealer(Decks decks) {
		this.playCard = new PlayCard(decks);
	}
	
	public boolean drawCard() {
		boolean draw = strategy.drawCard(playCard.getValue());
		if (draw) playCard.drawCard();
		return draw;
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
}

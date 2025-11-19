package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;

public class Dealer extends Player {
	
	public Dealer(Decks decks) {
		super(decks, 0, new Dn(17), null);
	}
	
	public boolean drawCard() {
		boolean draw = drawStrategy.drawCard(playCard.getValue());
		if (draw) playCard.drawCard();
		return draw;
	}
}

package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;
import blackjack.traits.*;

import java.util.Scanner;

public class Player {
	private PlayCard playCard;
	private Gambling gambling;
	private AskPlayerDraw drawStrategy;
	private AskPlayerBet betStrategy;
	
	public Player(Decks decks, int initialBalance, Scanner scanner) {
		this.playCard = new PlayCard(decks);
		this.gambling = new Gambling(initialBalance);
		this.drawStrategy = new AskPlayerDraw(scanner);
		this.betStrategy = new AskPlayerBet(scanner);
	}
	
	public void updateBalance(int dealerValue) {
		int value = playCard.getValue();
		if(value>21) {
			gambling.bust();
			return;
		}
		if(dealerValue>21) {
			gambling.blackjack();
			return;
		}
		if(value>dealerValue) {
			gambling.blackjack();
			return;
		}
		if(value<dealerValue) {
			gambling.bust();
			return;
		}
	}
	
	public boolean drawCard() {
		boolean draw = drawStrategy.drawCard();
		if (draw) playCard.drawCard();
		return draw;
	}
	
	public int placeBet() {
		int bet = betStrategy.placeBet(gambling.getBalance());
		gambling.placeBet(bet);
		return bet;
	}
}

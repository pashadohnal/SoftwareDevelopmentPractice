package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;

import java.util.Scanner;

public class User extends Player {
	
	public User(Decks decks, int initialBalance, Scanner scanner) {
		super(decks, initialBalance, new AskPlayerDraw(scanner), new AskPlayerBet(scanner)	);
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


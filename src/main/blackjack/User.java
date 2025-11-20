package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import pokerDecks.*;
import blackjack.strategies.*;

public class User extends Player {
	
	public User(String name, Decks decks, int initialBalance, Scanner scanner) {
		super(name, decks, initialBalance, new AskPlayerDraw(scanner), new AskPlayerBet(scanner)	);
	}
	
	public boolean drawCard() {
		boolean draw = drawStrategy.drawCard(name);
		if (draw) playCard.drawCard();
		return draw;
	}
	
	public int placeBet() {
		int bet = betStrategy.placeBet(gambling.getBalance());
		gambling.placeBet(bet);
		return bet;
	}
	
	public static ArrayList<User> makeUser(Decks decks, int initialBalance, Scanner scanner, int numberOfUsers) {
		ArrayList<User> users = new ArrayList<>();
		for (int i = 0; i < numberOfUsers; i++) {
			users.add(new User("Player " + (i+1), decks, initialBalance, scanner));
		}
	    return users;
	} 
}


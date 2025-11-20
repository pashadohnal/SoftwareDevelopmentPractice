package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;

import java.util.*;

public class Bot extends Player {	
	public Bot(String name, Decks decks, int initialBalance, DrawStrategy drawStrategy, BetStrategy betStrategy) {
		super(name, decks, initialBalance, drawStrategy, betStrategy);
	}
	
	public boolean drawCard() {
		boolean draw = drawStrategy.drawCard(name, playCard.getValue());
		if (draw) playCard.drawCard();
		return draw;
	}
	
	public int placeBet() {
		int bet = betStrategy.placeBet(gambling.getBalance());
		gambling.placeBet(bet);
		return bet;
	}

	public static ArrayList<Bot> makeBots(Decks decks, int initialBalance, int numberOfBots) {
	    // Create mutable lists for strategies
	    List<DrawStrategy> drawStrategies = new ArrayList<>(List.of(
	        new Dn(18),
	        new Dn(17),
	        new Dn(16)
	    ));

	    List<BetStrategy> betStrategies = new ArrayList<>(List.of(
	        new Bn(10),
	        new Bn(15),
	        new Bn(20)
	    ));

	    // Shuffle strategies
	    Collections.shuffle(drawStrategies);
	    Collections.shuffle(betStrategies);

	    // Create bots using shuffled strategies
	    ArrayList<Bot> bots = new ArrayList<>();
	    for (int i = 0; i < numberOfBots; i++) {
	        DrawStrategy draw = drawStrategies.get(i % drawStrategies.size());
	        BetStrategy bet = betStrategies.get(i % betStrategies.size());
	        bots.add(new Bot("Bot " + (i+1), decks, initialBalance, draw, bet));
	    }

	    return bots;
	}
}

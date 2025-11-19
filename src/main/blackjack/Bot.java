package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;
import blackjack.traits.*;

import java.util.*;

public class Bot {
	private PlayCard playCard;
	private Gambling gambling;
	private DrawStrategy drawStrategy;
	private BetStrategy betStrategy;
	
	public Bot(Decks decks, int initialBalance, DrawStrategy drawStrategy, BetStrategy betStrategy) {
		this.playCard = new PlayCard(decks);
		this.gambling = new Gambling(initialBalance);
		this.drawStrategy = drawStrategy;
		this.betStrategy = betStrategy;
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
		boolean draw = drawStrategy.drawCard(playCard.getValue());
		if (draw) playCard.drawCard();
		return draw;
	}
	
	public int placeBet() {
		int bet = betStrategy.placeBet(gambling.getBalance());
		gambling.placeBet(bet);
		return bet;
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

	public static ArrayList<Bot> makeBots(Decks decks, int initialBalance, int numberOfBots) {
	    // Create mutable lists for strategies
	    List<DrawStrategy> drawStrategies = new ArrayList<>(List.of(
	        new blackjack.strategies.Dn(18),
	        new blackjack.strategies.Dn(18),
	        new blackjack.strategies.Dn(17),
	        new blackjack.strategies.Dn(17),
	        new blackjack.strategies.Dn(16),
	        new blackjack.strategies.Dn(16)
	    ));

	    List<BetStrategy> betStrategies = new ArrayList<>(List.of(
	        new Bn(10),
	        new Bn(10),
	        new blackjack.strategies.Bn(15),
	        new blackjack.strategies.Bn(15),
	        new blackjack.strategies.Bn(20),
	        new blackjack.strategies.Bn(20)
	    ));

	    // Shuffle strategies
	    Collections.shuffle(drawStrategies);
	    Collections.shuffle(betStrategies);

	    // Create bots using shuffled strategies
	    ArrayList<Bot> bots = new ArrayList<>();
	    for (int i = 0; i < numberOfBots; i++) {
	        DrawStrategy draw = drawStrategies.get(i % drawStrategies.size());
	        BetStrategy bet = betStrategies.get(i % betStrategies.size());
	        bots.add(new Bot(decks, initialBalance, draw, bet));
	    }

	    return bots;
	}
}

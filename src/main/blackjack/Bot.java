package blackjack;

import pokerDecks.*;
import blackjack.strategies.*;
import blackjack.traits.*;

import java.util.List;
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
	
	public static List<Bot> makeBots(Decks decks, int initialBalance, int numberOfBots) {
		List<DrawStrategy> drawStrategies = List.of(
			new blackjack.strategies.Dn(18),
			new blackjack.strategies.Dn(18),
			new blackjack.strategies.Dn(17),
			new blackjack.strategies.Dn(17),
			new blackjack.strategies.Dn(16),
			new blackjack.strategies.Dn(16)
		); 
		
		Collections.shuffle(drawStrategies);
		
		List<BetStrategy> betStrategies = List.of(
			new blackjack.strategies.Bn(10),
			new blackjack.strategies.Bn(10),
			new blackjack.strategies.Bn(15),
			new blackjack.strategies.Bn(15),
			new blackjack.strategies.Bn(20),
			new blackjack.strategies.Bn(20)
		);
		
		Collections.shuffle(betStrategies);
		
		List<Bot> bots = new ArrayList<>();
		
		for (int i=0; i<numberOfBots; i++) {
			Bot bot = new Bot(decks, initialBalance, drawStrategies.get(i%drawStrategies.size()), betStrategies.get(i%betStrategies.size()));
			bots.add(bot);
		}
		
		return bots;
	}
}

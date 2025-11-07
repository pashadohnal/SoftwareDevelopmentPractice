package blackjack;

import java.util.*;

/**
 * Implementation of the Single Player Mode
 * You can also play against up to 6 bots
 */
public class SinglePlayer {
	/**
	 * Entry point for Single Player Mode
	 * @param args
	 */
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	Decks decks = new Decks(4);
    	
    	Bot dealer = new Bot(decks, 0);
    	ArrayList<Player> bots = setup(scanner, decks);
    	Player human = bots.removeLast();
    	do {
    		reset();
    		initBet();
    		initPlayers(dealer, human, bots);
    		showGame();
    		sideBet();
    		while(round()) {};
    		updateBalance();
    		showBalance();
    	} while(nextGame());
    	
    }
    
    /**
     * Generate an arraylist of bots and player. player is the last element
     * @param scanner
     * @param decks
     * @return
     */
    private static ArrayList<Player> setup(Scanner scanner, Decks decks) {
    	System.out.println("This is the single player mode for the game blackjack. It also allows you to play against bots");
    	
    	System.out.print("What should be the initial balance. Enter an integer: ");
    	int initBalance = 0;
    	while (true) {
    		String input = scanner.nextLine().trim();
    		try {
    			initBalance = Integer.parseInt(input);
    			break;
    		} catch (NumberFormatException e) {
                System.out.print("That was not an Integer. Enter an integer: ");
                continue;
            }
    	}
    	
       	System.out.println("Ha Ha No Friends. How many bots you want to play with ? (at most 6 bots)");
       	int nPlayers;
    	while (true) {
    		String input = scanner.nextLine().trim();
    		try {
    			nPlayers = Integer.parseInt(input);
    		} catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number (0-6): ");
                continue;
            }
    		
            if (nPlayers >= 0 && nPlayers <= 6) {
                break;
            } else {
                System.out.print("Please enter a number between 0 and 6: ");
            }
    	}
    	
    	ArrayList<Player> result = new ArrayList<>();
    	for (int i=0; i<nPlayers; i++) {result.add(new Bot(decks, initBalance));}
    	result.add(new Player(decks, initBalance));
    	return result;
    }
    
    /**
     * let human player and bots to put their initial bet
     */
    private static void initBet() {
    	
    }
    
    /**
     * Draw 2 cards for dealer, human player and bots
     * @param human
     * @param bots
     */
    private static void initPlayers(Bot dealer, Player human, ArrayList<Player> bots) {
    	for (int i=0; i<2; i++) {
    		dealer.draw();
    		human.draw();
    		for (Player bot: bots) {bot.draw();}
    	}
    }
    
    /**
     * show the state of the game. i.e. the hand of the dealer and player
     */
    private static void showGame() {
    	
    }
    
    /**
     * let players to put side bet
     * Optional Bets (After Cards Are Dealt):

		Insurance: If the dealer shows an Ace, players may bet up to half their original wager that the dealer has blackjack.
		
		Double Down: After receiving the first two cards, the player can double their bet and receive exactly one more card.
		
		Split: If the first two cards are a pair, the player can split them into two hands, placing an additional bet equal to the original.
		
		Surrender (if allowed): Some casinos let players forfeit half their bet and end the hand immediately after the initial deal.
     */
    private static void sideBet() {
    	
    }
    
    /**
     * play 1 iteration of the game
     * @return true if the game continue
     */
    private static boolean round() {
    	
    }
    
    /**
     * ask if the player want to play next round
     * @return true if player wants to continue, false otherwise
     */
    private static boolean nextGame() {
    	
    }
    
    /**
     * reset the hands of dealer, player and bots, but not their balance
     */
    private static void reset() {
    	
    }
    
    private static void updateBalance() {
    	
    }
    
    private static void showBalance() {}
}

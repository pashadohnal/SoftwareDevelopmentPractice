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
    	
    	Player dealer = new Player(decks, 0);
    	ArrayList<Player> bots = setup(scanner, decks);
    	Player human = bots.removeLast();
    	do {
    		reset(decks, dealer, human, bots);
    		initBet(scanner, human);
    		initPlayers(dealer, human, bots);
    		sideBet(scanner, dealer);
    		while(round(scanner, dealer, human, bots)) {};
    		updateBalance(dealer, human, bots);
    		showBalance(human, bots);
    	} while(nextRound(scanner));
    	
    }
    
    /**
     * Generate an arraylist of bots and player. player is the last element
     * @param scanner
     * @param decks
     * @return
     */
    private static ArrayList<Player> setup(Scanner scanner, Decks decks) {
    	System.out.println("This is the single player mode for the game blackjack. It also allows you to play against bots");
    	
    	System.out.print("What should be the initial balance? Enter an integer: ");
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
    	
       	System.out.print("Ha Ha No Friends. How many bots you want to play with (at most 6 bots)? ");
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
    	for (int i=0; i<nPlayers; i++) {result.add(new Player(decks, initBalance));}
    	result.add(new Player(decks, initBalance));
    	return result;
    }
    
    /**
     * let human player and bots to put their initial bet
     */
    private static void initBet(Scanner scanner, Player human) {
    	int bet =0;
    	System.out.print("Your account has $" + human.getAccountBalance() + ") ");
    	while (true) {
    		System.out.print("Please place your bet : ");
    		
    		try {
    			String input = scanner.nextLine().trim();
    			bet = Integer.parseInt(input);
    			human.betting(bet);
    		} catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
    		}
    		
    		if (human.betting(bet)) {break;}
    		System.out.println("Not enough money");
    	}
    }
    
    /**
     * Draw 2 cards for dealer, human player and bots
     * @param human
     * @param bots
     */
    private static void initPlayers(Player dealer, Player human, ArrayList<Player> bots) {
    	for (int i=0; i<2; i++) {
    		dealer.draw();
    		human.draw();
    		for (Player bot: bots) {bot.draw();}
    	}
    }
    
    /**
     * let players to put side bet
     * Optional Bets (After Cards Are Dealt):

		Insurance: If the dealer shows an Ace, players may bet up to half their original wager that the dealer has blackjack.
		
		Double Down: After receiving the first two cards, the player can double their bet and receive exactly one more card.
		
		Split: If the first two cards are a pair, the player can split them into two hands, placing an additional bet equal to the original.
		
		Surrender (if allowed): Some casinos let players forfeit half their bet and end the hand immediately after the initial deal.
     */
    private static void sideBet(Scanner scanner, Player dealer) {
    	System.out.println(dealer.handToString(true));
    }
    
    /**
     * play 1 iteration of the game
     * @return true if the game continue
     */
    private static boolean round(Scanner scanner, Player dealer, Player human, ArrayList<Player> bots) {
    	boolean next = dealer.play();
    	System.out.println("Dealer " + dealer.handToString(false));
    	System.out.println("Player " + human.handToString(false));
    	System.out.print("(H)it or (S)tand : ");
    	while (true) {
    		char choice = scanner.nextLine().charAt(0);
    		if (choice=='H') {
    			human.draw();
    			System.out.println(human.handToString(false));
    			next = true;
    			break;
    		}
    		if (choice=='S') {
    			break;
    		}
    		System.out.print("Invalid Input");
    	}
    	for (Player bot: bots) {
    		next = next | bot.play();
    	}
    	
    	return next;
    }
    
    /**
     * reset the hands of dealer, player and bots, but not their balance
     */
    private static void reset(Decks decks, Player dealer, Player human, ArrayList<Player> bots) {
    	decks.reset();
    	dealer.reset();
    	for (Player bot: bots) {bot.reset();}
    }
    
    private static void updateBalance(Player dealer, Player human, ArrayList<Player> bots) {
    	int value = dealer.getValue();
    	human.updateBalance(value);
    	for (Player bot: bots) {bot.updateBalance(value);}
    }
    
    private static void showBalance(Player human, ArrayList<Player> bots) {
    	System.out.println("Player balance : " + human.getAccountBalance());
    	for (int i=0; i<bots.size(); i++) {
    		System.out.println("Bot " + i+1 +" balance : " + bots.get(i).getAccountBalance());
    	}
    }
    
    /**
     * ask if the player want to play next round
     * @return true if player wants to continue, false otherwise
     */
    private static boolean nextRound(Scanner scanner) {
    	System.out.print("Continue (Y/N)? ");
    	while (true) {
    		char input = scanner.nextLine().charAt(0);
    		if (input=='T') {return true;}
    		if (input=='F') {return false;}
    		System.out.print("Invalid Input.");
    	}
    }
}

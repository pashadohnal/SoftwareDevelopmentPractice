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
    		sidBetfinish(scanner, dealer);
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
    	for (int i=0; i<nPlayers; i++) {Player bot = new Player(decks, 100000);bot.betting(1000); result.add(bot);}
    	result.add(new Player(decks, initBalance));
    	return result;
    }
    
    /**
     * let human player and bots to put their initial bet
     */
    private static void initBet(Scanner scanner, Player human) { // can still play even <=0, having approach to solve but waiting for time implementing
        if (human.getAccountBalance() <= 0) {
            System.out.println("Lost all. Cannot place bet.");
            return;
        }

        int bet = 0;
        System.out.print("Your account has $" + human.getAccountBalance() + ". ");

        while (true) {
            System.out.print("Please place your bet : ");
            String input = scanner.nextLine().trim();

            try {
                bet = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            if (human.betting(bet)) {
                break;
            } else {
                System.out.println("Not enough money. Try again.");
            }
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

	private static void sidBetfinish(Scanner scanner, Player dealer) {
		System.out.println("Dealer's " + dealer.handToString(false));
	}
    /**
     * play 1 iteration of the game
     * @return true if the game continue
     */
	private static boolean round(Scanner scanner, Player dealer, Player human, ArrayList<Player> bots) {
	    System.out.println("Player " + human.handToString(false));
	    while (true) {
	        System.out.print("(H)it or (S)tand : ");
	        String input = scanner.nextLine().trim().toUpperCase();
	        if (input.isEmpty()) {
	            System.out.println("Invalid input. Please type H or S.");
	            continue;
	        }
	        char choice = input.charAt(0);
	        if (choice == 'H') {
	            human.draw();
	            System.out.println("Player " + human.handToString(false));
	            if (human.getValue() > 21) {
	                System.out.println("Player busts!");
	                break;
	            }
	        } else if (choice == 'S') {
	            break;
	        } else {
	            System.out.println("Invalid input. Please type H or S.");
	        }
	    }
	    for (int i = 0; i < bots.size(); i++) {
	        Player bot = bots.get(i);
	        System.out.println("Bot " + (i + 1) + " " + bot.handToString(false));

	        while (true) {
	            System.out.print("Bot " + (i + 1) + " (H)it or (S)tand? ");
	            boolean hit = bot.getValue() < 17;

	            if (hit) {
	                System.out.println("H");
	                bot.draw();
	                System.out.println("Bot " + (i + 1) + " hits: " + bot.handToString(false));
	                if (bot.getValue() > 21) {
	                    System.out.println("Bot " + (i + 1) + " busts!");
	                    break;
	                }
	            } else {
	                System.out.println("S");
	                System.out.println("Bot " + (i + 1) + " stands.");
	                break;
	            }
	            try { Thread.sleep(600); } catch (Exception e) {}  // readable pace
	        }
	    }
	    System.out.println("Dealer's " + dealer.handToString(false));  // Reveal both cards
	    while (dealer.getValue() < 17) {
	        dealer.draw();
	        System.out.println("Dealer hits: " + dealer.handToString(false));
	        if (dealer.getValue() > 21) {
	            System.out.println("Dealer busts!");
	            break;
	        }
	    }
	    if (dealer.getValue() >= 17 && dealer.getValue() <=21) {
	        System.out.println("Dealer stands.");
	    }

	    return false;
	}    
    /**
     * reset the hands of dealer, player and bots, but not their balance
     */
    private static void reset(Decks decks, Player dealer, Player human, ArrayList<Player> bots) {
    	decks.reset();
    	dealer.reset();
        human.reset();
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
    		if (input=='Y') {return true;}
    		if (input=='N') {return false;}
    		System.out.print("Invalid Input.");
    	}
    }
}

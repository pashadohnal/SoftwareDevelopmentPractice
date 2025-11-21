package gameModes;

import java.util.*;

import blackjack.Bot;
import blackjack.Dealer;
import blackjack.User;
import pokerDecks.Card;
import pokerDecks.Decks;


/**
 * SinglePlayer - Console-based single-player mode for a simplified Blackjack game.
 *
 * <p>This class contains a small, self-contained interactive driver for playing
 * blackjack against a dealer and a configurable number of simple bots. It is
 * intended as a lightweight UI for manual play and teaching/practice. The
 * implementation is synchronous and console-driven (Scanner + System.out).
 *
 * <p>Notes and caveats:
 * <ul>
 *   <li>Many parts are simplified compared to a casino-grade blackjack engine
 *       (simple bot strategy, no split/double/surrender handling, minimal
 *       side-bet logic).</li>
 *   <li>Some method and variable names may be legacy from earlier iterations;
 *       the JavaDoc below attempts to document current behaviour rather than
 *       older intent.</li>
 *   <li>This class uses Thread.sleep for pacing the bot actions (catching and
 *       ignoring InterruptedException). That behaviour is purely cosmetic.</li>
 * </ul>
 *
 * <p>Public API: this class is a standalone driver (all methods are static).
 */
public class SinglePlayer {
    /**
     * Program entry point. Starts an interactive single-player blackjack session.
     *
     * <p>Behaviour: prompts for an initial balance, creates the dealer, human
     * player and requested number of bots, then enters a loop of rounds until
     * the user chooses to quit.
     *
     * @param args unused
     */
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("This is the single player mode for the game blackjack. It also allows you to play against bots");
	    while (true) {
	        int initBalance = askInitBalance(scanner);
	        Decks decks = new Decks(4, Card.genDeck());
	        Dealer dealer = new Dealer(decks);
	        User human = new User("Player", decks, initBalance,scanner);
	        ArrayList<Bot> bots = makeBots(scanner, initBalance, decks);
	        boolean playMoreRounds = true;
	        while (playMoreRounds) {
	            reset(decks, dealer, human, bots);
	            if (human.getBalance() <= 0) {
	                System.out.println("You have no money left for another round.");
	                break;
	            }
	            initBet(scanner, human, bots);
	            initPlayers(dealer, human, bots);
	            sideBet(scanner, dealer);
	            playerTurn(scanner, human, "Player");
	    		playAllBots(bots);
	    		dealerTurn(dealer);	
	            sidBetfinish(scanner, dealer);
	            updateBalance(dealer, human, bots);
	            showBalance(human, bots);
	            playMoreRounds = nextRound(scanner); 
	        }
	        System.out.println();
	        if (!wantsGame(scanner)) {
	            scanner.close();
	            return;                              
	        }
	    }
	}
	private static boolean wantsGame(Scanner scanner) {
	    while (true) {
	        System.out.print("Start a completely new game? (Y/N): ");
	        String input = scanner.nextLine().trim().toUpperCase();

	        if (input.equals("Y")) {
	            System.out.println("Starting a completely new game with fresh balance...");
	            return true;
	        }
	        if (input.equals("N")) {
	            System.out.println("Thank you for playing! Goodbye.");
	            return false;
	        }
	        System.out.println("Invalid input. Please type Y or N.");
	    }
	}
    
	/**
     * Prompt the user for the initial balance.
     *
     * This method repeatedly asks the user to input an integer until a valid
     * integer is provided. It performs basic input validation and returns the
     * chosen starting balance for the human player.
     *
     * @param scanner Scanner used to read user input (must not be null)
     * @return the chosen initial balance as an int (guaranteed to be an integer)
     */
    public static int askInitBalance(Scanner scanner) {
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
    	return initBalance;
    }
    
    /**
     * Create the requested number of bot players.
     *
     * <p>The user is prompted for the number of bots (0..6). Each bot is
     * constructed with the provided deck and initial balance. The method
     * returns a list of newly created bot players. The human player is not
     * included here; the caller constructs it separately.
     *
     * @param scanner Scanner for reading user input
     * @param initBalance starting balance to assign to each bot
     * @param decks shared deck manager
     * @return an ArrayList of bot Player instances (size between 0 and 6)
     */
    public static ArrayList<Bot> makeBots(Scanner scanner, int initBalance , Decks decks) { 	
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
    	
    	return (ArrayList<Bot>) Bot.makeBots(decks, initBalance, nPlayers);
    }
    
    /**
     * Request initial bet from the human player.
     *
     * <p>If the player's account balance is zero or negative the method prints a
     * message and returns. Otherwise it repeatedly prompts the user to enter a
     * numeric bet until the Player.betting(bet) call succeeds.
     *
     * @param scanner Scanner for reading the user's bet
     * @param human the human Player who will place the bet
     */
    public static void initBet(Scanner scanner, User human, ArrayList<Bot> bots) {
    	/*
        if (human.getBalance() <= 0) {
            System.out.println("Lost all. Cannot place bet.");
            return;
        }
        int balance = human.getBalance();
        System.out.println("Your current balance: $" + balance);
        while (true) {
            System.out.print("How much do you want to bet? (1–" + balance + "): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Please enter a bet amount.");
                continue;
            }
            int bet;
            try {
                bet = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            if (bet <= 0) {
                System.out.println("Bet must be at least $1.");
                continue;
            }
            if (bet > balance) {
                System.out.println("You don't have enough money. Maximum bet: $" + balance);
                continue;
            }
            if (human.placeBet()) {
                System.out.println("Bet accepted: $" + bet);
                return;
            } else {
                System.out.println("Bet failed. Please try again.");
            }
        }
        */

    	human.placeBet();
    	for (Bot bot: bots) {bot.placeBet();}
    }
    /**
     * Deal two initial cards to dealer, human and bots.
     *
     * This performs the standard "deal two cards each" action. The Decks and
     * Player.draw() implementations determine the exact card objects and side
     * effects.
     *
     * @param dealer the dealer Player (will receive two cards)
     * @param human the human Player (will receive two cards)
     * @param bots list of bot players to deal two cards each
     */
    public static void initPlayers(Dealer dealer, User human, ArrayList<Bot> bots) {
    	for (int i=0; i<2; i++) {
			dealer.drawCard();
			human.forceDrawCard();
			for (Bot bot: bots) {bot.drawCard();}
		}
    }
    
    /**
     * Show side-bet options and reveal the dealer's visible card.
     *
     * Current implementation only prints the dealer's visible card. The
     * JavaDoc lists common optional bets for reference; full support is not
     * implemented here.
     *
     * @param scanner scanner for reading any additional input (unused)
     * @param dealer the dealer player whose visible card is printed
     */
    public static void sideBet(Scanner scanner, Dealer dealer) {
    	System.out.println("Dealer's " + dealer.handToString(true));
    }

    /**
     * Reveal the dealer's full hand after side-bet stage finishes.
     *
     * @param scanner scanner for any input (unused)
     * @param dealer the dealer player to reveal
     */
	public static void sidBetfinish(Scanner scanner, Dealer dealer) {
		System.out.println("Dealer's " + dealer.handToString(false));
	}
    /**
     * Play one round: player decisions, bots decisions, then dealer actions.
     *
     * <p>Return value is currently unused by the caller; the method always
     * returns false at the end of the round (this mirrors the original code
     * and allows the call-site to loop if the return value semantics are
     * changed later).
     *
     * @param scanner scanner for reading human player's choices
     * @param dealer dealer player
     * @param human human player
     * @param bots list of bot players
     * @return false (method currently always returns false)
     */


	private static void playerTurn(Scanner scanner, User player, String name) {
	    System.out.println(name + " " + player.handToString(false));

	    while (true) {
	        System.out.print("(H)it or (S)tand : ");
	        String line = scanner.nextLine().trim().toUpperCase();
	        if (line.isEmpty()) {
	            System.out.println("Invalid input. Please type H or S.");
	            continue;
	        }
	        char choice = line.charAt(0);

	        if (choice == 'H') {
	            player.drawCard();
	            System.out.println(name + " " + player.handToString(false));
	            if (player.getValue() > 21) {
	                System.out.println(name + " busts!");
	                break;
	            }
	        } else if (choice == 'S') {
	            break;
	        } else {
	            System.out.println("Invalid input. Please type H or S.");
	        }
	    }
	}
	private static void botTurn(Bot bot, int botIndex) {
	    System.out.println("Bot " + (botIndex + 1) + " " + bot.handToString(false));

	    while (true) {
	        boolean shouldHit = bot.drawCard(); 
	        System.out.print("Bot " + (botIndex + 1) + " (H)it or (S)tand? ");
	        if (shouldHit) {
	            System.out.println("H");
	            System.out.println("Bot " + (botIndex + 1) + " hits: " + bot.handToString(false));
	            if (bot.getValue() > 21) {
	                System.out.println("Bot " + (botIndex + 1) + " busts!");
	                return;
	            }
	        } else {
	            System.out.println("S");
	            System.out.println("Bot " + (botIndex + 1) + " stands.");
	            return;
	        }
	        try { Thread.sleep(600); } catch (Exception ignored) {}
	    }
	}
	private static void playAllBots(ArrayList<Bot> bots) {
	    for (int i = 0; i < bots.size(); i++) {
	        botTurn(bots.get(i), i);
	    }
	}
	private static void dealerTurn(Dealer dealer) {
	    System.out.println("Dealer's " + dealer.handToString(false));
	    while (dealer.getValue() < 17) {
	        dealer.drawCard();
	        System.out.println("Dealer hits: " + dealer.handToString(false));
	        if (dealer.getValue() > 21) {
	            System.out.println("Dealer busts!");
	            return;                 // early exit – no need to stand
	        }
	    }
	    if (dealer.getValue() >= 17 && dealer.getValue() <= 21) {
	        System.out.println("Dealer stands.");
	    }
	}
    /**
     * Reset hands for the next round while preserving balances.
     *
     * <p>This calls Decks.reset(), Player.reset() for dealer, human and each bot.
     * The decks manager will determine whether cards are reshuffled.
     *
     * @param decks shared deck manager to be reset
     * @param dealer dealer player whose hand is cleared
     * @param human human player whose hand is cleared
     * @param bots list of bot players whose hands are cleared
     */
    public static void reset(Decks decks, Dealer dealer, User human, ArrayList<Bot> bots) {
    	decks.reset();
    	dealer.reset();
        human.reset();
    	for (Bot bot: bots) {bot.reset();}
    }
    
    /**
     * Update players' balances based on the dealer's final value.
     *
     * <p>This delegates to each Player.updateBalance(int dealerValue).
     * The precise settlement rules are implemented in the Player class and may
     * include win/lose/push logic, blackjack payouts, and side-bet handling if
     * present.
     *
     * @param dealer the dealer player whose final value is used for settlement
     * @param human the human player to update
     * @param bots list of bot players to update
     */
    public static void updateBalance(Dealer dealer, User human, ArrayList<Bot> bots) {
    	int value = dealer.getValue();
    	human.updateBalance(value);
    	for (Bot bot: bots) {bot.updateBalance(value);}    
    }
    
    /**
     * Print account balances for human and bots to the console.
     *
     * @param human human player whose balance is printed
     * @param bots list of bots whose balances are printed
     */
    public static void showBalance(User human, ArrayList<Bot> bots) {
    	System.out.println("Player balance : " + human.getBalance());
    	for (int i=0; i<bots.size(); i++) {
			System.out.println("Bot " + (i+1) +" balance : " + bots.get(i).getBalance());
		}
    }
    
    /**
     * Ask the user whether to continue playing another round.
     *
     * This method expects the user to type a single character: 'Y' to continue
     * or 'N' to quit. The prompt loops until a valid choice is entered.
     *
     * @param scanner scanner used to read the user's reply
     * @return true if the user entered 'Y', false if the user entered 'N'
     */
    private static boolean nextRound(Scanner scanner) {
        while (true) {
            System.out.print("Play another round? (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Y")) {
                return true;
            }
            if (input.equals("N")) {
                return false;              
            }
            System.out.println("Invalid input. Please type Y or N.");
        }
    }
}

package gameModes;

import java.util.Scanner;
import java.util.ArrayList;

import pokerDecks.*;
import blackjack.*;

public class MultiPlayer {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("This is the multiplayer mode for blackjack. It also maximum of 6 players and fill the remaining seats with computer players.");
        int initBalance = askInitBalance(scanner);
        Decks decks = new Decks(4, Card.genDeck());
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Dealer(decks));
		players.addAll(askNewUsers(scanner, decks, initBalance));
		players.addAll(Bot.makeBots(decks, initBalance, 6 - (players.size() - 1)));
		do {
			reset(decks,players);
			placeBets(players);
			draw2Cards(players);
			dealerShow1(players);
			playRound(players);
			dealerReveal(players.get(0));
			updateBalances(players);
		}while (nextRound(scanner));
		scanner.close();
	}

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
	
	public static void reset(Decks decks, ArrayList<Player> players) {
		decks.reset();
		for (Player player : players) {
			player.reset();
		}
	}
    
    public static ArrayList<User> askNewUsers(Scanner scanner, Decks decks, int initBalance) {
		int nPlayers;
		do {
			System.out.print("Enter the number of human players (0-6): ");
		    try {
		        nPlayers = Integer.parseInt(scanner.nextLine().trim());
		    } catch (NumberFormatException e) {
		        nPlayers = -1; // force retry
		        System.out.println("Invalid input. Please enter digits only.");
		    }
		} while (nPlayers < 0 || nPlayers > 6);
		return User.makeUser(decks, initBalance, scanner, nPlayers);
    }
    
    public static void placeBets(ArrayList<Player> players) {
    	// Skip dealer at index 0
		for (Player player : players.subList(1, players.size())) {
			int bet = player.placeBet();
			System.out.println(player.getName() + " placed a bet of " + bet);
		}
	}
    
    public static void draw2Cards(ArrayList<Player> players) {
    	for (Player player : players) {
			player.forceDrawCard();
			player.forceDrawCard();
    	}
    }
    
    public static void dealerShow1(ArrayList<Player> players) {
		Player dealer = players.get(0);
		System.out.println("Dealer's hand: " + dealer.handToString(true));
    }
    
    public static void playRound(ArrayList<Player> players) {
    	for (Player player : players.subList(1, players.size())) {
    		for (Player p : players.subList(1, players.size())) {
                if (p != player) {
                    System.out.println(p.getName() + "'s hand: " + p.handToString(false) +  " (Value: " + p.getValue() + ")");}
                }
            System.out.println();
            System.out.println(player.getName() + "'s hand: " + player.handToString(false) + " (Value: " + player.getValue() + ")");
            
    		boolean drew;
			do {
				drew = player.drawCard();
				System.out.println(player.getName() + "'s hand: " + player.handToString(false) + " (Value: " + player.getValue() + ")");
				if (drew) {
					System.out.println(player.getName() + " drew a card. New hand: " + player.handToString(false) + " (Value: " + player.getValue() + ")");
				} else {
					System.out.println(player.getName() + " chose to stand.");
				}
			} while (player.getValue() < 21 && drew);
		}
	}
    
	public static void dealerReveal(Player dealer) {
		boolean drew;
		do {
			System.out.println(dealer.getName() + "'s hand: " + dealer.handToString(false) + " (Value: " + dealer.getValue() + ")");
			drew = dealer.drawCard();
			if (drew) {
				System.out.println(dealer.getName() + " drew a card. New hand: " + dealer.handToString(false) + " (Value: " + dealer.getValue() + ")");
			} else {
				System.out.println(dealer.getName() + " chose to stand.");
			}
		} while (dealer.getValue() < 21 && drew);	
		System.out.println(dealer.getName() + "'s hand: " + dealer.handToString(false) + " (Value: " + dealer.getValue() + ")");
	}
    
    public static void updateBalances(ArrayList<Player> players) {
    	Dealer dealer = (Dealer) players.get(0);
    	int dealerValue = dealer.getValue();
    	for (Player player : players.subList(1, players.size())) {
			player.updateBalance(dealerValue);
			dealer.updateBalance(player.getValue());
    	}
    	
    	for (Player player : players.subList(1, players.size())) {
			System.out.println(player.getName() + "'s balance: " + player.getBalance());
    	}
    }
    
    public static boolean nextRound(Scanner scanner) {
    	while (true) {
    	    System.out.print("Play another round? (Y/N): ");
    	    String input = scanner.nextLine().trim().toUpperCase();
    	    if ("Y".equals(input)) return true;
    	    if ("N".equals(input)) return false;
    	    System.out.println("Invalid input. Please type Y or N.");
    	}
    }
    
}

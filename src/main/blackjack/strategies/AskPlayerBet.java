package blackjack.strategies;

import java.util.Scanner;

public class AskPlayerBet extends BetStrategy {
	private Scanner scanner;
	
	public AskPlayerBet(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public int placeBet(int balance) {
		while (true) {
	        System.out.print("Place your bet (Balance: " + balance + "): ");
	        String line = scanner.nextLine().trim();
	        try {
	            int bet = Integer.parseInt(line);
	            if (bet > 0 && bet <= balance) {
	                return bet;
	            } else {
	                System.out.println("Invalid bet amount. Please enter a positive number up to your balance.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }
	}
}

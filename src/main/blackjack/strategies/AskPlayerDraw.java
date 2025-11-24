package blackjack.strategies;

import java.util.Scanner;

public class AskPlayerDraw extends DrawStrategy {
	private Scanner scanner;
	
	public AskPlayerDraw(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public boolean drawCard(String name) {
	    while (true) {
	        System.out.print(name + " (H)it or (S)tand : ");
	        String line = scanner.nextLine().trim().toUpperCase();
	        if (line.isEmpty()) {
	            System.out.println("Invalid input. Please type H or S.");
	            continue;
	        }
	        char choice = line.charAt(0);

	        if (choice == 'H') {
	            return true;
	        } else if (choice == 'S') {
	            return false;
	        } else {
	            System.out.println("Invalid input. Please type H or S.");
	        }
	    }
	}
}

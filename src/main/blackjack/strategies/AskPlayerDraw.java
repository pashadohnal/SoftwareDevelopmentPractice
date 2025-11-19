package blackjack.strategies;

import java.util.Scanner;

public class AskPlayerDraw extends DrawStrategy {
	private Scanner scanner;
	
	public AskPlayerDraw(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public boolean drawCard() {
	    return true;
	}
}

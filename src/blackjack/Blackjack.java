package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import blackjack.*;

public class Blackjack {
	static public int noOfDeckUsed =1;

	@Deprecated
	// use calValue instead
    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10; 
		else return (int)card - '0'; //Card 10 would be 1. There is no conversion from char "1" into 10. Either better to store as a string or convert using a dictionary (Map).
    }
<<<<<<< HEAD
	
    static public int calValue(ArrayList<Character> hand) {
    	int value =0;
    	int noA =0;
    	for (Character card: hand) {
    		if (!Decks.RANKS.contains(card)) continue;
    		
    		if (card=='A') {value +=1; noA++; continue;}
    		if (card=='J' || card=='Q' || card=='K') value +=10;
    		else value +=(int)card - '0';
    		
    	}
=======
    
    static public int calValue(ArrayList<String> hand) throws InvalidCardException {
        int value = 0;
        int aceCount = 0;
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git

<<<<<<< HEAD
    	ArrayList<Integer> values = new ArrayList<>();
    	values.add(value);
    	while(noA-->0) {values.add(values.getLast()+10);}
=======
        for (String card : hand) {
            // Extract the rank: everything except the last character if suit is included
            String rank;
            if (card.length() == 3) { // "10♠"
                rank = card.substring(0, 2);
            } else { // "A♠", "J♠", "2♠", etc.
                rank = card.substring(0, 1);
            }
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git

<<<<<<< HEAD
    	int index =0;
    	while (index+1<values.size() && values.get(index+1)<=21) index++;
    	return values.get(index);
=======
            if (!Decks.RANKS.contains(rank)) {
                throw new InvalidCardException("The hand has unknown card(s): " + card);
            }

            if (rank.equals("A")) aceCount++;
            else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) value += 10;
            else value += Integer.parseInt(rank); // works for "2".."10"
        }

        // Add Aces — count as 11 if it doesn’t bust, else 1
        for (int i = 0; i < aceCount; i++) {
            if (value + 11 <= 21) value += 11;
            else value += 1;
        }

        return value;
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git
    }
<<<<<<< HEAD
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Blackjack game");
        int nPlayers = 0;
        boolean valid = false;
        System.out.print("How many players? (1-6): ");
        while (!valid) {
            String input = scanner.nextLine().trim();
=======
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git

            try {
                nPlayers = Integer.parseInt(input);
                if (nPlayers >= 1 && nPlayers <= 6) {
                    valid = true;
                } else {
                    System.out.print("Please enter a number between 1 and 6: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number (1-6): ");
            }
        };      
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player());
        }
        Dealer dealer = new Dealer();
        boolean playAgain = true;
        while (playAgain) {
            startGame(players, dealer, scanner);
            System.out.print("\nPlay again? (y/n): ");
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("y");
        }
        System.out.println("Game finish!");
        scanner.close();
    }
    
    private static void startGame(List<Player> players, Dealer dealer, Scanner scanner) {
        Decks.reset();
        for (Player p : players)
        	p.reset();
        dealer.reset();

        Dealer.initialDeal(players, dealer);

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            System.out.print("Player " + (i + 1) + "'s hand: ");
            printHand(p.getHand());
            System.out.println(" | Value: " + calValue(p.getHand()));
        }
        System.out.print("Dealer's hand: ");
        dealer.showHand(false);
        for (int i = 0; i < players.size(); i++) {
            playerTurn(players.get(i), dealer, scanner, i + 1);
        }
        boolean live = false;
        // determine if there is player not busted
        for (Player p : players) {
            if (calValue(p.getHand()) <= 21) {
                live = true;
                break;
            }
        }
        if (live) {
            System.out.println("\n--- Dealer shown hidden card ---");
            dealer.play();
            System.out.print("Dealer's final hand: ");
            dealer.showHand(true);
        } else {
            System.out.println("\nAll players busted! Dealer wins.");
        }

        int dValue = calValue(dealer.getHand());
        boolean dBusted = dValue > 21;

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int pValue = calValue(p.getHand());

            System.out.print("Player " + (i + 1) + ": ");
            if (pValue > 21) {
                System.out.println("BUSTED! You lose.");
            } else if (dBusted) {
                System.out.println("Dealer busted! You win!");
            } else if (pValue > dValue) {
                System.out.println("You win!");
            } else if (pValue < dValue) {
                System.out.println("You lose.");
            } else {
                System.out.println("Push!");
            }
        }
    }
    
    private static void playerTurn(Player player, Dealer dealer, Scanner scanner, int playerNum) {
        while (calValue(player.getHand()) < 21) {
            System.out.print("\nPlayer " + playerNum + " - (H)it or (S)tand? ");
            String choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equals("S")) {
                break;
            }
            if (!choice.equals("H")) {
                System.out.println("Invalid input. Type H or S.");
                continue;
            }
            player.draw();
            Character drawn = player.getHand().get(player.getHand().size() - 1);
            System.out.print("You drew: " + drawn);
            System.out.print(" → Hand: ");
            printHand(player.getHand());
            System.out.println(" | Value: " + calValue(player.getHand()));
            if (calValue(player.getHand()) > 21) {
                System.out.println("BUSTED!");
                return;
            }
        }
    }
    
    private static void printHand(List<Character> hand) {
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i));
            if (i < hand.size() - 1) System.out.print(", ");
        }
    }
}

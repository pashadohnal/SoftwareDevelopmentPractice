<<<<<<< HEAD
package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
	public Dealer() {
		super();
	}
	public static void initialDeal(Player player, Dealer dealer) {
	    List<Player> players = new ArrayList<>();
	    players.add(player);
	    initialDeal(players, dealer);
	}
	public static void initialDeal(List<Player> players, Dealer dealer) {
	    for (Player p : players) {
	        p.draw();
	    }
	    dealer.draw(); 
	    for (Player p : players) {
	        p.draw();
	    }
	    dealer.draw();
	}
	public void play() {
		while (getValue() < 17) {
			draw();
		}
	}
=======
	package blackjack;
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git
	
<<<<<<< HEAD
	public void showHand(boolean showAll) {
		if(showAll) {
			for(Character card : getHand()) {
				System.out.print(card + " ");
			}
			System.out.println("Dealer's hand value: " + getValue());
		} else if(getHand().size()>1){
			Character firstCard = getHand().get(0);
			if(firstCard == 'A')
				System.out.print("Dealer's hand value: A" +" [hidden] ");
		    else
		    	System.out.println("Dealer's hand value: " + Blackjack.valueOf(0, firstCard) + " + [hidden]");
=======
	public class Dealer extends Player {
		public Dealer() {
			super(0);
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git
		}
		public void play() {
			while (getValue() < 17) {
				draw();
			}
		}
		
		public void showHand(boolean showAll) {
			if(showAll) {
				System.out.println("Dealer's hand value: " + getValue());
			} else if(hand.size()>1){
				Character firstCard = hand.get(0);
				System.out.println("Dealer's hand value: " + Blackjack.valueOf(0, firstCard) + " + [hidden]");
			}
			else {
				System.out.println("Dealer has no cards yet.");
			}
		}
	}
<<<<<<< HEAD
}


=======
>>>>>>> branch 'calValue_v1.1' of https://github.com/pashadohnal/SoftwareDevelopmentPractice.git

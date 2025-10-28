package blackjack;

public class Dealer extends Player {
	public Dealer() {
		super();
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

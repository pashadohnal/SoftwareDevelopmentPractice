import java.util.Scanner;

public class BlackJackGame {
    private Deck deck;
    private Hand p1;
    private Hand d1;
    private Scanner s;
    private int initial;
    private int current;  
    private int PandL;
    private int games; 
    public BlackJackGame() {
        deck = new Deck();
        p1 = new Hand();
        d1 = new Hand();
        s = new Scanner(System.in);
        initial = 1000;
        current = 0;
        PandL = 0; 
        games = 0; 
    }

    public void play() {
        System.out.println("Welcome! You have " + initial + " tokens initial.");
        boolean again = true;
        while (again) {
            if (initial <= 0) {
                System.out.println("You lost!");
                System.out.print("Start a new game? (y/n): ");
                String y = s.nextLine().toLowerCase();
                if (y.startsWith("y")) {
                	games++; 
                    initial = 1000;
                    System.out.println("Game " + games + "." +" Tokens reset " + initial + "./n Total P&L: " + PandL);
                    continue;
                } 
                else {
                	again = false;
                    continue;
                }
            }
            Bet();
            Start();
            pturn();
            if (!p1.Bust()) {
                dturn();
            }
            getwinner();
            update();
            System.out.println("Current Tokens: " + initial + ", Total P&L: " + PandL);
            if(initial > 0) {
            	System.out.print("Play again? (y/n): ");
            	again = s.nextLine().toLowerCase().startsWith("y");
                
            }
            if(!again) {
            	System.out.println("Game stop with " + games + " tries and" + initial + " tokens. Total P&L: " + PandL);
            	break;
            }
        }
        s.close();
    }

    private void Bet() {
        while (true) {
            System.out.print("Enter your bet (between 1 -" + initial + " tokens): ");
            try {
            	current = Integer.parseInt(s.nextLine());
                if (current > 0 && current <= initial) {
                	initial -= current;
                    System.out.println("Bet placed: " + current + " tokens. Remaining: " + initial);
                    break;
                } 
                else {
                    System.out.println("Invalid bet. Must between 1 and " + initial + " tokens.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void Start() {
        deck = new Deck(); 
        p1 = new Hand();
        d1 = new Hand();

  
        p1.addCard(deck.draw());
        d1.addCard(deck.draw());
        p1.addCard(deck.draw());
        d1.addCard(deck.draw());
        Card d1show = d1.getFirstCard();
        System.out.println("\nDealershown card: " + (d1show));
        System.out.println(p1);
    }

    private void pturn() {
        while (!p1.Bust()) {
            System.out.print("Hit (press h) or Stand (press s)? ");
            String choice = s.nextLine().toLowerCase();
            if (choice.startsWith("s")) {
                break;
            }
            Card newCard = deck.draw();
            p1.addCard(newCard);
            System.out.println("You get: " + newCard);
            System.out.println(p1);
            if (p1.Blackjack()) {
                System.out.println("Blackjack! You win!");
                return;
            }
        }
        if (p1.Bust()) {
            System.out.println("Bust! You lose.");
        }
    }

    private void dturn() {
        System.out.println("\nDealer's hand: " + d1);
        while (d1.getValue() < 17) {
            Card newcard = deck.draw();
            d1.addCard(newcard);
            System.out.println("Dealer draws: " + newcard);
            System.out.println(d1);
        }
        if (d1.Bust()){
            System.out.println("Dealer busts! You win.");
        }
    }

    private void getwinner() {
        if (p1.Bust()) {
        	return;
        }
        if (d1.Bust()) {
        	return; 
        }

        int pvalue = p1.getValue();
        int dvalue = d1.getValue();

        if (pvalue > dvalue) {
            System.out.println("You win");
        } 
        else if (dvalue > pvalue) {
            System.out.println("Dealer win");
        }
        else { //(further extension for this rule)
            System.out.println("Tie.");
        }
    }

    private void update() {
        if (p1.Bust()) {
        	PandL -= current;
            System.out.println("Lost " + current + " tokens.");
        } 
        else if (d1.Bust()) {
            int win = current * 2;
            int profit = current;
            initial += win;
            PandL += profit;
            System.out.println("Won " + win + " tokens.");
        } 
        else if (p1.Blackjack()) {
            int winnings = (int) (current * 3);
            int profit = (int) (current * 2);
            initial += winnings;
            PandL += profit;
            System.out.println("Won " + winnings + " tokens.");
        } 
        else if(d1.Blackjack()) {
        	int win = -(int) (current * 3);
            int profit = -(int) (current * 2);
            initial += win;
            PandL += profit;
            System.out.println("Lost " + win + " tokens.");
        }
        else {
            int pvalue = p1.getValue();
            int dvalue = d1.getValue();
            if (pvalue > dvalue) {
                int win = current * 2;
                int profit = current;
                initial += win;
                PandL += profit;
                System.out.println("Won " + win + " tokens.");
            } 
            else if (dvalue > pvalue) {
            	games -= current;
                System.out.println("Lost " + current + " tokens.");
            } 
            else {
            	initial += current;
                System.out.println("Tie with Bet returned.");// not sure yet
            }
        }
        current = 0; 
    }

    public static void main(String[] args) {
        new BlackJackGame().play();
    }
}

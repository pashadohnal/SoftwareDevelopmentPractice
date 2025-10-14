package blackjack;

public class Dealer extends Player {
    public void play(Deck useDeck) {
        while (value < 17) {
            draw(useDeck);
        }
    }
}
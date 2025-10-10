import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> decks;

    public Deck() {
        decks = new ArrayList<>();
        String[] shapes = {"Heart", "Diamond", "Club", "Spade"};
        String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String shape: shapes) {
            for (String number : numbers) {
                decks.add(new Card(shape, number));
            }
        }
        shuffle();
    }
    public void shuffle() {
        Collections.shuffle(decks);
    }

    public Card draw() {
        if (decks.isEmpty()) {
        	return null;
        }
        return decks.remove(0);
    }
}
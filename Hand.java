import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private int A; 

    public Hand() {
        cards = new ArrayList<>();
        A = 0;
    }

    public void addCard(Card card) {
        cards.add(card);
        if (card.getNumber().equals("A")) {
        	A++;
        };
    }

    public int getValue() {
        int value = 0;
        for (Card card : cards) {
            value += card.getValue();
        }
        for (int i = 0; i < A; i++) {
            if (value + 10 <= 21) {
                value += 10;
            }
        }
        return value;
    }

    public boolean Bust() {
        return getValue() > 21;
    }

    public boolean Blackjack() {
        return getValue() == 21;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hand: ");
        for (Card card : cards) {
            sb.append(card.toString()).append(", ");
        }
        sb.append("(Value: ").append(getValue()).append(")");
        return sb.toString();
    }
    public Card getFirstCard() {
        return cards.get(0);
    }
}
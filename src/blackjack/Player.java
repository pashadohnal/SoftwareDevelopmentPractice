package blackjack;

import java.util.*;

public class Player {
    protected ArrayList<String> hand = new ArrayList<>();
    protected int value = 0;
    private int accountBalance = 0;

    public Player(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    // Draw a card from the deck
    public void draw() {
        String card = Decks.draw();
        hand.add(card);
        try {
            value = Blackjack.calValue(hand);
        } catch (blackjack.InvalidCardException e) {
            System.out.println("Error calculating value: " + e.getMessage());
        }
    }

    // Reset player's hand
    public void reset() {
        hand.clear();
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public int getBalance() {
        return accountBalance;
    }

    public void updateBalance(int delta) {
        accountBalance += delta;
    }

    public ArrayList<String> getHand() {
        return new ArrayList<>(hand);
    }

    @Override
    public String toString() {
        return "Hand: " + hand + " (Value: " + value + ")";
    }
}

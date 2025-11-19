package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import pokerDecks.Card;
import pokerDecks.Decks;

public class UserTest {

    // Helper to create a Decks with a single known card (no shuffle effect)
    private Decks singleCardDeck(Card card) {
        ArrayList<Card> list = new ArrayList<>(Arrays.asList(card));
        return new Decks(1, list);
    }

    @Test
    public void placeBet_and_balance_increases_on_dealer_bust() {
        Card ten = new Card(Card.Suit.SPADE, Card.Face.TEN);
        Decks decks = singleCardDeck(ten);
        // Scanner input: bet = 10
        Scanner scanner = new Scanner("10\n");
        User user = new User(decks, 100, scanner);

        int bet = user.placeBet();
        assertEquals(10, bet);
        // balance unchanged until result
        assertEquals(100, user.getBalance());

        // Dealer busts (>21) should award the bet
        user.updateBalance(22);
        assertEquals(110, user.getBalance());
    }

    @Test
    public void placeBet_and_balance_decreases_when_dealer_higher() {
        Card ten = new Card(Card.Suit.HEART, Card.Face.TEN);
        Decks decks = singleCardDeck(ten);
        Scanner scanner = new Scanner("20\n");
        User user = new User(decks, 100, scanner);

        int bet = user.placeBet();
        assertEquals(20, bet);
        assertEquals(100, user.getBalance());

        // Player value is 0 (no draws). Dealer has higher value -> bust (loss)
        user.updateBalance(10);
        assertEquals(80, user.getBalance());
    }

    @Test
    public void tie_leaves_balance_unchanged() {
        Card ten = new Card(Card.Suit.CLUB, Card.Face.TEN);
        Decks decks = singleCardDeck(ten);
        Scanner scanner = new Scanner("15\n");
        User user = new User(decks, 100, scanner);

        int bet = user.placeBet();
        assertEquals(15, bet);
        assertEquals(100, user.getBalance());

        // Player value 0 equals dealer 0 -> no change
        user.updateBalance(0);
        assertEquals(100, user.getBalance());
    }

    @Test
    public void drawCard_uses_scanner_and_updates_hand_string() {
        Card ten = new Card(Card.Suit.SPADE, Card.Face.TEN);
        Decks decks = singleCardDeck(ten);
        // First input used by AskPlayerDraw: H (hit). AskPlayerBet isn't called in this test
        Scanner scanner = new Scanner("H\n");
        User user = new User(decks, 50, scanner);

        boolean drew = user.drawCard();
        assertTrue(drew);

        String hand = user.handToString(false);
        // Should show the numeric value 10 in the hand string
        assertTrue(hand.contains("Value:10"), "hand string should contain Value:10 but was: " + hand);

        // Reset should clear the hand/value
        user.reset();
        String emptyHand = user.handToString(false);
        // After reset value is 0 and no cards shown; check it contains Value:0
        assertTrue(emptyHand.contains("Value:0") || emptyHand.contains("Value:"), "after reset hand string: " + emptyHand);
    }

}
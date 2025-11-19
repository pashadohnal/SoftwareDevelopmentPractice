package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import pokerDecks.Card;
import pokerDecks.Decks;

public class UserIntegrationTest {

    @Test
    public void testPlaceBetImmediate() {
        Scanner scanner = new Scanner("25\n");
        // deck is irrelevant for betting; provide a minimal deck
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Face.TEN));
        Decks decks = new Decks(1, cards);

        User user = new User(decks, 100, scanner);
        int bet = user.placeBet();
        assertEquals(25, bet, "User should place the bet provided via scanner");
        // placing a bet does not immediately change balance (Gambling stores the bet)
        assertEquals(100, user.getBalance(), "Balance should remain unchanged until round resolution");
    }

    @Test
    public void testPlaceBetInvalidThenValidEmitsError() {
        String input = "notanumber\n999\n15\n"; // invalid, exceed, then valid
        Scanner scanner = new Scanner(input);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADE, Card.Face.A));
        Decks decks = new Decks(1, cards);

        User user = new User(decks, 50, scanner);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            int bet = user.placeBet();
            String output = baos.toString();
            assertTrue(output.contains("Invalid") , "Should print an invalid input/bet message when bad input provided");
            assertEquals(15, bet, "After invalid inputs, the subsequent valid bet should be returned");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testDrawHitAndValueUpdated() {
        // Scanner will provide 'H' -> hit
        Scanner scanner = new Scanner("H\n");
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Face.TEN));
        Decks decks = new Decks(1, cards);

        User user = new User(decks, 10, scanner);
        boolean drew = user.drawCard();
        assertTrue(drew, "User should draw when input is 'H'");
        assertEquals(10, user.getValue(), "After drawing TEN, user's value should be 10");
    }

    @Test
    public void testDrawInvalidThenStandShowsMessage() {
        String input = "\nX\nS\n"; // empty, invalid, then stand
        Scanner scanner = new Scanner(input);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Face.FIVE));
        Decks decks = new Decks(1, cards);

        User user = new User(decks, 10, scanner);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            boolean drew = user.drawCard();
            String output = baos.toString();
            assertTrue(output.contains("Invalid input. Please type H or S."), "Should print invalid input message for bad draw input");
            assertFalse(drew, "After receiving 'S' the user should not draw");
        } finally {
            System.setOut(originalOut);
        }
    }

}
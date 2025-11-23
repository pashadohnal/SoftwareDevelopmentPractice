package blackjack.strategies;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AskPlayerBetUnitTest {

    @Test
    public void testValidBetImmediate() {
        Scanner scanner = new Scanner("100\n");
        AskPlayerBet strategy = new AskPlayerBet(scanner);
        int bet = strategy.placeBet("player",100);
        assertEquals(100, bet);
    }

    @Test
    public void testNonNumericThenValid() {
        Scanner scanner = new Scanner("abc\n30\n");
        AskPlayerBet strategy = new AskPlayerBet(scanner);
        int bet = strategy.placeBet("player",100);
        assertEquals(30, bet);
    }

    @Test
    public void testNegativeThenValid() {
        Scanner scanner = new Scanner("-5\n10\n");
        AskPlayerBet strategy = new AskPlayerBet(scanner);
        int bet = strategy.placeBet("player",50);
        assertEquals(10, bet);
    }

    @Test
    public void testZeroThenValid() {
        Scanner scanner = new Scanner("0\n5\n");
        AskPlayerBet strategy = new AskPlayerBet(scanner);
        int bet = strategy.placeBet("player",20);
        assertEquals(5, bet);
    }

    @Test
    public void testExceedThenValidAndOutputContainsErrorMessages() {
        String input = "999\nnotanumber\n15\n";
        Scanner scanner = new Scanner(input);
        AskPlayerBet strategy = new AskPlayerBet(scanner);

        // capture System.out to assert messages printed
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            int bet = strategy.placeBet("player",50);
            String output = baos.toString();

            assertTrue(output.contains("Invalid bet amount") || output.contains("Invalid input"),
                    "Expected an error message for invalid inputs");
            assertEquals(15, bet);
        } finally {
            System.setOut(originalOut);
        }
    }

}

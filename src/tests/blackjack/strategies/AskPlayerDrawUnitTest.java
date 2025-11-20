package blackjack.strategies;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AskPlayerDrawUnitTest {

    @Test
    public void testHitImmediate() {
        Scanner scanner = new Scanner("H\n");
        AskPlayerDraw strategy = new AskPlayerDraw(scanner);
        assertTrue(strategy.drawCard(""), "Input 'H' should result in true (hit)");
    }

    @Test
    public void testStandImmediate() {
        Scanner scanner = new Scanner("S\n");
        AskPlayerDraw strategy = new AskPlayerDraw(scanner);
        assertFalse(strategy.drawCard(""), "Input 'S' should result in false (stand)");
    }

    @Test
    public void testLowercaseAndWhitespaceHandled() {
        Scanner scanner = new Scanner("  h  \n");
        AskPlayerDraw strategy = new AskPlayerDraw(scanner);
        assertTrue(strategy.drawCard(""), "Lowercase 'h' with whitespace should still be interpreted as hit");
    }

    @Test
    public void testInvalidThenHitProducesErrorMessage() {
        String input = "x\nH\n"; // invalid then valid
        Scanner scanner = new Scanner(input);
        AskPlayerDraw strategy = new AskPlayerDraw(scanner);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            boolean result = strategy.drawCard("");
            String output = baos.toString();
            assertTrue(output.contains("Invalid input. Please type H or S."), "Should print invalid input message");
            assertTrue(result, "After invalid input, subsequent 'H' should return true");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testEmptyThenStandProducesErrorMessage() {
        String input = "\nS\n"; // empty line then stand
        Scanner scanner = new Scanner(input);
        AskPlayerDraw strategy = new AskPlayerDraw(scanner);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            boolean result = strategy.drawCard("");
            String output = baos.toString();
            assertTrue(output.contains("Invalid input. Please type H or S."), "Should print invalid input message for empty input");
            assertFalse(result, "After empty input then 'S', drawCard should return false");
        } finally {
            System.setOut(originalOut);
        }
    }

}
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
	public void testExceedingBalanceThenValid() {
		Scanner scanner = new Scanner("200\n50\n");
		AskPlayerBet strategy = new AskPlayerBet(scanner);
		int bet = strategy.placeBet("player", 100);
		assertEquals(50, bet);
	}
}

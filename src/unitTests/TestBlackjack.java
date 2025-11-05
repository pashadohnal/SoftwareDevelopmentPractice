package unitTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import blackjack.Blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public class TestBlackjack {
	@Test
	public void test_calValue_simple() {
		ArrayList<Character> hand = new ArrayList<>(Arrays.asList('2'));
		assertEquals(2, Blackjack.calValue(hand));
	}
	
	@Test
	public void test_calValue_A_max() {
		ArrayList<Character> hand = new ArrayList<>(Arrays.asList('2', 'A'));
		assertEquals(13, Blackjack.calValue(hand));
	}

	@Test
	public void test_calValue_A_min() {
		ArrayList<Character> hand = new ArrayList<>(Arrays.asList('k', '2', '8', 'A'));
		assertEquals(21, Blackjack.calValue(hand));
	}
}

package blackjack.strategies;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BnUnitTest {

    @Test
    public void testTenPercentOfHundred() {
        Bn strategy = new Bn(10);
        assertEquals(10, strategy.placeBet(100));
    }

    @Test
    public void testTenPercentRoundedDown() {
        Bn strategy = new Bn(10);
        // 49 * 10 / 100.0 = 4.9 -> (int) -> 4
        assertEquals(4, strategy.placeBet(49));
    }

    @Test
    public void testThirtyThreePercentSmallBalance() {
        Bn strategy = new Bn(33);
        assertEquals(33, strategy.placeBet(100));
        // 1 * 33 / 100.0 = 0.33 -> 0
        assertEquals(0, strategy.placeBet(1));
    }

    @Test
    public void testZeroPercentAndFullPercent() {
        Bn zero = new Bn(0);
        assertEquals(0, zero.placeBet(1234));

        Bn full = new Bn(100);
        assertEquals(500, full.placeBet(500));
    }

    @Test
    public void testQuarterPercentSmallBalance() {
        Bn strategy = new Bn(25);
        // 3 * 25 / 100.0 = 0.75 -> 0
        assertEquals(0, strategy.placeBet(3));
        // 4 * 25 /100.0 = 1.0 -> 1
        assertEquals(1, strategy.placeBet(4));
    }

}
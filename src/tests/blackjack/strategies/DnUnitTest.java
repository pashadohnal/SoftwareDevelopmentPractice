package blackjack.strategies;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DnUnitTest {

    @Test
    public void testDrawWhenValueLessThanN() {
        Dn strategy = new Dn(17);
        assertTrue(strategy.drawCard(16), "Value less than n should return true (draw)");
    }

    @Test
    public void testDoNotDrawWhenValueEqualToN() {
        Dn strategy = new Dn(17);
        assertFalse(strategy.drawCard(17), "Value equal to n should return false (do not draw)");
    }

    @Test
    public void testDoNotDrawWhenValueGreaterThanN() {
        Dn strategy = new Dn(17);
        assertFalse(strategy.drawCard(20), "Value greater than n should return false (do not draw)");
    }

    @Test
    public void testZeroThresholdBehavior() {
        Dn strategy = new Dn(0);
        assertFalse(strategy.drawCard(0), "With n=0, value 0 should not draw");
        assertFalse(strategy.drawCard(1), "With n=0, any non-negative value should not draw");
    }

    @Test
    public void testLargeThresholdBehavior() {
        Dn strategy = new Dn(100);
        assertTrue(strategy.drawCard(99), "Value one less than large n should draw");
        assertFalse(strategy.drawCard(100), "Value equal to large n should not draw");
    }

}
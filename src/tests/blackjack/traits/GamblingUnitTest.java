package blackjack.traits;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GamblingUnitTest {

    @Test
    public void testPlaceBetExceedsBalance() {
        Gambling g = new Gambling(100);
        assertFalse(g.placeBet(200), "Placing a bet larger than the balance should fail");
        assertEquals(100, g.getBalance(), "Balance should remain unchanged when bet fails");
    }

    @Test
    public void testPlaceBetValidAndWinByHigherValue() {
        Gambling g = new Gambling(100);
        assertTrue(g.placeBet(30), "Valid bet should be accepted");
        // self (20) > dealer (19) -> blackjack (win)
        g.updateBalance(20, 19);
        assertEquals(130, g.getBalance(), "Winning should increase balance by the bet amount");
    }

    @Test
    public void testSelfBustCausesLoss() {
        Gambling g = new Gambling(100);
        assertTrue(g.placeBet(25));
        // selfValue > 21 -> bust
        g.updateBalance(22, 15);
        assertEquals(75, g.getBalance(), "Busting should decrease balance by the bet amount");
    }

    @Test
    public void testDealerBustCountsAsWin() {
        Gambling g = new Gambling(50);
        assertTrue(g.placeBet(10));
        // dealerValue > 21 -> blackjack (win)
        g.updateBalance(18, 22);
        assertEquals(60, g.getBalance(), "Dealer bust should count as a win and increase balance");
    }

    @Test
    public void testTieDoesNotChangeBalance() {
        Gambling g = new Gambling(80);
        assertTrue(g.placeBet(20));
        // tie -> neither blackjack() nor bust() called -> balance unchanged
        g.updateBalance(18, 18);
        assertEquals(80, g.getBalance(), "A tie should leave the balance unchanged");
    }

    @Test
    public void testBetExactBalanceAndLose() {
        Gambling g = new Gambling(40);
        assertTrue(g.placeBet(40), "Betting the entire balance should be accepted");
        // selfValue < dealerValue -> bust (loss)
        g.updateBalance(15, 20);
        assertEquals(0, g.getBalance(), "Losing with the full-balance bet should leave balance at zero");
    }

}
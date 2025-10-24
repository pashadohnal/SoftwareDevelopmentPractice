package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import blackjack.*;

public class TestPlayer {
	
    @Test
    public void testDraw() {
    	Deck.reset();
    	ArrayList<Character> cards = Deck.getDeck();
    	Player player = new Player();
    	player.draw();
    	assertEquals(player.getValue(), Blackjack.valueOf(0, cards.get(0)));
    }

}

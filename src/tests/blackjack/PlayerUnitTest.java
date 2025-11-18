package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import gameModes.Local;
import pokerDecks.Decks;

public class PlayerUnitTest {
	
    @Test
    public void testDraw() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Player player = new Player();
    	player.draw();
    	assertEquals(player.getValue(), Local.valueOf(0, cards.get(0)));
    }

}

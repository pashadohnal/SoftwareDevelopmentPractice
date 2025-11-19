package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import gameModes.Local;
import pokerDecks.Decks;

public class DealerTest {
    @Test
    public void testDraw2() {
    	Decks decks = new Decks(); 
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.drawCard();
    	assertEquals(dealer.getValue(), Local.valueOf(0, cards.get(0)));
    }
    @Test
    public void testDraw3() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.drawCard();
    	assertEquals(dealer.getValue(), Local.valueOf(0, cards.get(0)));
    	dealer.reset();
    	assertEquals(dealer.getValue(), 0);
    }
    @Test
    public void testDraw4() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.drawCard();
    	dealer.drawCard();
    	assertEquals(dealer.getValue(), Local.valueOf(0, cards.get(0)) + Local.valueOf(Local.valueOf(0, cards.get(0)), cards.get(1)));
    }
    @Test
    public void testDraw5() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.drawCard('7');
    	dealer.drawCard('8');
    	dealer.drawCard('2');
    	dealer.autoDraw();
    	dealer.autoDraw();
    	assertEquals(dealer.getValue(), 17);
    	assertEquals(3,dealer.getHand().size());
    }
    @Test
    public void testDraw6() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.autoDraw();
    	assertTrue(dealer.getValue() > 17);
    	assertTrue(dealer.getValue() < 28);
    }
    @Test
    public void testDraw7() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.drawCard('9');
    	dealer.drawCard('8');
    	dealer.drawCard('2');
    	dealer.autoDraw();
        
        assertEquals(3, dealer.getHand().size());
    }
    
    
}

package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import blackjack.*;

public class TestDealer {
    @Test
    public void testDraw2() {
    	Decks decks = new Decks(); 
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.draw();
    	assertEquals(dealer.getValue(), SinglePlayer.valueOf(0, cards.get(0)));
    }
    @Test
    public void testDraw3() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.draw();
    	assertEquals(dealer.getValue(), SinglePlayer.valueOf(0, cards.get(0)));
    	dealer.reset();
    	assertEquals(dealer.getValue(), 0);
    }
    @Test
    public void testDraw4() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.draw();
    	dealer.draw();
    	assertEquals(dealer.getValue(), SinglePlayer.valueOf(0, cards.get(0)) + SinglePlayer.valueOf(SinglePlayer.valueOf(0, cards.get(0)), cards.get(1)));
    }
    @Test
    public void testDraw5() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Bot dealer = new Bot();
    	dealer.draw('7');
    	dealer.draw('8');
    	dealer.draw('2');
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
    	dealer.draw('9');
    	dealer.draw('8');
    	dealer.draw('2');
    	dealer.autoDraw();
        
        assertEquals(3, dealer.getHand().size());
    }
    
    
}

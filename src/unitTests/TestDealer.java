package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import blackjack.*;

public class TestDealer {
    @Test
    public void testDraw2() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Dealer dealer = new Dealer();
    	dealer.draw();
    	assertEquals(dealer.getValue(), Blackjack.valueOf(0, cards.get(0)));
    }
    @Test
    public void testDraw3() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Dealer dealer = new Dealer();
    	dealer.draw();
    	assertEquals(dealer.getValue(), Blackjack.valueOf(0, cards.get(0)));
    	dealer.reset();
    	assertEquals(dealer.getValue(), 0);
    }
    @Test
    public void testDraw4() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Dealer dealer = new Dealer();
    	dealer.draw();
    	dealer.draw();
    	assertEquals(dealer.getValue(), Blackjack.valueOf(0, cards.get(0)) + Blackjack.valueOf(Blackjack.valueOf(0, cards.get(0)), cards.get(1)));
    }
    @Test
    public void testDraw5() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Dealer dealer = new Dealer();
    	dealer.draw('7');
    	dealer.draw('8');
    	dealer.draw('2');
    	dealer.play();
    	dealer.play();
    	assertEquals(dealer.getValue(), 17);
    	assertEquals(3,dealer.getHand().size());
    }
    @Test
    public void testDraw6() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Dealer dealer = new Dealer();
    	dealer.play();
    	assertTrue(dealer.getValue() > 17);
    	assertTrue(dealer.getValue() < 28);
    }
    @Test
    public void testDraw7() {
    	Decks.reset();
    	ArrayList<Character> cards = Decks.getDeck();
    	Dealer dealer = new Dealer();
    	dealer.draw('9');
    	dealer.draw('8');
    	dealer.draw('2');
    	dealer.play();
        
        assertEquals(3, dealer.getHand().size());
    }
    
    
}

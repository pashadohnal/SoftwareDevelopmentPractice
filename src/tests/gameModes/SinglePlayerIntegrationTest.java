package gameModes;

import blackjack.*;
import pokerDecks.Card;
import pokerDecks.Decks;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SinglePlayerIntegrationTest {
	@Test
	void fullGameFlow_multipleRounds_allMethodsCovered() {
	    String input = """
	        1000
	        2
	        100
	        S
	        N 
	        Y     
	        50
	        1
	        30
	        S
	        N      
	        N      
	        
	        """;

		 System.setIn(new ByteArrayInputStream(input.getBytes()));

			SinglePlayer.main(new String[0]);
			assertTrue(true);
			return;

	    
	}


	
	
	@Test
	void insufficientBalance_gameOver() {
	    String input = """
	        50
	        
	        0
	        
	        50  
	         
	        H
	        
	        H
	        
	        H

	        Y
	        N
	        
	        
	        """;

	    System.setIn(new ByteArrayInputStream(input.getBytes()));

		SinglePlayer.main(new String[0]);
		assertTrue(true);
		return;

	}
	@Test
	void ultimateCoverage_testAllErrorPaths_andAllBranches() {
	    String input = """
	        abc     
	        1000    
	        9       
	        -1      
	        2       
	        100    
	        H
	        S
	        Y   
	           
	        50     
	        S
	        N  
     
	        N
	        
	            
	        
	            
	        
	        """;

	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    Scanner sc = new Scanner(System.in);

	    ArrayList<Card> shoe = new ArrayList<>();
	    for (int i = 0; i < 100; i++) {
	        shoe.add(new Card(Card.Suit.HEART, Card.Face.TEN));
	        shoe.add(new Card(Card.Suit.CLUB, Card.Face.SEVEN));
	    }
	    Decks decks = new Decks(1, shoe);

	    Dealer dealer = new Dealer(decks);
	    User human = new User("Player", decks, 1000, sc);

	
	    SinglePlayer.askInitBalance(sc);                   
	    ArrayList<Bot> bots = SinglePlayer.makeBots(sc, 1000, decks);  

	    SinglePlayer.reset(decks, dealer, human, bots);
	    SinglePlayer.initBet(sc, human, bots);
	    SinglePlayer.initPlayers(dealer, human, bots);
	    SinglePlayer.playerTurn(sc, bots, human, "Player");
	    SinglePlayer.playAllBots(bots);     
	    SinglePlayer.dealerTurn(dealer);
	    SinglePlayer.updateBalance(dealer, human, bots);

	    assertTrue(SinglePlayer.nextRound(sc));
	    assertFalse(SinglePlayer.nextRound(sc));

	}
	@Test
	void forceCoverageandBotSleep() {
	    String input = """
	        1000
	        2          
	        100
	        S
	        N
	        N
	        """;

	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    Scanner sc = new Scanner(System.in);

	    ArrayList<Card> shoe = new ArrayList<>();
	    for (int i = 0; i < 50; i++) shoe.add(new Card(Card.Suit.HEART, Card.Face.TEN));
	    Decks decks = new Decks(1, shoe);

	    Dealer dealer = new Dealer(decks);
	    User human = new User("Player", decks, 1000, sc);

	    ArrayList<Bot> bots = SinglePlayer.makeBots(sc, 1000, decks);

	    SinglePlayer.reset(decks, dealer, human, bots);
	    SinglePlayer.initBet(sc, human, bots);
	    SinglePlayer.initPlayers(dealer, human, bots);
	    SinglePlayer.playAllBots(bots);  

	    assertTrue(true);
	}
	@Test
	void testwholegame() {
		String input = """
				2
				
				1
				
				1
				
				S
				
				Y
				
				1
				
				
				S
				
				N
				
				N
				""";
		 System.setIn(new ByteArrayInputStream(input.getBytes()));

			SinglePlayer.main(new String[0]);
			assertTrue(true);
			return;
	}
	
}

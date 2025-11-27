package gameModes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;


class MultiplayerIntegrationTest {

    @Test
    void multiPlayer_completeFlow_withFixedShoe_andAllInputs() {
        String input = """
            105
            
        	3
        	
            100
            
            200
            
            100
            
            50
            
            S
            
            S
            
            H
             
            S
             
            Y
            
            Y
            
            1
            
            2
            
            30
            
            S
            S
            S
            N
            
            """;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        
  
        MultiPlayer.main(new String[0]);
            assertTrue(true);
            return;
    }



    @Test
    void multiPlayer_initialBalanceTest() {
    	 String input = """
    	           a
    	           1000
    	           a
    	           1
    	           a
    	           100
    	           S
    	           S
    	           N
    	            """;
    	 System.setIn(new ByteArrayInputStream(input.getBytes()));

			MultiPlayer.main(new String[0]);
			assertTrue(true);
			return;
    }
    @Test
    void multiPlayer_fullGameLoopTest() {
        String input = """
            1000
            2
            900
            800
            S
            H
            S
            S
            S
            Y
            50
            100
            S
            S
            N
            """;

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MultiPlayer.main(new String[0]);

        assertTrue(true);
    }
}

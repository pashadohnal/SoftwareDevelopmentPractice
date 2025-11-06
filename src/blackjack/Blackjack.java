package blackjack;

import java.util.ArrayList;
import blackjack.*;

public class Blackjack {
	static public int noOfDeckUsed =1;

	@Deprecated
	// use calValue instead
    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10; 
		else return (int)card - '0'; //Card 10 would be 1. There is no conversion from char "1" into 10. Either better to store as a string or convert using a dictionary (Map).
    }
	
    static public int calValue(ArrayList<Character> hand) {
    	int value =0;
    	int noA =0;
    	for (Character card: hand) {
    		if (!Decks.RANKS.contains(card)) continue;
    		
    		if (card=='A') {value +=1; noA++; continue;}
    		if (card=='J' || card=='Q' || card=='K') value +=10;
    		else value +=(int)card - '0';
    		
    	}

    	ArrayList<Integer> values = new ArrayList<>();
    	values.add(value);
    	while(noA-->0) {values.add(values.getLast()+10);}

    	int index =0;
    	while (index+1<values.size() && values.get(index+1)<=21) index++;
    	return values.get(index);
    }

    	ArrayList<Integer> values = new ArrayList<>();
    	values.add(value);
    	while(noA-->0) {values.add(values.getLast()+10);}

    	int index =0;
    	while (index+1<values.size() && values.get(index+1)<=21) index++;
    	return values.get(index);
    }
}

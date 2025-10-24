package blackjack;

import java.util.ArrayList;

public class Blackjack {
	static public int noOfDeckUsed =1;
    // cal values
    // it is better to let the player class to calculate the value
    static public int valueOf(int value, Character card) {
		if (card=='A') return(value+11<=21)?11:1;
		if (card=='J' || card=='Q' || card=='K') return 10; 
		else return (int)card - '0'; //Card 10 would be 1. There is no conversion from char "1" into 10. Either better to store as a string or convert using a dictionary (Map).
    }
    
    static public int calValue(ArrayList<Character> hand) throws InvalidCardException {
    	int value =0;
    	int noA =0;
    	for (Character card: hand) {
    		if (card=='A') {value +=1; noA++;}
    		if (card=='J' || card=='Q' || card=='K') return value +=10;
    		else throw new InvalidCardException("asd");
    	}

    	ArrayList<Integer> values = new ArrayList<>();
    	while(noA>=0) values.add(value+10*noA);

    	int index =0;
    	while (values.get(index)>21 && index<values.size()) index++;
    	return values.get(index);
    }
}

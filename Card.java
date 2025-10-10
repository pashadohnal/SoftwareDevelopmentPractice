public class Card {
    private String shape;
    private String number;

    public Card(String shape1, String number1) {
        this.shape = shape1;
        this.number = number1;
    }

    public int getValue() {
        if (number.equals("A")) {
        	return 1;
        }
        if (number.equals("J") || number.equals("Q") || number.equals("K")) {
        	return 10;
        }
        return Integer.parseInt(number);
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return shape + number;
    }
}
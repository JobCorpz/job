
public class SuitedCard extends CardSuperItem
{
	//This class needs to be completed for the second part of the coursework (Difficulty #2: Include Face Cards)
	
	//This second difficulty is designed to be a more difficult task for students to show their understanding.
	//As such, you are expected to solve this problem using the knowledge you have learnt from the teaching materials
	//and completing Difficulty #1. Do not attempt to solve this until you have the rest working!
	
	//Examples of expected behaviour including this class can be found in Appendix D and E in the coursework specification
	
	
	
	SuitedCard(int value, String cardName, String suit) {
		super(value, cardName, suit);
	}
	

	@Override
	public int applyCardEffect() {
        String cardName = getCardName();

        if (cardName.equals("Jack")) {
            // Halve the current hand total
            return -99; 
        } else if (cardName.equals("Queen")) {
            // Double the current hand total
            return -99; 
        } else if (cardName.equals("King")) {
            // Double the next card's value 
            return -99; 
        } else {
        	if (getSuit().equals("Spades") || getSuit().equals("Clubs")) {
                return getValue(); // Black card: add value
            } else {
                return -(getValue() / 2); // Red card: deduct half value
            }
        }
    }

	@Override
    public void printCardDetails() { 
        System.out.println(getCardName() + " of " + getSuit());
    }}
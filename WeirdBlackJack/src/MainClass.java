import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainClass
{
	//DO NOT CHANGE THIS ARRAYLIST! 
	public static ArrayList<CardSuperItem> drawDeck;
	//DO NOT CHANGE THIS ARRAYLIST!
	
	//YOU NEED TO ADD A NEW ARRAYLIST HERE TO REPRESENT THE CARDS IN A PLAYER'S HAND (see name from coursework)
	public static ArrayList<CardSuperItem> playerHand = new ArrayList<>();

	public static void main(String[] args) 
	{
		/**
		 * STEP 0: The code below WILL NOT COMPILE. Fix it, so you have the correct local variables. For later tasks you might need to add
		 * 	       variables here too. See coursework spec.
		 */
		Scanner scanner = new Scanner(System.in);
		int currentHandTotal = 0;
		CardSuperItem drawnCard;
		Random random = new Random();
		
		
		System.out.println("Welcome to Weird Blackjack! Your aim is to draw as many cards as you can, without going over a value of 21!");
		System.out.println("Select which you want to play with:\n1. Number Cards Only\n2. Include Face Cards");
		
		/**
		 * STEP 1: You should create your deck of cards for the game here, based upon the player's response above.
		 *         To do this, you should call a version of createDeckOfCards() you have completed or created depending on mode chosen.
		 */
		int option = scanner.nextInt(); 
		if (option == 1) {
            createDeckOfCards();
        } else if (option == 2) {
            createDeckOfCards(4);
        } else {
            System.out.println("Invalid Option Selected");
            System.exit(0);
        }
		
		
		/**
		 * STEP 2: You should now draw a card for the player, apply its effects (if any) and put it in their hand. Remember to check if they've gone bust!
		 * 	       HINT: cards should be removed from drawDeck when drawn, so you cannot draw the same card twice, and
		 * 		         then put in another ArrayList to track the cards the player has drawn.
		 */
	
		
		while(currentHandTotal < 21)
		{
			//Code for drawing cards and resolving their effects goes here...
			System.out.println("Okay, drawing a new card:");
			int drawnCardIndex = random.nextInt(drawDeck.size());
			
            drawnCard = drawDeck.remove(drawnCardIndex);
            playerHand.add(drawnCard);
            boolean doubleNextCard = false;
            
           
            if (option == 1) {
            	if(drawnCard.getCardName().startsWith("A") || drawnCard.getCardName().startsWith("E")) {
            		System.out.println("You drew an " + drawnCard.getCardName());
            	} else {
            		System.out.println("You drew a " + drawnCard.getCardName());
            	} 
            	
            } else if (option == 2) {
            	if(drawnCard.getCardName().startsWith("A") || drawnCard.getCardName().startsWith("E")) {
            		System.out.println("You drew an " + drawnCard.getCardName() + " of " + drawnCard.getSuit());
            	} else {
            		System.out.println("You drew a " + drawnCard.getCardName() + " of " + drawnCard.getSuit());
            	}
            }

            
            
            int cardValue = drawnCard.applyCardEffect();
            
            
            if (cardValue == -99) {
            	String cardName = drawnCard.getCardName();

            	switch (cardName) {
                case "Jack":
                    currentHandTotal /= 2;
                    System.out.println("Ooh a Jack! Your total has been halved!");
                    break;
                case "Queen":
                    currentHandTotal *= 2;
                    System.out.println("Oh no a Queen! Your total has been doubled!");
                    break;
                case "King":
                    doubleNextCard = true;
                    System.out.println("Yikes, a King! The value of the next card will be doubled!");
                    break;
                }
            } else {
            	if (doubleNextCard && drawnCard instanceof NumberCard) {
            	    
            	    if (drawnCard.getSuit().equals("Spades") || drawnCard.getSuit().equals("Clubs")) {
            	        cardValue *= 2; // Black card: double positive value
            	    } else {
            	        cardValue *= 2; // Red card: double negative value
            	    }
            	    doubleNextCard = false;
            	    System.out.println("King effect activated! The value of this card is doubled: " + cardValue);
            	    currentHandTotal += cardValue;
            	} else {
            	    currentHandTotal += drawnCard.applyCardEffect();
            	}

            }

			//Code for checking whether they've got Blackjack, or gone bust should go here. If they haven't, ask if they want to draw again as per coursework spec!
			
			if (currentHandTotal == 21) {
                System.out.println("Blackjack! You got exactly 21, well done!");
                System.out.println("You drew " + playerHand.size() + " card(s) before getting Blackjack. Well done!");
                break;
            } else if (currentHandTotal > 21) {
                System.out.println("Oh no! You've gone bust with a score of " + currentHandTotal);
                break;
            } else {
            	if(playerHand.size() == 1 ) {
            		System.out.println("You are still in this! You have " + playerHand.size() + " card in hand, and a total of " + currentHandTotal + ".");
            	} else if (playerHand.size() > 1) {
            		System.out.println("You are still in this! You have " + playerHand.size() + " cards in hand, and a total of " + currentHandTotal + ".");
            	}
                System.out.println("Do you want to draw again? Yes or No");
                String response = scanner.next();
                if (response.equalsIgnoreCase("no")) {
                    break;
                } else if (response.equalsIgnoreCase("yes")) {
                    continue;
                }
            }
			

		}
		
		/**
		 * STEP 3: Finally you should tell the players how they've done. Remember there are three possible outcomes:
		 *         * They got Blackjack (exactly 21)
		 *         * They went bust (over 21)
		 *         * They decided to stop drawing cards!
		 */
		//Remember to print all the cards in a player's hand here, and to close any scanners you've opened!
		System.out.println("Your final hand:");
        for (CardSuperItem card : playerHand) {
            System.out.println("A " + card.getCardName());
        }
        
        scanner.close();
	}
	
	private static void createDeckOfCards()
	{
		//This should fill the drawDeck variable with the expected number of card objects. for Difficulty #1.
		drawDeck = new ArrayList<>();
		//This initial version should just create the correct number of NumberCards, with the expected values of 1 to 6, as in the coursework specification
		for (int value = 1; value <= 6; value++) {
	        for (int repeat = 0; repeat < 3; repeat++) {
	        	drawDeck.add(new NumberCard(value, "", CardsDetails.cardsNames[value - 1]));
	        }
	    }

		//Hint: When adding a new NumberCard to the drawDeck ArrayList, make sure you call the correct Constructor as explained in the teaching materials!
		//      You should use the the two arrays from the CardDetails class to determine the values and related card names. 
		
		
	
	}
	//For Difficulty #2 you should create a new method here which overloads createDeckOfCards(). This method will create SuitedCards,
	//instead of NumberCards. There are more details in the coursework specification about how to do this.
	//MAKE SURE YOU DIFFICULTY #1 IS FULLY WORKING BEFORE ATTEMPTING THIS!
	private static void createDeckOfCards(int numberOfSuits) {
		drawDeck = new ArrayList<>();
		String[] suits = CardsDetails.suitNames;
		int maxSuits = Math.min(numberOfSuits, 4);
	    
	    for (int suitIndex = 0; suitIndex < maxSuits; suitIndex++) {
            String suit = suits[suitIndex];

            // number cards
            for (int value = 1; value <= 6; value++) {
                drawDeck.add(new SuitedCard(value, CardsDetails.cardsNames[value - 1], suit));
            }

            // face cards
            for (int i = 10; i <= 12; i++) {
                drawDeck.add(new SuitedCard(-99, CardsDetails.cardsNames[i], suit));
            }
        }


	}
	
}

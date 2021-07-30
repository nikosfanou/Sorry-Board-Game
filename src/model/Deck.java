package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import model.card.Card;
import model.card.NumberElevenCard;
import model.card.NumberFourCard;
import model.card.NumberOneCard;
import model.card.NumberSevenCard;
import model.card.NumberTenCard;
import model.card.NumberTwoCard;
import model.card.SimpleNumberCard;
import model.card.SorryCard;
import model.square.EndSlideSquare;
import model.square.HomeSquare;
import model.square.InternalSlideSquare;
import model.square.SafetyZoneSquare;
import model.square.SimpleSquare;
import model.square.Square;
import model.square.StartSlideSquare;
import model.square.StartSquare;

public class Deck {

	/**
	 * Contains all the cards of the game, four times each card.
	 */

	private ArrayList<Card> packOfCards;

	/**
	 * Contains all the squares of the board.
	 */
	private ArrayList<Square> squaresOfBoard;

	/**
	 * The latest card played. If the latest card played was card with number 2 then
	 * the turn doesn't change.
	 */

	private Card latestCardPlayed;

	/**
	 * Represents the amount of the cards that haven't been played yet.
	 */

	private int cardsLeft;

	/**
	 * Returns the cards of the deck.
	 * 
	 * @pre Cards where given to the cards list.
	 * @post Returns the cards of the deck.
	 * @return The cards of the deck.
	 */

	public ArrayList<Card> getPackOfCards() {
		return packOfCards;
	}

	/**
	 * Returns the squares of the board.
	 * 
	 * @pre Squares where given to the squares of board list .
	 * @post Returns the squares of the board.
	 * @return The squares of the board.
	 */

	public ArrayList<Square> getBoard() {
		return squaresOfBoard;
	}

	/**
	 * Initializes the board with the squares, the deck with the cards, and the two
	 * players with their two pawns each.
	 * 
	 * @pre
	 * @post Initializes the board with the squares, the deck with the cards, and
	 *       the two players with their two pawns each.
	 */

	public void initialize() {
		packOfCards = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			Card card = new NumberOneCard();
			card.setImage("images/images/cards/card1.2.png");
			packOfCards.add(card);
			card = new NumberTwoCard();
			card.setImage("images/images/cards/card2.2.png");
			packOfCards.add(card);
			card = new SimpleNumberCard(3);
			card.setImage("images/images/cards/card3.2.png");
			packOfCards.add(card);
			card = new NumberFourCard();
			card.setImage("images/images/cards/card4.2.png");
			packOfCards.add(card);
			card = new SimpleNumberCard(5);
			card.setImage("images/images/cards/card5.2.png");
			packOfCards.add(card);
			card = new NumberSevenCard();
			card.setImage("images/images/cards/card7.2.png");
			packOfCards.add(card);
			card = new SimpleNumberCard(8);
			card.setImage("images/images/cards/card8.2.png");
			packOfCards.add(card);
			card = new NumberTenCard();
			card.setImage("images/images/cards/card10.2.png");
			packOfCards.add(card);
			card = new NumberElevenCard();
			card.setImage("images/images/cards/card11.2.png");
			packOfCards.add(card);
			card = new SimpleNumberCard(12);
			card.setImage("images/images/cards/card12.2.png");
			packOfCards.add(card);
			card = new SorryCard();
			card.setImage("images/images/cards/cardSorry.2.png");
			packOfCards.add(card);
		}
		cardsLeft = 44;
		reshuffling();
		squaresOfBoard = new ArrayList<Square>();
		int givenPosition = 0;
		for (int i = 0; i < 4; i++) {
			Color color;
			if (i == 0) {
				color = Color.RED;
			} else if (i == 1) {
				color = Color.BLUE;
			} else if (i == 2) {
				color = Color.YELLOW;
			} else {
				color = Color.GREEN;
			}
			squaresOfBoard.add(new SimpleSquare(givenPosition, Color.WHITE));
			givenPosition++;
			squaresOfBoard.add(new StartSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new InternalSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new InternalSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new EndSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new SimpleSquare(givenPosition, Color.WHITE));
			givenPosition++;
			squaresOfBoard.add(new SimpleSquare(givenPosition, Color.WHITE));
			givenPosition++;
			squaresOfBoard.add(new SimpleSquare(givenPosition, Color.WHITE));
			givenPosition++;
			squaresOfBoard.add(new SimpleSquare(givenPosition, Color.WHITE));
			givenPosition++;
			squaresOfBoard.add(new StartSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new InternalSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new InternalSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new InternalSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new EndSlideSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new SimpleSquare(givenPosition, Color.WHITE));
			givenPosition++;
		}
		
		for (int i = 0; i < 2; i++) {
			Color color;
			if (i == 0) {
				color = Color.RED;
			} else {
				color = Color.YELLOW;
			}
			squaresOfBoard.add(new SafetyZoneSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new SafetyZoneSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new SafetyZoneSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new SafetyZoneSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new SafetyZoneSquare(givenPosition, color));
			givenPosition++;
			squaresOfBoard.add(new HomeSquare(givenPosition, color));
			givenPosition++;
		}
		squaresOfBoard.add(new StartSquare(givenPosition, Color.RED));
		givenPosition++;
		squaresOfBoard.add(new StartSquare(givenPosition, Color.YELLOW));

	}

	/**
	 * Chooses a card from the cards list and saves it to latestCardPlayed variable.
	 * If zero cards are left it calls the reshuffling method.
	 * 
	 * @pre Able to get a card and the card selected hadn't been played before.
	 * @post Gets a card and updates the variable latestCardPlayed with that card.
	 * 
	 */

	public void getCard() {
		if (cardsLeft == 0) {
			reshuffling();
		}
		latestCardPlayed = packOfCards.get(cardsLeft - 1);
		cardsLeft--;

	}

	/**
	 * Returns the latest card played.
	 * 
	 * @pre At least one card is played.
	 * @post Returns the latest card played.
	 * @return The latest card played.
	 */

	public Card getLatestCard() {
		return latestCardPlayed;
	}

	/**
	 * Returns the amount of cards that haven't been played yet.
	 * 
	 * @pre
	 * @post Returns the amount of cards that haven't been played yet.
	 * @return the amount of cards that haven't been played yet.
	 */

	public int getCardsLeft() {
		return cardsLeft;
	}

	/**
	 * Makes all the cards able to be played again.
	 * 
	 * @pre All the card have been played.
	 * @post All the cards are able to be played again.
	 * 
	 */

	public void reshuffling() {
		while (cardsLeft != 44) {
			packOfCards.get(cardsLeft).putBackCard();
			cardsLeft++;
		}
		Collections.shuffle(packOfCards);
	}

}

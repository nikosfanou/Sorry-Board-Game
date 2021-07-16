package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;

public abstract class Card {

	/**
	 * Let us know if the card is played (isPlayed==true) or not.
	 */

	private boolean isPlayed = false;

	/**
	 * The path of the image of the card.
	 */
	private String image;

	/**
	 * Returns a string describing the path of the image.
	 * 
	 * @pre
	 * @post Returns a string describing the path of the image.
	 * @return A string describing the path of the image.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the path of the image of the card.
	 * 
	 * @pre Parameter is valid.
	 * @post Sets the path of the image of the card.
	 * @param image The image we want to give to the card.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Set card as played ,so it won't be able to be played again until the next
	 * reshuffling.
	 * 
	 * @pre The card is played.
	 * @post Set card as played after the move of the pawn ,so it won't be able to
	 *       be played again.
	 */

	public void DropCard() {
		isPlayed = true;
	}

	/**
	 * Puts the card back in order to be played again.
	 * 
	 * @pre All cards are played.
	 * @post Puts the card back in order to be played again.
	 */

	public void putBackCard() {
		isPlayed = false;
	}

	/**
	 * Returns if the card is already played or not.
	 * 
	 * @pre
	 * @post Returns true if the card is already played ,else it returns false.
	 * @return True if the card is already played ,else it returns false.
	 */

	public boolean IsPlayed() {
		return isPlayed;
	}

	/**
	 * Informs about the attributes of the card.
	 * 
	 * @pre
	 * @post Returns a string that describes the attributes of the card.
	 * @return A string that describes the attributes of the card.
	 */

	public abstract String toString();

	/**
	 * Moves the pawn accordingly to the card played. If the pawn moved to a square
	 * where a pawn of different color is located, it sends the different color pawn
	 * to its start square. Also, if the pawn moved to a StartSlideSquare with
	 * different color then it slides to the EndSlideSquare of this color and sends
	 * pawns which are in this whole slide square to their start squares.
	 * 
	 * @pre ableToMove function returned true/move is valid.
	 * @post Moves the pawn accordingly to the card played.
	 * @param pawn  The pawn that will be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 */

	public abstract void movePawn(Pawn pawn, Deck table);

	/**
	 * Checks if the move is valid accordingly to the card.
	 * 
	 * @pre Parameters are not null.
	 * @post Returns true if the move is valid, else false.
	 * @param pawn  The pawn we want to check if can be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 * @return True if the move is valid, else false.
	 */

	public abstract boolean ableToMove(Pawn pawn, Deck table);

	/**
	 * Sends the pawn to its start square.
	 * 
	 * @pre
	 * @post Sends the pawn to its start square.
	 * @param pawn  the pawn we send to its start square.
	 * @param table the board.
	 */
	public void sendStart(Pawn pawn, Deck table) {
		int tmpPosition = pawn.getPosition();
		table.getBoard().get(pawn.getPosition()).getPawnOn().setDisable();
		if (pawn.getColor() == Color.RED) {
			table.getBoard().get(pawn.getPosition()).getPawnOn().setPosition(72);
		} else {
			table.getBoard().get(pawn.getPosition()).getPawnOn().setPosition(73);
		}
		table.getBoard().get(tmpPosition).setPawnOn(null);
		return;
	}
}

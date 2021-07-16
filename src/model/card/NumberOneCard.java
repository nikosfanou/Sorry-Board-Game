package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;

public class NumberOneCard extends NumberCard {

	/**
	 * Constructor. Method to create an object of type NumberOneCard.
	 * 
	 * @pre
	 * @post Creates an object of type NumberOneCard and gives to it the number one.
	 */

	public NumberOneCard() {
		setNumber(1);
	}

	public String toString() {
		return "Card's number: " + getNumber() + "\n"
				+ "Attributes: With this card player can move one of his pawns out of its start "
				+ "square or 1 square front.\n";
	}

	/**
	 * Pawn can start from the start square or move one square front. If the pawn
	 * moved to a square where a pawn of different color is located, it sends the
	 * different color pawn to its start square. Also, if the pawn moved to a
	 * StartSlideSquare with different color then it slides to the EndSlideSquare of
	 * this color and sends pawns which are in this whole slide square to their
	 * start squares.
	 * 
	 * @pre ableToMove function returned true/move is valid.
	 * @post Moves the pawn 1 square front or moves it out of its start square.
	 * @param pawn  The pawn that will be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 */

	public void movePawn(Pawn pawn, Deck table) {
		if (pawn.getPosition() == 72) {
			if (table.getBoard().get(4).isPawnOn() == true) {
				// stelnei to kitrino pawn sto start square tou
				sendStart(table.getBoard().get(4).getPawnOn(), table);
			}
			pawn.setPosition(4);
			pawn.setEnable();
			table.getBoard().get(4).setPawnOn(pawn);
		} else if (pawn.getPosition() == 73) {
			if (table.getBoard().get(34).isPawnOn() == true) {
				// stelnei to kokkino pawn sto start square tou
				sendStart(table.getBoard().get(34).getPawnOn(), table);
			}
			pawn.setPosition(34);
			pawn.setEnable();
			table.getBoard().get(34).setPawnOn(pawn);
		} else {
			super.movePawn(pawn, table);
		}
		return;
	}

	/**
	 * Checks if the move is valid. Pawn can start from its start square but must
	 * not move to opponent's home, safety zone or to a square where a pawn of the
	 * same colour is.
	 * 
	 * @pre Parameters are not null.
	 * @post Returns true if move is valid.
	 * @param pawn  The pawn we want to check if can be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 * @return True if move is valid.
	 */

	public boolean ableToMove(Pawn pawn, Deck table) {
		if (pawn.getPosition() == 72) {
			if (table.getBoard().get(4).isPawnOn() == true) {
				if (table.getBoard().get(4).getPawnOn().getColor() == Color.RED) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else if (pawn.getPosition() == 73) {
			if (table.getBoard().get(34).isPawnOn() == true) {
				if (table.getBoard().get(34).getPawnOn().getColor() == Color.YELLOW) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return super.ableToMove(pawn, table);
	}

}

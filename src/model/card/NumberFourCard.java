package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.square.StartSlideSquare;
import model.square.StartSquare;

public class NumberFourCard extends NumberCard {

	/**
	 * Constructor. Method to create an object of type NumberFourCard.
	 * 
	 * @pre
	 * @post Creates an object of type NumberFourCard and gives to it the number
	 *       four.
	 */

	public NumberFourCard() {
		setNumber(4);
	}

	/**
	 * Reduces pawns position by 4. If the pawn moved to a square where a pawn of
	 * different color is located, it sends the different color pawn to its start
	 * square. Also, if the pawn moved to a StartSlideSquare with different color
	 * then it slides to the EndSlideSquare of this color and sends pawns which are
	 * in this whole slide square to their start squares.
	 * 
	 * @pre ableToMove function returned true/move is valid.
	 * @post Moves the pawn four squares back.
	 * @param pawn  The pawn that will be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 */

	public void movePawn(Pawn pawn, Deck table) {
		table.getBoard().get(pawn.getPosition()).setPawnOn(null);
		// an einai se safety zone
		if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) {
			if (pawn.getPosition() == 60) {
				pawn.isEnabled();
				pawn.setPosition(59);
			} else if (pawn.getPosition() == 64) {
				pawn.setPosition(pawn.getPosition() - getNumber());
			} else {
				pawn.isEnabled();
				pawn.setPosition(pawn.getPosition() - getNumber() - 57);
			}
			if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
				sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}

		if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) {
			if (pawn.getPosition() == 70) {
				pawn.setPosition(pawn.getPosition() - getNumber());
			} else {
				pawn.isEnabled();
				pawn.setPosition(pawn.getPosition() - getNumber() - 33);
			}
			if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
				sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}
		// se apla tetragwna me thesi 0 1 2 h 3
		if (pawn.getPosition() <= 3) {
			pawn.setPosition(pawn.getPosition() - getNumber() + 60);
			if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
				sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}
		// slides
		if (pawn.getColor() == Color.RED) {
			if (pawn.getPosition() == 28 || pawn.getPosition() == 43 || pawn.getPosition() == 58) {
				for (int i = 1; i <= 4; i++) {
					if (table.getBoard().get(pawn.getPosition() - i).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition() - i).getPawnOn(), table);
					}
				}
			} else { // sta ypoloipa tetragwna
				pawn.setPosition(pawn.getPosition() - getNumber());
				if (table.getBoard().get(pawn.getPosition()) instanceof StartSlideSquare
						&& table.getBoard().get(pawn.getPosition()).getColor() != Color.RED) { // tote tha pesei sti
																								// mikri skala slide
					for (int i = 0; i <= 3; i++) {
						if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
							sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
						}
					}
					pawn.setPosition(pawn.getPosition() + 3);
				} else {
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
				}
			}
		} else { // an einai kitrino
			if (pawn.getPosition() == 28 || pawn.getPosition() == 13 || pawn.getPosition() == 58) {
				for (int i = 1; i <= 4; i++) {
					if (table.getBoard().get(pawn.getPosition() - i).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition() - i).getPawnOn(), table);
					}
				}
			} else { // sta ypoloipa tetragwna
				pawn.setPosition(pawn.getPosition() - getNumber());
				if (table.getBoard().get(pawn.getPosition()) instanceof StartSlideSquare
						&& table.getBoard().get(pawn.getPosition()).getColor() != Color.YELLOW) { // tote tha pesei sti
																									// mikri skala slide
					for (int i = 0; i <= 3; i++) {
						if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
							sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
						}
					}
					pawn.setPosition(pawn.getPosition() + 3);
				} else {
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
				}
			}
		}
		table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
		return;
	}

	/**
	 * Checks if the pawn can move four squares back. Pawn must not move if it is
	 * located at its start square and also can't move to opponent's home, safety
	 * zone or to a square where a pawn of the same colour is.
	 * 
	 * @pre Parameters are not null.
	 * @post Returns true if the move can be done, else false.
	 * @param pawn  The pawn we want to check if can be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 * @return True if the move can be done, else false.
	 */
	public boolean ableToMove(Pawn pawn, Deck table) {
		if (pawn.isFinished() == true) { // an exei ftasei home
			return false;
		}
		// an einai start
		if (pawn.isEnabled() == false) {
			if (table.getBoard().get(pawn.getPosition()) instanceof StartSquare) { // an einai se start square
				return false;
			}
		}
		// an einai se safety zone
		if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // gia to kokkino pawn
			if (pawn.getPosition() == 60) {
				if (table.getBoard().get(59).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(59).getPawnOn().getColor() == Color.YELLOW) {
						return true;
					} else {
						return false;
					}
				}
			} else if (pawn.getPosition() == 64) {
				if (table.getBoard().get(pawn.getPosition() - getNumber()).isPawnOn() == true) { // an iparxei tote
																									// einai kokkino ara
																									// false.
					return false;
				} else {
					return true;
				}
			} else {
				if (table.getBoard().get(pawn.getPosition() - getNumber() - 57).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(pawn.getPosition() - getNumber() - 57).getPawnOn()
							.getColor() == Color.YELLOW) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		// gia to kitrino
		if (pawn.getPosition() >= 66) {
			if (pawn.getPosition() == 70) {
				if (table.getBoard().get(pawn.getPosition() - getNumber()).isPawnOn() == true) { // an iparxei tote
																									// einai kitrino ara
																									// false.
					return false;
				} else {
					return true;
				}
			} else {
				if (table.getBoard().get(pawn.getPosition() - getNumber() - 33).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(pawn.getPosition() - getNumber() - 33).getPawnOn()
							.getColor() == Color.RED) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		// alliws an einai se aplo tetragwno
		if (pawn.getColor() == Color.RED) {
			// ta slides
			if (pawn.getPosition() == 28 || pawn.getPosition() == 43 || pawn.getPosition() == 58) {
				return true;
			} else if (pawn.getPosition() <= 3) {
				if (table.getBoard().get(pawn.getPosition() - getNumber() + 60).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(pawn.getPosition() - getNumber() + 60).getPawnOn()
							.getColor() == Color.RED) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				if (table.getBoard().get(pawn.getPosition() - getNumber()).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(pawn.getPosition() - getNumber()).getPawnOn().getColor() == Color.RED) {
						return false;
					} else {
						return true;
					}
				}
			}
		} else { // an einai kitrino
			// ta slides
			if (pawn.getPosition() == 28 || pawn.getPosition() == 13 || pawn.getPosition() == 58) {
				return true;
			} else if (pawn.getPosition() <= 3) {
				if (table.getBoard().get(pawn.getPosition() - getNumber() + 60).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(pawn.getPosition() - getNumber() + 60).getPawnOn()
							.getColor() == Color.YELLOW) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				if (table.getBoard().get(pawn.getPosition() - getNumber()).isPawnOn() == false) {
					return true;
				} else {
					if (table.getBoard().get(pawn.getPosition() - getNumber()).getPawnOn().getColor() == Color.YELLOW) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}

	public String toString() {
		return "Card's number: " + getNumber() + "\n"
				+ "Attributes: With this card player can move one of his pawns 4 squares" + "back.\n";
	}

}

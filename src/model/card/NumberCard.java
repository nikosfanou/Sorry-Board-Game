package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.square.StartSquare;

public abstract class NumberCard extends Card {

	/**
	 * Representing the number of the card.
	 */

	private int number;

	/**
	 * Sets the card with a valid number.
	 * 
	 * @pre The given number must be valid , between 1 and 12.
	 * @post Sets the card with a number.
	 * @param number The number the card will get.
	 */

	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Informs about the number of the card.
	 * 
	 * @pre A number was given.
	 * @post Returns the number of the card.
	 * @return The number of the card.
	 */

	public int getNumber() {
		return number;
	}

	/**
	 * Moves the pawn front as much as the number of the card. If the pawn moved to
	 * a square where a pawn of different color is located, it sends the different
	 * color pawn to its start square. Also, if the pawn moved to a StartSlideSquare
	 * with different color then it slides to the EndSlideSquare of this color and
	 * sends pawns which are in this whole slide square to their start squares.
	 * 
	 * @pre ableToMove function returned true/move is valid.
	 * @post Moves the pawn front as much as the number of the card.
	 * @param pawn  The pawn that will be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 */

	public void movePawn(Pawn pawn, Deck table) {
		// in safety zone
		if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // red pawn
			table.getBoard().get(pawn.getPosition()).setPawnOn(null);
			if (pawn.getPosition() + number == 65) {
				pawn.setPosition(65);
				pawn.setFinished();
			} else {
				pawn.setPosition(pawn.getPosition() + number);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}
		if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) { // yellow pawn
			table.getBoard().get(pawn.getPosition()).setPawnOn(null);
			if (pawn.getPosition() + number == 71) {
				pawn.setPosition(71);
				pawn.setFinished();
			} else {
				pawn.setPosition(pawn.getPosition() + number);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}
		// else if it isn't located on a start home or safety zone square.
		table.getBoard().get(pawn.getPosition()).setPawnOn(null);
		if (pawn.getColor() == Color.RED) {
			if (pawn.getPosition() <= 2) {
				if (pawn.getPosition() + number <= 2) {
					pawn.setPosition(pawn.getPosition() + number);
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) { // if there is a pawn on, it is different colour
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
				} else {
					if (pawn.getPosition() + number == 8) {
						pawn.setPosition(65);
						pawn.setFinished();
					} else {
						pawn.setPosition(pawn.getPosition() + number + 57);
					}
					pawn.setDisable();
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + number <= 59) {
					pawn.setPosition(pawn.getPosition() + number);
					// not slides
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
					// slides
					if (pawn.getPosition() == 16 || pawn.getPosition() == 31 || pawn.getPosition() == 46) {
						for (int i = 1; i <= 3; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 3);
					} else if (pawn.getPosition() == 24 || pawn.getPosition() == 39 || pawn.getPosition() == 54) {
						for (int i = 1; i <= 4; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 4);
					}
				} else if (pawn.getPosition() + number < 63) {
					pawn.setPosition(pawn.getPosition() + number - 60);
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
				} else {
					pawn.setPosition(pawn.getPosition() + number - 3);
					pawn.setDisable();
					if (pawn.getPosition() == 65) {
						pawn.setFinished();
					}
				}
			}
		} else { // yellow
			if (pawn.getPosition() <= 32) {
				if (pawn.getPosition() + number <= 32) {
					pawn.setPosition(pawn.getPosition() + number);
					// not slides
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
					// slides
					if (pawn.getPosition() == 1 || pawn.getPosition() == 16) {
						for (int i = 1; i <= 3; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 3);
					} else if (pawn.getPosition() == 9 || pawn.getPosition() == 24) {
						for (int i = 1; i <= 4; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 4);
					}
				} else {
					if (pawn.getPosition() + number == 38) {
						pawn.setPosition(71);
						pawn.setFinished();
					} else {
						pawn.setPosition(pawn.getPosition() + number + 33);
					}
					pawn.setDisable();
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + number <= 59) {
					pawn.setPosition(pawn.getPosition() + number);
					// not slides
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
					// slides
					if (pawn.getPosition() == 46) {
						for (int i = 1; i <= 3; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 3);
					} else if (pawn.getPosition() == 54) {
						for (int i = 1; i <= 4; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 4);
					}
				} else {
					pawn.setPosition(pawn.getPosition() + number - 60);
					// not slides
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
					// slides
					if (pawn.getPosition() == 1) {
						for (int i = 1; i <= 3; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 3);
					} else if (pawn.getPosition() == 9) {
						for (int i = 1; i <= 4; i++) {
							if (table.getBoard().get(pawn.getPosition() + i).isPawnOn() == true) {
								sendStart(table.getBoard().get(pawn.getPosition() + i).getPawnOn(), table);
							}
						}
						pawn.setPosition(pawn.getPosition() + 4);
					}
				}
			}
		}
		table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
		return;
	}

	/**
	 * Checks if the move is valid. Pawn must not move if it is located at its start
	 * square and also can't move to opponent's home, safety zone or to a square
	 * where a pawn of the same color is.
	 * 
	 * @pre Parameters are not null.
	 * @post Returns true if the move can be done, else false.
	 * @param pawn  The pawn we want to check if can be moved.
	 * @param table The table/ squares where the pawn is moving on.
	 * @return True if the move can be done, else false.
	 */
	public boolean ableToMove(Pawn pawn, Deck table) {
		if (pawn.isFinished() == true) { // if reach home
			return false;
		}
		if (pawn.isEnabled() == false) {
			if (table.getBoard().get(pawn.getPosition()) instanceof StartSquare) { // an einai se start square
				return false;
			}
			// on safety zone
			if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // red pawn
				if ((pawn.getPosition() + number) <= 65) {
					if (pawn.getPosition() + number == 65) {
						return true;
					}
					if (table.getBoard().get(pawn.getPosition() + number).isPawnOn() == true) { // if there is a pawn on, it has the same colour
						return false;
					}
					return true;
				} else {
					return false;
				}
			}
			if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) { // yellow pawn
				if ((pawn.getPosition() + number) <= 71) {
					if (pawn.getPosition() + number == 71) {
						return true;
					}
					if (table.getBoard().get(pawn.getPosition() + number).isPawnOn() == true) {
						return false;
					}
					return true;
				} else {
					return false;
				}
			}
		}
		// else if it isn't located on a start home or safety zone square.
		int possiblePosition = 0;
		if (pawn.getColor() == Color.RED) {
			if (pawn.getPosition() <= 2) {
				if (pawn.getPosition() + number <= 2) {
					possiblePosition = pawn.getPosition() + number;
				} else if (pawn.getPosition() + number <= 8) {
					if (pawn.getPosition() + number == 8) {
						return true;
					}
					possiblePosition = pawn.getPosition() + number + 57;
				} else {
					return false;
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + number <= 59) {
					possiblePosition = pawn.getPosition() + number;
				} else if (pawn.getPosition() + number < 63) {
					possiblePosition = pawn.getPosition() + number - 60;
				} else if (pawn.getPosition() + number <= 68) {
					if (pawn.getPosition() + number == 68) {
						return true;
					}
					possiblePosition = pawn.getPosition() + number - 3;
				} else {
					return false;
				}
			}
			if (table.getBoard().get(possiblePosition).isPawnOn() == true) {
				if (table.getBoard().get(possiblePosition).getPawnOn().getColor() == Color.RED) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else { // yellow
			if (pawn.getPosition() <= 32) {
				if (pawn.getPosition() + number <= 32) {
					possiblePosition = pawn.getPosition() + number;
				} else if (pawn.getPosition() + number <= 38) {
					if (pawn.getPosition() + number == 38) {
						return true;
					}
					possiblePosition = pawn.getPosition() + number + 33;
				} else {
					return false;
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + number <= 59) {
					possiblePosition = pawn.getPosition() + number;
				} else {
					possiblePosition = pawn.getPosition() + number - 60;
				}
			}
			if (table.getBoard().get(possiblePosition).isPawnOn() == true) {
				if (table.getBoard().get(possiblePosition).getPawnOn().getColor() == Color.YELLOW) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
	}

}

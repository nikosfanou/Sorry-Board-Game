package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.square.StartSquare;

public class NumberSevenCard extends NumberCard {
	/**
	 * Constructor. Method to create an object of type NumberSevenCard.
	 * 
	 * @pre
	 * @post Creates an object of type NumberSevenCard and gives to it the number
	 *       seven.
	 */

	public NumberSevenCard() {
		setNumber(7);
	}

	public String toString() {
		return "Card's number: " + getNumber() + "\n"
				+ "Attributes: With this card player can move one of his pawns 7 squares front"
				+ "or move both of his pawns 7 squares front in total.\n ";
	}

	/**
	 * Moves player's pawns 7 squares front in total. If a pawn moved to a square
	 * where a pawn of different color is located, it sends the different color pawn
	 * to its start square. Also, if the pawn moved to a StartSlideSquare with
	 * different color then it slides to the EndSlideSquare of this color and sends
	 * pawns which are in this whole slide square to their start squares.
	 * 
	 * @pre ableToMove function returned true/move is valid.
	 * @post Moves front player's pawn1 by increasePosition1 and pawn2 by
	 *       increasePosition2.
	 * @param pawn1             The first pawn that will be moved.
	 * @param increasePosition1 How much the first pawn will be moved.
	 * @param pawn2             The second pawn that will be moved.
	 * @param increasePosition2 How much the second pawn will be moved.
	 * @param table             The table/ squares where the pawn is moving on.
	 */

	public void movePawn(Pawn pawn1, int increasePosition1, Pawn pawn2, int increasePosition2, Deck table) {
		movePawn(pawn1, increasePosition1, table);
		movePawn(pawn2, increasePosition2, table);

	}

	/**
	 * Moves pawn front by increasePosition. If the pawn moved to a square where a
	 * pawn of different color is located, it sends the different color pawn to its
	 * start square. Also, if the pawn moved to a StartSlideSquare with different
	 * color then it slides to the EndSlideSquare of this color and sends pawns
	 * which are in this whole slide square to their start squares.
	 * 
	 * @pre ableToMove function returned true/move is valid.
	 * @post Moves front pawn by increasePosition.
	 * @param pawn             The pawn that will be moved.
	 * @param increasePosition How much the pawn will be moved.
	 * @param table            The table/ squares where the pawn is moving on.
	 */

	public void movePawn(Pawn pawn, int increasePosition, Deck table) {
		// on safety zone
		if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // red pawn
			table.getBoard().get(pawn.getPosition()).setPawnOn(null);
			if (pawn.getPosition() + increasePosition == 65) {
				pawn.setPosition(65);
				pawn.setFinished();
			} else {
				pawn.setPosition(pawn.getPosition() + increasePosition);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}
		if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) { // yellow pawn
			table.getBoard().get(pawn.getPosition()).setPawnOn(null);
			if (pawn.getPosition() + increasePosition == 71) {
				pawn.setPosition(71);
				pawn.setFinished();
			} else {
				pawn.setPosition(pawn.getPosition() + increasePosition);
			}
			table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			return;
		}
		// else if it isn't located on a start home or safety zone square.
		table.getBoard().get(pawn.getPosition()).setPawnOn(null);
		if (pawn.getColor() == Color.RED) {
			if (pawn.getPosition() <= 2) {
				if (pawn.getPosition() + increasePosition <= 2) {
					pawn.setPosition(pawn.getPosition() + increasePosition);
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) { // if exists it will be different color
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
				} else {
					if (pawn.getPosition() + increasePosition == 8) {
						pawn.setPosition(65);
						pawn.setFinished();
					} else {
						pawn.setPosition(pawn.getPosition() + increasePosition + 57);
					}
					pawn.setDisable();
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + increasePosition <= 59) {
					pawn.setPosition(pawn.getPosition() + increasePosition);
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
				} else if (pawn.getPosition() + increasePosition < 63) {
					pawn.setPosition(pawn.getPosition() + increasePosition - 60);
					if (table.getBoard().get(pawn.getPosition()).isPawnOn() == true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(), table);
					}
				} else {
					pawn.setPosition(pawn.getPosition() + increasePosition - 3);
					pawn.setDisable();
					if (pawn.getPosition() == 65) {
						pawn.setFinished();
					}
				}
			}
		} else { // yellow pawn
			if (pawn.getPosition() <= 32) {
				if (pawn.getPosition() + increasePosition <= 32) {
					pawn.setPosition(pawn.getPosition() + increasePosition);
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
					if (pawn.getPosition() + increasePosition == 38) {
						pawn.setPosition(71);
						pawn.setFinished();
					} else {
						pawn.setPosition(pawn.getPosition() + increasePosition + 33);
					}
					pawn.setDisable();
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + increasePosition <= 59) {
					pawn.setPosition(pawn.getPosition() + increasePosition);
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
					pawn.setPosition(pawn.getPosition() + increasePosition - 60);
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
	 * Checks if player's pawn1 can move front by increasePosition1 and pawn2 by
	 * increasePosition2 by calling the function ableToMove(Pawn pawn,int
	 * increasePosition,Deck table) for each pawn.This move is invalid when at least
	 * one of the pawns is located at its start square or at least one of the
	 * squares where the pawns would move has already a same colour pawn on it. Also
	 * pawns can't move to opponent's home and safety zone.
	 * 
	 * @pre Parameters not null.
	 * @post Returns true if the move is valid,else false.
	 * @param pawn1             The first pawn we want to check if could be moved.
	 * @param increasePosition1 How much the first pawn could be moved.
	 * @param pawn2             The second pawn we want to check if could be moved.
	 * @param increasePosition2 How much the second pawn could be moved.
	 * @param table             The table/ squares where the pawn is moving on.
	 * @return True if the move is valid,else false.
	 */

	public boolean ableToMove(Pawn pawn1, int increasePosition1, Pawn pawn2, int increasePosition2, Deck table) {
		// check if the two pawns try to go to the same square

		if (pawn1.isFinished() == true || pawn2.isFinished() == true) { // reached home
			return false;
		}

		if (table.getBoard().get(pawn1.getPosition()) instanceof StartSquare
				|| table.getBoard().get(pawn2.getPosition()) instanceof StartSquare) { // on start square
			return false;
		}

		int possiblePosition1 = -1, possiblePosition2 = -1;
		if (pawn1.getColor() == Color.RED) { // if pawn1 is red then pawn2 is red too
			// pawn 1
			if (pawn1.getPosition() <= 2) {
				if (pawn1.getPosition() + increasePosition1 <= 2) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1;
				} else if (pawn1.getPosition() + increasePosition1 <= 8) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1 + 57;
				} else {
					possiblePosition1 = -1;
				}
			} else if (pawn1.getPosition() <= 59) {
				if (pawn1.getPosition() + increasePosition1 <= 59) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1;
				} else if (pawn1.getPosition() + increasePosition1 <= 62) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1 - 60;
				} else if (pawn1.getPosition() + increasePosition1 <= 65) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1 - 3;
				} else {
					possiblePosition1 = -1;
				}
			} else if (pawn1.getPosition() <= 64) {
				if (pawn1.getPosition() + increasePosition1 <= 65) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1;
				} else {
					possiblePosition1 = -1;
				}
			}

			// pawn 2
			if (pawn2.getPosition() <= 2) {
				if (pawn2.getPosition() + increasePosition2 <= 2) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2;
				} else if (pawn2.getPosition() + increasePosition2 <= 8) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2 + 57;
				} else {
					possiblePosition2 = -1;
				}
			} else if (pawn2.getPosition() <= 59) {
				if (pawn2.getPosition() + increasePosition2 <= 59) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2;
				} else if (pawn2.getPosition() + increasePosition2 <= 62) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2 - 60;
				} else if (pawn2.getPosition() + increasePosition2 <= 65) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2 - 3;
				} else {
					possiblePosition2 = -1;
				}
			} else if (pawn2.getPosition() <= 64) {
				if (pawn2.getPosition() + increasePosition2 <= 65) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2;
				} else {
					possiblePosition2 = -1;
				}
			}
		}

		if (pawn1.getColor() == Color.YELLOW) { // if pawn1 is yellow then pawn2 is yellow too
			// pawn 1
			if (pawn1.getPosition() <= 32) {
				if (pawn1.getPosition() + increasePosition1 <= 32) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1;
				} else if (pawn1.getPosition() + increasePosition1 <= 38) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1 + 33;
				} else {
					possiblePosition1 = -1;
				}
			} else if (pawn1.getPosition() <= 59) {
				if (pawn1.getPosition() + increasePosition1 <= 59) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1;
				} else {
					possiblePosition1 = pawn1.getPosition() + increasePosition1 - 60;
				}
			} else if (pawn1.getPosition() <= 70) {
				if (pawn1.getPosition() + increasePosition1 <= 71) {
					possiblePosition1 = pawn1.getPosition() + increasePosition1;
				} else {
					possiblePosition1 = -1;
				}
			}

			// pawn 2
			if (pawn2.getPosition() <= 32) {
				if (pawn2.getPosition() + increasePosition2 <= 32) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2;
				} else if (pawn2.getPosition() + increasePosition2 <= 38) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2 + 33;
				} else {
					possiblePosition2 = -1;
				}
			} else if (pawn2.getPosition() <= 59) {
				if (pawn2.getPosition() + increasePosition2 <= 59) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2;
				} else {
					possiblePosition2 = pawn2.getPosition() + increasePosition2 - 60;
				}
			} else if (pawn2.getPosition() <= 70) {
				if (pawn2.getPosition() + increasePosition2 <= 71) {
					possiblePosition2 = pawn2.getPosition() + increasePosition2;
				} else {
					possiblePosition2 = -1;
				}
			}
		}

		if (possiblePosition1 == -1 || possiblePosition2 == -1
				|| (possiblePosition1 == possiblePosition2 && (possiblePosition1 != 65 && possiblePosition1 != 71))) {
			return false;
		}
		if (ableToMove(pawn1, increasePosition1, table) == true
				&& ableToMove(pawn2, increasePosition2, table) == true) {
			return true;
		}
		return false;

	}

	/**
	 * Checks if pawn can move front by increasePosition .This move is invalid when
	 * the pawn is located at its start square or the square where the pawn would
	 * move has already a same colour pawn on it. Also pawn can't move to opponent's
	 * home and safety zone.
	 * 
	 * @pre Parameters not null.
	 * @post Returns true if the move is valid,else false.
	 * @param pawn             The pawn we want to check if could be moved.
	 * @param increasePosition How much the pawn could be moved.
	 * @param table            The table/ squares where the pawn is moving on.
	 * @return True if the move is valid,else false.
	 */

	public boolean ableToMove(Pawn pawn, int increasePosition, Deck table) {
		if (pawn.isEnabled() == false) {
			// on safety zone
			if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // red pawn
				if ((pawn.getPosition() + increasePosition) <= 65) {
					if (pawn.getPosition() + increasePosition == 65) {
						return true;
					}
					if (table.getBoard().get(pawn.getPosition() + increasePosition).isPawnOn() == true) {
						return false;
					}
					return true;
				} else {
					return false;
				}
			}
			if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) { // yellow pawn
				if ((pawn.getPosition() + increasePosition) <= 71) {
					if (pawn.getPosition() + increasePosition == 71) {
						return true;
					}
					if (table.getBoard().get(pawn.getPosition() + increasePosition).isPawnOn() == true) {
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
				if (pawn.getPosition() + increasePosition <= 2) {
					possiblePosition = pawn.getPosition() + increasePosition;
				} else if (pawn.getPosition() + increasePosition <= 8) {
					if (pawn.getPosition() + increasePosition == 8) {
						return true;
					}
					possiblePosition = pawn.getPosition() + increasePosition + 57;
				} else {
					return false;
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + increasePosition <= 59) {
					possiblePosition = pawn.getPosition() + increasePosition;
				} else if (pawn.getPosition() + increasePosition < 63) {
					possiblePosition = pawn.getPosition() + increasePosition - 60;
				} else if (pawn.getPosition() + increasePosition <= 68) {
					if (pawn.getPosition() + increasePosition == 68) {
						return true;
					}
					possiblePosition = pawn.getPosition() + increasePosition - 3;
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
		} else { // yellow pawn
			if (pawn.getPosition() <= 32) {
				if (pawn.getPosition() + increasePosition <= 32) {
					possiblePosition = pawn.getPosition() + increasePosition;
				} else if (pawn.getPosition() + increasePosition <= 38) {
					if (pawn.getPosition() + increasePosition == 38) {
						return true;
					}
					possiblePosition = pawn.getPosition() + increasePosition + 33;
				} else {
					return false;
				}
			} else if (pawn.getPosition() <= 59) {
				if (pawn.getPosition() + increasePosition <= 59) {
					possiblePosition = pawn.getPosition() + increasePosition;
				} else {
					possiblePosition = pawn.getPosition() + increasePosition - 60;
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

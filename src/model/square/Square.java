package model.square;

import java.awt.Color;

import model.Pawn;

public abstract class Square {

	/**
	 * The pawn on this square.
	 */
	private Pawn pawnOn;

	/**
	 * Informs about the position of the square on the deck.
	 */
	private int position;

	/**
	 * Informs about the color of the square.
	 */
	private Color color;

	/**
	 * Sets a position for the square.
	 * 
	 * @pre A valid position is given.
	 * @post Sets a position for the square.
	 * @param position The position that the square will have on the deck.
	 */

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Sets a color for the square.
	 * 
	 * @pre A color is given.
	 * @post Sets a color for the square.
	 * @param color The color that the square will have.
	 */

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Sets a pawn on the square.
	 * 
	 * @pre
	 * @post Sets a pawn on the square.
	 */
	public void setPawnOn(Pawn pawn) {
		this.pawnOn = pawn;
	}

	/**
	 * Informs about the position of the square.
	 * 
	 * @pre A position is given.
	 * @post Returns the position of the square.
	 * @return The position of the square.
	 */

	public int getPosition() {
		return position;
	}

	/**
	 * Informs about the color of the square.
	 * 
	 * @pre A color is given.
	 * @post Returns the color of the square.
	 * @return The color of the square.
	 */

	public Color getColor() {
		return color;
	}

	/**
	 * Returns the pawn which is in this square.
	 * 
	 * @pre There is a pawn in this square.
	 * @post Returns the pawn which is in this square.
	 * @return the pawn which is in this square.
	 */

	public Pawn getPawnOn() {
		return pawnOn;
	}

	/**
	 * Returns true if a pawn exists in this square,else false.
	 * 
	 * @pre
	 * @post Returns true if a pawn exists in this square,else false.
	 * @return true if a pawn exists in this square,else false.
	 */

	public boolean isPawnOn() {
		if (pawnOn == null) {
			return false;
		}
		return true;
	}

}

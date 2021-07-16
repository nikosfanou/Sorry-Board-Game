package model.square;

import java.awt.Color;

public class StartSlideSquare extends SlideSquare {

	/**
	 * Constructor. Method to create an object of type StartSlideSquare.
	 * 
	 * @pre Valid color and position are given.
	 * @param position The position the square will have.
	 * @param color    The color the square will have.
	 * @post Creates an object of type StartSlideSquare with a position and a
	 *       number.
	 */

	public StartSlideSquare(int position, Color color) {
		setPosition(position);
		setColor(color);
		super.setPawnOn(null);
	}

}

package model.square;

import java.awt.Color;

public class SafetyZoneSquare extends Square {

	/**
	 * Constructor. Method to create an object of type SafetyZoneSquare.
	 * 
	 * @pre Valid color and position are given.
	 * @param position The position the square will have.
	 * @param color    The color the square will have.
	 * @post Creates an object of type SafetyZoneSquare with a position and a
	 *       number.
	 */

	public SafetyZoneSquare(int position, Color color) {
		setPosition(position);
		setColor(color);
		super.setPawnOn(null);
	}

}

package model.square;

import java.awt.Color;

public class SimpleSquare extends Square{

	/** Constructor. 
	* Method to create an object of type SimpleSquare.
	* @pre Valid color and position are given.
	* @param position The position the square will have.
	* @param color The color the square will have.
	* @post Creates an object of type SimpleSquare with a position and a number.
	*/
	
	public SimpleSquare(int position,Color color) {
		setPosition(position);
		setColor(color);
		super.setPawnOn(null);
		}
	
}

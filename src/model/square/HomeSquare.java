package model.square;

import java.awt.Color;

import model.Pawn;

public class HomeSquare extends Square{
	
	/** Constructor. 
	* Method to create an object of type HomeSquare.
	* @pre Valid color and position are given.
	* @param position The position the square will have.
	* @param color The color the square will have.
	* @post Creates an object of type HomeSquare with a position and a number.
	*/
	
	public HomeSquare(int position,Color color) {
		setPosition(position);
		setColor(color);
		super.setPawnOn(null);
		}
	
}

package model;

import java.awt.Color;

public class Pawn {
/**
* Pawn's color.
*/
private Color color;
/**
 * Pawn's position. Helps us to move the pawn.
 */
private int position;
/**
 * When enabled is equal to false it means the pawn is located at its start or safety 
 * zone square. In other cases enabled is equal to true.
 */
private boolean enabled;
/**
 * When finished is equal to true it means the pawn is located at its home square, so it reached
 * the finish and is not able to play/move any further.
 */
private boolean finished;

/**
 * Constructor.
 * Method to create an object of type Pawn.
 * @pre Parameters should not be null.
 * @post Creates an object of type pawn and initializes its data.
 * @param color The color of the pawn.
 * @param position The starting position of the pawn.
 */
public Pawn(Color color,int position) {
	this.color=color;
	this.position=position;
	finished=false;
	enabled=false;
}

/**
 * Returns true if the pawn isn't located at its start,home or safety zone square,else returns
 * false.
 * @pre
 * @post Returns true if the pawn isn't located at its start,home or safety zone square,else returns
 * false.
 * @return True if the pawn isn't located at its start,home or safety zone square,else returns
 * false.
 */
public boolean isEnabled() {
	return enabled;
}

/**
 * Disables the pawn.
 * @pre Pawn moves at its start, home or safety zone squares.
 * @post Disables the pawn.
 */
public void setDisable() {
	this.enabled=false;
}

/**
 * Enables the pawn.
 * @pre Pawn isn't located at its start,home or safety zone squares.
 * @post Enables the pawn.
 */
public void setEnable() {
	this.enabled=true;
}

/**
 * Sets the position of the pawn.
 * @pre Valid position.
 * @post Sets the position of the pawn.
 * @param position The new position of the pawn.
 */
public void setPosition(int position) {
	this.position=position;
}

/**
 * Returns the position of the pawn.
 * @pre
 * @post Returns the position of the pawn.
 * @return Returns the position of the pawn.
 */
public int getPosition() {
	return position;
}

/**
 * Sets pawn as finished.
 * @pre Pawn reached the home square.
 * @post Sets pawn as finished.
 */
public void setFinished() {
	finished=true;
}

/**
 * Returns true if the pawn is located at its home square,else returns false.
 * @pre
 * @post Returns true if the pawn is located at its home square,else returns false.
 * @return True if the pawn is located at its home or square,else returns false.
 */
public boolean isFinished() {
	return finished;
}

/**
 * Returns the color of the pawn.
 * @pre
 * @post Returns the color of the pawn.
 * @return The color of the pawn.
 */
public Color getColor() {
	return color;
}


}

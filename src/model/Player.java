package model;

import java.awt.Color;

public class Player {
	/**
	 * Player's name.
	 */
private String name;
/**
 * Player's color which is the same with his pawns color.
 */
private Color color;
/**
 * Player's first pawn.
 */
private Pawn pawn1;
/**
 * Player's second pawn.
 */
private Pawn pawn2;
/**
 * Show if it is the turn of the player to play.
 */
private boolean turn;

/**
* Constructor
* @pre Given a name , a color and two pawns.
* @post Initialize the object of class player with the given name, color and pawns.
* @param name The name of the player.
* @param color The color of the pawns of the player.
* @param pawn1 The first pawn of the player.
* @param pawn2 The second pawn of the player.
*/

public Player(String name,Color color,Pawn pawn1, Pawn pawn2) {
	this.name=name;
	this.color=color;
	this.pawn1=pawn1;
	this.pawn2=pawn2;
	this.turn=false;
}

/**
* Returns the name of the player.
* @pre A name was given for the player.
* @post Returns the name of the player.
* @return A string that describes the name of the player.
*/

public String getName(){
	return name;
}

/**
* Returns the color of the player which is the same color with the color of the pawns.
* @pre A color was given for the player.
* @post Returns the color of the player.
* @return Returns the color of the player.
*/

public Color getColor(){ //player's color is the same with pawns color.
	return color;
}

/**
* Informs about the winner.
* @pre Two pawns were given for the player.
* @post Returns true if both of its pawns has reach the finish,else returns false.
* @return True if both of its pawns has reach the finish,else returns false.
*/

public boolean isWinner() {
	return pawn1.isFinished() && pawn2.isFinished();
}

/**
* Changes player's turn.
* @pre 
* @post Sets turn to true if it is player's turn to play,else false.
* @param turn True if it's players turn to play,else false.
*/

public void changeTurn() {
	if(turn==false) {
		turn=true;
	}else {
		turn =false;
	}
}

/**
* Informs about player's turn.
* @pre 
* @post Returns true if it's players turn to play,else false.
* @return true if it's players turn to play,else false.
*/

public boolean isTurn() {
	return turn;
}

/**
 * Returns player's name and color.
 * @pre
 * @post Returns player's name and color.
 * @return Player's name and color.
 */
public String toString() {
	String color;
	if(getColor()==Color.RED) {
		color="Red";
	}else {
		color="Yellow";
	}
	return getName()+"("+color+")\n";
}

/**
 * Returns the first pawn of the player.
 * @pre
 * @post Returns the first pawn of the player.
 * @return The first pawn of the player.
 */
public Pawn getFirstPawn(){
	return pawn1;
}

/**
 * Returns the second pawn of the player.
 * @pre
 * @post Returns the second pawn of the player.
 * @return The second pawn of the player.
 */
public Pawn getSecondPawn() {
	return pawn2;
}

}

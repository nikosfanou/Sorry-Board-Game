package model.card;

public class SimpleNumberCard extends NumberCard{
	/** Constructor. 
	* Method to create an object of type SimpleNumberCard.
	* @pre Number must be one of the following: 3, 5, 8, 12.
	* @param number Number that is given for the card.
	* @post Creates an object of type SimpleNumberCard with a number between three, five, eight and twelve.
	*/
	
	public SimpleNumberCard(int number) {
		setNumber(number);
	}

	public String toString() {
		return "Card's number: "+getNumber()+"\n"
		      +"Attributes: With this card player can move one of his pawns "+getNumber()
		      +" squares front.\n";
		         
	}


}

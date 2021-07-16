package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.square.HomeSquare;
import model.square.SafetyZoneSquare;
import model.square.StartSquare;

public class NumberElevenCard extends NumberCard {
	
	/** Constructor. 
	* Method to create an object of type NumberElevenCard.
	* @pre 
	* @post Creates an object of type NumberElevenCard and gives to it the number 
	* eleven.
	*/
	
	public NumberElevenCard() {
		setNumber(11);
	}
	
	/**
	 * Exchanges positions between pawn and opponentPawn.
	 * @pre ableToMove function returned true/move is valid.
	 * @post Exchanges positions between pawn and opponentPawn.
	 * @param pawn Player's pawn that will be moved.
	 * @param opponentPawn One of opponent's pawns which will exchange its position
	 *  with player's pawn.
	 * @param table The table/ squares where the pawn is moving on.
	 */

public void movePawn(Pawn pawn, Pawn opponentPawn,Deck table) {
		int tmpPosition=pawn.getPosition();
		pawn.setPosition(opponentPawn.getPosition());
		opponentPawn.setPosition(tmpPosition);
		table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
		table.getBoard().get(opponentPawn.getPosition()).setPawnOn(opponentPawn);
		
		
		if(pawn.getColor()==Color.RED) {
			//ta slides
	if(pawn.getPosition()==31) {
		for(int i=1;i<=3;i++) {
			if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
				sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
				}
		}
		table.getBoard().get(pawn.getPosition()).setPawnOn(null);
		pawn.setPosition(pawn.getPosition()+3);
		table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
	}else if(pawn.getPosition()==39) {
		for(int i=1;i<=4;i++) {
			if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
				sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
				}
		}
		table.getBoard().get(pawn.getPosition()).setPawnOn(null);
		pawn.setPosition(pawn.getPosition()+4);
		table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
	}
		}else { //an to pioni einai kitrino kai pesei stin arxi kokkinou slide
			//ta slides
			if(pawn.getPosition()==1) {
				for(int i=1;i<=3;i++) {
					if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
						sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
						}
				}
				table.getBoard().get(pawn.getPosition()).setPawnOn(null);
				pawn.setPosition(pawn.getPosition()+3);
				table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			}else if(pawn.getPosition()==9) {
				for(int i=1;i<=4;i++) {
					if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
						sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
						}
				}
				table.getBoard().get(pawn.getPosition()).setPawnOn(null);
				pawn.setPosition(pawn.getPosition()+4);
				table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
			}
		}
		
		//kai gia to opponentPawn
		if(opponentPawn.getColor()==Color.RED) {
			//ta slides
	if(opponentPawn.getPosition()==31) {
		for(int i=1;i<=3;i++) {
			if(table.getBoard().get(opponentPawn.getPosition()+i).isPawnOn()==true) {
				sendStart(table.getBoard().get(opponentPawn.getPosition()+i).getPawnOn(),table);
				}
		}
		table.getBoard().get(opponentPawn.getPosition()).setPawnOn(null);
		opponentPawn.setPosition(opponentPawn.getPosition()+3);
		table.getBoard().get(opponentPawn.getPosition()).setPawnOn(opponentPawn);
	}else if(opponentPawn.getPosition()==39) {
		for(int i=1;i<=4;i++) {
			if(table.getBoard().get(opponentPawn.getPosition()+i).isPawnOn()==true) {
				sendStart(table.getBoard().get(opponentPawn.getPosition()+i).getPawnOn(),table);
				}
		}
		table.getBoard().get(opponentPawn.getPosition()).setPawnOn(null);
		opponentPawn.setPosition(opponentPawn.getPosition()+4);
		table.getBoard().get(opponentPawn.getPosition()).setPawnOn(opponentPawn);
	}
		}else { //an to pioni einai kitrino kai pesei stin arxi kokkinou slide
			//ta slides
			if(opponentPawn.getPosition()==1) {
				for(int i=1;i<=3;i++) {
					if(table.getBoard().get(opponentPawn.getPosition()+i).isPawnOn()==true) {
						sendStart(table.getBoard().get(opponentPawn.getPosition()+i).getPawnOn(),table);
						}
				}
				table.getBoard().get(opponentPawn.getPosition()).setPawnOn(null);
				opponentPawn.setPosition(opponentPawn.getPosition()+3);
				table.getBoard().get(opponentPawn.getPosition()).setPawnOn(opponentPawn);
			}else if(opponentPawn.getPosition()==9) {
				for(int i=1;i<=4;i++) {
					if(table.getBoard().get(opponentPawn.getPosition()+i).isPawnOn()==true) {
						sendStart(table.getBoard().get(opponentPawn.getPosition()+i).getPawnOn(),table);
						}
				}
				table.getBoard().get(opponentPawn.getPosition()).setPawnOn(null);
				opponentPawn.setPosition(opponentPawn.getPosition()+4);
				table.getBoard().get(opponentPawn.getPosition()).setPawnOn(opponentPawn);
			}
		}
		
	}

/**
 * Checks if player's pawn and opponent's pawn can exchange positions.
 * They can't exchange positions if pawns are located at their home or start squares and if 
 * opponent's pawn is located at its safety zone.
 * @pre Parameters are not null.
 * @post Returns true if the move is valid, else false.
 * @param pawn Player's pawn that we want to check if can be moved.
 * @param opponentPawn Opponent's pawn which we want to check if can exchange its
 * position with player's pawn.
 * @param table The table/ squares where the pawn is moving on.
 * @return True if the move is valid, else false.
 */

public boolean ableToMove(Pawn pawn, Pawn opponentPawn,Deck table) {
	if(table.getBoard().get(pawn.getPosition()) instanceof StartSquare || table.getBoard().get(pawn.getPosition()) instanceof HomeSquare || table.getBoard().get(pawn.getPosition()) instanceof SafetyZoneSquare ) {
		return false;
	}else {
		if(table.getBoard().get(opponentPawn.getPosition()) instanceof StartSquare || table.getBoard().get(opponentPawn.getPosition()) instanceof SafetyZoneSquare || table.getBoard().get(opponentPawn.getPosition()) instanceof HomeSquare) {
			return false;
		}
		return true;
	}
}

	
public String toString() {
	return "Card's number: "+getNumber()+"\n"
          +"Attributes: With this card player can move one of his pawns 11 squares"
          + "front or exchange it with an opponent's pawn if that is possible.\n"; 
}
	
}

package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.square.HomeSquare;
import model.square.SafetyZoneSquare;
import model.square.StartSquare;

public class SorryCard extends Card{

	/**
	 * Sorry card doesn't move the pawn in a different way than exchanging the positions of the
	 * player's pawn with opponent's pawn if that can happen. So this function can't do something
	 * important for our program as it doesn't take opponent's pawn as a parameter and won't be
	 * used in the program.
	 */
	
	public void movePawn(Pawn pawn, Deck d) {
    return;
	}
	
	/**
	 * Sorry card doesn't move the pawn in a different way than exchanging the positions of the
	 * player's pawn with opponent's pawn if that can happen. This function can't do something
	 * important for our program as it doesn't take opponent's pawn as a parameter so
	 * it will always return false and won't be used in the program.
	 * @return False
	 */
	
	public boolean ableToMove(Pawn pawn, Deck d) {
		return false;
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
	
	public void movePawn(Pawn pawn,Pawn opponent, Deck d) {
		    pawn.setEnable();
			pawn.setPosition(opponent.getPosition());
			sendStart(opponent,d);
			if(pawn.getColor()==Color.RED) {
				//ta slides
		if(pawn.getPosition()==31) {
			for(int i=1;i<=3;i++) {
				if(d.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
					sendStart(d.getBoard().get(pawn.getPosition()+i).getPawnOn(),d);
					}
			}
			pawn.setPosition(pawn.getPosition()+3);
		}else if(pawn.getPosition()==39) {
			for(int i=1;i<=4;i++) {
				if(d.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
					sendStart(d.getBoard().get(pawn.getPosition()+i).getPawnOn(),d);
					}
			}
			pawn.setPosition(pawn.getPosition()+4);
		}
			}else { //an to pioni einai kitrino kai pesei stin arxi kokkinou slide
				//ta slides
				if(pawn.getPosition()==1) {
					for(int i=1;i<=3;i++) {
						if(d.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
							sendStart(d.getBoard().get(pawn.getPosition()+i).getPawnOn(),d);
							}
					}
					pawn.setPosition(pawn.getPosition()+3);
				}else if(pawn.getPosition()==9) {
					for(int i=1;i<=4;i++) {
						if(d.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
							sendStart(d.getBoard().get(pawn.getPosition()+i).getPawnOn(),d);
							}
					}
					pawn.setPosition(pawn.getPosition()+4);
				}
			}
			d.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
		 
	}
	
	/**
	 * Checks if player's pawn and opponent's pawn can exchange positions.
	 * They can't exchange positions if opponent's pawns are located at their home, safety zone
	 * or start squares and if players's pawn isn't located at its start square.
	 * @pre Parameters are not null.
	 * @post Returns true if the move is valid, else false.
	 * @param pawn Player's pawn that we want to check if can be moved.
	 * @param opponentPawn Opponent's pawn which we want to check if can exchange its
	 * position with player's pawn.
	 * @param table The table/ squares where the pawn is moving on.
	 * @return True if the move is valid, else false.
	 */
	
	public boolean ableToMove(Pawn pawn,Pawn opponent, Deck d) {
		if(d.getBoard().get(pawn.getPosition()) instanceof StartSquare) {
			if(d.getBoard().get(opponent.getPosition()) instanceof StartSquare || d.getBoard().get(opponent.getPosition()) instanceof SafetyZoneSquare || d.getBoard().get(opponent.getPosition()) instanceof HomeSquare) {
				return false;
			}
			return true;
		}
		return false;
	}

	public String toString() {
	return "Sorry Card\n"+"Attributes: With this card player can"
		 + "exchange positions between one of his pawns and an "
		 + "opponent's pawn";
	}

}

package model.card;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.square.HomeSquare;
import model.square.SafetyZoneSquare;
import model.square.StartSquare;

public class NumberTenCard extends NumberCard{
	
	/** Constructor. 
	* Method to create an object of type NumberTenCard.
	* @pre 
	* @post Creates an object of type NumberTenCard and gives to it the number ten.
	*/
	
	public NumberTenCard() {
		setNumber(10);
	}
	
	public String toString() {
		return "Card's number: "+getNumber()+"\n"
			      +"Attributes: With this card player can move one of his pawns 10 squares front"
			      + "or 1 square back.\n";
	}

	/**
	 * Moves the pawn 1 square back.
	 * @pre Function ableToMoveBack returned true.
	 * @post Moves the pawn 1 square back.
	 * @param pawn The pawn that will be moved.
	 * @param table The table/ squares where the pawn is moving
	 * on.
	 */
	public void movePawnBack(Pawn pawn, Deck table) {
		table.getBoard().get(pawn.getPosition()).setPawnOn(null);
		if(pawn.getPosition()==0) {
			pawn.setPosition(pawn.getPosition()-1+60);
			if(table.getBoard().get(pawn.getPosition()).isPawnOn()==true) {
				sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(),table);
			}
		}else if(pawn.getPosition()==60) {
			pawn.setEnable();
			pawn.setPosition(pawn.getPosition()-1-57);
			if(table.getBoard().get(pawn.getPosition()).isPawnOn()==true) {
				sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(),table);
			}
		}else if(pawn.getPosition()==66) {
			pawn.setEnable();
			pawn.setPosition(pawn.getPosition()-1-33);
			if(table.getBoard().get(pawn.getPosition()).isPawnOn()==true) {
				sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(),table);
			}
		}else {
			pawn.setPosition(pawn.getPosition()-1);
			//ta slides
			if(pawn.getColor()==Color.RED) {
				if(pawn.getPosition()==16 || pawn.getPosition()==31 ||pawn.getPosition()==46) {
					for(int i=0;i<=3;i++) {
						if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
							sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
						}
					}
					pawn.setPosition(pawn.getPosition()+3);
				}else if(pawn.getPosition()==24 || pawn.getPosition()==39 || pawn.getPosition()==54) {
					for(int i=0;i<=4;i++) {
						if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
							sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
						}
					}
	        	  pawn.setPosition(pawn.getPosition()+4);
				}else {
					if(table.getBoard().get(pawn.getPosition()).isPawnOn()==true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(),table);
					}
				}
				
			}else { //an einai kitrino
          if(pawn.getPosition()==16 || pawn.getPosition()==1 ||pawn.getPosition()==46) {
        	  for(int i=0;i<=3;i++) {
					if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
						sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
					}
				}
        	  pawn.setPosition(pawn.getPosition()+3);
				}else if(pawn.getPosition()==24 || pawn.getPosition()==9 || pawn.getPosition()==54) {
					for(int i=0;i<=4;i++) {
						if(table.getBoard().get(pawn.getPosition()+i).isPawnOn()==true) {
							sendStart(table.getBoard().get(pawn.getPosition()+i).getPawnOn(),table);
						}
					}
	        	  pawn.setPosition(pawn.getPosition()+4);
				}else {
					if(table.getBoard().get(pawn.getPosition()).isPawnOn()==true) {
						sendStart(table.getBoard().get(pawn.getPosition()).getPawnOn(),table);
					}
				}
			}
		}
		table.getBoard().get(pawn.getPosition()).setPawnOn(pawn);
	}
	
	/**
	 * Checks if the pawn can move 1 square back.
	 * @pre Parameters not null.
	 * @post Returns true if the move is valid, else false.
	 * @param pawn The pawn that will be moved.
	 * @param table The table/ squares where the pawn is moving
	 * on.
	 * @return True if the move is valid, else false.
	 */
	public boolean ableToMoveBack(Pawn pawn, Deck table) {
		if(table.getBoard().get(pawn.getPosition())instanceof StartSquare) {
			return false;
		}
		if(table.getBoard().get(pawn.getPosition())instanceof HomeSquare) {
			return false;
		}
		if(table.getBoard().get(pawn.getPosition())instanceof SafetyZoneSquare) {
			if(pawn.getPosition()==60) {
				if(table.getBoard().get(pawn.getPosition()-1-57).isPawnOn()==false) {
					return true;
				}else {
					if(table.getBoard().get(pawn.getPosition()-1-57).getPawnOn().getColor()==Color.RED) {
						return false;
					}else {
						return true;
					}
				}
			}else if(pawn.getPosition()==66) {
				if(table.getBoard().get(pawn.getPosition()-1-33).isPawnOn()==false) {
					return true;
				}else {
					if(table.getBoard().get(pawn.getPosition()-1-33).getPawnOn().getColor()==Color.YELLOW) {
						return false;
					}else {
						return true;
					}
				}
			}else {
				if(table.getBoard().get(pawn.getPosition()-1).isPawnOn()==false) {
					return true;
				}else {
					return false;     //an uparxei pawn sto safety zone tha einai sigoura idiou xrwmatos
				}
			}
		}
		
		//alliws einai se aplo tetragwno
		if(pawn.getColor()==Color.RED) {
			if(pawn.getPosition()==0) {
				if(table.getBoard().get(pawn.getPosition()-1+60).isPawnOn()==false) {
					return true;
				}else {
					if(table.getBoard().get(pawn.getPosition()-1+60).getPawnOn().getColor()==Color.RED) {
						return false;
					}else {
						return true;
					}
				}
			}
			if(table.getBoard().get(pawn.getPosition()-1).isPawnOn()==false) {
				return true;
			}else {
				if(table.getBoard().get(pawn.getPosition()-1).getPawnOn().getColor()==Color.RED) {
					return false;
				}else {
					return true;
				}
			}
		}else {
			if(pawn.getPosition()==0) {
				if(table.getBoard().get(pawn.getPosition()-1+60).isPawnOn()==false) {
					return true;
				}else {
					if(table.getBoard().get(pawn.getPosition()-1+60).getPawnOn().getColor()==Color.YELLOW) {
						return false;
					}else {
						return true;
					}
				}
			}
			if(table.getBoard().get(pawn.getPosition()-1).isPawnOn()==false) {
				return true;
			}else {
				if(table.getBoard().get(pawn.getPosition()-1).getPawnOn().getColor()==Color.YELLOW) {
					return false;
				}else {
					return true;
				}
			}
		}
	}
	

}

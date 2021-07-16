package controller;

import java.awt.Color;
import java.awt.Menu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import model.Deck;
import model.Pawn;
import model.Player;
import model.card.Card;
import model.card.NumberCard;
import model.card.NumberElevenCard;
import model.card.NumberSevenCard;
import model.card.NumberTenCard;
import model.card.NumberTwoCard;
import model.card.SimpleNumberCard;
import model.card.SorryCard;
import model.square.StartSquare;
import view.View;

public class Controller {
	/**
	 * First player of the game.
	 */
	private Player player1;
	/**
	 * Second player of the game.
	 */
	private Player player2;
	/**
	 * The player who's turn to play.
	 */
	private Player currentPlayer;
	/**
	 * The board and the pack of the cards.
	 */
	private Deck cardsNBoard;
	/**
	 * A graphical interface for the user.
	 */
	private View view;

	/**
	 * Initializes the view ,the cards and the players.
	 * 
	 * @pre
	 * @post Initializes the view ,the cards and the players.
	 */
	public void initialize() {
		cardsNBoard = new Deck();
		cardsNBoard.initialize();
		view = new View();
		Pawn redPawn1 = new Pawn(Color.RED, 72);
		Pawn redPawn2 = new Pawn(Color.RED, 72);
		player1 = new Player("Player 1", Color.RED, redPawn1, redPawn2);
		Pawn yellowPawn1 = new Pawn(Color.YELLOW, 73);
		Pawn yellowPawn2 = new Pawn(Color.YELLOW, 73);
		player2 = new Player("Player 2", Color.YELLOW, yellowPawn1, yellowPawn2);
		setListeners();
		int cp = new Random().nextInt(2);
		if (cp == 0) {
			currentPlayer = player1;
		} else {
			currentPlayer = player2;
		}
		currentPlayer.changeTurn();
		System.out.println(currentPlayer.toString());
		cardsNBoard.getCard();
		view.updateLastCard(cardsNBoard.getLatestCard());
		try {
			view.updateInfoBox(view.getInfoBox().getText(0, 9) + "\n\nTurn:" + currentPlayer.toString() + "Cards Left: "
					+ cardsNBoard.getCardsLeft() + "\n");
		} catch (BadLocationException ee) {
			System.out.println("ERROR");
		}
		System.out.println("New card\n");
	}

	/**
	 * Checks if player can select fold option and if he can it changes the turn.
	 * 
	 * @pre Player isn't obligated to play or can't make any move.
	 * @post Changes the turn.
	 * @param player The player that is his turn to play.
	 * 
	 */

	public void fold() {
		if (cardsNBoard.getLatestCard() instanceof SorryCard) {
			SorryCard c = (SorryCard) cardsNBoard.getLatestCard();
			if (currentPlayer.equals(player1)) {
				if (c.ableToMove(currentPlayer.getFirstPawn(), player2.getFirstPawn(), cardsNBoard) == false
						&& c.ableToMove(currentPlayer.getSecondPawn(), player2.getFirstPawn(), cardsNBoard) == false
						&& c.ableToMove(currentPlayer.getFirstPawn(), player2.getSecondPawn(), cardsNBoard) == false
						&& c.ableToMove(currentPlayer.getSecondPawn(), player2.getSecondPawn(), cardsNBoard) == false) {

					System.out.println("Fold\n");
					changeTurn();
					c.DropCard();
				}
			} else {
				if (c.ableToMove(currentPlayer.getFirstPawn(), player1.getFirstPawn(), cardsNBoard) == false
						&& c.ableToMove(currentPlayer.getSecondPawn(), player1.getFirstPawn(), cardsNBoard) == false
						&& c.ableToMove(currentPlayer.getFirstPawn(), player1.getSecondPawn(), cardsNBoard) == false
						&& c.ableToMove(currentPlayer.getSecondPawn(), player1.getSecondPawn(), cardsNBoard) == false) {

					System.out.println("Fold\n");
					changeTurn();
					c.DropCard();
				}
			}
		} else if (cardsNBoard.getLatestCard() instanceof NumberSevenCard) {
			NumberSevenCard c = (NumberSevenCard) cardsNBoard.getLatestCard();
			if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getFirstPawn(), 6, currentPlayer.getSecondPawn(), 1,
							cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getFirstPawn(), 5, currentPlayer.getSecondPawn(), 2,
							cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getFirstPawn(), 4, currentPlayer.getSecondPawn(), 3,
							cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getFirstPawn(), 3, currentPlayer.getSecondPawn(), 4,
							cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getFirstPawn(), 2, currentPlayer.getSecondPawn(), 5,
							cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getFirstPawn(), 1, currentPlayer.getSecondPawn(), 6,
							cardsNBoard) == false) {

				System.out.println("Fold\n");
				changeTurn();
				c.DropCard();
			}
		} else if (cardsNBoard.getLatestCard() instanceof NumberTenCard) {
			NumberTenCard c = (NumberTenCard) cardsNBoard.getLatestCard();
			if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == false
					&& c.ableToMoveBack(currentPlayer.getFirstPawn(), cardsNBoard) == false
					&& c.ableToMoveBack(currentPlayer.getSecondPawn(), cardsNBoard) == false) {

				System.out.println("Fold\n");
				changeTurn();
				c.DropCard();
			}
		} else if (cardsNBoard.getLatestCard() instanceof NumberTwoCard) {
			NumberTwoCard c = (NumberTwoCard) cardsNBoard.getLatestCard();
			if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == false) {

				System.out.println("Fold\n");
				c.DropCard();
			}
		} else {
			NumberCard c = (NumberCard) cardsNBoard.getLatestCard();
			if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == false
					&& c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == false) {

				System.out.println("Fold\n");
				changeTurn();
				c.DropCard();
			}
		}
		return;
	}

	/**
	 * Changes the turn of the players and initializes the "new" current player.
	 * 
	 * @pre
	 * @post Changes the turn.
	 */
	public void changeTurn() {
		if (currentPlayer.equals(player1)) {
			currentPlayer.changeTurn();
			currentPlayer = player2;
		} else {
			currentPlayer.changeTurn();
			currentPlayer = player1;
		}
		currentPlayer.changeTurn();
		System.out.println(currentPlayer.toString());
	}

	/**
	 * Gets the fold button, Cards button and pawns buttons and adds the right
	 * listeners to them.
	 * 
	 * @pre
	 * @post Adds listeners to the fold, cards and pawns buttons.
	 */
	public void setListeners() {
		FoldListener listener = new FoldListener();
		PawnListener listener1 = new PawnListener();
		PawnListener listener2 = new PawnListener();
		PawnListener listener3 = new PawnListener();
		PawnListener listener4 = new PawnListener();
		CardsListener listener5 = new CardsListener();
		MenuListener listener6 = new MenuListener();

		view.getFoldButton().addMouseListener(listener);
		view.getPlayer1Pawns()[0].addMouseListener(listener1);
		view.getPlayer1Pawns()[1].addMouseListener(listener2);
		view.getPlayer2Pawns()[0].addMouseListener(listener3);
		view.getPlayer2Pawns()[1].addMouseListener(listener4);
		view.getPackOfCards().addMouseListener(listener5);
		view.getJMenuBar().getMenu(0).addMouseListener(listener6);
	}

	/**
	 * Checks if someone won and the game finished.
	 * 
	 * @pre
	 * @post Returns true if one of the players won,else false.
	 * @return True if one of the players won,else false.
	 */
	public boolean checkIfGameFinished() {
		if (currentPlayer.isWinner() == true) {
			return true;
		}
		return false;
	}

	/**
	 * Shows who won the game.
	 * 
	 * @pre One of the players won and the game finished.
	 * @post Shows the player who won.
	 * @param message The message we want to show.
	 */
	public void showWinningMessage(String message) {
		JOptionPane.showInputDialog(view, message, "Customized Dialog", JOptionPane.INFORMATION_MESSAGE, null, null,
				"");
	}

	/**
	 * A class which implements MouseListener. We need it for the clicks on the fold
	 * button.
	 */
	class FoldListener implements MouseListener {
		/**
		 * What will happen if we click on fold button.
		 * 
		 * @pre The current player can select to fold.
		 * @post Changes the turn.
		 */

		public void mouseClicked(MouseEvent e) {
			if ((JButton) e.getSource() == view.getFoldButton()) {

				if (checkIfGameFinished()) {
					showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
					return;
				}

				if (cardsNBoard.getLatestCard().IsPlayed() == false) {
					fold();

				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * A class which implements MouseListener. We need it for the clicks on the
	 * packOfCards button.
	 */
	class CardsListener implements MouseListener {
		/**
		 * What will happen if we click on the packOfCards button.
		 * 
		 * @pre There is at least one card which hasn't been played.
		 * @post A new card is played and the currentCard button changes its image
		 *       according to the new card.
		 */

		public void mouseClicked(MouseEvent e) {
			if ((JButton) e.getSource() == view.getPackOfCards()) {

				if (checkIfGameFinished()) {
					showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
					return;
				}

				if (cardsNBoard.getLatestCard().IsPlayed() == true) {
					cardsNBoard.getCard();
					view.updateLastCard(cardsNBoard.getLatestCard());
					try {
						view.updateInfoBox(view.getInfoBox().getText(0, 9) + "\n\nTurn:" + currentPlayer.toString()
								+ "Cards Left: " + cardsNBoard.getCardsLeft() + "\n");
					} catch (BadLocationException ee) {
						System.out.println("ERROR");
					}
					System.out.println("New card\n");
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * A class which implements MouseListener. We need it for the clicks on the pawn
	 * buttons.
	 */
	class PawnListener implements MouseListener {
		/**
		 * What will happen if we click on one of the pawns.
		 * 
		 * @pre At least one of the current player's pawns can be moved.
		 * @post Moves the pawn which we clicked on.
		 */

		public void mouseClicked(MouseEvent e) {
			if ((JButton) e.getSource() == view.getPlayer1Pawns()[0]) {
				if (currentPlayer.getColor() == Color.RED) {

					if (cardsNBoard.getLatestCard() instanceof SorryCard) {
						SorryCard c = (SorryCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player2.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player2.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player2.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player2.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTwoCard) {
						NumberTwoCard c = (NumberTwoCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberElevenCard) {

						NumberElevenCard c = (NumberElevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2", "Move front" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player2.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player2.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player2.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player2.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTenCard) {
						NumberTenCard c = (NumberTenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move front", "Move back" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMoveBack(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawnBack(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberSevenCard) {
						NumberSevenCard c = (NumberSevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move pawn1 by 7", "Move pawn1 by 6 and pawn2 by 1",
									"Move pawn1 by 5 and pawn2 by 2", "Move pawn1 by 4 and pawn2 by 3",
									"Move pawn1 by 3 and pawn2 by 4", "Move pawn1 by 2 and pawn2 by 5",
									"Move pawn1 by 1 and pawn2 by 6" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 6, currentPlayer.getSecondPawn(), 1,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 6, currentPlayer.getSecondPawn(), 1,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 5, currentPlayer.getSecondPawn(), 2,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 5, currentPlayer.getSecondPawn(), 2,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[3])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 4, currentPlayer.getSecondPawn(), 3,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 4, currentPlayer.getSecondPawn(), 3,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[4])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 3, currentPlayer.getSecondPawn(), 4,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 3, currentPlayer.getSecondPawn(), 4,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[5])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 2, currentPlayer.getSecondPawn(), 5,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 2, currentPlayer.getSecondPawn(), 5,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[6])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 1, currentPlayer.getSecondPawn(), 6,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 1, currentPlayer.getSecondPawn(), 6,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}
					} else {
						NumberCard c = (NumberCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
								changeTurn();
							}
						}

					}
				}
			} else if ((JButton) e.getSource() == view.getPlayer1Pawns()[1]) {
				if (currentPlayer.getColor() == Color.RED) {

					if (cardsNBoard.getLatestCard() instanceof SorryCard) {
						SorryCard c = (SorryCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player2.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player2.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player2.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player2.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTwoCard) {
						NumberTwoCard c = (NumberTwoCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberElevenCard) {

						NumberElevenCard c = (NumberElevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2", "Move front" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player2.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player2.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player2.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player2.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTenCard) {
						NumberTenCard c = (NumberTenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move front", "Move back" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMoveBack(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawnBack(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberSevenCard) {
						NumberSevenCard c = (NumberSevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move pawn2 by 7", "Move pawn2 by 6 and pawn1 by 1",
									"Move pawn2 by 5 and pawn1 by 2", "Move pawn2 by 4 and pawn1 by 3",
									"Move pawn2 by 3 and pawn1 by 4", "Move pawn2 by 2 and pawn1 by 5",
									"Move pawn2 by 1 and pawn1 by 6" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 6, currentPlayer.getFirstPawn(), 1,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 6, currentPlayer.getFirstPawn(), 1,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 5, currentPlayer.getFirstPawn(), 2,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 5, currentPlayer.getFirstPawn(), 2,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[3])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 4, currentPlayer.getFirstPawn(), 3,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 4, currentPlayer.getFirstPawn(), 3,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[4])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 3, currentPlayer.getFirstPawn(), 4,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 3, currentPlayer.getFirstPawn(), 4,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[5])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 2, currentPlayer.getFirstPawn(), 5,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 2, currentPlayer.getFirstPawn(), 5,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[6])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 1, currentPlayer.getFirstPawn(), 6,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 1, currentPlayer.getFirstPawn(), 6,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}
					} else {
						NumberCard c = (NumberCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
								changeTurn();
							}
						}

					}
				}
			} else if ((JButton) e.getSource() == view.getPlayer2Pawns()[0]) {
				if (currentPlayer.getColor() == Color.YELLOW) {

					if (cardsNBoard.getLatestCard() instanceof SorryCard) {
						SorryCard c = (SorryCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player1.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player1.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player1.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player1.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTwoCard) {
						NumberTwoCard c = (NumberTwoCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberElevenCard) {

						NumberElevenCard c = (NumberElevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2", "Move front" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player1.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player1.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), player1.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), player1.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTenCard) {
						NumberTenCard c = (NumberTenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move front", "Move back" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMoveBack(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawnBack(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberSevenCard) {
						NumberSevenCard c = (NumberSevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move pawn1 by 7", "Move pawn1 by 6 and pawn2 by 1",
									"Move pawn1 by 5 and pawn2 by 2", "Move pawn1 by 4 and pawn2 by 3",
									"Move pawn1 by 3 and pawn2 by 4", "Move pawn1 by 2 and pawn2 by 5",
									"Move pawn1 by 1 and pawn2 by 6" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 6, currentPlayer.getSecondPawn(), 1,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 6, currentPlayer.getSecondPawn(), 1,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 5, currentPlayer.getSecondPawn(), 2,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 5, currentPlayer.getSecondPawn(), 2,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[3])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 4, currentPlayer.getSecondPawn(), 3,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 4, currentPlayer.getSecondPawn(), 3,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[4])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 3, currentPlayer.getSecondPawn(), 4,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 3, currentPlayer.getSecondPawn(), 4,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[5])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 2, currentPlayer.getSecondPawn(), 5,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 2, currentPlayer.getSecondPawn(), 5,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[6])) {
								if (c.ableToMove(currentPlayer.getFirstPawn(), 1, currentPlayer.getSecondPawn(), 6,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getFirstPawn(), 1, currentPlayer.getSecondPawn(), 6,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}
					} else {
						NumberCard c = (NumberCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getFirstPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getFirstPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
								changeTurn();
							}
						}

					}
				}
			} else if ((JButton) e.getSource() == view.getPlayer2Pawns()[1]) {
				if (currentPlayer.getColor() == Color.YELLOW) {

					if (cardsNBoard.getLatestCard() instanceof SorryCard) {
						SorryCard c = (SorryCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player1.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player1.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player1.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player1.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTwoCard) {
						NumberTwoCard c = (NumberTwoCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();

								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberElevenCard) {

						NumberElevenCard c = (NumberElevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Exchange with pawn1", "Exchange with pawn2", "Move front" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player1.getFirstPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player1.getFirstPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), player1.getSecondPawn(),
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), player1.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}

						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberTenCard) {
						NumberTenCard c = (NumberTenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move front", "Move back" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMoveBack(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawnBack(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}

					} else if (cardsNBoard.getLatestCard() instanceof NumberSevenCard) {
						NumberSevenCard c = (NumberSevenCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							String[] moves = { "Move pawn2 by 7", "Move pawn2 by 6 and pawn1 by 1",
									"Move pawn2 by 5 and pawn1 by 2", "Move pawn2 by 4 and pawn1 by 3",
									"Move pawn2 by 3 and pawn1 by 4", "Move pawn2 by 2 and pawn1 by 5",
									"Move pawn2 by 1 and pawn1 by 6" };
							String s = (String) JOptionPane.showInputDialog(view, "SelectMove\n", "Customized Dialog",
									JOptionPane.PLAIN_MESSAGE, null, moves, "");
							if (s.equals(moves[0])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[1])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 6, currentPlayer.getFirstPawn(), 1,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 6, currentPlayer.getFirstPawn(), 1,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[2])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 5, currentPlayer.getFirstPawn(), 2,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 5, currentPlayer.getFirstPawn(), 2,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[3])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 4, currentPlayer.getFirstPawn(), 3,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 4, currentPlayer.getFirstPawn(), 3,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[4])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 3, currentPlayer.getFirstPawn(), 4,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 3, currentPlayer.getFirstPawn(), 4,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();

									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[5])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 2, currentPlayer.getFirstPawn(), 5,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 2, currentPlayer.getFirstPawn(), 5,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();
									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							} else if (s.equals(moves[6])) {
								if (c.ableToMove(currentPlayer.getSecondPawn(), 1, currentPlayer.getFirstPawn(), 6,
										cardsNBoard) == true) {
									c.movePawn(currentPlayer.getSecondPawn(), 1, currentPlayer.getFirstPawn(), 6,
											cardsNBoard);
									view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(),
											player2.getFirstPawn(), player2.getSecondPawn());
									c.DropCard();
									if (checkIfGameFinished()) {
										showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
										return;
									}
									changeTurn();
								}
							}
						}
					} else {
						NumberCard c = (NumberCard) cardsNBoard.getLatestCard();
						if (c.IsPlayed() == false) {
							if (c.ableToMove(currentPlayer.getSecondPawn(), cardsNBoard) == true) {
								c.movePawn(currentPlayer.getSecondPawn(), cardsNBoard);
								view.updatePawn(player1.getFirstPawn(), player1.getSecondPawn(), player2.getFirstPawn(),
										player2.getSecondPawn());
								c.DropCard();
								if (checkIfGameFinished()) {
									showWinningMessage("The winner is " + currentPlayer.toString() + "\n");
									return;
								}
								changeTurn();
							}
						}

					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * A class which implements MouseListener. We need it for the clicks on the menu
	 * bar.
	 */
	class MenuListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == view.getJMenuBar().getMenu(0)) {
				newGame();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Creates a new game.
	 * 
	 * @pre
	 * @post Creates a new controller,view and model.
	 */
	public static void newGame() {
		Controller controller = new Controller();
		controller.initialize();
	}

	public static void main(String[] args) {
		newGame();
	}

}

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import model.Pawn;
import model.card.Card;
import model.square.Square;

public class View extends JFrame {
    /**
     * The menu of the application.
     */
    private JMenuBar menuBar;
    /**
     * The button for the pack of the cards.
     */
    private JButton packOfCards;
    /**
     * The button for the last card played.
     */
    private JButton currentCard;
    /**
     * The squares of the board.
     */
    private JLabel[] squares;
    /**
     * The fold button.
     */
    private JButton foldButton;
    /**
     * The info box.
     */
    private JTextArea infoBox;

    /**
     * All the components of the game will be added here in order to be shown.
     */
    private JLayeredPane basic_panel;
    /**
     * The background of the board.
     */
    private JLabel background;

    /**
     * The background of the table.
     */
    private JLabel tableBackground;

    /**
     * The pawns of the first player.
     */
    private JButton[] player1Pawns;
    /**
     * The pawns of the second player.
     */
    private JButton[] player2Pawns;

    /**
     * Creates the fold button.
     * 
     * @pre
     * @post Creates the fold button.
     */
    public void createFoldButton() {
        foldButton = new JButton("Fold Button");
        foldButton.setBounds(750, 300, 185, 40);
        foldButton.setBackground(Color.RED);
    }

    /**
     * Gives access to the foldButton.
     * 
     * @pre
     * @post Returns the fold button.
     * @return The fold button.
     */
    public JButton getFoldButton() {
        return foldButton;
    }

    /**
     * Creates the packOfCards button.
     * 
     * @pre
     * @post Creates the packOfCards button.
     */
    public void createPackOfCards() {
        packOfCards = new JButton("Pack Of Cards");
        packOfCards.setBounds(750, 150, 85, 120);
        // packOfCards.setIcon(new ImageIcon(new
        // ImageIcon(tableBackground.getClass().getResource("images/images/cards/backCard.png")).getImage().getScaledInstance(36,
        // 36, Image.SCALE_SMOOTH)));
        packOfCards.setHorizontalAlignment(JButton.CENTER);
        packOfCards.setIcon(new ImageIcon("images/images/cards/backCard2.png"));
    }

    /**
     * Creates the currentCard button.
     * 
     * @pre
     * @post Creates the currentCard button.
     */
    public void createCurrentCardButton() {
        currentCard = new JButton();
        currentCard.setBounds(850, 150, 85, 120);
        currentCard.setBackground(Color.WHITE);
    }

    /**
     * Gives access to the packOfCards Button.
     * 
     * @pre
     * @post Returns the button for the pack of the cards.
     * @return The button for the pack of the cards.
     */
    public JButton getPackOfCards() {
        return packOfCards;
    }

    /**
     * Creates the Info box.
     * 
     * @pre
     * @post Creates the Info box.
     */

    public void createInfoBox() {
        infoBox = new JTextArea("Info Box\n\n\n");
        infoBox.setEditable(false);
        infoBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        infoBox.setFont(new Font("Arial", Font.BOLD, 15));
        infoBox.setBounds(750, 350, 185, 100);

    }

    /**
     * Updates currentCard button image.
     * 
     * @pre Parameter is not null.
     * @post Show the image of the latest played card.
     * @param c The latest played card.
     */
    public void updateLastCard(Card c) {
        currentCard.setIcon(new ImageIcon(c.getImage()));
        basic_panel.repaint();
    }

    /**
     * Creates the squares of the board. Every square will have the color/image
     * corresponding to its type.
     * 
     * @pre Parameter is not null.
     * @post Creates the squares of the board.
     */
    public void createSquares() {
        squares = new JLabel[74];

        for (int i = 0; i < 16; i++) { // panw seira
            squares[i] = new JLabel();
            squares[i].setBounds(20 + 40 * i, 20, 40, 40);
            squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            if (i == 1 || i == 9) {
                squares[i].setIcon(new ImageIcon("images/images/slides/redSlideStart2.png"));
            }

            if (i == 2 || i == 3 || i == 10 || i == 11 || i == 12) {
                squares[i].setIcon(new ImageIcon("images/images/slides/redSlideMedium2.png"));
            }

            if (i == 4 || i == 13) {
                squares[i].setIcon(new ImageIcon("images/images/slides/redSlideEnd2.png"));
            }

            squares[i].setOpaque(true);
        }

        for (int i = 16; i < 30; i++) { // deksia seira
            squares[i] = new JLabel();
            squares[i].setBounds(620, 20 + 40 * (i - 15), 40, 40);
            squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            if (i == 16 || i == 24) {
                squares[i].setIcon(new ImageIcon("images/images/slides/blueSlideStart2.png"));
            }

            if (i == 17 || i == 18 || i == 25 || i == 26 || i == 27) {
                squares[i].setIcon(new ImageIcon("images/images/slides/blueSlideMedium2.png"));
            }

            if (i == 19 || i == 28) {
                squares[i].setIcon(new ImageIcon("images/images/slides/blueSlideEnd2.png"));
            }

            squares[i].setOpaque(true);
        }

        for (int i = 30; i < 45; i++) { // katw seira
            squares[i] = new JLabel();
            squares[i].setBounds(620 - 40 * (i - 30), 620, 40, 40);
            squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            if (i == 31 || i == 39) {
                squares[i].setIcon(new ImageIcon("images/images/slides/yellowSlideStart2.png"));
            }

            if (i == 32 || i == 33 || i == 40 || i == 41 || i == 42) {
                squares[i].setIcon(new ImageIcon("images/images/slides/yellowSlideMedium2.png"));
            }

            if (i == 34 || i == 43) {
                squares[i].setIcon(new ImageIcon("images/images/slides/yellowSlideEnd2.png"));
            }

            squares[i].setOpaque(true);
        }

        for (int i = 45; i < 60; i++) { // aristeri seira
            squares[i] = new JLabel();
            squares[i].setBounds(20, 620 - 40 * (i - 45), 40, 40);
            squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            if (i == 46 || i == 54) {
                squares[i].setIcon(new ImageIcon("images/images/slides/greenSlideStart2.png"));
            }

            if (i == 47 || i == 48 || i == 55 || i == 56 || i == 57) {
                squares[i].setIcon(new ImageIcon("images/images/slides/greenSlideMedium2.png"));
            }

            if (i == 49 || i == 58) {
                squares[i].setIcon(new ImageIcon("images/images/slides/greenSlideEnd2.png"));
            }

            squares[i].setOpaque(true);
        }

        for (int i = 60; i < 66; i++) { // panw safety zone
            squares[i] = new JLabel();
            if (i == 65) {
                squares[i].setBounds(78, 20 + 40 * (i - 59), 85, 70);
                squares[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                squares[i].setText("Home");
                squares[i].setFont(new Font("Arial", Font.BOLD, 20));
                squares[i].setVerticalAlignment(JLabel.BOTTOM);
                squares[i].setHorizontalAlignment(JLabel.CENTER);
            } else {
                squares[i].setBounds(100, 20 + 40 * (i - 59), 40, 40);
                squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                squares[i].setBackground(Color.RED);
            }
            squares[i].setOpaque(true);
        }

        for (int i = 66; i < 72; i++) { // katw safety zone
            squares[i] = new JLabel();
            if (i == 71) {
                squares[i].setBounds(518, 590 - 40 * (i - 65), 85, 70);
                squares[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                squares[i].setText("Home");
                squares[i].setFont(new Font("TimesRoman", Font.BOLD, 20));
                squares[i].setVerticalAlignment(JLabel.TOP);
                squares[i].setHorizontalAlignment(JLabel.CENTER);
            } else {
                squares[i].setBounds(540, 620 - 40 * (i - 65), 40, 40);
                squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                squares[i].setBackground(Color.YELLOW);
            }
            squares[i].setOpaque(true);
        }

        // start squares

        squares[72] = new JLabel("Start");
        squares[72].setBounds(158, 60, 85, 70);
        squares[72].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        squares[72].setBackground(Color.WHITE);
        squares[72].setFont(new Font("TimesRoman", Font.BOLD, 20));
        squares[72].setVerticalAlignment(JLabel.BOTTOM);
        squares[72].setHorizontalAlignment(JLabel.CENTER);

        squares[73] = new JLabel("Start");
        squares[73].setBounds(438, 550, 85, 70);
        squares[73].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        squares[73].setBackground(Color.WHITE);
        squares[73].setFont(new Font("TimesRoman", Font.BOLD, 20));
        squares[73].setVerticalAlignment(JLabel.TOP);
        squares[73].setHorizontalAlignment(JLabel.CENTER);

    }

    /**
     * Creates the pawns buttons.
     * 
     * @pre
     * @post Creates the pawns buttons.
     */
    public void createPawns() {
        player1Pawns = new JButton[2];
        player2Pawns = new JButton[2];
        player1Pawns[0] = new JButton(new ImageIcon("images/images/pawns/redPawn1,2.png"));
        player1Pawns[1] = new JButton(new ImageIcon("images/images/pawns/redPawn2,2.png"));
        player2Pawns[0] = new JButton(new ImageIcon("images/images/pawns/yellowPawn1,2.png"));
        player2Pawns[1] = new JButton(new ImageIcon("images/images/pawns/yellowPawn2,2.png"));
        player1Pawns[0].setBounds(161, 62, 40, 40); // gia to start tou
        player1Pawns[1].setBounds(200, 62, 40, 40); // gia to start tou
        player2Pawns[0].setBounds(441, 578, 40, 40); // gia na vrisketai sto start tou
        player2Pawns[1].setBounds(480, 578, 40, 40); // gia na vrisketai sto start tou
    }

    /**
     * Sends the pawn to its start square.
     * 
     * @pre Valid pawn is given.
     * @post Sends the pawn to its start square.
     * @param pawn the pawn we move.
     */

    public void startAgain(JButton pawn) {
        if (pawn.equals(player1Pawns[0])) {
            player1Pawns[0].setBounds(161, 62, 40, 40);
        } else if (pawn.equals(player1Pawns[1])) {
            player1Pawns[1].setBounds(200, 62, 40, 40);
        } else if (pawn.equals(player2Pawns[0])) {
            player2Pawns[0].setBounds(441, 578, 40, 40);
        } else if (pawn.equals(player2Pawns[1])) {
            player2Pawns[1].setBounds(480, 578, 40, 40);
        }
    }

    /**
     * Sends the pawn to its home square.
     * 
     * @pre Valid pawn is given.
     * @post Sends the pawn to its home square.
     * @param pawn the pawn we move.
     */
    public void reachHome(JButton pawn) {
        if (pawn.equals(player1Pawns[0])) {
            player1Pawns[0].setBounds(80, 262, 40, 40); // otan mpainei home to prwto kokkino
        } else if (pawn.equals(player1Pawns[1])) {
            player1Pawns[1].setBounds(120, 262, 40, 40); // otan mpainei home to deytero kokkino
        } else if (pawn.equals(player2Pawns[0])) {
            player2Pawns[0].setBounds(521, 375, 40, 40); // otan mpainei sto home to prwto kitrino
        } else if (pawn.equals(player2Pawns[1])) {
            player2Pawns[1].setBounds(561, 375, 40, 40); // otan mpainei home to deytero kitrino
        }

    }

    /**
     * Gives access to the first player pawns.
     * 
     * @pre
     * @post Returns the buttons of the first player pawns.
     * @return The buttons of the first player pawns..
     */
    public JButton[] getPlayer1Pawns() {
        return player1Pawns;
    }

    /**
     * Gives access to the second player's pawns.
     * 
     * @pre
     * @post Returns the buttons of the second player pawns.
     * @return The buttons of the second player pawns..
     */
    public JButton[] getPlayer2Pawns() {
        return player2Pawns;
    }

    /**
     * Updates what the info box shows.
     * 
     * @pre
     * @post Updates what the info box shows.
     * @param info The message we want to be shown at the info box.
     */
    public void updateInfoBox(String info) {
        infoBox.setText(info);
        basic_panel.repaint();
    }

    /**
     * Returns the info box.
     * 
     * @pre
     * @post Returns the info box.
     * @return The info box.
     */
    public JTextArea getInfoBox() {
        return infoBox;
    }

    /**
     * Updates the positions of all pawns.
     * 
     * @pre Pawns are given in the right order.
     * @post Updates the positions of the pawns.
     * @param redPawn1    The first pawn of the first player(red).
     * @param redPawn2    The second pawn of the first player(red).
     * @param yellowPawn1 The first pawn of the second player(yellow).
     * @param yellowPawn2 The second pawn of the second player(yellow).
     */
    public void updatePawn(Pawn redPawn1, Pawn redPawn2, Pawn yellowPawn1, Pawn yellowPawn2) {
        if (redPawn1.getPosition() == 72) {
            startAgain(player1Pawns[0]);
        } else if (redPawn1.getPosition() == 65) {
            reachHome(player1Pawns[0]);
        } else {
            int x, y;
            x = squares[redPawn1.getPosition()].getX();
            y = squares[redPawn1.getPosition()].getY();
            player1Pawns[0].setLocation(x, y);
        }

        if (redPawn2.getPosition() == 72) {
            startAgain(player1Pawns[1]);
        } else if (redPawn2.getPosition() == 65) {
            reachHome(player1Pawns[1]);
        } else {
            int x, y;
            x = squares[redPawn2.getPosition()].getX();
            y = squares[redPawn2.getPosition()].getY();
            player1Pawns[1].setLocation(x, y);
        }

        if (yellowPawn1.getPosition() == 73) {
            startAgain(player2Pawns[0]);
        } else if (yellowPawn1.getPosition() == 71) {
            reachHome(player2Pawns[0]);
        } else {
            int x, y;
            x = squares[yellowPawn1.getPosition()].getX();
            y = squares[yellowPawn1.getPosition()].getY();
            player2Pawns[0].setLocation(x, y);
        }

        if (yellowPawn2.getPosition() == 73) {
            startAgain(player2Pawns[1]);
        } else if (yellowPawn2.getPosition() == 71) {
            reachHome(player2Pawns[1]);
        } else {
            int x, y;
            x = squares[yellowPawn2.getPosition()].getX();
            y = squares[yellowPawn2.getPosition()].getY();
            player2Pawns[1].setLocation(x, y);
        }
        basic_panel.repaint();
    }

    /**
     * Creates the menu bar.
     * 
     * @pre
     * @post Creates the menu bar.
     */
    public void createMenu() {
        menuBar = new JMenuBar();

        JMenu item1, item2, item3, item4;
        item1 = new JMenu("New Game");
        item2 = new JMenu("Save Game");
        item3 = new JMenu("Continue Saved Game");
        item4 = new JMenu("Exit Game");
        menuBar.add(item1);
        menuBar.add(item2);
        menuBar.add(item3);
        menuBar.add(item4);
    }

    /**
     * Sets the background of the board.
     * 
     * @pre
     * @post Sets the background of the board.
     */
    public void setBackground() {
        background = new JLabel("");
        background.setBounds(60, 60, 560, 560);
        background.setBackground(Color.CYAN);
        background.setOpaque(true);
        background.setIcon(new ImageIcon("images/images/sorryImage2.png"));
        background.setHorizontalAlignment(SwingConstants.CENTER);

    }

    /**
     * Sets the background of the table.
     * 
     * @pre
     * @post Sets the background of the table.
     */
    public void setTableBackground() {
        tableBackground = new JLabel(new ImageIcon("images/images/background.png"));
        tableBackground.setBounds(0, 20, 1000, 700);
    }

    /**
     * Initializes all the components of the game by calling the right/above methods
     * and adds them to the basic_panel.
     * 
     * @pre
     * @post Initializes all the components and adds them to the basic_panel.
     */
    public void initialize() {
        basic_panel = new JLayeredPane();
        createFoldButton();
        setTableBackground();
        createPackOfCards();
        createCurrentCardButton();
        setBackground();
        createMenu();
        createPawns();
        createInfoBox();
        createSquares();
        basic_panel.add(tableBackground);
        basic_panel.add(foldButton, 0);
        basic_panel.add(packOfCards, 0);
        basic_panel.add(currentCard, 0);
        basic_panel.add(background, 0);
        for (int i = 0; i < 74; i++) {
            basic_panel.add(squares[i], 0);
        }
        basic_panel.add(player1Pawns[0], 0);
        basic_panel.add(player1Pawns[1], 0);
        basic_panel.add(player2Pawns[0], 0);
        basic_panel.add(player2Pawns[1], 0);
        basic_panel.add(infoBox, 0);
    }

    /**
     * Constructor. Adds the panel to the jframe.
     * 
     * @pre
     * @post Adds the panel to the jframe in order to show the components of the
     *       game.
     */

    public View() {
        initialize();
        this.setSize(1000, 700);
        this.setLayout(null);
        this.setLayeredPane(basic_panel);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */

package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Represents a game of Battleship with two players.
 *
 */
public class Game implements Runnable {

    private static final ShipType[] FLEET = ShipType.values();

    private int currentPlayer;
    private BufferedReader in;
    private PrintStream out;
    private final String[] playerNames;
    private final Board[] boards;
    private final IO[] ios;

    /**
     * Construct a new game.
     *
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     * @param p1 an IO for player 1
     * @param p2 an IO for player 2
     */
    public Game(String player1Name, String player2Name, IO p1, IO p2) {
        this.currentPlayer = 0;
        this.in = p1.getIn();
        this.out = p1.getOut();
        this.playerNames = new String[] { player1Name, player2Name };
        this.boards = new Board[] { new Board(), new Board() };
        this.ios = new IO[] { p1, p2 };
    }

    /**
     * Constructs a game with default values for the input/output pairs.
     *
     * The default values use console input and output for both players.
     * @param player1Name
     * @param player2Name
     */
    public Game(String player1Name, String player2Name) {
    	this(player1Name, player2Name, IO.STDIO, IO.STDIO);
        // DONE implement this constructor
    	// constructor chaining, if they only input p1name and p2name we go here and use default IO
    }

    /**
     * Set the current input and output to the given player's.
     *
     * @param player which player to get input from
     */
    private void setIO(int player) {
        // DONE implement this method
    	this.in = this.ios[player].getIn();
    	this.out = this.ios[player].getOut();
    }

    /**
     * Sets up the ships of the player.
     *
     * For each ship in a player's fleet (Carrier, Battleship,
     * Cruiser, Submarine, Destroyer), take the input of the current
     * player to add the ship onto the current player's board. Each line
     * of a player's input when prompted "Place your [shipType]" will be in
     * the form "[square] [direction]" (i.e. "A1 v").
     *
     * @param player the player whose board to set up
     */
    private void setup(int player) throws IOException {
        setIO(player);

        // this player's name and board
        String name = this.playerNames[player];
        Board board = this.boards[player];

        this.out.printf("%s, set up your fleet.\n", name);

        for (ShipType shipType : FLEET) {
            // prompt for a location and direction until successful
            while(true) {
                board.printShips(this.out);
                this.out.printf("Place your %s (%d): ", shipType, shipType.length());
                try {
                    // TODO implement the following
                    /*
                     * Read each line of this player's input. Split each line by any
                     * whitespace character (hint: use "\\s+" as the regex to split on)
                     * to get the square of the upper-left corner square the ship should
                     * be placed at and the direction of the ship. Then add the ship to the
                     * this player's board.
                     *
                     * (see the Direction class for a useful method to convert user input
                     * to Direction's).
                     */
                	String[] inputParts = this.in.readLine().split("\\s+");

                	String shipCoordinates = inputParts[0];
                	String dir = inputParts[1];
                	Direction direction = Direction.parseDirection(dir);
                	
                	int[] coordinates = new int[]{Board.squareToColumnNumber(shipCoordinates), Board.squareToRowNumber(shipCoordinates)};
                	board.addShipToBoard(new Ship(shipType, direction, coordinates));

                    // Keep this break statement to break out of the while loop when the try
                    // has run to completion, i.e., no errors occur.
                    break;
                } catch (Exception e) {
                    this.out.println(e);
                    this.out.println("Invalid placement. Try again.");
                }
            }
        }

        board.printShips(this.out);
    }

    /**
     * Sets up the board.
     *
     * Player 1 sets up their board first.
     */
    private void setup() throws IOException {
        setup(0);
        setup(1);
    }

    /**
     * Run one player's turn.
     *
     * Runs the current player's turn, and then sets the current player to the opponent.
     * First, prompts the current player for square to fire upon. Then, tries to
     * fire shot at square specified. If it is a hit, "HIT!\n" will be sent to the
     * current player's out. Else "MISS!\n" will be sent to the current player's out.
     */
    private void turn() throws IOException {
        setIO(this.currentPlayer);

        this.out.printf("%s's turn.\n", this.playerNames[this.currentPlayer]);

        int opponentPlayer = 1 - this.currentPlayer;
        Board board = this.boards[opponentPlayer]; // player 1 shoots onto player 2's board, and vice versa

        // prompt for a square to fire on until successful
        while (true) {
            this.out.printf("%s's board:\n", this.playerNames[opponentPlayer]);
            board.printShots(this.out);
            this.out.print("Square to fire upon: ");
            String square = this.in.readLine();
            if (square == null) {
                throw new IOException("EOF");
            }
            try {
                // TODO implement the following
                /*
                 * Fire shot at square specified from the input. If the
                 * shot is a hit, print to the current player's output
                 * "HIT!\n" (ending with a newline). If the shot is a miss,
                 * print to the current player's output "MISS!\n"
                 * (ending with a newline).
                 */
            	if (square.length() != 2) {
            		throw new Exception("Input a value like A3");
            	}
            	if(board.fireShot(square)) {
            		this.out.print("HIT!\n");
            	} else {
            		this.out.print("MISS!\n");
            	}
            	
                
                // Keep this break statement to break out of the while loop when the try
                // has run to completion, i.e., no errors occur.
                break;
            } catch (Exception e) {
                this.out.println(e);
                this.out.println("Invalid shot. Try again.");
            }
        }

        this.out.printf("%s's board:\n", this.playerNames[opponentPlayer]);
        board.printShots(this.out);

        this.currentPlayer = opponentPlayer; // toggle current player
    }

    /**
     * Check if the game is over.
     *
     * @return true iff one of the player's ships have all been sunk.
     */
    private boolean isOver() {
     // TODO implement this method

        for(Board item : boards) {
        	if(item.checkShipsSunk()) {
        		return true;
        	}
        }
        return false;
    }

    /**
     * Print out the game result.
     *
     * Displays who won.
     *
     * @throws IllegalStateException if the game is not over
     */
    private void reportGameResult() {
        if (!isOver()) {
            throw new IllegalStateException("game is not over");
        }

        int winner;
        if (this.boards[0].checkShipsSunk()) {
            winner = 1;
        } else {
            winner = 0;
        }

        String winnerName = this.playerNames[winner];

        // Print out the winner to all participants.
        System.out.printf("%s wins!\n", winnerName);
        this.ios[0].getOut().printf("%s wins!\n", winnerName);
        this.ios[1].getOut().printf("%s wins!\n", winnerName);
    }

    /**
     * Start the game.
     *
     * Initiates Board setup, then begins player 1's turn.
     */
    @Override
    public void run() {
        try {
            setup();
            while (!isOver()) {
                turn();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        }
        reportGameResult();
    }

    /**
     * Run the Game class as the main program.
     *
     * By default, runs the game as a console program between
     * two players playing on the same computer.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Name of player 1: ");
        String player1 = input.readLine();
        System.out.print("Name of player 2: ");
        String player2 = input.readLine();

        Game game = new Game(player1, player2);
        game.run();
    }
}

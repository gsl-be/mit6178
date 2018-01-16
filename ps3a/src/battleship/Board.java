/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

import java.io.PrintStream;

/**
 * The Board class represents a players battle ship board.
 *
 * It keeps track of where all the players ships are located as well as what
 * points have been fired on and if they were a hit or miss.
 *
 * The Board class also serves as the interface for placing ships and firing
 * shots as well as printing the board (target board and ships board)
 */
public class Board {

    // 2d array which keeps track of whether each square of the board has been hit ("HIT"),
    // missed ("MISSED")or not shot on ("CLEAN")
    private SquareState[][] boardState;

    // 2d array which keeps track of the ships on the board. for example, if there is a ship at
    // column = 3, row = 7, then boardShips[7][3] will be "true", otherwise it will be "false"
    private boolean[][] boardShips;

    // Store the column and row sizes for the board
    private int numColumns;
    private int numRows;

    /**
     * Constructs an empty board, size 10 by 10, with no ships.
     */
    public Board() {
        this(new Ship[0], 10, 10);
    }

    /**
     * This method is a constructor for a Board object. This means whenever a new Board object
     * is created, this is called method right away.
     *
     * This constructor takes in an array of ship objects and should store this
     * information in the boardShips variable.
     *
     * The boardState variable should also be initialized such that it represents
     * a numRows x numColumns sized 2d array. We should initialize each
     * SquareState of the board to "CLEAN"
     *
     *
     * @param ships array of Ships to place on board
     * @param coordinates coordinates of board
     */
    public Board(Ship[] ships, int numColumns, int numRows) {
        // Initialize boardShips
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.boardShips = new boolean[numRows][numColumns];

        for (int i = 0; i < this.numRows; i++) {
            boolean[] newRow = new boolean[this.numColumns];
            for (int j = 0; j < this.numColumns; j++) {
                newRow[j] = false;
            }
            this.boardShips[i] = newRow;
        }

        for (int i = 0; i < ships.length; i++) {
            try {
                addShipToBoard(ships[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.boardState = new SquareState[this.numRows][this.numColumns];
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                this.boardState[i][j] = SquareState.CLEAN;
            }
        }
    }

    /**
     * Add a ship to the board.
     *
     * @param ship the ship to add to this board
     * @throws IllegalArgumentException if the ship is out of bounds, or if it
     *   intersects another ship. The state of the Board is not modified.
     */
    void addShipToBoard(Ship ship) {
        int column = ship.getCoordinate()[0];
        int row = ship.getCoordinate()[1];
        Direction direction = ship.getDirection();
        ShipType shipType = ship.getShipType();

        if (!shipInBounds(this.numRows, this.numColumns, column, row, direction, shipType)) {
            throw new IllegalArgumentException("Ships not in bounds of the board. Please check ship positions.");
        }

        int[][] spannedSquares = squaresShipSpans(column, row, ship.getDirection(), ship.getShipType());

        for (int[] coordinate : spannedSquares) {
            row = coordinate[1];
            column = coordinate[0];
            if (boardShips[row][column]) {
                throw new IllegalArgumentException("Ships overlap. Please check ship positions.");
            }
        }

        for (int[] coordinate : spannedSquares) {
            row = coordinate[1];
            column = coordinate[0];
            boardShips[row][column] = true;
        }
    }

    /**
     * Getter method that returns the board's state
     *
     * @return a 2d array of SquareStates representing the board's state
     */
    public SquareState[][] getBoardState() {
        return this.boardState;
    }

    /**
     * Fires a shot on the board. The board state {@code boardState} should be
     * updated to reflect a shot on the inputed coordinate. If the shot is not in
     * bounds, please print the string "Shot was out of bounds. Please try again.\n"
     *
     * @param square the square's coordinate (e.g., "B7")
     * @return true iff the shot hits
     * @throws IllegalArgumentException if the square is out of bounds
     */
    public boolean fireShot(String square) {
        int row = squareToRowNumber(square);
        int column = squareToColumnNumber(square);
        if (!shotInBounds(this.numRows, this.numColumns, column, row)) {
            throw new IllegalArgumentException("shot out of bounds");
        }
        if (boardShips[row][column]) {
            boardState[row][column] = SquareState.HIT;
            return true;
        } else {
            boardState[row][column] = SquareState.MISS;
            return false;
        }
    }

    /**
    * Checks the board to see if all ships have been sunk
    *
    * @return true iff all the ships on the board have sunk else false
    */
    public boolean checkShipsSunk() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                if (boardShips[i][j] && boardState[i][j] != SquareState.HIT) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the board reflecting shots fired as hits or misses. A square with
     * a Miss should print as a "O", a square with a hit should print as a "X",
     * and a "Clean" square (may or may not have a ship, but hasn't been fired
     * on) should print as "-". The end of each row should be terminated with a
     * "\n" for a new line. There should be no spaces between the points
     *
     * For example
     *
     * "---XX\n-OO--\n-----\nOO---\n-----\n"
     */
    public void printShots(PrintStream out) {
        for (SquareState[] row : boardState) {
            for (SquareState sq : row) {
                char ch;
                switch (sq) {
                case CLEAN:
                    ch = '-';
                    break;
                case HIT:
                    ch = 'X';
                    break;
                case MISS:
                    ch = 'O';
                    break;
                default:
                    throw new RuntimeException("encountered invalid square " + sq);
                }
                out.print(ch);
            }
            out.println();
        }
    }

    /**
     * Print the ships on board to the console.
     *
     *  Mark each square that has a ship with "O" and
     *  other squares with '-'. End each row with "\n"
     *
     *  For example, a 3x3 board with on horizontal destroyer in the upper left
     *  would print:
     *
     *  "OO-\n---\n---\n"
     *
     * @param board the 2d boolean array to be printed containing true's (hits) and false's (misses)
     */
    public void printShips(PrintStream out) {
        for (boolean[] row : boardShips) {
            for (boolean ship : row) {
                out.print(ship ? 'O' : '-');
            }
            out.println();
        }
    }

    /**
     * Convert the square's letter, representing a column on the board, to a
     * 0-based column number.
     *
     * @param square the square's coordinate (e.g., "B7")
     * @return the 0-based column number, numbered from left to right
     */
    public static int squareToColumnNumber(String square) {
        String letters = "ABCDEFGHIJ";

        return letters.indexOf(square.substring(0, 1));
    }

    /**
     * Convert the square's number, representing a row on the board, to a
     * 0-based row number.
     *
     * @param square the square's coordinate (e.g., "B7")
     * @return the 0-based row number, numbered from top to bottom
     */
    public static int squareToRowNumber(String square) {
        int number = Integer.parseInt(square.substring(1));
        int rowNumber = number - 1;

        return rowNumber;
    }

    /**
     * Determine the coordinates of the squares that the given ship spans
     *
     * @param shipColumn    the 0-based column number of the upper-left square of the ship
     * @param shipRow       the 0-based row number of the upper-left square of the ship
     * @param shipDirection the orientation of the ship
     * @param shipType the class of the ship
     * @return 2d integer array of coordinates of squares that the ship spans
     */
    public static int[][] squaresShipSpans(int shipColumn, int shipRow, Direction shipDirection, ShipType shipType) {
        int[][] squares = new int[shipType.length()][2];
        int column = 0;
        int row = 1;

        if (shipDirection == Direction.HORIZONTAL) {
            for (int i = 0; i < shipType.length(); i++) {
                squares[i][column] = shipColumn + i;
                squares[i][row] = shipRow;
            }
        } else if (shipDirection == Direction.VERTICAL) {
            for (int j = 0; j < shipType.length(); j++) {
                squares[j][column] = shipColumn;
                squares[j][row] = shipRow + j;
            }
        }

        return squares;
    }

    /**
     * Determine whether a ship position is within the game board.
     *
     * @param height the number of rows    in the game board
     * @param width  the number of columns in the game board
     * @param column the 0-based column number of the upper-left square of the ship
     * @param row    the 0-based row number of the upper-left square of the ship
     * @param direction the orientation of the ship
     * @param shipType the class of the ship
     * @return true iff the entirety of the ship is within the bounds of the game board
     */
    public static boolean shipInBounds(int height, int width, int column, int row, Direction direction,
            ShipType shipType) {
        int[][] squares = squaresShipSpans(column, row, direction, shipType);

        // Check if each square the ship spans is within the boundaries of the board
        for (int i = 0; i < shipType.length(); i++) {
            if (squares[i][0] >= width || squares[i][1] >= height) {
                return false;
            }
        }

        // If no square was out of the boundaries, all the squares were in  bounds.
        return true;
    }

    /**
     * Determine whether a shot is within the game board.
     *
     * @param height the number of rows    in the game board
     * @param width  the number of columns in the game board
     * @param column the 0-based column number of the shot
     * @param row    the 0-based row number of the shot
     * @return true iff the shot is within the bounds of the game board
     */
    public static boolean shotInBounds(int height, int width, int column, int row) {
        // check whether column coordinate is between 0 and width - 1, inclusive,
        // and row coordinate is between 0 and height - 1, inclusive
        boolean inBounds = true;
        if (column < 0 || column >= width) {
            inBounds = false;
        }
        if (row < 0 || row >= height) {
            inBounds = false;
        }
        return inBounds;
    }
}

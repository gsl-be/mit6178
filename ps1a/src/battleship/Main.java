/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static final int BOARD_ROWS    = 10;
    public static final int BOARD_COLUMNS = 10;

    /**
     * Convert the square's letter, representing a column on the board, to a 0-based column number.
     *
     * @param square the square's coordinate (e.g., "B7")
     * @return the 0-based column number, numbered from left to right
     */
    public static int squareToColumnNumber(String square) {
        // TODO implement this function
        throw new UnsupportedOperationException("Main.squareToColumnNumber() unimplemented");
    }

    /**
     * Convert the square's number, representing a row on the board, to a 0-based row number.
     *
     * @param square the square's coordinate (e.g., "B7")
     * @return the 0-based row number, numbered from top to bottom
     */
    public static int squareToRowNumber(String square) {
        // TODO implement this function
        throw new UnsupportedOperationException("Main.squareToRowNumber() unimplemented");
    }

    /**
     * Determine the coordinates of the squares that the given ship spans
     *
     * @param shipColumn    the 0-based column number of the upper-left square of the ship
     * @param shipRow		the 0-based row number of the upper-left square of the ship
     * @param shipDirection the orientation of the ship
     * @param shipType the class of the ship
     * @return 2d integer array of coordinates of squares that the ship spans
     */
//    public static int[][] squaresShipSpans(int shipColumn, int shipRow, Direction shipDirection,
//        ShipType shipType) {
//        throw new UnsupportedOperationException("Main.squaresShipSpans() unimplemented");
//    }

    /**
     * Determine whether the shot would hit the ship.
     *
     * @param shotColumn 	the 0-based column  number of the shot
     * @param shotRow		the 0-based row		number of the shot
     * @param shipColumn    the 0-based column  number of the upper-left square of the ship
     * @param shipRow		the 0-based row		number of the upper-left square of the ship
     * @param shipDirection the orientation of the ship
     * @param shipType the class of the ship
     * @return true iff the shot intersects with some part of the ship
     */
    public static boolean isHit(int shotColumn, int shotRow, int shipColumn, int shipRow,
        Direction shipDirection, ShipType shipType) {
        // TODO implement this function

        throw new UnsupportedOperationException("Main.isHit() unimplemented");
    }

    /**
     * Determine whether a ship position is within the game board.
     *
     * @param height the number of rows    in the game board
     * @param width  the number of columns in the game board
     * @param column the 0-based column number of the upper-left square of the ship
     * @param row	 the 0-based row number of the upper-left square of the ship
     * @param direction the orientation of the ship
     * @param shipType the class of the ship
     * @return true iff the entirety of the ship is within the bounds of the game board
     */
    public static boolean shipInBounds(int height, int width, int column, int row,
        Direction direction, ShipType shipType) {
        // TODO implement this function
        throw new UnsupportedOperationException("Main.shipInBounds() unimplemented");
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
        // TODO implement this function
        throw new UnsupportedOperationException("Main.shotInBounds() unimplemented");
    }

    /**
     * Print the board to the console.
     *
     * Separate each square from others in its row with the character '|'.
     * Mark each hit square with 'X' and each missed square with 'O'.
     * We're assuming all spaces are either a hit or a miss.
     * @param board the 2d boolean array to be printed containing true's (hits) and false's (misses)
     */
    public static void printBoard(boolean[][] board) {
        // TODO implement this function
        throw new UnsupportedOperationException("Main.printBoard() unimplemented");
    }

    // DO NOT EDIT BELOW THIS COMMENT
    //
    // All code below this comment is used to check your answers
    // if you modify it, it may produce incorrect results, and you may
    // get wrong answers!

    private static int allAssertions = 0;
    private static int failedAssertions = 0;

    /**
     * Checks that the two values are equal.
     *
     * If not, prints out a message along with the current backtrace to help debugging.
     * @param expected the expected value (correct answer)
     * @param actual   the actual value (produced by your function)
     * @param equals   a binary predicate for comparing values
     * @param toString a function for printing out values
     */
    private static <T> void assertEquals(T expected, T actual, BiPredicate<T, T> equals, Function<T, String> toString) {
        allAssertions++;
        if (!equals.test(expected, actual)) {
            failedAssertions++;
            System.err.printf("Expected %s, got %s\n",
                    toString.apply(expected),
                    toString.apply(actual));
            new Exception().printStackTrace();
        }
    }

    /**
     * Checks that the two values are equal.
     *
     * Equivalent to:
     * {@code assertEquals(expected, actual, (x, y) -> x.equals(y), x -> x.toString())}
     */
    private static <T> void assertEquals(T expected, T actual) {
        assertEquals(expected, actual, (x, y) -> x.equals(y), x -> x.toString());
    }

    /**
     * Returns the contents of whatever is printed by some code.
     *
     * Side effect: prevents the contents from actually being printed to stdout.
     *
     * @param code a piece of code to execute
     * @param data a piece of data to provide {@code code}
     * @return any text printed by {@code code.accept(data)} to {@code System.out}
     */
    private static <T> String captureOutput(Consumer<T> code, T data) {
        PrintStream old = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        code.accept(data);

        System.setOut(old);

        return output.toString();
    }

    public static void main(String[] args) {
        // USED FOR GRADING DO NOT EDIT

        /*
         * squareLetterToColumnNummber
         */
        assertEquals(0, squareToColumnNumber("A4"));
        assertEquals(5, squareToColumnNumber("F7"));
        assertEquals(9, squareToColumnNumber("J2"));

        /*
         * squareNumberToRowNumber
         */
        assertEquals(0, squareToRowNumber("B1"));
        assertEquals(6, squareToRowNumber("J7"));
        assertEquals(9, squareToRowNumber("G10"));

        /*
         * squaresShipSpans
         */
        int[][] expected1 = new int[][] { {0, 0}, {1, 0}, {2, 0}, {3, 0} };
        int[][] actual1 = squaresShipSpans(0, 0, Direction.HORIZONTAL, ShipType.BATTLESHIP);
        assertEquals(expected1, actual1, Arrays::deepEquals, Arrays::deepToString);

        int[][] expected2 = new int[][] { {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4} };
        int[][] actual2 = squaresShipSpans(0, 0, Direction.VERTICAL, ShipType.CARRIER);
        assertEquals(expected2, actual2, Arrays::deepEquals, Arrays::deepToString);

        /*
         * isHit
         */

        assertEquals(true, isHit(0, 0, 0, 0, Direction.HORIZONTAL, ShipType.CARRIER));

        assertEquals(true, isHit(1, 0, 0, 0, Direction.HORIZONTAL, ShipType.CARRIER));

        assertEquals(false, isHit(2, 1, 0, 0, Direction.HORIZONTAL, ShipType.CARRIER));

        assertEquals(false, isHit(4, 0, 0, 0, Direction.VERTICAL, ShipType.BATTLESHIP));

        assertEquals(false, isHit(1, 0, 0, 0, Direction.VERTICAL, ShipType.BATTLESHIP));

        assertEquals(true, isHit(5, 4, 3, 4, Direction.HORIZONTAL, ShipType.BATTLESHIP));

        /*
         * shipInBounds
         */

        assertEquals(true, shipInBounds(10, 10, 0, 0, Direction.HORIZONTAL, ShipType.CARRIER));
        assertEquals(false, shipInBounds(10, 10, 7, 7, Direction.HORIZONTAL, ShipType.CARRIER));

        assertEquals(true, shipInBounds(10, 10, 3, 6, Direction.VERTICAL, ShipType.BATTLESHIP));
        assertEquals(false, shipInBounds(10, 10, 7, 8, Direction.HORIZONTAL, ShipType.BATTLESHIP));

        /*
         * shotInBounds
         */

        assertEquals(true, shotInBounds(10, 10, 0, 0));
        assertEquals(false, shotInBounds(10, 10, 10, 10));
        assertEquals(true, shotInBounds(10, 10, 6, 7));

        /*
         * printBoard
         */

        boolean[][] board1 = {
            { true,  true,  true,  false, false },
            { true,  false, false, true,  false },
            { false, false, false, true,  true  },
            { false, true,  true,  true,  false },
            { false, false, false, false, false },
        };
        String[] expectedLines1 = new String[] {
            "X|X|X|O|O",
            "X|O|O|X|O",
            "O|O|O|X|X",
            "O|X|X|X|O",
            "O|O|O|O|O",
        };
        String[] actualLines1 = captureOutput(Main::printBoard, board1).split("\r?\n|\r");
        assertEquals(expectedLines1, actualLines1, Arrays::deepEquals, Arrays::deepToString);

        boolean[][] board2 = {
            { true,  true,  true,  true,  true  },
            { true,  false, true,  true,  false },
            { false, false, false, false, false },
            { false, true,  false, true,  false },
            { false, false, false, false, false },
        };
        String[] expectedLines2 = new String[] {
            "X|X|X|X|X",
            "X|O|X|X|O",
            "O|O|O|O|O",
            "O|X|O|X|O",
            "O|O|O|O|O",
        };
        String[] actualLines2 = captureOutput(Main::printBoard, board2).split("\r?\n|\r");
        assertEquals(expectedLines2, actualLines2, Arrays::deepEquals, Arrays::deepToString);

        if (failedAssertions == 0) {
            System.out.println("All tests passed!");
        } else {
            System.out.printf("Failed %d/%d tests\n", failedAssertions, allAssertions);
        }
    }

}

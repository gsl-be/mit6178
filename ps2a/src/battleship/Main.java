/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Main {
    
    // DO NOT EDIT BELOW THIS COMMENT
    //
    // All code below this comment is used to check your answers
    // if you modify it, it may produce incorrect results, and you may
    // get wrong answers!
    private static int allAssertions = 0;
    private static int failedAssertions = 0;
    private static String FIRE_SHOT_ERROR = "Shot was out of bounds. Please try again.\n";

    /**
     * Checks that the two values are equal.
     *
     * If not, prints out a message along with the current backtrace to help debugging.
     * @param label    a name for this test
     * @param expected the expected value (correct answer)
     * @param actual   the actual value (produced by your function)
     * @param equals   a binary predicate for comparing values
     * @param toString a function for printing out values
     */
    private static <T> void assertEquals(String label, T expected, T actual, BiPredicate<T, T> equals,
            Function<T, String> toString) {

        allAssertions++;
        if (equals.test(expected, actual)) {
            System.err.printf("Test \"%s\" passed.\n", label);
        } else {
            failedAssertions++;
            System.err.printf("Test \"%s\" failed: expected %s, got %s\n", label, toString.apply(expected),
                    toString.apply(actual));
            new Exception().printStackTrace();
        }
    }

    /**
     * Checks that the two values are equal.
     *
     * Equivalent to:
     * {@code assertEquals(label, expected, actual, (x, y) -> x.equals(y), x -> x.toString())}
     *
     * @param label    a name for this test
     * @param expected the expected value (correct answer)
     * @param actual   the actual value (produced by your function)
     */
    private static <T> void assertEquals(String label, T expected, T actual) {
        assertEquals(label, expected, actual, (x, y) -> x.equals(y), x -> x.toString());
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
    private static String captureOutput(Runnable code) {
        PrintStream old = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        code.run();

        System.setOut(old);

        return output.toString();
    }

    public static void main(String[] args) {
        /*
         * If you would like, write your own tests here.
         */
        
        // USED FOR GRADING DO NOT EDIT BELOW

        /*
         * Constructing ships and board
         */

        int[] submarineCoords = { 2, 0 };
        Ship submarine = new Ship(ShipType.SUBMARINE, Direction.VERTICAL, submarineCoords);

        int[] destroyerCoords = { 0, 0 };
        Ship destroyer = new Ship(ShipType.DESTROYER, Direction.HORIZONTAL, destroyerCoords);

        Ship[] ships = { submarine, destroyer };
        Board board = new Board(ships, 3, 3);

        /*
         * Board.fireShot Tests
         */
        board.fireShot("A1");
        SquareState[][] actualBoard1 = board.getBoardState();
        SquareState[][] expectedBoard1 = {
            { SquareState.HIT, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.CLEAN, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.CLEAN, SquareState.CLEAN, SquareState.CLEAN },
        };
        assertEquals("testFireShot1", expectedBoard1, actualBoard1, Arrays::deepEquals, Arrays::deepToString);

        String actualError1 = captureOutput(() -> {
            board.fireShot("D1");
        });
        String expectedError1 = FIRE_SHOT_ERROR;
        assertEquals("testFireShotError1", expectedError1, actualError1);
        SquareState[][] actualBoard2 = board.getBoardState();
        SquareState[][] expectedBoard2 = {
            { SquareState.HIT, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.CLEAN, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.CLEAN, SquareState.CLEAN, SquareState.CLEAN },
        };
        assertEquals("testFireShot2", expectedBoard2, actualBoard2, Arrays::deepEquals, Arrays::deepToString);
        
        board.fireShot("A2");
        SquareState[][] actualBoard3 = board.getBoardState();
        SquareState[][] expectedBoard3 = {
            { SquareState.HIT, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.MISS, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.CLEAN, SquareState.CLEAN, SquareState.CLEAN },
        };
        assertEquals("testFireShot3", expectedBoard3, actualBoard3, Arrays::deepEquals, Arrays::deepToString);

        board.fireShot("C3");
        SquareState[][] actualBoard4 = board.getBoardState();
        SquareState[][] expectedBoard4 = {
            { SquareState.HIT, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.MISS, SquareState.CLEAN, SquareState.CLEAN },
            { SquareState.CLEAN, SquareState.CLEAN, SquareState.HIT },
        };
        assertEquals("testFireShot4", expectedBoard4, actualBoard4, Arrays::deepEquals, Arrays::deepToString);

        /*
         * Board.checkShipsSunk Tests
         */
        boolean actualSunk1 = board.checkShipsSunk();
        boolean expectedSunk1 = false;
        assertEquals("testCheckShipsSunk1", expectedSunk1, actualSunk1);

        board.fireShot("B1");
        boolean actualSunk2 = board.checkShipsSunk();
        boolean expectedSunk2 = false;
        assertEquals("testCheckShipsSunk2", expectedSunk2, actualSunk2);

        board.fireShot("C1");
        boolean actualSunk3 = board.checkShipsSunk();
        boolean expectedSunk3 = false;
        assertEquals("testCheckShipsSunk3", expectedSunk3, actualSunk3);

        board.fireShot("C2");
        boolean actualSunk4 = board.checkShipsSunk();
        boolean expectedSunk4 = true;
        assertEquals("testCheckShipsSunk4", expectedSunk4, actualSunk4);

        /*
         * Board.printShots Tests
         */
        String actualPrint1 = captureOutput(() -> {
            board.printShots();
        });
        String expectedPrint1 = 
            "XXX\n" +
            "O-X\n" +
            "--X\n";
        assertEquals("testPrintShots1", expectedPrint1, actualPrint1);
        
        board.fireShot("A3");
        String actualPrint2 = captureOutput(() -> {
            board.printShots();
        });
        String expectedPrint2 =
            "XXX\n" +
            "O-X\n" +
            "O-X\n";
        assertEquals("testPrintShots2", expectedPrint2, actualPrint2);
        
        /*
         * Board.printShips Tests
         */
        String actualPrint3 = captureOutput(() -> {
            board.printShips();
        });
        String expectedPrint3 =
            "OOO\n" +
            "--O\n" +
            "--O\n";
        assertEquals("testPrintShips1", expectedPrint3, actualPrint3);
        
        int[] submarine2Coords = {0, 0};
        Ship submarine2 = new Ship(ShipType.SUBMARINE, Direction.VERTICAL, submarine2Coords);

        int[] destroyer2Coords = {1, 1};
        Ship destroyer2 = new Ship(ShipType.DESTROYER, Direction.HORIZONTAL, destroyer2Coords);

        Ship[] ships2 = {submarine2, destroyer2};
        Board board2 = new Board(ships2, 3, 3);
        
        String actualPrint4 = captureOutput(() -> {
            board2.printShips();
        });
        String expectedPrint4 =
            "O--\n" +
            "OOO\n" +
            "O--\n";
        assertEquals("testPrintShips2", expectedPrint4, actualPrint4);

        if (failedAssertions == 0) {
            System.out.println("All tests passed!");
        } else {
            System.out.printf("Failed %d/%d tests\n", failedAssertions, allAssertions);
        }
    }
}

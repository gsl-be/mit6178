/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private interface Code {
        public void run() throws Exception;
    }

    // DO NOT EDIT BELOW THIS COMMENT
    //
    // All code below this comment is used to check your answers
    // if you modify it, it may produce incorrect results, and you may
    // get wrong answers!
    private static int numTests = 0;
    private static int numFailed = 0;

    /**
     * Runs a block of test code.
     *
     * If the test code throws an exception, catches it and prints out the
     * stack trace. Test failure is indicated by an exception; code that runs
     * to completion with no exception is considered a passed test.
     *
     * @param label a name for this test
     * @param code  a block of code to run
     */
    private static void test(String label, Code code) {
        numTests++;
        try {
            code.run();
            System.out.printf("Test \"%s\" passed.\n", label);
        } catch (Exception e) {
            numFailed++;
            System.out.printf("Test \"%s\" failed.\n", label);
            e.printStackTrace();
        }
    }

    /**
     * Checks whether two values are equal.
     *
     * <ul>
     * <li>Two primitives are equal if they are {@code ==}.
     * <li>Two Objects are equal if they are {@code .equals()}.
     * <li>Two arrays are equal if they are {@code Arrays.deepEquals()}.
     * </ul>
     *
     * @param a a value
     * @param b a value
     * @return true iff a and b are "equal"
     */
    private static boolean smartEquals(Object a, Object b) {
        if (a == null || b == null) {
            return a == null && b == null;
        }
        Class<?> klass = a.getClass();
        if (b.getClass() != klass) {
            throw new IllegalArgumentException("different types");
        }

        if (klass.isArray()) {
            Object[] foo = new Object[Array.getLength(a)];
            Object[] bar = new Object[Array.getLength(b)];
            for (int i = 0; i < foo.length; i++) {
                foo[i] = Array.get(a, i);
            }
            for (int i = 0; i < bar.length; i++) {
                bar[i] = Array.get(b, i);
            }
            return Arrays.deepEquals(foo, bar);
        } else {
            return a.equals(b);
        }
    }

    /**
     * Returns a nice representation of a value.
     *
     * Most things are printed out with {@code .toString()}.
     *
     * Strings themselves are printed out after quoting and escaping special
     * characters.
     *
     * Arrays are printed out using {@code Arrays.deepToString()}
     *
     * @param value a value
     * @return useful string value of {@code value}
     */
    private static String smartToString(Object value) {
        if (value instanceof String) {
            String escaped = ((String) value)
                .replace("\\", "\\\\")
                .replace("\'", "\\\'")
                .replace("\"", "\\\"")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\f", "\\f");
            return "\"" + escaped + "\"";
        } else if (value.getClass().isArray()) {
            Object[] array = new Object[Array.getLength(value)];
            for (int i = 0; i < array.length; i++) {
                array[i] = Array.get(value, i);
            }
            return Arrays.deepToString(array);
        }

        return value.toString();
    }

    /**
     * Asserts that the two values are equal.
     *
     * @param expected the expected value (correct answer)
     * @param actual   the actual value (produced by your function)
     * @throws AssertionError if the two values are not equal
     */
    private static <T> void assertEquals(T expected, T actual) {
        if (!smartEquals(expected, actual)) {
            String msg = String.format("expected %s, got %s", smartToString(expected), smartToString(actual));
            throw new AssertionError(msg);
        }
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
    @SuppressWarnings("unused")
    private static String captureOutput(Code code) {
        PrintStream old = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            code.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(old);
        }

        return output.toString();
    }

    private static InputStream stringToStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * If you would like, write your own tests here.
         */

        // USED FOR GRADING DO NOT EDIT BELOW

        // TODO tests

        
        test("Getters for IO", () -> {
            InputStream testIn = stringToStream("test");
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();
            IO testIO = new IO(testIn, testOut);

            BufferedReader testBR = testIO.getIn();
            assert(testBR != null);
            assertEquals(testBR, testIO.getIn());


            PrintStream testPS = testIO.getOut();
            assert(testPS != null);
            assertEquals(testPS, testIO.getOut());
        });

        test("IO input stream wrapping", () -> {
            byte[] buf = "this\nis\na\ntest\n".getBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            IO testIO = new IO(bais, System.out);

            BufferedReader reader = testIO.getIn();
            assertEquals("this", reader.readLine());
            assertEquals("is", reader.readLine());
            assertEquals("a", reader.readLine());
            assertEquals("test", reader.readLine());
            assertEquals(null, reader.readLine());
        });

        test("IO output stream wrapping", () -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IO testIO = new IO(System.in, baos);
            PrintStream out = testIO.getOut();

            out.print("hello\n");
            out.printf("%d %d %d\n", 10, 20, 30);
            out.flush();

            String expected = "hello\n10 20 30\n";
            assertEquals(expected, baos.toString());
        });


        test("test game 1", () -> {
            String aliceShips =
                "A2 v\n" +
                "B1 v\n" +
                "C1 v\n" +
                "D1 v\n" +
                "E1 v\n";
            String aliceShots = "A1\nA2\nA3\nA4\nA5\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nD1\nD2\nD3\nE1\nE2\n";
            InputStream aliceIn = stringToStream(aliceShips + aliceShots);
            ByteArrayOutputStream aliceOut = new ByteArrayOutputStream();
            IO aliceIO = new IO(aliceIn, aliceOut);

            String bobShips =
                "A1 v\n" +
                "B1 v\n" +
                "C1 v\n" +
                "D1 v\n" +
                "E1 v\n";
            String bobShots = "A2\nA3\nA4\nA5\nA6\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nD1\nD2\nD3\nE1\nE2\n";
            InputStream bobIn = stringToStream(bobShips + bobShots);
            ByteArrayOutputStream bobOut = new ByteArrayOutputStream();

            IO bobIO = new IO(bobIn, bobOut);

            System.out.println("Running the game...");
            new Game("Alice", "Bob", aliceIO, bobIO).run();

            System.out.println("Writing Alice's output to test/alice1.out...");
            try (OutputStream outFile = new FileOutputStream("test/alice1.out")) {
                aliceOut.writeTo(outFile);
            }

            System.out.println("Writing Bob's output to test/bob1.out...");
            try (OutputStream outFile = new FileOutputStream("test/bob1.out")) {
                bobOut.writeTo(outFile);
            }

            BufferedReader aliceReader = new BufferedReader(new StringReader(aliceOut.toString()));
            BufferedReader bobReader = new BufferedReader(new StringReader(bobOut.toString()));

            test("Output to Alice's screen", () -> {
                List<String> expected = Files.readAllLines(new File("test/alice1.txt").toPath());
                List<String> actual = aliceReader.lines().collect(Collectors.toList());
                assertEquals(expected, actual);
            });

            test("Output to Bob's screen", () -> {
                List<String> expected = Files.readAllLines(new File("test/bob1.txt").toPath());
                List<String> actual = bobReader.lines().collect(Collectors.toList());
                assertEquals(expected, actual);
            });
        });

        test("test game 2", () -> {
            String aliceShips =
                "A1 v\n" +
                "B1 v\n" +
                "C1 v\n" +
                "D1 v\n" +
                "E1 v\n";
            String aliceShots = "A1\nA2\nA3\nA4\nA5\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nD1\nD2\nD3\nE1\nE2\n";
            InputStream aliceIn = stringToStream(aliceShips + aliceShots);
            ByteArrayOutputStream aliceOut = new ByteArrayOutputStream();
            IO aliceIO = new IO(aliceIn, aliceOut);

            String bobShips =
                "A1 v\n" +
                "B1 v\n" +
                "C1 v\n" +
                "D1 v\n" +
                "E1 v\n";
            String bobShots = "A1\nA2\nA3\nA4\nA5\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nD1\nD2\nD3\nE1\nE2\n";
            InputStream bobIn = stringToStream(bobShips + bobShots);
            ByteArrayOutputStream bobOut = new ByteArrayOutputStream();
            IO bobIO = new IO(bobIn, bobOut);

            System.out.println("Running the game...");
            new Game("Alice", "Bob", aliceIO, bobIO).run();

            System.out.println("Writing Alice's output to test/alice2.out...");
            try (OutputStream outFile = new FileOutputStream("test/alice2.out")) {
                aliceOut.writeTo(outFile);
            }

            System.out.println("Writing Bob's output to test/bob2.out...");
            try (OutputStream outFile = new FileOutputStream("test/bob2.out")) {
                bobOut.writeTo(outFile);
            }

            BufferedReader aliceReader = new BufferedReader(new StringReader(aliceOut.toString()));
            BufferedReader bobReader = new BufferedReader(new StringReader(bobOut.toString()));

            test("Output to Alice's screen", () -> {
                List<String> expected = Files.readAllLines(new File("test/alice2.txt").toPath());
                List<String> actual = aliceReader.lines().collect(Collectors.toList());
                assertEquals(expected, actual);
            });

            test("Output to Bob's screen", () -> {
                List<String> expected = Files.readAllLines(new File("test/bob2.txt").toPath());
                List<String> actual = bobReader.lines().collect(Collectors.toList());
                assertEquals(expected, actual);
            });
        });

        test("test game 3", () -> {
            String aliceShips =
                "A1 v\n" +
                "B1 v\n" +
                "C1 v\n" +
                "D1 v\n" +
                "E1 v\n";

            String aliceShots = "A1\nA2\nA3\nA4\nA5\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nD1\nD2\nD3\nE1\nE2\n";
            InputStream aliceIn = stringToStream(aliceShips + aliceShots);
            ByteArrayOutputStream aliceOut = new ByteArrayOutputStream();
            IO aliceIO = new IO(aliceIn, aliceOut);

            String bobShips =
                "B3 v\n" +
                "C4 h\n" +
                "D5 v\n" +
                "G1 h\n" +
                "J8 v\n";
            String bobShots = "A1\nA2\nA3\nA4\nA5\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nD1\nD2\nD3\nE1\nE2\n";
            InputStream bobIn = stringToStream(bobShips + bobShots);
            ByteArrayOutputStream bobOut = new ByteArrayOutputStream();
            IO bobIO = new IO(bobIn, bobOut);

            System.out.println("Running the game...");
            new Game("Alice", "Bob", aliceIO, bobIO).run();

            System.out.println("Writing Alice's output to test/alice3.out...");
            try (OutputStream outFile = new FileOutputStream("test/alice3.out")) {
                aliceOut.writeTo(outFile);
            }

            System.out.println("Writing Bob's output to test/bob3.out...");
            try (OutputStream outFile = new FileOutputStream("test/bob3.out")) {
                bobOut.writeTo(outFile);
            }

            BufferedReader aliceReader = new BufferedReader(new StringReader(aliceOut.toString()));
            BufferedReader bobReader = new BufferedReader(new StringReader(bobOut.toString()));

            test("Output to Alice's screen", () -> {
                List<String> expected = Files.readAllLines(new File("test/alice3.txt").toPath());
                List<String> actual = aliceReader.lines().collect(Collectors.toList());
                assertEquals(expected, actual);
            });

            test("Output to Bob's screen", () -> {
                List<String> expected = Files.readAllLines(new File("test/bob3.txt").toPath());
                List<String> actual = bobReader.lines().collect(Collectors.toList());
                assertEquals(expected, actual);
            });
        });

        // just in case
        System.err.flush();
        System.out.flush();

        if (numFailed == 0) {
            System.out.println("All tests passed!");
        } else {
            System.out.printf("Failed %d/%d tests\n", numFailed, numTests);
        }
    }
}

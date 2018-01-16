/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */

package battleship;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * IO represents an input and output stream combined.
 */
class IO {
    /**
     * The constant STDIO is an IO that represents the default console input
     * and output pair. Use it for a player who is interacting with the game
     * through the console.
     */
    public static final IO STDIO = new IO(System.in, System.out);

    private final BufferedReader in;
    private final PrintStream out;

    /**
     * Construct a new IO.
     *
     * @param in an input stream
     * @param out an output stream
     */
    public IO(InputStream in, OutputStream out) {
        // DONE implement this constructor
    	this.in = new BufferedReader(new InputStreamReader(in));
    	this.out = new PrintStream(out);

    }

    /**
     * Returns the input or BufferedReader of the IO object
     * @return the BufferedReader object that gives the input
     */
    public BufferedReader getIn() {
        // DONE implement this method
        return this.in;
    }

    /**
     * Returns the output or PrintStream of the IO object
     * @return the PrintStream object that gives the output
     */
    public PrintStream getOut() {
        // DONE implement this method
        return this.out;
    }
}

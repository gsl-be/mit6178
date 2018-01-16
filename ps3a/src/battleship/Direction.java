/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

public enum Direction {
    HORIZONTAL, VERTICAL;

    /**
     * Parse a string as a direction.
     *
     * Any string beginning with "h" or "H" is treated as HORIZONTAL.
     * Any string beginning with "v" or "V" is treated as VERTICAL.
     *
     * @param input a string representing a direction
     * @return the Direction specified by {@code input}
     * @throws IllegalArgumentException if {@code input} is not valid
     */
    public static Direction parseDirection(String input) {
        String inputLower = input.toLowerCase();
        if (inputLower.startsWith("h")) {
            return HORIZONTAL;
        } else if (inputLower.startsWith("v")) {
            return VERTICAL;
        }

        throw new IllegalArgumentException("invalid direction: " + inputLower);
    }
}

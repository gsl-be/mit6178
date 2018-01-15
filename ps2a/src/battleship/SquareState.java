/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

public enum SquareState {
    /*
     * Types of state a coordinate can be in. At the beginning of the game, all
     * squares are Clean (including squares with ships) Once a coordinate is
     * shot at, it transitions to Hit if there was a ship there, or Miss, other
     * wise
     */
    HIT, MISS, CLEAN
}

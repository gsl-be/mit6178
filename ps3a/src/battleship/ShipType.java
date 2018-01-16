/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

/**
 * Represents a class of ship in the Battleship game.
 *
 * There are five classes of ship: battleship, carrier, cruiser, destroyer, submarine.
 */
public enum ShipType {
    CARRIER, BATTLESHIP, CRUISER, SUBMARINE, DESTROYER;

    /**
     * Calculates the length of this ship type.
     *
     * Each ship type has the following length, as per
     * {@link https://en.wikipedia.org/wiki/Battleship_(game)#Description}:
     *
     *     ------------+--------
     *      Ship type  | Length
     *     ------------+--------
     *      Carrier    |      5
     *      Battleship |      4
     *      Cruiser    |      3
     *      Submarine  |      3
     *      Destroyer  |      2
     *
     * @return the length of this type of ship. If not valid ShipType, return 0
     */
    public int length() {
        int length;
        switch (this) {
        case BATTLESHIP:
            length = 4;
            break;
        case CARRIER:
            length = 5;
            break;
        case CRUISER:
            length = 3;
            break;
        case SUBMARINE:
            length = 3;
            break;
        case DESTROYER:
            length = 2;
            break;
        default:
            length = 0;
            break;
        }
        return length;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

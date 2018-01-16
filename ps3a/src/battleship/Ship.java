/**
 * Battleship Problem Set
 * Copyright (c) 2017 6.178 Course Staff
 */
package battleship;

public class Ship {
    /*
     * The Ship class represents a ship to be placed on a board
     */

    // These are member variables that are
    // only accessible within the class.
    // They can be used to keep track of info
    // for a particular ship.
    private final ShipType shipType;
    private final Direction direction;
    private final int[] coordinate;
    private final int length;

    /**
     * Constructor for Ship. We initialize the object with a
     * given shipType and use this to calculate info about this
     * ship Object
     *
     * @param inputShipType the ShipType of this ship
     * @param inputDirection the direction of this ship
     * @param inputCoordinate the upper left coordinate of this ship
     */
    public Ship(ShipType inputShipType, Direction inputDirection, int[] inputCoordinate) {

        this.shipType = inputShipType;
        this.direction = inputDirection;
        this.coordinate = inputCoordinate;
        this.length = shipType.length();
    }

    /**
     * Returns the 0-based upper-left coordinate of the ship
     * in the form {column, row}
     *
     * @return the upper left coordinate of this ship
     */
    public int[] getCoordinate() {
        return this.coordinate;
    }

    /**
     * Returns the integer length of the ship
     *
     * @return length of this ship
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Returns the ShipType of this ship
     *
     * @return the type of this ship
     */
    public ShipType getShipType() {
        return this.shipType;
    }

    /**
     * Returns the Direction of the ship
     *
     * @return Direction of this ship
     */
    public Direction getDirection() {
        return this.direction;
    }
}

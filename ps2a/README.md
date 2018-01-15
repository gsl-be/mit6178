# 6.178 Problem Set 2 - Let's Get Classy! Introducing Board & Ship Classes

This problem set will give you more practice with methods and classes by expanding on our code so far. Please do not change any method signatures (parameters or names for any of the methods we ask you to implement). Also please don't edit the `Main` class. If you need to, please only add lines above this comment:
```
/*
 * If you would like, write your own tests here.
 */
// USED FOR GRADING DO NOT EDIT BELOW
```
Please refer to problem set 1 for instructions on running tests and reading stack traces.

## About Battleship
Battleship is a game that involves 2 players that have __two 10 by 10 boards__ and __five ships__ each. These 5 ships are the Carrier (5), the Battleship (4), the Cruiser (3), the Submarine (3), and the Destroyer (2). The number in parentheses next to the name represents how long the ship is or how many squares the ship spans.

<div align="center"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Battleship_game_board.svg/1000px-Battleship_game_board.svg.png"></div>

One board records a player's ships and their opponent's shots. One board records the shots the player has taken. From the image above, you can see that each square has a letter and a number associated with it representing its column and row respectively. For example, "A1" represents the upper-left square in the corner.

The game starts when both players have finished placing all their ships on their board. Ships must be placed either horizontally or vertically. Then, the players take turns to announce the square in their opponent's board which is to be shot at. The opposing player will then announce if that shot at the target square was a hit or a miss. A ship is considered sunk when every square it spans is hit. The game continues until one player's ships are all sunk.

If you are not familiar with Battleship or want to know more, please reference the [Wikipedia article](https://en.wikipedia.org/wiki/Battleship_(game)).

## Another Enum
`SquareState` (found in `SquareState.java`) is an enum we are adding to organize board state. It has three values:

1. `HIT`, which indicates a position on the board which has been hit
2. `MISS`, which indicates a position on the board which has been fired on but was a miss
3. `CLEAN`, which indicates a position that has not been fired on

## Classes in the Problem Set

We have introduced two new classes for this problem set: the `Ship` class found in `Ship.java` and the `Board` class found in `Board.java`. As explained in class, a class is the template for an object. We can create any number of objects from a class, and they will all have the same types of properties, although the values of those individual properties may be different (two `Ship` objects will both have a `direction` attribute (accessible through `getDirection()`) but what is returned for each object may be different (one `HORIZONTAL` and the other `VERTICAL`, for instance).

### `Ship` Class
`Ship` represents a ship placed on the board. We have implemented this class for you as an example. It has four methods:

- `getCoordinate()`
- `getLength()`
- `getShipType()`
- `getDirection()`

which allow you to access properties of a given `Ship` object.

### `Board` Class
A `Board` represents the 2D game board for a single player. It is responsible for keeping track of the ships placed, as well as what points have been fired on by the opponent. This class contains many methods you implemented in problem set 1 (the starting code contains staff implementations of these methods), as well as many new methods you will implement for this problem set. These are explained below.

## Problem 1: `Board` Constructor
As explained in class, a constructor is a special method which is called every time a new instance of the class is created. There are two main things we want to do when initializing a new board:

1. Place all the ships
2. Mark all the points on the board as `CLEAN`

These are represented using two instance variables (also called "members", "fields", or "properties"):

- `boardState`, which is of type `SquareState[][]`. This variable represents a 2d array of the `SquareState` enum (`HIT`, `MISS`, `CLEAN`) such that `boardState[i][j]` represents the state of row i and column j. 

- `boardShips`, which is of type `boolean[][]`, and keeps track of ship locations
on the board such that `boardShips[i][j]` is `true` if that location is one of the coordinates spanned by a ship and `false` otherwise. 

Since these variables are associated with an object, we can change them from within the class methods. __IMPORTANT:__ note that `boardState` and `boardShips` are indexed with the row first, then the column.

We have done step 1 (Place all the ships) for you. All the ships are marked on the board by first initializing the variable `boardShips` to an appropriately sized array, then marking all positions as false. We then use the helper method `addShipToBoard(Ship ship)` to mark all positions each ship takes up as `true` on `boardShips`. If any ships are out of bounds or overlap, `addShipToBoard(Ship ship)` will throw an exception.

__TODO:__ Complete the `Board` constructor in `Board.java` by initializing the `boardState` variable of `Board` (refer to line 27) to a new array of the size of the board, with each element having a `SquareState` of `CLEAN`.

## Problem 2: `getBoardState()`
__TODO:__ Complete the `getBoardState()` method in `Board.java`. This will return a value of type `SquareState[][]`, representing the current state of the board. Hint: Look at the methods in `Ship.java` (like `getShipType()`) for examples.

## Problem 3: `fireShot()`
__TODO:__ Complete the `fireShot()` method in `Board.java` which updates the `boardState` variable by marking the point of the shot fired as `HIT` if there was a ship at that location or `MISS` other wise. Make sure the shot is in bounds. If the shot is not in bounds, please print "Shot was out of bounds. Please try again." Hint: Use the method `shotInBounds()` to check if the shot was in the bounds of the board. This was a method from the previous pset, but we have implemented this for you already.

__Note:__ If you are failing the test labeled __testFireShotError1__, it’s most likely because you are on a Windows machine and using `System.out.println()`. When you print the error that we tell you to print, use `System.out.print()` with an explicit `“\n”` character at the end of the error message.

If you want to know more about why: [`System.out.println()`](https://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html#println()) uses the system’s `line.separator` property, which for Windows machines, is typically `\r\n` instead of `\n` (see [here](https://en.wikipedia.org/wiki/Newline)). Therefore, when our tests check if the Strings are equal there’s a discrepancy with the newline character, so your test will fail.

If there are no ships on a 3x3 board, we would expect the following behavior:

```
Board board = new Board({}, 3, 3);
board.fireShot(“A1”);
```
Output:
```
boardState: {
	{"MISS", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"}
}
```

If there was a ship at that location only (pretending there was a single point ship), we would have expected:
```
Board board = new Board(ships, 3, 3);
board.fireShot(“A1”);
```
Output:
```
boardState: {
	{"HIT", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"}
}
```

(__Note:__ You will not actually see the output for boardState unless you print it out for testing, this is just an illustration of how the variable has changed.)


## Problem 4: `checkShipsSunk()`
__TODO:__ Complete the `checkShipsSunk()` method in `Board.java` which checks each ship position on the
board and sees if that position has a `HIT` state. If all ship positions are `HIT`, then the method should return `true`. Otherwise, the method should return `false`.

For example, if we have one horizontal destroyer at "A1" on a 3x3 board, we expect the following behavior based on the given boardStates:

```
Board board = new Board(ships, 3, 3);
board.fireShot(“A1”);

boardState: {
	{"HIT", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"}
}

board.checkShipsSunk();
```
Output: `false`


```
Board board = new Board(ships, 3, 3);
board.fireShot(“A1”);
board.fireShot(“B1”);

boardState: {
	{"HIT", "HIT", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"}
}

board.checkShipsSunk();
```
Output: `true`


## Problem 5: `printShots()`
__TODO:__ Complete the `printShots()` method in `Board.java`. This is similar to the `printBoard()` method from problem set 1 except we will also be be considering if a square has not been fired on. This will basically be printing a string form of `boardState` in which "X" represents a hit, "O" represents a miss, and "-" represents a clean position. The end of each row should end with the newline character: "\n".

Examples

```
boardState: {
	{"HIT", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"}
}

board.printShots();
```

Console Output: 
```
						X--
						---
						---
```
(which is represented by the string: `"X--\n---\n---\n"` )


```
boardState: {
	{"HIT", "HIT", "CLEAN"},
	{"CLEAN", "CLEAN", "CLEAN"},
	{"CLEAN", "MISS", "MISS"}
}
board.checkShipsSunk();
```

Console Output:
```
						XX-
						---
						-OO
```

## Problem 6: `printShips()`
__TODO:__ Complete the `printShips()` method in `Board.java`. This will print the ships on the board, in which a position with a ship should be marked with a "O", and "-" otherwise. The end of each row should end with the newline character: "\n".

Examples

```
boardShips: {
	{ true, true, false },
	{ false, false, false },
	{ false, false, false }
}

board.printShips();

```
Console Output:
```
						OO-
						---
						---
```
(which is represented by the string: `"OO-\n---\n---\n"` )


```
boardShips: {
	{true, true, false},
	{false, false, false},
	{true, true, true}
}

board.printShips();
```
Console Output:
```
						OO-
						---
						OOO
```

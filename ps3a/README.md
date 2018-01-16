# 6.178 Problem Set 3 - Interactive Game Interface

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

## Special Notes about Problem Set 3 Tests

Some of the tests involve quite large input and output. Since it is unwieldy to report "expected ..., got ..." on the console for such large amounts of text, the tests are instead file-based. The **expected output** for each of the tests can be found in files `test/alice1.txt`, `test/bob1.txt`, `test/alice2.txt`, etc.

When you run the tests, the program will write the corresponding **actual output** for each test to files `test/alice1.out`, `test/bob1.out`, `test/alice2.out`, etc. You can use the `git-diff` subcommand to examine the differences between the two files:

```sh
git diff -b --no-index -- test/alice1.txt test/alice1.out
```

The `git-diff` subcommand will report which lines are different between the two files, so that should be helpful in tracking down incorrect outputs.

## New Classes

### `IO` class

`IO` is a class that represents the combination of an input source and an output stream. Effectively, it is used to represent the input and output for a single player. It has a `getIn()` method that retrieves a `BufferedReader` that you can use to read the input from the player, and a `getOut()` method that retrieves a `PrintStream` to which you can print messages to the player.

There is also a static class constant called `STDIO` that represents the `IO` pair of `System.in` and `System.out`, which are the standard console input/output sources, for a player who is interacting with the game through the terminal or through the console in Eclipse (see "Playing the Game" below).

### `Game` class

The `Game` class represents a two-player Battleship game, with each player having a `Board`, an `IO`, and name. The game is started by calling the `.run()` method, which prompts the players to set up their boards and then takes turns having each player fire a shot onto the opponent's board.

The `Game` class also contains a static `main()` method that you can use to run the game from the console: it will prompt you for the names of the two players and then start the game with both players on the same terminal.

## Problem 1: Implement `IO` Class
__TODO:__ Implement the `IO` class in `IO.java`. You must first implement the constructor which takes in two parameters (an InputStream `in` and an OutputStream `out`) and stores appropriate values into the fields `in` (a variable of type `BufferedReader`) and `out` (a variable of type `PrintStream`). Then implement the two getters that simply return the two fields that the `IO` class stores.

See the Java API documentation for the `BufferedReader`, `InputStreamReader`, and `PrintStream` classes, specifically the available constructors.

## Problem 2: Implement the `Game(String, String)` constructor
`Game` has two constructors: one that takes in the names of the two players and the `IO` objects corresponding to their input/output streams, and one that should default to the normal console input and output streams, `System.in` and `System.out`.

__TODO:__ Implement the second constructor in `Game` according to the specification comment.

## Problem 3: Implement `Game.setIO()`
__TODO:__ Implement the instance method `Game.setIO()`. This method takes the 0-based index of the player in question and sets the `Game` object's `in` and `out` fields as the player in question's `BufferedReader` and `PrintStream` respectively.

## Problem 4: Implement `Game.setup()`
The setup takes the 0-based index of the player in question. For that player, we prompt the player to place each of their five ships (Carrier, Battleship, Cruiser, Submarine, Destroyer). The player will input the ships as `[square] [direction]` like `"A3 v"`. The `[square]` is the square of the upper-left corner of the ship when placed on the board, and `[direction]` is either the character "v" for vertical or "h" for horizontal placement of the ship. You must read the input, parse the square and direction, and finally add the ship to the player's board.

At the end of this README are notes about manually testing your implementation.

__TODO:__ Implement the instance method `Game.setup()` according to the specification comment.

You can manually test your implmentation by running `Game.java`.

__Note:__ Keep the `break;` statement at the end of the section of code within the try. This will break out of the while loop when the try has run to completion (i.e. when no errors occur)

## Problem 5: Implement `Game.turn()`
The instance method `turn()` within `Game` runs the current player's turn then sets the current player to the opponent.Within the try statement, take the current player's input (or the square the player wishes to fire upon), check if that square is a hit or a miss and print `"HIT!\n"` or `"MISS\n"` respectively.

__TODO:__ Implement the instance method `Game.turn()`.

__Note:__ You will find `Board.fireShot()` helpful, and please note that we have changed the specs of how `Board.fireShot()` has been changed to return `true` if the shot was a hit and `false` if the shot was a miss. Like the last problem, keep the `break;` statement at the end of the section of code within the try. This will break out of the while loop when the try has run to completion (i.e. when no errors occur).

## Problem 6: Implement `Game.isOver()`
__TODO:__ Implement the instance method `isOver()` in the `Game` class. `isOver()` should return `true` if one of the players has sunk all of their opponent's ships. You may want to refer to the methods in `Board`.

Similarly to `super()` used to invoke a superclass constructor, you can use `this(args...)` to invoke another constructor in the current class. You can use this syntax in the second constructor to avoid reimplementing much of the
same behavior that already exists in `Game(String, String, IO, IO)`.

# Playing the Game

To run the game, you can run `Game` as a Java Application in Eclipse, or if you prefer, you can run the program from the command line (Terminal or Git Bash):

```sh
cd ~/Documents/path/to/ps3
java -classpath bin battleship.Game
```

## Setup

The game will prompt you to place your ships at the beginning of the game. Ship placement is specified by a square and a direction, separated by a space, like so:

```
B3 vertical
```

The square specifies the top-left corner of the ship, and the direction specifies the orientation of the ship on the game board. Ships may not overlap, and must be fully within the bounds of the game board.

The direction may be specified more concisely using `v` or `h` instead of `vertical` or `horizontal` (or any string starting with `v` or `h`, respectively, case-insensitive).

## Gameplay

The game will prompt you for a square to fire upon. You will be shown the current state of your knowledge of the opponent's game board, i.e., the shots you have made, and which were hits (`X`) and which were misses (`O`).

Enter a square (e.g., `C4`), and the game will report whether your shot was a hit or a miss. Unlike real Battleship, this simplified version does __not__ report when you have completely sunk an enemy ship.

When one of the players has lost all of their ships, the other player wins, and the game ends.

##  testing

`Game.setup()` testing

Below are expected inputs and outputs for running the game from the `Game.java` main method. 

```
Output: 
Name of player 1: 
Input: 
Alice
Output: 
Name of player 2:
Input: 
Bob
Output: 
Alice, set up your fleet.
----------
----------
----------
----------
----------
----------
----------
----------
----------
----------
Place your carrier (5):
Input:
A2 h
Output:
----------
OOOOO-----
----------
----------
----------
----------
----------
----------
----------
----------
Place your battleship (4):
Input:
B4 h
Output:
----------
OOOOO-----
----------
-OOOO-----
----------
----------
----------
----------
----------
----------
Place your cruiser (3):
Input:
D8 h
Output:
----------
OOOOO-----
----------
-OOOO-----
----------
----------
----------
---OOO----
----------
----------
Place your submarine (3):
Input: 
B2 h
Output:
java.lang.IllegalArgumentException: Ships overlap. Please check ship positions.
Invalid placement. Try again.
----------
OOOOO-----
----------
-OOOO-----
----------
----------
----------
---OOO----
----------
----------
Place your submarine (3):
Input: J8 h
Output:
java.lang.IllegalArgumentException: Ships not in bounds of the board. Please check ship positions.
Invalid placement. Try again.
----------
OOOOO-----
----------
-OOOO-----
----------
----------
----------
---OOO----
----------
----------
Place your submarine (3):
Input:
J2 v
Output:
----------
OOOOO----O
---------O
-OOOO----O
----------
----------
----------
---OOO----
----------
----------
Place your destroyer (2):
Input:
A1 h
Output:
OO--------
OOOOO----O
---------O
-OOOO----O
----------
----------
----------
---OOO----
----------
----------
Bob, set up your fleet.
----------
----------
----------
----------
----------
----------
----------
----------
----------
----------
Place your carrier (5):

```

It should be similar when entering the ships for the other player. Until you implement `Game.turn()` the rest of the game won't work.

For `Game.turn()` and `Game.isOver()` you can type sample inputs and make sure you can correctly fire on ships, and that your game prints the status (such as hit, miss, and out of bounds messages) and make sure once you sink all ships on a board you get the game over message and and game ends.

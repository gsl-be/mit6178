# 6.178 Lecture #3

## The Pset
* Pset #1 extended until Sunday (1/15) 11:59pm.
* Pset #2 To be released on Monday (1/16) due Sunday 22nd 11:59pm.
* [Instructions on Cloning & Submitting Psets](https://github.mit.edu/6178-2017/pset-instructions)
* You will be given a suite of tests but there are also a suite of hidden tests that staff will use to give you additional feedback. You will not be penalized if you do not pass the hidden suite of tests.
* You will pass the pset if you pass all the tests that were given to you.
* We have a schedule of our classes on Stellar [here](https://stellar.mit.edu/S/course/6/ia17/6.178/courseMaterial/topics/topic1/resource/Schedule_(1)/Schedule_(1).pdf)
* If you didn't get a repo for pset #1, please let us know by filling out [this Google form](https://goo.gl/forms/TDHtVW5f3ncpWImE2).
* Please sign up for the [Piazza](https://piazza.com/class/ixbeg17oz79vm) to get updated on pset revisions and other things.

## Structure of a Java Class

```java
package myproject

import java.util.Array

public class CoolClass {

	//Class Body

}
```

At the top of the class file is the package declaration and import statments
Then the class is defined by its visibility and class name

```java
package myproject

import java.util.Array

public class CoolClass {

	//Other methods and declarations

	public static void main(String[] args) {
		//This code runs when the program is run
	}
	
	//Other methods and declarations

}
```

Code that you want to run should go in main (example calling other methods, printing etc.) 
except for special code that we'll see later.

## Methods (cont'd)

Methods are attached to the class they are in. The are called by typing the name, 
for example 'myMethod' and parenthesis where we put the required arguments, for example
'myMethod(arg1, arg2, arg3...)'. Types (for what is returned and parameters) are only 
specified in the method declaration. When actually calling the method, we don't include
the types f the method or the arguments. 

A method can return a value. To access the value it returns, we can assign it to a variable
with the appropriate type. For instance, if 'myStringMethod' returns a String if you pass in 
String 'name', you can get the method value via 
```
String inputName = "Famien";
String methodResult = myStringMethod(inputName);\
```

Methods outside of a class (from a different file) are called by typing the class name
and then '.' and then the method. If they are from a different package, we must also
import the package. For example:

```java
public class Ship {
	public static int[] getCoordinates(ShipType type, int[] position) {
		// method code
	}
}

// in another file

public class Game {
	public static void main(String[] args) {
	ShipType ourShipType = ShipType.Carrier;
	int[] ourPosition = {1,0};
	
	int[] ourCoordinates = Ship.getCoordinates(ourShipType, ourPosition);
	}
}
```

## Stack Traces

Stack traces allow us to track down errors. The are ordered in order of methods being called
which led to the displayed error.

```java
Exception in thread "main" java.lang.UnsupportedOperationException: Main.squareToColumnNumber() unimplemented
	at battleship.Main.squareToColumnNumber(Main.java:26)
	at battleship.Main.main(Main.java:183)
```

at line 183:

```java
assertEquals(0, squareToColumnNumber("A4"));
```

at line 26
```java
throw new UnsupportedOperationException("Main.squareToColumnNumber() unimplemented");
```



## Review of Arrays and Strings
Arrays are technically objects, so can be instantiated via the `new` keyword (we'll learn more about this later)



## Scope and Method Calling
Variables and methods in Java are scoped. That means you can only access them within 
a certain context. You can read more about visibility and usability of variables and methods [here](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html).

Parameters and variables defined within a method, are only accessible within the method. 

Example:

```
public static String coolMethod(String name) {
	String cool = "is cool.";
	
	return name + cool;
}
```

the `cool` variable is only accessible within the method, and the return `name + cool` is only accessible if you call the method, and store the returned value.

```java
package myproject

import java.util.Array

public class CoolClass {

	public static int publicNum = 4;

	public static void main(String[] args) {
		//This code runs when the program is run
	}
	
	public static String coolMethod(String name) {
		String cool = "is cool.";
		if (cool.length() > publicNum) { //publicNum is accessible here
			System.out.println("cool is longer than publicNum");
		}
	
	return name + cool;
}
	
	//Other methods and declarations

}
```
## Exercises

1. Implement `letterCount`

2. Implement `average` 

3. Declare and implement `coldestPlace`




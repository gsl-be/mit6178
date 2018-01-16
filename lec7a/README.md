# 6.178 Lecture #7 - Exceptions and I/O

## Exceptions
__Exceptions__ are events that occur during the execution of a program that disrupt the normal flow of the program. Good programs are written such that these exceptional cases clearly communicate bugs and are handled appropriately. Exceptions can result due to programmer error or due to user error.

In the Java language, an exception is an __object__ that wraps an error event that occurs within a method and contains:
* information about the error including its type
* the state of the program when the error occurred
* optionally other custom information

We have already seen some examples of exceptions so far.  Some of these include:
* `ArrayIndexOutOfBoundsException` for trying to access an element of an array outside of the array's range
* `NullPointerException` for trying to call a method on a `null` object
* `ArithmeticException` for arithmetic errors like division by zero
* `UnsupportedOperationException` for unimplemented methods in the psets

### Exceptions for Signaling Bugs
------
Most of the exceptions we've seen so far have occurred in order to indicate __bugs__. Reading the stack trace of an exception can help you locate and fix bugs. These exceptions exist to signal programmer errors.
### Example 1
```java
package examples;

public class Example1 {
  
  public static int getLength(String word) {
    return word.length();
  }
  
  public static void main(String[] args) {
    
    //Illustrates ArrayIndexOutOfBoundsException
    int[] array = new int[5];
    for (int i=0; i <= array.length; i++) {
      array[i] = i+2;
    }
    
    //Illustrates NullPointerException
    String word = null;
    getLength(word);
  }
}
```
When running the program, the following stack trace is produced:
```java
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 5
    at examples.Example1.main(Example1.java:14)
```
At line 14:
```java
array[i] = i + 2;
```
Since the array is of length 5, 4 is the greatest index to the array. Thus the exception is thrown when trying to access the 5th index of the array.

If we comment out lines 12-15, then when running the program again we get the following stack trace:
```java
Exception in thread "main" java.lang.NullPointerException
    at examples.Example1.getLength(Example1.java:6)
    at examples.Example1.main(Example1.java:19)
```
At line 19:
```java
getLength(word);
```
At line 6:
```java
return word.length();
```
`getLength` is called on the String `word`, but since `word` is `null`, the `length` method throws an exception.

These kinds of exceptions that are used to signal bugs are called __unchecked__ exceptions. Unchecked exceptions occur at the time of execution, so they are also called __runtime__ exceptions. They are ignored at the time of compilation.

### Exceptions for User Error and Special Results
------
We can write programs such that certain inputs cause exceptions to be thrown. These exceptions can improve the clarity and structure of code that produces special results.

When handling special results, an alternative to throwing an exception is returning special values, such as -1, 0, or `null`. However, this can cause two problems. First, it becomes tedious to check certain return values. Second, it's easy to forget to check for these return values, exposing you to possible bugs. When using exceptions you can get help from the compiler in reminding you to handle special cases.

### Example 2
```java
package examples;

import java.util.HashMap;
import java.util.Map;

class Gradebook {
  private Map<String, Double> grades = new HashMap<>();
  
  public void addGrade(String student, double grade) {
    grades.put(student, grade);
  }
  
  public double lookup(String student) throws NoStudentFoundException {
    if (grades.containsKey(student)) {
      return grades.get(student);
    } else {
      throw new NoStudentFoundException("Student does not exist");
    }
  }
}


public class Example2 {
  public static void main(String[] args) {
    Gradebook gradeBook = new Gradebook();
    gradeBook.addGrade("Mike", 95.0);
    gradeBook.addGrade("Susan", 87.0);
    
    String[] students = {"Susan", "Tony", "Mike"};
    
    for (String student : students) {
      try {
        double grade = gradeBook.lookup(student);
        System.out.println(student + " has grade: " + grade);
      } catch (NoStudentFoundException e) {
        System.out.println(student + " is not in the gradebook");
      }  
    }
  }
}
```
In this example we have a gradebook with students and their numerical grades. The method `lookup` looks up a specified student in the gradebook. But what if that student is not in the gradebook? We could return an invalid grade, like a negative number, but this doesn't really make sense. So instead `lookup` throws an exception and the caller in the `main` method handles the exception with a `catch` clause. This allows us to specify an error message if that exception is thrown and continue executing our program.

### Example 3
```java
package examples;

import java.io.File;
import java.io.FileReader;

public class Example3 {
  
  public static void main(String[] args) {
    File file = new File("/file/not/found.txt");
    FileReader reader = new FileReader(file);
  }
}
```
If we try to compile the above code, we get the following error: 
```java
Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
  Unhandled exception type FileNotFoundException

  at examples.Example3.main(Example3.java:10)
```

`FileNotFoundException` is an example of a __checked__ exception. These exceptions occur at compile time and cannot simply be ignored. The programmer must handle these types of exceptions. If a method might throw a checked exception, this must be declared in the method signature. If a method calls another method that might throw a checked exception, it must either handle it in a `try`-`catch` or declare the exception itself. 

If you call a method that might throw a checked exception and forget to handle that exception, the compiler will reject your code. This is useful in ensuring that exceptions that are expected to occur are handled.

More documentation on the Exception class in java can be found [here](https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html).


## I/O

__I/O__ stands for __input/output__ and is the communication between an information processing system, such as a computer, and the outside world, possibly a human. __Inputs__ are signals or data received by the system, and __outputs__ are signals or data sent from it.

For example, a computer mouse takes human input for a computer, while a monitor displays computer output to a human.

Last lecture we began looking at implementing File I/O in Java. In that case files can be __read__ in order to provide input to a method. Files can then be __written__ with the output of a method.

In Problem Set 3 you will implement a battleship game that can be played from either the Eclipse console or the command line. In order to play the game, the console will output instructions telling you what to do. It will then wait for you to provide your input that you type, such as where to place your ships or where to shoot. It will then print out the result of your input, such as "Hit!" or "Miss!" or instructions for the next input.

### Basic I/O in Java

------

There are several Java classes that will be helpful to know for the final problem set. The `java.io` package contains many classes that programs can use to read and write data. More information on basic I/O in java can be found [here](http://docs.oracle.com/javase/tutorial/essential/io/). The complete documentation of the `java.io` package is linked [here](https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html).

### I/O Streams
An _I/O Stream_ represents an input source or an output destination. A stream is a sequence of data. A program uses an __input stream__ to read data from a source, one item at a time. A program uses an __ouput stream__ to write data to a destination, one item at a time.

### I/O from the Command Line
Programs can be run from the command line and can interact with the user through the command line. The Java platform supports this through __Standard Streams__.

Standard Streams read input from the keyboard and write output to the display. Java supports 3 Standard Streams:

1. Standard Input accessed through `System.in`
2. Standard Output accessed through `System.out`
3. Standard Error accessed through `System.err`

These streams are defined automatically and do not need to be opened. `System.out` and `System.err` are `PrintStream` objects. When catching exceptions, we often call `printStackTrace()` on the exception, which writes that information to Standard Error.

#### `InputStreamReader`

An InputStreamReader reads bytes from a byte stream and decodes them into characters to produce a character stream.

#### `BufferedReader`

A BufferedReader efficiently reads text from a character-input stream. Each time `read()` or `readLine()` is invoked on an InputStreamReader, bytes are read from the file, converted to characters, and then returned, which can be costly. Thus, it is useful to wrap a BufferedReader around an InputStreamReader. BufferedReader reads data from a buffer, an array in memory that holds data until it's read.

The following example will help to familiarize you on how to use these classes.

### Example 4
```java
package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Example4 {
  
  /**
   * Interact with the following program from the Eclipse console
   * 
   * Asks the user to enter a sentence and displays each word of that sentence
   * on a separate line
   */
  public static void main(String[] args) {
    PrintStream out = new PrintStream(System.out);
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    while (true) {
      out.println("Please enter a sentence");
      out.print("> ");
      try {
        String sentence = in.readLine();
        
        //exit the program if user hits enter with no sentence
        if (sentence.isEmpty()) {
          out.println("bye");
          return;
        }
        
        //splits sentence on whitespaces, creating an array of non-whitespace strings
        String[] words = sentence.split("\\s+");
        
        for (String word : words) {
          out.println(word);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
  }
}
```
In this example `out` represents the `PrintStream` output to the command line. `in` allows us to read input from the command line. The method `readLine()` throws an `IOException` if an I/O Error occurs, thus requiring the `try-catch` block.

## Exercise
Create a Gradebook program that can be used from the command line. From the command line the user will be able to enter grades, calculate their GPA, and show their enrollment. All code can be written within `lec7/src/exercise/Exercise.java`.

#### Problem 1: Implement `addGrade`
__TODO:__ Implement `addGrade` which puts `className` mapped to `grade` into the `grades` HashMap. It should throw a new `InvalidGradeException` when `grade` is not a valid grade on a 4.0 scale. Provide a helpful message to the user when `InvalidGradeException` is thrown. Hint: check out the method `lookup2` in `Example2.java` for a similar example.

#### Problem 2: Implement exiting the program
__TODO:__ In the `main` method exit the program if the user types enter without any input.

#### Problem 3: Implement displaying GPA
__TODO:__ In the `main` method call `calculateGPA` on `grades` and print out to the command line "Current GPA is: {gpa}" when the user inputs "gpa".

#### Problem 4: Throw `IllegalArgumentException`
__TODO:__ In the `main` method throw a new `IllegalArgumentException` if the user inputs a single word that is not "gpa" or "enrollment".

#### Problem 5: Implement adding a grade to the gradebook
__TODO:__ In the `main` method call `addGrade` on `grades` if `inputWords` is of length 2. Remember `inputWords` is an array of Strings, so you'll have to convert the grade value to a double. Once the grade is added to `grades`, print out to the command line "Added grade for {class name} to the gradebook".
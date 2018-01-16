# 6.178 Lecture #8 - Review of `if`/`else if`/`else`, Standard Library Classes, and Testing

Today is the final lecture for 6.178. Reminder that the third and final problem set is due __Sunday, January 29 at 11:59 PM__. We will have office hours __Sunday__ from __11 AM - 4 PM in 4-145__. Lecture 7 solutions are posted in the `lec7-solutions` repo found [here](https://github.mit.edu/6178-2017/lec7-solutions).

## Review of `if`/`else` and `if`/`else if`/`else`

First let's consider a single `if`/`else` statement. The `if` statement specifies a condition that evaluates to a boolean within the parentheses. If that condition evaluates to `true`, the code within the curly braces of the `if` statement is executed. Once the `if` code is executed, the program jumps to after the close of the `else` curly braces. However, if that condition evaluates to `false`, the code within the `if` curly braces is not executed, and the code within the `else` curly braces is executed. The program then continues on.

```java
1     if (some condition) {
2
3       // if some condition == true, this code runs
4       // after executing, jump to line 12
5
6     } else {
7    
8       // if some condition == false, this code runs
9       // after executing, continue onto line 12  
10 
11    }
12    // outside of the if/else
```

Now let's review an `if`/`else if`/`else` statement. Again, `if` specifies a condition that evaluates to a boolean within the parentheses. The code within the `if` curly braces is executed if that condition evaluates to true. Once that code executes, the program jumps to after the close of the `else` curly braces. If that condition evaluates to false, the program then goes to check the `else if` condition. If the condition within the `else if` parentheses is true, the code within those curly braces is run, and then jumps to after the close of the `else` curly braces. However, if that condition is also false, then the program executes code within the `else` curly braces.

```java
1     if (condition A) {
2       
3       // if condition A == true, this code runs
4       // after executing, jump to line 17
5  
6     } else if (condition B) {
7       
8       // if condition B == true, this code runs
9       // after executing, jump to line 17
10  
11    } else {
12
13      // if condition A == false and condition B == false, this code runs
14      // after executing, continue onto line 17
15  
16    }
17    // code outside of if/else if/else
```

What happens if we have an `if` statement without an `else`? If the condition within the `if` parentheses is true, then the code within the `if`'s curly braces runs. Otherwise, that code is skipped. In both cases when the condition evaluates to true and when the condition evaluates to false, the code immediately following the `if` block will be run.

```java
1     if (condition) {
2       
3       // if condition == true, this code runs
4       // after executing, continue onto line 7
5
6     }
7     // always runs, whether condition == true or condition == false
```

What gets tricky is when we have multiple `if` statements following each other. Let's examine the difference between having multiple `if` statements versus having multiple `if`/`else if` statements.

```java
1     if (condition A) {
2       
3       // if condition A == true, this code runs
4       // after executing, continue onto line 6
5  
6     } if (condition B) {
7       
8       // if condition B == true, this code runs
9       // after executing, continue onto line 11
10  
11    } if (condition C) {
12
13      // if condition C == true, this code runs
14      // after executing, continue onto line 17
15  
16    }
17
```
Here the code will check each condition A, B, and C, regardless of what the previous condition evaluated to. Note that if conditions A and B both evaluate to true, then both blocks of code, lines 2-5 and lines 7-10, will be run. If we insert an `else` statement after condition B and before condition C, then that `else` code will be executed if condition B evaluates to false. Regardless of whether condition B evaluates to true or false, condition C will be checked.

#### Example 1

```java
package examples;

public class Example1 {
    
    static char getLetterGrade(double percentage) {
        char letterGrade;
        if (percentage >= 90) {
            letterGrade = 'A';
        } if (percentage >= 80) {
            letterGrade = 'B';
        } if (percentage >= 70) {
            letterGrade = 'C';
        } if (percentage >= 60) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        return letterGrade;
    }
    
    public static void main(String[] args) {
        double percentage = 75.0;
        char letterGrade = getLetterGrade(percentage); // expected to be C
        System.out.println(letterGrade);
    }
}
```
The issue here is that 75 is >= 70 and also >= to 60, so `letterGrade` first gets set to 'C' and then gets updated to 'D' before it is returned. We can fix this bug by changing the 3 `if` statements after the first `if` to `else if` statements.

#### Example 2

```java
1     if (condition A) {
2       
3       // if condition A == true, this code runs
4       // after executing, skip to line 11
5  
6     } else if (condition B) {
7       
8       // if condition B == true, this code runs
9       // after executing, continue onto line 11
10  
11    } if (condition C) {
12
13      // if condition C == true, this code runs
14      // after executing, skip to line 22
15  
16    } else {
17
18      // if condition C == false, this code runs
19      // after executing, continue onto line 22  
20    
21    }
22    
```
Here the code first checks whether condition A is true or false. If it's true, then that block executes, and then skips to check condition C. If condition A is false, then the code checks if condition B is true or false. If condition B is true, then that block executes, and then moves onto check condition C. If condition B is false, then it skips to check condition C. If condition C is true, that block executes, and then skips to after the `else` block. If condition C is false, then the `else` block executes.

We can see what blocks of code run using the following truth table.

| Condition A | Condition B | Condition C | Lines of code that run |
|:-----------:|:-----------:|:-----------:|:----------------------:|
| T           | F           | F           |                        |
| T           | T           | F           |                        |
| T           | F           | T           |                        |
| T           | T           | T           |                        |
| F           | F           | F           |                        |
| F           | T           | F           |                        |
| F           | F           | T           |                        |
| F           | T           | T           |                        |

In order to test our work, you can play around with the `Example2.java` file.

Note, for all of the above examples, I have assumed that in each block of code, there are no `return` statements, since those would break from the clause and method, stopping the code from continuing on.

## Important Java Standard Library Classes

There are several important Java Standard Library Classes that provide helpful data structures for organizing more complex data and provide more functionality than arrays.

### `Iterable` Interface
Implementing this interface allows an object to be iterated over. For each element in the iterable, do something.

### `Collection` Interface
A collection represents a group of objects, known as its elements. Some collections may allow duplicates, others may not. Some collections may be ordered, others are unordered. Extends the `Iterable` interface.

### `List` Interface
An ordered collection, aka a sequence. The user of this interface can decide where in the list to insert each element and can access elements by their index or by searching the list for an element. Lists allow duplicate elements. Like arrays, lists are 0-indexed. Extends the `Collection` interface.

### `ArrayList` Class
An implementation of the `List` interface. What's great about an `ArrayList` is that it is resizable, in other words, unlike a normal array, you don't need to declare the size of the list when it is initialized. Some useful methods include:
* `add(e)`: appends `e` to the end of the list
* `add(index, e)`: inserts `e` at the specified index
* `clear()`: removes all elements from the list
* `contains(e)`: returns true if the list contains `e`, false otherwise
* `get(index)`: returns the element at the specified index
* `indexOf(e)`: returns the index of the first appearance of `e` in the list, -1 if it does not exist in the list
* `isEmpty()`: returns true if the list is empty, false otherwise
* `remove(index)`: remove the element at the specified index
* `remove(e)`: remove the first instance of `e` in the list, if it is present
* `size()`: returns the number of elements in the list

The complete `ArrayList` documentation can be found [here](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html).

#### Example 3
```java
package examples;

import java.util.ArrayList;
import java.util.Arrays;

public class Example3 {
    
    public static void main(String[] args) {
        
        ArrayList<String> noAnimals = new ArrayList<>();
        ArrayList<String> existingAnimals = new ArrayList<>(Arrays.asList("cow", "pig","horse"));
        
        System.out.println(existingAnimals);
        System.out.println(noAnimals);
        
    }
}
```

### `Set` Interface
A collection that contains no duplicate elements. Extends the `Collection` interface.

### `HashSet` Class
An implementation of the `Set` interface. Unlike a list, it does not maintain the order of elements. Some useful methods include:
* `add(e)`: adds `e` to the set if it is not already present
* `clear()`: removes all elements from the set
* `contains(e)`: returns true if `e` is in the set, false otherwise
* `isEmpty()`: returns true if the set is empty, false otherwise
* `remove(e)`: removes `e` from the set if it is present
* `size()`: returns the number of elements in the set

The complete `HashSet` documentation can be found [here](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html).

#### Example 4
```java
package examples;

import java.util.Arrays;
import java.util.HashSet;

class Student {
    
    public String name;
    public int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class Example4 {
    
    public static void main(String[] args) {
        
        HashSet<Student> emptyClass = new HashSet<Student>();
        if (emptyClass.isEmpty()) {
            System.out.println("No students yet");
        }
        
        Student student1 = new Student("Bob", 3);
        Student student2 = new Student("Lucy", 2);
        Student student3 = new Student("Greg", 1);
        HashSet<Student> javaClass = new HashSet<Student>(
                Arrays.asList(student1, student2, student3));
        
        for (Student student : javaClass) {
            System.out.println(student.name);
        }
        
    }
}
```

### `Map` Interface
Like a Python dictionary, this object maps keys to values. A map cannot contain duplicate keys and each key maps to at most one value. A map has 3 collection views:

1. a set of keys (set here because each key is unique)
2. a collection of values (collection here because values do not need to be unique)
3. a set of key-value pairs (set here because each key-value pair is unique)

### `HashMap` Class
An implementation of the `Map` interface. This does not maintain the order of elements. Some useful methods include:
* `clear()`: removes all mappings from the map
* `containsKey(key)`: returns true if the map contains a mapping for the specified key, false otherwise
* `containsValue(value)`: returns true if the map maps one or more keys to the value, false otherwise
* `get(key)`: returns the value to which the key is mapped, or null if the map does not contain the key
* `isEmpty()`: returns true if the map is empty, false otherwise
* `put(key, value)`: associates the specified key with the specified value in the map
* `remove(key)`: removes the mapping for the specified key, if present
* `size()`: returns the number of mappings in the map
* `keySet()`: returns the set of keys in the map
* `values()`: returns the collection of values in the map

The complete `HashMap` documentation can be found [here](https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html).

#### Example 5
```java
package examples;

import java.util.HashMap;

public class Example5 {
    
    /**
     * Creates a mapping of characters in a word to the frequencies at which they appear
     * in that word
     * i.e. word = "massachusetts" => {'m':1, 'a':2, 's':4, 'c':1, 'h':1, 'u':1, 'e':1, 't':2}
     * 
     * @param word a positive-length String
     * @return a mapping of characters in word mapped to their frequencies
     * 
     */
    public static HashMap<Character, Integer> getLetterFrequencies(String word) {
        // TODO
    }
    
    public static void main(String[] args) {
        
        HashMap<Character, Integer> wordMap = getLetterFrequencies("massachusetts");
        System.out.println(wordMap);
        
    }
}
```

## Testing

Testing your code is a major component taught in 6.031. This will provide you with a broad overview of why and how to test your code.

### The Importance of Testing
Testing is a method for _validating_ your code. That's why we provided you tests in your problem sets, so you could validate your implementations as you went along and so the graders could easily check your code. As a good programmer, you want to uncover bugs in your code in order to fix them and thereby increase your confidence that your code is right. A programmer not only needs to think about how to implement a given method, but also how to extensively test that method in order to prove that their implementation is sound. Software controls many important decisions with huge consequences, such as how and when to move large amounts of money, the mechanisms for launching a rocket into space, and recommending medical diagnoses. Software bugs in these instances could result in devastating losses. That's why there are jobs dedicated solely to testing software.

Software testing can be difficult since it's unlike physical systems and mathematical equations. It's impossible to test every possible input for a given method, and randomly choosing any input won't be helpful in identifying bugs unless that one specific input just happened to cause problems. Repeated testing also won't work, since unlike physical systems, there isn't a product lifespan and failure point at which the frequency of failure increases. A program might run as expected for hundreds of input values before hitting one boundary value that produces an incorrect result. Thus, careful consideration must be taken to build a high-coverage yet efficient test suite. As a tester your goal should be to make your code fail and not just assume that since your code makes sense, it is correct.

### Test-first Programming
If you leave testing until the end once you've finished implementing a method, it becomes much harder to validate your code. Since bugs can be anywhere in your code, this exposes you to many possible points of failure and can make debugging much more painful. This is why it's better to test your code as you develop it.

__Test-first programming__ is the idea of writing tests for methods even before implementing them. The work flow under this model is:

1. Write a specification for a method.
2. Write tests that match that specification.
3. Implement the method, running the tests as you go and debug. Once you pass all of your tests, supposing you wrote good tests, you're done.

Good specifications should provide you with enough information to write your tests. Along with the method signature, you know the inputs and input types, what the function should do, and the return type. Test-first programming is good because you write your tests before you know the details of your implementation, so you don't fit the tests to your specific implementation. Doing so exposes you to missing important edge cases. 

### How to test your code
There are several practices to keep in mind when testing your code. First, how should you select your test suite? It's a good idea to test both normal, more typical inputs and identify edge cases that would be good to test. For example, if we have a function that converts from Farenheit to Celsius, it might be a good idea to test a positive temperature, a negative temperature, 0, and freezing and boiling points.

When testing it is best to reduce your test's scope to as few failure points as possible. Thus it is best to test one method at a time. If a method calls another method, you should test both methods separately. This notion is called __unit testing__.

Running your code from the `main` method and manually inputting different values and checking the outputs using `print` statements is tedious and error-prone. __Automated testing__ provides us with the ability to run our tests and check their results automatically. _JUnit_ is the framework we will introduce today that allows you to write a test suite that can easily be run again and again. It is important to run your test suite after every change you make, whether you're fixing a bug or adding a new `if` statement, to verify that you haven't caused new problems. This is called __regression testing__.

### JUnit
JUnit is a popular Java unit-testing libary that is used in 6.031. JUnit test files are written like standard java files, having a class with methods. JUnit knows which test methods to call based on which ones are preceded with `@Test`. Even if a test fails, the others will run. 

JUnit test methods can make calls to `assertEquals(expected, actual)`, which asserts that `expected` and `actual` are equal and fails if they are not; `assertTrue(condition)` , which asserts that `condition` is true and fails if it's not; and `assertFalse(condition)`, which asserts that `condition` is false and fails if it's not.

It's best to familiarize ourselves with JUnit by looking at an example. `Example5Test.java` is a JUnit test file that we can use to test our `getLetterFrequencies` method in Example 5.

## Today's Exercise (the last one!)

You're going to create a `GroceryStore` class that sells `Food`, which is another class. Each `Food` object has a name and a price. The `GroceryStore` will maintain an inventory that keeps track of how much of each `Food` is in stock. You will write tests for and implement the methods `addToStore`, `canBuy`, and `buy`.

#### Problem 1: Create `Food` class
__TODO:__ In `Food.java` define 2 public fields for the `Food` class, namely a String `name` and double `price`. The only method you need to write is the constructor method.

#### Problem 2: Write tests
__TODO:__ Read the specs for the methods in `GroceryStore.java`. In `GroceryStoreTest.java` try to write a couple of `assert` statements for each method. There are some example `assert` statements already written. You can use the public method `getInventory()` to check the inventory when testing `addToStore` and `buy`. Note outside of the test method there are some `Food` objects defined, so you don't need to define them within each test method. Also note that for using `assertEquals` when comparing floating-point numbers, you need to provide a third argument. This third argument is the delta that the expected and actual values can be different by. In the given test I set this equal to 0.0001.

#### Problem 3: Implement `addToStore`
__TODO:__ Implement this method, which is used to update the `inventory` field. Don't forget to test along the way!

#### Problem 4: Implement `canBuy`
__TODO:__ Implement this method according to the spec.

#### Problem 5: Implement `buy`
__TODO:__ Implement this method that takes in a map of `Food` objects to quantity desired and returns the total cost if all of the items are in the `inventory` and decreases `inventory` accordingly, otherwise return 0. Hint: you just implemented a method that might be helpful here.

Don't forget to push your code at the end of lecture! We hope you enjoyed the class!


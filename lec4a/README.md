- [Announcements](#sec-1)
  - [Problem Set Deadlines](#sec-1-1)
  - [Office Hours](#sec-1-2)
  - [Problem Set Grades and Comments](#sec-1-3)
  - [Grading](#sec-1-4)
- [Objects](#sec-2)
  - [What are objects?](#sec-2-1)
  - [Examples of objects](#sec-2-2)
  - [Primitive vs. Reference (&ldquo;object&rdquo;) types](#sec-2-3)
    - [Which is which?](#sec-2-3-1)
    - [Semantics](#sec-2-3-2)
    - [Value vs. Pointer semantics: why does it matter?](#sec-2-3-3)
    - [`null`](#sec-2-3-4)
- [Classes](#sec-3)
  - [Fields](#sec-3-1)
  - [Methods](#sec-3-2)
  - [Constructors](#sec-3-3)
    - [Default Constructor](#sec-3-3-1)
  - [Access Modifiers](#sec-3-4)
  - [`static`](#sec-3-5)


# Announcements<a id="sec-1"></a>

## Problem Set Deadlines<a id="sec-1-1"></a>

Problem sets are now due Sundays at 11:59 p.m.

## Office Hours<a id="sec-1-2"></a>

We will hold additional office hours on Sundays 11 a.m. to 4 p.m., room 4-145.

## Problem Set Grades and Comments<a id="sec-1-3"></a>

Your problem set grade and comments will be pushed to your personal `ps1-<kerberos>` repository as a `grade.txt` file in the root directory of the repo. Please contact staff by making a private Piazza post if you do not find such a file in your repo by Thursday morning.

## Grading<a id="sec-1-4"></a>

The bar for passing this class is not &ldquo;passing all the tests&rdquo;, so **don&rsquo;t panic** if you aren&rsquo;t able to make all the tests pass by the deadline for any of the problem sets. Contact the staff by email or Piazza if you are concerned about your performance in the class.

# Objects<a id="sec-2"></a>

## What are objects?<a id="sec-2-1"></a>

Objects are data types that can have both **state** and **behavior**.

-   **State:** An object can have some amount of information in it. For example, a `String` with `n` characters in it knows its length (`n`) and each of the characters at positions `0` through `n - 1`.
-   **Behavior:** An object can have behavior attached to it. For example, a `String` knows how to compare itself to another `String`: when you call `s.equals(t)`, `s` will compare itself to `t` by checking that the two strings have the same length and the same characters at each position. When you call `s.equalsIgnoreCase(t)`, it will do the same thing, but ignoring character case.

## Examples of objects<a id="sec-2-2"></a>

-   `String`&rsquo;s are objects that represent character sequences.
-   All arrays are objects, even arrays of primitive types like `int[]`.
-   `System.out` is a `PrintStream` object representing your program&rsquo;s console output stream. When you call methods on it, such as `println()`, the output is displayed in the console in Eclipse, or in your terminal if you run your program from the command line.

## Primitive vs. Reference (&ldquo;object&rdquo;) types<a id="sec-2-3"></a>

### Which is which?<a id="sec-2-3-1"></a>

-   Here is a list of all of the primitive types in Java:
    -   `boolean`
    -   `byte`
    -   `short`
    -   `char`
    -   `int`
    -   `long`
    -   `float`
    -   `double`
-   Array types are reference types.
-   This is not a syntactic requirement, as Java **will allow** you to declare a class whose name does not begin with an uppercase letter, but in Java convention and throughout the standard library, the names of classes are capitalized, e.g.:
    -   `StringBuilder`
    -   `IOException`
    -   `File`

### Semantics<a id="sec-2-3-2"></a>

1.  Primitive Types

    Values of primitive types have a &ldquo;direct representation&rdquo; in memory.

    ```java
    int x = 3;
    int y = x; // y = 3
    ```

          x     y
        #---# #---#
        |   | |   |
        | 3 | | 3 |
        |   | |   |
        #---# #---#

2.  Reference Types

    Values of reference types &ldquo;point to&rdquo; the location of the actual object. For example:

    ```java
    int[] x = new int[] { 1, 2, 3, 4, 5 };
    int[] y = x;
    // the above line is *not* equivalent to int[] y = new int [] { 1, 2, 3, 4, 5 };
    ```

          x       y
        #---#   #---#
        |   |   |   |
        | | |   | | |
        | | |   | | |
        #-|-#   #-|-#
          |       |
          |      /
          |     /
          v    v
        #-----------#
        |#-#-#-#-#-#|
        ||1|2|3|4|5||
        |#-#-#-#-#-#|
        #-----------#

    The `new` operator **always** creates a totally new, never-before-seen object in your program.

    ```java
    int[] x = new int[] { 1, 2, 3, 4, 5 };
    int[] y = new int[] { 1, 2, 3, 4, 5 };
    ```

          x       y
        #---#   #---#      #-----------#
        |   |   |   |      |#-#-#-#-#-#|
        | | |   | -------->||1|2|3|4|5||
        | | |   |   |      |#-#-#-#-#-#|
        #-|-#   #---#      #-----------#
          |
          |
          |
          v
        #-----------#
        |#-#-#-#-#-#|
        ||1|2|3|4|5||
        |#-#-#-#-#-#|
        #-----------#

3.  Aliasing

    If you have more than one pointer to a given object, e.g., two local variables, they are said to be **aliased**. Aliasing can cause lots of confusion because if you mutate the object through one of the variables, and then inspect the other, it will seem to have mysteriously changed.

    ```java
    int[] x = new int[] { 1, 2, 3, 4, 5 };
    int[] y = x;

    x[2] = 9001;

    System.out.println(Arrays.toString(y)); // prints out [1, 2, 9001, 4, 5]
    ```

          x       y
        #---#   #---#
        |   |   |   |
        | | |   | | |
        | | |   | | |
        #-|-#   #-|-#
          |       |
          |      /
          |     /
          v    v
        #-----------------#
        |#-#-#-------#-#-#|
        ||1|2|3->9001|4|5||
        |#-#-#-------#-#-#|
        #-----------------#

### Value vs. Pointer semantics: why does it matter?<a id="sec-2-3-3"></a>

Below are a couple examples of ways in which your Java program may seem to behave unexpectedly, which are due to the differences between value and pointer semantics.

1.  Method arguments

    Consider the following snippet from `src/examples/Example1.java`.

    ```java
     6  private static int smallest(int[] data) {
     7      Arrays.sort(data);
     8      return data[0];
     9  }
    10
    11  private static int largest(int[] data) {
    12      Arrays.sort(data);
    13      return data[data.length - 1];
    14  }
    15
    16  private static void showMeTheData(int[] data) {
    17      int min = smallest(data);
    18      int max = largest(data);
    19      int first = data[0];
    20      int last = data[data.length - 1];
    21
    22      System.out.printf("min: %d, max: %d, first: %d, last: %d\n", min, max, first, last);
    23  }
    24
    25  public static void main(String[] args) {
    26      int[] data = new int[] { 19, 88, 4, 39, 10, 72 };
    27      showMeTheData(data);
    28  }
    ```

    We expect the output to be:

        min: 4, max: 88, first: 19, last: 72

    But instead we get:

        min: 4, max: 88, first: 4, last: 88

    This is because `Arrays.sort()` modifies the array in order to sort the elements into the correct order. When `data` is passed from `main()` to `showMeTheData()` to `smallest()` to `Arrays.sort()`, it is passed as a `reference` (&ldquo;pointer&rdquo;) to the original array object created in `main()` (on line 26).

    Since these all point to the same exact location in memory, `Arrays.sort()` writes over the original array, giving us the wrong answers for `first` and `last`.

2.  `==` vs. `.equals()`

    Reference semantics also impacts the way equality comparison works. If you envision a location in memory (such as a variable, or an array element, etc.) as just a slot in which you can put a primitive value or a pointer to a reference value, `==` just compares the contents of that slot.

    With primitive types:

    ```java
    char x = '%';
    char y = '%';

    boolean equal = (x == y); // true
    ```

           x     y
        #----# #----#
        |    | |    |
        | 37 | | 37 |
        |    | |    |
        #----# #----#

    With reference types:

    ```java
    int[] x = new int[] { 1, 2, 3, 4, 5 };
    int[] y = x;

    boolean equal = (x == y); // true
    ```

               x               y
        #------------#   #------------#
        |            |   |            |
        | 0x59e8f938 |   | 0x59e8f938 |
        | |          |   |  |         |
        #-|----------#   #--|---------#
          |     ------------/
          |    /
          |    |
          v    v
        #-----------#
        |#-#-#-#-#-#|
        ||1|2|3|4|5||
        |#-#-#-#-#-#|
        #-----------#

    ```java
    int[] x = new int[] { 1, 2, 3, 4, 5 };
    int[] y = new int[] { 1, 2, 3, 4, 5 };
    ```

               x           y
        #------------#   #------------#      #-----------#
        |            |   |            |      |#-#-#-#-#-#|
        | 0x59e8f938 |   | 0xa7b9ff20------->||1|2|3|4|5||
        | |          |   |            |      |#-#-#-#-#-#|
        #-|----------#   #------------#      #-----------#
          |
          |
          |
          v
        #-----------#
        |#-#-#-#-#-#|
        ||1|2|3|4|5||
        |#-#-#-#-#-#|
        #-----------#

    ```java
    int[] x = new int[] { 1, 2, 3, 4, 5 };
    int[] y = new int[] { 1, 2, 3, 4, 5 };

    boolean equal = (x == y); // false
    ```

    1.  `.equals()`

        `a.equals(b)` is a method call, which means it relies on the behavior specified by the class of `a` to determine whether `a` and `b` are equal. Typically this is implemented in such a way as to compare the &ldquo;contents&rdquo; of two objects, e.g.,

        ```java
        String a = "Hello, World!";
        String b = "Hello, " + "World!";

        boolean sameObject = (a == b); // false
        boolean equal = a.equals(b);   // true
        ```

### `null`<a id="sec-2-3-4"></a>

Recall that a reference-type value can be thought of as a slot with a pointer to the actual object. A **null reference** refers to a slot with no pointer, i.e., it is not pointing to any actual object. This is still a valid value for a reference type like `String`, even though there may be no `String` there!

Internally, this is represented with something like a memory address of zero, which is not a valid memory address so cannot refer to the location of an actual object.

You can assign `null` to variables and pass it around without any problems, but if you ever try to call a method on a `null` reference value, Java&rsquo;s runtime will throw a `NullPointerException`, telling you that you called a method on a `null` value.

# Classes<a id="sec-3"></a>

Classes are used to define the following:

-   A type of object
-   **State** (fields/properties/members) for objects of that type
-   **Behavior** (methods) for objects of that type

An example class might look like this:

```java
 1  package examples;
 2
 3  class Toaster {
 4      private String color;
 5
 6      private int length;
 7      private int width;
 8      private int height;
 9
10      private double dialSetting; // 0 to 10
11      private double wattage;
12
13      public static int numberOfToasters = 0;
14      public static int piecesOfToast = 0;
15      public static final int NUMBER_OF_COMPARTMENTS = 2;
16      public static String[] availableColors = new String[] { "chartreuse", "peach" };
17
18      public Toaster(int length, int width, int height, double wattage) {
19          this.length = length;
20          this.width = width;
21          this.height = height;
22          this.wattage = wattage;
23
24          this.color = availableColors[Math.random() > 0.5 ? 0 : 1];
25          this.dialSetting = Math.random() * 10.0;
26
27          numberOfToasters++;
28      }
29
30      public void makeToast() {
31          System.out.printf("I made a piece of toast with dimensions %d x %d x %d\n", length, width, height);
32          System.out.printf("It took %f minutes.\n", dialSetting);
33
34          double heatEnergy = wattage * (60 * dialSetting);
35          System.out.printf("It took %f J of electricity.  Hope you're happy.\n", heatEnergy);
36
37          piecesOfToast++;
38      }
39
40      public void describe() {
41          System.out.printf("I am a %s-colored toaster.\n", color);
42      }
43
44      public void setDial(double dialSetting) {
45          this.dialSetting = dialSetting;
46      }
47  }
48
49  public class Example2 {
50      public static void main(String[] args) {
51          Toaster braveLittleToaster = new Toaster(3, 4, 5, 1000);
52
53          braveLittleToaster.describe();
54          braveLittleToaster.makeToast();
55          braveLittleToaster.setDial(5);
56          braveLittleToaster.makeToast();
57
58          System.out.println(Toaster.numberOfToasters);
59          System.out.println(Toaster.piecesOfToast);
60      }
61  }
```

## Fields<a id="sec-3-1"></a>

Fields declare parts of the state of instances of the class. They are declared just like local variables.

```java
class Cat {
    String name;
    String favoriteFood;
    int age;
}
```

Each object that is an instance of `Cat` will have its own `name`, `favoriteFood`, and `age` values.

```java
Cat garfield = new Cat("Garfield", "Lasagna", 38);

System.out.println(garfield.name);         // Garfield
System.out.println(garfield.favoriteFood); // Lasagna
System.out.println(garfield.age);          // 38
```

## Methods<a id="sec-3-2"></a>

Method definitions in classes are used to define the methods that can be called on an object. Methods can take any number of parameters through the normal way, but they also receive an implicit `this` parameter, which is a reference to the instance of the class they were called on.

```java
 1  class Cat {
 2      // field definitions
 3
 4      public void introduceSelf(String toWhom) {
 5          System.out.printf("Hello, %s.\n", toWhom);
 6          System.out.printf("My name is %s, I am %d years old, and my favorite food is %s.\n",
 7                            this.name,
 8                            this.age,
 9                            this.favoriteFood);
10      }
11  }
12
13  Cat garfield = new Cat("Garfield", "Lasagna", 38);
14  garfield.introduceSelf("Mr. President");
15  // the above line prints the following:
16  // Hello, Mr. President.
17  // My name is Garfield, I am 38 years old, and my favorite food is Lasagna.
```

On line 13 above, the method `introduceSelf()` is called with `"Mr. President"` as the argument for `toWhom`, and the implicit `this` parameter refers to `garfield` inside of the method.

Recall that methods may also specify a return type other than `void`, so that they can return a value to the caller. E.g., a method called `getName()` might return a `String` whose value is the name of the cat.

Inside methods, you can refer to the fields of the object (`this`) without specifying `this.fieldName` each time, by writing:

```java
public void whoAmI() {
    System.out.printf("My name is %s.\n", name);
}
```

## Constructors<a id="sec-3-3"></a>

A constructor is a special method used when creating new instances of the class. Constructors are defined like normal methods, but omitting the return type and naming the method the same thing as the class, e.g.:

```java
class Cat {
    // field definitions

    public Cat(String name, String favoriteFood, int age) {
        this.name = name;
        this.favoriteFood = favoriteFood;
        this.age = age;
    }
}
```

Note that we **must** use the `this.fieldName` notation to set the values of the fields, because we could not refer to `name`, `favoriteFood`, and `age` in the body of the constructor (since they are the names of the parameters to the method, they would refer to the arguments the constructor was called with instead).

Constructors are called differently from normal methods. They power the `new` keyword, which creates new instances of classes:

```java
class Car {
    int wheels;

    public Car() {
        this.wheels = 4;
        System.out.println("Your car is done, sir/madam.");
    }
}

// Even Teslas have just four wheels
Car tesla = new Car(); // prints "Your car is done..."
```

### Default Constructor<a id="sec-3-3-1"></a>

If you don&rsquo;t define any constructors for a class, Java provides a default one. The default constructor does the following things (approximately):

-   Initializes each field to its default value:
    -   Fields of primitive types like `int` are initialized to zero.
    -   Fields of reference types like `String` are initialized to `null` (not &ldquo;empty&rdquo;).
-   Calls the superclass constructor (you&rsquo;ll learn about inheritance next time)

Default constructors can come in handy sometimes, but more often than not, you will want to define your own constructor, especially because the default constructor cannot be passed any arguments.

## Access Modifiers<a id="sec-3-4"></a>

You can specify what code is allowed to access the fields and methods of your class by using **access modifiers**. The access modifiers are, in decreasing order of access: `public`, `protected`, (no modifier), and `private`.

`public` causes fields and methods to be accessible from any part of the program.

`protected` causes fields and methods to be accessible from the current class, any code in the current package, and any code in subclasses of this class.

Fields and methods with no modifiers are accessible from any code in the current package.

`private` fields and methods are accessible from inside the current class only.

Violation of the Java access modifiers will result in a compilation error.

Below is a table that sums this all up (from [here](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html))):

| Modifier    | Class | Package | Subclass | World |
| public      | ✓     | ✓       | ✓        | ✓     |
| protected   | ✓     | ✓       | ✓        |       |
| no modifier | ✓     | ✓       |          |       |
| private     | ✓     |         |          |       |

## `static`<a id="sec-3-5"></a>

`static` is a confusing and poorly-named modifier in Java that causes a lot of grief for students. Basically, it names methods and fields that do not belong to *instances* of the class, but rather to *the class itself*. For example, static methods and fields in the `Cat` class are not related to any particular `Cat` object, nor can they be referred to from an instance like `garfield.getName()`, but are referred to instead from the class itself, e.g., `Cat.getSpeciesName()`.

The reason it&rsquo;s called `static` is a bit of a both deeply technical and legacy/historical discussion. Basically, `static` fields and methods exist at the start of the program, before any instances of objects have been created by `new` (before `main()` is run). In fact, Java will give you a compilation error if you try to access instance (non-static) methods and fields from within the body of a `static` method.

Since Java knows exactly how much space is required for all of the `static` methods and fields in your entire program (only one copy of each of them exists ever, in the entire lifetime of your program), it can allocate all of that memory right at the beginning of your program in one go (as it happens, this can be done by just allocating space for these objects in the program code itself).

This is referred to as **static** allocation (cf. [Wikipedia](https://en.wikipedia.org/wiki/Static_variable)), as opposed to **dynamic** memory allocation, which happens over the course of your program&rsquo;s execution, and which needs to be managed by the runtime, since Java does not know how much memory you will use, and when you will use it.

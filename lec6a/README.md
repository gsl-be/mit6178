# 6.178 Lecture #6 - More Practice

## Logistics
* Pset 2 due __Sunday 11:59pm__

## Interfaces Cont'd
As discussed, interfaces define a set of methods (names and signatures). They provide a contract, a way for programmers
to know what to expect from a given class. Even if different classes implement an interface differently, we can 
always interact with them in a standard way. 

### Example

```java
package lec6;

import java.util.List;

public interface Student {
    
    // Enter class grade on a 4.0 point scale
    public void enterClassGrade(String className, double grade);
    
    // get GPA on a 4.0 point scale
    public double getGPA();
    
    // get list of all classes student is registered for
    public List<String> getClassRegistration();
    
    // register new class
    public void registerClass(String className);
    
    // gets the residence name of the student
    public String getResidence();
    

}
```

```java
package lec6;

import java.util.List;

public class HighSchooler implements Student {


}
```

### Exercise
```java
package lec6;

public class Undergrad implements Student {

    //implement Student
}

```

### FILE IO

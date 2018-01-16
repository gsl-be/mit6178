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

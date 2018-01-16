package examples;

import java.util.HashMap;
import java.util.Map;

class Gradebook {
	private Map<String, Double> grades = new HashMap<>();
	
	public void addGrade(String student, double grade) {
		grades.put(student, grade);
	}
	
	public double lookup1(String student) {
		return grades.get(student);
	}
	
	public double lookup2(String student) throws NoStudentFoundException {
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
				double grade = gradeBook.lookup2(student);
				System.out.println(student + " has grade: " + grade);
			} catch (NoStudentFoundException e) {
				//System.out.println(e.getMessage());
				//e.printStackTrace();
				System.out.println(student + " is not in the gradebook");
			} 
			
		}
		
		
	}
}

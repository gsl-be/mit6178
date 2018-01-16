package exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

class GradeReport {
	
	//represents a map of class names to grades in those classes
	private HashMap<String, Double> grades;
	
	public GradeReport() {
		this.grades = new HashMap<String, Double>();
	}
	
	/**
	 * Adds a student's grade in a class to grades
	 * 
	 * @param className the name of the class
	 * @param grade the grade they received on a 4.0 scale
	 * @throws InvalidGradeException when grade is not in the correct range
	 */
	public void addGrade(String className, double grade) throws InvalidGradeException {
		if(grade > 4.0 || grade < 0.0) {
			throw new InvalidGradeException("Grades must be between 0.0 and 4.0.");
		} else {
			grades.put(className, grade);
		}
	}
	
	/**
	 * @return the student's GPA (all classes have the same weight)
	 */
	public double calculateGPA() {
		int numClasses = 0;
		double total = 0.0;
		for (double grade : this.grades.values()) {
			numClasses++;
			total += grade;
		}
		if (numClasses == 0) {
			return 0;
		} else {
			return total/numClasses;
		}
	}
	
	/**
	 * @return the student's classes and grades
	 */
	public Map<String, Double> getGrades() {
		return this.grades;
	}
}

public class Exercise {
	
	public static void main(String[] args) {
		GradeReport grades = new GradeReport();
		
		PrintStream out = new PrintStream(System.out);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		out.println("Welcome to the gradebook");
		
		while (true) {
			
			out.print("> ");
			try {
				String input = in.readLine();
				
				// DONE exit the program if user hits enter with no input
				if(input.isEmpty()) {
					out.println("Exiting Program.");
					return;
				}
				
				//splits sentence on whitespaces, creating an array of non-whitespace strings
				String[] inputWords = input.split("\\s+");
				
				if (inputWords.length == 1) {
					
					//get list of classes if input is enrollment
					if (inputWords[0].equals("enrollment")) {
						Map<String, Double> classes = grades.getGrades();
						if (classes.isEmpty()) {
							out.println("Not currently enrolled in any classes");
						} else {
							for (String className : classes.keySet()) {
								out.println(className + ": " + classes.get(className));
							}
						}
					} else if (inputWords[0].equals("gpa")) {
						out.println(grades.calculateGPA());
					} else {
						throw new IllegalArgumentException("Input one word long may only be enrollment or gpa.");
					}
					
					// DONE throw IllegalArgumentException if input is anything else
				}
				
				else if (inputWords.length == 2) {
					String yourClass = inputWords[0];
					Double yourGrade = Double.parseDouble(inputWords[1]);
					grades.addGrade(yourClass, yourGrade);
					out.println("Added grade for " + yourClass + " to the gradebook.");
					
				} else {
					throw new IllegalArgumentException("Invalid input");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}

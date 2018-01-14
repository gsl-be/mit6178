package lec6;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class HighSchooler implements Student {
	private List<String> classes = new ArrayList<String>();
	private Map<String, Double> grades = new HashMap<String, Double>();
	private String residence = null;
	
    // Enter class grade on a 4.0 point scale
    public void enterClassGrade(String className, double grade) {
    	grades.put(className, grade);
    }
    
    // get GPA on a 4.0 point scale
    public double getGPA() {
    	double total = 0;
    	for(Double value : grades.values()) {
    		total += value;
    	}
    	return total/grades.size();
    }
    
    // get list of all classes student is registered for
    public List<String> getClassRegistration() {
    	return classes;
    }
    
    // register new class
    public void registerClass(String className) {
    	classes.add(className);
    }
    
    // enter residence for student
    public void listResidence(String residenceName) {
    	residence = residenceName;
    }
    
    // gets the residence name of the student
    public String getResidence() {
    	return residence;
    }

    
    // main method is to be able to run code later 
    public static void main(String[] args) {
        Student mike = new HighSchooler();
        mike.listResidence("hat");
        mike.enterClassGrade("Physics", 4.0);
        mike.enterClassGrade("Chemistry", 3.8);
        
        mike.registerClass("Physics");
        
        System.out.println(mike.getGPA());
        System.out.println(mike.getClassRegistration());
        System.out.println(mike.getResidence());
    }
}

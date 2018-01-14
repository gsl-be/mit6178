package lec6;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Grad implements Student {
	private Map<String, Double> grades = new HashMap<String, Double>();
	private List<String> classes = new ArrayList<String>();
	private String residence = null;
	private String department = null;
    // Enter class grade on a 4.0 point scale
    public void enterClassGrade(String className, double grade) {
    	grades.put(className, 0.0);
    }
    
    // get GPA on a 4.0 point scale
    public double getGPA() {
    	return 0;
    }
    
    // get list of all classes student is registered for
    public List<String> getClassRegistration() {
    	return classes;
    }
    
    // register new class
    public void registerClass(String className){
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
    
    public void listDepartment(String departmentName) {
    	department = departmentName;
    }
    
    public String getDepartment() {
    	return department;
    }
    
    public static void main(String[] args) {
    	Student dave = new Grad();
    	Student stu = new Grad();
        dave.listResidence("hatter");
        ((Grad) dave).listDepartment("biology");
        dave.enterClassGrade("Physics", 4.0);
        dave.enterClassGrade("Chemistry", 3.8);
        
        dave.registerClass("Physics");
        
        stu.listResidence("hopper");
        ((Grad) stu).listDepartment("shoe");
        stu.enterClassGrade("hat", 4.0);
        stu.enterClassGrade("frog", 3.8);
        
        stu.registerClass("monkey");
        
        System.out.println(dave.getGPA());
        System.out.println(dave.getClassRegistration());
        System.out.println(dave.getResidence());
        System.out.println(((Grad) dave).getDepartment());
        
        System.out.println(stu.getGPA());
        System.out.println(stu.getClassRegistration());
        System.out.println(stu.getResidence());
        System.out.println(((Grad) stu).getDepartment());
        
        System.out.println(dave.getGPA());
        System.out.println(dave.getClassRegistration());
        System.out.println(dave.getResidence());
        System.out.println(((Grad) dave).getDepartment());
    	
    }
}

package lec6;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Undergrad implements Student {
	private String residence = null;
	private List<String> classes = new ArrayList<String>();
	private Map<String, Double> grades = new HashMap<String, Double>();
	private String major = null;

	// Enter class grade on a 4.0 point scale
	public void enterClassGrade(String className, double grade) {
		grades.put(className, grade);
	}

	// get GPA on a 4.0 point scale
	public double getGPA() {
		double total = 0;
		for (Double grade : grades.values()) {
			total += grade;
		}
		return total/grades.size();
	}

	// get list of all classes stu2dent is registered for
	public List<String> getClassRegistration() {
		return classes;
	}

	// register new class
	public void registerClass(String className) {
		classes.add(className);
	}

	// enter residence for stu2dent
	public void listResidence(String residenceName) {
		residence = residenceName;
	}

	// gets the residence name of the stu2dent
	public String getResidence() {
		return this.residence;
	}

	public void listMajor(String majorName) {
		major = majorName;
	}

	public String getMajor() {
		return this.major;
	}

	// implement stu2dent
	public static void main(String[] args) {
		Student dave2 = new Undergrad();
		Student stu2 = new Undergrad();
		
		dave2.listResidence("hatter");
		((Undergrad) dave2).listMajor("biology");
		dave2.enterClassGrade("Physics", 4.0);
		dave2.enterClassGrade("Chemistry", 3.8);

		dave2.registerClass("Physics");

		stu2.listResidence("hopper");
		((Undergrad) stu2).listMajor("shoe");
		stu2.enterClassGrade("hat", 4.0);
		stu2.enterClassGrade("frog", 3.8);

		stu2.registerClass("monkey");

		System.out.println(dave2.getGPA());
		System.out.println(dave2.getClassRegistration());
		System.out.println(dave2.getResidence());
		System.out.println(((Undergrad) dave2).getMajor());

		System.out.println(stu2.getGPA());
		System.out.println(stu2.getClassRegistration());
		System.out.println(stu2.getResidence());
		System.out.println(((Undergrad) stu2).getMajor());

		System.out.println(dave2.getGPA());
		System.out.println(dave2.getClassRegistration());
		System.out.println(dave2.getResidence());
		System.out.println(((Undergrad) dave2).getMajor());
	}
}

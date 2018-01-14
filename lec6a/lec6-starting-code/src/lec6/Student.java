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
    
    // enter residence for student
    public void listResidence(String residenceName);
    
    // gets the residence name of the student
    public String getResidence();
    

}



package lec6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class MIT {

    public static String loc = "C:\\Code\\6178\\lec6\\lec6-starting-code\\src\\lec6\\";

    public static void main (String args[]) {
        readFile();
        
        loadStudentGrades();
        loadStudentResidences();
        loadStudentClasses();
        
        FileOutputStream gradesFile;
        
        try {
            gradesFile = new FileOutputStream(loc + "student-gpa.txt");
            PrintStream writeGrades = new PrintStream(gradesFile);

            writeGrades.print("wrote this");
            System.out.println("wrote it");
            
            writeGrades.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        BufferedReader reader = null;
        
        try {
            File file = new File(loc +"students.txt");
            reader = new BufferedReader(new FileReader(file));
            
            String line; 
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void loadStudentGrades() {
        BufferedReader reader = null;
        
        try {
            File file = new File(loc + "grades.txt");
            reader = new BufferedReader(new FileReader(file));
            
            String line; 
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split("\\s+");
                System.out.println(Arrays.toString(lineSplit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private static void loadStudentResidences() {
        BufferedReader reader = null;
        
        try {
            File file = new File(loc +"residences.txt");
            reader = new BufferedReader(new FileReader(file));
            
            String line; 
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split("\\s+");
                System.out.println(Arrays.toString(lineSplit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private static void loadStudentClasses() {
        BufferedReader reader = null;
        
        try {
            File file = new File( loc +"students-classes.txt");
            reader = new BufferedReader(new FileReader(file));
            
            String line; 
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split("\\s+");
                System.out.println(Arrays.toString(lineSplit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}

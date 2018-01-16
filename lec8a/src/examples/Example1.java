package examples;

public class Example1 {
    
    static char getLetterGrade(double percentage) {
        char letterGrade;
        if (percentage >= 90) {
            letterGrade = 'A';
        } if (percentage >= 80) {
            letterGrade = 'B';
        } if (percentage >= 70) {
            letterGrade = 'C';
        } if (percentage >= 60) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        return letterGrade;
    }
    
    public static void main(String[] args) {
        double percentage = 75.0;
        char letterGrade = getLetterGrade(percentage); // expected to be C
        System.out.println(letterGrade);
    }
}

package examples;

public class Example2 {
    public static void main(String[] args) {
        
        boolean conditionA = false;
        boolean conditionB = true;
        boolean conditionC = true;
        
        if (conditionA) {
            System.out.println("Condition A was true!");
        } else if (conditionB) {
            System.out.println("Condition B was true and Condition A was false!");
        }
        if (conditionC) {
            System.out.println("Condition C was true!");
        } else {
            System.out.println("Condition C was false!");
        }
    }
}

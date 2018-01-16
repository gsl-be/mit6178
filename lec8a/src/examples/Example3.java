package examples;

import java.util.ArrayList;
import java.util.Arrays;

public class Example3 {
    
    public static void main(String[] args) {
        
        ArrayList<String> noAnimals = new ArrayList<>();
        ArrayList<String> existingAnimals = new ArrayList<>(Arrays.asList("cow", "pig","horse"));
        noAnimals.add("dog");
        
        System.out.println(existingAnimals);
        System.out.println(noAnimals);
        
    }
}

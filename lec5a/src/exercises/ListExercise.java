package exercises;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<Integer>();

        for (int i = 0; i < 20; i++) {
            int randomInt = (int)(Math.random() * 100);
            numbers.add(randomInt);
        }

        List<Integer> evenNumbers = new ArrayList<Integer>();
        
        for(Integer even : numbers) {
        	if(even % 2 == 0) {
        		evenNumbers.add(even);
        	}
        }
        
        System.out.println(evenNumbers.toString());
        System.out.println(evenNumbers);
        // TODO add all of the even numbers in `numbers` to `evenNumbers`

        // TODO print out evenNumbers (use toString())
        System.out.println(evenNumbers.size());

        // TODO print out how many elements evenNumbers has
    }
}

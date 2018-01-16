package examples;

import java.util.HashMap;

public class Example5 {
    
    /**
     * Creates a mapping of characters in a word to the frequencies at which they appear
     * in that word
     * i.e. word = "massachusetts" => {'m':1, 'a':2, 's':4, 'c':1, 'h':1, 'u':1, 'e':1, 't':2}
     * 
     * @param word a positive-length String
     * @return a mapping of characters in word mapped to their frequencies
     * 
     */
    public static HashMap<Character, Integer> getLetterFrequencies(String word) {
        HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
        String[] arrayOfChars = word.split("");
    	for(int i = 0; i < word.length(); i ++) {
    		Integer count = new Integer(1);
    		Character myChar = new Character(word.charAt(i));
    		if(frequencies.containsKey(myChar)) {
    			count = frequencies.get(myChar) + 1;
    			frequencies.put(myChar, count);
    		} else {
    			frequencies.put(myChar, count);
    		}
    	}
    	return frequencies;
    }
    
    public static void main(String[] args) {
        
        HashMap<Character, Integer> wordMap = getLetterFrequencies("massachusetts");
        System.out.println(wordMap);
        
    }
}

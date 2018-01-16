package examples;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class Example5Test {

    @Test
    public void testGetLetterFrequencies() {
        HashMap<Character, Integer> massFreq = Example5.getLetterFrequencies("massachusetts");
        assertFalse(massFreq.isEmpty());
        assertEquals(8, massFreq.size());
        int numS = massFreq.get('s');
        assertEquals(4, numS);

    }
    
    @Test
    public void testGetLetterFrequenciesEmptyString() {
    	HashMap<Character, Integer> emptyFreq = Example5.getLetterFrequencies("");
    	assertTrue(emptyFreq.isEmpty());

    }

}

package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Example4 {
	
	/**
	 * Interact with the following program from the Eclipse console
	 * 
	 * Asks the user to enter a sentence and displays each word of that sentence
	 * on a separate line
	 */
	public static void main(String[] args) {
		PrintStream out = new PrintStream(System.out);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			out.println("Please enter a sentence");
			out.print("> ");
			try {
				String sentence = in.readLine();
				
				//exit the program if user hits enter with no sentence
				if (sentence.isEmpty()) {
					out.println("bye");
					return;
				}
				
				//splits sentence on whitespaces, creating an array of non-whitespace strings
				String[] words = sentence.split("\\s+");
				
				for (String word : words) {
					out.println(word);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

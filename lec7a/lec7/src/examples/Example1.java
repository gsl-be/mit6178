package examples;

public class Example1 {
	
	public static int getLength(String word) {
		return word.length();
	}
	
	public static void main(String[] args) {
		
//		//Illustrates ArrayIndexOutOfBoundsException
//		int[] array = new int[5];
//		for (int i=0; i <= array.length; i++) {
//			array[i] = i+2;
//		}
		
		//Illustrates NullPointerException
		String word = null;
		getLength(word);
	}
}

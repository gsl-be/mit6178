package lec3;

public class Main {

	/**
	 * Estimate the price of a ride
	 * 
	 * @param basePrice
	 *            the base price of the ride in dollars
	 * @param serviceFee
	 *            the service fee in dollars
	 * @param rate
	 *            the dollars per mile of the ride
	 * @param milesToTravel
	 *            the miles traveled in the ride
	 * @return the price of the ride in dollars
	 */
	public static double estimateRidePrice(double basePrice, double serviceFee, double rate, double milesToTravel) {
		double price;
		price = basePrice + serviceFee + rate * milesToTravel;
		return price;
	}

	public static void main(String[] args) {
		
		double price = estimateRidePrice(1,23,5,6);
		
		//int, double, char, float, boolean
		
		int myInt = 5;
		

		//Array Declaration
		String[] months = new String[3];
		//months[2] = "December";
		
		months = new String[10];

		// same as
		String[] months2 = { "June", "July", "August" };
		
		
		int monthsLength = months.length;

		// Strings are objects: We can call methods on them

		String dabString = "dab";

		int dabStringLength = dabString.length();
		System.out.println("dabString is " + dabStringLength + " characters long");

		String substring1 = dabString.substring(2,3);
		String subString = dabString.substring(1, dabString.length());
		
		System.out.println("substring: " + subString);

		String[] dabArray = new String[3];

		for (int i = 0; i < dabString.length(); i++) {
			dabArray[i] = dabString.substring(i, i + 1);
		}
	
		
		int parsedInt = Integer.parseInt("54");
		
//		System.out.printf("%d is an int \n", parsedInt);
		
		// dabArray is now ["d", "a", "b"]

		// Integer (capital letter) are objects. We can use their methods to
		// convert strings to intergers

		String chargedUp = "9001";

		int chargedUpInt = Integer.parseInt(chargedUp);

		boolean ready = true;

		if (chargedUpInt > 9000 & ready) {
			System.out.println("Charged up and ready");
		}

		if (chargedUpInt > 9000 | ready) {
			System.out.println("Charged up or ready");
		}

		String hi = "hi";
		String h = "h";
		
		int first = 1;
		int last = 99;
		if (first == last) {
			
		}

		if ("fist".equals("last")) {
		}
		
		String mississippi = "mississippi";
		String container = "container";
		String preposterous = "preposterous";

		System.out.println(letterCount(mississippi, "i"));
		System.out.println(letterCount(mississippi, "p"));
		// letterCount(container, "c");
		// letterCount(preposterous, "p");
		
		//System.out.println(average(new double[] {1,2,3}));// should be 2

		double[] bostonTemps = { 12, 5, -20, 50, 12, 20, 10 };
		double[] floridaTemps = { 70, 70, 70, 70, 70, 70, 70 };
		double[] alabamaTemps = { 50, 40, 27, 30, 49, 80, 10 };

		double[][] cityTemps = { bostonTemps, floridaTemps, alabamaTemps };

		String[] cityNames = { "Boston", "Florida", "Alabama" };

		// String coldestCity = coldestPlace(cityTemps, cityNames);
		// System.out.printf("%s is the coldest place!, coldestCity);

	}

	//string.substring(index, index + 1) //Returns the single string character at the index
	// Exercise 1
	/**
	 * Takes in a word and a letter, and counts the number of time the letter 
	 * occur in the word and returns as an integer
	 * 
	 * @param word
	 * @param letter
	 * @return
	 */
	// NOTE method signature changed to return int rather than String
	public static int letterCount(String word, String letter) {
		int counter = 0;
		
		for (int i = 0; i < word.length(); i++) {
			String letterChecking = word.substring(i, i+1);
			
			if (letterChecking.equals(letter)) {
				counter = counter + 1;
			}
			
		}
		
		return counter;
	}
	
	public int myVarialbe = 4;
	// Exercise 2
	/**
	 * Take in an array of integers, and returns average of integers in array
	 * 
	 * @param values
	 * @return
	 */
	//NOTE method changed to take in an array of type double rather than int
	//public static double average(double[] values) {
	    //TODO implement this method		
	//}

	// Excercise 3
	/**
	 * Coldest place will take in a 2d array of city temperatures (cityTemps)
	 * where each element of the array is an array of temperatures for different
	 * days for that particular city It will also take in an array of city names
	 * (cityNames) which corresponds to the temperatures so that the
	 * cityTemps[i] has city name cityNames[i]
	 * 
	 * @param cityTemps
	 * @param cityNames
	 * @return the city with the lowest average temp
	 */
	// TODO declare and implement coldestPlace, which takes in a 2d array of
	// cities and their temps, and another array of city names
}
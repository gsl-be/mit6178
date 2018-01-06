package examples;

import java.util.Arrays;

public class Example1 {
    private static int smallest(int[] data) {
    	int[] dataCopy = new int[data.length];
    	for ( int i = 0; i < data.length; i ++) {
    		dataCopy[i] = data[i];
    	}
        Arrays.sort(dataCopy);
        return dataCopy[0];
    }

    private static int largest(int[] data) {
    	int[] dataCopy = new int[data.length];
    	for ( int i = 0; i < data.length; i ++) {
    		dataCopy[i] = data[i];
    	}
        Arrays.sort(dataCopy);
        return dataCopy[dataCopy.length - 1];
    }

    private static void showMeTheData(int[] data) {
        int min = smallest(data);
        int max = largest(data);
        int first = data[0];
        int last = data[data.length - 1];

        System.out.printf("min: %d, max: %d, first: %d, last: %d\n", min, max, first, last);
    }

    public static void main(String[] args) {
        int[] data = new int[] { 19, 88, 4, 39, 10, 72 };
        showMeTheData(data);
    }
}

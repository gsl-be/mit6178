package examples;

import java.io.File;
import java.io.FileReader;

public class Example3 {
	
	public static void main(String[] args) {
		File file = new File("/file/not/found.txt");
		FileReader reader = new FileReader(file);
	}
}

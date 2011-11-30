

import java.util.Scanner;


public class ConsoleReader {
	Scanner scanner;
	
	public ConsoleReader() {
		scanner = new Scanner(System.in);
	}
	
	public String readConsole() {
		String input;
		input = scanner.nextLine();
		return input;
	}	
}

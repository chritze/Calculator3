

public class Main {

	/**
	 * This is the main class for the program
	 * It makes you enter a infix expression and get the postfix expression and value
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleReader r = new ConsoleReader();
		System.out.print("Enter your Infix expression: ");
		String infix = r.readConsole();
		Postfix p = new Postfix();
		p.printCalculation(infix);
	}

}

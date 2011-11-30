package postfix;

public class BadFormatException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public BadFormatException() {
		System.out.println("The Expression you have entered is not in the right format!");
	}
}

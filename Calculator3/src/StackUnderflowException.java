
/**
 * This Exception will be throw if the Stack is actually empty
 * 
 * @author gdivincenzo
 * 
 */
public class StackUnderflowException extends Exception {

	private static final long serialVersionUID = 1L;

	public StackUnderflowException() {
		System.out.println("Error: the Stack is empty !");
	}
}
package stack;
/**
 * This is the interface for our Stack data-structure
 * 
 * @author cmoller, gdivincenzo
 * 
 */
public interface MyStack {
	public void push(Object n) throws StackOverflowError;

	public Object pop() throws StackUnderflowException;

	public Object top();

	public boolean isEmpty();

	public void clear();

	public String toString();
}

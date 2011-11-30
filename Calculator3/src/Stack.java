
/**
 * This is the implementation of the interface MyStack. This class simply
 * reproduce a Stack data-structure using a Double Linked List
 * 
 * @author gdivincenzo
 * 
 */
public class Stack implements MyStack {

	private Node first;
	private Node last;
	private Node tmpLast;

	// constructor for the class Stack
	public Stack() {
		first = null;
		last = null;
	}

	/**
	 * This method insert an Object to the Stack
	 * 
	 * @param Object
	 *            o The object to insert
	 */
	public void push(Object o) throws StackOverflowError {
		Node node = new Node(o);
		if (first == null) {
			first = node;
			last = node;
		} else {
			last.setNextNode(node);
			node.setPrevNode(last);
			last = node;
		}
	}

	/**
	 * This method remove and return the last inserted element of the Stack
	 * 
	 * @return Object The just removed Object
	 */
	public Object pop() throws StackUnderflowException {
		if (!isEmpty()) {
			tmpLast = last;
			last = last.getPrevNode();
			if (tmpLast.hasPrev()) {
				tmpLast.setPrevNode(null);
			} else {
				tmpLast.setNextNode(null);
				first = null;
			}
			return tmpLast.getData();
		} else {
			throw new StackUnderflowException();
		}
	}

	/**
	 * This method returns the last inserted Object without removing it from the
	 * Stack
	 * 
	 * @return Object The last inserted Object, actually located at the top of
	 *         the Stack
	 */
	public Object top() {
		return last.getData();
	}

	/**
	 * This method ask if the Stack is empty or contains any entries
	 * 
	 * @return boolean true if the Stack is empty
	 */
	public boolean isEmpty() {
		if ((first == null) && (last == null)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Delete all elements from the Stack
	 */
	public void clear() {
		first = null;
		last = null;
	}

	/**
	 * Overrides the toString() method to get a String of the Stack
	 * 
	 * @return String A String with all entries of the Stack
	 */
	public String toString() {
		Node current = first;
		String stackToString = "";
		while (current.hasNext()) {
			stackToString += " " + current.toString();
			current = current.getNextNode();
		}
		stackToString += " " + current.toString();
		return stackToString;
	}
}

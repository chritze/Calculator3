package stack;
/**
 * This class represents a single node of a Stack data-structure
 * 
 * @author gdivincenzo
 * 
 */
public class Node {

	private Node prevNode;
	private Node nextNode;
	private Object data;

	// constructore for the class Node
	public Node(Object current) {
		data = current;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public Object getData() {
		return data;
	}

	/**
	 * Ask if this node is linked to a previous node
	 * 
	 * @return boolean true if this node is linked to a previous node
	 */
	public boolean hasPrev() {
		if (prevNode == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Ask if this node is linked to a next node
	 * 
	 * @return boolean true if this node is linked to a next node
	 */
	public boolean hasNext() {
		if (nextNode == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Overrides the toString() method
	 * 
	 * @return String a String with the data contained in this node
	 */
	public String toString() {
		String s = getData().toString();
		return s;
	}

}

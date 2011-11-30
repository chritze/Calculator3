

public class Postfix {
	
	Stack stack = new Stack();
	
	
	/**
	 * Takes a String representing a postfix expression and evaluates it.
	 * @param pfx
	 * @return The value of the postfix expression
	 */
	public int evaluate(String pfx) throws BadFormatException {
		char[] charArray = pfx.toCharArray();
		int solution = 0;
		
		for(int i = 0; i < pfx.length(); i++) {

			char c = charArray[i];
			
			//if c is an operand, push it to the stack
			if(isOperator(c)) {
				stack.push(c - '0');
				}
			
			//if c is an operator, get the last two operands from the stack
			else if(isOperand(c)) {
				
					Integer opA = null;
					Integer opB = null;

					//get both operands
					try {
						opA = (Integer)(stack.pop());
						opB = (Integer)(stack.pop());
					}
					catch(Exception e) {
						System.out.println(e);
					}
					
					//do the operations on the operands
					switch(c) {
					case '*': solution = opA * opB; break;
					case '+': solution = opB + opA; break;
					case '-': solution = opB - opA; break;
					case '/': solution = opB / opA; break;
					case '^': solution = (int)(Math.pow(opB, opA)); break;
					}
					
					//push the evalution to the stack
					stack.push(solution);
			}
			else if(c == ' ') {
				//nothing to do!
			}
			else {
				throw (new BadFormatException());
			}
			
		}
		try {
		solution = (Integer)(stack.pop());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return solution;
		
	}
	
	
	/**
	 * Takes an infix expression and converts it to a postfix expression.
	 * @param ifx
	 * @return The postfix expression
	 */
	public String infixToPostfix(String ifx) throws BadFormatException{
		char[] charArray = ifx.toCharArray();
		String solution = "";
		
		//creating an empty stack
		stack = new Stack();
		
		for(int i = 0; i < ifx.length(); i++) {
			char c = charArray[i];
			
			//if its an operand, add it to the solution string
			if(isOperator(c)) {
				solution += c;
			}
			else if(isOperand(c)) {
				solution = processOperator(c, solution);
			}
			else if(c == ' ') {
				//nothing to do!
			}
			else {
				throw (new BadFormatException());
			}
		}
		while(!stack.isEmpty()) {	
			try {
			solution += stack.pop();
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return solution;
		
		
	}
	
	public String processOperator(char c, String solution) {
		String sol = solution;
		char cha = c;
		int precedence = getPrecedence(cha);
		//if the stack is empty, push the operator to it
		if(stack.isEmpty()) {
			stack.push(cha);
		}
		
		else {
			while(!stack.isEmpty()) {
				int prec = getPrecedence((Character)(stack.top()));
				if(prec >= precedence) {
					try {
					sol += stack.pop();
					}
					catch(Exception e) {
						System.out.println(e);
					}
				}
				else {
					break;
				}
				
			}
			stack.push(cha);
		}
		return sol;	
	}
	
	
	public int getPrecedence(char c) {
		switch(c) {
		case '^': 	return 2;
		case '*': 	return 1;
		case '/': 	return 1;
		case '+': 	return 0;
		case '-': 	return 0;
		default: 	return -1;
		}
		
	}
	
	public boolean checkPrecedence(char a, char b) {
		if(getPrecedence(a) > getPrecedence(b)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isOperand(char c) {
		if(c == '*' ||
				c == '+' ||
				c == '-' ||
				c == '/' ||
				c == '^') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isOperator(char c) {
		if(c >= '0' && c <= '9') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void printCalculation(String infix) {
		try {
		String ifExpression = infix;
		System.out.println("THE INFIX EXPRESSION IS: [ " + ifExpression + " ]");
		System.out.println();
		String pfExpression = infixToPostfix(ifExpression);
		System.out.println("THE POSTFIX EXPRESSION IS: [ " + pfExpression + " ]");
		System.out.println();
		int value = evaluate(pfExpression);
		System.out.println("THE VALUE OF THIS EXPRESSION IS: [ " + value + " ]");
		}
		catch (BadFormatException e) {
			System.out.println(e);
		}
	}
			

}

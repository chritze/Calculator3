

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being done
 * here. This class is responsible just for putting up the display on screen. It
 * then refers to the "CalcEngine" to do all the real work.
 * 
 * @author D. J. Barnes, M. Kolling, C.Möller, G.Di Vincenzo
 * @version 2008.03.30
 */
public class UserInterface implements ActionListener {
	private CalcEngine calc;

	private JFrame frame;
	private JTextField display;
	private JTextField hexDisplay;
	private JTextField postfixDisplay;
	private JPanel buttonPanel;
	private JPanel hexPanel;
	private JCheckBox hexButton;
	private JCheckBox postfixButton;

	/**
	 * Create a user interface.
	 * 
	 * @param engine
	 *            The calculator engine.
	 */
	public UserInterface(CalcEngine engine) {
		calc = engine;
		makeFrame();
		frame.setVisible(true);
		frame.setSize(250, 320);
	}

	/**
	 * Set the visibility of the interface.
	 * 
	 * @param visible
	 *            true if the interface is to be made visible, false otherwise.
	 */
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame() {
		frame = new JFrame(calc.getTitle());
		
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		display = new JTextField();
		contentPane.add(display, BorderLayout.NORTH);

		buttonPanel = new JPanel(new GridLayout(8, 3));
		addButton(buttonPanel, "9");
		addButton(buttonPanel, "8");
		addButton(buttonPanel, "7");

		addButton(buttonPanel, "6");
		addButton(buttonPanel, "5");
		addButton(buttonPanel, "4");

		addButton(buttonPanel, "3");
		addButton(buttonPanel, "2");
		addButton(buttonPanel, "1");
		// addButton(buttonPanel, "?");

		addButton(buttonPanel, "0");

		addButton(buttonPanel, "*");
		addButton(buttonPanel, "/");
		addButton(buttonPanel, "+");
		addButton(buttonPanel, "-");
		addButton(buttonPanel, "=");

		hexPanel = new JPanel(new GridLayout(6, 1));
		addButton(hexPanel, "A");
		addButton(hexPanel, "B");
		addButton(hexPanel, "C");
		addButton(hexPanel, "D");
		addButton(hexPanel, "E");
		addButton(hexPanel, "F");
		addButton(buttonPanel, "CE");

		hexPanel.setVisible(false);
		contentPane.add(buttonPanel, BorderLayout.CENTER);
		contentPane.add(hexPanel, BorderLayout.LINE_END);

		hexButton = new JCheckBox("Hex");
		hexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hexPanel.isVisible()) {
					hexPanel.setVisible(false);
					hexDisplay.setVisible(false);
					hexButton.setText("Hex");
				} else {
					hexPanel.setVisible(true);
					hexDisplay.setVisible(true);
					hexButton.setText("Hex:");					
				}
			}
		});
		buttonPanel.add(hexButton);
		hexDisplay = new JTextField();
		buttonPanel.add(hexDisplay);
		postfixButton = new JCheckBox("RPN");
		buttonPanel.add(postfixButton);
		postfixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (postfixDisplay.isVisible()) {
					postfixDisplay.setVisible(false);
					postfixButton.setText("RPN");
				} else {
					postfixDisplay.setVisible(true);
					postfixButton.setText("RPN:");
				}
			}
		});
		hexDisplay.setVisible(false);

		postfixDisplay = new JTextField();
		postfixDisplay.setVisible(false);
		contentPane.add(postfixDisplay, BorderLayout.SOUTH);
		
		
		frame.pack();
	}

	/**
	 * Add a button to the button panel.
	 * 
	 * @param panel
	 *            The panel to receive the button.
	 * @param buttonText
	 *            The text for the button.
	 */
	private void addButton(Container panel, String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and handle
	 * it.
	 * 
	 * @param event
	 *            The event that has occured.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		if (command.equals("0") || command.equals("1") || command.equals("2")
				|| command.equals("3") || command.equals("4")
				|| command.equals("5") || command.equals("6")
				|| command.equals("7") || command.equals("8")
				|| command.equals("9")) {
			int number = Integer.parseInt(command);
			calc.numberPressed(number, "dec");
		} else if (command.equals("A") || command.equals("B")
				|| command.equals("C") || command.equals("D")
				|| command.equals("E") || command.equals("F")) {
			int number = Integer.parseInt(command, 16);
			calc.numberPressed(number, "hex");
		} else if (command.equals("+")) {
			calc.plus();
		} else if (command.equals("-")) {
			calc.minus();
		} else if (command.equals("=")) {
			postfixDisplay.setText(calc.getRPN());
			calc.equals();
		} else if (command.equals("CE")) {
			postfixDisplay.setText("");
			calc.clear();
		} else if (command.equals("*")) {
			calc.multiply();
		} else if (command.equals("/")) {
			calc.divide();
		}
		// else unknown command.

		redisplay();
	}

	/**
	 * Update the interface display to show the current value of the calculator.
	 */
	private void redisplay() {
		display.setText("" + calc.getDisplayValue());
		hexDisplay.setText(calc.getHexValue());
	}
}

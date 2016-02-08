package banking.gui;

import banking.primitive.core.Account;
import banking.primitive.core.AccountServer;
import banking.primitive.core.AccountServerFactory;

import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

/**
Class: MianFrame

Description: Creates the GUI for this project
*/
@SuppressWarnings("serial")
class MainFrame extends JFrame {
	AccountServer	myServer;
	Properties		props;
	JLabel			typeLabel;
	JLabel			nameLabel;
	JLabel			balanceLabel;
	JComboBox		typeOptions;
	JTextField		nameField;
	JTextField		balanceField;
	JButton 		depositButton;
	JButton 		withdrawButton;
	JButton			newAccountButton;
	JButton			displayAccountsButton;
	JButton			displayODAccountsButton;

	/**
	  Method: MainFrame
	  Inputs: The file containing the required properties
	  Returns: None

	  Description: Constructor
	  @param propertyFile The file containing the required properties
	*/
	public MainFrame(String propertyFile) throws IOException {

		//** initialize myServer
		myServer = AccountServerFactory.getMe().lookup();

		props = new Properties();

		FileInputStream fis = null; 
		try {
			fis =  new FileInputStream(propertyFile);
			props.load(fis);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		constructForm();
	}
	
	/**
	  Method: constructForm
	  Inputs: None
	  Returns: None

	  Description: Sets all the required fields, labels, and properties for the main form.
	*/
	private void constructForm() {
		//*** Make these read from properties
		typeLabel		= new JLabel(props.getProperty("TypeLabel"));
		nameLabel		= new JLabel(props.getProperty("NameLabel"));
		balanceLabel	= new JLabel(props.getProperty("BalanceLabel"));

		Object[] accountTypes = {"Savings", "Checking"};
		typeOptions = new JComboBox(accountTypes);
		nameField = new JTextField(20);
		balanceField = new JTextField(20);

		newAccountButton = new JButton("New Account");
		JButton depositButton = new JButton("Deposit");
		JButton withdrawButton = new JButton("Withdraw");
		JButton saveButton = new JButton("Save Accounts");
		displayAccountsButton = new JButton("List Accounts");
		JButton displayAllAccountsButton = new JButton("All Accounts");

		this.addWindowListener(new FrameHandler());
		newAccountButton.addActionListener(new NewAccountHandler());
		displayAccountsButton.addActionListener(new DisplayHandler());
		displayAllAccountsButton.addActionListener(new DisplayHandler());
		depositButton.addActionListener(new DepositHandler());
		withdrawButton.addActionListener(new WithdrawHandler());
		saveButton.addActionListener(new SaveAccountsHandler());		
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.add(typeLabel);
		panel1.add(typeOptions);
		
		JPanel panel2 = new JPanel();
		panel2.add(displayAccountsButton);
		panel2.add(displayAllAccountsButton);
		panel2.add(saveButton);
		
		JPanel panel3 = new JPanel();
		panel3.add(nameLabel);
		panel3.add(nameField);
		
		JPanel panel4 = new JPanel();
		panel4.add(balanceLabel);
		panel4.add(balanceField);
		
		JPanel panel5 = new JPanel();
		panel5.add(newAccountButton);
		panel5.add(depositButton);
		panel5.add(withdrawButton);

		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		pane.add(panel4);
		pane.add(panel5);
		
		setSize(400, 250);
	}

	
	/**
	  Class: DisplayHandler
	  
	  Description: Sets all the required fields, labels, and properties for the dialog box that appears when
	  the displayAccountsButton is pressed.
	*/
	class DisplayHandler implements ActionListener {
		
		/**
		  Method: actionPerformed
		  Inputs: an event
		  Returns: None

		  Description: Displays the appropriate dialog box if the displayAccountsButton is pressed
		  @param e the event performed on the main form.
		*/
		public void actionPerformed(ActionEvent e) {
			List<Account> accounts = null;
			if (e.getSource() == displayAccountsButton) {
				accounts = myServer.getActiveAccounts();
			} else {
				accounts = myServer.getAllAccounts();
			}
			StringBuffer sb = new StringBuffer();
			Account thisAcct = null;
			for (Iterator<Account> li = accounts.iterator(); li.hasNext();) {
				thisAcct = (Account)li.next();
				sb.append(thisAcct.toString()+"\n");
			}

			JOptionPane.showMessageDialog(null, sb.toString());
		}
	}

	/**
	  Class: NewAccountHandler
	  
	  Description: Sets all the required fields, labels, and properties for the dialog box that appears when
	  the newAccount button is pressed.
	*/
	class NewAccountHandler implements ActionListener {
		
		/**
		  Method: actionPerformed
		  Inputs: an event
		  Returns: None

		  Description: Displays the appropriate dialog box if the newAccount button is pressed
		  @param e the event performed on the main form.
		*/
		public void actionPerformed(ActionEvent e) {
			String type = typeOptions.getSelectedItem().toString();
			String name = nameField.getText();
			String balance = balanceField.getText();

			if (myServer.newAccount(type, name, Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Account created successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Account not created!");
			}
		}
	}
	
	/**
	  Class: SaveAccountsHandler
	  
	  Description: Sets all the required fields, labels, and properties for the dialog box that appears when
	  the saveAccounts button is pressed.
	*/
	class SaveAccountsHandler implements ActionListener {
		
		/**
		  Method: actionPerformed
		  Inputs: an event
		  Returns: None

		  Description: Displays the appropriate dialog box if the saveAccounts button is pressed
		  @param e the event performed on the main form.
		*/
		public void actionPerformed(ActionEvent e) {
			try {
				myServer.saveAccounts();
				JOptionPane.showMessageDialog(null, "Accounts saved");
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(null, "Error saving accounts");
			}
		}
	}

	/**
	  Class: DepositHandler
	  
	  Description: Sets all the required fields, labels, and properties for the dialog box that appears when
	  the deposit button is pressed.
	*/
	class DepositHandler implements ActionListener {
		
		/**
		  Method: actionPerformed
		  Inputs: an event
		  Returns: None

		  Description: Displays the appropriate dialog box if the deposit button is pressed
		  @param e the event performed on the main form.
		*/
		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			String balance = balanceField.getText();
			Account acc = myServer.getAccount(name);
			if (acc != null && acc.deposit(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Deposit successful");
			} else {
				JOptionPane.showMessageDialog(null, "Deposit unsuccessful");
			}		
		}
	}
	
	/**
	  Class: WithdrawHandler
	  
	  Description: Sets all the required fields, labels, and properties for the dialog box that appears when
	  the withdraw button is pressed.
	*/
	class WithdrawHandler implements ActionListener {
		
		/**
		  Method: actionPerformed
		  Inputs: an event
		  Returns: None

		  Description: Displays the appropriate dialog box if the withdraw button is pressed
		  @param e the event performed on the main form.
		*/
		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			String balance = balanceField.getText();
			Account acc = myServer.getAccount(name);
			if (acc != null && acc.withdraw(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful");
			} else {
				JOptionPane.showMessageDialog(null, "Withdrawal unsuccessful");
			}		
		}
	}
	
	//** Complete 
	/**
	  Class: FrameHandler
	  
	  Description: A handler for the Frame that terminates (System.exit(1)) on windowClosing event.
	*/
	static class FrameHandler extends WindowAdapter {
		/**
		  Method: windowClosing
		  Inputs: an event
		  Returns: None

		  Description: Exits the application if the window is closed
		  @param e the event performed on the main form.
		*/
		public void windowClosing(WindowEvent e) {

			System.exit(0);
		}
	}
}

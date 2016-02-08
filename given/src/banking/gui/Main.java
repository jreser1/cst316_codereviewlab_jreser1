package banking.gui;

import javax.swing.JFrame;

/**
Class:	Main

Description: Method for running the program.
 @author kevinagary
*/
final class Main {
	/**
	  Method: Main
	  Inputs: None
	  Returns: None

	  Description: Private constructor to address STYLE issue.
	*/
	private Main() {
	}
	
	/**
	  Method: main()
	  Inputs: my.properties file
	  Returns: None

	  Description: The main method for the project.
	 * @param args command-line arguments
	 * @throws Exception as per typical main specifications
	 */
	public static void main(final String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java FormMain <property file>");
			System.exit(1);
		}

		String propertyFile = args[0];
		JFrame frame = new MainFrame(propertyFile);
		frame.setVisible(true);

	}
}

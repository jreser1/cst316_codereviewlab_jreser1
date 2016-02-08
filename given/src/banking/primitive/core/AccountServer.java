package banking.primitive.core;

import java.io.IOException;
import java.util.List;

/**
Class: AccountServer

Description: An interface outlining the requirements for a way to manage and store multiple Account class instances.
*/
public interface AccountServer {

	/** 
	  Method: getAccount
	  Inputs: The name of the account
	  Returns: The account named
	  
	  Description: Retrieves the named account.
	  @param name name of the account
	  @return account object or null if not found. 
	 */
	public Account	getAccount(String name);

	/** 
	  Method: getAllAccounts
	  Inputs: None
	  Returns: None
	  
	  Description: Retrieves all accounts.
	  @return a list of all Accounts inside the server 
	 */
	public List<Account> getAllAccounts();

	/** 
	  Method: getActiveAccounts
	  Inputs: None
	  Returns: None
	  
	  Description: Retrieves all accounts without a CLOSED State.
	  @return a list of Accounts inside the server that are not CLOSED
	 */
	public List<Account> getActiveAccounts();

	/** 
	  Method: closeAccount
	  Inputs: The name of the account
	  Returns: whether or not the account closed successfully
	  
	  Description: Close the named account.
	  @param name leading or trailing whitespace is removed
	  @return boolean true if there was an account with this name and close was successful
	*/
	public boolean	closeAccount(String name);

	/** 
	  Method: newAccount
	  Inputs: The account type, name, and initial balance
	  Returns: whether or not the account was successfully created

	  Description: Creates a new account object in the server. if an account already exists with the given name 
	  then a new account is not created and stored.
	  @param type must be one of Savings or Checking
	  @param name leading or trailing whitespace is removed
	  @param balance must be non-negative
	  @throws IllegalArgumentException if the account type is invalid or the balance is non-negative.
	  @return boolean true if the account was created and stored, false otherwise
	*/
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	/** 
	  Method: saveAccounts
	  Inputs: None
	  Returns: None
	  
	  Description: Saves all accounts to a file
	  @throws IOException if unable to save the state
	 */
	 public void	saveAccounts() throws IOException;
}

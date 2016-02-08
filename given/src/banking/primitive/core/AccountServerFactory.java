package banking.primitive.core;


/**
Class:	AccountServerFactory

Description: 
*/
public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	/**
	  Method:
	  Inputs: None
	  Returns: None

	  Description: Default constructor
	*/
	protected AccountServerFactory() {

	}

	/**
	  Method: getMe
	  Inputs: None
	  Returns: The current AccountServerFactory

	  Description:Returns the current AccountServerFactory
	  @return the current AccountServerFactory
	*/
	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	/**
	  Method: lookup
	  Inputs: None
	  Returns: a new ServerSolution

	  Description:
	  @return a new ServerSolution
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
}

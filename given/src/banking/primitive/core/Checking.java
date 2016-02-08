package banking.primitive.core;

/**
Class:	Checking

Description: A subclass of Account that manages deposits, withdraws, and the State of Checking Accounts.
*/
public class Checking extends Account {

	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;
	
	/**
	  Method: Checking
	  Inputs: Account name
	  Returns: None
	
	  Description: Constructor using only an account name.
	  @param name account name
	*/
	private Checking(String name) {
		super(name);
	}

	/**
	  Method: createChecking
	  Inputs: Account name
	  Returns: A Checking Account that has the provided name

	  Description: Constructor using an account name and initial balance.
	  @param name account name
	  @return checking account with the provided name
	*/
	public static Checking createChecking(String name) {
        return new Checking(name);
    }

    /**
	  Method: Checking
	  Inputs: Account name, account balance
	  Returns: None

	  Description: Constructor using an account name and initial balance.
	  @param name account name
	  @param balance initial account balance
	*/
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	  Method: getType
	  Inputs: None
	  Returns: The account type

	  Description: Returns the type of account
	  @return the string "Checking"
	*/
	public String getType() { 
		return "Checking"; 
	}

	/**
	  Method: deposit
	  Inputs: Amount to deposit
	  Returns: Whether or not the deposit was successful

	  Description: Adjusts the balance by adding the provided value after subtracting the 50 cent fee and returns true if successful.
	  @param amount the amount to be added to the existing balance
	  @return whether or not the deposit was successful
	*/
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}
	
	/**
	  Method: toString()
	  Inputs: None
	  Returns: A string with the account type, name, and balance.
	  	  
	  Description: Returns the account type, name, and balance
	  @return a string with the account type, name, and balance
	*/
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}

	/**
	  Method: withdraw
	  Inputs: Amount to withdraw
	  Returns: Whether or not the withdraw was successful

	  Description: Adjusts the balance by subtracting the withdrawal amount. After 10 withdrawals a fee of $2 is charged per transaction You may 
	  continue to withdraw an overdrawn account until the balance is below -$100.
	  @param amount the amount to be withdrawn
	  @return whether or not the withdraw was successful
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10)
					balance = balance - 2.0f;
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}
}

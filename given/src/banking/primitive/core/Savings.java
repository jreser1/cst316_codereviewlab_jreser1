package banking.primitive.core;

/**
Class:	Savings

Description: A subclass of Account that manages deposits, withdraws, and the State of Savings Accounts.
*/
public class Savings extends Account {
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;

	/**
	  Method: Savings
	  Inputs: Account name
	  Returns: None
	
	  Description: Constructor using only an account name.
	  @param name account name
	*/
	public Savings(String name) {
		super(name);
	}

	/**
	  Method: Savings
	  Inputs: Account name, account balance
	  Returns: None

	  Description: Constructor using an account name and initial balance.
	  @param name account name
	  @param balance initial account balance
	*/
	public Savings(String name, float balance) throws IllegalArgumentException {
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
			balance = balance + amount - 0.50F;
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
		return "Savings: " + getName() + ": " + getBalance();
	}

	/**
	  Method: withdraw
	  Inputs: Amount to withdraw
	  Returns: Whether or not the withdraw was successful

	  Description: Adjusts the balance by subtracting the withdrawal amount. After 3 withdrawals a fee of $1 is added to each withdrawal.
	  An account whose balance dips below 0 is in an OVERDRAWN state.
	  @param amount the amount to be withdrawn
	  @return whether or not the withdraw was successful
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3){				
				balance = balance - 1.0f;
			}
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}

}

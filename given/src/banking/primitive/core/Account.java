package banking.primitive.core;

public abstract class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float balance = 0.0F;
    protected String name;
    private State state;



	/**
	  Method: Account
	  Inputs: Account name
	  Returns: None
	
	  Description: Constructor using only an account name.
	  @param name account name
	*/
	protected Account(String n) {
        name = n;
        state = State.OPEN;
    }



	/**
	  Method: Account
	  Inputs: Account name, account balance
	  Returns: None

	  Description: Constructor using an account name and initial balance.
	  @param name account name
	  @param balance initial account balance
	*/
	protected Account(String n, float b) {
        this(n); 
        balance = b;
    }

	/**
	  Method: getBalance
	  Inputs: None
	  Returns: The balance in the account

	  Description: Retrieves the amount of the account balance
	  @return balance in an account
	*/
	public final float getBalance() {
        return balance;
    }

	/**
	  Method: getName
	  Inputs: None
	  Returns: The name of the account

	  Description: Retrieves the name of the account
	  @return name of the account
	*/
	public final String getName() {
        return name;
    }
	
	/**
	  Method: getState
	  Inputs: None
	  Returns: The account state

	  Description: Returns the state of account
	  @return the current State. Either OPEN, CLOSED, or OVERDRAWN
	*/
	protected final State getState() {
        return state;
    }
	
	/**
	  Method: getType
	  Inputs: None
	  Returns: The account type

	  Description: Returns the type of account
	  @return either "Checking" or "Savings"
	*/
	public abstract String getType();


	/**
	  Method: setState
	  Inputs: The desired State for the current account
	  Returns: None

	  Description: Sets the State of the account to the desired value
	  @param s the desired State for the account. Either OPEN, CLOSED, or OVERDRAWN
	*/
	protected final void setState(State s) {
        state = s;
    }

	/**
	  Method: deposit
	  Inputs: The amount to be added to the existing balance
	  Returns: Whether or not the deposit was successful

	  Description: Adds money to an account. May not be done if the account is CLOSED.
	  @param amount the amount to be added to the existing balance (must be > 0)
	  @return whether or not the deposit was successful
     */
    public abstract boolean deposit(float amount);


	/**
	  Method: toString
	  Inputs: None
	  Returns: A string containing the account name, balance, and state

	  Description: Returns the account name, balance, and state of an account
	  @return a string containing the account name, balance, and state
	*/
	public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
    }

    /**
	  Method: withdraw
	  Inputs: Amount to withdraw
	  Returns: Whether or not the withdraw was successful

	  Description: Takes money out of an account. If the balance falls below 0 then the
	  account is moved to an OVERDRAWN state.
	  @param amount the amount to be withdrawn (must be > 0)
	  @return whether or not the withdraw was successful
	*/
    public abstract boolean withdraw(float amount);
}

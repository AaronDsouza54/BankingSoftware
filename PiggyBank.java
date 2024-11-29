
public class PiggyBank {
	private int pennies;
	private int nickels;
	private int dimes;
	private int quarters;
	private int loonies;
	private int toonies;
	private int piggyBankId;
	private int [] valuesToDivideBy = {1,5,10,25,100,200};
	
	//Constructor that creates a new PiggyBank object after taking in an int id number for the
	//piggy bank.
	public PiggyBank(int piggyBankId) {
		this(piggyBankId,0,0,0,0,0,0);
	}
	//Constructor that creates a new PiggyBank object after taking in an int id number and 
	//the number of coins of each denomination to set to the piggy bank.
	public PiggyBank(
					int piggyBankId,
					int pennies, 
					int nickels, 
					int dimes, 
					int quarters, 
					int loonies, 
					int toonies
					) {
		setPiggyBankId(piggyBankId);
		setPennies(pennies);
		setNickels(nickels);
		setDimes(dimes);
		setQuarters(quarters);
		setLoonies(loonies);
		setToonies(toonies);
	}
	
	/*
	 * Method: getters
	 * 	These methods return the class variables after which the getters are named.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	none 
	 * Return Values: 
	 *	int values that represent the number of coins for the coin type.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public int getPennies() {
		return pennies;
	}
	public int getNickels() {
		return nickels;
	}
	public int getDimes() {
		return dimes;
	}
	public int getQuarters() {
		return quarters;
	}
	public int getLoonies() {
		return loonies;
	}
	public int getToonies() {
		return toonies;
	}
	public int getPiggyBankId() {
		return piggyBankId;
	}
	
	/*
	 * Method: getPiggyBankTotal
	 * 	This method adds the number of coins of each denomination that is available and returns
	 * 	the value.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	none 
	 * Return Values: 
	 *	double value representing the total money owned. 
	 * Exceptions Thrown: 
	 *  none.
	 */
	public double getPiggyBankTotal() {
		return getPennies()*0.01+
				getNickels()*0.05+
				getDimes()*0.1+
				getQuarters()*0.25+
				getLoonies()+
				getToonies()*2;
	}
	
	/*
	 * Method: setters
	 * 	These methods set the value of the fields of the object.
	 * Parameters:
	 * 	int parameters that take in the number of coins of the particular denomination.
	 * Local Variables:
	 *	none 
	 * Return Values: 
	 *	none
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: If the argument is negative, the program crashes since 
	 *  number of coins cannot be negative.
	 */
	public void setPennies(int pennies) {
		if (pennies < 0)
			throw new IllegalArgumentException("Cannot have negative number of pennies!");
		this.pennies = pennies;
	}
	public void setNickels(int nickels) {
		if (nickels < 0)
			throw new IllegalArgumentException("Cannot have negative number of nickels!");
		this.nickels = nickels;
	}
	public void setDimes(int dimes) {
		if (dimes < 0)
			throw new IllegalArgumentException("Cannot have negative number of dimes!");
		this.dimes = dimes;
	}
	public void setQuarters(int quarters) {
		if (quarters < 0)
			throw new IllegalArgumentException("Cannot have negative number of quarters!");
		this.quarters = quarters;
	}
	public void setLoonies(int loonies) {
		if (loonies < 0)
			throw new IllegalArgumentException("Cannot have negative number of loonies!");
		this.loonies = loonies;
	}
	public void setToonies(int toonies) {
		if (toonies < 0)
			throw new IllegalArgumentException("Cannot have negative number of toonies!");
		this.toonies = toonies;
	}
	public void setPiggyBankId(int piggyBankId) {
		if (piggyBankId < 1000)
			throw new IllegalArgumentException("Not enough characters in piggy bank id!");
		this.piggyBankId = piggyBankId;
	}
	
	/*
	 * Method: addCoins
	 * 	This method takes in the number of coins of each denomination and adds it to the current value.
	 * Parameters:
	 *	pennies: int value that takes in the number of pennies to add.
	 *	nickels: int value that takes in the number of nickels to add.
	 *	dimes: int value that takes in the number of dimes to add.
	 *	quarters: int value that takes in the number of quarters to add.
	 *	loonies: int value that takes in the number of loonies to add.
	 *	toonies: int value that takes in the number of toonies to add.
	 * Local Variables:
	 *	none 
	 * Return Values: 
	 *	none
	 * Exceptions Thrown: 
	 *  none.
	 */
	public void addCoins(
							int pennies, 
							int nickels, 
							int dimes, 
							int quarters, 
							int loonies, 
							int toonies
						) {
		
		setPennies(this.pennies+pennies);
		setNickels(this.nickels+nickels);
		setDimes(this.dimes+dimes);
		setQuarters(this.quarters+quarters);
		setLoonies(this.loonies+loonies);
		setToonies(this.toonies+toonies);
	}
	
	/*
	 * Method: addCoins
	 * 	This method takes in the double value of money to add and adds it to the field variables by 
	 * 	checking the most efficient distribution.
	 * Parameters:
	 * 	valueToAdd: double value that takes in how much money to add.
	 * Local Variables:
	 *	numCoins: int array that takes in the number of coins of each denomination to add.
	 *	newValue: int value that multiplies the valueToAdd argument by 100 and rounds it so that it is 
	 *			  easier to work with.
	 * Return Values: 
	 *	none
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: thrown if the valueToAdd is negative.
	 */
	public void addCoins(double valueToAdd) {	
		int [] numCoins = new int[6];
		int newValue = (int)(valueToAdd*100);
		
		if (valueToAdd < 0)
			throw new IllegalArgumentException("Attempt to add negative money detected!");
		
		for (int i=(numCoins.length-1);i>=0;i--) {
			numCoins[i] = newValue/valuesToDivideBy[i];
			newValue -= numCoins[i]*valuesToDivideBy[i];
		}
		
		addCoins(
				numCoins[0],
				numCoins[1],
				numCoins[2],
				numCoins[3],
				numCoins[4],
				numCoins[5]
				);
			
	}
	
	/*
	 * Method: removeCoins
	 * 	This method takes in the number of coins of each denomination and removes it from the 
	 * 	current value.
	 * Parameters:
	 *	pennies: int value that takes in the number of pennies to remove.
	 *	nickels: int value that takes in the number of nickels to remove.
	 *	dimes: int value that takes in the number of dimes to remove.
	 *	quarters: int value that takes in the number of quarters to remove.
	 *	loonies: int value that takes in the number of loonies to remove.
	 *	toonies: int value that takes in the number of toonies to remove.
	 * Local Variables:
	 *	numPenniesToRemove: int value that determines the total money to remove in pennies.
	 *	numPenniesAvailable: int value that finds the total money that the user currently has, in pennies.
	 *	valueLeft: double value that is left over after the numPenniesToRemove is subtracted out of
	 *			   the numPenniesAvailable.
	 * Return Values: 
	 *	none
	 * Exceptions Thrown: 
	 *  none.
	 */
	public void removeCoins(
							int pennies, 
							int nickels, 
							int dimes, 
							int quarters, 
							int loonies, 
							int toonies
							) {
		int numPenniesToRemove;
		int numPenniesAvailable = (int)(getPiggyBankTotal()*100);
		double valueLeft;

		numPenniesToRemove = pennies*1+
								nickels*5+
								dimes*10 +
								quarters*25 +
								loonies*100 +
								toonies*200;
		
		numPenniesAvailable -= numPenniesToRemove;
		
		setPennies(0);
		setNickels(0);
		setDimes(0);
		setQuarters(0);
		setLoonies(0);
		setToonies(0);
		
		valueLeft = numPenniesAvailable*0.01;
		addCoins(valueLeft);
		
	}
	
	/*
	 * Method: removeCoins
	 * 	This method takes in the double value of money to remove and removes it in the most efficient 
	 * 	denomination.
	 * Parameters:
	 * 	valueToRemove: double value that takes in how much money to remove.
	 * Local Variables:
	 *	numCoins: int array that takes in the number of each coin type that needs to be removed.
	 *	newValue: int variable that determines the value to remove, in pennies.
	 * Return Values: 
	 *	none
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: thrown if the valueToRemove is negative.
	 */
	public void removeCoins(double valueToRemove) {
		int [] numCoins = new int[6];
		int newValue = (int)(valueToRemove*100);
		
		if (valueToRemove < 0)
			throw new IllegalArgumentException("Attempt to remove negative money detected!");
		
		
		for (int i=(numCoins.length-1);i>=0;i--) {
			numCoins[i] = newValue/valuesToDivideBy[i];
			newValue -= numCoins[i]*valuesToDivideBy[i];
		}
		removeCoins(
					numCoins[0],
					numCoins[1],
					numCoins[2],
					numCoins[3],
					numCoins[4],
					numCoins[5]
					);
	}

	/*
	 * Method: toString
	 * 	This method overrides the tString method and outputs the id, and number of coin of each
	 * 	denomination as well as the total number of coins.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *	String with the id number, number of coins of each denomination and total money.
	 * Exceptions Thrown: 
	 *  none
	 */
	@Override
	public String toString() {
		return "PiggyBank Id: "+getPiggyBankId()+
				"\tPennies: "+getPennies()+
				"\tNickels: "+getNickels()+
				"\tDimes: "+getDimes()+
				"\tQuarters: "+getQuarters()+
				"\tLoonies: "+getLoonies()+
				"\tToonies: "+getToonies()+
				"\tTotal: "+Utils.formatCurrency(getPiggyBankTotal());
	}
	
}

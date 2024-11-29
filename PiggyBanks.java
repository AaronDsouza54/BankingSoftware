import java.util.ArrayList;

public class PiggyBanks {
	private ArrayList <PiggyBank> piggyBanks; 
	
	//constructor that creates a new PiggyBanks object.
	public PiggyBanks() {
		piggyBanks = new ArrayList<>();
	}
	
	/*
	 * Method: getAllPiggyBankBalance
	 * 	Gets the balance of all the piggy banks stored in the PiggyBank array piggyBanks.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	piggyBankTotal: double array that stores the values of the balance in each piggy bank.
	 * Return Values: 
	 *	double array that represents the balance of all the piggy banks.
	 * Exceptions Thrown: 
	 *  IllegalStateException: exception is thrown if the currentLastSpot int variable is less than 0,
	 *  since a negative array length is not possible. 
	 */
	public double [] getAllPiggyBankBalance() {
		double [] piggyBankTotal = new double[checkArrayLength()];
		if (piggyBanks.get(0) == null)
			throw new IllegalStateException("There are no piggy banks in this account!!!");
		
		for (int i=0;i<piggyBanks.size();i++)
			piggyBankTotal[i] = piggyBanks.get(i).getPiggyBankTotal();
		
		return piggyBankTotal;
	}
	
	/*
	 * Method: getPiggyBank
	 * 	This method returns a PiggyBank object at the index specified.
	 * Parameters:
	 * 	pos: int value that represents the position that the PiggyBank object is at.
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *	PiggyBank value at the specified position is returned.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: the exception is thrown if the pos value is negative since
	 *  an array cannot have negative indexes. 
	 */
	public PiggyBank getPiggyBank(int pos) {
		if (pos < 0)
			throw new IllegalArgumentException("Cannot have a negative index value!");
		
		return piggyBanks.get(pos);
	}
	
	/*
	 * Method: checkArrayLength
	 * 	This method checks the value of the field variable currentLastSpot is 0. If so,
	 * 	it returns a 1 since this method helps determine the length of the array.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *	int value that represents the size of the array.
	 * Exceptions Thrown: 
	 *  none
	 */
	public int checkArrayLength() { 
		return piggyBanks.size();
	}
	
	/*
	 * Method: getPiggyBanks
	 * 	This method finds all the piggy banks owned by the owner and returns them in an array.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	piggyBank: PiggyBank array that stores all the piggy banks owned by the owner.
	 * Return Values: 
	 *	PiggyBank array that contains all the piggy banks owned by the owner.
	 * Exceptions Thrown: 
	 *  none
	 */
	public PiggyBank[] getPiggyBanks() {
		PiggyBank [] piggyBank = new PiggyBank[checkArrayLength()];
		
		for (int i=0;i<piggyBank.length;i++)
			piggyBank[i] = piggyBanks.get(i);
		return piggyBank;
	}

	/*
	 * Method: getPiggyBankIdNumbers
	 * 	This method finds and returns the id numbers of all the piggy banks owned by the owner.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	temp: int array that stores the id numbers of the piggy banks owned by the owner.
	 * Return Values: 
	 *	int array that contains the id numbers of all the piggy banks owned by the owner.
	 * Exceptions Thrown: 
	 *  none
	 */
	public int [] getPiggyBankIdNumbers() {
		int [] temp = new int[checkArrayLength()];
		
		for (int i=0; i < piggyBanks.size(); i++)
			temp[i] = piggyBanks.get(i).getPiggyBankId();
		
		return temp;
	}
	
	
	/*
	 * Method: addPiggyBank
	 * 	This method creates a new PiggyBank object for the user and adds it to the user. 
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	idNumber: int parameter that takes in the id number to assign the new PiggyBank object.
	 * Return Values: 
	 *	void
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if the id number is already assigned to another 
	 *  						  piggy bank.
	 */
	public void addPiggyBank(int idNumber) {
		if (exists(idNumber))
		    throw new IllegalArgumentException("Attempt to add duplicate ID Number Detected!");
		
		piggyBanks.add(new PiggyBank(idNumber)); 
	}

	/*
	 * Method: exists
	 * 	This method determines whether a piggy bank with the given id number exists.
	 * Parameters:
	 * 	idToSearchFor: int parameter that takes in the id of the piggy bank that will be added.
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *	boolean value that indicates whether a piggy bank with the same id number exists.
	 * Exceptions Thrown: 
	 *  none
	 */
	public boolean exists(int idToSearchFor) {
		return findPiggyBankId(idToSearchFor) != -1;
	}
	
	/*
	 * Method: findPiggyBankId
	 * 	This method searches the user's piggy banks to ensure that there is no duplication in the 
	 * 	id numbers.
	 * Parameters:
	 * 	idToSearchFor: int parameter that takes in the id number to search in the user's piggy banks.
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *	int value that returns the position of the piggy bank in the array if the piggy bank 
	 *	with the id number exists.
	 * Exceptions Thrown: 
	 *	IllegalArgumentException: throws an exception if the idToSearch is negative.
	 */
	public int findPiggyBankId(int idToSearchFor) {
		if (idToSearchFor < 0)
			throw new IllegalArgumentException("Piggy bank id must be greater than or equal to 1000");
		
		for (int i=0; i<piggyBanks.size(); i++)
			if (piggyBanks.get(i).getPiggyBankId()== idToSearchFor)
				return i;
		
		return -1; 
	}
	
	/*
	 * Method: toString
	 * 	This method overrides the tString method and outputs the piggy banks that the owner owns.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	result: String variable that stores the piggy banks. 
	 * Return Values: 
	 *	String with the id number, number of coins of each denomination and total money.
	 * Exceptions Thrown: 
	 *  none
	 */
	@Override
	public String toString() {
		String result=""; 
		if (piggyBanks.get(0) == null)
			return "Piggy Banks";
		
		for (int i=0; i<piggyBanks.size(); i++)
			result  += piggyBanks.get(i).toString() + "\n";
		
		return result; 
	}
}

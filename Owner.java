
public class Owner {
  private String ownerName;
	private int idNumber;
	private PiggyBanks piggyBanksOwned;
	private static int nextAvailableOwnerIdNumber =  100_000_000;
	private static int nextAvailablePiggyBankIdNumber =  100_000_000;
	
	//Constructor that creates a new object by taking in a string for the owner's name and 
	//int id number.
	public Owner(String ownerName) {
		piggyBanksOwned = new PiggyBanks();
		setIdNumber(getNextAvailableOwnerIdNumber());
		setOwnerName(ownerName);
		
	}
	
	public int getNextAvailablePiggyBankIdNumber() {
		return nextAvailablePiggyBankIdNumber++;
	}
	
	public int getNextAvailableOwnerIdNumber() {
		return nextAvailableOwnerIdNumber++;
	}
	
	/*
	 * Method: getters
	 * 	These methods return the class variables after which the getters are named.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	none 
	 * Return Values: *respectively
	 *	PiggyBanks object
	 *	String 
	 *	int
	 *	int array
	 * Exceptions Thrown: 
	 *  none.
	 */
	public PiggyBanks getPiggyBanksOwned() {
		return piggyBanksOwned;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public int getIdNumber() {
		return idNumber;
	}
	public int [] getOwnerPiggyBanks() {
		return getPiggyBanksOwned().getPiggyBankIdNumbers();
	}
	
	/*
	 * Method: setters
	 * 	These methods set the value of the fields of the object.
	 * Parameters:
	 * 	ownerName: String variable that takes in the name of the owner in the setOwnerName method.
	 *	idNumber: int variable that takes in the id number of the owner in the setIdNumber method.
	 * Local Variables:
	 *	none 
	 * Return Values: 
	 *	none
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: If the argument is negative, the program crashes since 
	 *  						  the id number cannot be negative.
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public void setIdNumber(int idNumber) {
		if (idNumber < 1000000)
			throw new IllegalArgumentException("Not enough characters in owner id!");
		this.idNumber = idNumber;
	}
	
	/*
	 * Method: getPiggyBank
	 * 	This method returns a piggy bank from the owner's available piggy banks.
	 * Parameters:
	 * 	piggyBankIdNumber: int parameter that takes in the id number to get a piggy bank. 
	 * Local Variables:
	 *	piggyBankPosition: int variable that records the position of the specific piggy bank in the
	 *					   owner's array.
	 * Return Values: 
	 *	PiggyBank object that has the piggyBankIdNumber.
	 * Exceptions Thrown: 
	 *  none
	 */
	public PiggyBank getPiggyBank(int piggyBankIdNumber) {
		
		
		int piggyBankPosition = getPiggyBankPosition(piggyBankIdNumber);
		
		if (piggyBankPosition == -1)
			return null;
		
		return piggyBanksOwned.getPiggyBank(piggyBankPosition);
	}
	
	/*
	 * Method: getPiggyBankPosition
	 * 	This method gets the position number of the piggy bank with the requested idNumber.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	position: int variable that takes in the position of the piggy bank in the array.
	 * Return Values: 
	 *	int variable that returns the position of the piggy bank in the array.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception when the piggyBankIdNumber is negative.
	 */
	private int getPiggyBankPosition(int piggyBankIdNumber) {
		int position;
		
		if (piggyBankIdNumber<0)
			throw new IllegalArgumentException("Piggy bank Id number cannot be negative");
		
		for (int i=0;i<piggyBanksOwned.getPiggyBanks().length;i++) {
			if (piggyBanksOwned.exists(piggyBankIdNumber)) {
				position = piggyBanksOwned.findPiggyBankId(piggyBankIdNumber);
				return position;
			}
		}
		return -1;
	}
	
	/*
	 * Method: makeNewPiggy
	 * 	This method calls the addPiggyBank method in the PiggyBanks class to create a new piggy 
	 * 	bank for the owner.
	 * Parameters:
	 * 	idNumber: int value that takes in the id number for the new piggy bank to be created.
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *	none.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public void makeNewPiggy() {
		if (idNumber < 0)
			throw new IllegalArgumentException("Id number cannot be negative!");
		
		piggyBanksOwned.addPiggyBank(getNextAvailablePiggyBankIdNumber());
	}
	
	/*
	 * Method: toString
	 * 	This method overrides the toString method and 
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
		return piggyBanksOwned.toString();
	}
	
}

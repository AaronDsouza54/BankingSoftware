
public class Main {
	/*
	 * Method: main
	 * 	The main method starts the program.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	owners: Owner array that stores the owners to run the programs.
	 * Return Values: 
	 *  none
	 * Exceptions Thrown: 
	 *  none
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to AlmaBank!");
		Owner [] owners = initializeOwners();
		
		mainMenu(owners);
	}
	
	/*
	 * Method: initializeOwner
	 * 	This method creates an array of type owner and populates it with random owners. It also 
	 * 	creates a piggy bank for each owner as a default. 
	 * Parameters:
	 * 	none
	 * Local Variables:
	 *	ownerName: String array that stores the names of the owners. 
	 * 	owners: Owner array that stores the Owner objects.
	 * 	piggyBankIdNumbers: int array that stores the piggy bank id numbers.
	 * Return Values: 
	 *  An Owner array is returned. 
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static Owner [] initializeOwners() {
		String [] ownerName = {"Cookie Monster", "Elmo", "Big Bird", "Oscar the Grouch", "Bert"};
		Owner [] owners = new Owner[ownerName.length];
		
		
		for (int i=0;i<ownerName.length;i++) {
			owners[i] = new Owner(ownerName[i]);
			owners[i].makeNewPiggy();
		}
		randomizeBankBalance(owners);
		return owners;	
	}
	
	/*
	 * Method: randomizeBankBalance
	 * 	This method populates each owner's piggy bank with a random bank balance
	 * Parameters:
	 * 	owners: Owner array that takes in the different Owner objects.
	 * Local Variables:
	 * 	piggyBankIdNumbers: int array that stores the piggy bank id numbers.
	 * 	valueToAdd: double variable that randomizes the double value to add to each piggy bank.
	 * Return Values: 
	 *  none.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void randomizeBankBalance(Owner [] owners) {
		int [] piggyBankIdNumbers;
		double valueToAdd;
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		for (Owner currentOwner : owners) {
			piggyBankIdNumbers = currentOwner.getPiggyBanksOwned().getPiggyBankIdNumbers();
			valueToAdd = Utils.randomDoubleBetween(0.01, 200);
			for (int currentIdNumber : piggyBankIdNumbers) {
				currentOwner.getPiggyBank(currentIdNumber).addCoins(valueToAdd);
			}
		}
	}
	
	/*
	 * Method: findOwner
	 * 	This method finds the owner based on the ownerIdNumber.
	 * Parameters:
	 * 	owners: Owner array that stores the Owner objects. 
	 * 	ownerIdNumber: int variable that takes in the id number of the owners. 
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *  Returns Owner object based on the id number entered.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws an exception if the owner id entered is negative or 
	 *  the array is empty.
	 */
	public static Owner findOwner(Owner [] owners, int ownerIdNumber) {
		if (ownerIdNumber < 0)
			throw new IllegalArgumentException("Owner id number cannot be negative!");
		else if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		for (int i=0;i<owners.length;i++) {
			if (owners[i].getIdNumber() == ownerIdNumber) {
				return owners[i];
			}
		}
		return null;
	}
	
	/*
	 * Method: checkPiggyBanks
	 * 	This method checks if the user has piggy banks.
	 * Parameters:
	 * 	owner: Owner object that is to be checked for the existence of piggy banks.
	 * Local Variables:
	 *	none
	 * Return Values: 
	 *  boolean is returned if the owner has piggy banks already.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static boolean checkPiggyBanks(Owner owner) {
		if (owner.getPiggyBanksOwned().getPiggyBanks().length == 0)
			return false;
		return true;
	}
	
	/*
	 * Method: getPiggyBank
	 * 	This method returns a PiggyBank object from the owner's available piggy banks.
	 * Parameters:
	 * 	owner: Owner object that that is checked to get the piggy bank.
	 * Local Variables:
	 *	piggyBankId: prompts the user to enter the id of the piggy bank.
	 * Return Values: 
	 *  PiggyBank object is returned.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static PiggyBank getPiggyBank(Owner owner) {
		int piggyBankId;
		
		do {
			piggyBankId = Utils.inputIntegerLow("Please enter your piggy bank Id number: ",1000);
			if (owner.getPiggyBank(piggyBankId) == null)
				System.out.println("Invalid Piggy Bank Number. Please try again!");
			else
				return owner.getPiggyBank(piggyBankId);
		}while(true);
		
	}
	
	/*
	 * Method: coinOperations
	 * 	This method prompts the user the number of coins they want to add or remove.
	 * Parameters:
	 * 	message: String parameter that takes in whether the user wants to add or remove coins.
	 * Local Variables:
	 *	storeCoins: int array that stores the number of coins of each denomination the user wants
	 *				to add or remove.
	 *	coinType: String array that stores the name of each denomination of coin.
	 * Return Values: 
	 *  int array that returns the number of each type of coin.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static int [] coinOperations(String message) {
		int [] storeCoins = new int[6];
		String [] coinType = {"pennies","nickels","dimes","quarters","loonies","toonies"};
		for (int i=0;i<6;i++)
			storeCoins[i] = Utils.inputIntegerLow("Enter how many "+coinType[i]+" you want to "+message+": ", 
													0);
		return storeCoins;
	}
	
	/*
	 * Method: ownerSignIn
	 * 	This method enables the user to sign in as a particular owner and returns the Owner object
	 * 	associated with the entered id number.
	 * Parameters:
	 * 	owners: Owner array that stores the different owners in the program.
	 * Local Variables:
	 *	ownerIdNumber: int variable that prompts user to enter the id number of teh owner.
	 * Return Values: 
	 *  Owner object that has the id number of entered by the user is returned.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static Owner ownerSignIn(Owner [] owners) {
		int ownerIdNumber;
		Owner owner = null;
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		do {
			ownerIdNumber = Utils.inputIntegerLow("Please enter your 7-digit id number: ", 1_000_000);
			if (findOwner(owners, ownerIdNumber) != null) {
				owner = findOwner(owners, ownerIdNumber);
				System.out.println("\tRecords for "+owner.getOwnerName()+" successfully retrieved");
				return owner;
			}
				
		} while (true);
		
	}
	
	/*
	 * Method: returnCurrentOwners
	 * 	This method formats the owners available and their respective id numbers and returns it 
	 * 	as a String to the user.
	 * Parameters:
	 * 	owners: Owner array that contains the owners in the program.
	 * Local Variables:
	 *	line: String variable that holds a line to print out.
	 *	result: String variable that stores the current owners and their id numbers. 
	 * Return Values: 
	 *  none.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void returnCurrentOwners(Owner [] owners) {
		String line = "--------------------------------------------------------";
		String result = String.format("%-45s %-25s\n","Name of Owner","Id Number");
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		System.out.println("\n"+line);
		System.out.println("Current Owners: \n");
		
		for (Owner currentOwner : owners)
			result += String.format("%-45s %-25d\n",
									currentOwner.getOwnerName(),
									currentOwner.getIdNumber()
									);
		result += line;
		System.out.println(result);
	}
	
	/*
	 * Method: mainMenu
	 * 	This method is the method that initiates the program and gets the user to choose which menu 
	 * 	option they want.
	 * Parameters:
	 * 	owners: Owner array that holds the owners in the program.
	 * Local Variables:
	 *	userChoice: int variable that gains the user input on what menu option they want to choose.
	 *	owner: Owner object that stores the object after the user has given the id number and 
	 *		   the object is found. 
	 * Return Values: 
	 *  none.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void mainMenu(Owner [] owners) {
		int userChoice;
		Owner owner;
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		do {
			
			System.out.println("Main Menu");
			System.out.println("\t1) Deposits Menu");
			System.out.println("\t2) Withdrawals Menu");
			System.out.println("\t3) Special Options Menu");
			System.out.println("\t4) Reports Menu");
			System.out.println("\t5) Accounts Management");
			System.out.println("\t6) Quit Program");
			userChoice = Utils.inputIntegerBetween("\tPlease enter your choice: ", 1, 6);
			
			if (userChoice == 1) {
				returnCurrentOwners(owners);
				owner = ownerSignIn(owners);
				deposits(owner);
			}	
			else if (userChoice == 2) {
				returnCurrentOwners(owners);
				owner = ownerSignIn(owners);
				withdrawals(owner);
			}	
			else if (userChoice == 3) {
				specialOptions(owners);
			}	
			else if (userChoice == 4) {
				reports(owners);
			}
			else if (userChoice == 5) {
				System.out.println("This feature is currently unavailable!");
			}
			else {
				System.out.println("Bye!");
				break;
			}
				
		}while(true);
	}
	
	/*
	 * Method: confirmPiggyCreation
	 * 	This method confirms whether the user wants to create the piggy bank.
	 * Parameters:
	 * 	owner: Owner object to which the piggy bank will be added. 
	 * Local Variables:
	 *	userChoice: char variable that obtains confirmation that the user wants to create the piggy 
	 *				bank.
	 * Return Values: 
	 *  none
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void confirmPiggyCreation(Owner owner) {
		char userChoice;
		
		do {
			userChoice = Utils.obtainYesNo("Are you sure you want to make a new piggy bank? (y-yes or n-no) ");
			if (userChoice == 'Y' || userChoice == 'y') {
				owner.makeNewPiggy();
				System.out.println("New Piggy Bank Created!");
				break;
			}
			else
				break;
		}while (true);
	}
	
	/*
	 * Method: deposits
	 * 	This method is the main method that runs the deposits menu. Based on what the user wants 
	 * 	to do, it will call the appropriate function.
	 * Parameters:
	 * 	owner: Owner object of the owner that the user wants to add money to.
	 * Local Variables:
	 *	userChoice: int variable that takes in the user choice. 
	 * Return Values: 
	 *  none.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void deposits(Owner owner) {
		int userChoice;
		do {
			System.out.println("Customer "+owner.getOwnerName()+" Deposit Menu");
			System.out.println("\t1) View Customer Piggy Banks");
			System.out.println("\t2) Create new Piggy Bank for customer");
			System.out.println("\t3) Perform deposits in Piggy Banks");
			System.out.println("\t4) Back to previous menu");
			userChoice = Utils.inputIntegerBetween("\tPlease enter your choice: ", 1, 4);
			
			if (userChoice == 1 && checkPiggyBanks(owner)) 
				System.out.println(owner.getPiggyBanksOwned());
			else if (userChoice == 2) 
				confirmPiggyCreation(owner);
			else if (userChoice == 3 && checkPiggyBanks(owner)) 
				handleDeposit(owner);
			else if (userChoice == 4)
				break;
			else 
				System.out.println("You don't have any piggy banks available!");
		}while(true);
	}
	
	/*
	 * Method: handleDeposit
	 * 	This method runs if the user wants to deposit a value into the current piggy bank.
	 * Parameters:
	 * 	owner: Owner object to deposit a value to a deposit.
	 * Local Variables:
	 *	userChoice: int variable that takes in what the user wants to do.
	 *	moneyToAdd: double variable that takes in how much money the user wants to add if want to 
	 *				deposit by value.
	 *	storeCoins: int array that stores the number of coins of each denomination the user wants 
	 *				to add if they want to add by coin.
	 * Return Values: 
	 *  none
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void handleDeposit(Owner owner) {
		int userChoice;
		double moneyToAdd;
		int [] storeCoins = new int[6];
		PiggyBank piggyBankToDeposit = getPiggyBank(owner);
		do {
			System.out.println("Deposit Piggy Bank Acount Number "+piggyBankToDeposit.getPiggyBankId());
			System.out.println("\t1) Deposit by coin");
			System.out.println("\t2) Deposit by value");
			System.out.println("\t3) Back to previous menu");
			userChoice = Utils.inputIntegerBetween("\tPlease enter your choice: ", 1, 3);
			if (userChoice == 1) {
				storeCoins = coinOperations("enter");
				confirmDeposit(
								"Are you sure you want to add: \n\tPennies: "+storeCoins[0]+
								"\n\tNickels: "+storeCoins[1]+
								"\n\tDimes: "+storeCoins[2]+
								"\n\tQuarters: "+storeCoins[3]+
								"\n\tLoonies: "+storeCoins[4]+
								"\n\tToonies: "+storeCoins[5]+
								"\n\tDo you confirm? ",
								storeCoins,
								piggyBankToDeposit
								);
			}
			else if (userChoice == 2) {
				moneyToAdd = Utils.inputDoubleLow("Please enter how much money to deposit: ", 0);
				confirmDeposit(
								"Are you sure you want to add $"+moneyToAdd+" to the piggy bank? ",
								piggyBankToDeposit,
								moneyToAdd
								);
			}
			else
				break;
			
		} while(true);
		
	}
	
	/*
	 * Method: confirmDeposit
	 * 	This method confirms with the user that they want to add some value to the piggy bank.
	 * Parameters:
	 * 	message: String variable that prints out a message for the user.
	 * 	piggyBankToDeposit: PiggyBank object to which the coins need to be added.
	 * 	moneyToAdd: double variable that stores how much money to add. 
	 * Local Variables:
	 *	userChoice: char variable that takes in whether the user wants to go ahead with the deposit.
	 * Return Values: 
	 *  void.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void confirmDeposit(String message, 
										PiggyBank piggyBankToDeposit, 
										double moneyToAdd
										) {
		char userChoice;
		userChoice = Utils.obtainYesNo(message);
		if (userChoice == 'Y' || userChoice == 'y') {
			piggyBankToDeposit.addCoins(moneyToAdd);
			System.out.println("$"+moneyToAdd+" has been added to piggy bank #"+piggyBankToDeposit.getPiggyBankId());
		}
		else
			System.out.println("Ok, bye!");
	}
	
	/*
	 * Method: confirmDeposit
	 * 	This method confirms with the user that they want to add some value to the piggy bank.
	 * Parameters:
	 * 	message: String variable that prints out a message for the user.
	 * 	source: int array that stores how many of each coin type to be added to the total. 
	 * 	piggyBankToDeposit: PiggyBank object to which the coins need to be added.
	 * Local Variables:
	 *	userChoice: char variable that takes in whether the user wants to go ahead with the deposit.
	 * Return Values: 
	 *  void.
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void confirmDeposit(String message, 
										int [] source, 
										PiggyBank piggyBankToDeposit
										) {
		char userChoice;
		userChoice = Utils.obtainYesNo(message);
		
		if (source.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		if (userChoice == 'Y' || userChoice == 'y')
			piggyBankToDeposit.addCoins(
										source[0], 
										source[1], 
										source[2], 
										source[3], 
										source[4], 
										source[5]
										);
		else
			System.out.println("Ok, bye!");
	}

	/*
	 * Method: withdrawals
	 * 	This method is the main menu for withdrawing money. 
	 * Parameters:
	 * 	owner: Owner object that brings in the owner object of the owner who wants to withdraw. 
	 * Local Variables:
	 *	userChoice: int variable that takes in what the user wants to do inside the withdrawals menu. 
	 * Return Values: 
	 *  void.
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void withdrawals(Owner owner) {
		int userChoice;
		
		do {
			System.out.println(owner.getOwnerName()+" Withdrawal menu");
			System.out.println("\t1) View Customer Piggy Banks");
			System.out.println("\t2) Perform Withdrawal on Piggy Bank");
			System.out.println("\t3) Close Piggy Bank withdraw funds");
			System.out.println("\t4) Return to Main Menu");
			userChoice = Utils.inputIntegerBetween("\tPlease enter your choice: ", 1, 4);
			
			if (userChoice == 1 && checkPiggyBanks(owner))
				System.out.println(owner.getPiggyBanksOwned());
			else if (userChoice == 2 && checkPiggyBanks(owner))
				withdrawalFromPiggyBank(owner);
			else if (userChoice == 3)
				System.out.println("The feature is currently unavailable!");
			else if (userChoice == 4)
				break;
			else 
				System.out.println("You don't have a piggy bank available!");
			
		}while(true);
	}
	
	/*
	 * Method: withdrawalFromPiggyBank
	 * 	This method runs the menu option for what the user wants to do if they want to withdraw
	 * 	money. 
	 * Parameters:
	 * 	owner: Owner object that gives the owner object being changed.
	 * Local Variables:
	 *	userChoice: int variable that takes in what the user wants to do. 
	 *	moneyToRemove: double variable that stores the value that the user wants to remove from
	 *				   the piggy bank.
	 * Return Values: 
	 *  void
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void withdrawalFromPiggyBank(Owner owner) {
		int userChoice;
		double moneyToRemove;
		PiggyBank piggyBankToWithdraw = getPiggyBank(owner);
		do {
			System.out.println("Withdraw Piggy Bank Acount Number "+piggyBankToWithdraw.getPiggyBankId());
			System.out.println("\t1) Withdraw by coin");
			System.out.println("\t2) Withdraw by value");
			System.out.println("\t3) Back to previous menu");
			userChoice = Utils.inputIntegerBetween("\tPlease enter your choice: ", 1, 3);
			
			if (userChoice == 1) {
				checkWithdrawal(
								coinOperations("remove"),
								piggyBankToWithdraw
								);
			}
			else if (userChoice == 2) {
				moneyToRemove = Utils.inputDoubleLow("Please enter how much money to withdraw: ", 0);
				checkWithdrawal(
								moneyToRemove,
								piggyBankToWithdraw
								);
			}
			else
				break;
		}while(true);
	}
	
	/*
	 * Method: checkWithdrawal
	 * 	This method checks if there is enough money in the piggy bank for the value that the 
	 * 	user wants to remove. 
	 * Parameters:
	 * 	value: money that the user wants to remove. 
	 * 	piggyBankToWithdraw: PiggyBank object from which the money is being withdrawn.
	 * Local Variables:
	 *	currentTotal: current total, in pennies, in the PiggyBank object.
	 * Return Values: 
	 *  void
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void checkWithdrawal(double value, 
										PiggyBank piggyBankToWithdraw
										) {
		double currentTotal;
		currentTotal = piggyBankToWithdraw.getPiggyBankTotal();
		if (value <= currentTotal)
			confirmWithdrawals("Are you sure you want to remove $"+value+" from the piggy bank? ",
								piggyBankToWithdraw,
								value
								);
		else
			System.out.println("Not enough money in the piggy bank!");
	}
	
	/*
	 * Method: checkWithdrawals
	 * 	This method checks if there is enough money in the piggy bank for the value that the 
	 * 	user wants to remove. 
	 * Parameters:
	 * 	source: int array that has the number of coins of each denomination for the user to 
	 * 			remove. 
	 * 	piggyBankToWithdraw: PiggyBank object from which the money is being withdrawn.
	 * Local Variables:
	 *	currentTotal: current total, in pennies, in the PiggyBank object.
	 * Return Values: 
	 *  void
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception of array is empty.
	 */
	public static void checkWithdrawal(int [] source, 
										PiggyBank piggyBankToWithdraw
										) {
		double currentTotal;
		int totalToWithdraw = 0;
		
		currentTotal = piggyBankToWithdraw.getPiggyBankTotal();
		
		if (source.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		for (int i=0;i<source.length;i++)
			totalToWithdraw += source[i];
		
		if (totalToWithdraw <= currentTotal)
			confirmWithdrawals(
								"Are you sure you want to add: \n\tPennies: "+source[0]+
								"\n\tNickels: "+source[1]+
								"\n\tDimes: "+source[2]+
								"\n\tQuarters: "+source[3]+
								"\n\tLoonies: "+source[4]+
								"\n\tToonies: "+source[5]+
								"\n\tDo you confirm? ",
								source,
								piggyBankToWithdraw
								);
		else
			System.out.println("Not enough money in the piggy bank!");
	}
	
	
	/*
	 * Method: confirmWithdrawals
	 * 	This method confirms if the user wants to remove the value they have entered.  
	 * Parameters:
	 * 	message: String variable for the message that is shown to the user. 
	 * 	source: int array that has the number of coins of each denomination for the user to 
	 * 			remove.
	 * 	piggyBankToWithdraw: PiggyBank object from which the money is being withdrawn.
	 * Local Variables:
	 *	userChoice: char variable that confirms if the user wants to remove a certain value from
	 *				the piggy bank.
	 * Return Values: 
	 *  void 
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void confirmWithdrawals(String message, 
											int [] source, 
											PiggyBank piggyBankToWithdraw
											) {
		char userChoice;
		userChoice = Utils.obtainYesNo(message);
		
		if (source.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		if (userChoice == 'Y' || userChoice == 'y')
			piggyBankToWithdraw.removeCoins(source[0], 
											source[1], 
											source[2], 
											source[3], 
											source[4], 
											source[5]
											);
		else
			System.out.println("Ok, bye!");
	}
	
	/*
	 * Method: confirmWithdrawals
	 * 	This method confirms if the user wants to remove the value they have entered.  
	 * Parameters:
	 * 	message: String variable for the message that is shown to the user. 
	 * 	moneyToRemove: double value that the user wants to subtract from the piggy bank.
	 * 	piggyBankToWithdraw: PiggyBank object from which the money is being withdrawn.
	 * Local Variables:
	 *	userChoice: char variable that confirms if the user wants to remove a certain value from
	 *				the piggy bank.
	 * Return Values: 
	 *  void 
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static void confirmWithdrawals(String message, 
											PiggyBank piggyBankToWithdraw, 
											double moneyToRemove
											) {
		char userChoice;
		userChoice = Utils.obtainYesNo(message);
		if (userChoice == 'Y' || userChoice == 'y') {
			piggyBankToWithdraw.removeCoins(moneyToRemove);
			System.out.println("$"+moneyToRemove+" has been removed from piggy bank #"+piggyBankToWithdraw.getPiggyBankId());
		}
		else
			System.out.println("Ok, bye!");
	}
	
	/*
	 * Method: specialOptions
	 * 	This method is the main method for the special options menu.
	 * Parameters:
	 * 	owners: Owner array that stores the owners in the program.
	 * Local Variables:
	 *	userChoice: int variable that takes in what the user wants to do. 
	 * Return Values: 
	 *  void
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void specialOptions(Owner [] owners) {
		int userChoice;
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		do {
			System.out.println("Special Options Menu");
			System.out.println("\t1) It's Raining Coins");
			System.out.println("\t2) The Taxman Cometh");
			System.out.println("\t3) Back to previous menu");
			userChoice = Utils.inputIntegerBetween("\tPlease enter your choice: ",1,3);
			if (userChoice == 1)
				itsRainingCoins(owners);
			else if (userChoice == 2)
				theTaxmanCometh(owners);
			else
				break;
			
		}while (true);
	}

	/*
	 * Method: itsRainingCoins
	 * 	This method runs the menu option its raining coins where a random predetermined value 
	 * 	is added to all piggy banks. 
	 * Parameters:
	 * 	owners: Owner array that brings in the current owners. 
	 * Local Variables:
	 *	ownerPiggyBankIdNumbers: int array that stores the piggy bank id numbers of all the 
	 *							 piggy banks of every owner.
	 *	coinPos: int variable that returns the position of the denomination of coin that is added.
	 *	coin: double array that stores value of each coin type. 
	 *	numCoins: gets a random int value that stands for the number of coins to be added to the 
	 *			  piggy bank.
	 *	valueToAdd: int variable that gets the product of numCoins and coin to get the amount of
	 *				money to add to each piggy bank.
	 * Return Values: 
	 *  void 
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if the array in the argument is empty.
	 */
	public static void itsRainingCoins(Owner [] owners) {
		int [] ownerPiggyBankIdNumbers;
		int coinPos = generateRandomCoin();
		double [] coin = {0.01,0.05,0.10,0.25,1.00,2.00};
		int numCoins = Utils.randomIntBetween(10, 20);
		double valueToAdd = coin[coinPos]*numCoins;
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		for (Owner currentOwner : owners) {
			ownerPiggyBankIdNumbers = currentOwner.getPiggyBanksOwned().getPiggyBankIdNumbers();
			
			for (int currentIdNumber : ownerPiggyBankIdNumbers) {
				currentOwner.getPiggyBank(currentIdNumber).addCoins(valueToAdd);
				System.out.println(currentOwner);
			}
		}
	}
	
	/*
	 * Method: generateRandomCoin
	 * This method generates a random coin number where 1 represents pennies, 2 nickels, 3 dimes,
	 * 4 quarters, 5 loonies, and 6 toonies. The respective probabilities of each coin are 50%, 25%, 12%,
	 * 8%, 3% and 2% respectively.
	 * Parameters:
	 * 	none
	 * Local Variables:
	 * randomNumber: int variable used to obtain a random number from 1 to 100.
	 * probabilities: integer array containing the percentage probability of each coin type. 
	 * cumulativeProbability: integer value used to add previous boundry value to probablity value from the array.
	 * Return Values: 
	 *  int value is returned representing the coin number outlined above. 
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static int generateRandomCoin() {
		int [] probabilities = {50, 25, 12, 8, 3, 2};
		int randomValue = Utils.randomIntBetween(1,100);
		int cumulativeProbability = 0;
	        
		for (int i = 0; i < probabilities.length; i++) {
			cumulativeProbability += probabilities[i];
			if (randomValue <= cumulativeProbability) {
				return i + 1; // Coin types are 1-indexed
			}
		}
		// Unlikely to occur as all cases taken into account above, 
		// but required since method has a return value. 
		return -1;
	}
	
	/*
	 * Method: theTaxmanCometh
	 * 	This method deducts 10% of the money in the account for taxes, unless it is less than $10. 
	 * Parameters:
	 * 	owners: Owner array that contains the owners in the system.
	 * Local Variables:
	 *	ownerPiggyBankIdNumbers: int array that stores the id numbers of all the piggy banks.
	 *	valueInAccount: double value that takes in the value that is currently in the piggy bank.
	 *	valueToRemove: double value to remove from the piggy bank.
	 * Return Values: 
	 *  void
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if the array is empty.
	 */
	public static void theTaxmanCometh(Owner [] owners) {
		int [] ownerPiggyBankIdNumbers;
		double valueInAccount;
		double valueToRemove;
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		for (Owner currentOwner : owners) {
			ownerPiggyBankIdNumbers = currentOwner.getPiggyBanksOwned().getPiggyBankIdNumbers();
			
			for (int currentIdNumber : ownerPiggyBankIdNumbers) {
				valueInAccount = currentOwner.getPiggyBank(currentIdNumber).getPiggyBankTotal();
				if (valueInAccount <= 10) {
					System.out.println("The piggy bank does not have enough money");
					System.out.println(currentOwner);
					continue;
				}
				else {
					valueToRemove = valueInAccount*0.1;
					currentOwner.getPiggyBank(currentIdNumber).removeCoins(valueToRemove);
				}
				System.out.println(currentOwner);
			}	
		}
	}
	
	/*
	 * Method: reports
	 * 	This method prints the report of the number of each type of coin in the owner's piggy 
	 * 	banks. 
	 * Parameters:
	 * 	owners: Owner array that brings in the different owner accounts in the program.
	 * Local Variables:
	 *	none.
	 * Return Values: 
	 *  An Owner array is returned. 
	 * Exceptions Thrown: 
	 *  IllegalArgumentException: throws exception if array is empty.
	 */
	public static void reports(Owner [] owners) {
		System.out.println("Current Owners: \n");
		
		if (owners.length == 0)
			throw new IllegalArgumentException("Cannot operate on an empty array!");
		
		for (Owner currentOwner : owners) {
			System.out.println(currentOwner.getOwnerName() + 
								"\tUser Id: "+
								currentOwner.getIdNumber()
								);
			System.out.println(
								formatString(currentOwner)
								);
		}
	}
	
	/*
	 * Method: formatString
	 * 	This method formats the report so that it is more appealing to users.
	 * Parameters:
	 * 	owner: Owner object for the owner who's piggy banks are being outputted. 
	 * Local Variables:
	 *	ownerPiggyBankIdNumbers: int array that gets the user's piggy banks' id numbers. 
	 *	result: String variable that stores the formatted string.
	 * Return Values: 
	 *  String containing the report information formatted. 
	 * Exceptions Thrown: 
	 *  none.
	 */
	public static String formatString(Owner owner) {
		int [] ownerPiggyBankIdNumbers = owner.getOwnerPiggyBanks();
		String result = String.format("%-30s %-10s %-10s %-10s %-10s %-10s %-10s %-25s\n", 
										"Account Number", 
										"Pennies", 
										"Nickels", 
										"Dimes", 
										"Quarters", 
										"Loonies", 
										"Toonies",
										"Total"
										);
		
		for (int i =0; i<ownerPiggyBankIdNumbers.length;i++) {
			result += String.format("%-30d %-10d %-10d %-10d %-10d %-10d %-10s %-25s\n", 
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getPiggyBankId(), //Id number for piggy bank
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getPennies(), //Number of Pennies 
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getNickels(), //Number of Nickels 
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getDimes(), //Number of Dimes 
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getQuarters(), //Number of Quarters 
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getLoonies(), //Number of Loonies 
									owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getToonies(), //Number of Toonies 
									Utils.formatCurrency(
															owner.getPiggyBank(ownerPiggyBankIdNumbers[i]).getPiggyBankTotal()
															//Total money in the piggy bank
														)
									);
		}
		return result;
	}
}

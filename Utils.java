// Utils Version 3.0 - Employs Generic Classes.
import java.util.Scanner;
import java.util.Random; 
import java.text.NumberFormat;
import java.util.Locale; 

public class Utils {
	public static boolean isInteger(String stringToCheck) {
		return isNumeric(stringToCheck,Integer.class);
	}
	
	public static boolean isDouble(String stringToCheck) {
		return isNumeric(stringToCheck,Double.class);
	}
	
	public static double inputDouble(String message) {
	    return inputNumber(message,Double.class); 
    }    
	
	public static int inputInteger(String message) {
		return inputNumber(message,Integer.class);
	}
	
	public static int inputIntegerHigh(String message,int highBound) {
		return inputNumberHigh(message,highBound,Integer.class);
	} 

    public static double inputDoubleHigh(String message,double highBound) {
    	return inputNumberHigh(message,highBound,Double.class);
	} 
    
    public static double inputDoubleLow(String message,double lowBound) {
		return inputNumberLow(message,lowBound,Double.class); 
	} 
    
    public static int inputIntegerLow(String message,int lowBound) {
    	return inputNumberLow(message,lowBound,Integer.class);
	} 
	
    public static double inputDoubleBetween(String message,double lowBound,double highBound) {
			return inputNumberBetween(message, lowBound,highBound,Double.class); 
    } 
    
    public static int inputIntegerBetween(String message,int lowBound,int highBound) {
    		return inputNumberBetween(message,lowBound,highBound,Integer.class);
	} 
    
	public static char obtainYesNo(String message) {
	    Scanner userInput = new Scanner(System.in);
	    String result; 
	     
	    do {
	    	System.out.print(message);
	    	result = userInput.nextLine().toLowerCase(); 
	    
	    	if (result.equals("y") || result.equals("n"))
	    		break; 
	    	else
	    		System.out.println("Be sure to enter either y or n only.");	
	    } while (true); 
	    return result.charAt(0);
	}
    
    private static <T extends Comparable<T>> T inputNumberHigh(String message, T highBound,Class <T> numberClass) {
    	T result;
        do {
            result = inputNumber(message, numberClass);

            if (result.compareTo(highBound) > 0)
                System.out.println("Please enter a value less than or equal to " + highBound);
            else
                break;
        } while (true);

        return result;
    }

    private static <T extends Comparable<T>> T inputNumberLow(String message, T lowBound,Class <T> numberClass) {
    	T result;
        do {
            result = inputNumber(message, numberClass);

            if (result.compareTo(lowBound) < 0)
                System.out.println("Please enter a value greater than or equal to" + lowBound);
            else
                break;
        } while (true);

        return result;
    }
    
    private static <T extends Comparable<T>> T inputNumberBetween(String message, T lowBound, T highBound, Class <T> numberClass) {
    	T result;
        do {
            result = inputNumber(message, numberClass);

            if (result.compareTo(lowBound) < 0 || result.compareTo(highBound) > 0)
                System.out.println("Please enter a value between " + lowBound + " and " + highBound + " inclusive.");
            else
                break;
        } while (true);

        return result;
    }

    private static <T> T inputNumber(String message, Class <T> numberClass) {
        Scanner userInput = new Scanner(System.in);
        String result;

        do {
        	System.out.print(message);
            result = userInput.nextLine();

            if (isNumeric(result, numberClass))
                break;
            else
                System.out.println("Please enter a valid " + getNumberClassName(numberClass) + "!");
        } while (true);
    	
        if (numberClass == Integer.class) 
            return numberClass.cast(Integer.parseInt(result));
        else if (numberClass == Double.class) 
            return numberClass.cast(Double.parseDouble(result));
        else 
            throw new IllegalArgumentException("Unsupported number class");
    }
    
    private static <T> String getNumberClassName(Class<T> numberClass) {
        if (numberClass == Integer.class) 
            return "integer";
        else if (numberClass == Double.class) 
            return "decimal number";
        else 
            throw new IllegalArgumentException("Unsupported number class");
    }
	
	private static <T> boolean isNumeric(String stringToCheck, Class <T> numberClass) {
		try {
			if (numberClass == Integer.class) {
                Integer.parseInt(stringToCheck);
                return true; 
            } 
			else if (numberClass == Double.class) {
                Double.parseDouble(stringToCheck);
                return true;
            } 
            else 
                throw new IllegalArgumentException("Unsupported number class");
		}
		catch (NumberFormatException e) {
            return false;
        }
	}
	
	public static double roundDouble(double value,int decimalPlaces) {			
		if (decimalPlaces <0)
			throw new IllegalArgumentException(); 
		return Math.round(value* Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
	}
	
	public static int randomIntBetween(int low, int high) {
		Random rand = new Random(); 
		return rand.nextInt(high - low + 1) + low; 
	}
	
	public static String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
        return currencyFormatter.format(amount); 
    }
	
    public static double randomDoubleBetween(double low, double high) {
        Random rand = new Random(); 
        if (low >= high) 
            throw new IllegalArgumentException("High value must be greater than low value");
        return low + (high - low) * rand.nextDouble();
    }
}

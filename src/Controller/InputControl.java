package Controller;
import java.util.Scanner;

/**
 * To check that user provides correct input
 * @author vince
 *
 */
public class InputControl {

	public InputControl() {}
	
	/**
	 * Check for correct integer input between a given upper limit and lower limit
	 * @param lowRange Lower limit of selection
	 * @param highRange Upper limit of selection
	 * @return
	 */
	public static int integerInput(int lowRange, int highRange) {
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextInt()){
			System.err.println("Error... Please input an Integer:");
			scan.next();
		}
		int choice = scan.nextInt();
		if (choice < lowRange || choice > highRange) {
			System.err.printf("Error... Please input an Integer between %d-%d:\n",lowRange,highRange);
			return integerInput(lowRange,highRange);
		}else {
			return choice;
		}
	}
	
	/**
	 * Checks for string input from user
	 * @return
	 */
	public static String stringInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.next();
		return input;
	}
	
	/**
	 * Checks for string input from user includes whitespace
	 * @return
	 */
	public static String lineInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
	}

	
	/**
	 * Checks for float input from user between a upper limit and a lower limit
	 * @param lowRange Lower limit of selection
	 * @param highRange Upper limit of selection
	 * @return
	 */
	public static float floatInput(int lowRange, int highRange) {
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextFloat()){
			System.err.println("Error... Please input a float:");
			scan.next();
		}
		float choice = scan.nextFloat();
		if (choice < (float)lowRange || choice > (float)highRange) {
			System.err.printf("Error... Please input a float between %d-%d:\n",lowRange,highRange);
			return floatInput(lowRange,highRange);
		}else{
			return choice;
		}
	}
}

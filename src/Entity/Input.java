package Entity;
import java.util.Scanner;

/**
 * To check for user inputs
 * @author vince
 *
 */
public class Input {

	public Input() {
	}
	
	/**
	 * Checks that user input falls between 2 numbers
	 * @param lowRange Lower limit of user input
	 * @param highRange Upper limit of user input
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
			integerInput(lowRange,highRange);
		}
		return choice;
	}
	
	/**
	 * Check that user provides a String as input
	 * @return
	 */
	public static String stringInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.next();
		return input;
	}

}

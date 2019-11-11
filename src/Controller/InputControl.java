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
	public static String seatInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if ((int) input.charAt(0) <65 || (int) input.charAt(0) > 90) {
			System.out.println("Please enter a valid seat selection");
			return seatInput();
		}
		else if ((int) input.charAt(1) <48 || (int) input.charAt(1)>57) {
			System.out.println("Please enter a valid seat selection");
			return seatInput();
		}
		else if (input.length()!=2) {
			System.out.println("Please enter a valid seat selection");
			return seatInput();
		}
		else
			return input;
	}
	
	public static char ynInput() {
		Scanner scan = new Scanner(System.in);
		char input = scan.next().charAt(0);
		if (input != 'Y' && input != 'N') {
			System.out.println("Please enter Y/N");
			return ynInput();
		}
		else
			return input;
	}
	public static String statusInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if (input.equals("Coming Soon")||input.equals("Preview")||input.equals("Showing"))
			return input;			
		else {
			System.out.println("Please enter: Coming Soon/Preview/Showing");
			return statusInput();
		}
	}
	public static String movieTypeInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if (input.equals("3D")||input.equals("Blockbuster"))
			return input;			
		else {
			System.out.println("Please enter: 3D/Blockbuster");
			return movieTypeInput();
		}
	}
	public static String ageRatingInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if (input.equals("3D")||input.equals("PG13")||input.equals("NC16")||input.equals("M18")||input.equals("R21"))
			return input;			
		else {
			System.out.println("Please enter: G/PG13/NC16/M18/R21");
			return ageRatingInput();
		}
	}
	
}

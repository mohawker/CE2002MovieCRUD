package Assignment;
import java.util.Scanner;

public class InputHandler {

	public InputHandler() {
	}
	
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
		}
		else {
		return choice;
		}
	}
	
	public static String stringInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.next();
		return input;
	}

}

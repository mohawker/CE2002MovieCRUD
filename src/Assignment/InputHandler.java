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
	
	public static float priceInput(int lowRange, int highRange) {
		
		float input = 0;
		Scanner scan = new Scanner(System.in);
		if (scan.hasNextInt()) {
			input = (float) scan.nextInt();

		} else if(scan.hasNextFloat()) {
			input = scan.nextFloat();

		} else {
			scan.next(); 
		}
		if (input < (float)lowRange || input > (float)highRange) {
			System.err.printf("Error... Please input an Integer between %d-%d:\n",lowRange,highRange);
			return priceInput(lowRange,highRange);
		}
		else {
			return input;
		}
	}

}

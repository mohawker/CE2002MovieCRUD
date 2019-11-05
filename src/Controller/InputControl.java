package Controller;
import java.util.Scanner;

public class InputControl {

	public InputControl() {}
	
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
	
	public static String stringInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.next();
		return input;
	}

	public static float priceInput(int lowRange, int highRange) {
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextFloat()){
			System.err.println("Error... Please input a float:");
			scan.next();
		}
		float choice = scan.nextFloat();
		if (choice < (float)lowRange || choice > (float)highRange) {
			System.err.printf("Error... Please input a float between %d-%d:\n",lowRange,highRange);
			return priceInput(lowRange,highRange);
		}else{
			return choice;
		}
	}
}

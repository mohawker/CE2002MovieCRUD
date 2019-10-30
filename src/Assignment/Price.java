package Assignment;

import java.util.Scanner;

public class Price {
	private int age;
	private String cinemaType;
	private boolean weekendPH = false;
	private String movieType;
	private float price = 8;
	private static float basePrice = 8;
	private static float multAge = 0.5f;
	private static float mult3D = (float) 0.75;
	private static float multGC = (float) 2.0;
	private static float surBlockbuster = 3;
	private static float surPHWeekend = 5;
	
	public Price() {
	}
	
	public void generatePrice(int age, String movieType, String cinemaType, String date) {
		this.age = age;
		this.weekendPH = DateChecker.checkSpecialDate(date);
		this.movieType = movieType;
		this.cinemaType = cinemaType;		
		
		if ( this.age<=6 || this.age >= 55) {
			this.price = Price.basePrice*Price.multAge;
		}
		if (this.movieType.equals("3D")) {
			this.price = Price.basePrice*Price.mult3D;
		}
		if (this.movieType.equals("Blockbuster")) {
			this.price = Price.basePrice + Price.surBlockbuster;
		}
		if(this.cinemaType.equals("GoldClass")) {
			this.price = Price.basePrice*Price.multGC;
		}
		if (this.weekendPH == true) {
			this.price = Price.basePrice + Price.surPHWeekend;
		}
	}
	
	public void printBreakdown() {
		System.out.println("=== Price Breakdown ===");
		float currPrice = Price.basePrice;
		System.out.printf("Base Price: $%.2f\n", Price.basePrice);
		if ( this.age<=6 || this.age >= 55) {
			System.out.printf("Child/Senior Citizen Discount: -$%.2f\n", Price.basePrice*Price.multAge);
			currPrice = currPrice*(1-Price.multAge);
		}		
		if (this.movieType.equals("3D")) {
			System.out.printf("3D Movie Multiplier: +$%.2f\n", currPrice*Price.mult3D);
			currPrice = currPrice*(Price.mult3D+1);
		}
		if (this.movieType.equals("Blockbuster")) {
			System.out.printf("Blockbuster Movie Surcharge: +$%.2f\n", Price.surBlockbuster);
			currPrice += Price.surBlockbuster;
		}
		if(this.cinemaType.equals("GoldClass")) {
			System.out.printf("Gold Class Movie Multiplier: +$%.2f\n", currPrice*Price.multGC);
			currPrice = currPrice*(Price.multGC+1);
		}
		if (this.weekendPH == true) {
			System.out.printf("Public Holiday/Weekend Movie Surcharge: +$%.2f\n", Price.surPHWeekend);
			currPrice += Price.surPHWeekend;
		}
		System.out.printf("Final Price: $%.2f\n", currPrice);
	}
	
	public static void updatePrices() {
		System.out.println("=== Prices & Surcharges & Multiplers ===");
		System.out.println("[1] Multiplier for 3D Movies");
		System.out.println("[2] Surcharge for Blockbuster Movies");
		System.out.println("[3] Multipler for GoldClass Cinemas");
		System.out.println("[4] Child & Senior Citizen Discount");
		System.out.println("[5] Weekend & Public Holiday Surcharge");
		System.out.println("[6] Ticket Base Price");
		System.out.print("Select option: ");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		System.out.println();
		switch(choice) {
			case 1:
				System.out.println("=== Multiplier for 3D Movies ===");
				System.out.printf("Current Multiplier: %.2f\n", Price.mult3D);
				System.out.print("Input New Multiplier for 3D Movies: ");
				Price.mult3D = scan.nextFloat();
				System.out.printf("New rate: %.2f\n", Price.mult3D);
				break;
			case 2:
				System.out.println("=== Surcharge for Blockbuster Movies ===");
				System.out.printf("Current Surcharge: %.2f\n", Price.surBlockbuster);
				System.out.print("Input New Surcharge: ");
				Price.surBlockbuster = scan.nextFloat();
				System.out.printf("New surcharge: %.2f\n", Price.surBlockbuster);
				break;
			case 3:
				System.out.println("=== Multipler for GoldClass Cinemas ===");
				System.out.printf("Current rate: %.2f\n", Price.multGC);
				System.out.print("Input new rate for Gold Class Cinema: ");
				Price.multGC = scan.nextFloat();
				System.out.printf("New rate: %.2f\n", Price.multGC);
				break;
			case 4:
				System.out.println("=== Child & Senior Citizen Discount ===");
				System.out.printf("Current discount: %.2f\n", Price.multAge);
				System.out.print("Input new discount from (0.00-1.00): ");
				Price.multAge = scan.nextFloat();
				System.out.printf("New discount: %.2f\n", Price.multAge);
				break;
			case 5:
				System.out.println("=== Weekend & Public Holiday Surcharge ===");
				System.out.printf("Current surcharge: %.2f\n", Price.surPHWeekend);
				System.out.print("Input new weekend/public holiday surcharge: ");
				Price.surPHWeekend = scan.nextFloat();
				System.out.printf("New surcharge: %.2f\n", Price.surPHWeekend);
				break;
			case 6:
				System.out.println("=== Ticket Base Price ===");
				System.out.printf("Current base price: $%.2f\n", Price.basePrice);
				System.out.print("Input new base price for tickets: ");
				Price.basePrice = scan.nextFloat();
				System.out.printf("New base price: $%.2f\\n"+ Price.basePrice);
				break;
				
			default:
				System.out.println("Invalid choice");
		}		
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getPrice() {
		return this.price;
	}
}

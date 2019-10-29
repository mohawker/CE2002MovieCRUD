package movieAssignment;

import java.util.Scanner;

public class Price {
	private int age;
	private String cinemaType;
	private boolean weekendPH = false;
	private String movieType;
	private float price = 8;
	private static float basePrice = 8;
	private static float multAge = 0.5f;
	private static float mult3D = 3;
	private static float multGC = 5;
	private static float surBlockbuster = 3;
	private static float surPHWeekend = 5;
	
	public Price() {
	}
	
	public void generatePrice(int age, String movieType, String cinemaType, boolean date) {
		this.age = age;
		this.weekendPH = date;
		this.movieType = movieType;
		this.cinemaType = cinemaType;		
		
		if ( this.age<=6 || this.age >= 55) {
			this.price = Price.basePrice*Price.multAge;
		}
		if (this.movieType == "3D") {
			this.price = Price.basePrice*Price.mult3D;
		}
		if (this.movieType == "Blockbuster") {
			this.price = Price.basePrice + Price.surBlockbuster;
		}
		if(this.cinemaType == "Gold Class") {
			this.price = Price.basePrice*Price.multGC;
		}
		if (this.weekendPH == true) {
			this.price = Price.basePrice + Price.surPHWeekend;
		}
		
	}
	
	public static void updatePrices() {
		System.out.println(" --Change Pricings for--");
		System.out.println("1. Multiplier for 3D Movies");
		System.out.println("2. Surcharge for Blockbuster Movies");
		System.out.println("3. Type of cinema");
		System.out.println("4. Age of movie-goer");
		System.out.println("5. Weekened/PH multiplier");
		System.out.println("6. Change base price");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		switch(choice) {
			case 1:
				System.out.println("Current rate: "+mult3D);
				System.out.println("Input new rate for 3D Movies: ");
				Price.mult3D = scan.nextFloat();
				System.out.println("New rate: "+mult3D);
				break;
			case 2:
				System.out.println("Current surcharge: +"+surBlockbuster);
				System.out.println("Input new surcharge for: ");
				Price.surBlockbuster = scan.nextFloat();
				System.out.println("New surcharge: +"+surBlockbuster);
				break;
			case 3:
				System.out.println("Current rate: "+Price.multGC);
				System.out.println("Input new rate for Gold Class Cinema: ");
				Price.multGC = scan.nextFloat();
				System.out.println("New rate: "+Price.multGC);
				break;
			case 4:
				System.out.println("Current discount: "+ Price.multAge);
				System.out.println("Input new discount from (0.00-1.00): ");
				Price.multAge = scan.nextFloat();
				System.out.println("New discount: "+ Price.multAge);
				break;
			case 5:
				System.out.println("Current surcharge: +"+ Price.surPHWeekend);
				System.out.println("Input new weekend/public holiday surcharge: ");
				Price.surPHWeekend = scan.nextFloat();
				System.out.println("New surcharge: +"+ Price.surPHWeekend);
				break;
			case 6:
				System.out.println("Current base price: "+ Price.basePrice);
				System.out.println("Input new base price for tickets: ");
				Price.basePrice = scan.nextFloat();
				System.out.println("New base price: "+ Price.basePrice);
				break;
				
			default:
				System.out.println("Invalid choice");
		}
		scan.close();
		
	}
	
	public float getPrice() {
		return this.price;
	}
}

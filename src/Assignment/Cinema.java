package Assignment;

import java.util.Scanner;
import java.util.ArrayList;
public abstract class Cinema {
		
	private int ROW=0;
	private int COL=0;
	private String cinemaType; // normal/gold-class/3D/imax etc
	private String cinemaCode; //3lettercode
	
	
	public String[][][][] floorplan; // first 2 dimension is the index of the time, date then show time
	public ArrayList<String>[] showtimes = new ArrayList[6];
	public ArrayList<String> dates;
	
	public Cinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinema_type
		this.cinemaType = cinema_type;
		this.cinemaCode = cinema_code;
		for (int i=0; i<dates.size(); i++) {
			this.showtimes[i] = showtimes;
		}
		this.dates = dates;

	}
	
	
	public static Cinema generateCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		// dynamically generate seats
		// For seating, different cinema types have different number of seats
		if (cinema_type.equals("3D")) {
			return new TDCinema(cinema_type, cinema_code, showtimes, dates);
		}else if (cinema_type.equals("GoldClass")) {
			return new GCCinema(cinema_type, cinema_code, showtimes, dates);
		}else if (cinema_type.equals("Normal")) {
			return new NormalCinema(cinema_type, cinema_code, showtimes, dates);
		}
		else {
			return new ImaxCinema(cinema_type, cinema_code, showtimes, dates);
		} 
		
	}
	
	
	//Show available seats for a specific show time
	public abstract void viewSeats(String time, String date);
	
	public String[][][][] getFloorplan(){
		return this.floorplan;
	};
	public void setFloorplan(String[][][][] myFloorPlan) {
		this.floorplan = myFloorPlan;
	};
	public ArrayList<String>[] getShowtime(){
		return this.showtimes;
	};
	public void setShowtime(ArrayList<String>[] myShowtimes) {
		this.showtimes = myShowtimes;
	};
	
	public ArrayList<String> getDates(){
		return this.dates;
	};
	public void setDates(ArrayList<String> myDates) {
		this.dates = myDates;
	};
	

	public int getROW() {
		return this.ROW;
	};
	public void setROW(int myRow) {
		this.ROW = myRow;
	};
	
	public int getCOL() {
		return this.COL;
	};
	public void setCOL(int myCOL) {
		this.COL = myCOL;
	};
	
	public String getCinemaType() {
		return this.cinemaType;
	};
	public void setCinemaType(String myType) {
		this.cinemaType = myType;
	};
	
	public String getCinemaCode() {
		return this.cinemaCode;
	};
	public void setCinemaCode(String myType) {
		this.cinemaCode = myType;
	};
	
	
	
	

	
	public MovieTicket bookSeat(Cineplex cineplex, User user, Cinema cinema, String time, Movie movie, int numTicket, String date) {
		Scanner scan = new Scanner(System.in);
		int dateIndex = this.dates.indexOf(date);
		int index = this.showtimes[dateIndex].indexOf(time);
		String[][] seats = this.getFloorplan()[dateIndex][index];
		
		System.out.println("=== Seats for " + movie.title + " on " + date + " " + time + " at " + cineplex.name + " " + cineplex.location + " ===");
		this.viewSeats(time, date);
		System.out.println();
		MovieTicket ticket = new MovieTicket(movie, this, time, date, 1);
		ticket.price.generatePrice(user.getAge(), movie.type, cinema.cinemaType, date);
		ticket.perTicketPrice = ticket.getPrice();
		System.out.printf("Each ticket costs $%.2f\n", ticket.perTicketPrice);
		ticket.price.printBreakdown();
		while (true) {
			if (numTicket == 1) {
				System.out.print("Choose your seat: ");
				String seat = scan.next();
				int row_index = (int) seat.charAt(0) - 65;
				int column_index = (int) seat.charAt(1) - 49;
				if (row_index > this.ROW - 1 || column_index > this.COL - 1) {
					System.out.println("No such seats available.");
				} else if (seats[row_index][column_index].equals("X")) {
					System.out.println("Seat is taken. Please choose another one.");
				} else if (seats[row_index][column_index].equals("@")){
						System.out.println("Seat is a spacing. Please choose another one.");
				} else {
					seats[row_index][column_index] = "+";
					this.viewSeats(time, date);
					System.out.print("\nPlease confirm your seat (Y/N) ");
					char reply = scan.next().charAt(0);
					if (reply == 'Y') {
						seats[row_index][column_index] = "X";
						System.out.printf("Payment is done! Here is your receipt. Total price is $%.2f\n",ticket.getPrice());
						movie.movieSales += ticket.getPrice();
						return ticket;
					} else {
						seats[row_index][column_index] = "O";
						System.out.println("Please choose another seat");
					}
				}
			} else {
				ArrayList<String> seatList = new ArrayList<String>();
				for (int i = 1; i <= numTicket; i++) {
					System.out.print("Choose seat " + i + ": ");
					String seat = scan.next();
					seatList.add(seat);
					int row_index = (int) seat.charAt(0) - 65;
					int column_index = (int) seat.charAt(1) - 49;

					if (row_index > this.ROW - 1 || column_index > this.COL - 1) {
						System.out.println("No such seats available.");
					} else if (seats[row_index][column_index].equals("X")) {
						System.out.println("Seat is taken. Please choose another one.");
						i -= 1;
					} else if (seats[row_index][column_index].equals("@")){
						System.out.println("Seat is a spacing. Please choose another one.");
					} 
					else {
						seats[row_index][column_index] = "+";
					}
				}
				this.viewSeats(time, date);
				System.out.println();
				float totalPrice = 0;
				System.out.println("Please confirm your seat (Y/N)");
				char reply = scan.next().charAt(0);
				if (reply == 'Y') {
					for (int i = 0; i < seatList.size(); i++) {
						int row_index = (int) seatList.get(i).charAt(0) - 65;
						int column_index = (int) seatList.get(i).charAt(1) - 49;
						seats[row_index][column_index] = "X";
						totalPrice += ticket.perTicketPrice;
					}
					ticket.setPrice(totalPrice);
					System.out.printf("Payment is done! Here is your receipt. Total price is $%.2f", ticket.getPrice());
					movie.movieSales += ticket.getPrice();
					ticket.quantityTicket = seatList.size();
					return ticket;
				} else {
					for (int i = 0; i < seatList.size(); i++) {
						int row_index = (int) seatList.get(i).charAt(0) - 65;
						int column_index = (int) seatList.get(i).charAt(1) - 49;
						seats[row_index][column_index] = "O";
					}
					System.out.println("Please choose other seats");
				}
			}
		}
	}
}
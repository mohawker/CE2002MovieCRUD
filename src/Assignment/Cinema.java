package Assignment;

import java.util.Scanner;
import java.util.ArrayList;
public class Cinema {
	
	// For seating, different cinema types have different number of seats

	int GCROW=4;
	int GCCOL=4;
	int TDROW=6;
	int TDCOL=6;
	int NROW=8;
	int NCOL=8;
	
	int ROW=0;
	int COL=0;
	public String[][][][] floorplan; // first dimension is the index of the time
	public ArrayList<String>[] showtimes = new ArrayList[6];
	public String cinema_type; // normal/gold-class/3D etc
	public String cinema_code; //3lettercode
	public ArrayList<String> dates;
	
	public Cinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinema_type

		this.cinema_type = cinema_type;
		this.cinema_code = cinema_code;
		
		for (int i=0; i<dates.size(); i++) {
			this.showtimes[i] = showtimes;
		}
		this.dates = dates;
		// dynamically generate seats
		if (this.cinema_type.equals("3D")) {
			this.ROW = TDROW;
			this.COL = TDCOL;
		}else if (this.cinema_type.equals("GoldClass")) {
			this.ROW = GCROW;
			this.COL = GCCOL;
		}else if (this.cinema_type.equals("Normal")) {
			this.ROW = NROW;
			this.COL = NCOL;
		}
		
		// dynamically generate floor plan;
		int num_showtimes = this.showtimes[0].size();
		this.floorplan = new String [dates.size()][num_showtimes][this.ROW][this.COL];
		
		for (int date=0; date<dates.size(); date++) {
			for (int i=0;i<num_showtimes;i++) {
				for (int j=0;j<this.ROW;j++) {
					for (int k=0;k<this.COL;k++) {
						this.floorplan[date][i][j][k]= "O";
					}
				}
			}
		}
	}
	
	//Show available seats for a specific show time
	public void viewSeats(String time, String date) {
		int dateIndex = this.dates.indexOf(date);
		int index = this.showtimes[dateIndex].indexOf(time);
		String[][] seats = this.floorplan[dateIndex][index];
		System.out.println("-------------LEGEND-------------");
		System.out.println("[O] - Vacant Seats");
		System.out.println("[X] - Occupied Seats");
		System.out.println("[+] - Chosen Seats");
		System.out.println("___ - Screen");
		System.out.println("--------------------------------");
		System.out.printf("\t ");
		for (int col_num=0; col_num<this.COL; col_num++) {
			System.out.print(col_num+1 + "   ");
		}
		System.out.println();
		for (int row_num=0; row_num<this.ROW; row_num++) {
			System.out.printf((char)(row_num+65) + "\t");
			for (int col_num=0; col_num<this.COL; col_num++) {
				System.out.printf("[%s] ", seats[row_num][col_num]);
			}
			System.out.println();
		}
		System.out.print("\t");
		for (int i=0; i<this.COL*4 - 1; i++){
			System.out.print("_");
		}
	}
	
	public MovieTicket bookSeat(Cineplex cineplex, User user, Cinema cinema, String time, Movie movie, int numTicket, String date) {
		Scanner scan = new Scanner(System.in);
		int dateIndex = this.dates.indexOf(date);
		int index = this.showtimes[dateIndex].indexOf(time);
		String[][] seats = this.floorplan[dateIndex][index];
		
		System.out.println("=== Seats for " + movie.title + " on " + date + " " + time + " at " + cineplex.name + " " + cineplex.location + " ===");
		this.viewSeats(time, date);
		System.out.println();
		MovieTicket ticket = new MovieTicket(movie, this, time, date, 1);
		ticket.price.generatePrice(user.getAge(), movie.type, cinema.cinema_type, date);
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
					} else {
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
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
	public String[][][] floorplan; // first dimension is the index of the time
	public ArrayList<String> showtimes = new ArrayList<String>();
	public String cinema_type; // normal/gold-class/3D etc
	public String cinema_code; //3lettercode
	
	public Cinema(String cinema_type, String cinema_code, ArrayList<String> showtimes) { // can customise seats based on cinema_type

		this.cinema_type = cinema_type;
		this.cinema_code = cinema_code;
		this.showtimes = showtimes;
		
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
		int num_showtimes = showtimes.size();
		this.floorplan = new String [num_showtimes][this.ROW][this.COL];
		
		// initialise floorplan with 'O'
		for (int i=0;i<num_showtimes;i++) {
			for (int j=0;j<this.ROW;j++) {
				for (int k=0;k<this.COL;k++) {
					this.floorplan[i][j][k]= "O";
				}
			}
		}
	}
	
	//Show available seats for a specific show time
	public void viewSeats(String time) {
		int index = this.showtimes.indexOf(time);
		String[][] seats = this.floorplan[index];
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
	
	public MovieTicket bookSeat(String time, Movie movie, int numTicket) {
		Scanner scan = new Scanner(System.in);
		int index = this.showtimes.indexOf(time);
		String[][] seats = this.floorplan[index];
		
			System.out.println("Choose your seat");
			this.viewSeats(time);
			System.out.println();
			while (1==1) {
				if (numTicket == 1) {
					String seat = scan.next();
					int row_index = (int)seat.charAt(0)-65;
					int column_index = (int)seat.charAt(1)-49;
					
					if (row_index > this.ROW-1 || column_index > this.COL-1) {
						System.out.println("No such seats available.");
					}else if (seats[row_index][column_index].equals("X")) {
						System.out.println("Seat is taken. Please choose another one.");
					}else {
						seats[row_index][column_index] = "+";
						this.viewSeats(time);
						
						MovieTicket ticket = new MovieTicket(movie, this, time, 1);
						ticket.setPrice();
						ticket.perTicketPrice = ticket.getPrice();
						System.out.println("Please confirm your seat (Y/N)");
						char reply = scan.next().charAt(0);
						if (reply == 'Y') {
							seats[row_index][column_index] = "X";
							System.out.printf("Payment is done! Here is your receipt. Total price is $%.2f", ticket.getPrice());
							movie.movieSales += ticket.getPrice();
							return ticket;
						}else {
							seats[row_index][column_index] = "O";
							System.out.println("Please choose another seat");
						}
					}
				}else {
<<<<<<< Updated upstream
					seats[row_index][column_index] = "+";
					System.out.println("Please confirm your seat (Y/N)");
					this.viewSeats(time);
					MovieTicket ticket = new MovieTicket(movie, this, time);
=======
					ArrayList<String> seatList = new ArrayList<String>();
					for (int i=1; i<= numTicket; i++) {
						System.out.print("Choose seat " + i + ": ");
						String seat = scan.next();
						seatList.add(seat);
						int row_index = (int)seat.charAt(0)-65;
						int column_index = (int)seat.charAt(1)-49;
						
						if (row_index > this.ROW-1 || column_index > this.COL-1) {
							System.out.println("No such seats available.");
						}else if (seats[row_index][column_index].equals("X")) {
							System.out.println("Seat is taken. Please choose another one.");
							i -= 1;
						}else {
							seats[row_index][column_index] = "+";
						}
					}
					this.viewSeats(time);
					System.out.println();
					float totalPrice = 0;
					MovieTicket ticket = new MovieTicket(movie, this, time, 1);
>>>>>>> Stashed changes
					ticket.setPrice();
					char reply = scan.next().charAt(0);
					if (reply == 'Y') {
<<<<<<< Updated upstream
						seats[row_index][column_index] = "X";
						System.out.println("Payment is done! Here is your receipt");
=======
						for (int i=0; i<seatList.size(); i++) {
							int row_index = (int)seatList.get(i).charAt(0)-65;
							int column_index = (int)seatList.get(i).charAt(1)-49;
							seats[row_index][column_index] = "X";
							totalPrice += ticket.perTicketPrice;
						}
						ticket.price = totalPrice;
						System.out.printf("Payment is done! Here is your receipt. Total price is $%.2f", ticket.getPrice());
>>>>>>> Stashed changes
						movie.movieSales += ticket.getPrice();
						ticket.quantityTicket = seatList.size();
						return ticket;
					}else {
<<<<<<< Updated upstream
						seats[row_index][column_index] = "O";
						System.out.println("Please choose another seat");
					}
			}
		}
	}
	
	public ArrayList<MovieTicket> bookMultipleSeats(String time, Movie movie) {
		Scanner scan = new Scanner(System.in);
		int index = this.showtimes.indexOf(time);
		String[][] seats = this.floorplan[index];
		
		System.out.println("Choose your seats (e.g. A1 to A3)");
		this.viewSeats(time);
		while (1==1) {
			String seat = scan.nextLine();
						
			int first_row_index = (int)seat.charAt(0) - 65;
			int first_col_index = (int)seat.charAt(1) - 49;
			
			int second_row_index = (int)seat.charAt(6) - 65;
			int second_col_index = (int)seat.charAt(7) - 49;
			if (first_row_index != second_row_index) {
				System.out.println("Please select again. Seats must be on the same row");
			}else {
				for (int i = first_col_index; i<=second_col_index; i++) {
					if (seats[first_row_index][i] == "X") {
						System.out.println("At least one of the seats is taken");
					}else if (i == second_col_index) {
						for (i = first_col_index; i<=second_col_index; i++) {
							seats[first_row_index][i] = "+";
						}
						System.out.println("Please confirm your seat (Y/N)");
						this.viewSeats(time);
						MovieTicket ticket = new MovieTicket(movie, this, time);
						System.out.println("You are puchasing " + (second_col_index+first_col_index-1) + " tickets");
						System.out.println("This is the cost of each ticket");
						ticket.setPrice();
						char reply = scan.next().charAt(0);
						if (reply == 'Y') {
							for (i = first_col_index; i<=second_col_index; i++) {
								seats[first_row_index][i] = "X";
								movie.movieSales += ticket.getPrice();
							}
							System.out.println("Payment is done! Here is your receipt");
							ArrayList<MovieTicket> totalTickets = new ArrayList<MovieTicket>();
							for (i = first_col_index; i<=second_col_index; i++) {
								totalTickets.add(ticket);
							}
							return totalTickets;
						}else {
							for (i = first_col_index; i<=second_col_index; i++) {
								seats[first_row_index][i] = "O";
							}
							System.out.println("Please choose another seat");
=======
						for (int i=0; i<seatList.size(); i++) {
							int row_index = (int)seatList.get(i).charAt(0)-65;
							int column_index = (int)seatList.get(i).charAt(1)-49;
							seats[row_index][column_index] = "O";
>>>>>>> Stashed changes
						}
						System.out.println("Please choose other seats");
					}
				}
			}
	}
}
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
	}
	
	public MovieTicket bookSeats(String time, Movie movie) {//can implement idea of making sure booked seats are not staggered
		Scanner scan = new Scanner(System.in);
		int index = this.showtimes.indexOf(time);
		String[][] seats = this.floorplan[index];
		System.out.println("Choose your seat");
		this.viewSeats(time);

		while (1==1) {
			String seat = scan.next();

			int row_index = (int)seat.charAt(0)-65;
			int column_index = (int)seat.charAt(1)-49;
			
			if (row_index > this.ROW-1 || column_index > this.COL-1) {
				System.out.println("No such seats available.");
			}else if (seats[row_index][column_index].equals("X")) {
				System.out.println("Seat is taken. Please choose another one.");
			}else {
				seats[row_index][column_index] = "+";
				System.out.println("Please confirm your seat (Y/N)");
				this.viewSeats(time);
				char reply = scan.next().charAt(0);
				if (reply == 'Y') {
					seats[row_index][column_index] = "X";
					System.out.println("Payment is done! Here is your receipt");
					MovieTicket ticket = new MovieTicket(movie, this, time);
					movie.movieSales += ticket.getPrice();
					return ticket;
				}else {
					seats[row_index][column_index] = "O";
					System.out.println("Please choose another seat");
				}
			}
		}
		
		// implement payments here
	}
}
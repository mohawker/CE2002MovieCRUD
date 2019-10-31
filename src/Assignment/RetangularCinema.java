package Assignment;

import java.util.ArrayList;

public abstract class RetangularCinema extends Cinema {
	
	public RetangularCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinema_type
		super(cinema_type, cinema_code, showtimes, dates);
	}
	
	public void generateFloorPlan() {
		// dynamically generate floor plan;
		int num_showtimes = this.showtimes[0].size();
		this.floorplan = new String [dates.size()][num_showtimes][getROW()][getCOL()];
		
		for (int date=0; date<dates.size(); date++) {
			for (int i=0;i<num_showtimes;i++) {
				for (int j=0;j<getROW();j++) {
					for (int k=0;k<getCOL();k++) {
						this.floorplan[date][i][j][k]= "O";
					}
				}
			}
		}
	}
	
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
		for (int col_num=0; col_num<getCOL(); col_num++) {
			System.out.print(col_num+1 + "   ");
		}
		System.out.println();
		for (int row_num=0; row_num<getROW(); row_num++) {
			System.out.printf((char)(row_num+65) + "\t");
			for (int col_num=0; col_num<getCOL(); col_num++) {
				System.out.printf("[%s] ", seats[row_num][col_num]);
			}
			System.out.println();
		}
		System.out.print("\t");
		for (int i=0; i<getCOL()*4 - 1; i++){
			System.out.print("_");
		}
	}
	
}

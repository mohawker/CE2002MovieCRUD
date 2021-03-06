package entity;

import java.util.ArrayList;

/**
 * IMAX Cinema is a RectangularCinema with its own number of rows
 * and columns
 * @author vince
 *
 */
public final class ImaxCinema extends Cinema {
	int DIAMETER = 5; //only odd numbers allowed
	
	/**
	 * Uses the constructor from RectangularCinema class, IMAX Cinema's circular floorplan is created using stated diameter
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public ImaxCinema(String cinemaType, String cinemaCode, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinemaType
		super(cinemaType, cinemaCode, showtimes, dates);
		setROW(DIAMETER);
		setCOL(DIAMETER);
		// dynamically generate floor plan;
		int num_showtimes = getShowtime()[0].size();
		setFloorplan(new String [dates.size()][num_showtimes][this.DIAMETER][this.DIAMETER]);
		
		for (int date=0; date<dates.size(); date++) {
			for (int i=0;i<num_showtimes;i++) {
				for (int x=0;x<this.DIAMETER;x++) {
					for (int y=0;y<this.DIAMETER;y++) {
						if (checkInCircle(x,y)) {
							getFloorplan()[date][i][x][y]= "O";
						}
						else {
							getFloorplan()[date][i][x][y]= "@";
						}
					}
				}
			}
		}
	}
	
	/**
	 * Checks if a given seat should be included in the circular seating arrangement
	 * @param x - Row of seat
	 * @param y - Column of seat
	 * @return boolean indicating if that seat is in the circular floorplan
	 */
	private boolean checkInCircle(int x, int y) {
		int center = DIAMETER /2;
		double distanceFromCenter = Math.sqrt(Math.pow(x-center, 2) + Math.pow(y-center, 2));
		if (distanceFromCenter > center)  {
			return false;
		}
		else {
			return true;
		}
	}
	

	public void viewSeats(String time, String date) {
		int dateIndex = this.getDates().indexOf(date);
		int index = getShowtime()[dateIndex].indexOf(time);
		String[][] seats = this.getFloorplan()[dateIndex][index];
		System.out.println("-------------LEGEND-------------");
		System.out.println("[O] - Vacant Seats");
		System.out.println("[X] - Occupied Seats");
		System.out.println("[+] - Chosen Seats");
		System.out.println("___ - Screen");
		System.out.println("--------------------------------");
		System.out.printf("\t ");
		for (int col_num=0; col_num<this.DIAMETER; col_num++) {
			System.out.print(col_num+1 + "   ");
		}
		System.out.println();
		for (int row_num=0; row_num<this.DIAMETER; row_num++) {
			System.out.printf((char)(row_num+65) + "\t");
			for (int col_num=0; col_num<this.DIAMETER; col_num++) {
				if (seats[row_num][col_num].equals("@")) {
					System.out.printf("    ");
				}
				else {
					System.out.printf("[%s] ", seats[row_num][col_num]);
				}
			}
			System.out.println();
		}
		System.out.print("\t");
		for (int i=0; i<this.DIAMETER*4 - 1; i++){
			System.out.print("_");
		}
		System.out.println();
	}
}

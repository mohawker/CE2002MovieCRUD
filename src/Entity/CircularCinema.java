package entity;

import java.util.ArrayList;

/**
 * CircularCinema refers to a cinema with a circular seating plan, similar to IMAX theatres
 */
public class CircularCinema extends Cinema {
	int DIAMETER;  //only odd numbers allowed
	
	/**
	 * Uses the same constructor as Cinema class
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public CircularCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinema_type
		super(cinema_type, cinema_code, showtimes, dates);
	}
	
	/**
	 * Generate a circular floor plan
	 */
	public void generateFloorPlan() {
		int num_showtimes = getShowtime()[0].size();
		String[][][][] newFloorplan = new String [getShowtime().length][num_showtimes][getROW()][getCOL()];

		for (int date=0; date < getDates().size(); date++) {
			for (int i=0;i<num_showtimes;i++) {
				for (int x=0;x<this.DIAMETER;x++) {
					for (int y=0;y<this.DIAMETER;y++) {
						if (checkInCircle(x,y)) {
							newFloorplan[date][i][x][y]= "O";
						}
						else {
							//spacing
							newFloorplan[date][i][x][y]= "@";
						}
						
					}
				}
			}
		}
		setFloorplan(newFloorplan);
	}
	
	/**
	 * Checks if a given seat should be included in the circular seating arrangement
	 * @param x - Row of seat
	 * @param y - Column of seat
	 * @return boolean indicating if that seat is in the circular floorplan
	 */
	private boolean checkInCircle(int x, int y) {
		//[0,0,0,0,0],
		//[0,0,0,0,0],
		//[0,0,0,0,0],
		//[0,0,0,0,0],
		//[0,0,0,0,0]
		//generate center 
		int center = DIAMETER /2; //needs to divide by float ;
		//Pythagoras theorem 
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

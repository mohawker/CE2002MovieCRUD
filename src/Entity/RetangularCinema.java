package entity;

import java.util.ArrayList;

/**
 * Cinema with a rectangular floorplan
 */
public abstract class RetangularCinema extends Cinema {
	
	/**
	 * Uses Cinema class constructor
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public RetangularCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinema_type
		super(cinema_type, cinema_code, showtimes, dates);
	}
	
	/**
	 * Generate floorplan based on the rows and columns of the cinema
	 */
	public void generateFloorPlan() {
		// dynamically generate floor plan;
		int num_showtimes = super.getShowtime().length;
		String[][][][] newFloorplan = new String [getShowtime().length][num_showtimes][getROW()][getCOL()];
		
		
		for (int date=0; date<super.getDates().size(); date++) {
			for (int i=0;i<num_showtimes;i++) {
				for (int j=0;j<getROW();j++) {
					for (int k=0;k<getCOL();k++) {
						newFloorplan[date][i][j][k]= "O";
					}
				}
			}
		}
		setFloorplan(newFloorplan);
	}
	
	/**
	 * View seats available in the cinema based on a time and date
	 */
	public void viewSeats(String time, String date) {
		int dateIndex = super.getDates().indexOf(date);
		int index = super.getShowtime()[dateIndex].indexOf(time);
		String[][] seats =  getFloorplan()[dateIndex][index];
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
		System.out.println();
	}
	
}

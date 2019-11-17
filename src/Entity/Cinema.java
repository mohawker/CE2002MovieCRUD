package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cinema refers to the theatre which movies will be played in
 */
public abstract class Cinema implements Serializable{
		
	private int ROW=0;
	private int COL=0;
	private String cinemaType; 
	private String cinemaCode; 
	
	
	private String[][][][] floorplan; // first 2 dimension is the index of the time, date then show time
	private ArrayList<String>[] showtimes = new ArrayList[6];
	private ArrayList<String> dates;
	
	/**
	 * Constructor for Cinema class
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public Cinema(String cinemaType, String cinemaCode, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinemaType
		this.cinemaType = cinemaType;
		this.cinemaCode = cinemaCode;
		for (int i=0; i<dates.size(); i++) {
			this.showtimes[i] = showtimes;
		}
		this.dates = dates;

	}
	
	/**
	 * Generates the seating plan of the cinema based on its type
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 * @return Cinema with generated floorplan
	 */
	public static Cinema generateCinema(String cinemaType, String cinemaCode, ArrayList<String> showtimes, ArrayList<String> dates) {
		// dynamically generate seats
		// For seating, different cinema types have different number of seats
		if (cinemaType.equals("3D")) {
			return new TDCinema(cinemaType, cinemaCode, showtimes, dates);
		}else if (cinemaType.equals("GoldClass")) {
			return new GCCinema(cinemaType, cinemaCode, showtimes, dates);
		}else if (cinemaType.equals("Normal")) {
			return new NormalCinema(cinemaType, cinemaCode, showtimes, dates);
		}
		else {
			return new ImaxCinema(cinemaType, cinemaCode, showtimes, dates);
		} 
		
	}
	
	
	/**
	 * View seats of a cinema at a particular date and time
	 * @param time - Time chosen
	 * @param date - Date chosen
	 */
	public abstract void viewSeats(String time, String date);
	
	// getters
	public String[][][][] getFloorplan(){return this.floorplan;}
	public ArrayList<String>[] getShowtime(){return this.showtimes;}
	public ArrayList<String> getDates(){return this.dates;}
	public int getROW() {return this.ROW;}
	public int getCOL() {return this.COL;}
	public String getCinemaType() {return this.cinemaType;}
	public String getCinemaCode() {	return this.cinemaCode;}
	
	// setters
	public void setFloorplan(String[][][][] myFloorPlan) {this.floorplan = myFloorPlan;}
	public void setShowtime(ArrayList<String>[] myShowtimes) {this.showtimes = myShowtimes;}
	public void setDates(ArrayList<String> myDates) {this.dates = myDates;}
	public void setROW(int myRow) {this.ROW = myRow;}
	public void setCOL(int myCOL) {this.COL = myCOL;}
	public void setCinemaType(String myType) {this.cinemaType = myType;}
	public void setCinemaCode(String myType) {this.cinemaCode = myType;}
}
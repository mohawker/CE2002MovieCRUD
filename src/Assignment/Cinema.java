package Assignment;

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cinema implements Serializable{
		
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
}
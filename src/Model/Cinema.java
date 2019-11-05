package Model;

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import Controller.CineplexControl;

import Controller.Control;
import Controller.InputControl;
import Controller.MovieControl;

public abstract class Cinema implements Serializable{
		
	private int ROW=0;
	private int COL=0;
	private String cinemaType; // normal/gold-class/3D/imax etc
	private String cinemaCode; //3lettercode
	
	
	private String[][][][] floorplan; // first 2 dimension is the index of the time, date then show time
	private ArrayList<String>[] showtimes = new ArrayList[6];
	private ArrayList<String> dates;
	
	public Cinema(String cinemaType, String cinemaCode, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinemaType
		this.cinemaType = cinemaType;
		this.cinemaCode = cinemaCode;
		for (int i=0; i<dates.size(); i++) {
			this.showtimes[i] = showtimes;
		}
		this.dates = dates;

	}
	
	
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
	
	
	//Show available seats for a specific show time
	public abstract void viewSeats(String time, String date);
	
	public String[][][][] getFloorplan(){return this.floorplan;	}
	public void setFloorplan(String[][][][] myFloorPlan) {this.floorplan = myFloorPlan;	}
	
	public ArrayList<String>[] getShowtime(){return this.showtimes;	}
	public void setShowtime(ArrayList<String>[] myShowtimes) {this.showtimes = myShowtimes;	}
	
	public ArrayList<String> getDates(){return this.dates;}
	public void setDates(ArrayList<String> myDates) {this.dates = myDates;}
	

	public int getROW() {return this.ROW;}
	public void setROW(int myRow) {this.ROW = myRow;}
	
	public int getCOL() {return this.COL;}
	public void setCOL(int myCOL) {this.COL = myCOL;}
	
	public String getCinemaType() {return this.cinemaType;
	}
	public void setCinemaType(String myType) {this.cinemaType = myType;}
	
	public String getCinemaCode() {	return this.cinemaCode;}
	

	public void setCinemaCode(String myType) {this.cinemaCode = myType;}
}
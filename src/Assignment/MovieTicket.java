package Assignment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MovieTicket {
	
	// Multipliers for price
	static public float PEAK_PERIOD_MULT = (float) 1.5;
	static public float GOLD_CLASS_MULT = 5;
	static public float THREED_MULT = 3;
	static public int BASE_PRICE = 8;
	
	public float perTicketPrice;
	public int quantityTicket;
	public Movie movie;
	public Cinema cinema;
	public String time;
	public String TID;
	public float price = BASE_PRICE; // set base price
	boolean isPeak;
	public String currentDateTime;
	//public User user;
	
	// For time of ticket 
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	DateFormat timeFormat = new SimpleDateFormat("HHmm");
	Date date = new Date();
	
	// For peak period
	DateTimeFormatter peakFormat = DateTimeFormatter.ofPattern("HHmm");
	public String peakStart = "1700";
	public String peakEnd = "0300";
	public LocalTime peakStartTime = LocalTime.parse(peakStart, peakFormat);
	public LocalTime peakEndTime = LocalTime.parse(peakEnd, peakFormat);
	

	
	public MovieTicket(Movie movie, Cinema cinema, String time, int quantityTicket){
		this.movie = movie;
		this.cinema = cinema;
		this.time = time;
		this.quantityTicket = quantityTicket;
		this.currentDateTime = dateFormat.format(date) + " (YYYYMMDD) " + timeFormat.format(date) + " (HHmm) ";
		this.TID = this.cinema.cinema_code + dateFormat.format(date) + timeFormat.format(date);

		LocalTime timeTime = LocalTime.parse(time, peakFormat);
	    if (peakStartTime.isAfter(peakEndTime)) {
	        if (timeTime.isBefore(peakEndTime) || timeTime.isAfter(peakStartTime)) {
	            this.isPeak = true;
	        } else {
	        	this.isPeak = false;
	        }
	    } else {
	        if (timeTime.isBefore(peakEndTime) && timeTime.isAfter(peakStartTime)) {
	        	this.isPeak = true;
	        } else {
	        	this.isPeak = true;
	        }
	    }	
	}

	static public void setBase(int newPrice) {
		System.out.printf("Ticket Base Price changed to $%d from $%d", newPrice, BASE_PRICE);
		BASE_PRICE =newPrice;
	}
	static public void setPeakMult(float newMult) {
		System.out.printf("Peak Period Ticket Multiplier changed to %.1f from %.1f", newMult, PEAK_PERIOD_MULT);
		PEAK_PERIOD_MULT =newMult;
	}
	static public void set3DMult(float newMult) {
		System.out.printf("3D Ticket Multiplier changed to %f from %.1f", newMult, THREED_MULT);
		THREED_MULT =newMult;
	}
	static public void setGoldMult(float newMult) {
		System.out.printf("Gold Class Ticket Multiplier changed to %.1f from %.1f", newMult, GOLD_CLASS_MULT);
		GOLD_CLASS_MULT =newMult;
	}

	
	public void setPrice() {
		System.out.printf("The base price is $%.2f", this.price);
		System.out.println();
		if(cinema.cinema_type == "3D") {
			this.price *= THREED_MULT;
			System.out.println("This is a 3D Movie, a surcharge of X" + THREED_MULT + " is applied");
			System.out.println("Current price is $" + this.price);
			System.out.println();
		}
		if(cinema.cinema_type == "GoldClass") {
			this.price *= GOLD_CLASS_MULT;
			System.out.println("This is a Gold Class Cinema, a surcharge of X" + GOLD_CLASS_MULT + " is applied");
			System.out.printf("Current price is $%.2f", this.price);
			System.out.println();
		}
		if (this.isPeak) {
			this.price *= PEAK_PERIOD_MULT;
			System.out.println("This is purchased during peak period, a surcharge of X" + PEAK_PERIOD_MULT + "is applied");
			System.out.printf("Current price is $%.2f", this.price);
			System.out.println();
		};
		System.out.printf("The final price of one ticket is $%.2f", this.price);
		System.out.println();
	}
	
	public void setPrice(float totalPrice) {
		this.price = totalPrice;
	}

	public float getPrice() {
		return price;}
}

package Assignment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MovieTicket {
	
	public float perTicketPrice;
	public int quantityTicket;
	public Movie movie;
	public Cinema cinema;
	public String time;
	public String movieDate;
	public String TID;
	public Price price;
	boolean isPeak;
	public String currentDateTime;
	
	// For time of ticket 
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	DateFormat timeFormat = new SimpleDateFormat("HHmm");
	Date date = new Date();
	
	
	public MovieTicket(Movie movie, Cinema cinema, String time, String movieDate, int quantityTicket){
		this.movie = movie;
		this.cinema = cinema;
		this.time = time;
		this.movieDate = movieDate;
		this.quantityTicket = quantityTicket;
		this.currentDateTime = dateFormat.format(date) + " (YYYYMMDD) " + timeFormat.format(date) + " (HHmm) ";
		this.TID = this.cinema.cinema_code + dateFormat.format(date) + timeFormat.format(date);
		this.price = new Price();
	}

	public void setPrice(float totalPrice) {
		this.price.setPrice(totalPrice);
	}

	public float getPrice() {
		return this.price.getPrice();}
}

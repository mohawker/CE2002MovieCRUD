package Assignment;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MovieTicket implements Serializable{
	
	private float perTicketPrice;
	private int quantityTicket;
	private Movie movie;
	private Cinema cinema;
	private String time;
	private String movieDate;
	private String TID;
	private boolean isPeak;
	private String currentDateTime;
	
	
	private Price price;//no need getset
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
		this.TID = this.cinema.getCinemaCode() + dateFormat.format(date) + timeFormat.format(date);
		this.price = new Price();
	}

	public void setPrice(float totalPrice) {this.price.setPrice(totalPrice);}

	public float getPrice() {return this.price.getPrice();}
	
	//setters
	
	public void setPerTicketPrice(float perTicketPrice) {this.perTicketPrice=perTicketPrice;}	
	public void setQuantityTickets(int quantityTicket) {this.quantityTicket=quantityTicket;}	
	public void setTMovie(Movie movie) {this.movie=movie;}	
	public void setTCinema(Cinema cinema) {this.cinema=cinema;}	
	public void setTTime(String time) {this.time = time;}	
	public void setMovieDate(String movieDate) {this.movieDate = movieDate;}	
	public void setTID(String TID) {this.TID=TID;}	
	public void setIsPeak(boolean isPeak) {this.isPeak=isPeak;}
	public void setTprice(Price price) {this.price = price;}
	//getters
	public float getPerTicketPrice() {return this.perTicketPrice;}
	public int getQuantityTickets() {return this.quantityTicket;}
	public Movie getTMovie() {return this.movie;}
	public Cinema getTCinema() {return this.cinema;}
	public String getTTime() {return this.time;}
	public String getMovieDate() {return this.movieDate;}
	public String getTID() {return this.TID;}
	public boolean getIsPeak() {return this.isPeak;}
	public Price getTPrice() {return this.price;}
	public String getCurrentDateTime() {return this.currentDateTime;}
	
}

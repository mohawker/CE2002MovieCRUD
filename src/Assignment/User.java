package Assignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class User implements Serializable{
	private String username;
	private String email;
	private String telno;
	private int age;
	private ArrayList<MovieTicket> ticket_history = new ArrayList<MovieTicket>(); // Start out with no history
	
	
	public User(String username, String email, String telno, int age) {
		this.username = username;
		this.email = email;
		this.telno = telno;
		this.age = age;
	}
	
	//setMethods
	public void setUsername(String username) {this.username = username;}
	public void setEmail(String email) {this.email = email;}
	public void setTelno(String telno) {this.telno = telno;}
	public void setAge(int age) {this.age = age;}
	
	//getMethods
	public String getUsername() {return this.username;}
	public String getEmail() {return this.email;}
	public String getTelno() {return this.telno;}
	public int getAge() {return this.age;}
	
	public void viewTicketHistory() {
		System.out.println("Generating Ticket History");
		System.out.println("-----------------------");
		if (ticket_history.size() == 0) {
			System.out.println("No tickets have been booked yet");
		}else {
			for (int i = 0; i < ticket_history.size(); i++) {
				MovieTicket ticket = ticket_history.get(i);
				System.out.println("Transation ID is " + ticket.TID);
				System.out.println(ticket.quantityTicket + " Ticket(s) for " + ticket.movie.title + " on " + ticket.movieDate + " at " + ticket.time);
				System.out.printf("Each ticket is $%.2f\n", ticket.perTicketPrice);
				System.out.printf("Total price is $%.2f\n", ticket.getPrice());
				System.out.println("Purchased on " + ticket.currentDateTime);
				System.out.println();
			}
		}
	}
	
	public void viewMoviesListing(Cineplex cineplex) {
		for (int i = 0; i < cineplex.movies.size(); i++) {
			System.out.println("Title: " + cineplex.movies.get(i).title + ", Status: "+ cineplex.movies.get(i).status);
		}
	}
	
	public void viewMovieDetail(Movie movie) {
		movie.printMovie();
	}
	
	public void viewSeatAvailability(Cineplex cineplex, Movie movie, String date) {
		int index = cineplex.movies.indexOf(movie);
		Cinema cinemaShowing = cineplex.cinemas.get(index);
		Scanner scan = new Scanner(System.in);
		int movieIndex = cineplex.movies.indexOf(movie);
		int dateIndex = cineplex.cinemas.get(movieIndex).dates.indexOf(date);
		System.out.println("=== Available Showtimes ===");
		for (int i=0; i<cinemaShowing.showtimes[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.showtimes[dateIndex].get(i));
		}
		System.out.print("Select Showtime: ");
		int choice = InputControl.integerInput(1, cinemaShowing.showtimes.length);
		System.out.println();
		String showtime_chosen;
		while (1==1) {
			if (choice < 1 && choice > cinemaShowing.showtimes[dateIndex].size()) {
				System.out.println("Invalid option. Please try again");
			}else {
				showtime_chosen = cinemaShowing.showtimes[dateIndex].get(choice-1);
				System.out.println("=== Seats for " + movie.title + " on " + date + " " + showtime_chosen + " at " + cineplex.name + " " + cineplex.location + " ===");
				break;
			}
		}
		cinemaShowing.viewSeats(showtime_chosen, cinemaShowing.dates.get(dateIndex));
	}
	
	
	public void addTicket(MovieTicket ticket) {
		this.ticket_history.add(ticket);
	}
}

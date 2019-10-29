package Assignment;

import java.util.ArrayList;
import java.util.Scanner;


public class User {
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
		System.out.println("=== Movie Details of " + movie.title + " ===");
		movie.printMovie();
	}
	
	public void viewSeatAvailability(Cineplex cineplex, Movie movie, String date) {
		int index = cineplex.movies.indexOf(movie);
		Cinema cinema_showing = cineplex.cinemas.get(index);
		Scanner scan = new Scanner(System.in);
		int movieIndex = cineplex.movies.indexOf(movie);
		int dateIndex = cineplex.cinemas.get(movieIndex).dates.indexOf(date);
		System.out.println("=== Available Showtimes ===");
		for (int i=0; i<cinema_showing.showtimes[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinema_showing.showtimes[dateIndex].get(i));
		}
		System.out.print("Select showtime: ");
		
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		int choice = scan.nextInt();
		
		
		String showtime_chosen;
		while (1==1) {
			if (choice < 1 && choice > cinema_showing.showtimes[dateIndex].size()) {
				System.out.println("Invalid option. Please try again");
			}else {
				showtime_chosen = cinema_showing.showtimes[dateIndex].get(choice-1);
				System.out.println("=== Seats for " + movie.title + " on " + date + " " + showtime_chosen + " at " + cineplex.name + " " + cineplex.location + " ===");
				break;
			}
		}
		cinema_showing.viewSeats(showtime_chosen, cinema_showing.dates.get(dateIndex));
	}
	
	public MovieTicket bookPurchaseTicket(User user, Cineplex cineplex, Movie movie, int numTicket) {
		int index = cineplex.movies.indexOf(movie);
		
		Cinema cinema_showing = cineplex.cinemas.get(index);
		Scanner scan = new Scanner(System.in);
		System.out.println("\n=== Dates for " + movie.title + " at " + cineplex.name + " " + cineplex.location + " ===");
		for (int i=0; i<cinema_showing.dates.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinema_showing.dates.get(i));
		}
		System.out.print("Select date: ");
		int dateIndex = scan.nextInt()-1;
		System.out.println("\n=== Available showtimes ===");
		for (int i=0; i<cinema_showing.showtimes[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinema_showing.showtimes[dateIndex].get(i));
		}
		System.out.print("Select showtime: ");
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		int choice = scan.nextInt();
		System.out.println();
		String showtime = cinema_showing.showtimes[dateIndex].get(choice-1);
		return cinema_showing.bookSeat(cineplex, user, cinema_showing, showtime, movie, numTicket, cinema_showing.dates.get(dateIndex));
	}
	
	public void addTicket(MovieTicket ticket) {
		this.ticket_history.add(ticket);
	}
}

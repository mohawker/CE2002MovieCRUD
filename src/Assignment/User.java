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
		for (int i = 0; i < ticket_history.size(); i++) {
			System.out.println("Transation ID is " + ticket_history.get(i).TID);
			System.out.println("Ticket for " + ticket_history.get(i).movie.title + " at " + ticket_history.get(i).time);
			System.out.println("Purchased on " + ticket_history.get(i).currentDateTime);
			System.out.println();
		}
	}
	
	public void viewMoviesListing(Cineplex cineplex) {
		for (int i = 0; i < cineplex.movies.size(); i++) {
			System.out.println("Title: " + cineplex.movies.get(i).title + ", Status: "+ cineplex.movies.get(i).status);
		}
	}
	
	public void viewMovieDetail(Movie movie) {
		System.out.println("--These are the movie details--");
		movie.printMovie();
	}
	
	public void viewSeatAvailability(Cineplex cineplex, Movie movie) {
		int index = cineplex.movies.indexOf(movie);
		Cinema cinema_showing = cineplex.cinemas.get(index);
		System.out.println("These are the available showtimes. Please select one");
		for (int i=0; i<cinema_showing.showtimes.size(); i++) {
			System.out.println((i+1) + ". " + cinema_showing.showtimes.get(i));
		}
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		
		String showtime_chosen;
		while (1==1) {
			if (choice < 1 && choice > cinema_showing.showtimes.size()) {
				System.out.println("Invalid option. Please try again");
			}else {
				showtime_chosen = cinema_showing.showtimes.get(choice-1);
				break;
			}
		}
		cinema_showing.viewSeats(showtime_chosen);
	}
	
	public MovieTicket bookPurchaseTicket(Cineplex cineplex, Movie movie) {
		int index = cineplex.movies.indexOf(movie);
		Cinema cinema_showing = cineplex.cinemas.get(index);
		System.out.println("These are the available showtimes. Please select one");
		for (int i=0; i<cinema_showing.showtimes.size(); i++) {
			System.out.println((i+1) + ". " + cinema_showing.showtimes.get(i));
		}
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		String showtime = cinema_showing.showtimes.get(choice-1);
		return cinema_showing.bookSeat(showtime, movie);
	}
	
	public ArrayList<MovieTicket> bookPurchaseMultipleTickets(Cineplex cineplex, Movie movie){
		int index = cineplex.movies.indexOf(movie);
		Cinema cinema_showing = cineplex.cinemas.get(index);
		System.out.println("These are the available showtimes. Please select one");
		for (int i=0; i<cinema_showing.showtimes.size(); i++) {
			System.out.println((i+1) + ". " + cinema_showing.showtimes.get(i));
		}
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		String showtime = cinema_showing.showtimes.get(choice-1);
		return cinema_showing.bookMultipleSeats(showtime, movie);
	}
	
	public void addTicket(MovieTicket ticket) {
		this.ticket_history.add(ticket);
	}
}
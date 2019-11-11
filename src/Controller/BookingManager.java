package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Entity.Cinema;
import Entity.Cineplex;
import Entity.DateChecker;
import Entity.Movie;
import Entity.MovieTicket;
import Entity.Payment;
import Entity.User;

/**
 * Makes movie booking for users
 * @author vince
 *
 */
public class BookingManager extends Control{
	private MovieControl movieControl;
	private CineplexControl cineplexControl;
	
	/**
	 * Instantiates movieControl and cineplexControl for the BookingManager
	 * @param uniqueMovies
	 * @param user
	 * @param cineplexes
	 */
	public BookingManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
		this.cineplexControl = new CineplexControl(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Enables user to choose number of seats for movie booking and authenticates the payment upon user confirmation
	 * @param user
	 * @param cineplexes
	 * @param dateChecker
	 */
	public void bookTicket(User user, ArrayList<Cineplex> cineplexes, DateChecker dateChecker){
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		System.out.println();
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		System.out.println();
		
		System.out.println("\nWould you like to book single or multiple seats?");
		System.out.println("[1] Single Seat");
		System.out.println("[2] Multiple Seats");
		System.out.print("Select Option: ");
		int choice = InputControl.integerInput(1, 2);
			if (choice == 1) {
				MovieTicket ticket = this.selectTicketSeat(user, cineplexChosen, movieChosen, 1, dateChecker);
				if (new Payment().authenticatePayment(ticket) == true) {
					user.addTicket(ticket);
				}
				else {
					return;
				}
			}
			else if (choice==2){
				System.out.print("Number of seats: ");
				int numTicket = scan.nextInt();
				MovieTicket ticket = this.selectTicketSeat(user, cineplexChosen, movieChosen, numTicket, dateChecker);
				if (new Payment().authenticatePayment(ticket) == true) {
					user.addTicket(ticket);
				}
				else {
					return;
				}
			}
	}
	
	/**
	 * Enables user to choose seats for movie booking upon user confirmation
	 * @param user
	 * @param cineplex
	 * @param movie
	 * @param numTicket
	 * @param dateChecker
	 * @return
	 */
	public MovieTicket selectTicketSeat(User user, Cineplex cineplex, Movie movie, int numTicket, DateChecker dateChecker) {
		int index = cineplex.getMovies().indexOf(movie);
		
		Cinema cinemaShowing = cineplex.getCinemas().get(index);
		Scanner scan = new Scanner(System.in);
		System.out.println("\n=== Dates for " + movie.getTitle() + " at " + cineplex.getName() + " " + cineplex.getLocation() + " ===");
		for (int i=0; i<cinemaShowing.getDates().size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.getDates().get(i));
		}
		System.out.print("Select Date: ");
		int dateIndex = InputControl.integerInput(1, cinemaShowing.getDates().size()) - 1;
		System.out.println("\n=== Available showtimes ===");
		for (int i=0; i<cinemaShowing.getShowtime()[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.getShowtime()[dateIndex].get(i));
		}
		System.out.print("Select Showtime: ");
		int choice = InputControl.integerInput(1, cinemaShowing.getShowtime().length);
		System.out.println();
		
		String showtime = cinemaShowing.getShowtime()[dateIndex].get(choice-1);
		String date = cinemaShowing.getDates().get(dateIndex);
		int finalDateIndex = cinemaShowing.getDates().indexOf(date);
		int showtimeIndex = cinemaShowing.getShowtime()[finalDateIndex].indexOf(showtime);
		String[][] seats = cinemaShowing.getFloorplan()[finalDateIndex][showtimeIndex];
		
		System.out.println("=== Seats for " + movie.getTitle() + " on " + date + " " + showtime + " at " + cineplex.getName() + " " + cineplex.getLocation() + " ===");
		cinemaShowing.viewSeats(showtime, date);
		System.out.println();
		MovieTicket ticket = new MovieTicket(movie, cinemaShowing, showtime, date, 1);
		ticket.getTPrice().generatePrice(user.getAge(), movie.getType(), cinemaShowing.getCinemaType(), date, dateChecker);
		ticket.setPerTicketPrice(ticket.getPrice());
		System.out.printf("Each ticket costs $%.2f\n", ticket.getPerTicketPrice());
		ticket.getTPrice().printBreakdown();
		while (true) {
			if (numTicket == 1) {
				System.out.print("\nChoose your seat (e.g. A2): ");
				String seat = InputControl.seatInput();
				int row_index = (int) seat.charAt(0) - 65;
				int column_index = (int) seat.charAt(1) - 49;
				if (row_index > cinemaShowing.getROW() - 1 || column_index > cinemaShowing.getCOL() - 1) {
					System.out.println("No such seats available.");
				} else if (seats[row_index][column_index].equals("X")) {
					System.out.println("Seat is taken. Please choose another one.");
				} else if (seats[row_index][column_index].equals("@")){
						System.out.println("Seat is a spacing. Please choose another one.");
				} else {
					seats[row_index][column_index] = "+";
					cinemaShowing.viewSeats(showtime, date);
					System.out.print("\nPlease confirm your seat (Y/N) ");
					char reply = InputControl.ynInput();
					if (reply == 'Y') {
						seats[row_index][column_index] = "X";
							movie.setMovieSales(getMovieSales()+ticket.getPrice());
							return ticket;
						} 
					else {
						seats[row_index][column_index] = "O";
						System.out.println("Please choose another seat");
					}
				}
			} else {
				ArrayList<String> seatList = new ArrayList<String>();
				for (int i = 1; i <= numTicket; i++) {
					System.out.print("\nChoose seat " + i + ": ");
					String seat = InputControl.seatInput();
					seatList.add(seat);
					int row_index = (int) seat.charAt(0) - 65;
					int column_index = (int) seat.charAt(1) - 49;

					if (row_index > cinemaShowing.getROW() - 1 || column_index > cinemaShowing.getCOL() - 1) {
						System.out.println("No such seats available.");
					} else if (seats[row_index][column_index].equals("X")) {
						System.out.println("Seat is taken. Please choose another one.");
						i -= 1;
					} else if (seats[row_index][column_index].equals("@")){
						System.out.println("Seat is a spacing. Please choose another one.");
					} 
					else {
						seats[row_index][column_index] = "+";
					}
				}
				cinemaShowing.viewSeats(showtime, date);
				System.out.println();
				float totalPrice = 0;
				System.out.println("Please confirm your seat (Y/N)");
				char reply = InputControl.ynInput();
				if (reply == 'Y') {
					for (int i = 0; i < seatList.size(); i++) {
						int row_index = (int) seatList.get(i).charAt(0) - 65;
						int column_index = (int) seatList.get(i).charAt(1) - 49;
						seats[row_index][column_index] = "X";
						totalPrice += ticket.getPerTicketPrice();
					}
					ticket.setPrice(totalPrice);
					movie.setMovieSales(getMovieSales()+ticket.getPrice());
					ticket.setQuantityTickets(seatList.size());
					return ticket;
					} else {
					for (int i = 0; i < seatList.size(); i++) {
						int row_index = (int) seatList.get(i).charAt(0) - 65;
						int column_index = (int) seatList.get(i).charAt(1) - 49;
						seats[row_index][column_index] = "O";
					}
					System.out.println("Please choose other seats");
				}
			}
		}
	}

	private float getMovieSales() {
		// TODO Auto-generated method stub
		return 0;
	}
}

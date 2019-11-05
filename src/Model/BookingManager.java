package Model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Controller.CineplexControl;
import Controller.Control;
import Controller.InputControl;
import Controller.MovieControl;

public class BookingManager extends Control{
	MovieControl movieControl;
	CineplexControl cineplexControl;
	
	public BookingManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
		this.cineplexControl = new CineplexControl(uniqueMovies, user, cineplexes);
	}
	
	// single responsibility principle: Booking Manager solely handles bookTicket
	
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
				MovieTicket ticket = this.bookPurchaseTicket(user, cineplexChosen, movieChosen, 1, dateChecker);
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
				MovieTicket ticket = this.bookPurchaseTicket(user, cineplexChosen, movieChosen, numTicket, dateChecker);
				if (new Payment().authenticatePayment(ticket) == true) {
					user.addTicket(ticket);
				}
				else {
					return;
				}
			}
	}
	
	public MovieTicket bookPurchaseTicket(User user, Cineplex cineplex, Movie movie, int numTicket, DateChecker dateChecker) {
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
		return this.bookSeat(cineplex, user, cinemaShowing, showtime, movie, numTicket, cinemaShowing.getDates().get(dateIndex), dateChecker);
	}
	
	public MovieTicket bookSeat(Cineplex cineplex, User user, Cinema cinema, String time, Movie movie, int numTicket, String date, DateChecker dateChecker) {
		Scanner scan = new Scanner(System.in);
		int dateIndex = cinema.getDates().indexOf(date);
		int index = cinema.getShowtime()[dateIndex].indexOf(time);
		String[][] seats = cinema.getFloorplan()[dateIndex][index];
		
		System.out.println("=== Seats for " + movie.getTitle() + " on " + date + " " + time + " at " + cineplex.getName() + " " + cineplex.getLocation() + " ===");
		cinema.viewSeats(time, date);
		System.out.println();
		MovieTicket ticket = new MovieTicket(movie, cinema, time, date, 1);
		ticket.getTPrice().generatePrice(user.getAge(), movie.getType(), cinema.getCinemaType(), date, dateChecker);
		ticket.setPerTicketPrice(ticket.getPrice());
		System.out.printf("Each ticket costs $%.2f\n", ticket.getPerTicketPrice());
		ticket.getTPrice().printBreakdown();
		while (true) {
			if (numTicket == 1) {
				System.out.print("\nChoose your seat (e.g. A2): ");
				String seat = scan.next();
				int row_index = (int) seat.charAt(0) - 65;
				int column_index = (int) seat.charAt(1) - 49;
				if (row_index > cinema.getROW() - 1 || column_index > cinema.getCOL() - 1) {
					System.out.println("No such seats available.");
				} else if (seats[row_index][column_index].equals("X")) {
					System.out.println("Seat is taken. Please choose another one.");
				} else if (seats[row_index][column_index].equals("@")){
						System.out.println("Seat is a spacing. Please choose another one.");
				} else {
					seats[row_index][column_index] = "+";
					cinema.viewSeats(time, date);
					System.out.print("\nPlease confirm your seat (Y/N) ");
					char reply = scan.next().charAt(0);
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
					String seat = scan.next();
					seatList.add(seat);
					int row_index = (int) seat.charAt(0) - 65;
					int column_index = (int) seat.charAt(1) - 49;

					if (row_index > cinema.getROW() - 1 || column_index > cinema.getCOL() - 1) {
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
				cinema.viewSeats(time, date);
				System.out.println();
				float totalPrice = 0;
				System.out.println("Please confirm your seat (Y/N)");
				char reply = scan.next().charAt(0);
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

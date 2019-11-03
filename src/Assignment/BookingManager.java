package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class BookingManager extends Control{
	MovieControl movieControl;
	CineplexControl cineplexControl;
	
	public BookingManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
		this.cineplexControl = new CineplexControl(uniqueMovies, user, cineplexes);
	}
	
	// single responsibility principle: Booking Manager solely handles bookTicket
	
	public void bookTicket(User user, ArrayList<Cineplex> cineplexes){
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		
		System.out.println("\nWould you like to book single or multiple seats?");
		System.out.println("[1] Single Seat");
		System.out.println("[2] Multiple Seats");
		System.out.print("Select option: ");
		int choice = InputControl.integerInput(1, 2);
			if (choice == 1) {
				MovieTicket ticket = this.bookPurchaseTicket(user, cineplexChosen, movieChosen, 1);
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
				MovieTicket ticket = this.bookPurchaseTicket(user, cineplexChosen, movieChosen, numTicket);
				if (new Payment().authenticatePayment(ticket) == true) {
					user.addTicket(ticket);
				}
				else {
					return;
				}
			}
	}
	
	public MovieTicket bookPurchaseTicket(User user, Cineplex cineplex, Movie movie, int numTicket) {
		int index = cineplex.movies.indexOf(movie);
		
		Cinema cinemaShowing = cineplex.cinemas.get(index);
		Scanner scan = new Scanner(System.in);
		System.out.println("\n=== Dates for " + movie.title + " at " + cineplex.name + " " + cineplex.location + " ===");
		for (int i=0; i<cinemaShowing.dates.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.dates.get(i));
		}
		System.out.print("Select date: ");
		int dateIndex = InputControl.integerInput(1, cinemaShowing.dates.size()) - 1;
		System.out.println("\n=== Available showtimes ===");
		for (int i=0; i<cinemaShowing.showtimes[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.showtimes[dateIndex].get(i));
		}
		System.out.print("Select showtime: ");
		int choice = InputControl.integerInput(1, cinemaShowing.showtimes.length);
		System.out.println();
		String showtime = cinemaShowing.showtimes[dateIndex].get(choice-1);
		return this.bookSeat(cineplex, user, cinemaShowing, showtime, movie, numTicket, cinemaShowing.dates.get(dateIndex));
	}
	
	public MovieTicket bookSeat(Cineplex cineplex, User user, Cinema cinema, String time, Movie movie, int numTicket, String date) {
		Scanner scan = new Scanner(System.in);
		int dateIndex = cinema.dates.indexOf(date);
		int index = cinema.showtimes[dateIndex].indexOf(time);
		String[][] seats = cinema.getFloorplan()[dateIndex][index];
		
		System.out.println("=== Seats for " + movie.title + " on " + date + " " + time + " at " + cineplex.name + " " + cineplex.location + " ===");
		cinema.viewSeats(time, date);
		System.out.println();
		MovieTicket ticket = new MovieTicket(movie, cinema, time, date, 1);
		ticket.price.generatePrice(user.getAge(), movie.type, cinema.getCinemaType(), date);
		ticket.perTicketPrice = ticket.getPrice();
		System.out.printf("Each ticket costs $%.2f\n", ticket.perTicketPrice);
		ticket.price.printBreakdown();
		while (true) {
			if (numTicket == 1) {
				System.out.print("Choose your seat: ");
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
							movie.movieSales += ticket.getPrice();
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
					System.out.print("Choose seat " + i + ": ");
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
						totalPrice += ticket.perTicketPrice;
					}
					ticket.setPrice(totalPrice);
					movie.movieSales += ticket.getPrice();
					ticket.quantityTicket = seatList.size();
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
}

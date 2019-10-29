package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserHelper extends Helper{
	MovieHelper movieHelper;
	CinemaHelper cinemaHelper;
	ShowtimeHelper showtimeHelper;
	ViewHelper viewHelper;
	CineplexHelper cineplexHelper;
	
	public UserHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieHelper = new MovieHelper(uniqueMovies, user, cineplexes);
		this.cinemaHelper = new CinemaHelper(uniqueMovies, user, cineplexes);
		this.showtimeHelper = new ShowtimeHelper(uniqueMovies, user, cineplexes);
		this.viewHelper = new ViewHelper(uniqueMovies, user, cineplexes);
		this.cineplexHelper = new CineplexHelper(uniqueMovies, user, cineplexes);
	}
	
	public void searchUniqueMovies(Set<Movie> uniqueMovies, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movie Search ===");
		System.out.print("Enter movie title to search: ");
		String userInput = scan.nextLine();
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		boolean found = false; //assume not found
		Movie movieChosen = null;
		for (int i = 0; i < uniqueMovies.size(); i++) {
			//make all titles lower case, remove left and right space. 
			if (uniqueMoviesList.get(i).title.toLowerCase().contains(userInput.trim().toLowerCase())) {
				found = true;
				movieChosen = uniqueMoviesList.get(i);
			};			
		}
		if (found) {
			movieHelper.printMovieShowings(movieChosen, cineplexes);
		}
		else {
			System.out.print("Movie not found!");
		}
	}
	
	public void listUniqueMovies(Set<Movie> uniqueMovies, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movie Listings ===");
		movieHelper.printMovies(uniqueMovies);
		System.out.print("Would you like you view the showtimes? (Y/N) ");
		if (scan.next().equals("Y")) {
			Movie movie_chosen = movieHelper.selectMovie(uniqueMovies);
			movieHelper.printMovieShowings(movie_chosen, cineplexes); // for unique movies
		}
	}

	public void viewMovieDetails(User user, Set<Movie> uniqueMovies) {
		// print out titles of unique movie
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movies Available ===");
		movieHelper.printMovies(uniqueMovies);
		Movie movie_chosen = movieHelper.selectMovie(uniqueMovies);
		user.viewMovieDetail(movie_chosen);
	}

	public void checkSeatAvailability(User user, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplex_chosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movie_chosen = movieHelper.selectMovie(user, cineplex_chosen);
		String date = movieHelper.printAndSelectMovieDates(cineplex_chosen, movie_chosen);
		
		if (movie_chosen.status.equals("Showing")){
			user.viewSeatAvailability(cineplex_chosen, movie_chosen, date);
			System.out.println();
		}else {
			System.out.println("Movie is not showing yet");
		}
		
	}

	public void bookTicket(User user, ArrayList<Cineplex> cineplexes){
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplex_chosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movie_chosen = movieHelper.selectMovie(user, cineplex_chosen);
		
		System.out.println("\nWould you like to book single or multiple seats?");
		System.out.println("[1] Single Seat");
		System.out.println("[2] Multiple Seats");
		System.out.print("Select option: ");
		int choice=-1;
		while (!scan.hasNextInt()){
			System.out.println("Error... Please input an Integer");
			scan.nextLine();
		}
		while (choice != 1 && choice !=2) {
			choice = scan.nextInt();
			if (choice == 1) {
				MovieTicket ticket = user.bookPurchaseTicket(user, cineplex_chosen, movie_chosen, 1);
				user.addTicket(ticket);
			}else if (choice==2){
				System.out.print("Number of seats: ");
				int numTicket = scan.nextInt();
				MovieTicket ticket = user.bookPurchaseTicket(user, cineplex_chosen, movie_chosen, numTicket);
				user.addTicket(ticket);
			}else {
				System.out.println("Incorrect selection please select option 1 or 2");
			}
		}
	}

	public void viewBookingHistory(User user) {
		user.viewTicketHistory();
	}

	public void listTop5(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to get the top 5 by:");
		System.out.println("1. Sales");
		System.out.println("2. Overall Rating");
		int choice = scan.nextInt();
		if (choice == 1) {
			movieHelper.sortMovies(uniqueMovies, true);
		}else {
			movieHelper.sortMovies(uniqueMovies, false);
		}
	}
	
	public void addRating(Set<Movie> uniqueMovies, User user) {
		System.out.println("=== Review Movies ===");
		movieHelper.printMovies(uniqueMovies);
		Movie movie_chosen = movieHelper.selectMovie(uniqueMovies);
		Review review = new Review(user, movie_chosen);
		movie_chosen.addReview(review);
	}
}

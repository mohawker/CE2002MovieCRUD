package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class UserHelper extends Helper{
	public UserHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes){
		super(uniqueMovies, user, cineplexes);
	}
	
	public void user_1(Set<Movie> uniqueMovies, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Movie Listings:");
		printMovies(uniqueMovies);
		System.out.println("Would you like you view the showtimes? (Y/N)");
		if (scan.next().equals("Y")) {
			Movie movie_chosen = selectMovie(uniqueMovies);
			printMovies(movie_chosen, cineplexes); // for unique movies
		}
	}

	public void user_2(User user, Set<Movie> uniqueMovies) {
		// print out titles of unique movie
		Scanner scan = new Scanner(System.in);
		System.out.println("These are the movies available:");
		printMovies(uniqueMovies);
		Movie movie_chosen = selectMovie(uniqueMovies);
		user.viewMovieDetail(movie_chosen);
	}

	public void user_3(User user, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		Cineplex cineplex_chosen = selectCineplex(cineplexes);
		Movie movie_chosen = selectMovie(user, cineplex_chosen);
		if (movie_chosen.status.equals("Showing")){
			user.viewSeatAvailability(cineplex_chosen, movie_chosen);
		}else {
			System.out.println("Movie is not showing yet");
		}
		
	}

	public void user_4(User user, ArrayList<Cineplex> cineplexes){
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		Cineplex cineplex_chosen = selectCineplex(cineplexes);
		System.out.println("Select the movie:");
		Movie movie_chosen = selectMovie(user, cineplex_chosen);
		
		System.out.println("Would you like to book single or multiple seats?");
		System.out.println("1. Single Seat");
		System.out.println("2. Multiple Seats");
		int choice=-1;
		while (!scan.hasNextInt()){
			System.out.println("Error... Please input an Integer");
			scan.nextLine();
		}
		while (choice != 1 && choice !=2) {
			choice = scan.nextInt();
			if (choice == 1) {
				MovieTicket ticket = user.bookPurchaseTicket(cineplex_chosen, movie_chosen);
				user.addTicket(ticket);
			}else if (choice==2){
				MovieTicket ticket = user.bookPurchaseMultipleTickets(cineplex_chosen, movie_chosen);
				user.addTicket(ticket);
			}else {
				System.out.println("Incorrect selection please select option 1 or 2");
			}
		}
	}

	public void user_5(User user) {
		user.viewTicketHistory();
	}

	public void user_6(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to get the top 5 by:");
		System.out.println("1. Sales");
		System.out.println("2. Overall Rating");
		int choice = scan.nextInt();
		if (choice == 1) {
			sortMovies(uniqueMovies, true);
		}else {
			sortMovies(uniqueMovies, false);
		}
	}
	
	public void user_7(Set<Movie> uniqueMovies, User user) {
		System.out.println("Add review for:");
		printMovies(uniqueMovies);
		Movie movie_chosen = selectMovie(uniqueMovies);
		Review review = new Review(user, movie_chosen);
		movie_chosen.addReview(review);
	}
}

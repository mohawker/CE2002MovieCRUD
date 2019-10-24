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
		printAllMovies(uniqueMovies);
		System.out.println("Would you like you view the showtimes? (Y/N)");
		if (scan.next().equals("Y")) {
			System.out.println("Please select the movie you would like to see");
			printAllMovies(uniqueMovies);
			Movie movie_chosen = selectFromAllMovie(uniqueMovies);
			
			if (movie_chosen.status.equals("Showing")){
				for (int i=0; i<cineplexes.size(); i++) {
					System.out.println("Showing for " + movie_chosen.title + " at " + cineplexes.get(i).name + " " + cineplexes.get(i).location);
					int index = cineplexes.get(i).movies.indexOf(movie_chosen);
					if (index != -1) {
						ArrayList<String> showtimes = cineplexes.get(i).cinemas.get(index).showtimes;
						Collections.sort(showtimes);
						for (int j = 0; j<showtimes.size(); j++) {
							System.out.println("Showtime: " + showtimes.get(j));
						}
					}else {
						System.out.println("NO SHOWINGS");
					}
					System.out.println();
				}
			}else {
				System.out.println(movie_chosen.title + " is not showing yet.");
			}
		}
	}

	public void user_2(User user, Set<Movie> uniqueMovies) {
		// print out titles of unique movie
		Scanner scan = new Scanner(System.in);
		System.out.println("These are the movies available:");
		printAllMovies(uniqueMovies);
		Movie movie_chosen = selectFromAllMovie(uniqueMovies);
		user.viewMovieDetail(movie_chosen);
	}

	public void user_3(User user, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		Cineplex cineplex_chosen = selectCineplex(cineplexes);
		Movie movie_chosen = selectFromCineplexMovie(user, cineplex_chosen);
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
		Movie movie_chosen = selectFromCineplexMovie(user, cineplex_chosen);
		
		System.out.println("Would you like to book single or multiple seats?");
		System.out.println("1. Single Seat");
		System.out.println("2. Multiple Seats");
		int choice = scan.nextInt();
		if (choice == 1) {
			MovieTicket ticket = user.bookPurchaseTicket(cineplex_chosen, movie_chosen);
			user.addTicket(ticket);
		}else {
			ArrayList<MovieTicket> tickets = user.bookPurchaseMultipleTickets(cineplex_chosen, movie_chosen);
			for (int i=0; i<tickets.size(); i++) {
				user.addTicket(tickets.get(i));
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
		ArrayList<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		ArrayList<Movie> sortedMoviesList = new ArrayList<Movie>();
		sortedMoviesList.add(uniqueMoviesList.get(0));
		
		if (choice == 1) {
			for (int i=1; i<uniqueMoviesList.size(); i++) {
				for (int j=0; j<sortedMoviesList.size(); j++) {
					if (uniqueMoviesList.get(i).movieSales>sortedMoviesList.get(j).movieSales) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}else if (j == sortedMoviesList.size()-1) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}
				}
			}
			for (int i=0; i<5; i++) {
				Movie movie_chosen = sortedMoviesList.get(i);
				System.out.println(movie_chosen.title + " has total sales of $" + movie_chosen.movieSales);
			}
		}else {
			for (int i=1; i<uniqueMoviesList.size(); i++) {
				for (int j=0; j<sortedMoviesList.size(); j++) {
					if (uniqueMoviesList.get(i).getAverageRating()>sortedMoviesList.get(j).getAverageRating()) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}else if (j == sortedMoviesList.size()-1) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}
				}
			}
			for (int i=0; i<5; i++) {
				Movie movie_chosen = sortedMoviesList.get(i);
				System.out.println(movie_chosen.title + " has overall rating of " + movie_chosen.getAverageRating() + " out of 5.0");
			}
		}
	}
	
	public void user_7(Set<Movie> uniqueMovies, User user) {
		System.out.println("Add review for:");
		printAllMovies(uniqueMovies);
		Movie movie_chosen = selectFromAllMovie(uniqueMovies);
		Review review = new Review(user, movie_chosen);
		movie_chosen.addReview(review);
	}
}

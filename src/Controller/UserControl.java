package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Entity.Admin;
import Entity.Cineplex;
import Entity.Movie;
import Entity.User;
import Entity.VideoPlayer;

/**
 * Provides logic to perform user functions
 * @author vince
 *
 */
public class UserControl extends Control{
	private MovieControl movieControl;
	private CinemaControl cinemaControl;
	//private ShowtimeControl showtimeControl;
	private CineplexControl cineplexControl;
	
	/**
	 * Uses the Control class constructor and instantiates MovieControl, CinemaControl and CineplexControl
	 * @param uniqueMovies
	 * @param user
	 * @param cineplexes
	 */
	public UserControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
		this.cinemaControl = new CinemaControl(uniqueMovies, user, cineplexes);
		this.cineplexControl = new CineplexControl(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Search for a  movie based on string matching
	 * @param uniqueMovies
	 * @param cineplexes
	 */
	public void searchUniqueMovies(Set<Movie> uniqueMovies, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movie Search ===");
		System.out.print("Enter movie title to search: ");
		String userInput = scan.nextLine();
		System.out.println();
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		boolean found = false; //assume not found
		ArrayList<Movie> moviesChosen = new ArrayList<Movie>();
		for (int i = 0; i < uniqueMovies.size(); i++) {
			//make all titles lower case, remove left and right space. 
			if (uniqueMoviesList.get(i).getTitle().toLowerCase().contains(userInput.trim().toLowerCase())) {
				found = true;
				moviesChosen.add(uniqueMoviesList.get(i));
			};			
		}
		if (found) {
			for (Movie currMovie: moviesChosen) {
				movieControl.printMovieShowings(currMovie, cineplexes);
			}
			
		}
		else {
			System.out.print("=== Movie not found! === ");
		}
	}
	
	/**
	 * List unique movies being shown across all 3 cineplexes
	 * @param uniqueMovies
	 * @param cineplexes
	 */
	public void listUniqueMovies(Set<Movie> uniqueMovies, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movie Listings ===");
		movieControl.printMovies(uniqueMovies);
		System.out.print("\nWould you like you view the showtimes? (Y/N) ");
		if (scan.next().equals("Y")) {
			Movie movieChosen = movieControl.selectMovie(uniqueMovies);
			System.out.println();
			movieControl.printMovieShowings(movieChosen, cineplexes); // for unique movies
		}
	}
	
	/**
	 * View the details of a movie
	 * @param uniqueMovies
	 */
	public void viewMovieDetails(Set<Movie> uniqueMovies) {
		// print out titles of unique movie
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movies Available ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		System.out.println();
		movieChosen.printMovie();
	}
	
	/**
	 * Check the seats available for a movie on a particular date at a particualr cineplex
	 * @param cineplexes
	 */
	public void checkSeatAvailability(ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplex_chosen = cineplexControl.selectCineplex(cineplexes);
		System.out.println();
		Movie movieChosen = movieControl.selectMovie(cineplex_chosen);
		String date = movieControl.printAndSelectMovieDates(cineplex_chosen, movieChosen);
		
		if (movieChosen.getStatus().equals("Showing")){
			cinemaControl.viewSeatAvailability(cineplex_chosen, movieChosen, date);
			System.out.println();
		}else {
			System.out.println("Movie is not showing yet");
		}
		
	}
	
	/**
	 * View the booking history of the user
	 * @param user
	 */
	public void viewBookingHistory(User user) {
		user.viewTicketHistory();
	}

	/**
	 * Allow users to view trailers
	 * @param uniqueMovies
	 */
	public void viewTrailer(Set<Movie> uniqueMovies) {
		System.out.println("=== Movies Available ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		VideoPlayer player = new VideoPlayer();
		player.play(movieChosen);
	}
}

package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserControl extends Control{
	MovieControl movieControl;
	CinemaControl cinemaControl;
	ShowtimeControl showtimeControl;
	CineplexControl cineplexControl;
	
	public UserControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
		this.cinemaControl = new CinemaControl(uniqueMovies, user, cineplexes);
		this.showtimeControl = new ShowtimeControl(uniqueMovies, user, cineplexes);
		this.cineplexControl = new CineplexControl(uniqueMovies, user, cineplexes);
	}
	
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

	public void viewMovieDetails(Set<Movie> uniqueMovies) {
		// print out titles of unique movie
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movies Available ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		System.out.println();
		movieChosen.printMovie();
	}

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
	
	public void viewBookingHistory(User user) {
		user.viewTicketHistory();
	}

	public void listTop5(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to get the top 5 by:");
		System.out.println("[1] Sales");
		System.out.println("[2] Overall Rating");
		int choice = scan.nextInt();
		if (choice == 1) {
			movieControl.sortMovies(uniqueMovies, true);
		}else {
			movieControl.sortMovies(uniqueMovies, false);
		}
	}

	public void viewTrailer(Set<Movie> uniqueMovies) {
		System.out.println("=== Movies Available ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		VideoPlayer player = new VideoPlayer();
		player.play(movieChosen);
	}
}

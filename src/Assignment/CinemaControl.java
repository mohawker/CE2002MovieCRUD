package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class CinemaControl extends Control{

	public CinemaControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public Cinema printAndSelectCinema(Cineplex cineplex) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Cinemas ===");
		for (int i = 0; i<cineplex.cinemas.size(); i++) {
			System.out.println("[" + (i+1) + "] " + " Cinema Code " + cineplex.cinemas.get(i).getCinemaCode() + "(" + cineplex.cinemas.get(i).getCinemaType() + ")");
		}
		System.out.print("Select Cinema: ");
		int choice = InputControl.integerInput(1, cineplex.cinemas.size());
		System.out.println();
		return cineplex.cinemas.get(choice-1);
	}
	
	public void viewSeatAvailability(Cineplex cineplex, Movie movie, String date) {
		int index = cineplex.movies.indexOf(movie);
		Cinema cinemaShowing = cineplex.cinemas.get(index);
		Scanner scan = new Scanner(System.in);
		int movieIndex = cineplex.movies.indexOf(movie);
		int dateIndex = (cineplex.cinemas.get(movieIndex).dates.indexOf(date)) - 1;
		System.out.println("=== Available Showtimes ===");
		for (int i=0; i<cinemaShowing.showtimes[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.showtimes[dateIndex].get(i));
		}
		System.out.print("Select Showtime: ");
		int choice = InputControl.integerInput(1, cinemaShowing.showtimes.length);
		System.out.println();
		String showtimeChosen;
		while (1==1) {
			if (choice < 1 && choice > cinemaShowing.showtimes[dateIndex].size()) {
				System.out.println("Invalid option. Please try again");
			}else {
				showtimeChosen = cinemaShowing.showtimes[dateIndex].get(choice-1);
				System.out.println("=== Seats for " + movie.title + " on " + date + " " + showtimeChosen + " at " + cineplex.name + " " + cineplex.location + " ===");
				break;
			}
		}
		cinemaShowing.viewSeats(showtimeChosen, cinemaShowing.dates.get(dateIndex));
	}
}

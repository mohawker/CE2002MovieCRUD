package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Entity.Cinema;
import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

public class CinemaControl extends Control{

	public CinemaControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public Cinema printAndSelectCinema(Cineplex cineplex) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Cinemas ===");
		for (int i = 0; i<cineplex.getCinemas().size(); i++) {
			System.out.println("[" + (i+1) + "] " + " Cinema Code " + cineplex.getCinemas().get(i).getCinemaCode() + "(" + cineplex.getCinemas().get(i).getCinemaType() + ")");
		}
		System.out.print("Select Cinema: ");
		int choice = InputControl.integerInput(1, cineplex.getCinemas().size());
		System.out.println();
		return cineplex.getCinemas().get(choice-1);
	}
	
	public void viewSeatAvailability(Cineplex cineplex, Movie movie, String date) {
		int index = cineplex.getMovies().indexOf(movie);
		Cinema cinemaShowing = cineplex.getCinemas().get(index);
		Scanner scan = new Scanner(System.in);
		int movieIndex = cineplex.getMovies().indexOf(movie);
		int dateIndex = (cineplex.getCinemas().get(movieIndex).getDates().indexOf(date)) - 1;
		System.out.println("=== Available Showtimes ===");
		for (int i=0; i<cinemaShowing.getShowtime()[dateIndex].size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinemaShowing.getShowtime()[dateIndex].get(i));
		}
		System.out.print("Select Showtime: ");
		int choice = InputControl.integerInput(1, cinemaShowing.getShowtime().length);
		System.out.println();
		String showtimeChosen;
		while (1==1) {
			if (choice < 1 && choice > cinemaShowing.getShowtime()[dateIndex].size()) {
				System.out.println("Invalid option. Please try again");
			}else {
				showtimeChosen = cinemaShowing.getShowtime()[dateIndex].get(choice-1);
				System.out.println("=== Seats for " + movie.getTitle() + " on " + date + " " + showtimeChosen + " at " + cineplex.getName() + " " + cineplex.getLocation() + " ===");
				break;
			}
		}
		cinemaShowing.viewSeats(showtimeChosen, cinemaShowing.getDates().get(dateIndex));
	}
}

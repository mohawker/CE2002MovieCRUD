package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

import Model.Movie;
import Model.Admin;
import Model.Cineplex;
import Model.Cinema;
import Model.Price;
import Model.DateChecker;

public class AdminControl extends Control{
	
	MovieControl movieControl;
	CinemaControl cinemaControl;
	ShowtimeControl showtimeControl;
	CineplexControl cineplexControl;
	
	public AdminControl(Set<Movie> uniqueMovies, Admin admin, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, admin, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, admin, cineplexes);
		this.cinemaControl = new CinemaControl(uniqueMovies, admin, cineplexes);
		this.showtimeControl = new ShowtimeControl(uniqueMovies, admin, cineplexes);
		this.cineplexControl = new CineplexControl(uniqueMovies, admin, cineplexes);
	}

	public Movie createMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Movie movie = movieControl.createMovie(uniqueMovies);
		if (movie.getStatus().equals("Showing")) {
			System.out.println("Where would you like to show this movie in?");
			cineplexControl.printCineplexes(cineplexes);
			Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
			ArrayList <String> showtimes = showtimeControl.createShowtimes();
			Cinema cinemaChosen = cinemaControl.printAndSelectCinema(cineplexChosen);
			movieControl.replaceMovie(cineplexChosen, cinemaChosen, movie, showtimes);
		}
		return movie;
	}
	
	public void updateMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		System.out.println("\n=== Current Movie Listing ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		System.out.print("New Showing Status of " + movieChosen.getTitle() + "(Showing/End of Showing): ");
		Scanner scan = new Scanner(System.in);
		String newStatus = scan.nextLine();
		if (movieChosen.getStatus().equals(newStatus)) {
			System.out.println("\nNew status same as previous status");
			System.out.println("Shall not update movie status");
		}else if (newStatus.equals("Showing")){
			System.out.println();
			movieChosen.setStatus(newStatus);
			System.out.println();
			cineplexControl.printCineplexes(cineplexes);
			Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
			System.out.println();
			ArrayList <String> showtimes = showtimeControl.createShowtimes();
			System.out.println();
			Cinema cinemaChosen = cinemaControl.printAndSelectCinema(cineplexChosen);
			movieControl.replaceMovie(cineplexChosen, cinemaChosen, movieChosen, showtimes);			
		}else if (newStatus.equals("End of Showing")) {
			movieChosen.setStatus(newStatus);
			uniqueMovies.remove(movieChosen);
		}
	}

	public void removeMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		System.out.println("\n=== Current Movie Listing ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		System.out.println(movieChosen.getTitle() + " will be set to End of Showing");
		movieChosen.setStatus("End of Showing");
		uniqueMovies.remove(movieChosen);
		System.out.println("\n=== New Movie Listing ===");
		movieControl.printMovies(uniqueMovies);
	}
	
	// Has issues
	public void createCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		int valid = movieControl.ensureUnshownMovies(uniqueMovies);
		if (valid == 0) {
			System.out.println("There are no movies listed as 'Coming Soon'. Please create a new movie listing first before creating showtimes.");
			return;
		}
		else {
			System.out.println("=== Movies Coming Soon/Preview ===");
			Movie movieChosen = movieControl.printAndSelectFromUnshownMovies(uniqueMovies);
			movieChosen.setStatus("Showing");
			
			System.out.println("\nChoose Cineplex to be shown in:");
			cineplexControl.printCineplexes(cineplexes);
			Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
			System.out.println();
			ArrayList <String> showtimes = showtimeControl.createShowtimes();
			System.out.println();
			Cinema cinemaChosen = cinemaControl.printAndSelectCinema(cineplexChosen);
			movieControl.replaceMovie(cineplexChosen, cinemaChosen, movieChosen, showtimes);
		}
	}

	
	public void updateCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		System.out.println();
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		System.out.println();
		String date = movieControl.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
		int index = cineplexChosen.getMovies().indexOf(movieChosen);
		int dateIndex = cineplexChosen.getCinemas().get(index).getDates().indexOf(date);
		ArrayList <String> currShowtimes = cineplexChosen.getCinemas().get(index).getShowtime()[dateIndex];
		
		System.out.println("Current Show Times for " + movieChosen.getTitle() + ": " + currShowtimes + "\n");
		System.out.print("Number of Showtimes for " + movieChosen.getTitle() + "  to be removed: ");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Remove Showtime: ");
			currShowtimes.remove(scan.nextLine());
		}
		
		System.out.print("\nNumber of Showtimes for " + movieChosen.getTitle() + "  to be added: ");
		choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Add Showtime: ");
			currShowtimes.add(scan.nextLine());
		}
		Collections.sort(currShowtimes);
		System.out.println("\nNew Showtimes for " + movieChosen.getTitle() + " at " + cineplexChosen.getName() + " " + cineplexChosen.getName() + " : " + currShowtimes);
		cineplexChosen.getCinemas().get(index).getShowtime()[dateIndex] = currShowtimes;
	}
	
	public void addCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		System.out.println();
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		String date = movieControl.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
		int index = cineplexChosen.getMovies().indexOf(movieChosen);
		int dateIndex = cineplexChosen.getCinemas().get(index).getDates().indexOf(date);
		ArrayList <String> currShowtimes = cineplexChosen.getCinemas().get(index).getShowtime()[dateIndex];
		
		System.out.println("Current Show Times for " + movieChosen.getTitle() + ": " + currShowtimes);
		System.out.print("Number of Showtimes for " + movieChosen.getTitle() + "  to be added: ");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Add Showtime: ");
			currShowtimes.add(scan.nextLine());
		}
		Collections.sort(currShowtimes);
		System.out.println("New Showtimes for " + movieChosen.getTitle() + " at " + cineplexChosen.getName() + " " + cineplexChosen.getName() + " : " + currShowtimes);
		cineplexChosen.getCinemas().get(index).getShowtime()[dateIndex] = currShowtimes;
	}

	public void removeCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		String date = movieControl.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
		int index = cineplexChosen.getCinemas().indexOf(movieChosen);
		int dateIndex = cineplexChosen.getCinemas().get(index).getDates().indexOf(date);
		ArrayList <String> currShowtimes = cineplexChosen.getCinemas().get(index).getShowtime()[dateIndex];
		
		System.out.println("Current Show Times for " + movieChosen.getTitle() + ": " + currShowtimes);
		System.out.print("Number of Showtimes for " + movieChosen.getTitle() + "  to be removed: ");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Remove Showtime: ");
			currShowtimes.remove(scan.nextLine());
		}
		System.out.println("New Showtimes for " + movieChosen.getTitle() + " at " + cineplexChosen.getName() + " " + cineplexChosen.getName() + " : " + currShowtimes);
		cineplexChosen.getCinemas().get(index).getShowtime()[dateIndex] = currShowtimes;
	}

	public void configureSettings() {
		Price.updatePrices();
	}
	
	public void addNewHoliday(DateChecker dateChecker) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter date in the format DD/MM: ");
		String date = scan.next();
		dateChecker.addSpecialDate(date);
		
	}

	public void listTop5(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to get the top 5 by:");
		System.out.println("1. Sales");
		System.out.println("2. Overall Rating");
		int choice = scan.nextInt();
		if (choice == 1) {
			movieControl.sortMovies(uniqueMovies, true);
		}else {
			movieControl.sortMovies(uniqueMovies, false);
		}
	}
}

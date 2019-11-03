package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

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
		if (movie.status.equals("Showing")) {
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
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		System.out.print("New Showing Status of " + movieChosen.title + "(Showing/End of Showing): ");
		Scanner scan = new Scanner(System.in);
		String newStatus = scan.nextLine();
		if (movieChosen.status.equals(newStatus)) {
			System.out.println("New status same as previous status");
			System.out.println("Shall not update movie status");
		}else if (newStatus.equals("Showing")){
			movieChosen.status = newStatus;
			cineplexControl.printCineplexes(cineplexes);
			Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
			ArrayList <String> showtimes = showtimeControl.createShowtimes();
			Cinema cinemaChosen = cinemaControl.printAndSelectCinema(cineplexChosen);
			movieControl.replaceMovie(cineplexChosen, cinemaChosen, movieChosen, showtimes);			
		}else if (newStatus.equals("End of Showing")) {
			movieChosen.status = newStatus;
			uniqueMovies.remove(movieChosen);
		}
	}

	public void removeMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		System.out.println(movieChosen.title + " will be set to End of Showing");
		movieChosen.status = "End of Showing";
		uniqueMovies.remove(movieChosen);
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
		Movie movieChosen = movieControl.printAndSelectFromUnshownMovies(uniqueMovies);
		movieChosen.status = "Showing";
		System.out.println("Choose Cineplex to be shown in:");
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		ArrayList <String> showtimes = showtimeControl.createShowtimes();
		Cinema cinemaChosen = cinemaControl.printAndSelectCinema(cineplexChosen);
		movieControl.replaceMovie(cineplexChosen, cinemaChosen, movieChosen, showtimes);
		}
	}

	
	public void updateCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		String date = movieControl.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
		int index = cineplexChosen.movies.indexOf(movieChosen);
		int dateIndex = cineplexChosen.cinemas.get(index).dates.indexOf(date);
		ArrayList <String> currShowtimes = cineplexChosen.cinemas.get(index).showtimes[dateIndex];
		
		System.out.println("Current Show Times for " + movieChosen.title + ": " + currShowtimes);
		System.out.print("Number of Showtimes for " + movieChosen.title + "  to be removed: ");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Remove Showtime: ");
			currShowtimes.remove(scan.nextLine());
		}
		
		System.out.print("Number of Showtimes for " + movieChosen.title + "  to be added: ");
		choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Add Showtime: ");
			currShowtimes.add(scan.nextLine());
		}
		Collections.sort(currShowtimes);
		System.out.println("New Showtimes for " + movieChosen.title + " at " + cineplexChosen.name + " " + cineplexChosen.name + " : " + currShowtimes);
		cineplexChosen.cinemas.get(index).showtimes[dateIndex] = currShowtimes;
	}
	
	public void addCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		String date = movieControl.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
		int index = cineplexChosen.movies.indexOf(movieChosen);
		int dateIndex = cineplexChosen.cinemas.get(index).dates.indexOf(date);
		ArrayList <String> currShowtimes = cineplexChosen.cinemas.get(index).showtimes[dateIndex];
		
		System.out.println("Current Show Times for " + movieChosen.title + ": " + currShowtimes);
		System.out.print("Number of Showtimes for " + movieChosen.title + "  to be added: ");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Add Showtime: ");
			currShowtimes.add(scan.nextLine());
		}
		Collections.sort(currShowtimes);
		System.out.println("New Showtimes for " + movieChosen.title + " at " + cineplexChosen.name + " " + cineplexChosen.name + " : " + currShowtimes);
		cineplexChosen.cinemas.get(index).showtimes[dateIndex] = currShowtimes;
	}

	public void removeCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		cineplexControl.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexControl.selectCineplex(cineplexes);
		Movie movieChosen = movieControl.selectMovie(cineplexChosen);
		String date = movieControl.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
		int index = cineplexChosen.movies.indexOf(movieChosen);
		int dateIndex = cineplexChosen.cinemas.get(index).dates.indexOf(date);
		ArrayList <String> currShowtimes = cineplexChosen.cinemas.get(index).showtimes[dateIndex];
		
		System.out.println("Current Show Times for " + movieChosen.title + ": " + currShowtimes);
		System.out.print("Number of Showtimes for " + movieChosen.title + "  to be removed: ");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Remove Showtime: ");
			currShowtimes.remove(scan.nextLine());
		}
		System.out.println("New Showtimes for " + movieChosen.title + " at " + cineplexChosen.name + " " + cineplexChosen.name + " : " + currShowtimes);
		cineplexChosen.cinemas.get(index).showtimes[dateIndex] = currShowtimes;
	}

	public void configureSettings() {
		Price.updatePrices();
	}
	
	public void addNewHoliday() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter date in the format DD/MM");
		String date = scan.next();
		DateChecker.addSpecialDate(date);
		
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

package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class AdminHelper extends Helper{
	
	MovieHelper movieHelper;
	CinemaHelper cinemaHelper;
	ShowtimeHelper showtimeHelper;
	ViewHelper viewHelper;
	CineplexHelper cineplexHelper;
	
	public AdminHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieHelper = new MovieHelper(uniqueMovies, user, cineplexes);
		this.cinemaHelper = new CinemaHelper(uniqueMovies, user, cineplexes);
		this.showtimeHelper = new ShowtimeHelper(uniqueMovies, user, cineplexes);
		this.viewHelper = new ViewHelper(uniqueMovies, user, cineplexes);
		this.cineplexHelper = new CineplexHelper(uniqueMovies, user, cineplexes);
	}

	public Movie createMovieListing(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		Movie movie = movieHelper.createMovie(uniqueMovies);
		if (movie.status.equals("Showing")) {
			System.out.println("Where would you like to show this movie in?");
			cineplexHelper.printCineplexes(cineplexes);
			Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
			ArrayList <String> showtimes = showtimeHelper.createShowtimes();
			Cinema cinemaChosen = cinemaHelper.printAndSelectCinema(cineplexChosen);
			movieHelper.replaceMovie(cineplexChosen, cinemaChosen, movie, showtimes);
		}
		return movie;
	}
	
	public void updateMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		movieHelper.printMovies(uniqueMovies);
		Movie movieChosen = movieHelper.selectMovie(uniqueMovies);
		System.out.print("New Showing Status of " + movieChosen.title + "(Showing/End of Showing): ");
		Scanner scan = new Scanner(System.in);
		String newStatus = scan.nextLine();
		if (movieChosen.status.equals(newStatus)) {
			System.out.println("New status same as previous status");
			System.out.println("Shall not update movie status");
		}else if (newStatus.equals("Showing")){
			movieChosen.status = newStatus;
			cineplexHelper.printCineplexes(cineplexes);
			Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
			ArrayList <String> showtimes = showtimeHelper.createShowtimes();
			Cinema cinemaChosen = cinemaHelper.printAndSelectCinema(cineplexChosen);
			movieHelper.replaceMovie(cineplexChosen, cinemaChosen, movieChosen, showtimes);			
		}else if (newStatus.equals("End of Showing")) {
			movieChosen.status = newStatus;
			uniqueMovies.remove(movieChosen);
		}
	}

	public void removeMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		movieHelper.printMovies(uniqueMovies);
		Movie movieChosen = movieHelper.selectMovie(uniqueMovies);
		System.out.println(movieChosen.title + " will be set to End of Showing");
		movieChosen.status = "End of Showing";
		uniqueMovies.remove(movieChosen);
		movieHelper.printMovies(uniqueMovies);
	}
	
	// Has issues
	public void createCinemaShowtimes(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		int valid = movieHelper.ensureUnshownMovies(uniqueMovies);
		if (valid == 0) {
			System.out.println("There are no movies listed as 'Coming Soon'. Please create a new movie listing first before creating showtimes.");
			return;
		}
		else {
		Movie movieChosen = movieHelper.printAndSelectFromUnshownMovies(uniqueMovies);
		movieChosen.status = "Showing";
		System.out.println("Choose Cineplex to be shown in:");
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
		ArrayList <String> showtimes = showtimeHelper.createShowtimes();
		Cinema cinemaChosen = cinemaHelper.printAndSelectCinema(cineplexChosen);
		movieHelper.replaceMovie(cineplexChosen, cinemaChosen, movieChosen, showtimes);
		}
	}

	
	public void updateCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movieChosen = movieHelper.selectMovie(user, cineplexChosen);
		String date = movieHelper.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
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
	
	public void addCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movieChosen = movieHelper.selectMovie(user, cineplexChosen);
		String date = movieHelper.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
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

	public void removeCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movieChosen = movieHelper.selectMovie(user, cineplexChosen);
		String date = movieHelper.printAndSelectMovieDates(cineplexChosen, movieChosen);
		
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
}

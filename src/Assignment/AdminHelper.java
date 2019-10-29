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
			Cineplex cineplex_chosen = cineplexHelper.selectCineplex(cineplexes);
			ArrayList <String> showtimes = showtimeHelper.createShowtimes();
			Cinema cinema_chosen = cinemaHelper.printAndSelectCinema(cineplex_chosen);
			movieHelper.replaceMovie(cineplex_chosen, cinema_chosen, movie, showtimes);
		}
		movieHelper.printMovies(uniqueMovies);
		return movie;
	}
	
	public void updateMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		movieHelper.printMovies(uniqueMovies);
		Movie movie_chosen = movieHelper.selectMovie(uniqueMovies);
		System.out.println("What is the new showing status of " + movie_chosen.title + "? (Showing/End of Showing)");
		Scanner scan = new Scanner(System.in);
		String newStatus = scan.nextLine();
		if (movie_chosen.status.equals(newStatus)) {
			System.out.println("New status same as previous status");
			System.out.println("Shall not update movie status");
		}else if (newStatus.equals("Showing")){
			movie_chosen.status = newStatus;
			cineplexHelper.printCineplexes(cineplexes);
			Cineplex cineplex_chosen = cineplexHelper.selectCineplex(cineplexes);
			ArrayList <String> showtimes = showtimeHelper.createShowtimes();
			Cinema cinema_chosen = cinemaHelper.printAndSelectCinema(cineplex_chosen);
			movieHelper.replaceMovie(cineplex_chosen, cinema_chosen, movie_chosen, showtimes);			
		}else if (newStatus.equals("End of Showing")) {
			movie_chosen.status = newStatus;
			uniqueMovies.remove(movie_chosen);
		}
	}

	public void removeMovieListing(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		movieHelper.printMovies(uniqueMovies);
		Movie movie_chosen = movieHelper.selectMovie(uniqueMovies);
		System.out.println(movie_chosen.title + " will be set to End of Showing");
		movie_chosen.status = "End of Showing";
		uniqueMovies.remove(movie_chosen);
		movieHelper.printMovies(uniqueMovies);
	}
	
	public void createCinemaShowtimes(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		Movie movie_chosen = movieHelper.printAndSelectFromUnshownMovies(uniqueMovies);
		movie_chosen.status = "Showing";
		System.out.println("Where would you like to show this movie in?");
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplex_chosen = cineplexHelper.selectCineplex(cineplexes);
		ArrayList <String> showtimes = showtimeHelper.createShowtimes();
		Cinema cinema_chosen = cinemaHelper.printAndSelectCinema(cineplex_chosen);
		movieHelper.replaceMovie(cineplex_chosen, cinema_chosen, movie_chosen, showtimes);
	}
	
	public void updateCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movieChosen = movieHelper.selectMovie(user, cineplexChosen);
		
		int index = cineplexChosen.movies.indexOf(movieChosen);
		ArrayList <String> currShowtimes = cineplexChosen.cinemas.get(index).showtimes;
		
		System.out.println("Current showtimes are " + currShowtimes);
		System.out.println("How many showtimes " + movieChosen.title + " would you like to update?");
		while (!scan.hasNextInt()){
			System.out.println("Error... Please input an Integer");
			scan.nextLine();
		}
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.println("Which showtime would you like to remove?");
			currShowtimes.remove(scan.nextLine());
			System.out.println("What showtime would you like to add?");
			currShowtimes.add(scan.nextLine());
		}
		Collections.sort(currShowtimes);
		System.out.println("New Showtimes for " + movieChosen.title + " at " + cineplexChosen.name + " " + cineplexChosen.name + " are " + currShowtimes);
		cineplexChosen.cinemas.get(index).showtimes = currShowtimes;
	}

	public void removeCinemaShowtimes(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		cineplexHelper.printCineplexes(cineplexes);
		Cineplex cineplexChosen = cineplexHelper.selectCineplex(cineplexes);
		Movie movieChosen = movieHelper.selectMovie(user, cineplexChosen);
		
		int index = cineplexChosen.movies.indexOf(movieChosen);
		ArrayList <String> currShowtimes = cineplexChosen.cinemas.get(index).showtimes;
		
		System.out.println("Current Show Times for " + movieChosen.title + " are " + currShowtimes);
		System.out.println("How many showtimes for " + movieChosen.title + " would you like to remove?");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Showtime: ");
			currShowtimes.remove(scan.nextLine());
			System.out.println("Current Show Times for " + movieChosen.title + " are " + currShowtimes);
		}
		System.out.println("New Showtimes for " + movieChosen.title + " at " + cineplexChosen.name + " " + cineplexChosen.name + " are " + currShowtimes);
		cineplexChosen.cinemas.get(index).showtimes = currShowtimes;
	}

	public void configureSettings() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Which would you like to adjust?");
		System.out.println("1. Ticket Base Price");
		System.out.println("2. Peak Period Multiplier");
		System.out.println("3. 3D Multiplier");
		System.out.println("4. Gold Class Multiplier");
		while (!scan.hasNextInt()){
			System.out.println("Error... Please input an Integer");
			scan.nextLine();
		}
		int choice = scan.nextInt();
		float mult;
		// after price class is finished, set multipliers from price class
		// need to add senior citizen and child discounts 
		// set holidays (most probably from), therefore need holiday multiplier
		// maybe remove peak period price????
		// weekend multiplier
		switch (choice) {
			case 1:
				System.out.println("What is the new base price?");
				choice = scan.nextInt();
				MovieTicket.setBase(choice);
				break;
			case 2:
				System.out.println("What is the new peak period multiplier?");
				mult = scan.nextFloat();
				MovieTicket.setPeakMult(mult);
				break;
			case 3:
				System.out.println("What is the new 3D multiplier?");
				mult = scan.nextFloat();
				MovieTicket.set3DMult(mult);
				break;
			case 4:
				System.out.println("What is the new Gold Class Multiplier?");
				mult = scan.nextFloat();
				MovieTicket.setGoldMult(mult);
				break;
			default:
				System.out.println("Not a valid option");
				break;
		}
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

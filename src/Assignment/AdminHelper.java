package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class AdminHelper extends Helper{

	public AdminHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}

	public Movie admin_1(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		Movie movie = createMovie(uniqueMovies);
		if (movie.status.equals("Showing")) {
			System.out.println("Where would you like to show this movie in?");
			printCineplexes(cineplexes);
			Cineplex cineplex_chosen = selectCineplex(cineplexes);
			ArrayList <String> showtimes = createShowtimes();
			Cinema cinema_chosen = selectCinema(cineplex_chosen);
			replaceMovie(cineplex_chosen, cinema_chosen, movie, showtimes);
		}
		printMovies(uniqueMovies);
		return movie;
	}
	
	public void admin_2(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		printMovies(uniqueMovies);
		Movie movie_chosen = selectMovie(uniqueMovies);
		System.out.println("What is the new showing status of " + movie_chosen.title + "? (Showing/End of Showing)");
		Scanner scan = new Scanner(System.in);
		String newStatus = scan.nextLine();
		if (movie_chosen.status.equals(newStatus)) {
			System.out.println("New status same as previous status");
			System.out.println("Shall not update movie status");
		}else if (newStatus.equals("Showing")){
			movie_chosen.status = newStatus;
			printCineplexes(cineplexes);
			Cineplex cineplex_chosen = selectCineplex(cineplexes);
			ArrayList <String> showtimes = createShowtimes();
			Cinema cinema_chosen = selectCinema(cineplex_chosen);
			replaceMovie(cineplex_chosen, cinema_chosen, movie_chosen, showtimes);			
		}else if (newStatus.equals("End of Showing")) {
			movie_chosen.status = newStatus;
			uniqueMovies.remove(movie_chosen);
		}
	}

	public void admin_3(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		printMovies(uniqueMovies);
		Movie movie_chosen = selectMovie(uniqueMovies);
		System.out.println(movie_chosen.title + " will be set to End of Showing");
		movie_chosen.status = "End of Showing";
		uniqueMovies.remove(movie_chosen);
		printMovies(uniqueMovies);
	}
	
	public void admin_4(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		Movie movie_chosen = printAndSelectFromUnshownMovies(uniqueMovies);
		movie_chosen.status = "Showing";
		System.out.println("Where would you like to show this movie in?");
		printCineplexes(cineplexes);
		Cineplex cineplex_chosen = selectCineplex(cineplexes);
		ArrayList <String> showtimes = createShowtimes();
		Cinema cinema_chosen = selectCinema(cineplex_chosen);
		replaceMovie(cineplex_chosen, cinema_chosen, movie_chosen, showtimes);
	}
	
	public void admin_5(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		Cineplex cineplexChosen = selectCineplex(cineplexes);
		Movie movieChosen = selectMovie(user, cineplexChosen);
		
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

	public void admin_6(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		Cineplex cineplexChosen = selectCineplex(cineplexes);
		Movie movieChosen = selectMovie(user, cineplexChosen);
		
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

	public void admin_7() {
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

	public void admin_8(Set<Movie> uniqueMovies) {
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
}

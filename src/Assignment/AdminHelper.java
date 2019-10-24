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
		return movie;
	}
	
	public void admin_2(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		printAllMovies(uniqueMovies);
		Movie movie_chosen = selectFromAllMovie(uniqueMovies);
		System.out.println("What is the new showing status of " + movie_chosen.title + "? (Preview/Showing/End of Showing)");
		Scanner scan = new Scanner(System.in);
		String newStatus = scan.nextLine();
		if (movie_chosen.status.equals(newStatus)) {
			System.out.println("New status same as previous status");
			System.out.println("Shall not update movie status");
		}else if (newStatus.equals("Showing")){
			movie_chosen.status = newStatus;
			printCineplexes(cineplexes);
			Cineplex cineplex_chosen = selectCineplex(cineplexes);
			System.out.println("What is the number of showtimes for the movie");
			int numShows = scan.nextInt();
			scan.nextLine(); // remove the space left
			ArrayList <String> showtimes = new ArrayList <String>();
			for (int i=0; i<numShows; i++) {
				System.out.println("What is showtime " + (i+1) + "?");
				showtimes.add(scan.nextLine());
			}
				
			Cinema cinema_chosen = selectCinema(cineplex_chosen);
			
			System.out.println("Previous movies");
			printCineplexMovie(cineplex_chosen);
			int index = cineplex_chosen.cinemas.indexOf(cinema_chosen);
			cineplex_chosen.movies.get(index).status = "End of Showing";
			System.out.println("Movie being replaced is " + cineplex_chosen.movies.get(index).title);
			cineplex_chosen.movies.set(index, movie_chosen);
			System.out.println("New movie is " + cineplex_chosen.movies.get(index).title);
			cineplex_chosen.cinemas.get(index).showtimes = showtimes;
			System.out.println("Movie " + movie_chosen.title + " added to " + cineplex_chosen.name + " " + cineplex_chosen.location + " in Cinema Code " + cinema_chosen.cinema_code);
			System.out.println("New movies");
			printCineplexMovie(cineplex_chosen);
			
		}else if (newStatus.equals("End of Showing")) {
			movie_chosen.status = newStatus;
			uniqueMovies.remove(movie_chosen);
		}
	}

	public void admin_3(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		printAllMovies(uniqueMovies);
		Movie movie_chosen = selectFromAllMovie(uniqueMovies);
		System.out.println(movie_chosen.title + " will be set to End of Showing");
		movie_chosen.status = "End of Showing";
		uniqueMovies.remove(movie_chosen);
	}
	
	public void admin_4(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		System.out.println("Please key in the new movie you would like to show");
		Movie movie = createMovie(uniqueMovies);
		movie.status = "Showing";
		System.out.println("What is the number of showtimes for the movie");
		Scanner scan = new Scanner(System.in);
		int numShows = scan.nextInt();
		scan.nextLine(); // remove the space left
		ArrayList <String> showtimes = new ArrayList <String>();
		for (int i=0; i<numShows; i++) {
			System.out.println("What is showtime " + (i+1) + "?");
			showtimes.add(scan.nextLine());
		}
			
		printCineplexes(cineplexes);
		Cineplex cineplex_chosen = selectCineplex(cineplexes);
		Cinema cinema_chosen = selectCinema(cineplex_chosen);
		
		int index = cineplex_chosen.cinemas.indexOf(cinema_chosen);
		cineplex_chosen.movies.get(index).status = "End of Showing";
		cineplex_chosen.movies.set(index, movie);
		cineplex_chosen.cinemas.get(index).showtimes = showtimes;
		System.out.println("Movie " + movie.title + " added to " + cineplex_chosen.name + " " + cineplex_chosen.location + " in Cinema Code " + cinema_chosen.cinema_code);
		System.out.println("New Movies:");
		printCineplexMovie(cineplex_chosen);
	}
	
	public void admin_5(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies, User user) {
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		Cineplex cineplexChosen = selectCineplex(cineplexes);
		Movie movieChosen = selectFromCineplexMovie(user, cineplexChosen);
		
		int index = cineplexChosen.movies.indexOf(movieChosen);
		ArrayList <String> currShowtimes = cineplexChosen.cinemas.get(index).showtimes;
		
		System.out.println("How many showtimes for " + movieChosen.title + " would you like to insert?");
		int choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Showtime: ");
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
		Movie movieChosen = selectFromCineplexMovie(user, cineplexChosen);
		
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
}

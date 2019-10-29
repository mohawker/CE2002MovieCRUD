package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MovieHelper extends Helper{

	public MovieHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	// print all unique movies
	public void printMovies(Set<Movie> uniqueMovies) {
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.status.equals("End of Showing")) {
				System.out.println("[" + count + "] " + m.title + ", Status: " + m.status);
				count += 1;
			}
		}
	}
	
	// print all movies in a cineplex
	public void printMovies(Cineplex cineplex) {
		for (int i=0 ; i<cineplex.movies.size(); i++) {
			if (!cineplex.movies.get(i).status.equals("End of Showing")) {
				System.out.println("[" + (i+1) + "] " + cineplex.movies.get(i).title + " (" + cineplex.cinemas.get(i).cinema_type + ")");
			}
		}
	}
	
	// print movie showtimes for a movie in all cineplex
	public void printMovieShowings(Movie movie_chosen, ArrayList<Cineplex> cineplexes) {
		if (movie_chosen.status.equals("Showing")){
			for (int i=0; i<cineplexes.size(); i++) {
				System.out.println("=== Showing for " + movie_chosen.title + " at " + cineplexes.get(i).name + " " + cineplexes.get(i).location + " ===\n");
				int movieIndex = cineplexes.get(i).movies.indexOf(movie_chosen);
				if (movieIndex != -1) {
					for (int date=0; date < (cineplexes.get(i).cinemas.get(movieIndex).dates.size()); date++) {
						int index = cineplexes.get(i).movies.indexOf(movie_chosen);
						if (index != -1) {
							ArrayList<String> showtimes = cineplexes.get(i).cinemas.get(index).showtimes[date];
							Collections.sort(showtimes);
							System.out.println("Showtimes on " + cineplexes.get(i).cinemas.get(movieIndex).dates.get(date) + " are "+ showtimes);
						}				
					}
					System.out.println();
				}else {
					System.out.println("NO SHOWINGS\n");
				}
			}
		}else {
			System.out.println(movie_chosen.title + " is not showing yet.");
		}
	}
	
	// select a movie from all unique movies
	public Movie selectMovie(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		System.out.print("Select your movie: ");
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		System.out.println();
		int choice = scan.nextInt();
		return uniqueMoviesList.get(choice-1);
	}
	
	// select a movie from a particular cineplex
	public Movie selectMovie(User user, Cineplex cineplex) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Movies ===");
		printMovies(cineplex);
		System.out.print("Select your movie: ");
		int choice = -1;
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		while (choice<1 || choice > cineplex.movies.size()) {
			choice = scan.nextInt();
			if (!(choice>=1 && choice <= cineplex.movies.size())) {
				System.out.println("Please enter a valid selection from 1-"+ cineplex.movies.size());
			}
			else {
				System.out.println();
				user.viewMovieDetail(cineplex.movies.get(choice-1));
			}
		}
		return cineplex.movies.get(choice-1);
	}
	
	// for adding showtimes to movies which are coming soon/preview
	public Movie printAndSelectFromUnshownMovies(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		List<Movie> unshownMoviesList = new ArrayList<Movie>(uniqueMovies);
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.status.equals("End of Showing") && !m.status.equals("Showing")) {
				System.out.println(count + ". " + m.title + ", Status: " + m.status);
				count += 1;
			}else {
				unshownMoviesList.remove(m);
			}
		}
		System.out.print("Select your movie: ");
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		int choice = scan.nextInt();
		return unshownMoviesList.get(choice-1);
	}

	// creating a new movie
	public Movie createMovie(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name of Movie: ");
		String title = scan.nextLine();
		System.out.print("Status of Movie (Coming Soon/Preview/Showing): ");
		String status = scan.nextLine();
		System.out.print("Synopsis: ");
		String synopsis = scan.nextLine();
		System.out.print("Director: ");
		String director = scan.nextLine();
		System.out.print("Type (3D/Blockbuster): ");
		String type = scan.nextLine();
		System.out.print("Number of Cast Members: ");
		int numCast = scan.nextInt();
		scan.nextLine();; // remove the space left
		ArrayList <String> cast = new ArrayList <String>();
		for (int i=0; i<numCast; i++) {
			System.out.print("Cast Member " + (i+1) + ": ");
			cast.add(scan.nextLine());
		}
		Movie movie = new Movie(title, status, synopsis, director, type, cast);
		uniqueMovies.add(movie);
		return movie;
	}
	
	// sorting movies by sale/ratings
	public void sortMovies(Set<Movie> uniqueMovies, boolean bySales) {
		ArrayList<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		ArrayList<Movie> sortedMoviesList = new ArrayList<Movie>();
		sortedMoviesList.add(uniqueMoviesList.get(0));
		if (bySales) {
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

	public void replaceMovie(Cineplex cineplex_chosen, Cinema cinema_chosen, Movie movie_chosen, ArrayList<String> showtimes) {
		System.out.println("=== Previous Movies at " + cineplex_chosen.name + " " + cineplex_chosen.location + " ===");
		printMovies(cineplex_chosen);
		int index = cineplex_chosen.cinemas.indexOf(cinema_chosen);
		cineplex_chosen.movies.get(index).status = "End of Showing";
		System.out.println("\nMovie being replaced: " + cineplex_chosen.movies.get(index).title);
		cineplex_chosen.movies.set(index, movie_chosen);
		System.out.println("New movie: " + cineplex_chosen.movies.get(index).title);
		ArrayList <String>[] listOfTimes = new ArrayList[6];
		for (int i=0; i<cineplex_chosen.cinemas.get(index).dates.size(); i++) {
			listOfTimes[i] = showtimes;
		}
		cineplex_chosen.cinemas.get(index).showtimes = listOfTimes;
		System.out.println("Movie " + movie_chosen.title + " added to " + cineplex_chosen.name + " " + cineplex_chosen.location + " in Cinema Code " + cinema_chosen.cinema_code);
		System.out.println("=== New Movies ===");
		printMovies(cineplex_chosen);
	}
	
	public String printAndSelectMovieDates(Cineplex cineplex, Movie movie_chosen) {
		Scanner scan = new Scanner(System.in);
		Cinema cinema = cineplex.cinemas.get(cineplex.movies.indexOf(movie_chosen));
		System.out.println("===Dates available for " + movie_chosen.title + " at " + cineplex.name + " " + cineplex.location + "===");
		for (int i=0; i<cinema.dates.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinema.dates.get(i));
		}
		System.out.print("Select date: " );
		int choice = scan.nextInt();
		System.out.println();
		return cinema.dates.get(choice);
	}
}

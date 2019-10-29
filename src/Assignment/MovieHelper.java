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
				System.out.println(count + ". " + m.title + ", Status: " + m.status);
				count += 1;
			}
		}
	}
	
	// print all movies in a cineplex
	public void printMovies(Cineplex cineplex) {
		for (int i=0 ; i<cineplex.movies.size(); i++) {
			if (!cineplex.movies.get(i).status.equals("End of Showing")) {
				System.out.println((i+1) + ". " + cineplex.movies.get(i).title + " (" + cineplex.cinemas.get(i).cinema_type + ")");
			}
		}
	}
	
	// print movie showtimes for a movie in all cineplex
	public void printMovieShowings(Movie movie_chosen, ArrayList<Cineplex> cineplexes) {
		if (movie_chosen.status.equals("Showing")){
			for (int i=0; i<cineplexes.size(); i++) {
				System.out.println("Showing for " + movie_chosen.title + " at " + cineplexes.get(i).name + " " + cineplexes.get(i).location);
				int index = cineplexes.get(i).movies.indexOf(movie_chosen);
				if (index != -1) {
					ArrayList<String> showtimes = cineplexes.get(i).cinemas.get(index).showtimes;
					Collections.sort(showtimes);
					for (int j = 0; j<showtimes.size(); j++) {
						System.out.println("Showtime: " + showtimes.get(j));
					}
				}else {
					System.out.println("NO SHOWINGS");
				}
				System.out.println();
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
		System.out.println("What is the name of movie?");
		String title = scan.nextLine();
		System.out.println("What is the status of movie? (Coming Soon/Preview/Showing)");
		String status = scan.nextLine();
		System.out.println("What is the synopsis of the movie");
		String synopsis = scan.nextLine();
		System.out.println("What is the director of the movie");
		String director = scan.nextLine();
		System.out.println("What is the type of the movie");
		String type = scan.nextLine();
		System.out.println("What is the number of cast members of the movie");
		int numCast = scan.nextInt();
		scan.nextLine();; // remove the space left
		ArrayList <String> cast = new ArrayList <String>();
		for (int i=0; i<numCast; i++) {
			System.out.println("Who is cast member " + (i+1) + "?");
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
		System.out.println("Previous movies");
		printMovies(cineplex_chosen);
		int index = cineplex_chosen.cinemas.indexOf(cinema_chosen);
		cineplex_chosen.movies.get(index).status = "End of Showing";
		System.out.println("Movie being replaced is " + cineplex_chosen.movies.get(index).title);
		cineplex_chosen.movies.set(index, movie_chosen);
		System.out.println("New movie is " + cineplex_chosen.movies.get(index).title);
		cineplex_chosen.cinemas.get(index).showtimes = showtimes;
		System.out.println("Movie " + movie_chosen.title + " added to " + cineplex_chosen.name + " " + cineplex_chosen.location + " in Cinema Code " + cinema_chosen.cinema_code);
		System.out.println("New movies");
		printMovies(cineplex_chosen);
	}
}

package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import Entity.Admin;
import Entity.Cinema;
import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

/**
 * Provides helper functions relating to movies
 * @author vince
 *
 */
public class MovieControl extends Control{

	public MovieControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Prints out unique movies across all cineplexes
	 * @param uniqueMovies
	 */
	public void printMovies(Set<Movie> uniqueMovies) {
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.getStatus().equals("End of Showing")) {
				System.out.println("[" + count + "] " + m.getTitle() + ", Status: " + m.getStatus());
				count += 1;
			}
		}
	}
	
	/**
	 * Prints out movie shown in a cineplex
	 * @param cineplex
	 */
	public void printMovies(Cineplex cineplex) {
		for (int i=0 ; i<cineplex.getMovies().size(); i++) {
			if (!cineplex.getMovies().get(i).getStatus().equals("End of Showing")) {
				System.out.println("[" + (i+1) + "] " + cineplex.getMovies().get(i).getTitle() + " (" + cineplex.getMovies().get(i).getType() + ")");
			}
		}
	}
	
	/**
	 * Print movie showtimes for a given movie at a particular cineplex
	 * @param movieChosen
	 * @param cineplexes
	 */
	public void printMovieShowings(Movie movieChosen, ArrayList<Cineplex> cineplexes) {
		if (movieChosen.getStatus().equals("Showing")){
			for (int i=0; i<cineplexes.size(); i++) {
				System.out.println("=== Showing for " + movieChosen.getTitle() + " at " + cineplexes.get(i).getName() + " " + cineplexes.get(i).getLocation() + " ===\n");
				int movieIndex = cineplexes.get(i).getMovies().indexOf(movieChosen);
				if (movieIndex != -1) {
					for (int date=0; date < (cineplexes.get(i).getCinemas().get(movieIndex).getDates().size()); date++) {
						int index = cineplexes.get(i).getMovies().indexOf(movieChosen);
						if (index != -1) {
							ArrayList<String> showtimes = cineplexes.get(i).getCinemas().get(index).getShowtime()[date];
							Collections.sort(showtimes);
							System.out.println("Showtimes on " + cineplexes.get(i).getCinemas().get(movieIndex).getDates().get(date) + " are "+ showtimes);
						}				
					}
					System.out.println();
				}else {
					System.out.println("NO SHOWINGS\n");
				}
			}
		}else {
			System.out.println(movieChosen.getTitle() + " is not showing yet.");
		}
	}
	
	
	/**
	 * Prompts user to select a movie from the unqiue movies across all cineplexes
	 * @param uniqueMovies
	 * @return
	 */
	public Movie selectMovie(Set<Movie> uniqueMovies) {
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		System.out.print("Select your movie: ");
		int choice = InputControl.integerInput(1, uniqueMoviesList.size());
		return uniqueMoviesList.get(choice-1);
	}
	
	/**
	 * Prompts user to select a movie from a list of movies in a cineplex
	 * @param cineplex
	 * @return
	 */
	public Movie selectMovie(Cineplex cineplex) {
		System.out.println("=== Movies ===");
		printMovies(cineplex);
		System.out.print("Select your movie: ");
		int choice = InputControl.integerInput(1, cineplex.getMovies().size());
		System.out.println();
		cineplex.getMovies().get(choice-1).printMovie();
		return cineplex.getMovies().get(choice-1);
	}
	
	/*
	 * Ensure that there are movies that are "Coming Soon" or "Preview" for showtimes to be created for them
	 */
	public int ensureUnshownMovies(Set<Movie> uniqueMovies) {
		List<Movie> unshownMoviesList = new ArrayList<Movie>(uniqueMovies);
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.getStatus().equals("End of Showing") && !m.getStatus().equals("Showing")) {
				count += 1;
			}else {
				unshownMoviesList.remove(m);
			}
		}
		if (count==1) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	/*
	 * Print movies which are "Coming Soon" or "Preview"
	 */
	public Movie printAndSelectFromUnshownMovies(Set<Movie> uniqueMovies) {
		List<Movie> unshownMoviesList = new ArrayList<Movie>(uniqueMovies);
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.getStatus().equals("End of Showing") && !m.getStatus().equals("Showing")) {
				System.out.println("[" + count + "] " + m.getTitle() + ", Status: " + m.getStatus());
				count += 1;
			}else {
				unshownMoviesList.remove(m);
			}
		}
		System.out.print("Select your movie: ");
		int choice = InputControl.integerInput(1, unshownMoviesList.size());
		return unshownMoviesList.get(choice-1);
	}

	/*
	 * Prompts user for attributes needed to instantiate a new movie
	 */
	public Movie createMovie(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name of Movie: ");
		String title = InputControl.stringInput();
		System.out.print("Status of Movie (Coming Soon/Preview/Showing): ");
		String status = InputControl.stringInput();
		System.out.print("Synopsis: ");
		String synopsis = InputControl.lineInput();
		System.out.print("Director: ");
		String director = InputControl.stringInput();
		System.out.print("Type (3D/Blockbuster): ");
		String type = InputControl.stringInput();
		System.out.print("Number of Cast Members: ");
		int numCast = InputControl.integerInput(2, 999);
		ArrayList <String> cast = new ArrayList <String>();
		for (int i=0; i<numCast; i++) {
			System.out.print("Cast Member " + (i+1) + ": ");
			cast.add(scan.nextLine());
		}
		System.out.print("Age Rating: ");
		String ageRating = scan.next();
		Movie movie = new Movie(title, status, synopsis, director, type, cast, ageRating);
		uniqueMovies.add(movie);
		return movie;
	}
	
	/*
	 * Sort all the unique movies by sales or by review scores and prints out the top 5
	 */
	public void sortMovies(Set<Movie> uniqueMovies, boolean bySales) {
		ArrayList<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		ArrayList<Movie> sortedMoviesList = new ArrayList<Movie>();
		sortedMoviesList.add(uniqueMoviesList.get(0));
		if (bySales) {
			for (int i=1; i<uniqueMoviesList.size(); i++) {
				for (int j=0; j<sortedMoviesList.size(); j++) {
					if (uniqueMoviesList.get(i).getMovieSales()>sortedMoviesList.get(j).getMovieSales()) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}else if (j == sortedMoviesList.size()-1) {
						sortedMoviesList.add(j+1, uniqueMoviesList.get(i));
						break;
					}
				}
			}
			System.out.println("\n=== Top 5 Movies by Sales ===");
			for (int i=0; i<5; i++) {
				Movie movieChosen = sortedMoviesList.get(i);
				System.out.println("[" + (i+1) + "] " + movieChosen.getTitle() + " has total sales of $" + movieChosen.getMovieSales());
			}
		}else {
			for (int i=1; i<uniqueMoviesList.size(); i++) {
				for (int j=0; j<sortedMoviesList.size(); j++) {
					if (uniqueMoviesList.get(i).getAverageRating()>sortedMoviesList.get(j).getAverageRating()) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}else if (j == sortedMoviesList.size()-1) {
						sortedMoviesList.add(j+1, uniqueMoviesList.get(i));
						break;
					}
				}
			}
			System.out.println("\n=== Top 5 Movies by Ratings ===");
			for (int i=0; i<5; i++) {
				Movie movieChosen = sortedMoviesList.get(i);
				System.out.println("[" + (i+1) + "] " + movieChosen.getTitle() + " has overall rating of " + movieChosen.getAverageRating() + " out of 5.0");
			}
		}
	}
	
	/**
	 * Counts the number of days between 2 dates in DD/MM/YYYY format
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 * Show a new movie until a given date specified by the admin and replace the movie that was previously showing
	 * @param cineplexChosen
	 * @param cinemaChosen
	 * @param movieChosen
	 * @param showtimes
	 * @param uniqueMovies
	 */
	public void replaceMovie(Cineplex cineplexChosen, Cinema cinemaChosen, Movie movieChosen, ArrayList<String> showtimes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Previous Movies at " + cineplexChosen.getName() + " " + cineplexChosen.getLocation() + " ===");
		printMovies(cineplexChosen);
		int index = cineplexChosen.getCinemas().indexOf(cinemaChosen);
		cineplexChosen.getMovies().get(index).setStatus("End of Showing");
		uniqueMovies.remove(cineplexChosen.getMovies().get(index));
		System.out.println("\nMovie being replaced: " + cineplexChosen.getMovies().get(index).getTitle());
		cineplexChosen.getMovies().set(index, movieChosen);
		System.out.println("New Movie: " + cineplexChosen.getMovies().get(index).getTitle());
		
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    
	    long days=0;
	    Date currentDate = new Date();
		Date firstDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));
		try {
			System.out.print("End Date of Movie (DD/MM/YYYY): ");
		    Date endDate = sdf.parse(scan.next());
		    long diffInMillies = Math.abs(endDate.getTime() - firstDate.getTime());
			days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		}catch (ParseException e) {
            e.printStackTrace();
        }
		

		ArrayList<String> dates = new ArrayList<String>();
		
		for(int i=1;i<=days+2;i++){
			Date newDate = new Date(currentDate.getTime() + i*(1000 * 60 * 60 * 24));
			dates.add(sdf.format(newDate));
		}
		
		System.out.println(movieChosen.getTitle() + " will be shown from " + dates.get(0) + " to " + dates.get(dates.size()-1));		
		cineplexChosen.getCinemas().get(index).setDates(dates);
		ArrayList <String>[] listOfTimes = new ArrayList[dates.size()];
		for (int i=0; i<cineplexChosen.getCinemas().get(index).getDates().size(); i++) {
			listOfTimes[i] = showtimes;
		}
		cineplexChosen.getCinemas().get(index).setShowtime(listOfTimes);
		System.out.println("Movie " + movieChosen.getTitle() + " added to " + cineplexChosen.getName() + " " + cineplexChosen.getLocation() + " in Cinema Code " + cinemaChosen.getCinemaCode());
		System.out.println("\n=== New Movies ===");
		printMovies(cineplexChosen);
	}
	
	/**
	 * Print out the available dates for a movie and select from those options
	 * @param cineplex
	 * @param movieChosen
	 * @return
	 */
	public String printAndSelectMovieDates(Cineplex cineplex, Movie movieChosen) {
		Cinema cinema = cineplex.getCinemas().get(cineplex.getMovies().indexOf(movieChosen));
		System.out.println("=== Dates available for " + movieChosen.getTitle() + " at " + cineplex.getName() + " " + cineplex.getLocation() + " ===");
		for (int i=0; i<5; i++) {
			System.out.println("[" + (i+1) + "] " + cinema.getDates().get(i));
		}
		System.out.print("Select Date: " );
		int choice = InputControl.integerInput(1, cinema.getDates().size());
		System.out.println();
		return cinema.getDates().get(choice);
	}
}

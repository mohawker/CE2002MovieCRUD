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

public class MovieControl extends Control{

	public MovieControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	// print all unique movies
	public void printMovies(Set<Movie> uniqueMovies) {
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.getStatus().equals("End of Showing")) {
				System.out.println("[" + count + "] " + m.getTitle() + ", Status: " + m.getStatus());
				count += 1;
			}
		}
	}
	
	// print all movies in a cineplex
	public void printMovies(Cineplex cineplex) {
		for (int i=0 ; i<cineplex.getMovies().size(); i++) {
			if (!cineplex.getMovies().get(i).getStatus().equals("End of Showing")) {
				System.out.println("[" + (i+1) + "] " + cineplex.getMovies().get(i).getTitle() + " (" + cineplex.getMovies().get(i).getType() + ")");
			}
		}
	}
	
	// print movie showtimes for a movie in all cineplex
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
	
	
	// select a movie from all unique movies
	public Movie selectMovie(Set<Movie> uniqueMovies) {
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		System.out.print("Select your movie: ");
		int choice = InputControl.integerInput(1, uniqueMoviesList.size());
		return uniqueMoviesList.get(choice-1);
	}
	
	// select a movie from a particular cineplex
	public Movie selectMovie(Cineplex cineplex) {
		System.out.println("=== Movies ===");
		printMovies(cineplex);
		System.out.print("Select your movie: ");
		int choice = InputControl.integerInput(1, cineplex.getMovies().size());
		System.out.println();
		cineplex.getMovies().get(choice-1).printMovie();
		return cineplex.getMovies().get(choice-1);
	}
	
	// for ensuring there are UnshownMovies
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
	
	// for adding showtimes to movies which are now showing
//	public Movie printAndSelectNowShowingMovies(Set<Movie> uniqueMovies) {
//		List<Movie> showingMoviesList = new ArrayList<Movie>(uniqueMovies);
//		int count = 1;
//		for (Movie m : uniqueMovies) {
//			if (!m.status.equals("End of Showing") && !m.status.equals("Coming Soon")) {
//				System.out.println(count + ". " + m.title + ", Status: " + m.status);
//				count += 1;
//			}else {
//				showingMoviesList.remove(m);
//			}
//		}
//		System.out.print("Select your movie: ");
//		int choice = InputHandler.integerInput(1, showingMoviesList.size());
//		return showingMoviesList.get(choice-1);
//	}
	
	// for adding showtimes to movies which are coming soon/preview
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

	// creating a new movie
	public Movie createMovie(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name of Movie: ");
		String title = InputControl.stringInput();
		System.out.print("Status of Movie (Coming Soon/Preview/Showing): ");
		String status = InputControl.stringInput();
		System.out.print("Synopsis: ");
		String synopsis = InputControl.stringInput();
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
					if (uniqueMoviesList.get(i).getMovieSales()>sortedMoviesList.get(j).getMovieSales()) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
						break;
					}else if (j == sortedMoviesList.size()-1) {
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
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
						sortedMoviesList.add(j, uniqueMoviesList.get(i));
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
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public void replaceMovie(Cineplex cineplexChosen, Cinema cinemaChosen, Movie movieChosen, ArrayList<String> showtimes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Previous Movies at " + cineplexChosen.getName() + " " + cineplexChosen.getLocation() + " ===");
		printMovies(cineplexChosen);
		int index = cineplexChosen.getCinemas().indexOf(cinemaChosen);
		cineplexChosen.getMovies().get(index).setStatus("End of Showing");
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

package Assignment;

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
				System.out.println("[" + (i+1) + "] " + cineplex.movies.get(i).title + " (" + cineplex.cinemas.get(i).getCinemaType() + ")");
			}
		}
	}
	
	// print movie showtimes for a movie in all cineplex
	public void printMovieShowings(Movie movieChosen, ArrayList<Cineplex> cineplexes) {
		if (movieChosen.status.equals("Showing")){
			for (int i=0; i<cineplexes.size(); i++) {
				System.out.println("=== Showing for " + movieChosen.title + " at " + cineplexes.get(i).name + " " + cineplexes.get(i).location + " ===\n");
				int movieIndex = cineplexes.get(i).movies.indexOf(movieChosen);
				if (movieIndex != -1) {
					for (int date=0; date < (cineplexes.get(i).cinemas.get(movieIndex).dates.size()); date++) {
						int index = cineplexes.get(i).movies.indexOf(movieChosen);
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
			System.out.println(movieChosen.title + " is not showing yet.");
		}
	}
	
	
	// select a movie from all unique movies
	public Movie selectMovie(Set<Movie> uniqueMovies) {
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		System.out.print("Select your movie: ");
		int choice = InputHandler.integerInput(1, uniqueMoviesList.size());
		return uniqueMoviesList.get(choice-1);
	}
	
	// select a movie from a particular cineplex
	public Movie selectMovie(User user, Cineplex cineplex) {
		System.out.println("=== Movies ===");
		printMovies(cineplex);
		System.out.print("Select your movie: ");
		int choice = InputHandler.integerInput(1, cineplex.movies.size());
		System.out.println();
		user.viewMovieDetail(cineplex.movies.get(choice-1));
		return cineplex.movies.get(choice-1);
	}
	
	// for ensuring there are UnshownMovies
	public int ensureUnshownMovies(Set<Movie> uniqueMovies) {
		List<Movie> unshownMoviesList = new ArrayList<Movie>(uniqueMovies);
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.status.equals("End of Showing") && !m.status.equals("Showing")) {
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
			if (!m.status.equals("End of Showing") && !m.status.equals("Showing")) {
				System.out.println(count + ". " + m.title + ", Status: " + m.status);
				count += 1;
			}else {
				unshownMoviesList.remove(m);
			}
		}
		System.out.print("Select your movie: ");
		int choice = InputHandler.integerInput(1, unshownMoviesList.size());
		return unshownMoviesList.get(choice-1);
	}

	// creating a new movie
	public Movie createMovie(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name of Movie: ");
		String title = InputHandler.stringInput();
		System.out.print("Status of Movie (Coming Soon/Preview/Showing): ");
		String status = InputHandler.stringInput();
		System.out.print("Synopsis: ");
		String synopsis = InputHandler.stringInput();
		System.out.print("Director: ");
		String director = InputHandler.stringInput();
		System.out.print("Type (3D/Blockbuster): ");
		String type = InputHandler.stringInput();
		System.out.print("Number of Cast Members: ");
		int numCast = InputHandler.integerInput(2, 999);
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
				Movie movieChosen = sortedMoviesList.get(i);
				System.out.println(movieChosen.title + " has total sales of $" + movieChosen.movieSales);
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
				Movie movieChosen = sortedMoviesList.get(i);
				System.out.println(movieChosen.title + " has overall rating of " + movieChosen.getAverageRating() + " out of 5.0");
			}
		}
	}
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public void replaceMovie(Cineplex cineplexChosen, Cinema cinemaChosen, Movie movieChosen, ArrayList<String> showtimes) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== Previous Movies at " + cineplexChosen.name + " " + cineplexChosen.location + " ===");
		printMovies(cineplexChosen);
		int index = cineplexChosen.cinemas.indexOf(cinemaChosen);
		cineplexChosen.movies.get(index).status = "End of Showing";
		System.out.println("\nMovie being replaced: " + cineplexChosen.movies.get(index).title);
		cineplexChosen.movies.set(index, movieChosen);
		System.out.println("New Movie: " + cineplexChosen.movies.get(index).title);
		
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
		
		System.out.println(movieChosen.title + " will be shown from " + dates.get(0) + " to " + dates.get(dates.size()-1));		
		cineplexChosen.cinemas.get(index).dates = dates;
		ArrayList <String>[] listOfTimes = new ArrayList[dates.size()];
		for (int i=0; i<cineplexChosen.cinemas.get(index).dates.size(); i++) {
			listOfTimes[i] = showtimes;
		}
		cineplexChosen.cinemas.get(index).showtimes = listOfTimes;
		System.out.println("Movie " + movieChosen.title + " added to " + cineplexChosen.name + " " + cineplexChosen.location + " in Cinema Code " + cinemaChosen.getCinemaCode());
		System.out.println("=== New Movies ===");
		printMovies(cineplexChosen);
	}
	
	public String printAndSelectMovieDates(Cineplex cineplex, Movie movieChosen) {
		Cinema cinema = cineplex.cinemas.get(cineplex.movies.indexOf(movieChosen));
		System.out.println("===Dates available for " + movieChosen.title + " at " + cineplex.name + " " + cineplex.location + "===");
		for (int i=0; i<cinema.dates.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cinema.dates.get(i));
		}
		System.out.print("Select date: " );
		int choice = InputHandler.integerInput(1, cinema.dates.size());
		System.out.println();
		return cinema.dates.get(choice);
	}
}

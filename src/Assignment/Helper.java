package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Helper {
	Set<Movie> uniqueMovies;
	User user;
	ArrayList<Cineplex> cineplexes;
	
	public Helper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		this.uniqueMovies = uniqueMovies;
		this.user = user;
		this.cineplexes = cineplexes;
	}
	
	public void printMovies(Set<Movie> uniqueMovies) {
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.status.equals("End of Showing")) {
				System.out.println(count + ". " + m.title + ", Status: " + m.status);
				count += 1;
			}
		}
	}
	
	public void printMovies(Cineplex cineplex) {
		for (int i=0 ; i<cineplex.movies.size(); i++) {
			if (!cineplex.movies.get(i).status.equals("End of Showing")) {
				System.out.println((i+1) + ". " + cineplex.movies.get(i).title + " (" + cineplex.cinemas.get(i).cinema_type + ")");
			}
		}
	}
	
	public void printMovies(Movie movie_chosen, ArrayList<Cineplex> cineplexes) {
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

	public ArrayList<String> createShowtimes(){
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the number of showtimes for the movie");
		int numShows = scan.nextInt();
		scan.nextLine(); // remove the space left
		ArrayList <String> showtimes = new ArrayList <String>();
		for (int i=0; i<numShows; i++) {
			System.out.println("What is showtime " + (i+1) + "?");
			showtimes.add(scan.nextLine());
		}
		return showtimes;
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

	public void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("Select the cineplex:");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println((i+1) + ". " + cineplexes.get(i).name + " " + cineplexes.get(i).location);
		}
	}

	public Cineplex selectCineplex(ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Select Cineplex: ");
		int choice = -1;
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		while (choice <1 || choice >3) {
			choice = scan.nextInt();
			if (choice <1 || choice >3 )
				System.out.println("Please enter a valid selection from 1-3");	
			else
				System.out.println();
		}
		return cineplexes.get(choice-1);
	}

	public Cinema selectCinema(Cineplex cineplex) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Select the cinema:");
		for (int i = 0; i<cineplex.cinemas.size(); i++) {
			System.out.println((i+1) + ". Cinema Code " + cineplex.cinemas.get(i).cinema_code + "(" + cineplex.cinemas.get(i).cinema_type + ")");
		}
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		int choice = scan.nextInt();
		System.out.println();
		return cineplex.cinemas.get(choice-1);
	}

	public void adminView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		Admin admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
		AdminHelper adminHelper = new AdminHelper(uniqueMovies, admin, cineplexes);
		Cineplex cineplex_1 = cineplexes.get(0);
		Cineplex cineplex_2 = cineplexes.get(1);
		Cineplex cineplex_3 = cineplexes.get(2);
		admin.login();
		System.out.println("Welcome, " + admin.getUsername());
		while (1==1) {
			System.out.println("1. Create Movie Listing");
			System.out.println("2. Update Movie Listing (Movie Showing Status)");
			System.out.println("3. Remove Movie Listing");			
			System.out.println("4. Create Cinema Showtimes");
			System.out.println("5. Update Cinema Showtimes");
			System.out.println("6. Remove Cinema Showtimes");
			System.out.println("7. Configure System Settings");
			System.out.println("8. List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("9. Logout to see User view");
			while (!scan.hasNextInt()){
				System.out.println("Error... Please input an Integer");
				scan.nextLine();
			}
			int choice = scan.nextInt();
			scan.nextLine();
			switch (choice){
				case 1:
					adminHelper.admin_1(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
					break;
				case 2:
					adminHelper.admin_2(cineplexes, uniqueMovies);
					break;
				case 3:
					adminHelper.admin_3(cineplexes, uniqueMovies);
					break;
				case 4:
					adminHelper.admin_4(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
					break;
				case 5:
					adminHelper.admin_5(cineplexes, uniqueMovies, admin);
					break;
				case 6:
					adminHelper.admin_6(cineplexes, uniqueMovies, admin);
					break;
				case 7:
					adminHelper.admin_7();
					break;
				case 8:
					adminHelper.admin_8(uniqueMovies);
					break;
				case 9:
					adminHelper.userView(cineplexes, uniqueMovies);
					break;
			}
			System.out.println();
			System.out.println("What would you like to do next?");
		}
	}

	public void userView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		User user = new User("Vincent", "vincentyongweijie@gmail.com", "83189252", 22);
		UserHelper userHelper = new UserHelper(uniqueMovies, user, cineplexes);
		System.out.println("Welcome, " + user.getUsername());
		while (1==1) {
			System.out.println("1. List Movie");
			System.out.println("2. View Movie Details");
			System.out.println("3. Check Seat Availability");
			System.out.println("4. Book Ticket");
			System.out.println("5. View Booking History");
			System.out.println("6. List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("7. Add Rating");
			System.out.println("8. Login as Admin");
			while (!scan.hasNextInt()){
				System.out.println("Error... Please input an Integer");
				scan.nextLine();
			}
			int choice = scan.nextInt();
			switch (choice){
				case 1:
					userHelper.user_1(uniqueMovies, cineplexes);
					break;
				case 2:
					userHelper.user_2(user, uniqueMovies);
					break;
				case 3:
					userHelper.user_3(user, cineplexes);
					break;
				case 4:
					userHelper.user_4(user, cineplexes);
					break;
				case 5:
					userHelper.user_5(user);
					break;
				case 6:
					userHelper.user_6(uniqueMovies);
					break;
				case 7:
					userHelper.user_7(uniqueMovies, user);
					break;
				case 8:
					userHelper.adminView(cineplexes, uniqueMovies);
					break;
				default:
					System.out.println("Please enter a valid choice");
			}
			System.out.println();
			System.out.println("What would you like to do next?");
		}
	}
}

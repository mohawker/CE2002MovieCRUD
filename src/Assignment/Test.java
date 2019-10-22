package Assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> showtimes_1 = new ArrayList<String>();
		showtimes_1.add("0130");showtimes_1.add("1500");showtimes_1.add("1800");showtimes_1.add("1000");showtimes_1.add("0300");
		Cinema cinema_1 = new Cinema("3D", "101", showtimes_1);
		ArrayList<String> cast_1 = new ArrayList<String>();
		cast_1.add("Angelina Jolie");cast_1.add("Elle Fanning");cast_1.add("Michelle Pfeiffer");
		Movie movie_1 = new Movie("Disney's Maleficent: Mistress Of Evil", "Showing", "Maleficent: Mistress of Evil is a fantasy adventure that picks up several years after Maleficent, in which audiences learned of the events that hardened the heart of Disney's most notorious villain and drove her to curse a baby Princess Aurora. The film continues to explore the complex relationship between the horned fairy and the soon to be Queen as they form new alliances and face new adversaries in their struggle to protect the moors and the magical creatures that reside within.", "Joachim Ronning", "Adventure/ Fantasy", cast_1);
		
		ArrayList<String> showtimes_2 = new ArrayList<String>();
		showtimes_2.add("0000");showtimes_2.add("0800");showtimes_2.add("1100");showtimes_2.add("1400");showtimes_2.add("1800");
		Cinema cinema_2 = new Cinema("Normal", "102", showtimes_2);
		ArrayList<String> cast_2 = new ArrayList<String>();
		cast_2.add("Joaquin Phoenix");cast_2.add("Robert De Niro");cast_2.add("Zazie Beetz");
		Movie movie_2 = new Movie("Joker", "Showing", "A failed stand-up comedian is driven insane and becomes a psychopathic murderer.", "Todd Phillips", "Action/ Adventure", cast_2);
			
		ArrayList<String> showtimes_3 = new ArrayList<String>();
		showtimes_3.add("1200");showtimes_3.add("1400");showtimes_3.add("1600");showtimes_3.add("2000");showtimes_3.add("2200");
		Cinema cinema_3 = new Cinema("GoldClass", "103", showtimes_3);
		ArrayList<String> cast_3 = new ArrayList<String>();
		cast_3.add("Will Smith");cast_3.add("Mary Elizabeth Winstead");cast_3.add("Clive Owen");
		Movie movie_3 = new Movie("Gemini Man", "Showing", "Gemini Man is an innovative action-thriller starring Will Smith as Henry Brogan, an elite assassin, who is suddenly targeted and pursued by a mysterious young operative that seemingly can predict his every move. The film is directed by Academy Award-winning filmmaker Ang Lee and produced by renown producers Jerry Bruckheimer, David Ellison, Dana Goldberg and Don Granger. Also starring are Mary Elizabeth Winstead, Clive Owen and Benedict Wong.", "Ang Lee", "Action/ Adventure", cast_3);
			
		ArrayList<String> showtimes_4 = new ArrayList<String>();
		showtimes_4.add("1000");showtimes_4.add("1400");showtimes_4.add("1900");showtimes_4.add("2000");showtimes_4.add("2130");
		Cinema cinema_4 = new Cinema("3D", "104", showtimes_4);
		ArrayList<String> cast_4 = new ArrayList<String>();
		cast_4.add("Brad Pitt");cast_4.add("Tommy Lee Jones");cast_4.add("Ruth Negga");
		Movie movie_4 = new Movie("Ad Astra", "Showing", "Astronaut Roy McBride (Brad Pitt) travels to the outer edges of the solar system to find his missing father and unravel a mystery that threatens the survival of our planet. His journey will uncover secrets that challenge the nature of human existence and our place in the cosmos.", "James Gray", "Action/ Adventure", cast_4);
			
		ArrayList<String> showtimes_5 = new ArrayList<String>();
		showtimes_5.add("1100");showtimes_5.add("1300");showtimes_5.add("1600");showtimes_5.add("2100");showtimes_5.add("2200");
		Cinema cinema_5 = new Cinema("3D", "105", showtimes_5);
		ArrayList<String> cast_5 = new ArrayList<String>();
		cast_5.add("Wu Jing");cast_5.add("Zhang Ziyi");cast_5.add("Jing Boran");
		Movie movie_5 = new Movie("The Climbers", "Showing", "The four members of the China Everest Climbing Commando are attacking the most difficult second step of Mount Everest. This is their fifth attempt. The first four failures have cost them too much physical strength but finally, the wind and snow stop and they have an opportunity to climb.", "Daniel Lee", "Drama", cast_5);
		
		
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinemas.add(cinema_1);cinemas.add(cinema_2);cinemas.add(cinema_3);cinemas.add(cinema_4);cinemas.add(cinema_5);
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(movie_1);movies.add(movie_2);movies.add(movie_3);movies.add(movie_4);movies.add(movie_5);
		
		Cineplex cineplex_1 = new Cineplex("Cathay", "Ang Mo Kio", cinemas, movies);
		Cineplex cineplex_2 = new Cineplex("Cathay", "Bishan", cinemas, movies);
		Cineplex cineplex_3 = new Cineplex("Cathay", "Cineplex", cinemas, movies);
		ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
		cineplexes.add(cineplex_1);cineplexes.add(cineplex_2);cineplexes.add(cineplex_3);
		
		// get unique movies
		Set<Movie> uniqueMovies = new HashSet<>();
		uniqueMovies.addAll(cineplex_1.movies);
		uniqueMovies.addAll(cineplex_2.movies);
		uniqueMovies.addAll(cineplex_3.movies);
		
		System.out.println("Welcome to MOBLIMA App");
		System.out.println("Would you like to continue as a User or an Admin?");
		System.out.println("1. User");
		System.out.println("2. Admin");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if (choice == 1) {
			User user = new User("Vincent", "vincentyongweijie@gmail.com", "83189252", 22);
			System.out.println("Welcome, " + user.getUsername());
			while (1==1) {
				System.out.println("1. List Movie");
				System.out.println("2. View Movie Details");
				System.out.println("3. Check Seat Availability");
				System.out.println("4. Book Ticket");
				System.out.println("5. View Booking History");
				System.out.println("6. List Top 5 Movies by Ticket Sales or Overall Rating");
				System.out.println("7. Login as Admin");
				choice = scan.nextInt();
				switch (choice){
					case 1:
						user_1(uniqueMovies);
						break;
					case 2:
						user_2(user, uniqueMovies);
						break;
					case 3:
						user_3(user, cineplexes);
						break;
					case 4:
						user_4(user, cineplexes);
						break;
					case 5:
						user.viewTicketHistory();
						break;
					case 6:
						// to be implemented
						break;
					case 7:
						// to be implemented
						break;
					default:
						System.out.println("Please enter a valid choice");
				}
				System.out.println();
				System.out.println("What would you like to do next?");
			}
		}else if (choice == 2) {
			Admin admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
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
				choice = scan.nextInt();
				scan.nextLine();
				switch (choice){
					case 1:
						admin_1(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
						break;
					case 2:
						admin_2(cineplexes, uniqueMovies);
						break;
					case 3:
						admin_3(cineplexes, uniqueMovies);
						break;
					case 4:
						admin_4(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
						break;
					case 5:
						admin_5(cineplexes, uniqueMovies);
						break;
					case 6:
						admin_6(cineplexes, uniqueMovies);
						break;
					case 7:
						break;
					case 8:
						break;
					case 9:
						userView(cineplexes, uniqueMovies);
						break;
				}
				System.out.println();
				System.out.println("What would you like to do next?");
			}
		}
	}
	
	public static void userView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		User user = new User("Vincent", "vincentyongweijie@gmail.com", "83189252", 22);
		System.out.println("Welcome, " + user.getUsername());
		while (1==1) {
			System.out.println("1. List Movie");
			System.out.println("2. View Movie Details");
			System.out.println("3. Check Seat Availability");
			System.out.println("4. Book Ticket");
			System.out.println("5. View Booking History");
			System.out.println("6. List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("7. Login as Admin");
			int choice = scan.nextInt();
			switch (choice){
				case 1:
					user_1(uniqueMovies);
					break;
				case 2:
					user_2(user, uniqueMovies);
					break;
				case 3:
					user_3(user, cineplexes);
					break;
				case 4:
					user_4(user, cineplexes);
					break;
				case 5:
					user.viewTicketHistory();
					break;
				case 6:
					// to be implemented
					break;
				case 7:
					// to be implemented
					break;
				default:
					System.out.println("Please enter a valid choice");
			}
			System.out.println();
			System.out.println("What would you like to do next?");
		}
	}
	
	public static void printMovies(Set<Movie> uniqueMovies) {
		System.out.println("Select the movie:");
		int count = 1;
		for (Movie m : uniqueMovies) {
			System.out.println(count + ". " + m.title);
			count += 1;
		}
	}
	
	public static void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("Select the cineplex:");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println((i+1) + ". " + cineplexes.get(i).name + " " + cineplexes.get(i).location);
		}
	}

	public static void user_1(Set<Movie> uniqueMovies) {
		System.out.println("Movie Listings:");
		int count = 1;
		for (Movie m : uniqueMovies) {
			if (!m.status.equals("End of Showing")) {
				System.out.println(count + ". " + m.title + ", Status: " + m.status);
				count += 1;
			}
		}
	}
	
	public static void user_2(User user, Set<Movie> uniqueMovies) {
		// print out titles of unique movie
		Scanner scan = new Scanner(System.in);
		printMovies(uniqueMovies);
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		int choice = scan.nextInt();
		user.viewMovieDetail(uniqueMoviesList.get(choice-1));
	}
	
	public static void user_3(User user, ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		int choice = scan.nextInt();
		Cineplex cineplex_chosen = cineplexes.get(choice-1);
		System.out.println("Select the movie:");
		for (int i=0 ; i<cineplex_chosen.movies.size(); i++) {
			if (!cineplex_chosen.movies.get(i).status.equals("End of Showing")) {
				System.out.println((i+1) + ". " + cineplex_chosen.movies.get(i).title + ", Status: " + cineplex_chosen.movies.get(i).status);
			}
		}
		choice = scan.nextInt();
		Movie movie_chosen = cineplex_chosen.movies.get(choice-1);
		user.viewSeatAvailability(cineplex_chosen, movie_chosen);
	}
	
	public static void user_4(User user, ArrayList<Cineplex> cineplexes){
		Scanner scan = new Scanner(System.in);
		printCineplexes(cineplexes);
		int choice = scan.nextInt();
		Cineplex cineplex_chosen = cineplexes.get(choice-1);
		System.out.println("Select the movie:");
		for (int i=0 ; i<cineplex_chosen.movies.size(); i++) {
			if (!cineplex_chosen.movies.get(i).status.equals("End of Showing")) {
				System.out.println((i+1) + ". " + cineplex_chosen.movies.get(i).title + ", Status: " + cineplex_chosen.movies.get(i).status);
			}
		}
		choice = scan.nextInt();
		Movie movie_chosen = cineplex_chosen.movies.get(choice-1);
		MovieTicket ticket = user.bookPurchaseTicket(cineplex_chosen, movie_chosen);
		user.addTicket(ticket);
	}
	
	public static Movie admin_1(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the name of movie?");
		String title = scan.nextLine();
		System.out.println("What is the status of movie? (Coming Soon/Preview)");
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
	
	public static void admin_2(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		user_1(uniqueMovies);
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		scan.nextLine();
		Movie movie_chosen = uniqueMoviesList.get(choice-1);
		System.out.println("What is the new showing status of " + movie_chosen.title + "? (Preview/Showing/End of Showing)");
		
		String newStatus = scan.nextLine();
		if (movie_chosen.status.equals(newStatus)) {
			System.out.println("New status same as previous status");
			System.out.println("Shall not update movie status");
		}else{
			movie_chosen.status = newStatus;
			printCineplexes(cineplexes);
			choice = scan.nextInt();
			scan.nextLine();
			Cineplex cineplex_chosen = cineplexes.get(choice-1);
			if (newStatus.equals("Showing")) { // from preview to showing and coming soon to showing 
				System.out.println("What is the number of showtimes for the movie");
				int numShows = scan.nextInt();
				scan.nextLine();; // remove the space left
				ArrayList <String> showtimes = new ArrayList <String>();
				for (int i=0; i<numShows; i++) {
					System.out.println("What is showtime " + (i+1) + "?");
					showtimes.add(scan.nextLine());
				}
				
				System.out.println("Select the cinema:");
				for (int i = 0; i<cineplex_chosen.cinemas.size(); i++) {
					System.out.println((i+1) + ". Cinema Code " + cineplex_chosen.cinemas.get(i).cinema_code + "(" + cineplex_chosen.cinemas.get(i).cinema_type + ")");
				}
				choice = scan.nextInt();
				Cinema cinema_chosen = cineplex_chosen.cinemas.get(choice-1);
				
				cineplex_chosen.movies.get(choice-1).status = "End of Showing";
				System.out.println("Movie being replaced is " + cineplex_chosen.movies.get(choice-1).title);
				cineplex_chosen.movies.set(choice-1, movie_chosen);
				System.out.println("New movie is " + cineplex_chosen.movies.get(choice-1).title);
				cineplex_chosen.cinemas.get(choice-1).showtimes = showtimes;
				System.out.println("Movie " + movie_chosen.title + " added to " + cineplex_chosen.name + " " + cineplex_chosen.location + " in Cinema Code " + cinema_chosen.cinema_code);
			}
		}
	}
	
	public static void admin_3(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		user_1(uniqueMovies);
		List<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		scan.nextLine();
		System.out.println(uniqueMoviesList.get(choice-1).title + " will be set to End of Showing");
		uniqueMoviesList.get(choice-1).status = "End of Showing";
	}
	
	public static void admin_4(Admin admin, ArrayList<Cineplex> cineplexes, Cineplex cineplex_1, Cineplex cineplex_2, Cineplex cineplex_3, Set<Movie> uniqueMovies) {
		Movie movie = admin_1(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
		movie.status = "Showing";
		user_1(uniqueMovies);
		System.out.println("What is the number of showtimes for the movie");
		Scanner scan = new Scanner(System.in);
		int numShows = scan.nextInt();
		scan.nextLine();; // remove the space left
		ArrayList <String> showtimes = new ArrayList <String>();
		for (int i=0; i<numShows; i++) {
			System.out.println("What is showtime " + (i+1) + "?");
			showtimes.add(scan.nextLine());
		}
			
		printCineplexes(cineplexes);
		int choice = scan.nextInt();
		Cineplex cineplex_chosen = cineplexes.get(choice-1);
			
		System.out.println("Select the cinema:");
		for (int i = 0; i<cineplex_chosen.cinemas.size(); i++) {
			System.out.println((i+1) + ". Cinema Code " + cineplex_chosen.cinemas.get(i).cinema_code + "(" + cineplex_chosen.cinemas.get(i).cinema_type + ")");
		}
		choice = scan.nextInt();
		Cinema cinema_chosen = cineplex_chosen.cinemas.get(choice-1);
		
		cineplex_chosen.movies.get(choice-1).status = "End of Showing";
		cineplex_chosen.movies.set(choice-1, movie);
		cineplex_chosen.cinemas.get(choice-1).showtimes = showtimes;
			
		System.out.println("Movie " + movie.title + " added to " + cineplex_chosen.name + " " + cineplex_chosen.location + " in Cinema Code " + cinema_chosen.cinema_code);
	}
	
	public static void admin_5(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		printMovies(uniqueMovies);
		int index = scan.nextInt() - 1;
		
		printCineplexes(cineplexes);
		int choice = scan.nextInt();
		
		ArrayList<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		Movie movieChosen = uniqueMoviesList.get(index);
		
		index = 0;
		for (int i=0; i<cineplexes.get(choice-1).movies.size(); i++) {
			if (cineplexes.get(choice-1).movies.get(i).title.equals(movieChosen.title)) {
				index += 1;
				break;
			}
			index += 1;
		}
		ArrayList <String> currShowtimes = cineplexes.get(choice-1).cinemas.get(index-1).showtimes;
		
		System.out.println("How many showtimes for " + movieChosen.title + " would you like to insert?");
		choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Showtime: ");
			currShowtimes.add(scan.nextLine());
		}
		Collections.sort(currShowtimes);
		cineplexes.get(choice-1).cinemas.get(index).showtimes = currShowtimes;
	}
	
	public static void admin_6(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		printMovies(uniqueMovies);
		int index = scan.nextInt() - 1;
		
		printCineplexes(cineplexes);
		int choice = scan.nextInt();
		
		ArrayList<Movie> uniqueMoviesList = new ArrayList<Movie>(uniqueMovies);
		Movie movieChosen = uniqueMoviesList.get(index);
		
		index = 0;
		for (int i=0; i<cineplexes.get(choice-1).movies.size(); i++) {
			if (cineplexes.get(choice-1).movies.get(i).title.equals(movieChosen.title)) {
				index += 1;
				break;
			}
			index += 1;
		}
		ArrayList <String> currShowtimes = cineplexes.get(choice-1).cinemas.get(index-1).showtimes;
		
		System.out.println("Current Show Times for " + movieChosen.title + " are " + currShowtimes);
		System.out.println("How many showtimes for " + movieChosen.title + " would you like to remove?");
		choice = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<choice; i++) {
			System.out.print("Showtime: ");
			currShowtimes.remove(scan.nextLine());
			System.out.println("Current Show Times for " + movieChosen.title + " are " + currShowtimes);
		}
		cineplexes.get(choice-1).cinemas.get(index-1).showtimes = currShowtimes;
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static void test_movieticket_getandset_price() {
//		ArrayList<String> showtimes = new ArrayList<String>();
//		showtimes.add("0130");
//		Cinema cinema = new Cinema("3D", "101", showtimes);
//		ArrayList<String> cast = new ArrayList<String>();
//		cast.add("Tom Cruise");
//		Movie movie = new Movie("title", "status", "synopsis", "director", "type", cast);
//		MovieTicket ticket = new MovieTicket(movie, cinema, "0130");
//		ticket.setPrice();
//		float price = ticket.getPrice();
//	}
//	
//	public static void test_cinema_book_and_view() {
//		ArrayList<String> showtimes = new ArrayList<String>();
//		showtimes.add("0130");
//		ArrayList<String> cast = new ArrayList<String>();
//		cast.add("Tom Cruise");
//		Movie movie = new Movie("title", "status", "synopsis", "director", "type", cast);
//		Cinema cinema = new Cinema("Normal", "101", showtimes);
//		for (int i=0; i<10; i++) {
//			cinema.bookSeats("0130", movie);
//		}
//	}
//	
//	public static void test_movie_and_review(){
//		Scanner scan = new Scanner(System.in);
//		System.out.println("What is the name of movie?");
//		String title = scan.nextLine();
//		System.out.println("What is the status of movie?");
//		String status = scan.nextLine();
//		System.out.println("What is the synopsis of the movie");
//		String synopsis = scan.nextLine();
//		System.out.println("What is the director of the movie");
//		String director = scan.nextLine();
//		System.out.println("What is the type of the movie");
//		String type = scan.nextLine();
//		
//		System.out.println("What is the number of cast members of the movie");
//		int numCast = scan.nextInt();
//		scan.nextLine();; // remove the space left
//		ArrayList <String> cast = new ArrayList <String>();
//		for (int i=0; i<numCast; i++) {
//			System.out.println("Who is cast member " + (i+1) + "?");
//			cast.add(scan.nextLine());
//		}
//		
//		Movie movie = new Movie(title, status, synopsis, director, type, cast);
//		
//		while (1==1) {
//			System.out.println("1. Add Review");
//			System.out.println("2. Quit");
//			System.out.println("Choice:");
//			int choice = scan.nextInt();
//			scan.nextLine();; // remove the space left
//			if (choice == 1) {
//				System.out.println("Comment:");
//				String comment = scan.nextLine();
//				System.out.println("Rating:");
//				int rating = scan.nextInt();
//				Review review = new Review(comment, rating, "123", "456", movie.title);
//				movie.addReview(review);
//				movie.printReviews();
//			}else {
//				break;
//			}
//		}
//	}

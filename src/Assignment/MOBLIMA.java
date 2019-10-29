package Assignment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;   

public class MOBLIMA {

	public static void main(String[] args) {
		
		Cinema[] cinemas;
		Movie[] movies;
		cinemas = Initialiser.generateCinemas();
		movies = Initialiser.generateMovies();
			
		ArrayList<Cineplex> cineplexes = Initialiser.generateCineplexes(cinemas, movies);
		Cineplex cineplex_1 = cineplexes.get(0);
		Cineplex cineplex_2 = cineplexes.get(1);
		Cineplex cineplex_3 = cineplexes.get(2);
		
		Set<Movie> uniqueMovies = new HashSet<>();
		uniqueMovies = Initialiser.generateMovies(cineplexes);
		
		System.out.println("Welcome to MOBLIMA App");
		System.out.println("Would you like to continue as a User or an Admin?");
		System.out.println("[1] User");
		System.out.println("[2] Admin");
		int choice = InputHandler.integerInput(1, 2);
		if (choice == 1) {
			System.out.print("Enter your name: ");
			String userName = InputHandler.stringInput();
			System.out.print("Enter your age: ");
			int userAge = InputHandler.integerInput(1, 150);
			User user = new User(userName, "vincentyongweijie@gmail.com", "83189252", userAge);
			UserHelper userHelper = new UserHelper(uniqueMovies, user, cineplexes);
			ViewHelper viewHelper = new ViewHelper(uniqueMovies, user, cineplexes);
			System.out.println("------------------------------------------------------");
			System.out.println("Welcome, " + user.getUsername());
			System.out.printf("The date is: %s\n", java.time.LocalDate.now());
			while (true) {
				System.out.println("------------------------------------------------------");
				System.out.println("[0] Search Movie");
				System.out.println("[1] List Movie");
				System.out.println("[2] View Movie Details");
				System.out.println("[3] Check Seat Availability");
				System.out.println("[4] Book Ticket");
				System.out.println("[5] View Booking History");
				System.out.println("[6] List Top 5 Movies by Ticket Sales or Overall Rating");
				System.out.println("[7] Add Rating");
				System.out.println("[8] Login as Admin");
				System.out.println("[9] Log Off & Shut Down");
				System.out.println("------------------------------------------------------");
				System.out.print("Please select (0-9) : ");
				
				// error checker class - checkInt , checkStr etc
				choice = InputHandler.integerInput(0, 9);
				System.out.println();
				switch (choice){
					case 0:{userHelper.searchUniqueMovies(uniqueMovies, cineplexes);break;}
					case 1:{userHelper.listUniqueMovies(uniqueMovies, cineplexes);break;}
					case 2:{userHelper.viewMovieDetails(user, uniqueMovies);break;}
					case 3:{userHelper.checkSeatAvailability(user, cineplexes);break;}
					case 4:{userHelper.bookTicket(user, cineplexes);break;}
					case 5:{userHelper.viewBookingHistory(user);break;}
					case 6:{userHelper.listTop5(uniqueMovies);break;}
					case 7:{userHelper.addRating(uniqueMovies, user);break;}
					case 8:{viewHelper.adminView(cineplexes, uniqueMovies);break;}
					case 9:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return;}	
					default:{System.out.println("Please enter a valid choice");}
				}
				System.out.println("\nWhat would you like to do next?");
			}
		}else if (choice == 2) {
			Admin admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
			AdminHelper adminHelper = new AdminHelper(uniqueMovies, admin, cineplexes);
			ViewHelper viewHelper = new ViewHelper(uniqueMovies, admin, cineplexes);
			admin.login();
			System.out.println("Welcome, " + admin.getUsername());
			while (1==1) {
				System.out.println("------------------------------------------------------");
				System.out.println("[1] Create Movie Listing");
				System.out.println("[2] Update Movie Listing (Movie Showing Status)");
				System.out.println("[3] Remove Movie Listing");			
				System.out.println("[4] Create Cinema Showtimes");
				System.out.println("[5] Update Cinema Showtimes");
				System.out.println("[6] Remove Cinema Showtimes");
				System.out.println("[7] Configure System Settings");
				System.out.println("[8] List Top 5 Movies by Ticket Sales or Overall Rating");
				System.out.println("[9] Logout to see User view");
				System.out.println("[10] Log Off & Shut Down");
				System.out.println("------------------------------------------------------");
				System.out.print("Please select (1-10) : ");
				choice = InputHandler.integerInput(1, 10);
				switch (choice){
					case 1:{adminHelper.createMovieListing(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);break;}
					case 2:{adminHelper.updateMovieListing(cineplexes, uniqueMovies);break;}
					case 3:{adminHelper.removeMovieListing(cineplexes, uniqueMovies);break;}
					case 4:{adminHelper.createCinemaShowtimes(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);break;}
					case 5:{adminHelper.updateCinemaShowtimes(cineplexes, uniqueMovies, admin);break;}
					case 6:{adminHelper.removeCinemaShowtimes(cineplexes, uniqueMovies, admin);break;}
					case 7:{adminHelper.configureSettings();break;}
					case 8:{adminHelper.listTop5(uniqueMovies);break;}
					case 9:{viewHelper.userView(cineplexes, uniqueMovies);break;}
					case 10:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return;}	
					default:{System.out.println("Please enter a valid choice");}
				}
				System.out.println();
				System.out.println("\nWhat would you like to do next?");
			}
		}
	}
}	

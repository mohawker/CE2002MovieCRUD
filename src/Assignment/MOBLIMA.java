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
		int choice = InputControl.integerInput(1, 2);
		if (choice == 1) {
			System.out.print("Enter your name: ");
			String userName = InputControl.stringInput();
			System.out.print("Enter your age: ");
			int userAge = InputControl.integerInput(1, 150);
			User user = new User(userName, "vincentyongweijie@gmail.com", "83189252", userAge);
			UserControl userControl = new UserControl(uniqueMovies, user, cineplexes);
			ViewControl viewHelper = new ViewControl(uniqueMovies, user, cineplexes);
			BookingManager bookingManager = new BookingManager(uniqueMovies, user, cineplexes);
			ReviewManager reviewManager = new ReviewManager(uniqueMovies, user, cineplexes);
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
				choice = InputControl.integerInput(0, 9);
				System.out.println();
				switch (choice){
					case 0:{userControl.searchUniqueMovies(uniqueMovies, cineplexes);break;}
					case 1:{userControl.listUniqueMovies(uniqueMovies, cineplexes);break;}
					case 2:{userControl.viewMovieDetails(user, uniqueMovies);break;}
					case 3:{userControl.checkSeatAvailability(user, cineplexes);break;}
					case 4:{bookingManager.bookTicket(user, cineplexes);break;}
					case 5:{userControl.viewBookingHistory(user);break;}
					case 6:{userControl.listTop5(uniqueMovies);break;}
					case 7:{reviewManager.addRating(uniqueMovies, user);break;}
					case 8:{viewHelper.adminView(cineplexes, uniqueMovies);break;}
					case 9:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return;}	
					default:{System.out.println("Please enter a valid choice");}
				}
				System.out.println("\nWhat would you like to do next?");
			}
		}else if (choice == 2) {
			Admin admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
			AdminControl adminHelper = new AdminControl(uniqueMovies, admin, cineplexes);
			ViewControl viewHelper = new ViewControl(uniqueMovies, admin, cineplexes);
			admin.login();
			System.out.println("Welcome, " + admin.getUsername());
			while (1==1) {
				System.out.println("------------------------------------------------------");
				System.out.println("[1] Create Movie Listing");
				System.out.println("[2] Update Movie Listing (Movie Showing Status)");
				System.out.println("[3] Remove Movie Listing");			
				System.out.println("[4] Create Cinema Showtimes (For Movies with status 'Coming Soon' or 'Preview')");
				System.out.println("[5] Update Cinema Showtimes (For Movie with status 'Showing')");
				System.out.println("[6] Add Cinema Showtimes (For Movie with status 'Showing')");
				System.out.println("[7] Remove Cinema Showtimes (For Movie with status 'Showing')");
				System.out.println("[8] Configure System Settings");
				System.out.println("[9] Add a Public Holiday");
				System.out.println("[10] List Top 5 Movies by Ticket Sales or Overall Rating");
				System.out.println("[11] Logout to see User View");
				System.out.println("[12] Log Off & Shut Down");
				System.out.println("------------------------------------------------------");
				System.out.print("Please select (1-12) : ");
				choice = InputControl.integerInput(1, 12);
				switch (choice){
					case 1:{adminHelper.createMovieListing(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);break;}
					case 2:{adminHelper.updateMovieListing(cineplexes, uniqueMovies);break;}
					case 3:{adminHelper.removeMovieListing(cineplexes, uniqueMovies);break;}
					case 4:{adminHelper.createCinemaShowtimes(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);break;}
					case 5:{adminHelper.updateCinemaShowtimes(cineplexes, uniqueMovies, admin);break;}
					case 6:{adminHelper.addCinemaShowtimes(cineplexes, uniqueMovies, admin); break;}
					case 7:{adminHelper.removeCinemaShowtimes(cineplexes, uniqueMovies, admin);break;}
					case 8:{adminHelper.configureSettings();break;}
					case 9:{adminHelper.addNewHoliday();break;}
					case 10:{adminHelper.listTop5(uniqueMovies);break;}
					case 11:{viewHelper.userView(cineplexes, uniqueMovies);break;}
					case 12:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return;}	
					default:{System.out.println("Please enter a valid choice");}
				}
				System.out.println();
				System.out.println("\nWhat would you like to do next?");
			}
		}
	}
}	
